<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1" >
<title>會員資料</title>
<link rel="stylesheet" type="text/css" href="style/travelFun.css">

<script>

</script>

<style type="text/css">



.header{

position:fixed;


}



.registerform {


margin: 170px auto;
padding: 0;
width: 560px;




}


.registerform input{

/* height:25px ;
 width:300px ; */

}




</style>
</head>
<body>
<jsp:include page="/WEB-INF/subviews/header.jsp">
     
<jsp:param value="Home" name="header-subtitle"/>

</jsp:include>
<script src="http://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
 
<article>	
			
		<form class='registerform' method='POST' >
				    <h1>旅遊趣會員</h1><br>
				
				
					<h3><label>帳號:</label></h3>
					<input value="${sessionScope.member.id}"  disabled="disabled" id='id' >
				
				
					<h3><label>姓名:</label></h3>
					<input  id='name' value="">
				
				
					<h3><label>設定密碼:</label></h3>
					<input type="password" value='' minlength="5" maxlength="20" id='password1' ><br>
					<h3><label>確認密碼:</label></h3>
					<input type="password" value='' minlength="5" maxlength="20" id='password2'>

					<h3><label>性別:</label></h3>
					
					<input type="radio" name='gender'  id='gender' value='M' /><label>男</label>
					<input type="radio" name='gender'   id='gender' value='F' /><label>女</label>
					
				   
					<h3><label>E-mail:</label></h3>
					<input type='email'  id='email'  value=""> 
			
					<h3><label>生日:</label></h3>
					<input type='date' id='birthday'  value="">
				
					<h3><label>手機號碼:</label></h3>
					<input type='tel' id='phone' value="">
				
					<h3><label>地址:</label></h3>
					<textarea id='address' ></textarea><br>

				<br><input type='button'  id="send" value='修改資料' >				
			</form>
			<div id="msg2"></div>
		</article>
  
     <script>
     function view(data) {
    	 $("#msg2").html(data);
    	}
          function show(data){
        	  
        	  var jsObj=JSON.parse(data);
        	  
               $("#name").val(jsObj.name);
               $("#password1").val(jsObj.password);
               $("#password2").val(jsObj.password);
               $("#email").val(jsObj.email);
               $("#birthday").val(jsObj.birthday);
               $("#phone").val(jsObj.phone);
               $("#address").val(jsObj.address);
               if(jsObj.gender == 'M')
               $('input[name="gender"]')[0].checked = true;
               else
            	$('input[name="gender"]')[1].checked = true;      
              
          }
          function send(){
        	  
        	  if($("#password1").val()!=$("#password2").val())
        	  {alert("密碼不一致")}
        	  else{
        		  $.post("UpdatetServlet3",{"id":$("#id").val() ,"name":$("#name").val() , "password":$("#password1").val() ,
      		        "gender":$("#gender:checked").val(),"email":$("#email").val(),
      		        "birthday":$("#birthday").val(),"phone":$("#phone").val(),"address":$("#address").val()}, view);}
          }
          
 		$(function(){ {
 			 $.get("UpdatetServlet3",{"id":$("#id").val()},show);}})
          
  
          $("#send").click(send);
 </script>
 <%@include file="/WEB-INF/subviews/footer.jsp" %>
</body>
</html>