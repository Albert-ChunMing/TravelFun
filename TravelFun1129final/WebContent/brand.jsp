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

<style type="text/css">


.content2 {
	margin: 160px 0px 20px 0px;
	text-align: center;
}

.content2 img {
	max-width: 95%;
	height: auto;
}

.content3 {
	width: 100px;
	height: 100px;
	margin: 0px 5px 5px 120px;
	border: 2px solid grey;
	display: inline-table
}

.content4 {
	width: 100%;
	height: 80%;
	margin: 0.5px;
	/*  background:#00aac5; */
	background: #f3f3f0;
	background-position: center;
	clear: both
}

.productItem {
	margin: 0px 5px 120px 120px;
	display: inline-table
}

.productItem img {
	width: 300px;
	height: 300px;
	border: 2px solid grey;
}
</style>
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