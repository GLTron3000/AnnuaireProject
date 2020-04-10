<%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %>

<nav class="navbar" role="navigation" aria-label="main navigation">
  <div class="navbar-brand">
    <a class="navbar-item" href="/">
      <h1 class="title">Boo'Book</h1>
    </a>

    <a role="button" class="navbar-burger burger" aria-label="menu" aria-expanded="false" data-target="navbarBasicExample">
      <span aria-hidden="true"></span>
      <span aria-hidden="true"></span>
      <span aria-hidden="true"></span>
    </a>
  </div>

  <div id="navbar" class="navbar-menu">
    <div class="navbar-start">
      <a class="navbar-item" href="/">Rechercher</a>    
      <a class="navbar-item" href="/profiles">Personnes</a>
      <a class="navbar-item" href="/groups">Groupes</a>
      <a class="navbar-item">A Propos</a>
    </div>
    
    <div class="navbar-end">
       <div class="navbar-item">
         <div class="buttons">
         <c:choose>
		    <c:when test="${sessionScope.user.getPerson() == null}">
		        <a class="button is-light" href="/log">Connexion</a>
		    </c:when>    
		    <c:otherwise>
		    	<a class="button is-primary" href="/profiles?id=${sessionScope.user.getPerson().getId()}">Mon profile</a> 
		    	<a class="button is-danger" href="/logout">Déconnexion</a> 
		    </c:otherwise>
		</c:choose>
           
         </div>
       </div>
     </div>

  </div>
</nav>