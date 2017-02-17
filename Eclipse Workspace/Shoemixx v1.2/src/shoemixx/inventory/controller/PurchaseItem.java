package shoemixx.inventory.controller;

import java.io.IOException;
import java.sql.Connection;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import shoemixx.inventory.model.ProductBean;
import shoemixx.inventory.utility.DBConnectionUtil;

/**
 * Servlet implementation class PurchaseItem
 */
@WebServlet("/purchaseItem.html")
public class PurchaseItem extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private Connection connection = null;
		
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String key = request.getParameter("key");
		String customerName = request.getParameter("customerName");
		String purchased = request.getParameter("purchased");
		
		connection = DBConnectionUtil.getDBConnection();
		ProductBean purchase = new ProductBean();
		String timeStamp = new SimpleDateFormat("yyyy/MM/dd-HH:mm:ss").format(new java.util.Date());
		
		purchase.setDatePurchased(timeStamp);
		purchase.setCustomerName(customerName);
		
		purchase.purchaseItem(connection, key, purchased, customerName);
		response.sendRedirect("PurchaseItem.jsp");
	}

}
