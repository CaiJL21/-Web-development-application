<%@ page language="java" import="java.util.*,iot.bean.*"
	pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<jsp:include page="head.jsp"></jsp:include>

<body>
	<!-- form-horizontal：水平排列  ?action=add 带参数时 必须是post enctype="multipart/form-data"上传文件必须加入这个-->
	<form class="form-horizontal" action="DevicetypeServlet?action=add"
		method="post" role="form">

		<div class="form-group">
			<label class="col-md-4 control-label">设备类型号：</label>
			<div class="col-md-4">
				<input class="form-control" name="devicetypecode"
					id="devicetypecode" placeholder="请输入设备型号" type="text">
			</div>
		</div>
		<div class="form-group">
			<!--  col-md-4：表示12列占用4列，可以控制宽度 -->
			<label class="col-md-4 control-label">设备类型名称：</label>
			<div class="col-md-4">
				<input class="form-control" name="devicetypename"
					id="devicetypename" placeholder="请输入设备类型名称" type="text" required>
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-4 control-label"></label>
			<div class="col-md-4">
				<button type="submit" class="btn btn-default">保存</button>
				<a class="btn btn-default" href="DevicetypeServlet">返回</a>
			</div>
		</div>
	</form>



	<jsp:include page="foot.jsp"></jsp:include>