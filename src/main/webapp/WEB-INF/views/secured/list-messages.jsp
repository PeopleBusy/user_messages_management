<%@ include file="../common/navigation.jspf"%>

<div class="container">
	<h2 class="form-heading text-center">${title}</h2>
	<table class="table table-striped">
				<thead>
					<tr>
						<th>Message</th>
						<th>Created By</th>
						<th>Creation date</th>
						<th>Modified By</th>
						<th>Modification date</th>
						<th></th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${messages}" var="message">
						<tr>
							<td>${message.textMessage}</td>
							<td>${message.createdBy.username}</td>
							<td>${localDateTimeFormat.format(message.createdTs)}</td>
							<td>${message.modifiedBy.username}</td>
							<td>${message.modifiedTs != null ? localDateTimeFormat.format(message.modifiedTs) : ''}</td>
							<td><a type="button" class="btn btn-sm btn-success"
								href="/secured/update-message?id=${message.id}">Update</a>
							<a type="button" class="btn btn-sm btn-warning"
								href="/secured/show-message?id=${message.id}">View</a></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
</div>
<%@ include file="../common/footer.jspf"%>