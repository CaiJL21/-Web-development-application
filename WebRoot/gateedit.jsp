<%@ page language="java" import="java.util.*,iot.bean.*"
	pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<jsp:include page="head.jsp"></jsp:include>

<div class="container">
	<ol class="breadcrumb">
		<li><a href="#">Home</a></li>
		<li class="active">编辑网关</li>
	</ol>

	<div class="panel panel-default">
		<div class="panel-heading">编辑网关信息</div>
		<div class="panel-body">
			<jsp:useBean id="gate" class="iot.bean.Gate" scope="request">
				<jsp:setProperty name="gate" property="*" />
			</jsp:useBean>
			<!-- form-horizontal：水平排列  ?action=add 带参数时 必须是post-->
			<form class="form-horizontal" action="GateServlet?action=updateSave"
				method="post" role="form">
				<div class="form-group">
					<label class="col-md-4 control-label">项目ID：</label>
					<div class="col-md-4">
						<input class="form-control" name="pid" id="pid"
							value="<%=gate.getPid() %>" placeholder="请输入项目ID" type="text"
							required> <input type="hidden" name="id"
							value="<%=gate.getId() %>" id="id">
					</div>
				</div>
				<div class="form-group">
					<!--  col-md-4：表示12列占用4列，可以控制宽度 -->
					<label class="col-md-4 control-label">网关名称：</label>
					<div class="col-md-4">
						<input class="form-control" name="gatename" id="gatename"
							value="<%=gate.getGatename() %>" placeholder="请输入网关名称"
							type="text" required>
					</div>
				</div>
				<div class="form-group">
					<label class="col-md-4 control-label">网关编号：</label>
					<div class="col-md-4">
						<input class="form-control" name="gateid" id="gateid"
							value="<%=gate.getGateid() %>" placeholder="请输入网关编号" type="text"
							required>
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
