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
 <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
 <!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">

<!-- Optional theme -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">

<!-- Latest compiled and minified JavaScript -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
<title>
Add Member
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
				<li><a href="homepage" class="color-me-black">Home</a>
				<li><a href="jsp/create-project.jsp" class="color-me-black">Create Project</a>
			</ul>
			<ul id="right-buttons" class="nav navbar-nav navbar-right">
				<li><a href="#" class="color-me-black"><img src="images/signup.png" class="media-object" style="width:20px">Sign Up</a>
			</ul>
		</div>
	</nav>
	
	<br>
	
	<!-- Browse Member-->
	<div class="tumbotron text-center">
		<h2 style="color:black">Members to add</h2>
		<div class="form-horizontal center_div">
		<input type="text" class="form-control" style="width:170px" id="searchField" name="search" placeholder="Search!" ></input>
		</div>	
		<br>
		<ul class="list-group justify-content-center" >
   		<c:forEach items="${member_list}" varStatus="i" >
   			<li class="list-group-item" style="width:170px">
   					<img src="images/login.png" class="media-object" style="width:20px">    				
 					<button type="button" class="btn btn-primary btn-lg" data-toggle="modal" data-target="#myModal">
					${member_list[i.index]}
					</button>
					<!-- Modal -->
					<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
					  <div class="modal-dialog" role="document">
					    <div class="modal-content">
					      <div class="modal-header">
					        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
					        <h4 class="modal-title" id="myModalLabel">Add Member</h4>
					      </div>
					      <div class="modal-body">
					       Do you want to add ${member_list[i.index]} into your project?
					      </div>
					      <div class="modal-footer">
					        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
					        <button type="button" class="btn btn-primary">Add</button>
					      </div>
					    </div>
					  </div>
					</div>
   				<br>
    		</li>		 
    	</c:forEach>
    	
		</ul>
	</div>
</body>

<script>
	
	/* fetch user logged */
	<% String user = Session.getSession().getLoggedUser().getUsername(); %>
	var userLogged = "<%= user%>";
	
	/* called on page load */
	window.onload = function() {
		/* if user exists */
		if(userLogged){
			document.getElementById("right-buttons").children[0].style.display = "none";
		}
	};
</script>

</html>