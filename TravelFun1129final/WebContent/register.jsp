<%@ page pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>加入會員</title>
<link rel="stylesheet" type="text/css" href="style/travelFun.css">

<script src="https://code.jquery.com/jquery-3.5.1.js"
	integrity="sha256-QWo7LDvxbWT2tbbQ97B53yJnYU3WhH/C8ycbRAkjPDc="
	crossorigin="anonymous"></script>

<script
	src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<script
	src="https://cdn.jsdelivr.net/jquery.validation/1.16.0/jquery.validate.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/jquery-validate/1.19.2/localization/messages_zh_TW.min.js"></script>



<script>
	function refreshCaptchaImage() {
		//alert('refreshCaptchaImage');
		var captchaImage = document.getElementById("captchaImage");
		captchaImage.src = 'images/register_captcha.jpg?refresh=' + new Date();
	}

	$(function() {
		$.validator.addMethod('compareDate', function(value, element) {
			var assigntime = Date.parse($('#birthday').val()).valueOf();
			var deadlinetime = Date.now().valueOf();
			if (assigntime < deadlinetime) {
				return true;
			} else {
				return false;
			}
		}, '<font color="#E47068">日期必須小於今天</font>');
		$.validator.addMethod('passwordCheck', function(value, element) {
			var password1 =$('#password1').val();
			var password2 =$('#password2').val();
			if (password1==password2) {
				return true;
			} else {
				return false;
			}
		}, '<font color="#E47068">密碼不一致</font>');

		$('#form').validate({
			onkeyup : function(element, event) {
				var value = this.elementValue(element).replace(/^\s+/g, "");
				$(element).val(value);
			},

			rules : {
				
				birthday : {
					required : true,
					compareDate : true
				},
				id : {
					required : true
				},
				name : {
					required : true
				},
				password : {
					required : true,
					minlength : 6,
					maxlength : 20,
					passwordCheck : true
				},
				phone : {
					required : true,
					number : true
				},
				address : {
					required : true
				},
				email : {
					required : true,
					email : true
				},

				gender : {
					required : true
				}
			},
			messages : {
				id : {
					required : '必填'
				},
				birthday : {
					required : '必填'
				},
				name : {
					required : '必填'
				},
				password : {
					required : '必填',
					minlength : '不得少於6位數字',
					maxlength : '不得超過20位數字',

				},
				phone : {
					required : '必填',
					number : '電話需為數字'
				},
				address : {
					required : '必填'
				},
				email : {
					required : '必填',
					email : 'Email格式不正確'
				},
				gender : {
					required : "必填"
				},
			},
			
		});
	});
</script>

<style type="text/css">
.header {
	position: fixed;
}

.registerform {
	margin: 170px auto;
	padding: 0;
	width: 560px;
}

.registerform input {
	/* height:25px ;
 width:300px ; */
	
}

form label {
	display: inline-block;
	width: 100px;
}

form div {
	margin-bottom: 10px;
}

.error {
	color: red;
	margin-left: 5px;
}

label.error {
	display: inline;
}
</style>
</head>
<body>
	<jsp:include page="/WEB-INF/subviews/header.jsp">

		<jsp:param value="Home" name="header-subtitle" />

	</jsp:include>

	<article>

		<form id="form" class='registerform' method='POST'
			action='register.do'>
			<h1>加入旅遊趣會員</h1>
			<br>


			<h3>
				<label for="id">帳號:</label>
			</h3>
			<input placeholder='請輸入自行設定的帳號' id='id' name="id">


			<h3>
				<label for="name">姓名:</label>
			</h3>
			<input placeholder='請輸入姓名' id='name' name="name">


			<h3>
				<label>設定密碼:</label>
			</h3>
			<input type="password" placeholder='請輸入密碼' minlength="5"
				maxlength="20" id='password1' "><br>
			<h3>
				<label for="password">確認密碼:</label>
			</h3>
			<input type="password" placeholder='請再輸入密碼' minlength="5"
				maxlength="20" id='password2' name="password">

			<h3>
				<label for="gender">性別:</label>
			</h3>

			<input type="radio" name='gender' id='gender' value='M' required><label>男</label>
			<input type="radio" name='gender' id='gender' value='F' required><label>女</label>



			<h3>
				<label for="email">E-mail:</label>
			</h3>
			<input type='email' placeholder='請輸入E-mail' id='email' name="email">

			<h3>
				<label for="birthday">生日:</label>
			</h3>
			<input type='date' id='birthday' name="birthday">

			<h3>
				<label for="phone">手機號碼:</label>
			</h3>
			<input type='tel' id='phone' name="phone">

			<h3>
				<label for="address">地址:</label>
			</h3>
			<textarea id='address' name="address"></textarea>

			<h3>
				<label>驗證碼:</label>
			</h3>
			<img id='captchaImage' src='images/register_captcha.jpg'
				style='vertical-align: middle;'>
			<button type='button' onclick='refreshCaptchaImage()'>更新驗證碼</button>
			<br> <br> <input type="text" placeholder='依上圖輸入驗證碼'
				name='captcha' required> <br> <input type='submit'
				id="add" value='加入旅遊趣會員'>
		</form>
	</article>
	<div id="msg"></div>
	<script>
		function display(data) {
			$("#msg").html(data);

		}
		function insert() {
			
				$.post("register.do", {
					"id" : $("#id").val(),
					"name" : $("#name").val(),
					"password" : $("#password1").val(),
					"gender" : $("#gender:checked").val(),
					"email" : $("#email").val(),
					"birthday" : $("#birthday").val(),
					"phone" : $("#phone").val(),
					"address" : $("#address").val()
				}, display);
			}
		}
		 $('#add').click(function() {
			   if (validator.form()) {
			    insert();
			   }
			  });
	</script>

	<%@include file="/WEB-INF/subviews/footer.jsp"%>

</body>
</html>		