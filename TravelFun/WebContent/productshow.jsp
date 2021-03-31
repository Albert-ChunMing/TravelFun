<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<table border="1" class="tables">
      <tr>
      <th class="tables_tit">產品id</th>
      <th class="tables_tit">產品名稱</th>
      <th class="tables_tit">產品售價</th>
      <th class="tables_tit">產品描述</th>
      <th class="tables_tit">產品狀態</th> 
      <th class="tables_tit">產品折扣</th> 
      <th class="tables_tit">產品類別</th> 
      <th class="tables_tit">產品品牌</th> 
      <th class="tables_tit">廠商id</th> 
      </tr>
        <c:forEach  var="current"  items="${product}" >
        <tr>
          <td class="tables_tit"><c:out value="${current.id}" /></td>
          <td class="tables_tit"><c:out value="${current.name}" /></td>
          <td class="tables_tit"><c:out value="${current.unit_price}" /></td>
          <td class="tables_tit"><c:out value="${current.description}" /></td>
          <td class="tables_tit"><c:out value="${current.class_name}" /></td> 
          <td class="tables_tit"><c:out value="${current.discount}" /></td>
          <td class="tables_tit"><c:out value="${current.category}" /></td>
          <td class="tables_tit"><c:out value="${current.brand}" /></td>
          <td class="tables_tit"><c:out value="${current.prosupplier}" /></td>
        </tr>
      </c:forEach>
    </table>
</body>
</html>