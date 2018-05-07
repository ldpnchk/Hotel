<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>

<%@ page isELIgnored="false" %> 

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
	<head>
	
		<title>Hotel</title>
		<link rel="stylesheet" href="//netdna.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
		<script src="//netdna.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
		
		<body>

		<div class="navbar navbar-default">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#navbar">
					<span class="icon-bar"></span>
					<span class="icon-bar"></span>
					<span class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="${pageContext.request.contextPath}/hotel/main">HOTEL</a>
			</div>
			<div id="navbar" class="collapse navbar-collapse">
				<ul class="nav navbar-nav">
					<li><a href="${pageContext.request.contextPath}/hotel/main">Main Page</a></li>
					<c:if test="${user.getUserRole() eq 'CLIENT'}">
						<li><a href="${pageContext.request.contextPath}/hotel/client">Client page</a></li>
					</c:if>
					<c:if test="${user.getUserRole() eq 'ADMINISTRATOR'}">
						<li><a href="${pageContext.request.contextPath}/hotel/admin">Admin page</a></li>
					</c:if>
				</ul>
				<ul class="nav navbar-nav navbar-right">
					<c:choose>
						<c:when test="${empty user}">
							<li><a href="${pageContext.request.contextPath}/hotel/login">Login</a></li>
						</c:when>
						<c:otherwise>
							<li><a href="${pageContext.request.contextPath}/hotel/logout">Logout</a></li>
						</c:otherwise>
					</c:choose>
				</ul>
			</div>
		</div>