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
	<script> 
	function change(){
	if("${r1.status}"==1){
  			document.getElementById("a01").style.background="green";
  		}
  	if("${r1.status}"==7){
  			document.getElementById("a01").style.background="yellow";
  		}
  	if("${r1.status}"==4){
  			document.getElementById("a01").style.background="red";
  		}
  	if("${r2.status}"==2){
  			document.getElementById("a02").style.background="green";
  		}
  	if("${r2.status}"==8){
  			document.getElementById("a02").style.background="yellow";
  		}
  	if("${r2.status}"==5){
  			document.getElementById("a02").style.background="red";
  		}
  	if("${r3.status}"==3){
  			document.getElementById("b01").style.background="green";
  		}
  	if("${r3.status}"==9){
  			document.getElementById("b01").style.background="yellow";
  		}
  	if("${r3.status}"==6){
  			document.getElementById("b01").style.background="red";
  		} 
 	}
  	window.onload=change;
	 function mychange1()
 		{
  			document.getElementById("a01").style.background="yellow";
  			document.getElementById("01").value="7";
  			
  		}
  	 function mychange2()
 		{
  			document.getElementById("a02").style.background="yellow";
  			document.getElementById("02").value="8";
  		}
  	 function mychange3()
 		{
  			document.getElementById("b01").style.background="yellow";
  			document.getElementById("03").value="9";
  			
  		}
  	 function mychange4()
 		{
  			document.getElementById("b02").style.background="yellow";
  		}
  	 function mychange5()
 		{
  			document.getElementById("c01").style.background="yellow";
  			
  		}
  	 function mychange6()
 		{
  			document.getElementById("c02").style.background="yellow";
  		}
  	 function mychange7()
 		{
  			document.getElementById("d01").style.background="yellow";
  		}
  	 function mychange8()
 		{
  			document.getElementById("d02").style.background="yellow";
  		}
  	function refresh()
  	{
  		window.location.href="subscribe.jsp";
  	}
    </script>

<style>
	#a01{background:green}

	#a02{background:green}

	#b01{background:green}

	/*#b02{background:green}
	
	#c01{background:green}
	
	#c02{background:green}
	
	#d01{background:green}
	
	#d02{background:green}
	*/
</style>
	</head>
	<body>
	<form action="front?action=subscribe" method="post">
	<input type="hidden" name="yudinga01" value="" id="01">
	<input type="hidden" name="yudinga02" value="" id="02">
	<input type="hidden" name="yudingb01" value="" id="03">
    	<div data-role="page">
		<div data-role="header">
			
			<h6 >预定车位</h6>
			
			<input type="submit" value="确定" >
		</div>
		<div data-role="content">
			<div>
				<div>区域选择</div>
				
				<div data-role="collapsible" data-collapsed="false">
					<h1>A区域</h1>
					<div class="ui-grid-a">
					
					<div class="ui-block-a"><a href="#" data-role="button" id="a01"  onclick="mychange1()">A01</a></div>
					<div class="ui-block-b"><a href="#" data-role="button" id="a02"  onclick="mychange2()">A02</a></div>
					</div>
				</div>
				<div data-role="collapsible" data-collapsed="false">
					<h1>B区域</h1>
					<div class="ui-grid-a">
					<div class="ui-block-a"><a href="#" data-role="button" id="b01" onclick="mychange3()">B01</a></div>
					<div class="ui-block-b"><a href="#" data-role="button" id="b02" onclick="mychange4()">B02</a></div>
					</div>
				</div>
				<div data-role="collapsible" data-collapsed="false">
					<h1>C区域</h1>
					<div class="ui-grid-a">
					<div class="ui-block-a"><a href="#" data-role="button" id="c01" onclick="mychange5()">C01</a></div>
					<div class="ui-block-b"><a href="#" data-role="button" id="c02" onclick="mychange6()">C02</a></div>
				    </div>
				</div>
				<div data-role="collapsible" data-collapsed="false">
					<h1>D区域</h1>
					<div class="ui-grid-a">
					<div class="ui-block-a"><a href="#" data-role="button" id="d01" onclick="mychange7()">D01</a></div>
					<div class="ui-block-b"><a href="#" data-role="button" id="d02" onclick="mychange8()">D02</a></div>
					</div>
				</div>
				
			</div>
			<div class="sub_bos">
				<div id="sub_blank"></div>
				<p>空位</p>
				<div id="sub_ordered"></div>
				<p>已预订</p>
				<div id="sub_stoped"></div>
				<p>已停</p>
			</div>
		</div>
		<div data-role="footer" data-position="fixed">
			<div data-role="navbar">
				<ul>
					<li><a href="front?action=query1" data-icon="gear" class="ui-btn-active ui-state-persist" rel="external">预定车位</a></li>
					<li><a href="front?action=query" data-icon="info"  rel="external">用户信息</a></li>
				</ul>
			</div>
		</div>
	</div>
	</form>
</body>
</html>

