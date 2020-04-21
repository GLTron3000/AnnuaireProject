<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>

<%@ include file="/WEB-INF/jsp/include.jsp"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<c:url var="view" value="/groups" />

<html>
<head>
	<%@ include file="/WEB-INF/jsp/head.jsp"%>
	<title>Boo'Book :: Edit Profile</title>
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
                   
                       <form:form method="POST" modelAttribute="person">

				            <form:errors path="*" cssClass="subtitle has-text-danger" element="p" />
							
							<div class="field">
							  <label class="label">Prenom</label>						  
							  <form:input class="control" path="firstname" />
				              <form:errors path="firstname" cssClass="help is-danger" element="p" />
							</div>
							
							<div class="field">
							  <label class="label">Nom</label>						  
							  <form:input class="control" path="name" />
				              <form:errors path="name" cssClass="help is-danger" element="p" />
							</div>
							
							<div class="field">
							  <label class="label">Groupe</label>						  
							  <form:select path="group.id" multiple="false" class="form-control">
							        <form:options items="${groups}"/>
							    </form:select>
				              <form:errors path="group" cssClass="help is-danger" element="p" />
							</div>
							
							<div class="field">
							  <label class="label">Date de naissance</label>						  
							  <form:input type="date" class="control" path="birthdate"/>
				              <form:errors path="birthdate" cssClass="help is-danger" element="p" />
							</div>
							
							<div class="field">
							  <label class="label">Email</label>						  
							  <form:input class="control" path="email" />
				              <form:errors path="email" cssClass="help is-danger" element="p" />
							</div>
							
							<div class="field">
							  <label class="label">Site web</label>						  
							  <form:input class="control" path="website" />
				              <form:errors path="website" cssClass="help is-danger" element="p" />
							</div>
							
							<div class="field">
							  <label class="label">Mot de passe</label>						  
							  <form:input class="control" path="password" />
				              <form:errors path="password" cssClass="help is-danger" element="p" />
							</div>
																	
				            <div class="field">
				            	<div class="control">
							    	<button type="submit" class="button is-link">Sauvegarder</button>
							  	</div>
				            </div>
				            
			        	</form:form>
                       
                   </div>
              </div>    
          	</div>
        </div>
    </section>
</body>
</html>