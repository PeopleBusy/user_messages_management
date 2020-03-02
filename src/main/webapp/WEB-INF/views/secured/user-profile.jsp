<%@ include file="../common/navigation.jspf"%>

<div class="container">
	<div class="main-content">
		<div class="col-md-3"></div>
		<div class="col-md-6">
			<h2 class="form-heading text-center">Your informations</h2>
			<div class="form-group ">
					<p class="text-center txt-success">${msg}</p>					
					<form:form method="post" modelAttribute="user">
						<form:hidden path="id" />
						<fieldset class="form-group">
						<form:input path="username" required="required" class="form-control" placeholder="Username"/> 
						<form:errors path="username" cssClass="text-error" />							
						</fieldset>
						<p>
					   		<button class="btn btn-lg btn-primary btn-block" type="submit">Save</button>
						</p>
					</form:form>        
				</div>
			</div>
		</div>
</div>
<%@ include file="../common/footer.jspf"%>