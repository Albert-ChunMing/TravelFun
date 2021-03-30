<%@ page pageEncoding="UTF-8"%>
<%@ page import="tf.service.CustomersDAO"%>

<%
	//每次載入該頁時 從DB取得最新會員資料 同時更新session
	Customer mem = (Customer)session.getAttribute("member");
	mem=new CustomersDAO().selectCustomerById(mem.getId());
	session.setAttribute("member", mem);
	Customer m = (Customer)session.getAttribute("member");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>會員資料修改</title>
<link rel="stylesheet" type="text/css" href="style/travelFun.css">

<script src="https://code.jquery.com/jquery-3.5.1.js"
			integrity="sha256-QWo7LDvxbWT2tbbQ97B53yJnYU3WhH/C8ycbRAkjPDc="
			crossorigin="anonymous"></script>
<script	src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<script	src="https://cdn.jsdelivr.net/jquery.validation/1.16.0/jquery.validate.min.js"></script>
<script	src="https://cdnjs.cloudflare.com/ajax/libs/jquery-validate/1.19.2/localization/messages_zh_TW.min.js"></script>
<script>
	
	//$();等同$(document).ready()
	$(function() {
		//新增生日欄位的驗證規則jQuery.validator.addMethod(規則名稱, 自訂一個能返回布林值的函式, false時的訊息)
		jQuery.validator.addMethod('compareDate', function(value, element) {
			var assigntime = Date.parse($('#birthday').val());
			var deadlinetime = Date.now();
			if (assigntime < deadlinetime) {
				return true;
			} else {
				return false;
			}
		}, '日期必須小於今天');
		//新增防止密碼輸入錯誤的驗證規則
		$.validator.addMethod('passwordCheck', function(value, element) {
			var password1 =$('#password1').val();
			var password2 =$('#password2').val();
			if (password1==password2) {
				return true;
			} else {
				return false;
			}
		}, '密碼不一致');
		//設定該表單的欄位要套用什麼規則
		$('#form').validate({
			//消除左方空白
			onkeyup : function(element, event) {
				var value = this.elementValue(element).replace(/^\s+/g, "");
				$(element).val(value);
			},
			//新增每個input的驗證規則
			rules : {
				id : {required : true},
				name : {	required : true},
				password1 : {required : true,minlength : 6,maxlength : 20},
				password2 : {required : true,minlength : 6,maxlength : 20,passwordCheck : true},
				gender : {required : true},
				email : {	required : true,	email : true},
				birthday : {required : true,	compareDate : true},
				phone : {required : true, number : true},
				address : {	required : true},				
				captcha:{required : true}
			},
			//設定驗證規則未通過時 顯示的錯誤訊息
			messages : {
				id : {required : '必填'},				
				name : {	required : '必填'},
				password1 : {required : '必填',	minlength : '不得少於6位數字',maxlength : '不得超過20位數字',},
				password2 : {required : '必填',	minlength : '不得少於6位數字',maxlength : '不得超過20位數字',},
				gender : {required : "必填"},
				email : {	required : '必填',	email : 'Email格式不正確'},
				birthday : {required : '必填'},
				phone : {required : '必填', number : '電話需為數字'},
				address : {	required : '必填'},
				captcha:{required : "必填"}
			},
		});
	});
</script>

<style type="text/css">
h1{	text-align: center;margin:20px;}
article{	margin-top:90px;}
.registerform {margin-left:auto;margin-right:auto;	width: 410px;}
.registerform input {height:30px ;width:400px ; font-size:25px;margin-top:5px;}
#gender{width:30px;}
form div{margin-bottom:10px;height:100px;}
form label {display: inline-block;width: 150px;font-size:30px;font-weight:bold;}
.error {color: red;font-size:20px;font-weight:normal;}
/*label.error-->選擇目標為label且其class="error"   label .error-->選擇目標為包在label裡面的element(其class="error")*/
label.error {display: inline;}
#update{font-size:25px;font-weight:bold;border:2px solid #3C3C3C;height:50px;margin-left:5px;}
#update:hover { background-color:#3C3C3C; color: white;}
#msg{color: red;display: flex; align-items: flex-end; justify-content: center;}
@media screen and (max-width:800px) {
	.registerform {width: 310px;}
	.registerform input {width:300px;font-size:20px;}
	form label {font-size:25px;}
	.error {font-size:15px;}
}
</style>
</head>
<body>
<%@include file="/WEB-INF/subviews/header.jsp" %>
	<article>
		<form id="form" class='registerform' method='POST' action='UpdatetServlet3'>
			<h1>會員資料修改</h1>
			<div>			
				<label for="id">帳號:</label>
				<input placeholder='請輸入自行設定的帳號' id='id' name="id" value="<%=m.getId() %>" readonly="readonly">			
			</div>
			<div>
				<label for="name">姓名:</label>
				<input placeholder='請輸入姓名' id='name' name="name" value="<%=m.getName() %>">
			</div>
			<div>			
				<label for="password1">設定密碼:</label>
				<input type="password" placeholder='請輸入密碼'  id='password1' name="password1" value="<%=m.getPassword() %>">			
			</div>
			<div>
				<label for="password2">確認密碼:</label>
				<input type="password" placeholder='請再輸入密碼'  id='password2' name="password2" value="<%=m.getPassword() %>">			
			</div>
			<div>
				<label for="gender">性別:</label><br>
				<%if(m.getGender()=='M'){ %>
					<input type="radio" name='gender' id='gender' value='M' <%="checked" %>>男
					<input type="radio" name='gender' id='gender' value='F' >女
				<%} %>				
				<%if(m.getGender()=='F'){ %>
					<input type="radio" name='gender' id='gender' value='M' >男
					<input type="radio" name='gender' id='gender' value='F' <%="checked" %>>女					
				<%} %>
			</div>
			<div>
				<label for="email">E-mail:</label>
				<input type='email' placeholder='請輸入E-mail' id='email' name="email" value="<%=m.getEmail() %>">
			</div>
			<div>
				<label for="birthday">生日:</label>
				<input type='date' id='birthday' name="birthday" value="<%=m.getBirthday() %>">
			</div>
			<div>
				<label for="phone">手機號碼:</label>
				<input type='tel' placeholder="請輸入手機號碼" id='phone' name="phone" value="<%=m.getPhone() %>">
			</div>
			<div>
				<label for="address">地址:</label>
				<input type="text" placeholder="請輸入地址" id='address' name="address" value="<%=m.getAddress() %>"></input>
			</div>
			<div id="msg">${requestScope.updateStatus}</div>
				<input type='button' id="update" value='修改資料'>
		</form>
	</article>
	
<script>		
		$('#update').click(function() {
			if ($('#form').valid()) {
				$('#form').submit();
			}else{
				$("#msg").text("尚有欄位未完成");
			}
		});
</script>
<%@include file="/WEB-INF/subviews/footer.jsp"%>
</body>
</html>		