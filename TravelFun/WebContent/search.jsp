<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>產品搜尋</title>
<link rel="stylesheet" type="text/css" href="travelFun.css">
<script src='js/jquery.js'></script>	 
	<sql:setDataSource var="tf" driver="com.mysql.cj.jdbc.Driver"
		url="jdbc:mysql://localhost:3306/tf?useUnicode=true&characterEncoding=UTF-8&serverTimezone=CST" 
		user="root"
		password="1234" />
	<sql:query dataSource="${tf}" var="result">
		SELECT * FROM products join product_colors on products.id=product_colors.product_id where name like '%${param.search}%' or color_name like '%${param.search}%';
	</sql:query>
<style>
.content4p{
    background:#f3f3f0;	
    padding-left: 2.5%;
    padding-right: 2.5%;
    margin-top:90px;
}
.content4{	
	display: flex;
    flex-wrap: wrap;
    justify-content: flex-start;
    max-width: 1376px;
    margin-left: auto;
    margin-right: auto;
}	
.productItem{
	margin:20px;
	   width:304px;
}
.productItem img{
	max-width:300px;
	max-height:300px;
	border: 2px solid grey;
}
.content4p p{
	font-size:30px;
	font-weight:bold;
	text-align: center;
    padding-top: 10px;	
}
@media screen and (max-width:800px) {
		.productItem img{	max-width:240px;	max-height:240px;}
		.productItem{width:244px;}	
		.content4{justify-content: center;font-size:20px;}	
		h4{margin:10px 0px 10px 0px;}
		.content4p p{font-size:24px;}
}
</style>
</head>
<body>
<%@include file="/WEB-INF/subviews/header.jsp" %>
	<div class='content4p' >
		<p>${param.search} 的搜尋結果如下...</p>
		<div class='content4'><br>		
				<c:forEach items="${result.rows}" var="item">
					<div class='productItem'>
					<a href="product.jsp?productId=${item.id}&photo=${item.photo_main_url}&color=${item.color_name}"> <img src="${item.photo_main_url}" /></a>
					<h4><a href="product.jsp?productId=${item.id}&photo=${item.photo_main_url}&color=${item.color_name}"> ${item.name }(${item.color_name})</a></h4>
					<div>會員價:${item.discount}折，<fmt:formatNumber type="number" maxFractionDigits="1" value="${item.unit_price*item.discount/10}" />元</div>
					</div>
				</c:forEach>
		</div>
	</div>
</body>
</html>