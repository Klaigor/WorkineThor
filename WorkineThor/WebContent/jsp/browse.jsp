<%@page import="logic.model.Session"%>
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
</style>
<script src="bootstrap/js/jquery.js"></script>
<script src="bootstrap/js/bootstrap.js"></script>
<title>
Browse Project
</title>

</head>
<body background="images/background.png">

	<!-- navigation -->
	<nav class="navbar navbar-default">
		<div class="container-fluid">
			<div class="navbar-header">
				<a class="navbar-brand color-me-black" href="">WorkineThor</a>
			</div>
			<ul id="main-buttons" class="nav navbar-nav">
				<li><a href="#home" class="color-me-black" onclick="handleHome()">Home</a>
				<li><a href="jsp/create-project.jsp" class="color-me-black">Create Project</a>
			</ul>
			<ul id="right-buttons" class="nav navbar-nav navbar-right">
				<li><a href="#" class="color-me-black"><img src="images/signup.png" class="media-object" style="width:20px">Sign Up</a>
				<li><a href="logout" class="color-me-black"><img src="images/logout.png" class="media-object" style="width:20px">Logout</a>
			</ul>
		</div>
	</nav>
	
	<br>
	
	<!-- Browse project-->
	<div class="tumbotron text-center">
		<h2 style="color:white">Browse</h2>
		<div class="form-horizontal" align="center">
		<input type="text" class="form-control" style="width:170px" id="searchField" name="search" placeholder="Search!" ></input>
		</div>	
		<br>
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
	
<script>
	
	/* get logged user */
	<% String user = Session.getSession().getLoggedUser().getUsername(); %>
	var userLogged = "<%= user%>";
	
	/* hide items */
	window.onload = function() {
		if(userLogged === ""){
			document.getElementById("right-buttons").children[1].style.display = "none";
			document.getElementById("main-buttons").children[1].style.display = "none";
		}
		else{
			document.getElementById("right-buttons").children[0].style.display = "none";
		}
	};
	
	/* if logged or not logged --> redirects to the right page*/
	function handleHome() {
		if(userLogged === ""){
			document.location.href = "index.jsp";
		}
		else{
			document.location.href = "homepage";
		}
	}
</script>

</body>
</html>