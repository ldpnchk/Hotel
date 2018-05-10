<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%request.setCharacterEncoding("UTF-8");%>

<%@ page isELIgnored="false" %> 

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="language" scope="session" value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}"  />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="/i18n/messages" />

<html lang="${language}">
	<head>
		<title>Hotel</title>
		<meta charset=UTF-8>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<link rel="icon" href="data:;base64,=">
		<script type="text/javascript" src="https://cdn.jsdelivr.net/jquery/latest/jquery.min.js"></script>
		<link rel="stylesheet" href="//netdna.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
		<script src="//netdna.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
		<script type="text/javascript" src="https://cdn.jsdelivr.net/momentjs/latest/moment.min.js"></script>
		<script type="text/javascript" src="https://cdn.jsdelivr.net/npm/daterangepicker/daterangepicker.min.js"></script>
		<link rel="stylesheet" type="text/css" href="https://cdn.jsdelivr.net/npm/daterangepicker/daterangepicker.css" />
		<!-- root path -->
		<script type="text/javascript"> var domainURL = window.location.protocol + "//"  + window.location.host + /* "" + window.location.pathname; */ "/"; </script>
		<link rel="shortcut icon" href="${pageContext.request.contextPath}/resources/img/favicon.png">
    </head>

	<body>

		<div class="navbar navbar-default">
			<div class="navbar-header">

				<a class="navbar-brand" href="${pageContext.request.contextPath}/hotel/main">HOTEL</a>
			</div>
			<div id="navbar" class="collapse navbar-collapse">
				<ul class="nav navbar-nav">
					<li><a href="${pageContext.request.contextPath}/hotel/main">Main Page</a></li>
					<c:if test="${user.getUserRole() eq 'CLIENT'}">
						<li><a href="${pageContext.request.contextPath}/hotel/client">Client page</a></li>
						<li><a href="${pageContext.request.contextPath}/hotel/reservation">Make Reservation</a></li>
					</c:if>
					<c:if test="${user.getUserRole() eq 'ADMINISTRATOR'}">
						<li><a href="${pageContext.request.contextPath}/hotel/admin">Admin page</a></li>

					</c:if>
				</ul>
				<ul class="nav navbar-nav navbar-right">
					<form>
						<select id="language" name="language" onchange="submit()">
                			<option value="en_US" ${language == 'en_US' ? 'selected' : ''}>English</option>
               				<option value="uk_UA" ${language == 'uk_UA' ? 'selected' : ''}>Ukrainian</option>
           				</select>
           			</form>
           			<li><fmt:message key="hello" /></li>
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