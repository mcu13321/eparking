<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="utf-8"%>

<!DOCTYPE html>
<html>
	<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=no, minimum-scale=1.0, maximum-scale=1.0,minimal-ui">
    <meta charset="utf-8">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="description" content="eparking">
    <meta name="keywords" content="eparking">
	
	<link rel="stylesheet" href="http://code.jquery.com/mobile/1.3.2/jquery.mobile-1.3.2.min.css">
	<script src="http://code.jquery.com/jquery-1.8.3.min.js"></script>
	<script src="http://code.jquery.com/mobile/1.3.2/jquery.mobile-1.3.2.min.js"></script>
	<link rel="stylesheet" href="res/style.css">
	<link rel="stylesheet" href="res/reset.css">
	
	
	
	</head>
	<body>
    <div data-role="page">
		<div data-role="header">
			<h1>用户信息</h1>
		</div>
		<div data-role="content">
			<div>
			<div>
			<p>该车场共有车位：</p>
			<p id="chewei"></p>
			</div>
			</br>
			<div>
			<p>该车场共有空位：</p>
			<p id="kongwei"></p></div>
			</br>
			<div>
			<p>a区空车位：</p>
			<p id="a"></p></div>
			</br>
			<div>
			<p>b区空车位：</p>
			<p id="b"></p></div></br>
			<a href="subscribe.jsp" data-role="button" >点击预定</a>
			</div>
			<div><img src="" alt=""></div>
			
			<div data-role="listview" data-inset="true">
				<li><a href="">用户信息</a></li>
				<li>用户：admin</li>
				
				<li><a href="">消息中心</a></li>
				<img id="luxian" src="./res/eparking.png" width="200" height="250">
				
				<li><a href="">预定信息</a></li>
				<li id="yuding"></li>
				
				
				
			</div>
		</div>
		<div data-role="footer" data-position="fixed">
			<div data-role="navbar">
				<ul>
					<li><a href="front?action=query1" data-icon="gear"  rel="external">预定车位</a></li>
					<li><a href="front?action=query" data-icon="info" class="ui-btn-active ui-state-persist" rel="external">用户信息</a></li>
				</ul>
			</div>
		</div>
	</div>
	<script type="text/javascript">
	function show(){
		document.getElementById("chewei").innerHTML = "3";
		
		if("${r1.status}"==1){
			
			document.getElementById("kongwei").innerHTML = "1";
			document.getElementById("a").innerHTML = "1";
			
		}
		if("${r2.status}"==2){
			document.getElementById("kongwei").innerHTML = "1";
			document.getElementById("a").innerHTML = "1";
		}
		if("${r3.status}"==3){
			document.getElementById("kongwei").innerHTML = "1";
			document.getElementById("b").innerHTML = "1";
		}
		 if("${r1.status}"==1&&"${r2.status}"==2){
			document.getElementById("kongwei").innerHTML = "2";
			document.getElementById("a").innerHTML = "2";
		}
		 if("${r1.status}"==1&&"${r3.status}"==3){
			document.getElementById("kongwei").innerHTML = "2";
			
		}
		 if("${r1.status}"==2&&"${r3.status}"==3){
			
			document.getElementById("kongwei").innerHTML = "2";
			
		}
	
		if("${r1.status}"==1&&"${r2.status}"==2&&"${r3.status}"==3){
			document.getElementById("kongwei").innerHTML = "3"
		}
		if ("${r1.status}"!=1&&"${r2.status}"!=2){
			
			document.getElementById("a").innerHTML = "0";
			
		}
		if ("${r3.status}"!=3){
			
			document.getElementById("b").innerHTML = "0";
		}
		if ("${r1.status}"!=1&&"${r2.status}"!=2&&"${r3.status}"!=3){
			document.getElementById("kongwei").innerHTML = "0";
			
		}
		
		if("${r1.status}"==7){
			var para=document.createElement("li");
			var node=document.createTextNode("您已预定车位：A01号车位");
			para.appendChild(node);

			var element=document.getElementById("yuding");
			element.appendChild(para);
		}
		if("${r2.status}"==8){
			var para=document.createElement("li");
			var node=document.createTextNode("您已预定车位：A02号车位");
			para.appendChild(node);

			var element=document.getElementById("yuding");
			element.appendChild(para);
		}
		if("${r3.status}"==9){
			var para=document.createElement("li");
			var node=document.createTextNode("您已预定车位：B01号车位");
			para.appendChild(node);

			var element=document.getElementById("yuding");
			element.appendChild(para);
		}
		if("${r1.status}"==7){
			document.getElementById("luxian").src = "./res/areaaone.png";
			
		}
		else if("${r2.status}"==8){
			document.getElementById("luxian").src = "./res/areaatwo.png";
			
		}
		else if("${r3.status}"==9){
			document.getElementById("luxian").src = "./res/areabone.png";
			
		}
		if("${r1.status}"==4){
			var para=document.createElement("li");
			var node=document.createTextNode("您的车位A01已被停");
			para.appendChild(node);

			var element=document.getElementById("yuding");
			element.appendChild(para);
			
		}
		if("${r2.status}"==5){
			var para=document.createElement("li");
			var node=document.createTextNode("您的车位A02已被停");
			para.appendChild(node);

			var element=document.getElementById("yuding");
			element.appendChild(para);
			
		}
		
			
		}
		document.getElementById("kongwei").innerHTML = Number(document.getElementById("a").innerHTML)+Number(document.getElementById("b").innerHTML);
	}
		window.onload=show;
	</script>
  </body>
</html>
