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
      <th class="tables_tit">公司id</th>
      <th class="tables_tit">公司名稱</th>
      <th class="tables_tit">公司地址</th>
      <th class="tables_tit">公司email</th>
      <th class="tables_tit">公司電話</th> 
      <th class="tables_tit">業務名稱</th> 
      <th class="tables_tit">業務手機</th> 
      <th class="tables_tit">業務Email</th> 
           
      </tr>
        <c:forEach  var="current"  items="${supplier}" >
        <tr>
          <td class="tables_tit"><c:out value="${current.id}" /></td>
          <td class="tables_tit"><c:out value="${current.companyname}" /></td>
          <td class="tables_tit"><c:out value="${current.address}" /></td>
          <td class="tables_tit"><c:out value="${current.email}" /></td>
          <td class="tables_tit"><c:out value="${current.phone}" /></td> 
          <td class="tables_tit"><c:out value="${current.salesName}" /></td>
          <td class="tables_tit"><c:out value="${current.salesPhone}" /></td>
          <td class="tables_tit"><c:out value="${current.salesEmail}" /></td>
        </tr>
      </c:forEach>
    </table>
</body>
</html>