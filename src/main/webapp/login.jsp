<%@include file="header.jsp"%>

<div class="col-md-4 col-md-offset-4">
	<div class="container">
		<div class="col-md-4">
			<div class="panel panel-default">
				<div class="panel-heading" style="background-color: lightcyan !important;">
					<h1 class="panel-title"><strong><fmt:message key="sign.in"/></strong></h1>
				</div>
				<div class="panel-body">
					<form action="${pageContext.request.contextPath}/hotel/loginpost" method="post" >
						<div class="form-group">
							<label for="email"><fmt:message key="username"/></label>
							<input type="text" name="username" class="form-control" id="email" placeholder="<fmt:message key="username"/>" required="required"/>
						</div>
						<div class="form-group">
							<label for="pwd"><fmt:message key="password"/></label>
							<input type="password" name="password" class="form-control" id="pwd" placeholder="<fmt:message key="password"/>" required="required"/>
						</div>
						<div style="text-align: center">
							<button type="submit" class="btn btn-md btn-success"><fmt:message key="sign.in"/></button>
						</div>
						<div style="text-align: center; margin-top: 10px">
							<p><fmt:message key="not.registered.yet"/> <a href='${pageContext.request.contextPath}/hotel/registration'><fmt:message key="register.here"/></a></p>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
</div>

<%@include file="footer.jsp"%>