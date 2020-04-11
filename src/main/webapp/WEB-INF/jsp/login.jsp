<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>

<%@ include file="/WEB-INF/jsp/include.jsp"%>

<html>
<head>
	<%@ include file="/WEB-INF/jsp/head.jsp"%>
	<title>Boo'Book</title>
</head>
<body>
	<section class="hero is-fullheight">  
        <div class="hero-head">
            <%@ include file="/WEB-INF/jsp/navbar.jsp"%>
        </div>
        
        <div class="hero-body">
          <div class="container has-text-centered">
          <h1 class="title is-1">Login</h1>
            <div class="columns is-centered">
                <div class="column is-one-quarter">
                  <form action="/log" method="POST">
                      <div class="field">
                          <p class="control has-icons-left has-icons-right">
                            <input class="input" type="email" placeholder="Email" name="email">
                            <span class="icon is-small is-left">
                              <i class="fas fa-envelope"></i>
                            </span>
                          </p>
                        </div>
                        <div class="field">
                          <p class="control has-icons-left">
                            <input class="input" type="password" placeholder="Mot de passe" name="password">
                            <span class="icon is-small is-left">
                              <i class="fas fa-lock"></i>
                            </span>
                          </p>
                        </div>
                        <div class="field is-grouped is-grouped-centered">
                          <p class="control">
                            <button class="button is-info">Se connecter</button>
                          </p>
                      </div>
                  </form>
                  <hr>
                  <button class="button is-light">Créer un compte</button>
                </div>
            </div>
          </div>
        </div>
    </section>
</body>
</html>