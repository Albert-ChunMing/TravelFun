<%@page import="tf.entity.Customer"%>
<%@page pageEncoding="UTF-8"%>
<%@page import="tf.entity.Product"%>
<%@page import="java.util.List"%>
<%@page import="tf.service.ProductService"%>
<!doctype html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1" >
<title>旅遊趣戶外店</title>
<link rel="stylesheet" type="text/css" href="travelFun.css">
<script src='js/jquery.js'></script>	            
<script>
<% Customer c= (Customer)request.getAttribute("member");
Customer logoutMember = (Customer)request.getAttribute("logout-member");
if(c!=null){ %>	            
	
$(sayHello);
function sayHello(){
	
	  alert("<%=c!=null?c.getName():""%>,歡迎回來!");
  location.href="<%= request.getContextPath() %>"; //轉址  
}
<%}else if(logoutMember!=null){%>
	$(sayGoodBye);
 function sayGoodBye(){
	   alert("<%=logoutMember.getName()%>,已登出!");  
	   location.href="<%= request.getContextPath() %>"; //轉址
}
 <%}%>
   function tobrand(value){
	$.post("brand.jsp",{"brand":value},result);
	}
   function result(data){
	$("#productlist").html(data);
	}
</script>

<style type="text/css">

.content2{
	margin: 100px 0px 20px 0px; 
	text-align : center;	   
}	
.content2 img{
	max-width:95%;
	height:auto;		   
}	
.content3p{
	display: flex;
	justify-content: space-between;
    max-width: 1170px;
    margin-left: auto;
    margin-right: auto;
    margin-bottom: 20px;
    padding-left: 2.5%;
    padding-right: 2.5%;
}
.content3 input{
	max-width: 100%;
    height: auto;
 	border: 2px solid grey;
}	
.content4p{
    background:#f3f3f0;	
    padding-left: 2.5%;
    padding-right: 2.5%;
}
.content4{	
	display: flex;
    flex-wrap: wrap;
    justify-content: flex-start;
    max-width: 1376px;
    margin-left: auto;
    margin-right: auto;
}	
.productItem{
	margin:20px;
	   width:304px;
}
.productItem img{
	max-width:300px;
	max-height:300px;
	border: 2px solid grey;
}
@media screen and (max-width:800px) {
		.productItem img{	max-width:240px;	max-height:240px;}
		.content3 input{	max-width: 90%;}
		.productItem{width:244px;}	
		.content4{justify-content: center;font-size:20px;}	
		h4{margin:10px 0px 10px 0px;}
}
</style>
</head>

<body>
<%@include file="/WEB-INF/subviews/header.jsp" %>
<div >
    	<div class='content2'>			
    		<img src="images/2020Slide01.gif" /> 
    	</div>
   
		<div class="content3p">
			<div class='content3'>
			<input type="image" src="images/OSlogo.jpg" onclick="tobrand(this.value)" value="osprey"/>
			</div>		
			<div class='content3'>
			<input type="image"  src="images/R8logo.png" onclick="tobrand(this.value)" value="routeeigth"/>
			</div>		
			<div class='content3'>
			<input type="image" src="images/LOlogo.jpg" onclick="tobrand(this.value)"  value="loki"/>
			</div>				
			<div class='content3'>
			<input type="image" src="images/BFlogo.jpg" onclick="tobrand(this.value)"  value="buff"/>
			</div>				
			<div class='content3'>
			<input type="image" src="images/PFlogo.jpg" onclick="tobrand(this.value)" value="pacsafe"/>
			</div>			
			<div class='content3'>
			<input type="image" src="images/TBlogo.png" onclick="tobrand(this.value)" value="travelblue"/>
			</div>
			<div class='content3'>
			<input type="image"src="images/TIlogo.png" onclick="tobrand(this.value)" value="timbuk"/>
			</div>
		</div>		
		<div class='content4p' >
			<div class='content4'  id="productlist" ><br>
    		<% 
    			request.setCharacterEncoding("UTF-8"); 
    			ProductService service = new ProductService(); 
    			List<Product> list = null;
   				list = service.searchAllProducts();
				if(list != null && list.size()>0) {
               	for(int i=0;i<list.size();i++){
               	Product p = list.get(i);
         	%>
         		<div class='productItem'> 
         			<a href="product.jsp?productId=<%=p.getId()%>&photo=<%=p.getPhoto_main_url() %>&color=<%=p.getColorName()%>"><img src='<%=p.getPhoto_main_url() %>'></a>
         			<h4><a href="product.jsp?productId=<%=p.getId()%>&photo=<%=p.getPhoto_main_url() %>&color=<%=p.getColorName()%>"><%= p.getName()+'('+p.getColorName()+')' %></a></h4>
      				<div>會員價:<%=p.getDiscount() %>折，<%=Math.round(p.getUnitPrice()*p.getDiscount()/10) %> 元</div>
         		</div>
         	<% }
         	}else{ %>
            	<p>查無產品資料!</p>
         	<% } %>
			</div>
		</div>
</div>
<%@include file="/WEB-INF/subviews/footer.jsp" %>
</body>
</html>