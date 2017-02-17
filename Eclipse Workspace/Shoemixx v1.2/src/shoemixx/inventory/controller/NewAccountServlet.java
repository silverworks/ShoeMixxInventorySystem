package shoemixx.inventory.controller;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Connection;
import shoemixx.inventory.utility.DBConnectionUtil;
import shoemixx.inventory.model.UserBean;

/**
 * Servlet implementation class NewAccountServlet
 */
@WebServlet("/newAccount.html")
public class NewAccountServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	private Connection connection = null;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	//please don't forget to validate inputs for the servlet as well
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		connection = DBConnectionUtil.getDBConnection();
		
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String userPrivilege = request.getParameter("userPrivilege");
		
		UserBean newUser = new UserBean();
		try {
			
			if(newUser.isValidUserAccount(firstName, lastName, username, password, userPrivilege, connection)) {
				newUser.addUserAccount(connection, username, password, firstName, lastName, userPrivilege);
				request.setAttribute("succmessage", "Successful Creation of Account.");
				request.getRequestDispatcher("NewAccount.jsp").forward(request, response);
			} else {
				request.setAttribute("errmessage", "Error in Creation of Account.");
				request.getRequestDispatcher("NewAccount.jsp").forward(request, response);
			}
			
		} catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
