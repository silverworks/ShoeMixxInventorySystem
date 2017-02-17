<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" type="text/css" href="css/bootstrap.min.css">
<link rel="stylesheet" href="css/bootstrap-theme.min.css">
<title>Main Menu</title>
</head>
<style>
</style>
  <body>
  <%
  	if(request.getSession().getAttribute("currentAccount") == null)
  		response.sendRedirect("index.jsp");
  %>
  <br><br>
    <div class="row text-center">
      <div class="col-lg-6 col-sm-6 col-md-offset-3">
        <div class="panel panel-warning">
          <div class="panel-heading">
            <h1 class="panel-title" style="color: black; font-size: 200%">Welcome, ${currentAccount.username}</h1>
          </div>
          <form action="menu.html">
	          <div class="panel-body">
	            <div class="row">
	              <div class="col-md-6">
	                <div class="text-center">
	                  <button type="submit" class="btn btn-block btn-lg btn-warning center-block" style="font-size:200%" name="menuChosen" value="addDel">Add/Delete Stock<br><br></button>
	                </div>
	              </div>
	              <div class="col-md-6">
	                <div class="text-center">
	                  <button type="submit" class="btn btn-block btn-lg btn-warning center-block" style="height: 27%; font-size:200%" name="menuChosen" value="newProduct">New Product<br><br></button>
	                </div>
	              </div>
	            </div>
	            
	            <div class="row">
	           		<br>
	              <div class="col-md-6">
	                <div class="text-center">
	                  <button type="submit" class="btn btn-block btn-lg btn-warning center-block" style="height: 27%; font-size:200%" name="menuChosen" value="searchStock">Search Stock<br><br></button>
	                </div>
	              </div>
	              <div class="col-md-6">
	                <div class="text-center">
	                  <button type="submit" class="btn btn-block btn-lg btn-warning center-block" style="height: 27%; font-size:200%" name="menuChosen" value="checkInventory">Check Inventory<br><br></button>
	                </div>
	              </div>
	            </div>
	            <div class="row">
	           		<br>
	              <div class="col-md-6">
	                <div class="text-center">
	                  <button type="submit" class="btn btn-block btn-lg btn-warning center-block" style="height: 27%; font-size:200%" name="menuChosen" value="purchaseProduct">Purchase Product<br><br></button>
	                </div>
	              </div>
	              <div class="col-md-6">
	                <div class="text-center">
	                  <button type="submit" class="btn btn-block btn-lg btn-danger center-block" style="height: 27%; font-size:200%" name="menuChosen" value="logout">Logout<br><br></button>
	                </div>
	              </div>
	            </div>
	            <div class="row">
	            	<br>
	            	<div class="col-md-6">
	                <div class="text-center">
	                  <button type="submit" class="btn btn-block btn-lg btn-warning center-block" style="height: 27%; font-size:200%" name="menuChosen" value="newAccount">New Account<br><br></button>
	                </div>
	              </div>
	              <div class="col-md-6">
	                <div class="text-center">
	                  <button class="btn btn-block btn-lg btn-warning center-block" style="height: 27%; font-size:200%" name="menuChosen" value="manageAccounts">Manage Accounts<br><br></button>
	                </div>
	              </div>
	            </div>
	          </div>
          </form>
        </div>
      </div>
    </div>
   </body>
</html>