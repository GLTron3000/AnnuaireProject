<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>

<%@ include file="/WEB-INF/jsp/include.jsp"%>

<c:url var="view" value="/profiles" />

<html>
<head>
	<%@ include file="/WEB-INF/jsp/head.jsp"%>
	<title>Boo'Book :: ${group.getName()}</title>
</head>
<body>
	<section class="hero is-fullheight">  
        <div class="hero-head">
            <%@ include file="/WEB-INF/jsp/navbar.jsp"%>
        </div>
 		<div class="hero-body">
        	<div class="container">
				<div class="card">
                   <div class="card-content">
                       <p class="title"><c:out value="${group.getName()}" /></p>
                       <hr>
                       <p class="subtitle">Adhérents</p>
                       <div class="list is-hoverable">
                      	<c:forEach items="${group.getPersons()}" var="person">
			            	<a class="list-item" href="${view}?id=${person.getId()}"><c:out value="${person.getName()}" /> <c:out value="${person.getFirstname()}" /></a>
                       	</c:forEach>
                       </div>
                   </div>
              </div>    
          	</div>
        </div>
    </section>
</body>
</html>