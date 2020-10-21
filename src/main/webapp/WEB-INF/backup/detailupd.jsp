<%@ page isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<title>Employee details</title>
<link rel="stylesheet" type ="text/css" href="css/bootstrap.css"/>
</head>
<body>

<div class="container">
	<h3>Employee</h3>
	<hr>

	${employee.firstName} ${employee.lastName} ${employee.team.name}
	<br>
	<table>
		<form:form action="updatedet?empid=${id}" modelAttribute="detail">
			<form:hidden path="id" />
			<tr>
				<th>Email</th>
				<th>Location</th>
				<th>Action</th>
			</tr>
			<tr>
				<td><form:input path="email" value="${detail.email}" /></td>
				<td><form:input path="address" value="${detail.address}" /></td>
				<td><input type="submit" value="Update"></td>
			</tr>
		</form:form>
	</table>
	<c:url var="detailupd" value="/detailupd">
		<c:param name="detailid" value="${detail.id}" />
	</c:url>
</div>
</body>
</html>