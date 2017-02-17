package shoemixx.inventory.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import shoemixx.inventory.utility.DBConnectionUtil;

import shoemixx.inventory.model.UserBean;

/**
 *
 * this servlet will get all accounts on the database 
 * for the manageAccounts.jsp will be showing for viewing
 */
@WebServlet("/accountManaging.html")
public class manageAccountsServlet extends HttpServlet {
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
		
		if (connection != null) {
			ResultSet users = new UserBean().getAllRecords(connection);
			
			request.getSession().setAttribute("userList", users);
			
			request.getRequestDispatcher("manageAccounts.jsp").forward(request, response);
		} else {
			System.err.println("Connection is Null.");
		}
	}

}
