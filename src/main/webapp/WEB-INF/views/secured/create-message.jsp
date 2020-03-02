<%@ include file="../common/navigation.jspf"%>

<div class="container">
	<div class="main-content">
		<div class="col-md-3"></div>
		<div class="col-md-6">
			<h2 class="form-heading text-center">${title}</h2>
			<div class="form-group ">
					<p class="text-center txt-success">${msg}</p>					
					<form:form method="post" modelAttribute="message">
						<fieldset class="form-group">
						<form:textarea path="textMessage" required ="required" rows="5"  class="form-control" placeholder="Type message here.."></form:textarea> 
						<form:errors path="textMessage" cssClass="text-error" />							
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