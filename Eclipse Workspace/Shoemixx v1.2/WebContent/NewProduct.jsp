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
<title>Add New Product</title>
</head>
<body>
<br><br>
    <div class="section">
      <div class="container">
        <div class="row">
          <div class="col-lg-6 col-sm-6 col-md-offset-3">
            <div class="panel panel-warning">
              <div class="panel-heading">
                <h1 class="panel-title" style="color: black; font-size: 200%">Add New Product</h1>
              </div>
              <div class="panel-body">
                <form action="newproduct.html" class="form-horizontal" method="post">
                  <div class="form-group">
                    <div class="col-sm-3">
                      <label for="inputEmail3" class="control-label">Stock Number</label>
                    </div>
                    <div class="col-lg-8">
                      <input type="text" class="form-control" id="inputEmail3" name="productCode" placeholder="Stock Code">
                    </div>
                  </div>
                  <div class="form-group">
                    <div class="col-sm-3">
                      <label for="inputEmail3" class="control-label">Color</label>
                    </div>
                    <div class="col-lg-8">
                      <input type="text" class="form-control" id="inputEmail3" name="productColor" placeholder="Color">
                    </div>
                  </div>
                  <div class="form-group">
                    <div class="col-sm-3">
                      <label for="inputEmail3" class="control-label">Quantity</label>
                    </div>
                    <div class="col-lg-8">
                      <input type="number" class="form-control" id="inputEmail3" name="productQuantity" placeholder="Quantity">
                    </div>
                  </div>
                  <div class="form-group">
                    <div class="col-sm-3">
                      <label for="inputEmail3" class="control-label">Size</label>
                    </div>
                    <div class="col-lg-8">
                      <input type="number" class="form-control" id="inputEmail3" name="productSize" placeholder="Size">
                    </div>
                  </div>
                  <div class="form-group">
                    <div class="col-sm-3">
                      <label for="inputEmail3" class="control-label">Price</label>
                    </div>
                    <div class="col-lg-8">
                      <input type="number" class="form-control" id="inputEmail3" name="productPrice" placeholder="Price">
                    </div>
                  </div>
                  <div class="form-group">
                    <div class="col-sm-offset-2 col-sm-9">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                      <button type="submit" class="btn btn-primary">Add</button>
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