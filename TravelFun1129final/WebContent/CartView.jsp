<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page import="java.util.List" %>
<%@ page import="model.CartProduct" %>
<!DOCTYPE html>
<html>
<head>
<title>Cart View</title>
<meta charset="UTF-8">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<link rel="stylesheet" type="text/css" href="travelFun.css">
<style type="text/css">
	html, body {height: 100%;background-color:#eeeeee}
	.wrapper { min-height: 100%}
	.row {display: flex; width:90%; margin: 10px auto;background-color: #fff; border-radius: 3px; padding: 10px;align-items: center}
	.row div {font-family: Microsoft JhengHei; margin: 0px auto;width:200px}
	.color {background-color:#F5DEB3}
	.bold{font-weight:bold}
	.button{font-size:20px;font-weight:bold;font-family: Microsoft JhengHei;border:2px solid #3C3C3C;}
	.button:hover { background-color:#3C3C3C; color: white;}
	.add{font-size:15px;font-weight:bold;font-family:Verdana;color:#0099CC;border:2px solid #0099CC;width:30px;height:25px;text-align:left;margin: 5px}
	.add:hover { background-color:#0099CC; color: white;}
	.minus{font-size:11px;font-weight:bold;font-family:Impact;color:#0099CC;border:2px solid #0099CC;width:30px;height:25px;text-align:center;margin: 5px}
	.minus:hover { background-color:#0099CC; color: white;}
	.trashcan{width:40px;height:41px; background-image:url('images/trash.PNG');border:2px solid #ffffff}
	.trashcan:hover { background-color:#ffffff; color: white;border:2px solid #FF9797}
	.total{font-size:30px;font-weight:bold;font-family: Microsoft JhengHei;color:	#AE0000;}
	.title{text-align:center;width:90%; margin: 10px auto;margin-top:90px;background-color:#3C3C3C;padding: 10px;
   			 font-size:30px;font-weight:bold;font-family: Microsoft JhengHei;color:#ffffff;
   			 border:2px solid #ffffff}
</style>
</head>
<body style="margin: 0">
<script type="text/javascript">			
	window.history.forward(1);
</script>
<%@include file="/WEB-INF/subviews/header.jsp" %>
<div class="title">購物車</div>
<div class="wrapper" id="msg">
		<div class="row color bold">
			<div>商品</div>
			<div>規格</div>
			<div>單價</div>
			<div>數量</div>
			<div>小計</div>
			<div>操作</div>
		</div>
		<c:forEach var="p" items="${sessionScope.productList}">
			<div class="row">				
				<div>															
					<img alt="無法顯示圖片" src="${p.url}" width="100px" height="100px"><br>
					${p.productName}
				</div>			
				<div>
					顏色: ${p.productColor}<br>
					尺寸: ${p.productSize}<br>
					貨號: ${p.itemNumber}
				</div>
				<div>								
					<span>$<fmt:formatNumber type="number" maxFractionDigits="1" value="${p.productPrice}" /><span>		
				</div>
				<div>					 
						<button class="add" onclick="addQty(this.value)"  value='{"productName":"${p.productName}","productColor":"${p.productColor}","productSize":"${p.productSize}"}'>
						+
						</button>
						<span>${p.productQuantity}</span>										
						<button class="minus" onclick="minusQty(this.value)" value='{"productName":"${p.productName}","productColor":"${p.productColor}","productSize":"${p.productSize}"}'>
						—
						</button>								
				</div>
				<div>
					<span>$<fmt:formatNumber type="number" maxFractionDigits="1" value="${p.sum}" /></span>					
				</div>
				<div>
					<button class="trashcan" onclick="del(this.value)" 	
							value='{"productName":"${p.productName}","productColor":"${p.productColor}","productSize":"${p.productSize}"}'>					
					</button>
				</div>
			</div>
		</c:forEach>
		<div class="row color bold">
			<div><button class="button" onClick="location.href='index.jsp'">回到購物首頁</button></div>
			<div></div>
			<div></div>
			<div></div>
			<div class="total">總價: $<fmt:formatNumber type="number" maxFractionDigits="1" value="${sessionScope.totalPrice}" /></div>
			<div><button class="button" onclick="buy(this.value)" value="${sessionScope.totalPrice}">買單趣</button></div>
		</div>
		<script>

		function addQty(value){			
			$.post("CartAjaxServlet",{"addOne":value},show);
			}
		function minusQty(value){			
			$.post("CartAjaxServlet",{"minusOne":value},show);
			}
		function del(value){			
			$.post("CartAjaxServlet",{"productForDelete":value},show);
			}     
		function buy(value){
			var price=parseInt(value);		
			if(price==0){
				alert("總價為0，無法結帳，請確認購物車內容");
				}else{
					document.location.href="./Checkout.jsp"
					}
			}		
		function show(data){			
			$("#msg").html(data);		
			}
	</script>			
</div>
<%@include file="/WEB-INF/subviews/footer.jsp" %>
</body>
</html>