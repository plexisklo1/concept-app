<%@ page isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<title>Employee editing</title>
<link rel="stylesheet" type="text/css" href="css/bootstrap.css" />
</head>
<body>
	<br>
	<div class="container text-center mx-auto">
		<h3>${employee.firstName} ${employee.lastName}</h3>
		<hr>

		<table class="table">
			<form:form action="empupdate?detailid=${detailid}"
				modelAttribute="employee">
				<thead class="thead-light">
					<tr>
						<th>First name</th>
						<th>Last name</th>
						<th>Team</th>
						<th></th>
					</tr>
				</thead>
				<tr>
					<td><form:input path="firstName" value="${employee.firstName}" /></td>
					<td><form:input path="lastName" value="${employee.lastName}" />
					<td><select name="teamid">
							<c:forEach var="teamsid" items="${teams}">
								<option value="${teamsid.id}"
									${teamsid.id==id ? "selected" : ""}>${teamsid.name}</option>
							</c:forEach>
					</select></td>
					<td><input type="submit" value="Send" class="btn btn-dark" />
				</tr>
				<tr>
					<td><form:errors path="firstName"/></td>
					<td><form:errors path="lastName"/></td>
				</tr>
				<form:hidden path="id" value="${employee.id}" />

			</form:form>
		</table>

	</div>
</body>
</html>