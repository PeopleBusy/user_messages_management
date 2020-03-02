<%@ include file="common/header.jspf"%>

<div class="container">
	<div class="main-content">
		<div class="col-md-4"></div>
		<div class="col-md-4">
			<h2 class="form-heading text-center">Sign in</h2>
			<form method="POST" action="/login" class="form-signin">
				<div class="form-group ">
				
					<p class="text-center ${error != null ? 'txt-error' : 'txt-success'}">${msg}</p>
					
					<p>
					  <input name="username" type="text" class="form-control" placeholder="Username" autofocus required/>
					</p>
					<p>
					   <input name="password" type="password" class="form-control" placeholder="Password" required/>
					</p>
					       
					<p>
					   <button class="btn btn-lg btn-primary btn-block" type="submit">Sign In</button>
					</p>
					<a class="btn btn-lg btn-default btn-block" href="/register">Create account here</a>         
				</div>				
			</form>
		</div>
	</div>
</div>
	
<%@ include file="common/footer.jspf"%>