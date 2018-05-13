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
		<title><fmt:message key="hotel"/></title>
		<meta charset=UTF-8>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<link rel="icon" href="data:;base64,=">
		<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery.min.js"></script>
		<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css">
		<script src="${pageContext.request.contextPath}/resources/js/bootstrap.min.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/moment-with-locales.min.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/daterangepicker.min.js"></script>
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/daterangepicker.css" />
		<!-- root path -->
		<script type="text/javascript"> var domainURL = window.location.protocol + "//"  + window.location.host + /* "" + window.location.pathname; */ "/"; </script>
		<link rel="shortcut icon" href="${pageContext.request.contextPath}/resources/img/favicon.png">
    </head>

	<body>

		<div class="navbar navbar-default">
			<div class="navbar-header">
				<a class="navbar-brand" href="${pageContext.request.contextPath}/hotel/main"><fmt:message key="hotel.uc"/></a>
			</div>
			<div id="navbar" class="collapse navbar-collapse">
				<ul class="nav navbar-nav">
					<li><a href="${pageContext.request.contextPath}/hotel/main"><fmt:message key="main.page"/></a></li>
					<c:if test="${empty user}">
						<li><a href="${pageContext.request.contextPath}/hotel/reservation"><fmt:message key="make.reservation"/></a></li>
					</c:if>
					<c:if test="${user.getUserRole() eq 'CLIENT'}">
						<li><a href="${pageContext.request.contextPath}/hotel/client"><fmt:message key="client.page"/></a></li>
						<li><a href="${pageContext.request.contextPath}/hotel/reservation"><fmt:message key="make.reservation"/></a></li>
					</c:if>
					<c:if test="${user.getUserRole() eq 'ADMINISTRATOR'}">
						<li><a href="${pageContext.request.contextPath}/hotel/admin"><fmt:message key="admin.page"/></a></li>

					</c:if>
				</ul>
				<ul class="nav navbar-nav navbar-right">
					<form>
						<select class="selectpicker" id="language" name="language" onchange="submit()">
                			<option value="en_US" ${language == 'en_US' ? 'selected' : ''}><fmt:message key="english"/></option>
               				<option value="uk_UA" ${language == 'uk_UA' ? 'selected' : ''}><fmt:message key="ukrainian"/></option>
           				</select>
           			
           			</form>
           		</ul>
           		<ul class="nav navbar-nav navbar-right">
					<c:choose>
						<c:when test="${empty user}">
							<li><a href="${pageContext.request.contextPath}/hotel/login"><fmt:message key="login"/></a></li>
						</c:when>
						<c:otherwise>
							<li><a href="${pageContext.request.contextPath}/hotel/updateProfile"><fmt:message key="profile"/></a></li>
							<li><a href="${pageContext.request.contextPath}/hotel/logout"><fmt:message key="logout"/></a></li>
						</c:otherwise>
					</c:choose>
				</ul>
			</div>
		</div>