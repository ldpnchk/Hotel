<%@include file="header.jsp"%>

<div class="row centered-form">
	<div class="col-xs-12 col-sm-8 col-md-4 col-sm-offset-2 col-md-offset-4">
		<div class="panel panel-default">
			<div class="panel-heading">
				<h3 class="panel-title text-center"><fmt:message key="please.register"/></h3>
			</div>
			<div class="panel-body">
				<form name="registration" action="${pageContext.request.contextPath}/hotel/register" method="post">
					<div class="form-group">
						<input type="text" name="username" id="username" class="form-control input-sm" placeholder="<fmt:message key="username"/>" required>
					</div>

					<div class="form-group">
						<input type="email" name="email" id="email" class="form-control input-sm" placeholder="<fmt:message key="email.address"/>" required>
					</div>
					
					<div class="form-group">
						<input type="text" name="phonenumber" id="phonenumber" class="form-control input-sm" placeholder="<fmt:message key="phone.number"/>" required>
					</div>
					
					<div class="form-group">
						<input type="text" name="firstname" id="firstname" class="form-control input-sm" placeholder="<fmt:message key="first.name"/>" required>
					</div>
					
					<div class="form-group">
						<input type="text" name="lastname" id="lastname" class="form-control input-sm" placeholder="<fmt:message key="last.name"/>" required>
					</div>
					
					<div class="form-group">
						<input type="text" name="patronymic" id="patronymic" class="form-control input-sm" placeholder="<fmt:message key="patronymic"/>" >
					</div>
		
					<div class="form-group">
							<input type="password" name="password" id="password" class="form-control input-sm" placeholder="<fmt:message key="password"/>" required>
					</div>

					<input type="submit" value="<fmt:message key="register"/>" class="btn btn-info btn-block">

				</form>

				<c:if test="${!empty errors}">
					<center><font color="red">
						<c:forEach var="entry" items="${errors}">
							<p><fmt:message key="${entry.value}"/></p>
						</c:forEach>
					</font></center>
				</c:if>

			</div>
		</div>
	</div>
</div>

<%@include file="footer.jsp"%>