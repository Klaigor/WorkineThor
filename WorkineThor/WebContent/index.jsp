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
				<li><a href="browse" class="color-me-black">Browse Projects</a>
			</ul>
			<ul class="nav navbar-nav navbar-right">
				<li><a href="jsp/signup.jsp" class="color-me-black"><img src="images/signup.png" class="media-object" style="width:20px">Sign Up</a>
			</ul>
		</div>
	</nav>
	
	<!-- login form sends data to LoginServlet -> sends data to HomePageServlet that -->
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

<script>
	
	/* method that catches url params */ 
	function getParams(parameter){
		var queryString = window.location.search;
		var urlParams = new URLSearchParams(queryString);
	
		var value = urlParams.get(parameter);
	
		return value;
	}
	
	window.onload = function() {
		var failure = getParams("failure");
		if(failure){
			alert("Username and Password are already chosen!!");
		}
	}
	
</script>

</body>
</html>