<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
 <jsp:useBean id="currentStocks" type="java.sql.ResultSet" 
	scope="session"/>    
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>View Inventory</title>
	<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" type="text/css" href="css/bootstrap.min.css">
<link rel="stylesheet" href="css/bootstrap-theme.min.css">
<style>
      .center {
         float: none;
         margin-left: auto;
         margin-right: auto;
    }
    </style>
</head>
<body><br><br>
    <div class="section">
      <div class="container">
        <div class="row">
          <div class="col-lg-10 center">
            <div class="panel panel-warning">
              <div class="panel-heading">
                <h1 class="panel-title" style="color: black; font-size: 200%">Current Products</h1>
              </div>
              <div class="panel-body">
                <table class="table">
                  <thead>
                    <tr>
                      <th>Product Code</th>
                      <th>Color</th>
                      <th>Size</th>
                      <th>Quantity</th>
                    </tr>
                  </thead>
                  <tbody>
                    <% while (currentStocks.next()) { %>
					<tr>
						<td><%=currentStocks.getString(2) %></td>
						<td><%=currentStocks.getString(3) %></td>
						<td><%=currentStocks.getString(4) %></td>
						<td><%=currentStocks.getString(5) %></td>		
					</tr>	
					<% } 
                    	currentStocks.first();
					%>
                  </tbody>
                </table>
              </div>
            </div>
            <div class="col-lg-12">
         	<form>
	        	<button type="submit" formaction="MainMenu.jsp" class="btn btn-warning pull-right">Go back</button>
	        </form>
          </div>
          </div>
        </div>
        
      </div>
    </div>
</body>
</html>