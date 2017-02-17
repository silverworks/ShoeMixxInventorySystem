package shoemixx.inventory.controller;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;

import shoemixx.inventory.utility.ProductBeanFactory;
import shoemixx.inventory.model.ProductBean;
import shoemixx.inventory.utility.DBConnectionUtil;

//@WebServlet("/newproduct.html")
public class NewProductServlet extends HttpServlet {
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
		try{
			
			String productCode = request.getParameter("productCode");
			String productColor = request.getParameter("productColor");
			int productQuantity = Integer.parseInt(request.getParameter("productQuantity"));
			int productSize = Integer.parseInt(request.getParameter("productSize"));
			double productPrice = Double.parseDouble(request.getParameter("productPrice"));
			
			
			ProductBean prod;
			
			prod = ProductBeanFactory.getInstance(productCode, productColor, productQuantity, productSize, productPrice);
			prod.insertRecord(connection);
//			HttpSession session = request.getSession();
//			session.setAttribute("prod", prod);
		
			response.sendRedirect("NewProduct.jsp");
		
			
		}catch(Exception eh){
			eh.printStackTrace();
		}
	}

}
