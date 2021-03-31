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
<SELECT id="item" >	
<c:forEach items="${Object}" var="reple">
 <option>${reple.id},${reple.name},${reple.namecolor},${reple.namenumber},${reple.stock}</option>
 
 </c:forEach> 
 </SELECT>      
</body>
</html>