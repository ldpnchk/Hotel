<%@include file="header.jsp"%>

<div class="row centered-form">
	<div class="col-xs-12 col-sm-8 col-md-4 col-sm-offset-2 col-md-offset-4">
		<div class="panel panel-default">
			<div class="panel-heading">
				<h3 class="panel-title text-center">Please Register</h3>
			</div>
			<div class="panel-body">
				<form name="registration" action="./register" method="post">
					<div class="form-group">
						<input type="text" name="username" id="username" class="form-control input-sm" placeholder="Username">
					</div>

					<div class="form-group">
						<input type="email" name="email" id="email" class="form-control input-sm" placeholder="Email Address">
					</div>
					
					<div class="form-group">
						<input type="text" name="phonenumber" id="phonenumber" class="form-control input-sm" placeholder="Phone Number">
					</div>
					
					<div class="form-group">
						<input type="text" name="firstname" id="firstname" class="form-control input-sm" placeholder="First Name">
					</div>
					
					<div class="form-group">
						<input type="text" name="lastname" id="lastname" class="form-control input-sm" placeholder="Last Name">
					</div>
					
					<div class="form-group">
						<input type="text" name="patronymic" id="patronymic" class="form-control input-sm" placeholder="Patronymic">
					</div>
		
					<div class="form-group">
							<input type="password" name="password" id="password" class="form-control input-sm" placeholder="Password">
					</div>

					<input type="submit" value="Register" class="btn btn-info btn-block">

				</form>
			</div>
		</div>
	</div>
</div>

<%@include file="footer.jsp"%>