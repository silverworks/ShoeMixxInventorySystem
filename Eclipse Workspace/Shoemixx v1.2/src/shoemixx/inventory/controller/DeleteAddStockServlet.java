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
 * Servlet implementation class DeleteAddStockServlet
 */
@WebServlet("/deleteAddStock.html")
public class DeleteAddStockServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private Connection connection = null;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String key = request.getParameter("key");
//		String[] keys = key.split("_");
		
		String addOrSubtract = request.getParameter("addOrSubtract");
		String buttonClicked = request.getParameter("button");

		connection = DBConnectionUtil.getDBConnection();
		ProductBean update = new ProductBean();
		if(buttonClicked.equals("Add")){
			update.increaseStockCount(connection, key, addOrSubtract);
		} else {
			update.reduceStockCount(connection, key, addOrSubtract);
		}
		response.sendRedirect("AddDelete.jsp");
	}

}
