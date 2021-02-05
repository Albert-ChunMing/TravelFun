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
	/*position:fixed; */
	background:#f3f3f0;
	position:fixed;
	opacity:0.9;	
	}	
.login{
    position:fixed;
	top: 20px;
	right: 100px;
}	
#myForm{
	width: 80%;
	margin: 50px auto ;
}
#myCarticon{
   margin-left:4.7%;   
 }
#mySearch{
   width: 25%;
}
#searchButton{
  width:auto;
}
#msg{
	color:red;
	font-size:50px;
	font-weight:bold;
	font-family: Microsoft JhengHei;
}
</style>		
		<header class='header'>		
		<%
			Customer member = (Customer)session.getAttribute("member"); //取得session中已登入的member
		%>	
		<span class='login'>
	     <%
	     	if(member==null) { //尚未登入
	     %>	     
	        <a href='<%=request.getContextPath()%>/login.jsp'>登入</a>  |  
	        <a href='<%=request.getContextPath()%>/register.jsp'>註冊</a>	     
	     <%}else{ //已登入%>	        
	         <%=member!=null?member.getName():""%> 你好!
			<a href='<%=request.getContextPath()%>/logout.do'>登出</a>  |
			<a href='<%=request.getContextPath()%>/update.jsp'>修改會員</a> |
			<a href='<%=request.getContextPath()%>/QueryOrder.jsp'>查詢訂單</a>
			<%}	%>
	     </span>  
	    
	       <a href='index.jsp'><img id='myLogo' src='images/logo-header.png' /></a>
            <input id='mySearch' type='search'  placeholder='請輸入產品關鍵字...' name='search' value="">
            <input id='searchButton'type='submit' value='搜尋'>       
           <a href='CartServlet'><img src="images/carticon.png" id='myCarticon'/></a>
           <%
           		List<CartProduct> productList=(List<CartProduct>)request.getSession().getAttribute("productList");
                	int number=0;
                	if(productList!=null) number=productList.size();                      		
           %>           	           
           <span id="msg"><%=number %></span>
	
	   <%--      <sub><%= request.getParameter("header_subtitle")==null?""
							:request.getParameter("header_subtitle")%></sub>  --%>   					
			
		</header>
		

