<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>

<%@ include file="/WEB-INF/jsp/include.jsp"%>

<c:url var="view" value="/groups" />
<c:url var="findGroups" value="/groups/find" />

<html>
<head>
	<%@ include file="/WEB-INF/jsp/head.jsp"%>
	<title>Boo'Book :: Créer un compte</title>
</head>
<body>
	<section class="hero is-fullheight">  
        <div class="hero-head">
            <%@ include file="/WEB-INF/jsp/navbar.jsp"%>
        </div>
        
         <div class="container has-text-centered">
         	<form>
	         	<div class="form-group">
				    <label>Name</label>
				    <input class="input" type="text" placeholder="Enter your name..." size="20">
				</div>
				<div class="form-group">
				    <label>Firstname</label>
				    <input class="input" type="text" placeholder="Enter your firstname..." size="20">
				</div>
				<div class="form-group">
				    <label>Firstname</label>
				    <input class="input" type="text" placeholder="Enter your firstname..." size="20">
				</div>
				<div class="form-group">
				    <label>Birthdate</label>
				    <input class="input" type="text" placeholder="Enter your birthdate..." size="20">
				</div>
				<div class="form-group">
				    <label>E-mail</label>
				    <input class="input" type="text" placeholder="Enter mail adress..." size="20">
				</div>
				<div class="form-group">
				    <label>Website</label>
				    <input class="input" type="text" placeholder="Enter your website..." size="20">
				</div>
			  
			  <button type="submit" class="btn btn-primary">Submit</button>
			</form>
         </div>  
    </section>
</body>
</html>