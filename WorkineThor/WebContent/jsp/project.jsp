<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<h2>Project: ${active_project}<br></h2>
	
	--Members--<br>
	<c:forEach items="${project_users}" varStatus="i">
		<label for="${project_users[i.index]}">${project_users[i.index]}</label><br>
	</c:forEach>
</body>
</html>