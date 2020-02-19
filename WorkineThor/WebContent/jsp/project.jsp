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
${active_project}
</title>
</head>
<body background="images/background.png">

	<!-- navigation -->
	<nav class="navbar navbar-default">
		<div class="container-fluid">
			<div class="navbar-header">
				<a class="navbar-brand color-me-black" href="">WorkineThor</a>
			</div>
			<ul class="nav navbar-nav">
				<li><a href="homepage" class="color-me-black">Home</a>
				<li><a href="jsp/create-project.jsp" class="color-me-black">Create Project</a>
				<li><a href="browse" class="color-me-black">Browse Projects</a>
			</ul>
			<ul class="nav navbar-nav navbar-right">
				<li><a href="#" class="color-me-black"><img src="images/signup.png" class="media-object" style="width:20px">Sign Up</a>
			</ul>
		</div>
	</nav>
	
	<!-- project data (members and files) -->
	<div class="tumbotron text-center">
		<h2 style="color:white">Project: ${active_project}<br></h2>
		<h2 style="color:white">--Members--</h2>
		<br>
		<c:forEach items="${project_users}" varStatus="i">
			<label for="${project_users[i.index]}" style="color:white">${project_users[i.index]}</label><br>
		</c:forEach>
		<h3><input type="button" class="btn btn-default" value="add member"></h3>
	</div>
	
	<div class="tumbotron text-center">
		<h2 style="color:white">--Files--</h2>
		<br>
		<c:forEach items="${files}" varStatus="i">
			<label for="${files[i.index]}" style="color:white">${files[i.index]}</label><br>
		</c:forEach>
		<h3><input type="button" class="btn btn-default" value="add file" onclick="addFile()"></h3>
	</div>
	
<script>
	
	/* method that calls AddFileServlet --> sendRedirect*/
	function addFile(){
		var name = "${active_project}";
		document.location.href = "jsp/add-file.jsp?project-name=" + name;
	}
	
	/* method that catches url params */ 
	function getParams(parameter){
		var queryString = window.location.search;
		var urlParams = new URLSearchParams(queryString);
		
		var value = urlParams.get(parameter);
		
		return value;
	}
</script>
	
</body>
</html>