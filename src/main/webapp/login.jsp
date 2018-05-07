<%@include file="header.jsp"%>

<div class="col-md-4"></div>
<div class="col-md-4">
	<div class="container">
		<div class="col-md-4">
			<div class="panel panel-default">
				<div class="panel-heading">
					<h1 class="panel-title"><strong>Sign In </strong></h1>
				</div>
				<div class="panel-body">
					<form action="./loginpost" method="post" >
						<div class="form-group">
							<label for="email">Username</label>
							<input type="text" name="username" class="form-control" id="email" placeholder="Username" required="required"/>
						</div>
						<div class="form-group">
							<label for="pwd">Password</label>
							<input type="password" name="password" class="form-control" id="pwd" placeholder="Password" required="required"/>
						</div>
						<div style="text-align: center">
							<button type="submit" class="btn btn-sm btn-default">Sign in</button>
						</div>
						<div style="text-align: center; margin-top: 10px">
							<p>Not registered yet? <a href='./registration'>Register Here</a></p>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
</div>

<%@include file="footer.jsp"%>