<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>

<%@ include file="/WEB-INF/jsp/include.jsp"%>

<html>
<head>
	<%@ include file="/WEB-INF/jsp/head.jsp"%>
	<title>Boo'Book :: Connexion</title>
</head>
<body>
	<section class="hero is-fullheight">  
        <div class="hero-head">
            <%@ include file="/WEB-INF/jsp/navbar.jsp"%>
        </div>
        
        <div class="hero-body">
          <div class="container has-text-centered">
          <h1 class="title is-1">Connexion</h1>
            <div class="columns is-centered">
                <div class="column is-one-quarter">
                  <form action="/log" method="POST">
                      <div class="field">
                          <p class="control has-icons-left has-icons-right">
                            <input class="input" type="email" placeholder="Email" name="email" required>
                            <span class="icon is-small is-left">
                              <i class="fas fa-envelope"></i>
                            </span>
                          </p>
                        </div>
                        <div class="field">
                          <p class="control has-icons-left">
                            <input class="input" type="password" placeholder="Mot de passe" name="password" required>
                            <span class="icon is-small is-left">
                              <i class="fas fa-lock"></i>
                            </span>
                          </p>
                        </div>
                        <div>
	                        <c:if test = "${sessionScope.user.GetConnectionError() == true}">
	         					<p class="has-text-danger">Email ou mot de passe invalide</p>
	     			 		</c:if>
                        </div>
                        <div class="field is-grouped is-grouped-centered">
                          <p class="control">
                            <button class="button is-info">Se connecter</button>
                          </p>
                      </div>
                  </form>
                  <a class="button is-text is-small" href="/log/forgetpassword">mot de passe oublié ?</a>
                  <hr>
                  <button class="button is-light" disabled>Créer un compte</button>
                </div>
            </div>
          </div>
        </div>
    </section>
</body>
</html>