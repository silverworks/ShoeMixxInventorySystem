package shoemixx.inventory.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import shoemixx.inventory.model.UserBean;
import shoemixx.inventory.utility.DBConnectionUtil;

/**
 * Servlet implementation class editAccount
 */
@WebServlet("/edit.html")
public class EditAccount extends HttpServlet {
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
		String accountToEdit = request.getParameter("editChosen");
		System.out.println(accountToEdit);
		if (connection != null) {
			ResultSet acquiredAccount = new UserBean().obtainAccount(connection, accountToEdit);
			UserBean account = new UserBean();
			try {
				acquiredAccount.next();
				account.setFirstName(acquiredAccount.getString("firstname"));
				account.setLastName(acquiredAccount.getString("lastname"));
				account.setUsername(acquiredAccount.getString("username"));
				account.setPrivilege(acquiredAccount.getString("privilege"));
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			request.getSession(false).setAttribute("account", account);
			request.getRequestDispatcher("EditAccounts.jsp").forward(request, response);
		} else {
			System.err.println("Connection is Null.");
		}
	}

}
