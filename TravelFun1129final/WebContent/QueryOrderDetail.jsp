<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<title>Query Order</title>
<style type="text/css">
	html, body {height: 100%;background-color:#eeeeee}	
	.wrapper { min-height: 100%}
	.row {display: flex; width:90%; margin: 10px auto;background-color: #fff; border-radius: 3px; padding: 10px;align-items: center}
	.row div {font-family: Microsoft JhengHei; margin: 0px auto;}
	.color {background-color:#F5DEB3}
	.bold{font-weight:bold}
   	.width1{width:100px}
   	.width2{width:200px}
   	.width3{width:250px}
   	.height{height:80px}
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
			<div class="width1">訂單編號</div>
			<div class="width3">產品名稱</div>
			<div class="width2">顏色</div>
			<div class="width1">尺寸</div>
			<div class="width1">單價</div>
			<div class="width1">訂購數量</div>
			<div class="width1">小計</div>			
		</div>
		<c:forEach var="row" items="${result.rows}">
			<div class="row height">			
				<div class="width1">${row.OrderId}</div>
				<div class="width3">${row.ProductName}</div>
				<div class="width2">${row.Color}</div>
				<div class="width1">${row.Size}</div>
				<div class="width1">$<fmt:formatNumber type="number" maxFractionDigits="1" value="${row.Price}" /></div>
				<div class="width1">${row.Quantity}</div>
				<div class="width1">$<fmt:formatNumber type="number" maxFractionDigits="1" value="${row.Sales}" /></div>				
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