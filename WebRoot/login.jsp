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
	
	<link rel="stylesheet" href="res/jquery.mobile-1.3.2.min.css">
	<script src="res/jquery.mobile-1.3.2.min.js"></script>
	<script src="res/jquery-1.8.3.min.js"></script>
	<link rel="stylesheet" href="res/style.css">
	<link rel="stylesheet" href="res/reset.css">
	</head>
	<body>
		<div data-role="page">
		<div data-role="content">
			<form method="post" action="front?action=login">
				<div data-role="fieldcontain">
					<label for="fname">用户名：</label>
					<input type="text" id="fname">
					<label for="fword">密码：</label>
					<input type="password" id="fword">
					<input type="hidden" name="tourl" value="${tourl}">
					<input type="submit" data-inline="true" value="登录">
					<span><a href="reg.jsp">注册</a></span>
					
					
				</div>
			</form>
		</div>
		</div>
	</body>
	</html>