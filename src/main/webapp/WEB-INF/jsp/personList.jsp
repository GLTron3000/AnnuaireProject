<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>

<%@ include file="/WEB-INF/jsp/include.jsp"%>

<c:url var="view" value="/profiles" />

<html>
<head>
	<%@ include file="/WEB-INF/jsp/head.jsp"%>
	<title>Boo'Book :: Liste des personnes</title>
</head>
<body>
	<section class="hero is-fullheight">  
        <div class="hero-head">
            <%@ include file="/WEB-INF/jsp/navbar.jsp"%>
        </div>
 		<div class="hero-body">
        	<div class="container">
				<h1 class="title is-1">Personnes:</h1>
			<c:forEach items="${groups}" var="group">
				<c:forEach items="${group.getPersons()}" var="person">
					<div class="card">
	                  <a href="${view}?id=${person.getId()}">
	                    <div class="card-content">
	                        <p class="title"><c:out value="${person.getName()}" /> <c:out value="${person.getFirstname()}" /></p>
	                        <p class="subtitle"><c:out value="${group.getName()}" /></p>
	                    </div>
	                  </a>
	              </div>
				</c:forEach>
			</c:forEach>        
          	</div>
        </div>
    </section>
</body>
</html>