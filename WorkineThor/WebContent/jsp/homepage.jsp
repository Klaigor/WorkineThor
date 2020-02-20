<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>  
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>

<meta charset="ISO-8859-1">
<style type="text/css">
    <%@include file="../bootstrap/css/bootstrap.css" %>
    <%@include file="../bootstrap/css/bootstrap-theme.css" %>
    
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
  		background-image: url("images/background.png");
	}
	
	.center_div{
    	margin: 0 auto;
    	width:13% /* value of your choice which suits your alignment */
	}
    
</style>
<script src="bootstrap/js/jquery.js"></script>
<script src="bootstrap/js/bootstrap.js"></script>
<title>
HomePage
</title>

</head>
<body>

	<!-- navigation -->
	<nav class="navbar navbar-default">
		<div class="container-fluid">
			<div class="navbar-header">
				<a class="navbar-brand color-me-black" href="">WorkineThor</a>
			</div>
			<ul class="nav navbar-nav">
				<li><a href="#home" class="color-me-black">Home</a>
				<li><a href="jsp/create-project.jsp" class="color-me-black">Create Project</a>
				<li><a href="browse" class="color-me-black">Browse Projects</a>
			</ul>
			<ul class="nav navbar-nav navbar-right">
				<li><a href="logout" class="color-me-black"><img src="images/logout.png" class="media-object" style="width:20px">Logout</a>
			</ul>
		</div>
	</nav>
	
	<br>
	<br>
	<br>
	
	<!-- users data (projects) by clicking on a project you ask the ProjectServlet to display project.jsp-->
	<div class="tumbotron text-center">
		<h2 style="color:black">User: ${user}</h2>
		<ul class="list-group justify-content-center" >
   		<c:forEach items="${project_list}" varStatus="i">
   			<li class="list-group-item" style="width:170px">
   				<a href="project?id=${project_list[i.index]}">
   					<img src="images/project-icon.png" class="media-object" style="width:20px">    				
 					${project_list[i.index]}
   				</a>
   				<br>
    		</li>
    	</c:forEach>
		</ul>
	</div>	

</body>
</html>