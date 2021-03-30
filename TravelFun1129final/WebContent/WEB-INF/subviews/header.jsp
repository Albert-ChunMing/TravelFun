<%@ page import="tf.entity.Customer"%>
<%@ page pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="model.CartProduct" %>
<meta name="viewport" content="width=device-width, initial-scale=1" >
<style>
header{		
	width: 100%;
	height: 80px;
	left: 0px;
	top: 0px;	
	background:#f3f3f0;
	position:fixed;
	opacity:0.95;	
	display: flex;
	flex-wrap: wrap;
	justify-content: space-between;
    align-items: center;
    border-bottom: #c0c0c0 2px solid;
    border-top: #c0c0c0 2px solid;
}
.myLogo{
	display: flex;
	align-items: center;
}
#myLogo{
	margin-left:-20px;
}		
#myCarticon{
	width:27px;
	height:27px;
 }
#mySearch{
   width: 180px;
   height:28px;
   font-size:20px;
}
#searchButton{
	 font-size:20px;
}
#goods{
	color:red;
	font-size:27px;
}
.login{
	font-size: 20px;
}
.login a{
	text-decoration: none;
	margin-left:2px;
	margin-right:2px;
}
.login a:hover{
	text-decoration:underline;
}
@media screen and (max-width:800px) {
	header{display:block;}
	.myLogo{justify-content: center;}
	#myLogo{margin-left:-10px;width:100px;height:auto;}
	#mySearch{width: 150px;height:20px;font-size:15px;}
	#searchButton{font-size:12px;}
	.login{font-size:15px;text-align: end; margin-right: 10px;}		
}
</style>		
<header>
		<div class="myLogo">
			<div>
	     		<a href='index.jsp'><img id='myLogo' src='images/logo-header.png' /></a>
	    	</div>	    
	    	<div>
            	<input id='mySearch' type='search'  placeholder='請輸入產品關鍵字' name='search' value="">
            	<input id='searchButton' type='submit' value='搜尋'>
	     	</div>
	    </div>	     				
		<div class='login'>			
         <%
			Customer member = (Customer)session.getAttribute("member"); //取得session中已登入的member
	     	if(member==null) { //尚未登入
	     %>	     
	        <a href='<%=request.getContextPath()%>/login.jsp'>登入</a>|<a href='<%=request.getContextPath()%>/register.jsp'>註冊</a>|	     
	     <%}else{ //已登入%>	        
	         <%=member.getName()%> 你好!
			<a href='<%=request.getContextPath()%>/logout.do'>登出</a>|
			<a href='<%=request.getContextPath()%>/updatemember.jsp'>修改會員</a>|
			<a href='<%=request.getContextPath()%>/QueryOrder.jsp'>查詢訂單</a>|
			<%}			
           	List<CartProduct> products=(List<CartProduct>)request.getSession().getAttribute("productList");
            int number=0;
            if(products!=null) number=products.size();                      		
         %> 
           <a href='CartServlet'><img src="images/carticon.png" id='myCarticon'/><span id="goods"><%=number %></span></a>
	     </div>  
</header>
		

