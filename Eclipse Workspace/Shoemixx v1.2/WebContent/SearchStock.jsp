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
<title>Search Stock</title>
</head>

<style>
      .center {
         float: none;
         margin-left: auto;
         margin-right: auto;
    }
    </style>
 </head>
  
  <body>
    
	<br><br>
	<div class="section">
      <div class="container">
        <div class="row">
          <div class="col-lg-6 col-sm-6 col-md-offset-3">
            <div class="panel panel-warning">
              <div class="panel-heading">
                <h1 class="panel-title" style="color: black; font-size: 200%">Search Product</h1>
              </div>
              <div class="panel-body">
                <form class="form-horizontal" role="form" action="search.html" method="post">
                  <div class="form-group">
                    <div class="col-sm-3">
                      <label for="inputEmail3" class="control-label" style="text-align: left">Stock Code</label>
                    </div>
                    <div class="col-lg-8">
                      <input class="form-control" name="key" id="inputEmail3" placeholder="Stock Code" type="text">
                    </div>
                  </div>
                  <div class="form-group">
                    <div class="col-sm-offset-2 col-sm-9">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                      <button type="submit" class="btn btn-primary">Search</button>
                      <button type="submit" formaction="MainMenu.jsp" class="btn btn-warning pull-right">Go back</button>
                    </div>
                  </div>
                </form>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>


  </body>
</html>