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
<!-- 分页插件 -->
<script type="text/javascript" src="js/bootstrap-paginator.min.js"></script>
<script type="text/javascript">   
</script>

</head>

<body>

	<nav class="navbar navbar-default navbar-static-top" role="navigation">
	<div>
		<img src="images/titlebg.jpg" width="100%" height="200px" />
	</div>

	<div class="container">
		<div class="navbar-header">
			<a class="navbar-brand" href="#">物联网监控平台</a>
		</div>
		<div>
			<ul class="nav navbar-nav">
				<li class="active"><a href="UserServlet">用户管理</a></li>
				<li class="dropdown"><a href="#" class="dropdown-toggle"
					data-toggle="dropdown"> 设备管理<b class="caret"></b>
				</a>
					<ul class="dropdown-menu">
						<li><a href="DevicetypeServlet">设备类型管理</a></li>
						<li><a href="DeviceServlet">设备型号管理</a></li>
					</ul></li>
				<li class="dropdown"><a href="#" class="dropdown-toggle"
					data-toggle="dropdown"> 监控设置<b class="caret"></b>
				</a>
					<ul class="dropdown-menu">
						<li><a href="TprojectServlet">监控项目管理</a></li>
						<li><a href="GateServlet">监控网关管理</a></li>
						<li><a href="TgatedeviceServlet">监控设备管理</a></li>
					</ul></li>
				<li class="active"><a href="real.jsp">实时监控</a></li>
				<li class="active"><a href="HistoryServlet?action=query">历史数据</a></li>
			</ul>
		</div>
		<form class="navbar-form navbar-right">
			<label><%=session.getAttribute("loginusername")%>，<%=session.getAttribute("loginusertype")%>   <a href="login.jsp" >重新登录</a>                 </label>
		</form>
	</div>
	
	</nav>
	<%
		if (session.getAttribute("loginusername") == null) {
	%>
	<script type="text/javascript">
		alert("您 还 没 有 登 录，请 先 登 录、、、、、、");
		location.href = "login.jsp";
	</script>
	<%
		}
	%>

</body>
</html>
