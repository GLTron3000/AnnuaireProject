<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>

<%@ include file="/WEB-INF/jsp/include.jsp"%>

<html>
<head>
	<%@ include file="/WEB-INF/jsp/head.jsp"%>
	<title>Boo'Book :: ${person.getName()}</title>
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
                       <p class="title"><c:out value="${person.getName()}" /> <c:out value="${person.getFirstname()}" /></p>
                       <p class="subtitle"><c:out value="${person.getGroup().getName()}" /></p>
                       <hr>
                       <p class="subtitle"><c:out value="${person.getBirthdate()}" /></p>
                       <p class="subtitle"><c:out value="${person.getEmail()}" /></p>
                       <p class="subtitle"><c:out value="${person.getWebsite()}" /></p>
                   </div>
              </div>    
          	</div>
        </div>
    </section>
</body>
</html>