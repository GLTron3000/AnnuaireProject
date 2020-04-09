<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>

<%@ include file="/WEB-INF/jsp/include.jsp"%>

<c:url var="view" value="/groups" />
<c:url var="findGroups" value="/groups/find" />

<html>
<head>
	<%@ include file="/WEB-INF/jsp/head.jsp"%>
	<title>Boo'Book :: Liste des groupes</title>
</head>
<body>
	<section class="hero is-fullheight">  
        <div class="hero-head">
            <%@ include file="/WEB-INF/jsp/navbar.jsp"%>
        </div>
        <div class="container has-text-centered">
	        <form action="${findCourses}" style='display:inline-block;'>
	                <div class="field has-addons">
	                    <div class="control is-expanded">
	                      <input class="input" type="text" placeholder="Trouver un groupe" size="20">
	                    </div>
	                    <div class="control">
	                      <button class="button is-info">Recherche</button>
	                    </div>
	                </div>
	            </form>
	    </div>
 		<div class="hero-body">
        	<div class="container">
        		<h1 class="title is-1">Groupes:</h1>

			<c:forEach items="${groups}" var="group">
				<div class="card">
                  <a href="${view}?id=${group.getId()}">
                    <div class="card-content">
                        <p class="title"><c:out value="${group.getName()}" /></p>
                    </div>
                  </a>
              </div>
			</c:forEach>
			            
          	</div>
        </div>
    </section>
</body>
</html>