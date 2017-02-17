package shoemixx.inventory.model;

import java.math.BigInteger;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;


//probably andito yung password encryption
public class UserBean {
	private String username;
//	private String password;
	private String privilege;
	private String firstName;
	private String lastName;
	
	public String getFirstName(){
		return firstName;
	}
	
	public String getLastName(){
		return lastName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
//	public String getPassword() {
//		return password;
//	}
//	public void setPassword(String password) {
//		this.password = password;
//	}
	public String getPrivilege() {
		return privilege;
	}
	public void setPrivilege(String privilege) {
		this.privilege = privilege;
	}
	
	public boolean isUsernameValid(String username, Connection connection){
		ResultSet account = null;
		boolean valid = false;
		try {
			connection.setAutoCommit(false);
			String sql = "SELECT * "
					   + "FROM user "
					   + "WHERE username = ?";	
			
			PreparedStatement pstmnt = 
					connection.prepareStatement(sql);
			
			pstmnt.setString(1, username);
			account = pstmnt.executeQuery();
			connection.commit();
			System.out.println("account is? "+account.isBeforeFirst());
			if(account.isBeforeFirst()) {
				valid = true;
				setUsername(username);
			}
			else
				valid = false;
		} catch (SQLException sqle) {
			try{
				sqle.printStackTrace();
				System.err.print("Transaction is being rolled back");
				connection.rollback();
			}catch(SQLException sqlexc){
				sqlexc.printStackTrace();
			}
		}
		return valid;
	}
	
	public boolean isPasswordValid(String password, Connection connection) throws NoSuchAlgorithmException, InvalidKeySpecException {
		ResultSet account = null;
		boolean valid = false;
		try {
			connection.setAutoCommit(false);
			String sql = "SELECT * "
					   + "FROM user "
					   + "WHERE username = ?";	
			
			PreparedStatement pstmnt = 
					connection.prepareStatement(sql);
			
			pstmnt.setString(1, username);
			account = pstmnt.executeQuery();
			account.next();
			connection.commit();

//			if(password.equals(account.getString("password"))) 
			if(checkPassword(password, account.getString("password"))){
				valid = true;
				System.out.println(" username: "+account.getString("username")
								  +" privilege: "+account.getString("privilege"));
				System.out.println("successful log in");
				
//				setPassword(account.getString("password"));
				setUsername(account.getString("username"));
				setPrivilege(account.getString("privilege"));
				
			}
			else
				valid = false;
		} catch (SQLException sqle) {
			try{
				sqle.printStackTrace();
				System.err.print("Transaction is being rolled back");
				connection.rollback();
			}catch(SQLException sqlexc){
				sqlexc.printStackTrace();
			}
		}
		return valid;
	}
	
	public void addUserAccount(Connection connection, String username,
							   String password, String firstName, String lastName,
							   String userPrivilege) throws NoSuchAlgorithmException, InvalidKeySpecException{
		try{
			connection.setAutoCommit(false);
			String sql = "INSERT into user"
					   + "(privilege, username, password, "
					   + "firstname, lastname) values (?,?,?,?,?)";
			password = generatePasswordHash(password);
			PreparedStatement pstmnt = connection.prepareStatement(sql);
			pstmnt.setString(1, userPrivilege);
			pstmnt.setString(2, username);
			pstmnt.setString(3, password);
			pstmnt.setString(4, firstName);
			pstmnt.setString(5, lastName);
			
			pstmnt.executeUpdate();
			connection.commit();
		} catch(SQLException sqle) {
			try {
				sqle.printStackTrace();
				System.err.println("Transaction is being rolled back");
				connection.rollback();
			} catch(SQLException sqlexc){
				sqlexc.printStackTrace();
			}
		}
	}
	
	//password encryption uses PBKDF2WithHmacSHA1 hashing
	private static String generatePasswordHash(String password) throws NoSuchAlgorithmException, InvalidKeySpecException {
		
		int iterations = 1000;
		char[] chars = password.toCharArray();
		byte[] salt = getSalt();
		
		PBEKeySpec spec = new PBEKeySpec(chars, salt, iterations, 64 * 8);
        SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
        byte[] hash = skf.generateSecret(spec).getEncoded();
        System.out.println(iterations + ":" + toHex(salt) + ":" + toHex(hash));
        return iterations + ":" + toHex(salt) + ":" + toHex(hash);
	}
	
	//get salt for better security avoid rainbow table hack
	private static byte[] getSalt() throws NoSuchAlgorithmException {
		SecureRandom sr = SecureRandom.getInstance("SHA1PRNG");
        byte[] salt = new byte[16];
        sr.nextBytes(salt);
        return salt;
	}
	
	private static byte[] fromHex(String hex) throws NoSuchAlgorithmException {
        byte[] bytes = new byte[hex.length() / 2];
        for(int i = 0; i<bytes.length ;i++) {
            bytes[i] = (byte)Integer.parseInt(hex.substring(2 * i, 2 * i + 2), 16);
        }
        return bytes;
    }
	
	private static String toHex(byte[] array) throws NoSuchAlgorithmException {
		BigInteger bi = new BigInteger(1, array);
        String hex = bi.toString(16);
        int paddingLength = (array.length * 2) - hex.length();
        if(paddingLength > 0) {
            return String.format("%0"  +paddingLength + "d", 0) + hex;
        } else {
            return hex;
        }
	}
	
	private static boolean checkPassword(String password, String hashedPassword) throws NoSuchAlgorithmException, InvalidKeySpecException {
		System.out.println("Hashed PW: "+hashedPassword);
		System.out.println("Password: "+password);
		String[] parts = hashedPassword.split(":");
        int iterations = Integer.parseInt(parts[0]);
        byte[] salt = fromHex(parts[1]);
        byte[] hash = fromHex(parts[2]);
         
        PBEKeySpec spec = new PBEKeySpec(password.toCharArray(), salt, iterations, hash.length * 8);
        SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
        byte[] testHash = skf.generateSecret(spec).getEncoded();
         
        int diff = hash.length ^ testHash.length;
        for(int i = 0; i < hash.length && i < testHash.length; i++)
        {
            diff |= hash[i] ^ testHash[i];
        }
        return diff == 0;
	}
	
	public boolean isValidUserAccount(String firstName, String lastName, 
								      String username, String password, 
								      String userPrivilege, Connection connection) {
		boolean isValid = true;
		//check name not suer kung anong hindi pwede bawal numerals?
		
		if(userPrivilege.equals("admin") || userPrivilege.equals("staff") ||
			  userPrivilege.equals("cashier")) { //double checks if chosen privilege is incorrect
			System.out.println(userPrivilege);
			isValid = true;
		} else if(isUsernameValid(username, connection) || username.length() > 50) { //check if username > 50 length and exists in database
			System.out.println("username invalid");
			isValid = false;
		} else if(password.length() < 6) {//check password length
			System.out.println("password invalid");
			isValid = false;
		} else if(isLetterOnly(password)) {
			System.out.println("passw invalid");
			isValid = false;
		} else if(isNumberOnly(password)) {
			System.out.println("passw invalid");
			isValid = false;
		} else if(password.matches("[A-Za-z0-9!#$%&()*+,.\\/:;<=>?@[\\]^_`|~-]+$]+")) { //regex for not allowing special symbols
			isValid = false;
		}
		
		//else if(password.matches("[^a-zA-Z0-9!@#$%^&*()_+.,;:]/")){
//			
//		}
			
		

		//check userPrivilege
		
		return isValid;
		
	}
	
	public boolean isLetterOnly(String name) {
	    char[] chars = name.toCharArray();

	    for (char c : chars) {
	        if(!Character.isLetter(c)) {
	            return false;
	        }
	    }

	    return true;
	}
	
	public boolean isNumberOnly(String name) {
	    char[] chars = name.toCharArray();

	    for (char c : chars) {
	        if(!Character.isDigit(c)) {
	            return false;
	        }
	    }

	    return true;
	}
	
	//get all user accounts return a dataset for it
	public ResultSet getAllRecords(Connection connection) {
		ResultSet records = null;
		try {
			connection.setAutoCommit(false);
			String sql = "SELECT username, lastname, firstname, privilege FROM user";
			
			PreparedStatement pstmnt = 
					connection.prepareStatement(sql);
			
			records = pstmnt.executeQuery();
			
			connection.commit();
		} catch(SQLException sqle) {
			try {
				sqle.printStackTrace();
				System.err.println("Transaction is being rolled back");
				connection.rollback();
			} catch(SQLException sqlexc){
				sqlexc.printStackTrace();
			}
		}
		return records;
	}
	
	//used for edit account
	public ResultSet obtainAccount(Connection connection, String key) {
		ResultSet account = null;
		try {
			connection.setAutoCommit(false);
			String sql = "SELECT * "
					   + "FROM user "
					   + "WHERE username = ?";
			
			
			PreparedStatement pstmnt = 
					connection.prepareStatement(sql);
			
			pstmnt.setString(1, key);
			
			account = pstmnt.executeQuery();
			
			connection.commit();
		} catch(SQLException sqle) {
			try {
				sqle.printStackTrace();
				System.err.println("Transaction is being rolled back");
				connection.rollback();
			} catch(SQLException sqlexc){
				sqlexc.printStackTrace();
			}
		}
		return account;
	}
	
	public static void changePassword(String newPassword, Connection connection, String key) throws NoSuchAlgorithmException, InvalidKeySpecException {
		try {
			connection.setAutoCommit(false);
			String sql = "UPDATE user "
					   + "SET password = ? "
					   + "WHERE username = ? ";
			String generateHashedPass = generatePasswordHash(newPassword);
			PreparedStatement pstmnt = connection.prepareStatement(sql);
			pstmnt.setString(1, generateHashedPass);
			pstmnt.setString(2, key);

			
			pstmnt.executeUpdate();
			connection.commit();
			System.out.println("successful change of password");
		} catch(SQLException sqle) {
			try {
				sqle.printStackTrace();
				System.err.println("Transaction is being rolled back");
				connection.rollback();
			} catch(SQLException sqlexc){
				sqlexc.printStackTrace();
			}
		}
	}
	
	public static void changeAccountDetails(String firstNameNewVal, String lastNameNewVal, String privilegeNewVal, String key, Connection connection) {
		try {
			connection.setAutoCommit(false);
			String sql = "UPDATE user "
					   + "SET firstname = ?, lastname = ?, privilege = ? "
					   + "WHERE username = ? ";
			
			PreparedStatement pstmnt = connection.prepareStatement(sql);
			pstmnt.setString(1, firstNameNewVal);
			pstmnt.setString(2, lastNameNewVal);
			pstmnt.setString(3, privilegeNewVal);
			pstmnt.setString(4, key);

			
			pstmnt.executeUpdate();
			connection.commit();
			System.out.println("successful change of account details");
		} catch(SQLException sqle) {
			try {
				sqle.printStackTrace();
				System.err.println("Transaction is being rolled back");
				connection.rollback();
			} catch(SQLException sqlexc){
				sqlexc.printStackTrace();
			}
		}
	}
		
}
