<%@ page isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<title>Employee details</title>
<link rel="stylesheet" type="text/css" href="css/bootstrap.css" />
</head>
<body>

	<div class="container text-center mx-auto">
		<h3>Name: ${employee.firstName} ${employee.lastName} - Team:
			${employee.team.name}</h3>
		<hr>

		<br>
		<table class="table">
			<thead class="thead-light">
				<tr>
					<th>Email</th>
					<th>Location</th>
					<th></th>
				</tr>
			</thead>
			<tr>
				<c:url var="detailupd" value="/detailupd">
					<c:param name="detailid" value="${detail.id}" />
				</c:url>
				<td>${detail.email}</td>
				<td>${detail.address}</td>
				<td><a href="${detailupd}" class="btn btn-dark">Update
						details</a></td>

			</tr>
		</table>
	</div>

</body>
</html>