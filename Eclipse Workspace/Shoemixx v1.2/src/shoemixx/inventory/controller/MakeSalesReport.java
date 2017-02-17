package shoemixx.inventory.controller;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import shoemixx.inventory.model.ProductBean;
import shoemixx.inventory.utility.DBConnectionUtil;

/**
 * Servlet implementation class MakeSalesReport
 */
@WebServlet("/produceSalesReport.html")
public class MakeSalesReport extends HttpServlet {
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
		ProductBean results = new ProductBean();
		
		results.generateSalesReport(connection);

		response.sendRedirect("MainMenu.jsp");
	}

}
