<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<table border="1" width="70%">
      <tr>
      <th>產品id</th>
      <th>產品名稱</th>
      <th>產品顏色</th>
      <th>產品號碼</th>
      <th>產品庫存</th> 
      </tr>
        <c:forEach  var="current"  items="${Object}" >
        <tr>
          <td><c:out value="${current.id}" /></td>
          <td><c:out value="${current.name}" /></td>
          <td><c:out value="${current.namecolor}" /></td>
          <td><c:out value="${current.namenumber}" /></td>
          <td><c:out value="${current.stock}" /></td> 
        </tr>
      </c:forEach>
    </table>
</body>
</html>