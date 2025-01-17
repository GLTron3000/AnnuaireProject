<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>

<%@ include file="/WEB-INF/jsp/include.jsp"%>

<c:url var="edit" value="/profiles/edit" />

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
                       
                       <c:if test="${sessionScope.user.getIsLogged()}">
	                       <p class="subtitle">
	                       	<span class="icon"><i class="fas fa-birthday-cake"></i></span>
	                       	<fmt:parseDate pattern = "yyyy-MM-dd" value = "${person.getBirthdate()}" var="formatedDate"/>
	                       	<fmt:formatDate pattern = "dd/MM/yyyy" value = "${formatedDate}" />
	                       </p>
	                       <p class="subtitle">
	                       	<span class="icon"><i class="fas fa-envelope"></i></span>
	                       	<c:out value="${person.getEmail()}" />
	                       </p>
                       </c:if>
                       
                       <p class="subtitle">
                       	<span class="icon"><i class="fas fa-home"></i></span>
                       	<c:out value="${person.getWebsite()}" />
                       </p>
                       
                       <c:if test="${sessionScope.user.getPerson().getId().equals(person.getId())}">
                       	<a class="button is-warning" href="${edit }?id=${sessionScope.user.getPerson().getId()}">
                       		<span class="icon"><i class="fas fa-edit"></i></span>
                       		<span>Editer le profile</span>
                       	</a>
                       </c:if>
                       
                   </div>
              </div>    
          	</div>
        </div>
    </section>
</body>
</html>