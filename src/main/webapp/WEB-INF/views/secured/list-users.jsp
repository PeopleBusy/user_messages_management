<%@ include file="../common/navigation.jspf"%>

<div class="container">
	<h2 class="form-heading text-center">Users list</h2>
	<table class="table table-striped">
				<thead>
					<tr>
						<th>Users</th>
						<th>Creation date</th>
						<th></th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${users}" var="user">
						<tr>
							<td>${user.username}</td>
							<td>${user.createdTs != null ? localDateTimeFormat.format(user.createdTs) : ''}</td>
							<td><a type="button" class="btn btn-sm btn-success"
								href="/secured/update-user?id=${user.id}">View</a></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
</div>
<%@ include file="../common/footer.jspf"%>