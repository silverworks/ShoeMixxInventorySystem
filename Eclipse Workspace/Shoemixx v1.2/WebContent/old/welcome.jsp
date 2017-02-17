<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.sql.*" %>
<!DOCTYPE html>
<jsp:useBean id="branch" type="java.lang.String" 
	scope="session"/>     
<jsp:useBean id="resultSet" type="java.sql.ResultSet" 
	scope="session"/> 

<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Welcome - shoe-mixx</title>
	<link rel="stylesheet" href="bootstrap/css/bootstrap.min.css">
	<link rel="stylesheet" href="bootstrap/css/bootstrap-theme.min.css">
</head>
<body>
	
	<div class="container">
		<div class="container-fluid jumbotron form col-xs-12 col-xl-8 ">
				
			<h1 style="text-align:center;">Shoe-mixx ${branch}</h1>
		</div>
		
		<div class="col-xs-6">
			<h1 style="text-align:left;">Search</h1>
			<form action="search.html" method="post">
				<p>Search Products: <input type="text" id="key"
									name="key"> </p>
				<input type="submit" value="Search">
				<br><br><br>
			</form>
		</div>
		
		<div class="col-xs-6">
			<h1 style="text-align:left;">Insert New Product</h1>
			<form action="newproduct.html" method="post">
				
				<p>Enter Product Name: <input type="text" id="name"
									name="name"> </p>
				<p>Enter Quantity: <input type="number" id="quantity"
									name="quantity"> </p>
				<p>Enter Price per Unit(?): <input type="text" id="price"
									name="price"> </p>
				
				<input type="submit" value="Confirm">
				<br><br><br>
			</form>
		</div>
		<div class="col-xs-6">
			<h1>Add or Delete Stock</h1>
				<form action="deleteAddStock.html">
					<select name="key">
				        <%  while(resultSet.next()){ %>
				            <option value="<%= resultSet.getString(1) %>"><%= resultSet.getString("product_name")%></option>
				        <% } %>
	        		</select>
	        			<% resultSet.beforeFirst(); %>
	        		<input type="number" name="addOrSubtract"> &nbsp;
	        		<input type="submit" value="Delete" name="button"> &nbsp;
	        		<input type="submit" value="Add" name="button">
				</form>
			
		</div>
		<p><a href="branchselection.jsp">Back to Branch Selection</a></p>
		<p><a href="ViewInventoryServlet">View all stocks</a></p>
	</div>
	
</body>
</html>