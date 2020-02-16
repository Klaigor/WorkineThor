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
	<label for="user">User: ${user}!</label><br>
	
	<ul>
    <c:forEach items="${project_list}" varStatus="i">
    	<li><a href="project?id=${project_list[i.index]}">${project_list[i.index]}</a></li>
    </c:forEach>
	</ul>
	
</body>
</html>