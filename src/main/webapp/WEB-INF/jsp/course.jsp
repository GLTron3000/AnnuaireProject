<!DOCTYPE html>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:url var="list" value="/course/list" />
<c:url var="newCourse" value="/course/new" />
<c:url var="findCourses" value="/course/find" />
<c:url var="css" value="/style.css" />

<html>
<head>
	<meta charset="UTF-8">
	<title>Spring boot application</title>
	<link rel="stylesheet" href="${css}">
</head>
<body>
	<h1>Courses</h1>
	<div>
		<a href="${newCourse}">New course</a> |
		<form action="${findCourses}" style='display:inline-block;'>
			<input name="name" size="10"/>
			<input type="submit" value="Find" />
		</form>
	</div>
	<c:forEach items="${courses}" var="course">
		<p>${course.id}: <c:out value="${course.name}" /></p>
	</c:forEach>
</body>

</html>
