<%@ include file="common/header.jspf"%>

<div class="container">
	<div class="main-content">
		<div class="col-md-4"></div>
		<div class="col-md-4">
			<h2 class="form-heading text-center">Sign up </h2>
			<div class="form-group ">
					<span class="text-center txt-error">${errorMsg}</span>
					<p class="text-center txt-success">${msg}</p>
					
					<form:form method="post" modelAttribute="user">
						<fieldset class="form-group">
							<form:input path="username" type="text" class="form-control" placeholder="Username" autofocus="true" required="required"/>
							<form:errors path="username" cssClass="text-error" />
						</fieldset>

						<fieldset class="form-group">
							<form:input path="password" type="password" class="form-control" placeholder="Password" required="required"/>
							<form:errors path="password" cssClass="text-error" />
						</fieldset>
						
						<fieldset class="form-group">
							<form:input path="confirmation" type="password" class="form-control" placeholder="Confirmation" required="required"/>
							<form:errors path="confirmation" cssClass="text-error" />
							
						</fieldset>

						<p>
					   		<button class="btn btn-lg btn-primary btn-block" type="submit">Sign up</button>
						</p>
						<a class="btn btn-lg btn-default btn-block" href="/login">Back to log in page</a>
					</form:form>        
				</div>
		</div>
	</div>
</div>
	
<%@ include file="common/footer.jspf"%>