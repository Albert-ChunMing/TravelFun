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
	html, body {height: 100%;background-color:#eeeeee;}	
	.wrapper { min-height: 100%}
	.row {display: flex;margin: 10px auto;background-color: #fff; border-radius: 3px; padding: 10px;align-items: center;font-size:22px;}
	.row div {margin: 5px auto;}
	.color {background-color:#F5DEB3}
	.bold{font-weight:bold}
	.button{font-size:15px;font-weight:bold;border:#3C3C3C 2px solid;display: inline-block;}
	.button:hover { background-color:#3C3C3C; color: white;}
	.title{text-align:center;background-color:#3C3C3C;padding: 10px;
   			 font-size:30px;font-weight:bold;color:#ffffff;
   			 border:2px solid #ffffff}
   	#history{margin-top:90px;}
   	.width1{width:50px;text-align: center;}
   	.width2{width:120px;text-align: center;}
   	.width3{width:300px;}
   	.font{font-size:20px;font-weight:bold;}
   	.index{}
   	.index button{width:100%;font-size:30px;}
   	.height{height:auto;min-height:80px;}
@media screen and (max-width:800px) {
	.row {margin: 5px auto;padding: 5px;font-size:15px;}
	.title{padding: 5px; font-size:20px;}
	.button{font-size:10px;}
	.index button{font-size:20px;}
	.font{font-size:15px;}
	 .width1{min-width:50px;}
   	.width2{min-width:80px;}
   	.width3{width:200px;}
		
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
      	SELECT * from tf.order where customerid='${sessionScope.member.id}'
	</sql:query> 
<%@include file="/WEB-INF/subviews/header.jsp" %>
<div class="wrapper">
	<div class="title" id="history">歷史訂單</div>	
		<div class="row color bold">
			<div class="width1">編號</div>
			<div class="width2">時間</div>
			<div class="width3">收件資訊</div>
			<div class="width2">總價</div>
			<div class="width1">狀態</div>
		</div>
		<c:forEach var="row" items="${result.rows}">
			<div class="row height">			
				<div class="width1">${row.Orderid}<button class="button" onclick="query(this.value)" value="${row.Orderid}">明細</button></div>
				<div class="width2">${row.OrderTime}</div>
				<div class="width3">${row.Recipient}<br>${row.Address}</div>
				<div class="width2">$<fmt:formatNumber type="number" maxFractionDigits="1" value="${row.TotalSales}" /></div>
				<div class="width1">${row.StatusCode}</div>	
			</div>				
		</c:forEach>		
		<div class="font">
			<h3>訂單狀態:</h3>
			&nbsp;1: 已付款已出貨&nbsp;2: 已付款未出貨<br>&nbsp;3: 未付款已出貨&nbsp;4: 貨到付款				
		</div>		
	<div class="title" id="title2">訂單明細</div>
	<div id="msg"></div>	
		<br><br>
	<div class="index">
	<button class="button" onClick="location.href='index.jsp'">回到購物首頁</button>
	</div>
		<br>
		
<script>
		function query(value){						
			$.post("QueryOrderDetail.jsp",{"orderId":value},show);			
		}					
		function show(data){			
			$("#msg").html(data);		
		}
		$(document).ready(function(){
			$(".button").click(function(){
				document.getElementById("title2").scrollIntoView();
			});			
		});
</script>
</div>
<%@include file="/WEB-INF/subviews/footer.jsp" %>
</body>
</html>