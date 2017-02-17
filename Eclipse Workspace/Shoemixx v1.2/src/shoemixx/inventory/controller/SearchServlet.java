package shoemixx.inventory.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import shoemixx.inventory.model.ProductBean;
import shoemixx.inventory.utility.DBConnectionUtil;


public class SearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private Connection connection = null;
	
	/*public void init() throws ServletException {
		connection = DBConnectionUtil.getDBConnection();
	}*/

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		connection = DBConnectionUtil.getDBConnection(); 
		if (connection != null) {
			String key = request.getParameter("key");
			
			ResultSet search = new ProductBean()
				.search(connection, key);
			
			request.getSession().setAttribute("searchresults", search);
			
			response.sendRedirect("search.jsp");
		} else {
			System.err.println("connection is NULL.");
		}	
	}

}
