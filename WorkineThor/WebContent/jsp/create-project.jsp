<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
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
<title>Create Project</title>
</head>
<body background="../images/background.png">
	
	<!-- navigation -->
	<nav class="navbar navbar-default">
		<div class="container-fluid">
			<div class="navbar-header">
				<a class="navbar-brand color-me-black" href="">WorkineThor</a>
			</div>
			<ul class="nav navbar-nav">
				<li><a href="../homepage" class="color-me-black">Home</a>
				<li><a href="../browse" class="color-me-black">Browse Projects</a>
			</ul>
			<ul class="nav navbar-nav navbar-right">
				<li><a href="#" class="color-me-black"><img src="../images/signup.png" class="media-object" style="width:20px">Sign Up</a>
			</ul>
		</div>
	</nav>
	
	<!-- create form: sends data to CreateProjectServlet which will save the new project to the DB -->
	<div class="tumbotron">
		<h2 style="color:white">Create Project</h2>
		<br>
		<div class="form-horizontal">
			<label for="project_name" style="color:white"><h3>Insert Project Name</h3></label>
			<input type="text" class="form-control" style="width:170px" id="project_name_id" name="project_name" ></input>
			<br><br><br>
			<label style="color:white"><h3>Use Drive</h3></label>
			<input type="checkbox" name="drive_active" id="drive-active" onclick="driveActive()">
			<div class="bootstrap-select-wrapper">
  				<label style="color:white">Drive</label>
  				<select title="Scegli una opzione" id="drive-select" disabled>
    				<option value="gdrive">Google Drive</option>
   					<option value="mega">Mega</option>
   					<option value="dropbox">DropBox</option>
  				</select>
			</div>
			<br>
			<br>
			<input type="button" class="btn btn-default" value="Create" onclick="validateForm()">
		</div>
	</div>

<!-- javaScript to enable/disable drive selection -->
<script>
	
	/* activates drive selection if checkbox is checked */
	function driveActive() {
		var checkbox = document.getElementById("drive-active");
		var driveSelect = document.getElementById("drive-select");
		
		if(checkbox.checked == false){
			driveSelect.disabled = true;
		}
		else{
			driveSelect.disabled = false;
		}
	}
	
	/* method that redirects to servlet if project textfield is non-empty */
	function validateForm(){
		var project_name = document.getElementById("project_name_id");
		var checkbox = document.getElementById("drive-active");
		var driveSelect = document.getElementById("drive-select");	
		var selectedDriveItem = driveSelect.options[driveSelect.selectedIndex].text
		
		if(project_name.value.length == 0){
			alert("Insert a project name!");
		}
		else{
			document.location.href = "../create-project?project-name=" + project_name.value + 
			 							"&drive-active=" + checkbox.checked + 
			 							"&drive-select=" + selectedDriveItem;
		}
	}
	
	/* method that catches url params */ 
	function getParams(parameter){
		var queryString = window.location.search;
		var urlParams = new URLSearchParams(queryString);
		
		var value = urlParams.get(parameter);
		
		return value;
	}
	
	/* method that checks if project name is already taken */
	function checkIfExist(){
		var failure = getParams("failure");
	
		if(failure)
			alert("Project name already taken");
	}
	
	window.onload = checkIfExist();
	
</script>

</body>
</html>