package shoemixx.inventory.model;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

public class ProductBean {
	
	//old
//	private String productName;
//	private int productQuantity;
//	private double productPrice;
//	
//	public String getProductName() {
//		return productName;
//	}
//	public void setProductName(String productName) {
//		this.productName = productName;
//	}
//	public int getProductQuantity() {
//		return productQuantity;
//	}
//	public void setProductQuantity(int productQuantity) {
//		this.productQuantity = productQuantity;
//	}
//	public double getProductPrice() {
//		return productPrice;
//	}
//	public void setProductPrice(double productPrice) {
//		this.productPrice = productPrice;
//	}
	
	//new
	private String productCode;
	private String productColor;
	private int quantity;
	private int size;
	
	//for sales
	private double productPrice;
	private String customerName;
	private String datePurchased;
	private double purchaseValue;
	
	public double getPurchaseValue() {
		return purchaseValue;
	}

	public void setPurchaseValue(double purchaseValue) {
		this.purchaseValue = purchaseValue;
	}

	public double getProductPrice() {
		return productPrice;
	}

	public void setProductPrice(double productPrice) {
		this.productPrice = productPrice;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getDatePurchased() {
		return datePurchased;
	}

	public void setDatePurchased(String datePurchased) {
		this.datePurchased = datePurchased;
	}

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public String getProductColor() {
		return productColor;
	}

	public void setProductColor(String productColor) {
		this.productColor = productColor;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}
	
	public double computePurchaseValue(Connection connection, String key, String purchased) {
		double purchaseValue = 0;
		ResultSet value = null;
		try {
			connection.setAutoCommit(false);
			String sql = "SELECT PRICE "
					   + "FROM PRODUCTS "
					   + "WHERE ID="+key;
			PreparedStatement pstmnt = 
					connection.prepareStatement(sql);
			
			value = pstmnt.executeQuery();
			if(value.next()){
				purchaseValue = value.getDouble("PRICE") * Double.parseDouble(purchased);
			}
			
			
			System.out.println("Purchased ITEM_ID: "+key);
			System.out.println("Quantity: "+purchased);
			System.out.println("Total Value: "+purchaseValue);
			
		} catch(SQLException sqle) {
			try{
				sqle.printStackTrace();
				System.err.print("Transaction is being rolled back");
				connection.rollback();
			}catch(SQLException sqlexc){
				sqlexc.printStackTrace();
			}
		}
		return purchaseValue;
	}
	
	public void purchaseItem(Connection connection, String key, String purchased, String customerName) {
		try{
			connection.setAutoCommit(false);
//			String sql = "UPDATE products "
//					   + "SET QUANTITY = QUANTITY -" + purchased + " "
//					   + "WHERE ID = "+ key;
//
//			PreparedStatement pstmnt = 
//					connection.prepareStatement(sql);
//			pstmnt.executeUpdate();
//			connection.commit();
			
			reduceStockCount(connection, key, purchased);
			
			String sql2 = "insert into sales(ORDER_DATE, " +
					"PRODUCT_ID, QUANTITY_PURCHASED, CUSTOMER_NAME, PURCHASE_VALUE) values(?,?,?,?,?)";
			
			PreparedStatement pstmnt2 = 
				connection.prepareStatement(sql2);
			pstmnt2.setString(1, datePurchased);
			pstmnt2.setString(2, key);
			pstmnt2.setInt(3, Integer.parseInt(purchased));
			pstmnt2.setString(4, customerName);
			pstmnt2.setDouble(5, computePurchaseValue(connection, key, purchased));
			
			//commit to the database
			pstmnt2.executeUpdate();
			connection.commit();
			
//			System.out.println("removed "+subtracted+" units of "+key);
			
		} catch (SQLException sqle){
			try{
				sqle.printStackTrace();
				System.err.print("Transaction is being rolled back");
				connection.rollback();
			}catch(SQLException sqlexc){
				sqlexc.printStackTrace();
			}
		}
	}
	
	public ResultSet searchSpecific(Connection connection, String key) {
		ResultSet records = null;
		try {
			connection.setAutoCommit(false);
			//String sql = "SELECT * FROM products WHERE product_name = ?";	// searches exact string
			String sql = "SELECT * FROM products WHERE ID = ?";	// accepts exact stringsssss
			
			PreparedStatement pstmnt = 
					connection.prepareStatement(sql);
			
			pstmnt.setString(1, key);		//exact string lang
			
			records = pstmnt.executeQuery();
			
			connection.commit();

		} catch (SQLException sqle) {
			try{
				System.err.print("Transaction is being rolled back");
				
				connection.rollback();
				
			}catch(SQLException sqlexc){
				sqlexc.printStackTrace();
			}
		}
		
		return records;
	}
	
	public ResultSet search(Connection connection, String key) {
		ResultSet records = null;
		try {
			connection.setAutoCommit(false);
			//String sql = "SELECT * FROM products WHERE product_name = ?";	// searches exact string
			String sql = "SELECT * FROM products WHERE STOCK_CODE LIKE ?";	// accepts exact stringsssss
			
			PreparedStatement pstmnt = 
					connection.prepareStatement(sql);
			
			//pstmnt.setString(1, key);		//exact string lang
			pstmnt.setString(1, "%" + key + "%");	// accepts substring
			records = pstmnt.executeQuery();
			connection.commit();
			System.out.println("records is: "+records.isBeforeFirst());
		} catch (SQLException sqle) {
			try{
				System.err.print("Transaction is being rolled back");
				
				connection.rollback();
				
			}catch(SQLException sqlexc){
				sqlexc.printStackTrace();
			}
		}
		
		return records;
	}
	
	public void insertRecord(Connection connection) {
		try {
			connection.setAutoCommit(false);
			String sql = "insert into products(STOCK_CODE, " +
					"COLOR, SIZE, QUANTITY, PRICE) values(?,?,?,?,?)";
			PreparedStatement pstmnt = 
				connection.prepareStatement(sql);
			pstmnt.setString(1, productCode);
			pstmnt.setString(2, productColor);
			pstmnt.setInt(3, size);
			pstmnt.setInt(4, quantity);
			pstmnt.setDouble(5, productPrice);
			
			//commit to the database
			pstmnt.executeUpdate();
			connection.commit();
		} catch (SQLException sqle) {
			try{
				sqle.printStackTrace();
				System.err.print("Transaction is being rolled back");
				connection.rollback();
			}catch(SQLException sqlexc){
				sqlexc.printStackTrace();
			}
		}
	}
	
	public void reduceStockCount(Connection connection, String key, String subtracted){
		try{
			connection.setAutoCommit(false);
			String sql = "UPDATE products "
					   + "SET QUANTITY = QUANTITY -" + subtracted + " "
					   + "WHERE ID = "+ key;

			PreparedStatement pstmnt = 
					connection.prepareStatement(sql);
			pstmnt.executeUpdate();
			connection.commit();
			
			System.out.println("removed "+subtracted+" units of "+key);
			
			ResultSet reductedStock = searchSpecific(connection, key);
			reductedStock.next();
			String changes = "REDUCED "+reductedStock.getString("STOCK_CODE")+"-"+reductedStock.getString("COLOR")+"-"+reductedStock.getString("SIZE");
			int quantity = Integer.parseInt(reductedStock.getString("QUANTITY"));
			recordLog(connection, changes, quantity, (quantity - Integer.parseInt(subtracted)));
		} catch (SQLException sqle){
			try{
				sqle.printStackTrace();
				System.err.print("Transaction is being rolled back");
				connection.rollback();
			}catch(SQLException sqlexc){
				sqlexc.printStackTrace();
			}
		}
	}
	
	public void increaseStockCount(Connection connection, String key, String added){
		try{
			connection.setAutoCommit(false);
			String sql = "UPDATE products "
					   + "SET QUANTITY = QUANTITY +" + added + " "
					   + "WHERE ID = "+ key;
			PreparedStatement pstmnt = 
					connection.prepareStatement(sql);
			pstmnt.executeUpdate();
			connection.commit();
			System.out.println("added "+added+" units of "+key);
			
			ResultSet reductedStock = searchSpecific(connection, key);
			reductedStock.next();
			String changes = "ADDED "+reductedStock.getString("STOCK_CODE")+"-"+reductedStock.getString("COLOR")+"-"+reductedStock.getString("SIZE");
			int quantity = Integer.parseInt(reductedStock.getString("QUANTITY"));
			recordLog(connection, changes, quantity, (quantity + Integer.parseInt(added)));
		} catch (SQLException sqle){
			try{
				sqle.printStackTrace();
				System.err.print("Transaction is being rolled back");
				connection.rollback();
			}catch(SQLException sqlexc){
				sqlexc.printStackTrace();
			}
		}
	}
	
	public ResultSet getAllRecords(Connection connection) {
		ResultSet records = null;
		try {
			String sql = "select * from products";
			
			PreparedStatement pstmnt = 
					connection.prepareStatement(sql);
			
			records = pstmnt.executeQuery();
			
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
		return records;
	}
	
	public void generateSalesReport(Connection connection) {
		ResultSet records = null;
		double totalValue = 0;
		try{
			String sql = "select * from sales";
			PreparedStatement pstmnt = 
					connection.prepareStatement(sql);
			
			records = pstmnt.executeQuery();
			
			String timeStamp = new SimpleDateFormat("yyyy-MM-dd").format(new java.util.Date());
			String filename = timeStamp+".txt";
			PrintWriter writer = new PrintWriter(filename, "UTF-8");
			while(records.next()){
				writer.println("On "+records.getString("ORDER_DATE")+": "+records.getString("CUSTOMER_NAME")+
					        " purchased "+records.getString("QUANTITY_PURCHASED")+" of PRODUCT-ID: "+records.getString("PRODUCT_ID")+
					        " for "+records.getString("PURCHASE_VALUE") + "Php");
				totalValue = totalValue + records.getDouble("PURCHASE_VALUE");
			}
			writer.println();
			writer.println("total value for "+timeStamp+" : "+totalValue);
			writer.close();
			System.out.println("Successfull creation of report @"+new File(filename+".txt").getAbsolutePath());
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		} catch (IOException e) {
			   e.printStackTrace();
		}
	}
	
	public void recordLog(Connection connection, String changes, int previousQuantity, int newQuantity) {
		String timeStamp = new SimpleDateFormat("yyyy-MM-dd-hh:mm:ss").format(new java.util.Date());
		try {
			connection.setAutoCommit(false);
			String sql = "INSERT into logs(timestamp, previous_quantity, quantity_now, changes)"
						+ " values(?,?,?,?)";
			
			PreparedStatement pstmnt = connection.prepareStatement(sql);
			
			pstmnt.setString(1, timeStamp);
			pstmnt.setInt(2, previousQuantity);
			pstmnt.setInt(3, newQuantity);
			pstmnt.setString(4, changes);
			
			pstmnt.executeUpdate();
			connection.commit();
		} catch (SQLException sqle){
			try{
				sqle.printStackTrace();
				System.err.print("Transaction is being rolled back");
				connection.rollback();
			}catch(SQLException sqlexc){
				sqlexc.printStackTrace();
			}
		}
	}
	
}
