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
	<section id="mainPage" class="hero is-primary is-fullheight" style="transition: background-color 5000ms linear;">  
        <div class="hero-head">
            <%@ include file="/WEB-INF/jsp/navbar.jsp"%>
        </div>
        
        <div class="hero-body">
          <div class="container has-text-centered">
            <h1 class="title is-1">Trouvez n'importe qui</h1>
            <h2 class="subtitle">Même s'il existe pas</h2>
            <form action="">
                <div class="field has-addons">
                    <div class="control is-expanded">
                      <input class="input" type="text" placeholder="Trouver une personne ou un groupe">
                    </div>
                    <div class="control">
                      <button class="button is-info">Recherche</button>
                    </div>
                </div>
            </form>
          </div>
        </div>
    </section>
</body>
</html>