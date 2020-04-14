<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>

<%@ include file="/WEB-INF/jsp/include.jsp"%>

<html>
<head>
	<%@ include file="/WEB-INF/jsp/head.jsp"%>
	<title>Boo'Book :: Mot de passe oublié</title>
</head>
<body>
	<section class="hero is-fullheight">  
        <div class="hero-head">
            <%@ include file="/WEB-INF/jsp/navbar.jsp"%>
        </div>
        
        <div class="hero-body">
          <div class="container has-text-centered">
	          <h1 class="title is-1">Mot de passe oublié ?</h1>
	          <h2 class="subtitle is-1">Un mail de réinitialisation a été envoyé a l'adresse <c:out value="${email}"></c:out></h2>
          </div>
        </div>
    </section>
</body>
</html>