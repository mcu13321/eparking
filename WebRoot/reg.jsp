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
		<div data-role="content">
			<form action="front?action=reg" method="post">
				<div data-role="fieldcontain">
					<label for="username">请输入用户名：</label>
					<input type="text" id="username" name="username">
					<label for="password">请输入密码：</label>
					<input type="password" id="password" name="password">
					<label for="realname">请输入真实姓名：</label>
					<input type="text" id="realname" name="realname">
					<label for="mobile">请填写手机号码：</label>
					<input type="text" id="mobile" name="mobile">
					<label for="certificate">请填写身份证号码：</label>
					<input type="text" id="certificate" name="certificate">
					<label for="carinfo">请填写有关您的车信息</label>
					<textarea name="addinfo" id="carinfo" name="carinfo"></textarea>
					<input type="submit" data-inline="true" value="提交">
				</div>
			</form>
		</div>
	</div>
  </body>
</html>
