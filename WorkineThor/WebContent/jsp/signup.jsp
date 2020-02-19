<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>WorkineThor</title>
</head>

<style type="text/css">
	<%@include file="../bootstrap/css/bootstrap.css" %>
	<%@include file="../bootstrap/css/bootstrap-theme.css" %>
	<%@include file="../bootstrap/css/workinethor-theme.css" %>
</style>
<script src="bootstrap/js/jquery.js"></script>
<script src="bootstrap/js/bootstrap.js"></script>
<body>

	<!-- navigation -->
	<nav class="navbar navbar-default">
		<div class="container-fluid">
			<div class="navbar-header">
				<a class="navbar-brand color-me-black" href="">WorkineThor</a>
			</div>
			<ul class="nav navbar-nav">
				<li><a href="#home" class="color-me-black">Home</a>
				<li><a href="#browse" class="color-me-black">Browse Projects</a>
			</ul>
			<ul class="nav navbar-nav navbar-right">
			</ul>
		</div>
	</nav>
	
		<!-- login form sends data to SignupServlet -> sends data to HomePageServlet that -->
	<div class="jumbotron text-center">
  		<h1 style="color:white">WorkineThor</h1>
		<div class="form-horizontal" align="center">
			<form action="signup" method="post">
				<label for="username" style="color:white">New Username: </label>
				<input type="text" class="form-control" style="width:170px" id="user" name="username"><br>
				<label for="password" style="color:white">New Password: </label>
				<input type="password" class="form-control" style="width:170px" id="pass" name="password"><br>
				<input type="submit" class="btn btn-default" value="Login">
			</form>
		</div>
	</div>

</body>
</html>