package shoemixx.inventory.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class MainMenuServlet
 */
@WebServlet("/menu.html")
public class MainMenuServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String menuChosen = request.getParameter("menuChosen");
		
		if(menuChosen.equals("addDel"))
			request.getRequestDispatcher("AddDelete.jsp").forward(request, response);
		if(menuChosen.equals("newProduct"))
			request.getRequestDispatcher("NewProduct.jsp").forward(request, response);
		if(menuChosen.equals("searchStock"))
			request.getRequestDispatcher("SearchStock.jsp").forward(request, response);
		if(menuChosen.equals("checkInventory"))
			request.getRequestDispatcher("ViewInventoryServlet").forward(request, response);
		if(menuChosen.equals("purchaseProduct"))
			request.getRequestDispatcher("PurchaseItem.jsp").forward(request, response);
		if(menuChosen.equals("newAccount"))
			request.getRequestDispatcher("NewAccount.jsp").forward(request, response);
		if(menuChosen.equals("manageAccounts"))
			request.getRequestDispatcher("accountManaging.html").forward(request, response);
		if(menuChosen.equals("logout")){
			request.getSession().invalidate();
			request.getRequestDispatcher("index.jsp").forward(request, response);
		}
	}

}
