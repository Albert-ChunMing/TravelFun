<%@page import="tf.service.ProductService"%>
<%@page import="tf.entity.Product"%>
<%@page import=" java.util.StringTokenizer" %>
<%@page import=" java.lang.Math" %>
<%@page import=" java.util.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<%@ page pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/subviews/header.jsp" %>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type="text/css" href="travelFun.css">
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1" >	
<title>產品明細</title>
<style type="text/css">			
.productMain{	margin:10px;width:400px;}
#mainPhoto{width:400px;height:400px;border:gray solid 2px;}
.smallPhoto{text-align: center;max-width: -webkit-fill-available;min-width: 250px;}
.productPhotoIcon{max-width: 16%;min-width: 40px;margin:0.9%;}
.productPhotoIcon:hover{box-shadow: 1px 1px 3px gray;}
.productFeature{width: 350px;margin-left:20px}
.productFeature p{margin:5px 5px 30px 5px}			
.title{text-align: center;margin:90px 0px 20px 0px;font-size:40px;font-weight:bold;}
article{display:flex;flex-wrap: wrap;justify-content:center;}
.userSelect{font-size:20px;}
button{font-size:20px;font-weight:bold;border:2px solid #3C3C3C;margin:5px;}
button:hover { background-color:#3C3C3C; color: white;}
@media screen and (max-width:800px) {
	.productMain{width:300px;}
	#mainPhoto{width:300px;height:300px;}
	.title{text-align: center;margin:90px 10px 20px 10px;font-size:25px;font-weight:bold;}
	.productFeature p{margin:5px;font-size:20px;}
	.userSelect{font-size:18px;}
	button{font-size:18px;}
}
</style>
<script src='js/jquery.js'></script>	
<script>
	$(document).ready(function(){
		$.post("CartServlet",{"execute":"cartDetail"},show);//一進入此頁面就執行此行	
		$("#add").click(add);
		$("#buy").click(function(){
			add();
			window.location.href="CartServlet";
		});
		$(".productPhotoIcon").click(function(){			
			$("#mainPhoto").attr("src",$(this).attr("src"));
			return false;		
		});							
	});
	function add(){			
		$.post("CartServlet",{"name":$("#name").val() ,"color":$("#color").val() ,"size":$("#size").val() , "quantity":$("#quantity").val() ,"execute":"add"},show);
	}	
	function show(data){
		$("#goods").html(data);
	}	   	
</script>	
</head>
<body>
  	<%
		request.setCharacterEncoding("UTF-8"); 
		String productId = request.getParameter("productId");
		
		List<Product> productcolorlist = null;
		ProductService service = new ProductService();
		if(productId!=null){
			productcolorlist = service.getProductById(productId);
		}		
		
		Product p = null;
		ProductService servicep = new ProductService();
		if(productId!=null){
			p = servicep.getProductByIdp(productId);		
		}		
	%>
	<!-- 建立連線 -->
		<sql:setDataSource var="tf" driver="com.mysql.cj.jdbc.Driver"
     		url="jdbc:mysql://localhost:3306/tf?useUnicode=true&characterEncoding=UTF-8&serverTimezone=CST"
     		user="root"  password="1234"/> 	
	<!-- 查詢資料庫使用sql:query 並以變數名稱result代表查詢後的資料-->
		<sql:query dataSource="${tf}" var="sizeresult">
			SELECT * FROM products 
			JOIN product_colors ON products.id=product_colors.product_id 
			JOIN product_size ON product_colors.color_name=product_size.color_name 
			WHERE products.id=${param.productId} and product_size.color_name='${param.color}';	
		</sql:query>
		
		<div class="title"><%=p.getName() %><input type='hidden' id='name' name='name' value='<%=p.getName()%>'></div>
<article>
		<div class='productMain' >
			<img src='${param.photo}' id="mainPhoto">
			
   				<!-- 建立連線 -->
				<sql:setDataSource var="tf" driver="com.mysql.cj.jdbc.Driver"
     				url="jdbc:mysql://localhost:3306/tf?useUnicode=true&characterEncoding=UTF-8&serverTimezone=CST"
     				user="root"  
     				password="1234"/>
				<!-- 查詢資料庫使用sql:query 並以變數名稱result代表查詢後的資料-->
				<sql:query dataSource="${tf}" var="result">
      				SELECT * from tf.product_colors where photo_main_url='${param.photo}';
				</sql:query>
				<div class='smallPhoto'>
					<c:forEach var="row" items="${result.rows}">					
						<img class='productPhotoIcon' src='${row.photo_main_url}'/>		        
			        	<img class='productPhotoIcon' src='${row.photo_url1}'/>	        
			        	<img class='productPhotoIcon' src='${row.photo_url2}'/>
			        	<img class='productPhotoIcon' src='${row.photo_url3}'/>
			        	<img class='productPhotoIcon' src='${row.photo_url4}'/>					        		
					</c:forEach>
				</div>   		
		</div>	
 		<div class='productFeature'> 	      	    				    
			<%StringTokenizer st = new StringTokenizer(p.getSizeNumber()); %>	           
			<p>款式 : <%=st.nextToken("-")%></p>
			<p>品牌 : <%=p.getBrand() %></p>
			<p>定價 : <%=Math.round(p.getUnitPrice()) %> 元</p>
			<p>會員價 : <%=p.getDiscount() %> 折，<%=Math.round(p.getUnitPrice()*p.getDiscount()/10) %> 元</p>
			<p>顏色 : ${param.color}<input type='hidden' id='color' name='color' value='${param.color}'></p>			
			<p>尺寸 : 
				<select class="userSelect" id='size' name='size' required>
					<c:forEach var="row" items="${sizeresult.rows}">
						<option>${row.s_name}</option>					
					</c:forEach>						        
				</select>				
			</p>
			<p>數量 : 
				<input type='number' min='1' max='10'  class="userSelect" id='quantity' name='quantity' value='1' required>	            
			</p>
			<button id="add">加到購物車</button><button id="buy">直接購買</button>				
		</div>
</article>	
<hr>
	<div style="clear:both ;margin:10px 0px 0px 50px">
		商品描述<br />
  		<%= p.getDescription()%>	
	</div>
<%@include file="/WEB-INF/subviews/footer.jsp" %>
</body>
</html>