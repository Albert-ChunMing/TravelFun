

<%@ page pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/subviews/header.jsp" %>

<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1" >
<title>會員登入</title>
<link rel="stylesheet" type="text/css" href="style/travelFun.css">
<script>
 function refreshCaptchaImage(){  
	 var captchaImage = document.getElementById("captchaImage");
     captchaImage.src='images/captcha.jpg?refresh='+new Date();
 }
</script>
<style type="text/css">
.header{
position:fixed;
}
.loginform {
margin: 170px auto;
padding: 0;
width: 560px;
}
.loginform input{
 height:25px ;
 width:300px ;
}
</style>
</head>

<body>
<article> 
 <form class='loginform' action='login.do' method='POST' >
   <h1>會員登入</h1><br>
   <span style="color:red;">${requestScope.errors}</span>
   <span style="color:red;">${requestScope.Error}</span>
  <h3><label>請輸入帳號</label></h3>
  <input name='id' type='text' placeholder='請輸入帳號' >
  
  
  
 <h3><label>密碼:</label></h3>
  <input name='password' type='password'  placeholder='請輸入密碼' >
  <br>
  
 <h3><label>請輸入圖片中數字:</label></h3>
 <img id='captchaImage' src='images/captcha.jpg' style='vertical-align: middle;'>
 <button type='button'onclick='refreshCaptchaImage()'>更新驗證碼</button><br><br>
 <input name='captcha' type='text' placeholder='請依上圖輸入驗證碼' >
  <br><br><br>

 <input type='submit' value='旅遊趣帳號登入'>
  
 </form> 
 </article>


<%@include file="/WEB-INF/subviews/footer.jsp" %>
</body>
</html>