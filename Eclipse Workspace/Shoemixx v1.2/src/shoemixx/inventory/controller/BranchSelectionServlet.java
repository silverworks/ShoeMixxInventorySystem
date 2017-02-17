package shoemixx.inventory.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;

import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import shoemixx.inventory.model.ProductBean;
import shoemixx.inventory.utility.DBConnectionUtil;

/**
 * Servlet implementation class BranchSelectionServlet
 */
//@WebServlet("/branch.html")
public class BranchSelectionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	private Connection connection = null;
	
	/*public void init() throws ServletException {
		
		//connection = DBConnectionUtil.getDBConnection();
	}*/
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String branch = request.getParameter("branch");
		request.getSession().setAttribute("branch", branch);
		
		//acquire result set for the listings of each product for the deletion (for a particular branch)
		
		connection = DBConnectionUtil.getDBConnection(branch);
		ProductBean results = new ProductBean();
		
		ResultSet resultSet = results.getAllRecords(connection);
		request.getSession(false).setAttribute("resultSet", resultSet);
		
		
		response.sendRedirect("login.jsp");
	}

}
