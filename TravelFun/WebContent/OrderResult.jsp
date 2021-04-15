<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>刷卡結果</title>
<link rel="stylesheet" type="text/css" href="travelFun.css">
<style>
   .title{text-align:center; margin: 90px 0px 10px 0px;background-color:#3C3C3C;padding: 10px;
   			 font-size:30px;font-weight:bold;font-family: Microsoft JhengHei;color:#ffffff;
   			 border:2px solid #ffffff}
	.center{border: #868686 2px solid;border-radius: 15px; padding: 20px;margin-left: 10px; margin-right: 10px;}
	.center div{width:250px;margin: 5px auto 5px auto;font-size: 20px;}
	span{color:chocolate;}
@media screen and (max-width: 800px){
	.title{font-size: 20px;}
	.center div{width:200px;font-size: 15px;}
}	
</style>
</head>
<body>
<%@include file="/WEB-INF/subviews/header.jsp" %>
<div class="title">刷卡結果</div>
<div class="center">
	<div>刷卡狀態: <span>${requestScope.RtnMsg}</span></div>
	<div>刷卡金額: <span><fmt:formatNumber type="number" maxFractionDigits="1" value="${requestScope.TradeAmt}" /></span> 元</div>
	<div>訂單編號: <span>${requestScope.MerchantTradeNo}</span></div>
</div>

</body>
</html>