<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1" >
<title></title>
<link rel="stylesheet" type="text/css" href="style/travelFun.css">


</head>

<body>
<script src="http://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>

<table border="1" width="100%">
<tr>
	<th>ID</th>
   <th>Name</th>
   <th>Password</th>
   <th>Gender</th>
   <th>Email</th>
   <th>Birthday</th>
   <th>Phone</th>
   <th>Address</th>
   <h3>修改資料如下</h3>
</tr>
<c:forEach var="row" items="${member}">
<tr>
<td><c:out value="${row.id}"/></td>
   <td><c:out value="${row.name}"/></td>
   <td><c:out value="${row.password}"/></td>
   <td><c:out value="${row.gender}"/></td>
   <td><c:out value="${row.email}"/></td>
   <td><c:out value="${row.birthday}"/></td>
   <td><c:out value="${row.phone}"/></td>
   <td><c:out value="${row.address}"/></td>
</tr>
</c:forEach>
</table>

</body>
</html>