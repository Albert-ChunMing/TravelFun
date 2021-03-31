<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<title>Query Order</title>
<link rel="stylesheet" type="text/css" href="travelFun.css">
<style type="text/css">
	html, body {height: 100%;background-color:#eeeeee}	
	.wrapper { min-height: 100%}
	.row {display: flex;margin: 10px auto;background-color: #fff; border-radius: 3px; padding: 10px;align-items: center;font-size:22px;}
	.row div {margin: 5px auto;}
	.color {background-color:#F5DEB3}
	.bold{font-weight:bold}
   	.w1{width:50px;text-align: center;}
   	.w2{width:120px;text-align: center;}
   	.w3{width:300px;}
   	.height{height:auto;min-height:80px;}
@media screen and (max-width:800px) {
	.row {margin: 5px auto;padding: 5px;font-size:15px;}
	.w1{min-width:40px;}
   	.w2{min-width:80px;}
   	.w3{width:250px;}
}
</style>
</head>
<body style="margin: 0">
	<!-- 建立連線 -->
	<sql:setDataSource var="tf" driver="com.mysql.cj.jdbc.Driver"
     url="jdbc:mysql://localhost:3306/tf?useUnicode=true&characterEncoding=UTF-8&serverTimezone=CST"
     user="root"  password="1234"/> 
	<!-- 查詢資料庫使用sql:query 並以變數名稱result代表查詢後的資料-->
	<sql:query dataSource="${tf}" var="result">
      	SELECT * from tf.orderdetail where OrderId=${param.orderId}
	</sql:query> 

<div class="wrapper">
		<div class="row color bold">
			<div class="w3">品名</div>
			<div class="w2">顏色</div>
			<div class="w1">尺寸</div>
			<div class="w2">單價</div>
			<div class="w1">數量</div>	
		</div>
		<c:forEach var="row" items="${result.rows}">
			<div class="row height">			
				<div class="w3">${row.ProductName}</div>
				<div class="w2">${row.Color}</div>
				<div class="w1">${row.Size}</div>
				<div class="w2">$<fmt:formatNumber type="number" maxFractionDigits="1" value="${row.Price}" /></div>
				<div class="w1">${row.Quantity}</div>				
			</div>				
		</c:forEach>		

		
<script>
		function query(value){						
			$.post("QueryOrderDetail.jsp",{"orderId":value},show);			
		}					
		function show(data){			
			$("#msg").html(data);		
		}
</script>
</div>
	
</body>
</html>