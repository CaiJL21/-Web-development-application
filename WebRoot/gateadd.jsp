<%@ page language="java" import="java.util.*,iot.bean.*"
	pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<jsp:include page="head.jsp"></jsp:include>

<div class="container">
	<ol class="breadcrumb">
		<li><a href="#">Home</a></li>
		<li class="active">添加网关</li>
	</ol>

	<div class="panel panel-default">
		<div class="panel-heading">添加网关信息</div>
		<div class="panel-body">
			<!-- form-horizontal：水平排列  ?action=add 带参数时 必须是post-->
			<form class="form-horizontal" action="GateServlet?action=add"
				method="post" role="form">
				<div class="form-group">
					<label class="col-md-4 control-label">项目ID：</label>
					<div class="col-md-4">
						<input class="form-control" name="pid" id="pid"
							placeholder="请输入项目ID" type="text" required>
					</div>
				</div>
				<div class="form-group">
					<!--  col-md-4：表示12列占用4列，可以控制宽度 -->
					<label class="col-md-4 control-label">网关名称：</label>
					<div class="col-md-4">
						<input class="form-control" name="gatename" id="gatename"
							placeholder="请输入网关名称" type="text" required>
					</div>
				</div>
				<div class="form-group">
					<label class="col-md-4 control-label">网关编号：</label>
					<div class="col-md-4">
						<input class="form-control" name="gateid" id="gateid"
							placeholder="请输入网关编号" type="text">
					</div>
				</div>
				<div class="form-group">
					<label class="col-md-4 control-label"></label>
					<div class="col-md-4">
						<button type="submit" class="btn btn-default">保存</button>
						<a class="btn btn-default" href="GateServlet">返回</a>
					</div>
				</div>
			</form>
		</div>
	</div>

</div>
<jsp:include page="foot.jsp"></jsp:include>
