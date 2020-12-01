<%@page import="tf.service.ProductService"%>
<%@page import="tf.entity.Product"%>
<%@page import=" java.util.StringTokenizer" %>
<%@page import=" java.lang.Math" %>
<%@page import=" java.util.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<%@ page pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@include file="/WEB-INF/subviews/header.jsp" %>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1" >	
<title>產品明細</title>
<link rel="stylesheet" type="text/css" href="travelFun.css">
<script src='js/jquery.js'></script>

<script>
	$(function(){		
		$("ul a").click(function(){			
		$("figure img").attr("src",$(this).attr("href"));
		return false;		
		});		
	});
	
	$(document).ready(function(){	
		$("#add").click(add);						
		$.post("CartServlet",{"execute":"cartDetail"},show);//一進入此頁面就執行此行								
	});
	function add(){			
		$.post("CartServlet",{"name":$("#name").val() ,"color":$("#color").val() ,"size":$("#size").val() , "quantity":$("#quantity").val() ,"execute":"add"},show);
	}	
	function show(data){
		$("#msg").html(data);
	}
	   	
</script>	
</head>
<body>
<br><br><br><br>
<article>		
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
<div class="display">
<div>
	<div class='productMain' >
		<figure><img src='${param.photo}'></figure>
	</div>
<ul class='ultest'>
   <!-- 建立連線 -->
	<sql:setDataSource var="tf" driver="com.mysql.cj.jdbc.Driver"
     url="jdbc:mysql://localhost:3306/tf?useUnicode=true&characterEncoding=UTF-8&serverTimezone=CST"
     user="root"  password="1234"/> 	
	<!-- 查詢資料庫使用sql:query 並以變數名稱result代表查詢後的資料-->
	<sql:query dataSource="${tf}" var="result">
      	SELECT * from tf.product_colors where photo_main_url='${param.photo}';
	</sql:query>
			<c:forEach var="row" items="${result.rows}">					
					<li class='productPhotoIcon'><a href='${row.photo_main_url}'><img src='${row.photo_main_url}'/></a>			        
			        <li class='productPhotoIcon'><a href='${row.photo_url1}'><img src='${row.photo_url1}'/></a>			        
			         <li class='productPhotoIcon'><a href='${row.photo_url2}'><img src='${row.photo_url2}'/></a>			         
			         <li class='productPhotoIcon'><a href='${row.photo_url3}'><img src='${row.photo_url3}'/></a>			        
			         <li class='productPhotoIcon'><a href='${row.photo_url4}'><img src='${row.photo_url4}'/></a>					        		
			</c:forEach>	
</ul>
</div>
<div class="productFeature"> 	     
 	    	<h1><%=p.getName() %></h1>			    
			<%StringTokenizer st = new StringTokenizer(p.getSizeNumber()); %>	           
			<p>款式 : <%=st.nextToken("-")%></p>
			<p>品牌 : <%=p.getBrand() %></p>
			<p>定價 NT : <%=Math.round(p.getUnitPrice()) %> 元</p>
			<p>會員價 : <%=p.getDiscount() %> 折，<%=Math.round(p.getUnitPrice()*p.getDiscount()/10) %> 元</p>			
		    
		     <input type='hidden' id='name' name='name' value='<%=p.getName()%>'>	
		     	     
		    <h2>請選擇 顏色</h2>
		       <select class="colorIcon" id='color' name='color' required>
		       		<option>${param.color}</option>		   
		       </select>
			
		    <h2>請選擇 尺寸</h2>
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
				<select class="sizeIcon" id='size' name='size' required>
					<c:forEach var="row" items="${sizeresult.rows}">
						<option >${row.s_name}</option>					
					</c:forEach>		 
				</select>		
			
			<h2>數量：</h2>
					<input class='number' type='number' min='1' max='5' id='quantity' name='quantity' value='1' required>
	            	<button class='addCart' id="add" >加到購物車</button>			            
</div>
</div>

 	
   		
<hr>
	<div style="clear:both ;margin:10px 0px 0px 50px">
	商品描述<br />
  	<%= p.getDescription()%>	
	</div>
</article>
	<%@include file="/WEB-INF/subviews/footer.jsp" %>
</body>
</html>