<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>物联网监控平台</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<link href="js/bootstrap/css/bootstrap.min.css" rel="stylesheet">
<script src="js/jquery-1.11.3.min.js"></script>
<script src="js/bootstrap/js/bootstrap.min.js"></script>


<style type="text/css">
</style>
<script type="text/javascript">
	
</script>

</head>

<body>
	<div class="container">
		<!--  col-md-4：表示12列占用4列，可以控制宽度 -->
		<div class="col-md-4"></div>
		<!-- ?action=add 带参数时 必须是post-->
		<form role="form" action="UserServlet?action=login" method="post"
			class="col-md-4">
			<h2 class="form-signin-heading">物联网监控平台登录</h2>
			<div class="form-group">
				<label for="username">用户名：</label> <input type="text" id="username"
					name="username" class="form-control" placeholder="请输入用户名" required
					autofocus>
			</div>
			<div class="form-group">
				<label for="password">密 码：</label> <input type="password"
					id="password" name="password" class="form-control"
					placeholder="请输入密码" required>
			</div>
			<div class="checkbox">
				<label> <input type="checkbox" id="remember" name="remember"
					value="1"> 记住我
				</label>
			</div>
			<button class="btn btn-lg btn-primary btn-block" id="login"
				type="submit">登录</button>
			<%
				String msg = "";
				if (request.getAttribute("msg") != null) {
					msg = request.getAttribute("msg").toString();
				}
				if (msg.length() > 0) {
					//显示提示信息
			%>
			<br>
			<div class="alert alert-warning" id="statusbar" role="alert"><%=msg%></div>
			<%
				}
			%>

		</form>
		<div class="col-md-4"></div>

	</div>

</body>

</html>
