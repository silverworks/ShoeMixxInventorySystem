<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="shoemixx.inventory.utility.DBConnectionUtil" %>
<%@ page import="shoemixx.inventory.model.ProductBean" %>
<%@ page import="java.sql.*" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" type="text/css" href="css/bootstrap.min.css">
<link rel="stylesheet" href="css/bootstrap-theme.min.css">
<title>Purchase Item</title>
</head>
<body>
<br><br>
    <div class="section">
      <div class="container">
        <div class="row">
          <div class="col-lg-6 col-sm-6 col-md-offset-3">
            <div class="panel panel-warning">
              <div class="panel-heading">
                <h1 class="panel-title" style="color: black; font-size: 200%">Purchase Item</h1>
              </div>
              <div class="panel-body">
                <form class="form-horizontal" action="purchaseItem.html">
                  <div class="form-group">
                    <div class="col-sm-3">
                      <label for="inputEmail3" class="control-label" style="text-align: left;">Stock Number</label>
                    </div>
                    <div class="col-lg-8">
                      	<select class="form-control" name="key">
				        	<%  
				        		Connection connection = DBConnectionUtil.getDBConnection();
				        		ProductBean results = new ProductBean();
					        	ResultSet resultSet = results.getAllRecords(connection);
					    		request.getSession(false).setAttribute("resultSet", resultSet);
				        		while(resultSet.next()){ 
				        	
				        	%>
				         	   <option value="<%= resultSet.getString(1)%>">
				         	   						<%= resultSet.getString(2)%>_<%= resultSet.getString(3)%>_<%= resultSet.getString(4)%></option>
				        	<% } %>
	        			</select>
	        			<% resultSet.beforeFirst(); %>
                    </div>
                  </div>
                  <div class="form-group">
                    <div class="col-sm-3">
                      <label for="Quantity" class="control-label">Quantity</label>
                    </div>
                    <div class="col-lg-8">
                      <input type="number" class="form-control" id="Quantity" name="purchased" placeholder="Quantity">
                    </div>
                  </div>
                  <div class="form-group">
                    <div class="col-sm-3">
                      <label for="CustName" class="control-label" style="text-align: left">Customer Name</label>
                    </div>
                    <div class="col-lg-8">
                      <input type="text" class="form-control" id="CustName" name="customerName" placeholder="Customer Name">
                    </div>
                  </div>
                  <div class="form-group">
                    <div class="col-sm-offset-2 col-sm-9">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                      <button type="submit" value="purchase" name="purchase" class="btn btn-primary">Purchase</button>
                      <button type="submit" formaction="MainMenu.jsp" class="btn btn-warning pull-right">Go back</button>
                    </div>
                  </div>
                </form>
              </div>
            </div>
            <div class="col-lg-12">
	         	<form>
		        	<button type="submit" formaction="produceSalesReport.html" class="btn btn-warning pull-right">Generate Sales Report</button>
		        </form>
	          </div>
        </div>
          </div>
          
      </div>
    </div>
</body>
</html>