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

	.tumbotron {
 		padding-top: 30px;
  		padding-bottom: 30px;
  		margin-bottom: 30px;
  		color: inherit;
  		background-color: #eee;
	}

	.tumbotron h1,
	.tumbotron .h1 {
  		color: inherit;
	}
	
	.tumbotron p {
  		margin-bottom: 15px;
  		font-size: 21px;
  		font-weight: 200;
	}

	.tumbotron > hr {
  		border-top-color: #d5d5d5;
	}

	.container .tumbotron,
	.container-fluid .tumbotron {
  		padding-right: 15px;
  		padding-left: 15px;
  		border-radius: 6px;
	}

	.tumbotron .container {
  		max-width: 100%;
	}

	@media screen and (min-width: 768px) {
  	.tumbotron {
    	padding-top: 48px;
    	padding-bottom: 48px;
  	}
  
  	.container .tumbotron,
  	.container-fluid .tumbotron {
    	padding-right: 60px;
    	padding-left: 60px;
  	}
  
  	.tumbotron h1,
  	.tumbotron .h1 {
    	font-size: 63px;
  	}
	}
	
	.color-me-black{
		color:black;
	}

	.color-me-white{
		color:white;
	}
	
	body {
  		background-image: url("../images/background.png");
	}
	
	.center_div{
    	margin: 0 auto;
    	width:13% /* value of your choice which suits your alignment */
	}
	
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
				<li><a href="../index.jsp" class="color-me-black">Home</a>
				<li><a href="../browse" class="color-me-black">Browse Projects</a>
			</ul>
			<ul class="nav navbar-nav navbar-right">
			</ul>
		</div>
	</nav>
	
		<!-- login form sends data to SignupServlet -> sends data to HomePageServlet that -->
	<div class="jumbotron text-center">
  		<h1 style="color:white">WorkineThor</h1>
		<div class="form-horizontal center_div">
			<form action="../signup" method="post">
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