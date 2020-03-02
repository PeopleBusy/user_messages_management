<%@ include file="../common/navigation.jspf"%>

<div class="container">
	<div class="main-content">
		<div class="col-md-3"></div>
		<div class="col-md-6">
			<h2 class="form-heading text-center">${title}</h2>
			<div class="form-group ">			
				<textarea required ="required" rows="5"  class="form-control" placeholder="Type message here.." disabled="disabled">${message.textMessage}</textarea>	
        
			</div>
			<p>
				<a class="btn btn-lg btn-danger btn-block" href="/secured/delete-message?id=${message.id}" type="submit">Delete</a>
			</p>
		</div>
	</div>
</div>
<%@ include file="../common/footer.jspf"%>