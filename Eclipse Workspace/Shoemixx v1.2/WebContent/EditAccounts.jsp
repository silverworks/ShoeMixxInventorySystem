<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" type="text/css" href="css/bootstrap.min.css">
<link rel="stylesheet" href="css/bootstrap-theme.min.css">
<title>Edit Accounts</title>
</head>
<body>
	<br><br>
    <div class="section">
      <div class="container">
        <div class="row">
          <div class="col-lg-6 col-sm-6 col-md-offset-3">
            <div class="panel panel-warning">
              <div class="panel-heading">
                <h1 class="panel-title" style="color: black; font-size: 200%">Edit ${account.firstName } ${account.lastName }'s Account</h1>
              </div>
              <div class="panel-body">
                <form action="saveEditedAccount.html" class="form-horizontal" method="post">
                  <div class="form-group">
                    <div class="col-sm-3">
                      <label for="inputEmail3" class="control-label">First Name</label>
                    </div>
                    <div class="col-lg-8">
                      <input type="text" class="form-control" id="inputEmail3" name="firstNameNewVal"  value="${account.firstName}">
                    </div>
                  </div>
                  <div class="form-group">
                    <div class="col-sm-3">
                      <label for="inputEmail3" class="control-label">Last Name</label>
                    </div>
                    <div class="col-lg-8">
                      <input type="text" class="form-control" id="inputEmail3" name="lastNameNewVal"  value="${account.lastName }">
                    </div>
                  </div>
                  <div class="form-group">
                    <div class="col-sm-3">
                      <label for="inputEmail3" class="control-label" >Privilege</label>
                    </div>
                    <div class="col-lg-8">
                      <select class="form-control" name="privilegeNewVal" id="privilegeNewVal"> <!--  this uses a jquery below to select a default value -->
                      	<option value="admin">Admin</option>
                      	<option value="staff">Staff</option>
                      	<option value="cashier">Cashier</option>	   						
                      </select>
                    </div>
                  </div>
                  
                  <div class="form-group">
                    <div class="col-sm-offset-2 col-sm-9">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                      <button type="submit" class="btn btn-primary">Save changes</button>
                        
                        <button type="submit" formaction="changePassword.jsp" class="btn btn-danger pull-right">Change Password</button>
                    </div>
                    
                  </div>
              </form>
              </div>
            </div>
            <form>
             	<button type="submit" formaction="manageAccounts.jsp" class="btn btn-warning pull-right">Go back</button>
            </form>
          </div>
        </div>
      </div>
    </div>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
    <script type="text/javascript">
    	
    	$(document).ready(function(){
	    	var chosen = "${account.privilege}";
	        $('#privilegeNewVal').val(chosen).refresh(); //value of your default option
	    });
    </script>
</body>
</html>