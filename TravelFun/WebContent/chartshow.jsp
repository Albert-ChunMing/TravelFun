<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.7.2/Chart.min.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>

<canvas id="myChart" width="100" height="100" ></canvas>

<script> var ctx = document.getElementById("myChart").getContext('2d'); 

var myChart = new Chart(ctx, 
		{ type: 'bar', data: 
			{ labels: ["昨日業績", "前天業績", "近7天業績", "上個月平均", "今年平均"], 
			datasets: [{ label: '業績報表', data: 
			[<c:forEach  var="msg"  items="${message}" begin="0" end="2">${msg.sum},</c:forEach>
			<c:forEach  var="msg1"  items="${message}" begin="3" end="3">${msg1.avg}</c:forEach>
			<c:forEach  var="msg2"  items="${message}" begin="4" end="4">,${msg2.avg}</c:forEach>], 
				backgroundColor: [ 'rgba(255, 99, 132, 0.2)', 'rgba(54, 162, 235, 0.2)', 'rgba(255, 206, 86, 0.2)', 'rgba(75, 192, 192, 0.2)', 'rgba(153, 102, 255, 0.2)' ], 
				borderColor: [ 'rgba(255,99,132,1)', 'rgba(54, 162, 235, 1)', 'rgba(255, 206, 86, 1)', 'rgba(75, 192, 192, 1)', 'rgba(153, 102, 255, 1)' ],
				borderWidth: 1 }] },
				options: { scales: { 
					yAxes: [{ ticks: { beginAtZero:true } }] } } });
				 </script>
  
</body>
</html>