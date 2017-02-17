package shoemixx.inventory.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import shoemixx.inventory.model.ProductBean;
import shoemixx.inventory.utility.DBConnectionUtil;

/**
 * Servlet implementation class ViewInventoryServlet
 */
@WebServlet("/ViewInventoryServlet")
public class ViewInventoryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private Connection connection = null;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		connection = DBConnectionUtil.getDBConnection();
		
		if (connection != null) {
			ResultSet stocks = new ProductBean().getAllRecords(connection);
			
			request.getSession().setAttribute("currentStocks", stocks);
			
			request.getRequestDispatcher("ViewInventory.jsp").forward(request, response);
		} else {
			System.err.println("Connection is Null.");
		}
	}

}
