<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>

<%@ include file="/WEB-INF/jsp/include.jsp"%>

<c:url var="reset" value="/log/resetpassword" />

<html>
<head>
	<%@ include file="/WEB-INF/jsp/head.jsp"%>
	<title>Boo'Book :: Changement de mot de passe</title>
</head>
<body>
	<section class="hero is-fullheight">  
        <div class="hero-head">
            <%@ include file="/WEB-INF/jsp/navbar.jsp"%>
        </div>
        
        <div class="hero-body">
          <div class="container has-text-centered">
          <h1 class="title is-1">Entrez votre nouveau mot de passe</h1>
            <div class="columns is-centered">
                <div class="column is-one-quarter">
                  <form action="${reset }" method="POST">
                  	<input type="hidden" name="token" value="${param.token}">
                      <div class="field">
                          <p class="control has-icons-left has-icons-right">
                            <input class="input" placeholder="mot de passe" name="password" required>
                            <span class="icon is-small is-left">
                              <i class="fas fa-lock"></i>
                            </span>
                          </p>
                        </div>
                        <div class="field is-grouped is-grouped-centered">
                          <p class="control">
                            <button class="button is-info">Changer le mot de passe</button>
                          </p>
                      </div>
                  </form>
                </div>
            </div>
          </div>
        </div>
    </section>
</body>
</html>