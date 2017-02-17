<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<jsp:useBean id="userList" type="java.sql.ResultSet" 
	scope="session"/> 
<!DOCTYPE html>

<html>
<head>
<title>Manage Accounts</title>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>View Inventory</title>
	<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" type="text/css" href="css/bootstrap.min.css">
<link rel="stylesheet" href="css/bootstrap-theme.min.css">
<style>
	<!-- this is just to fix the gap of the last column -->
	.tdicon{ 
		width: 32px !important; 
	}
	
	.table-center {
	    border-radius: 5px;
	    width: 50%;
	    margin: 0px auto;
	    float: none;
	}	
</style>
</head>
<body>
	<br><br>
    <div class="section">
      <div class="container">
        <div class="row">
          <div class="col-lg-10 table-center ">
            <div class="panel panel-warning">
              <div class="panel-heading">
                <h1 class="panel-title" style="color: black; font-size: 200%">Manage Accounts</h1>
              </div>
              <form action="post">
              <div class="panel-body">
                <table class="table">
                  <thead>
                    <tr>
                      <th class="col-md-4">First Name</th>
                      <th class="col-md-4">Last Name</th>
                      <th class="col-md-4">User Name</th>
                      <th class="col-md-4">Privilege</th>
                      <th class="tdicon">&nbsp;</th>
                    </tr>
                  </thead>
                  <tbody>
                    <% while (userList.next()) { %>
					<tr>
						<td><%=userList.getString("firstname")%></td>
						<td><%=userList.getString("lastname")%></td>
						<td><%=userList.getString("username")%></td>
						<td><%=userList.getString("privilege")%></td>
						<td><button type="submit" value="<%=userList.getString("username") %>" formaction="edit.html" name="editChosen">View/Edit</button></td> <!--  username siguro since unique -->
					</tr>	
					<% } 
                    	userList.first();
					%>
                  </tbody>
                </table>
              </div>
            </div>
            <div class="col-lg-12">
	        	<button type="submit" formaction="MainMenu.jsp" class="btn btn-warning pull-right">Go back</button>
	        </form>
          </div>
          </div>
        </div>
      </div>
    </div>
</body>
</html>