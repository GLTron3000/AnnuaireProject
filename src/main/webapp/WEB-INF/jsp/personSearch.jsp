<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>

<%@ include file="/WEB-INF/jsp/include.jsp"%>

<c:url var="view" value="/profiles" />
<c:url var="findPerson" value="/profiles/find" />
<c:url var="edit" value="/groups/edit" />


<html>
<head>
	<%@ include file="/WEB-INF/jsp/head.jsp"%>
	<title>Boo'Book :: Recherche de personnes</title>
</head>
<body>
	<section class="hero is-fullheight">  
        <div class="hero-head">
            <%@ include file="/WEB-INF/jsp/navbar.jsp"%>
        </div>
                
 		<div class="hero-body">
        	<div class="container">
				<h1 class="title is-1"><c:out value="${persons.size()}" /> personnes trouvées:</h1>
				
				<c:forEach items="${persons}" var="person">
					<div class="card">
	                  <a href="${view}?id=${person.getId()}">
	                    <div class="card-content">
	                        <p class="title is-5"><c:out value="${person.getName()}" /> <c:out value="${person.getFirstname()}" /> | <c:out value="${person.getGroup().getName()}" /></p>
	                    </div>
	                  </a>
	              </div>
				</c:forEach>
				
          	</div>
        </div>
        
    </section>
</body>
</html>