<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page import="java.util.List, java.net.*, java.io.*" %>
<%@ page import="model.CartProduct" %>
<%@ page import="tf.entity.Customer" %>
<%
	String s_stName = request.getParameter("stName")==null? "":request.getParameter("stName");
    String s_stAddr = request.getParameter("stAddr")==null? "":request.getParameter("stAddr");
    String s_webPara = request.getParameter("webPara")==null?"":request.getParameter("webPara");
  //中文轉碼 將iso8859_1轉為UTF-8
    s_stName = new String(s_stName.getBytes("ISO8859_1"),"UTF-8");
    s_stAddr = new String(s_stAddr.getBytes("ISO8859_1"),"UTF-8");
    
    ServletContext context=session.getServletContext();
    if(s_webPara.equals("")){ 
    	context.setAttribute(session.getId(), session);
    	System.out.println("將目前session存入ServletContext中的Attribute");
    }
    if(!s_webPara.equals("")){
    	System.out.println("ezship產生的session: "+session.getId());
    	System.out.println(session.isNew());
    	//轉移attribute到新的session上
    	session.setAttribute("productList", ((HttpSession)context.getAttribute(s_webPara)).getAttribute("productList"));
    	session.setAttribute("totalPrice", ((HttpSession)context.getAttribute(s_webPara)).getAttribute("totalPrice"));
    	session.setAttribute("member", ((HttpSession)context.getAttribute(s_webPara)).getAttribute("member"));
    	System.out.println("舊session的attribute成功轉移到新session的attribute");
    	//移除ServletContext中的Attribute
    	context.removeAttribute(s_webPara);
    	System.out.println("刪除ServletContext中的Attribute(Checkout.jsp)");
    }
    
    System.out.println(session.getAttribute("productList"));
    System.out.println(session.getAttribute("totalPrice"));
    System.out.println(session.getAttribute("member"));
    List<CartProduct> productList=(List<CartProduct>)session.getAttribute("productList");
    Integer totalPrice=(Integer)session.getAttribute("totalPrice");
    Customer customer=(Customer)session.getAttribute("member");
%> 
<!DOCTYPE html>
<html>
<head>
<title>Cart View</title>
<meta charset="UTF-8">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<style type="text/css">
	html, body {height: 100%;background-color:#eeeeee}
	header {background-color:#00CACA; color:#FFFFFF;text-align:center;padding:10px;font-size:40px;font-weight:bold;font-family: Microsoft JhengHei}	
	footer {background-color:#00CACA; color:#FFFFFF;text-align:center;padding:10px;font-size:15px;font-weight:bold;font-family: Microsoft JhengHei;}	
	.wrapper { min-height: 100%}
	.row {display: flex; width:80%; margin: 10px auto;background-color: #fff; border-radius: 3px; padding: 10px;align-items: center}
	.row div {font-family: Microsoft JhengHei; margin: 0px auto;width:200px}
	.color {background-color:#F5DEB3}
	.bold{font-weight:bold}
	.button{font-size:20px;font-weight:bold;font-family: Microsoft JhengHei;border:2px solid #3C3C3C;}
	.button:hover { background-color:#3C3C3C; color: white;}
	.add{font-size:15px;font-weight:bold;font-family:Verdana;color:#0099CC;border:2px solid #0099CC;width:30px;height:25px;text-align:left;margin: 5px}
	.minus{font-size:11px;font-weight:bold;font-family:Impact;color:#0099CC;border:2px solid #0099CC;width:30px;height:25px;text-align:center;margin: 5px}
	.trashcan{width:38px;height:37px; background-image:url('images/trash.PNG');border:0px}
	.total{font-size:30px;font-weight:bold;font-family: Microsoft JhengHei;color:	#AE0000;}
    input{font-size: 20px;margin:5px}
    .check{position:relative;left:9%;font-size:20px;font-family: Microsoft JhengHei;}
    @keyframes bounce{from{transform:translateY(0px)}to{transform:translateY(-10px)}}
	@-webkit-keyframes bounce{from{transform:translateY(0px)}to{transform:translateY(-10px)}}
    .pay{width:82%;background-color:#F5DEB3;font-size:40px;font-weight:bold;font-family: Microsoft JhengHei;color:#AE0000;
    		border:2px solid #AE0000;padding: 10px;animation: bounce 1s infinite alternate;-webkit-animation: bounce 1s infinite alternate;}
    .pay:hover { background-color:#AE0000; color: white;}
    .title{text-align:center;width:80%; margin: 10px auto;background-color:#3C3C3C;padding: 10px;
   			 font-size:30px;font-weight:bold;font-family: Microsoft JhengHei;color:#ffffff;
   			 border:2px solid #ffffff}
</style>
</head>
<meta http-equiv="Content-Type" content="text/html;charset=utf-8">
<body style="margin: 0">
<script type="text/javascript">			
	window.history.forward(1);
</script>
<header>
		<div>旅遊趣線上購物</div>
</header>
<div class="wrapper">
<div class="title">訂單明細</div>
		<div class="row color bold">
			<div>商品</div>
			<div>規格</div>
			<div>單價</div>
			<div>數量</div>
			<div>小計</div>			
		</div>
		<%
			for(CartProduct p: productList){
		%>
			<div class="row">				
				<div><img alt="無法顯示圖片" src="<%=p.getUrl() %>" width="100px" height="100px"><br><%=p.getProductName()%></div>			
				<div>顏色: <%=p.getProductColor()%><br>尺寸: <%=p.getProductSize() %><br>貨號: <%=p.getItemNumber() %></div>
				<div>$<fmt:formatNumber type="number" maxFractionDigits="1" value="<%=p.getProductPrice() %>" /></div>
				<div><%=p.getProductQuantity() %>	</div>
				<div>$<fmt:formatNumber type="number" maxFractionDigits="1" value="<%=p.getSum() %>" /></div>				
			</div>
		<%}%>		
		<div class="row color bold">
			<div><button class="button" onClick="location.href='CartView.jsp'">回到購物車</button></div>
			<div></div>
			<div></div>
			<div></div>
			<div class="total">總價: $<fmt:formatNumber type="number" maxFractionDigits="1" value="<%=totalPrice%>" /></div>			
		</div>
<div class="title">付款與配送方式</div>
	<div class="check">
		<form action="AioCheckServlet" method="post">		
				<h3>收件人: </h3>
            		&nbsp;姓名：<input type="text" name="recipient" value="<%=customer.getName() %>" required>(必填)<br>
            		&nbsp;電話：<input type="text" name="phone" value="<%=customer.getPhone() %>" required>(必填)<br>
            		<%
            			String address="";
            			if(s_stName.equals("")) address=customer.getAddress();
            			if(!s_stName.equals("")) address=s_stName+s_stAddr;	
            		%>
            		&nbsp;地址：<input type="text" name="address" value="<%=address%>" required style="width: 600px" >(必填)
            		<input type="hidden" name="webPara" value="<%=s_webPara%>">
        		<br> 
				<h3>付款方式：</h3>
					<input type="radio" name="cashMethod" value="credit" checked>信用卡/ATM/超商條碼<br><br>
					<input type="radio" name="cashMethod" value="cod">貨到付款		
				<br>
				<h3>配送方式：</h3>					
					
					<input type="radio" name="delivery" value="store" checked>超商取貨 (自動帶入地址)<br><br>				
    				&nbsp;&nbsp;&nbsp;&nbsp;<input class="button" type="button" value="選擇超商門市" onclick="goEZship()"></input><br><br>
    				<input type="radio" name="delivery" value="home" >宅配到府 (請自行輸入地址)
    				<br><br><br><br><br>
    				<button class="pay">結帳</button>
    			<br><br>  			 
    	</form>
    	<!-- 將資料填入隱藏表  傳給ezShip以開啟電子地圖-->
    	<form action="https://map.ezship.com.tw/ezship_map_web.jsp" method="post" id="ezForm">		 
			<input type="hidden" name="suID" value="buyer@myweb.com.tw">
			<!-- 業主在 ezShip 使用的帳號, 這裡可以隨便寫 -->
			<input type="hidden" name="processID" value="3345678">
			<!-- 購物網站自行產生之訂單編號, 這裡可以隨便寫 -->
			<input type="hidden" name="stCate" value="">
			<!-- 取件門市通路代號 可為空值 若為空值 自動跳轉地圖選擇 -->
			<input type="hidden" name="stCode" value="">
			<!-- 取件門市代號 可為空值 若為空值 自動跳轉地圖選擇-->
			<input type="hidden" name="rtURL" id="rtURL" value="">
			<!-- 回傳路徑及程式名稱 -->
			<input type="hidden" id="webPara" name="webPara" value="">
			<!-- 我們網站所需的原Form Data。ezShip會將原值回傳，供我們網站帶回畫面用 -->
		</form>    	    	   		
	</div>				
</div>
<script>
		function goEZship() {
    	 //指定ezShip回傳資料的位址(本地url 配合自己專案名稱)
     	 var protocol = "<%=request.getScheme()%>";		
		<%URL whatismyip = new URL("http://checkip.amazonaws.com");
			BufferedReader in = new BufferedReader(new InputStreamReader(whatismyip.openStream()));%>
		 var ipAddress = "<%=in.readLine()%>";
		 <%in.close();%>
	     var url = protocol + "://" + ipAddress + ":" + location.port + "<%=request.getContextPath()%>/Checkout.jsp";	
		$("#rtURL").val(url);
		//設定要傳到ezShip的資料  之後再由ezShip原封不動帶回
		<%System.out.println("session ID: "+session.getId());%>		
		$("#webPara").val("<%=session.getId()%>");
		
		//提交表單			
	    $("#ezForm").submit();	   
}
			
</script>

<footer>
		<div>旅遊趣有限公司版權所有2020</div>
</footer>	
</body>
</html>