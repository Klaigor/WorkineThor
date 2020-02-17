<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<title>
WorkineThor
</title>
<style type="text/css">
	<%@include file="bootstrap/css/bootstrap.css" %>
	<%@include file="bootstrap/css/bootstrap-theme.css" %>
	<%@include file="bootstrap/css/workinethor-theme.css" %>
</style>
<script src="bootstrap/js/jquery.js"></script>
<script src="bootstrap/js/bootstrap.js"></script>
<body>
	
	<nav class="navbar navbar-default navbar-fixed-top">
		<div class="container-fluid">
			<div class="navbar-header">
				<a class="navbar-brand color-me-black" href="">WorkineThor</a>
			</div>
			<ul class="nav navbar-nav">
				<li><a href="#home" class="color-me-black">Home</a>
				<li><a href="#browse" class="color-me-black">Browse Projects</a>
			</ul>
			<ul class="nav navbar-nav navbar-right">
				<li><a href="#" class="color-me-black"><img src="images/signup.png" class="media-object" style="width:20px">Sign Up</a>
				<li><a href="index.jsp" class="color-me-black"><img src="images/login.png" class="media-object" style="width:20px">Login</a>
			</ul>
		</div>
	</nav>
	
	<div class="jumbotron text-center">
  		<h1 style="color:white">WorkineThor</h1>
		<div class="form-horizontal" align="center">
			<form action="login" method="post">
				<label for="username" style="color:white">Username: </label>
				<input type="text" class="form-control" style="width:170px" id="user" name="username"><br>
				<label for="password" style="color:white">Password: </label>
				<input type="password" class="form-control" style="width:170px" id="pass" name="password"><br>
				<input type="submit" class="btn btn-default" value="Login">
			</form>
		</div>
	</div>

</body>
</html>