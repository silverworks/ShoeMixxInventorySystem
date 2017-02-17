<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" type="text/css" href="css/bootstrap.min.css">
<link rel="stylesheet" href="css/bootstrap-theme.min.css">
<title>New Account</title>
</head>
<body>
<br><br>
    <div class="section">
      <div class="container">
        <div class="row">
          <div class="col-lg-6 col-sm-6 col-md-offset-3">
            <div class="panel panel-warning">
              <div class="panel-heading">
                <h1 class="panel-title" style="color: black; font-size: 200%">Create New Account</h1>
              </div>
              <div class="panel-body">
                <form action="newAccount.html" class="form-horizontal" method="post">
                <div class="form-group">
                    <div class="col-sm-3">
                      <label for="firstName" class="control-label">First Name</label>
                    </div>
                    <div class="col-lg-8">
                      <input type="text" class="form-control" id="firstName" name="firstName" placeholder="First Name">
                    </div>
                  </div>
                  <div class="form-group">
                    <div class="col-sm-3">
                      <label for="lastName" class="control-label">Last Name</label>
                    </div>
                    <div class="col-lg-8">
                      <input type="text" class="form-control" id="lastName" name="lastName" placeholder="Last Name">
                    </div>
                  </div>
                  <div class="form-group">
                    <div class="col-sm-3">
                      <label for="username" class="control-label">Username</label>
                    </div>
                    <div class="col-lg-8">
                      <input type="text" class="form-control" id="username" name="username" placeholder="Username">
                    </div>
                  </div>
                  <div class="form-group">
                    <div class="col-sm-3">
                      <label for="password" class="control-label">Password</label>
                    </div>
                    <div class="col-lg-8">
                      <input type="text" class="form-control" id="password" name="password" placeholder="Password"
                      onChange="isValidPassword();">
                      <div class="registrationFormAlert" id="divCheckValidPassword">
					</div>
                    </div>
                  </div>
                  <div class="form-group">
                    <div class="col-sm-3">
                      <label for="confirmpass" class="control-label text-nowrap">Confirm Password</label>
                    </div>
                    <div class="col-lg-8">
                      <input type="text" class="form-control" id="confirmPass" name="confirmPass" placeholder="Confirm Password"
                       onChange="checkPasswordMatch();">
                       <div class="registrationFormAlert" id="divCheckPasswordMatch"></div>
                    </div>
                    
                  </div> 
                  <div class="form-group">
                    <div class="col-sm-3">
                      <label for="userPrivilege" class="control-label text-nowrap">Select User Type</label>
                    </div>
                    <div class="col-lg-8">
                      <select class="form-control" id="userPrivilege" name="userPrivilege">
						    	<option value="admin">Admin</option>
						    	<option value="cashier">Cashier</option>
						    	<option value="staff">Staff</option>
						  </select>
                    </div>
                    
                  </div> 
                  
                  <div class="form-group">
                    <div class="col-sm-offset-2 col-sm-9">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                      <button type="submit" class="btn btn-primary">Create Account</button>
                        <button type="submit" formaction="MainMenu.jsp" class="btn btn-warning pull-right">Go back</button>
                    </div>
                  </div>
              </form>
              </div>
              
              
            </div>
            <p style="color: red">${errmessage}</p>
            <p style="color: green">${succmessage}</p>
          </div>
        </div>
      </div>
    </div>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
    <script type="text/javascript">
	    function checkPasswordMatch() {
	        var password = $("#password").val();
	        var confirmPassword = $("#confirmPass").val();
			var isValid = true;
			
	        if (password != confirmPassword){
	        	$("#divCheckPasswordMatch").html("Passwords do not match!").css('color', 'red');
	        	$("#confirmPass").css("background-color", "LightCoral");
	        	isValid = false;
	        } else {
	            $("#divCheckPasswordMatch").html("Passwords match.").css('color', 'green');
	            $("#confirmPass").css("background-color", "LightGreen");
	            isValid = false;
	        }
	        
	        return isValid;
	    }
	    
	    function isValidPassword() {
	    	var password = $("#password").val();
	      	var error = "";
	      	var isValid = true;
	      	if(password.length < 6){
	      		error = "Password too short."
	      		isValid = false;
	      	} else if(password.search(/\d/) == -1){
	      		error = "Must contain a digit."
	      		isValid = false;
	      	} else if(password.search(/[a-zA-Z]/) == -1) {
	      		error = "Must contain a letter."
	      		isValid = false;
	      	} else if(password.search(/[^a-zA-Z0-9\!\@\#\$\%\^\&\*\(\)\_\+\.\,\;\:\~\|\`\<\>]/) != -1) {
	      	  	error = "Bad character."
	      	  	isValid = false;
	        }

	      	$("#divCheckValidPassword").html(error).css('color', 'red');
	      	if(isValid == true){
	      		$("#password").css("background-color", "LightGreen");
	      	}
	      	else {
	      		$("#password").css("background-color", "LightCoral");
	      	}
	      		
	      	return isValid;
	    }
	
	    $(document).ready(function () {
	       	$("#confirmPass").keyup(checkPasswordMatch);
	    });
	    
	    $(document).ready(function () {
	    	$("#password").keyup(isValidPassword);
	    });
    </script>
</body>
</html>