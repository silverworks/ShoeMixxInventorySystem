package shoemixx.inventory.controller;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Connection;
import shoemixx.inventory.utility.DBConnectionUtil;
import shoemixx.inventory.model.UserBean;

public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private Connection connection = null;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		connection = DBConnectionUtil.getDBConnection();
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		UserBean user = new UserBean();
		
		if(user.isUsernameValid(username, connection)) { //valid username
			try {
				if(user.isPasswordValid(password, connection)){ //valid password
					request.getSession().setAttribute("currentAccount", user);
					response.sendRedirect("MainMenu.jsp");
				} else {
					request.setAttribute("errorMessage", "   Invalid username or password");
					//response.getWriter().println(pw);
					request.getRequestDispatcher("index.jsp").forward(request, response);
//				response.sendRedirect("index.jsp");
				}
			} catch (NoSuchAlgorithmException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvalidKeySpecException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else{
			//response.getWriter().println(pw);
			request.setAttribute("errorMessage", "Invalid username or password");
			request.getRequestDispatcher("index.jsp").forward(request, response);
//			response.sendRedirect("index.jsp");
		}
		
	}

}
