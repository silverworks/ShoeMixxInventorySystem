package shoemixx.inventory.utility;
import java.sql.*;

import javax.sql.DataSource;
import javax.naming.*;

public class DBConnectionUtil {
	
	public static Connection getDBConnection(String branch){
		Connection connection = null;
		try {
			/*Context initContext = new InitialContext();
			Context envContext = (Context) initContext.lookup("java:/comp/env");
			
			DataSource ds = (DataSource)envContext.lookup("jdbc/ICS114-3CSB-DB");
			connection = ds.getConnection();*/
			String dbpath = "";
			if(branch.equals("Branch 1")){
				dbpath = "java:/comp/env/jdbc/shoemixx-branch1";
				connection = ((DataSource) InitialContext.doLookup(dbpath))
						.getConnection();
			}
			else if(branch.equals("Branch 2")){
				dbpath = "java:/comp/env/jdbc/shoemixx-branch2";
				connection = ((DataSource) InitialContext.doLookup(dbpath))
						.getConnection();
			}
			else if(branch.equals("Branch 3")){
				dbpath = "java:/comp/env/jdbc/shoemixx-branch3";
				connection = ((DataSource) InitialContext.doLookup(dbpath))
						.getConnection();
			}
			
			
			System.out.println((connection != null)
				?"successful connection"
				:"unable to connect to db server");
		} catch (NamingException ne) {
			ne.printStackTrace();
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
		
		return connection;
	}
	
	public static Connection getDBConnection(){
		Connection connection = null;
		try{
			connection = ((DataSource) InitialContext.doLookup("java:/comp/env/jdbc//shoemixx-DB")).getConnection();
			System.out.println((connection != null)
				?"successful connection"
				:"unable to connect to db server");	
		}catch(NamingException ne) {
			ne.printStackTrace();
		}catch(SQLException sqle) {
			sqle.printStackTrace();
		}
		return connection;
	}
}
