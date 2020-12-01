<%@page import="tf.entity.Customer"%>
<%@ page pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="model.CartProduct" %>

<style>
.header{		
	width: 100%;
	height: 100px;
	left: 0px;
	top: 0px;
	text-align : center;
	background:	#f3f3f0;
	position:fixed;
	opacity:0.9;
		
	}	
.login{
    position:fixed;
	top: 30px;
	right: 20px;
}
#myCarticon{
   position:fixed;
	top: 15px;
	right:30%;
	display: flex; 
  	align-items: center;  
 }
#mySearch{
   width: 30%;
   margin:30px auto;    
}

#msg{
	color:red;
	font-size:50px;
	font-weight:bold;
	font-family: Microsoft JhengHei;
}
#myLogo{
	position:fixed;
	top: 20px;
	left: 20px;
}
</style>		
		<header class='header'>		
		<%
			Customer member = (Customer)session.getAttribute("member"); //取得session中已登入的member
		%>		  	    
	       <a href='index.jsp'><img id='myLogo' src='images/logo-header.png' /></a>
	       
	       <div id='mySearch'>
            	<input  style="width:400px;height:30px;font-size:20px" type='search'  placeholder='請輸入產品關鍵字...' name='search' value="">
            	<input style="height:33px;font-size:20px" type='submit' value='搜尋'>
            </div>            
           <%
           		List<CartProduct> productList=(List<CartProduct>)request.getSession().getAttribute("productList");
                	int number=0;
                	if(productList!=null) number=productList.size();                      		
           %>
           <div  id='myCarticon'>
           		<a href='CartServlet'><img style="width:50px;height:50px;" src="images/carticon.png" /></a>
           		<span id="msg"><%=number %></span>
           </div>
           
           <span class='login'>
	     <%
	     	if(member==null) { //尚未登入
	     %>	     
	        <a href='<%=request.getContextPath()%>/login.jsp'>登入</a>  |  
	        <a href='<%=request.getContextPath()%>/register.jsp'>註冊</a>
	        <% }else if(member.getClassname().equals("root")){ //已登入%>
	       <!-- 下面root是判斷關鍵歐 -->
	        <%= member.getClassname().equals("root")?member.getName():""%> 你好!
			<a href='<%= request.getContextPath()%>/logout.do'>登出</a>  |
			<a href='<%= request.getContextPath()%>/updatemember.jsp'>修改會員</a> |
			<a href='<%= request.getContextPath()%>/erp.html'>後臺管理</a>	     
	     <%}else{ //已登入%>	        
	         <%=member!=null?member.getName():""%> 你好!
			<a href='<%=request.getContextPath()%>/logout.do'>登出</a>  |
			<a href='<%=request.getContextPath()%>/updatemember.jsp'>修改會員</a> |
			<a href='<%=request.getContextPath()%>/QueryOrder.jsp'>查詢訂單</a>
			<%}	%>
	     </span>
                      	           
           
	
	   <%--      <sub><%= request.getParameter("header_subtitle")==null?""
							:request.getParameter("header_subtitle")%></sub>  --%>   					
			
		</header>
		

