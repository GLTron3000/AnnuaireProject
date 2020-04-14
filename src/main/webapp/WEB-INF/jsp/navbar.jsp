<%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %>

<nav class="navbar" role="navigation" aria-label="main navigation">
  <div class="navbar-brand">
    <a class="navbar-item" href="/">
      <h1 class="title">Boo'Book</h1>
    </a>

    <a role="button" class="navbar-burger burger" aria-label="menu" aria-expanded="false" data-target="navbarBasic">
      <span aria-hidden="true"></span>
      <span aria-hidden="true"></span>
      <span aria-hidden="true"></span>
    </a>
  </div>

  <div id="navbarBasic" class="navbar-menu">
    <div class="navbar-start">
      <a class="navbar-item" href="/">
      	<span class="icon is-medium"><i class="fas fa-search"></i></span>
      	<span>Rechercher</span>
      </a>    
      <a class="navbar-item" href="/profiles">
      	<span class="icon is-medium"><i class="fas fa-users"></i></span>
      	<span>Personnes</span>
      </a>
      <a class="navbar-item" href="/groups">
      	<span class="icon is-medium"><i class="fas fa-folder"></i></span>
      	<span>Groupes</span>
      </a>
      <a class="navbar-item">
      	<span class="icon is-medium"><i class="fas fa-question-circle"></i></span>
      	<span>A Propos</span>
      </a>
    </div>
    
    <div class="navbar-end">
       <div class="navbar-item">
         <div class="buttons">
         
         	<c:choose>
			    <c:when test="${sessionScope.user.getPerson() == null}">
			        <a class="button is-light" href="/log">
			        	<span class="icon"><i class="fas fa-sign-in-alt"></i></span>
			        	<span>Connexion</span>
			        </a>
			    </c:when>    
			    <c:otherwise>
			    	<a class="button is-primary" href="/profiles?id=${sessionScope.user.getPerson().getId()}">
			    		<span class="icon"><i class="fas fa-user"></i></span>
			    		<span>Mon profile</span>
			    	</a> 
			    	<a class="button is-danger" href="/log/out">
			    		<span class="icon"><i class="fas fa-sign-out-alt"></i></span>
			    		<span>Déconnexion</span>
			    	</a> 
			    </c:otherwise>
			</c:choose>
         
        </div>
       </div>
     </div>

  </div>
</nav>