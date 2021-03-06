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
${active_project}
</title>
</head>
<body>

	<!-- navigation -->
	<nav class="navbar navbar-default">
		<div class="container-fluid">
			<div class="navbar-header">
				<a class="navbar-brand color-me-black" href="">WorkineThor</a>
			</div>
			<ul id="main-buttons" class="nav navbar-nav">
				<li><a href="#home" class="color-me-black" onclick="handleHome()">Home</a>
				<li><a href="jsp/create-project.jsp" class="color-me-black">Create Project</a>
				<li><a href="browse" class="color-me-black">Browse Projects</a>
			</ul>
			<ul id = "right-buttons" class="nav navbar-nav navbar-right">
				<li><a href="signup" class="color-me-black"><img src="images/signup.png" class="media-object" style="width:20px">Signup</a>
				<li><a href="logout" class="color-me-black"><img src="images/logout.png" class="media-object" style="width:20px">Logout</a>
			</ul>
		</div>
	</nav>
	
	<!-- project data (members and files) -->
	<div class="tumbotron text-center">
		<h2 style="color:black">Project: ${active_project}<br></h2>
		<h2 style="color:black">--Members--</h2>
		<br>
		<c:forEach items="${project_users}" varStatus="i">
			<label for="${project_users[i.index]}" style="color:black">${project_users[i.index]}</label><br>
		</c:forEach>
		<h3><input type="button" id="add-member-button" class="btn btn-default" value="add member" onclick="addMember()"></h3>
	</div>
	
	<div class="tumbotron text-center">
		<h2 style="color:black">--Files--</h2>
		<br>
		<c:forEach items="${files}" varStatus="i">
			<label for="${files[i.index]}" style="color:black">${files[i.index]}</label><br>
		</c:forEach>
		<h3><input type="button" id="add-file-button" class="btn btn-default" value="add file" onclick="addFile()"></h3>
	</div>
	<br>
	<!-- sends to a servlet that retrieves and shows project duties -->
	<div class="tumbotron text-center">
		<h2 style="color:black">--Duties--</h2>
		<br>
		<h3><input type="button" id="duties-button" class="btn btn-default" value="Duties" onclick="showDuties()"></h3>
	</div>	
	
	<div class="tumbotron text-center">
		<input type="button" id="request-join-button" style="" class="btn btn-default" value="request to join" onclick="request()">
	</div>
	
<script>
	<% String user = Session.getSession().getLoggedUser().getUsername(); %>
	
	var userLogged = "<%= user%>";
	window.onload = function() {
		if(userLogged === ""){
			document.getElementById("right-buttons").children[0].style.display = "none";
			document.getElementById("right-buttons").children[1].style.display = "none";
			document.getElementById("main-buttons").children[1].style.display = "none";
			document.getElementById("add-member-button").style.display = "none";
			document.getElementById("add-file-button").style.display = "none";
			document.getElementById("duties-button").style.display = "none";
			document.getElementById("request-join-button").style.display = "none";
		}
		else{
			document.getElementById("right-buttons").children[0].style.display = "none";
		}
	};
	
		/* method that calls ShowDutiesServlet --> sendRedirect*/
	function showDuties() {
		var name = "${active_project}";
		document.location.href = "duties?project-name=" + name;
	}

	
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
	
	/* method taht call AddMemberServlet */
	function addMember(){
		var name = "${active_project}";
		document.location.href = "add-member?project-name=" + name;
	}
</script>

</body>
</html>
