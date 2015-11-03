<%@ page language="java" pageEncoding="utf-8"%>
<html>
    <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=no, minimum-scale=1.0, maximum-scale=1.0,minimal-ui">
    <meta charset="utf-8">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <link rel="stylesheet" type="text/css" href="res/reset.css">
    <link rel="stylesheet" type="text/css" href="res/style.css">

    <script language="JavaScript" type="text/javascript">
	function delayURL(url) {
		var delay = document.getElementById("time").innerHTML;
		if(delay > 0) {
			delay--;
			document.getElementById("time").innerHTML = delay;
		} else {
			window.top.location.href = url;
		}
		setTimeout("delayURL('" + url + "')", 1000);
	}
   </script>
<style>.gmu-media-detect{-webkit-transition: width 0.001ms; width: 0; position: absolute; clip: rect(1px, 1px, 1px, 1px);}
@media screen and (width: 1280px) { #gmu-media-detect0 { width: 1px; } }  
</style>
</head>
<body style="-webkit-tap-highlight-color: rgba(255, 255, 255, 0);">
<div class="pages">
    <section id="loginPage" class="__page__ login-page" style="display: block; -webkit-transition: all 0.3s ease-in-out; -webkit-transform: translateX(0px); overflow: hidden;">
        <div class="top-bar" style="-webkit-transition: -webkit-transform 0ms; -webkit-transform-origin: 0px 0px; -webkit-transform: translate(0px, 0px) translateZ(0px);">
            
            <h1>操作提示</h1>
        </div>
        <div class="content" style='text-align:center'> 
                <div id="tipinfo" style='margin-top:28px;visibility: visible;height: 20px;font-size:20px;color:red;'>${message}</div>
                <div style='margin-top:28px;visibility: visible;height: 20px;font-size:20px;color:red;'><span id="time">3</span>秒钟后自动跳转</div>
        </div>
    </section>
   
</div>


<script type="text/javascript">
delayURL("${url}");
</script>




</body></html>

