<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>品牌分類</title>
	<sql:setDataSource var="tf" driver="com.mysql.cj.jdbc.Driver"
		url="jdbc:mysql://localhost:3306/tf?useUnicode=true&characterEncoding=UTF-8&serverTimezone=CST" 
		user="root"
		password="1234" />
	<sql:query dataSource="${tf}" var="result">
      	SELECT * FROM products join product_colors on product_colors.product_id=products.id where brand='${param.brand}'
	</sql:query>
</head>
<body>	
		<div class='content4'>
			產品列表 <br>
			<ul>
				<c:forEach items="${result.rows}" var="item">
					<li class='productItem'>
					<a href="product.jsp?productId=${item.id}&photo=${item.photo_main_url}&color=${item.color_name}"> <img src="${item.photo_main_url}" /></a>
					<h4><a href="product.jsp?productId=${item.id}&photo=${item.photo_main_url}&color=${item.color_name}"> ${item.name }(${item.color_name})</a></h4>
					
					<div>會員價:${item.discount}折，<fmt:formatNumber type="number" maxFractionDigits="1" value="${item.unit_price*item.discount/10}" />元</div>
					</li>
				</c:forEach>
			</ul>
			<p>Oops! No More Product</p>
		</div>
</body>
</html>