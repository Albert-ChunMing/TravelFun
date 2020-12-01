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
	header {background-color:#00CACA; color:#FFFFFF;text-align:center;padding:10px;font-size:40px;font-weight:bold;font-family: Microsoft JhengHei}	
	footer {background-color:#00CACA; color:#FFFFFF;text-align:center;padding:10px;font-size:15px;font-weight:bold;font-family: Microsoft JhengHei;}	
	.wrapper { min-height: 100%}
	.row {display: flex; width:90%;margin: 10px auto;background-color: #fff; border-radius: 3px; padding: 10px;align-items: center}
	.row div {font-family: Microsoft JhengHei; margin: 0px auto;}
	.color {background-color:#F5DEB3}
	.bold{font-weight:bold}
	.button{font-size:15px;font-weight:bold;font-family: Microsoft JhengHei;border:2px solid;display: inline-block;}
	.button:hover { background-color:#3C3C3C; color: white;}
	.title{text-align:center;width:90%; margin: 10px auto;background-color:#3C3C3C;padding: 10px;
   			 font-size:30px;font-weight:bold;font-family: Microsoft JhengHei;color:#ffffff;
   			 border:2px solid #ffffff}
   	.width1{width:100px}
   	.width2{width:200px}
   	.width3{width:250px}
   	.font{font-size:20px;font-weight:bold;font-family: Microsoft JhengHei;position:relative;left:4.5%}
   	.index{align-items: center;text-align:center;display: flex; margin: 10px auto;width:90%;}
   	.index button{width:100%;font-size:30px;}
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
      	SELECT * from tf.order where customerid='${sessionScope.member.id}'
	</sql:query> 
<header>
		<div>旅遊趣線上購物</div>
</header>
<div class="wrapper">
	<div class="title">歷史訂單</div>	
		<div class="row color bold">
			<div class="width2">訂單編號</div>
			<div class="width3">成立時間</div>
			<div class="width2">收件人</div>
			<div class="width2">收件人電話</div>
			<div class="width3">收件地址</div>
			<div class="width1">總價</div>
			<div class="width1">訂單狀態</div>
		</div>
		<c:forEach var="row" items="${result.rows}">
			<div class="row height">			
				<div class="width2">${row.Orderid}&nbsp;&nbsp;<button class="button" onclick="query(this.value)" value="${row.Orderid}">查詢明細</button></div>
				<div class="width3">${row.OrderTime}</div>
				<div class="width2">${row.Recipient}</div>
				<div class="width2">${row.Phone}</div>
				<div class="width3">${row.Address}</div>
				<div class="width1">$<fmt:formatNumber type="number" maxFractionDigits="1" value="${row.TotalSales}" /></div>
				<div class="width1">${row.StatusCode}</div>	
			</div>				
		</c:forEach>		
		<div class="font">
			<h3>訂單狀態:</h3>
			&nbsp;&nbsp;1: 已付款已出貨&nbsp;&nbsp;2: 已付款未出貨&nbsp;&nbsp;3: 未付款已出貨&nbsp;&nbsp;4: 未付款未出貨						
		</div><br>
		
	<div class="title">訂單明細</div>
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
</script>
</div>
<footer>
		<div>旅遊趣有限公司版權所有2020</div>
</footer>	
</body>
</html>