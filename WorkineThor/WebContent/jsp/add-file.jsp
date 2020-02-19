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
AddFile
</title>

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
				<li><a href="create-project.jsp" class="color-me-black">Create Project</a>
			</ul>
			<ul class="nav navbar-nav navbar-right">
				<li><a href="#" class="color-me-black"><img src="../images/signup.png" class="media-object" style="width:20px">Sign Up</a>
			</ul>
		</div>
	</nav>
	
	<!-- centered container -->
	<div class="tumbotron text-center">
		<input type="file" id="fileElem" multiple style="display:none">
		<button id="fileSelect" onclick="checkFile()">Select File</button>
	
		<br>
		<br>
	
		<!-- shows all the selected files -->
		<div id="file-container">
			<label for="title" id="title-id" style="color:white;"></label>
		</div>
	
		<!-- requestDispatcher to AddFileServlet -->
		<div class="form-horizontal" align="center">
			<form action="../add-file" method="post">
				<input type="hidden" id="file_array" name="fileArray" >
				<input type="submit" id="file-submit" class="btn btn-default" value="Add Files">
			</form>
		</div>
	</div>
	
	
<script>

	/* selected file*/
	var file;
	
	/* array of selected files --> sent to servlet */
	var fileArray = [];
	
	/* change text to title-id label */
  	document.getElementById("title-id").innerHTML = "<h3> Selected Files </h3>";
  	
	/* on file selected do method */
	document.getElementById('fileElem').onchange = function () {
	  	
		/* selected file name */
		file = this.value
		fileArray.push(this.value);

		/* create a <br> to newLine the elements */
	  	var newLine = document.createElement("br");
	  	document.getElementById("file-container").appendChild(newLine);
		
	  	/* create a new label to show selected files */
	  	var fileLabel = document.createElement("label");
	  	fileLabel.innerHTML = file;
	  	fileLabel.style = "color:white;"
	  	document.getElementById("file-container").appendChild(fileLabel);
	  	
	  	/* assign input hidden element value --> fileArray*/
	  	document.getElementById("file_array").value = fileArray;
	  	
	};
		
	/* onclick open file browser */	
	function checkFile(){
		var fileElem = document.getElementById("fileElem");
		if(fileElem)
			fileElem.click();
	}
		
</script>
</body>
</html>