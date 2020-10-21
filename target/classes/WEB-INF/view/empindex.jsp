<%@ page isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<title>Employee/Team list</title>
<link rel="stylesheet" type="text/css" href="css/bootstrap.css" />
<!--  <link rel="stylesheet" type="text/css" href="css/default.css" /> -->
</head>
<body>

	<div class="container text-center mx-auto">
		<h3 class="text-center">Employees</h3>
		<table class="table">
		<thead class="thead-light">
			<tr>
				<th>#</th>
				<th>First name</th>
				<th>Last name</th>
				<th>Team</th>
				<th></th>
			</tr>
		</thead>
		
			<c:forEach var="emp" items="${employees}">
				<tr class="border-dark">
					<td>${emp.id}</td>
					<td>${emp.firstName}</td>
					<td>${emp.lastName}</td>
					<td>${emp.team.name}</td>
					<c:url var="empdetail" value="/empdetail">
						<c:param name="empid" value="${emp.id}" />
					</c:url>
					<c:url var="empedit" value="/empedit">
						<c:param name="empid" value="${emp.id}" />
					</c:url>
					<c:url var="empremove" value="/empremove">
						<c:param name="empid" value="${emp.id}" />
					</c:url>
					<td><a href="${empdetail}" class="btn btn-info">Details</a> 
					<a href="${empedit}" class="btn btn-dark">Update</a> 
					<a href="${empremove}" class="btn btn-warning">Remove</a></td>
				</tr>
			</c:forEach>
		</table>
		<br> <a
			href="${pageContext.request.contextPath}/addemployee" class="btn btn-secondary">New
			employee</a> <br> <br>
		<hr>
		<br>
		<br>
		<h3>Teams</h3>
		<table class="table">
		<thead class="thead-light">
			<tr>
				<th>#</th>
				<th>Name</th>
				<th>Description</th>
				<th></th>
			</tr>
		</thead>
			<c:forEach var="team" items="${teams}">
				<c:url var="teamedit" value="/teamedit">
					<c:param name="teamid" value="${team.id}" />
				</c:url>

				<tr>
					<td>${team.id}</td>
					<td>${team.name}</td>
					<td>${team.description}</td>
					<td><a href="${teamedit}" class="btn btn-dark">Update</a></td>
				</tr>
			</c:forEach>
		</table>
		<br> <a href="${pageContext.request.contextPath}/teamcreate" class="btn btn-secondary">New
			team</a>
	</div>

</body>
</html>


