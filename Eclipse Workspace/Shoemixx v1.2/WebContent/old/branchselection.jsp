<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>shoe-mixx</title>
	<link rel="stylesheet" href="bootstrap/css/bootstrap.min.css">
	<link rel="stylesheet" href="bootstrap/css/bootstrap-theme.min.css">
	<style>
	{
	  -moz-box-sizing: border-box;
	  box-sizing: border-box;
	}
	.login-header {
		  left: 0;
		  top: 0;
		  z-index: 1;
		  width: 20rem;
		  height: 20rem;
		  background: orange;
		  transition: width 0.5s ease-in-out;
		}
	body {
		  background: #333;
		  font: 100%/1 "Helvetica Neue", Arial, sans-serif;
		}
	.login {

		  width: 20rem;
		  height: 20rem;
		  padding: 20px;
		  background: #fff;
		  border-radius: 5px;
		  overflow: hidden;
		}
	.login-header {
		  left: 0;
		  top: 0;
		  z-index: 1;
		  width: 20rem;
		  height: 20rem;
		  background: orange;
		}
		
	</style>
</head>
	
	<div class="container ">
		<div class="container-fluid jumbotron form col-xs-12">
			<h1 style="text-align:center;">Shoe-mixx</h1>
			<h1 style="text-align:center;">Select Your Branch</h1>
		</div>
		
		<div class="col-xs-4">
			<form action="branch.html" method="post" class="login login-header">
				<input type="hidden" name="branch" value="Branch 1">
				<input type="submit" value="Branch 1" style="font-size: 250%">
			</form>
		</div>
		<div class="col-xs-4">
			<form action="branch.html" method="post" class="login login-header">
				<input type="hidden" name="branch" value="Branch 2">
				<input type="submit" value="Branch 2" style="font-size: 250%">
			</form>
		</div>
		<div class="col-xs-4">
			<form action="branch.html" method="post" class="login login-header">
				<input type="hidden" name="branch" value="Branch 3">
				<input type="submit" value="Branch 3" style="font-size: 250%">
			</form>
		</div>
				
		
	</div>
	
</body>
</html>