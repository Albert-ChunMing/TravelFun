<%@ page pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/subviews/header.jsp" %>
<%@ page import="java.util.List" %>
<%List<String> errors=(List<String>)request.getAttribute("errors");%>

<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1" >
<title>會員登入</title>
<link rel="stylesheet" type="text/css" href="style/travelFun.css">
<script>
 function refreshCaptchaImage(){  
	 var captchaImage = document.getElementById("captchaImage");
     captchaImage.src='images/captcha.jpg?refresh='+Math.random();//利用每次都攜帶不同參數 重新進入一次CaptchaServlet
 }
</script>
<style type="text/css">
.loginform {
margin-left:auto;
margin-right:auto;
width: 320px;
}
.loginform input{
 height:30px ;
 width:300px ;
 font-size:22px;
}
article{margin-top:90px;min-height:84%;}
h1{text-align: center;margin:20px;}
h3{margin-top:20px;margin-bottom:10px;}
#login{font-size:25px;font-weight:bold;border:2px solid #3C3C3C;height:50px;}
#login:hover { background-color:#3C3C3C; color: white;}
</style>
</head>

<body>
<article> 
	<form class='loginform' action='login.do' method='POST' >
		<h1>會員登入</h1>
		<span style="color:red;">
			<%
				if(errors!=null) {
					for(int i=0;i<errors.size();i++){%><%=errors.get(i)+"<br>"%><%}
				};
			%>
			${requestScope.Error}
		</span>
   
		<h3>帳號 :</h3>
		<input name='id' type='text' placeholder='請輸入帳號' value="${requestScope.id}">  
		<h3>密碼 :</h3>
		<input name='password' type='password'  placeholder='請輸入密碼' value="${requestScope.password}">
  		<h3><label>請輸入驗證碼 :</label></h3>
 		<img id='captchaImage' src='images/captcha.jpg' style='vertical-align: middle;'>
 		<button type='button'onclick='refreshCaptchaImage()'>更新驗證碼</button><br><br>
 		<input name='captcha' type='text' placeholder='請依上圖輸入驗證碼' ><br><br><br>  
 		<input type='submit'  id="login" value='旅遊趣帳號登入'>  
	</form> 
</article>
<%@include file="/WEB-INF/subviews/footer.jsp" %>
</body>
</html>