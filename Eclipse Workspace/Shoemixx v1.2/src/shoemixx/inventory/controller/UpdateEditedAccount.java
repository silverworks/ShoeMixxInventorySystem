package shoemixx.inventory.controller;

import java.awt.print.Printable;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import shoemixx.inventory.model.UserBean;
import shoemixx.inventory.utility.DBConnectionUtil;

/**
 * Servlet implementation class editAccountServlet
 */
@WebServlet("/saveEditedAccount.html")
public class UpdateEditedAccount extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Connection connection = null;
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		connection = DBConnectionUtil.getDBConnection();
		if(connection != null){
			String firstNameNewVal = request.getParameter("firstNameNewVal");
			String lastNameNewVal = request.getParameter("lastNameNewVal");
			String privilegeNewVal = request.getParameter("privilegeNewVal");
			UserBean user = (UserBean) request.getSession(false).getAttribute("account");
			
			UserBean.changeAccountDetails(firstNameNewVal, lastNameNewVal, privilegeNewVal, user.getUsername(), connection);
			

			request.getRequestDispatcher("accountManaging.html").forward(request, response);
		} else {
			System.out.println("database not connected");
		}
	}

}
