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
<meta name="viewport" content="width=device-width, initial-scale=1" >
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<link rel="stylesheet" type="text/css" href="travelFun.css">
<style type="text/css">
	html, body {height: 100%;background-color:#eeeeee}
	.wrapper { min-height: 100%}
	.row {display: flex; margin: 10px auto;background-color: #fff;padding: 10px;align-items: center}
	.row div {font-family: Microsoft JhengHei; margin: 0px auto;}
	.color {background-color:#F5DEB3}
	.bold{font-weight:bold}
	.button{font-size:20px;font-weight:bold;font-family: Microsoft JhengHei;border:2px solid #3C3C3C;}
	.button:hover { background-color:#3C3C3C; color: white;}
	.changeQuantity{font-size:15px;font-weight:bold;font-family:Verdana;color:#0099CC;border:2px solid #0099CC;width:28px;height:28px;text-align:left;margin: 5px}
	.changeQuantity:hover { background-color:#0099CC; color: white;}
	.trashcan{border: #FF9797 2px solid; font-size: 17px; color: #FF9797; background-color: white; font-family: unset; font-weight: bold;margin-left:5px;}
	.trashcan:hover { background-color:#FF9797; color: white;}
	.total{font-size:30px;font-weight:bold;font-family: Microsoft JhengHei;color:#AE0000;}
	.title{text-align:center; margin: 10px auto;margin-top:90px;background-color:#3C3C3C;padding: 10px;
   			 font-size:30px;font-weight:bold;font-family: Microsoft JhengHei;color:#ffffff;
   			 border:2px solid #ffffff}
   	.width1{width:30px}
   	.width2{width:120px;text-align:center;display:flex;align-items:center;}
   	.width3{width:200px;text-align:center;}
   	.row img{width:200px;height:200px;}
@media screen and (max-width:800px) {
	.row {font-size:15px;}
	.button{font-size:15px;min-width:50px;}
	.total{font-size:17px;min-width:120px;}
	.width1{min-width:25px}
   	.width2{min-width:50px;display:block;}
   	.width3{min-width:85px}
   	.trashcan{font-size: 12px;margin-left:1px;}
   	.changeQuantity{font-size:12px;width:25px;height:24px;margin: 2px}
   	.row img{width:80px;height:80px;}
}
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
			<div class="width3">商品</div>
			<div class="width3">規格</div>
			<div class="width2">單價</div>
			<div class="width2">數量</div>
			<div class="width3">小計</div>
		</div>
		<c:forEach var="p" items="${sessionScope.productList}">
			<div class="row">				
				<div class="width3">															
					<img alt="無法顯示圖片" src="${p.url}" width="100px" height="100px"><br>
					${p.productName}
				</div>			
				<div class="width3">
					${p.productColor}<br>
					${p.productSize}
				</div>
				<div class="width2">								
					<span>$<fmt:formatNumber type="number" maxFractionDigits="1" value="${p.productPrice}" /><span>		
				</div>
				<div class="width2">
						<button class="changeQuantity" onclick="addQty(this.value)"  value='{"productName":"${p.productName}","productColor":"${p.productColor}","productSize":"${p.productSize}"}'>
						+
						</button>
						<div>	${p.productQuantity}</div>										
						<button class="changeQuantity" onclick="minusQty(this.value)" value='{"productName":"${p.productName}","productColor":"${p.productColor}","productSize":"${p.productSize}"}'>
						—
						</button>						
				</div>
				<div class="width3">
					<span>$<fmt:formatNumber type="number" maxFractionDigits="1" value="${p.sum}" /></span>	
					<button class="trashcan" onclick="del(this.value)" 	
											value='{"productName":"${p.productName}","productColor":"${p.productColor}","productSize":"${p.productSize}"}'>X				
					</button>				
				</div>
			</div>
		</c:forEach>
		<div class="row color bold">
			<div><button class="button" onClick="location.href='index.jsp'">繼續逛</button></div>
			<div class="total">共計: $<fmt:formatNumber type="number" maxFractionDigits="1" value="${sessionScope.totalPrice}" /></div>
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