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
	    margin: 130px 0px 20px 0px; 
	    text-align : center;	   
}	
.content2 img{
		max-width:95%;
		height:auto;		   
}	
.content3{
		width:100px;
		height:100px;
		margin:0px 5px 5px 120px;
 	    border: 2px solid grey;
	    display:inline-table;
}	
.content4{
		width:100%;
	    height:80%;
	    margin:0.5px;
		background:#f3f3f0;	
		background-position:center;	
	    clear: both;
}	
.productItem{
		margin:0px 5px 120px 120px;
	    display:inline-table;
}
.productItem img{
		width:300px;
		height:300px;
	    border: 2px solid grey;
}
</style>
</head>

<body>
<%@include file="/WEB-INF/subviews/header.jsp" %>
<div >
    	<div class='content2'>			
    		<img src="images/2020Slide01.gif" /> 
    	</div>
    	
		<ul>
			<li class='content3'>
			<input type="image" src="images/OSlogo.jpg" onclick="tobrand(this.value)" value="osprey"/>
			</li>		
			<li class='content3'>
			<input type="image"  src="images/R8logo.png" onclick="tobrand(this.value)" value="routeeigth"/>
			</li>		
			<li class='content3'>
			<input type="image" src="images/LOlogo.jpg" onclick="tobrand(this.value)"  value="loki"/>
			</li>				
			<li class='content3'>
			<input type="image" src="images/BFlogo.jpg" onclick="tobrand(this.value)"  value="buff"/>
			</li>				
			<li class='content3'>
			<input type="image" src="images/PFlogo.jpg" onclick="tobrand(this.value)" value="pacsafe"/>
			</li>			
			<li class='content3'>
			<input type="image" src="images/TBlogo.png" onclick="tobrand(this.value)" value="travelblue"/>
			</li>
			<li class='content3'>
			<input type="image"src="images/TIlogo.png" onclick="tobrand(this.value)" value="timbuk"/>
			</li>
		</ul>		
		
		<div class='content4'  id="productlist" >產品列表 <br>
    <% 
    request.setCharacterEncoding("UTF-8"); 
    ProductService service = new ProductService(); 
    List<Product> list = null;
    list = service.searchAllProducts();
    %>
 
       <% if(list != null && list.size()>0) { %>
       
       <ul>
         <%    
               for(int i=0;i<list.size();i++){
               Product p = list.get(i);
         %>
         <li class='productItem'> 
         <a href="product.jsp?productId=<%=p.getId()%>&photo=<%=p.getPhoto_main_url() %>&color=<%=p.getColorName()%>"><img src='<%=p.getPhoto_main_url() %>'></a>
         <h4><a href="product.jsp?productId=<%=p.getId()%>&photo=<%=p.getPhoto_main_url() %>&color=<%=p.getColorName()%>"><%= p.getName()+'('+p.getColorName()+')' %></a></h4>
      <div>會員價:<%=p.getDiscount() %>折，<%=Math.round(p.getUnitPrice()*p.getDiscount()/10) %> 元</div>
         </li>
         <% } %>
         
        </ul> 
         <% }else{ %>
            <p>查無產品資料!</p>
         <% } %>
		</div>
</div>
<%@include file="/WEB-INF/subviews/footer.jsp" %>
</body>
</html>