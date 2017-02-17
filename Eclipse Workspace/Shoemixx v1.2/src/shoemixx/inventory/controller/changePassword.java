package shoemixx.inventory.controller;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import shoemixx.inventory.model.*;
import shoemixx.inventory.utility.DBConnectionUtil;

/**
 * Servlet implementation class changePassword
 */
@WebServlet("/changePassword.html")
public class changePassword extends HttpServlet {
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
		String password = request.getParameter("password");
		UserBean user = (UserBean) request.getSession(false).getAttribute("account");
		
//		String confPassword = request.getParameter("confirmPass")
		if(connection != null) {
			try {
				UserBean.changePassword(password, connection, user.getUsername());
			} catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//possible for future mag return ng true or false 
			//para may prompt na successful yuing pag change ng password
			request.getRequestDispatcher("changePassword.jsp").forward(request, response);
		} else {
			System.out.println("Error in Connecting to database");
		}
	}

}
