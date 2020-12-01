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
      <th class="tables_tit">產品顏色名稱</th>
      <th class="tables_tit">圖片1</th>
      <th class="tables_tit">圖片2</th>
      <th class="tables_tit">圖片3</th> 
      <th class="tables_tit">圖片4</th> 
      <th class="tables_tit">圖片5</th> 
      <th class="tables_tit">產品號碼</th> 
     
      </tr>
        <c:forEach  var="current"  items="${productcolor}" >
        <tr>
          <td class="tables_tit"><c:out value="${current.product_id}" /></td>
          <td class="tables_tit"><c:out value="${current.color_name}" /></td>
          <td class="tables_tit"><c:out value="${current.photo_main_url}" /></td>
          <td class="tables_tit"><c:out value="${current.photo_url1}" /></td>
          <td class="tables_tit"><c:out value="${current.photo_url2}" /></td> 
          <td class="tables_tit"><c:out value="${current.photo_url3}" /></td>
          <td class="tables_tit"><c:out value="${current.photo_url4}" /></td>
          <td class="tables_tit"><c:out value="${current.item_number}" /></td>
    
        </tr>
      </c:forEach>
    </table>
</body>
</html>