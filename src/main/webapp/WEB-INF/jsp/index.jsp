<!DOCTYPE html>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:url var="list" value="/course/list" />
<c:url var="css" value="/style.css" />

<html>
<head>
	<meta charset="UTF-8">
	<title>Spring boot application</title>
	<link rel="stylesheet" href="${css}">
</head>
<body>
	<h1>Spring boot application</h1>
	<p>
		Message is
		<c:out value="${message}" />
	</p>
	<p>
		<a href="${list}">Course</a>
	</p>
</body>
</html>