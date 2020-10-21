<%@ page isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
	
	${employee.firstName} ${employee.lastName} ${employee.team.name}<br> 
	<table>
		<tr>
			<th>Email</th>
			<th>Location</th>
			<th>Action</th>
		</tr>
		<tr>
			<c:url var="detailupd" value="/detailupd">
				<c:param name="detailid" value="${detail.id}"/>
			</c:url>
			<td>${detail.email}</td>
			<td>${detail.address}</td>
			<td><a href="${detailupd}">Update details</a></td>
			
		</tr>
	</table>
</div>

</body>
</html>