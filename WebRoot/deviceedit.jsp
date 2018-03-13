<%@ page language="java" import="java.util.*,iot.bean.*"
	pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>



<jsp:include page="head.jsp"></jsp:include>

<div class="container">
	<ol class="breadcrumb">
		<li><a href="#">Home</a></li>
		<li class="active">编辑设备</li>
	</ol>

	<div class="panel panel-default">
		<div class="panel-heading">编辑设备信息</div>
		<div class="panel-body">
			<jsp:useBean id="Device" class="iot.bean.tdevice" scope="request">
				<jsp:setProperty name="Device" property="*" />
			</jsp:useBean>
			<form class="form-horizontal"
				action="DeviceServlet?action=updateSave" method="post" role="form"
				enctype="multipart/form-data">
				<div class="form-group">
					<!--  col-md-4：表示12列占用4列，可以控制宽度 -->
					<label class="col-md-4 control-label">设备型号名称：</label>
					<div class="col-md-4">
						<input class="form-control" name="devicename" id="devicename"
							value="<%=Device.getDevicename()%>" placeholder="请输入设备型号名称"
							type="text" required>
						<!-- hidden是为了传递不可见的参数 -->
						<input type="hidden" name="id" value="<%=Device.getId()%>" id="id">
					</div>
				</div>
				<div class="form-group">
					<label class="col-md-4 control-label">设备型号：</label>
					<div class="col-md-4">
						<input class="form-control" name="devicecode" id="devicecode"
							value="<%=Device.getDevicecode()%>" placeholder="请输设备型号"
							type="text" required>
					</div>
				</div>
				<div class="form-group">
					<label class="col-md-4 control-label">设备照片：</label>
					<div class="col-md-4">
						<input class="form-control" name="devicephoto" id="devicephoto"
							value="<%=Device.getDevicephoto()%>" type="file">
					</div>
				</div>
				<div class="form-group">
					<label class="col-md-4 control-label"></label>
					<div class="col-md-4">
						<button type="submit" class="btn btn-default">保存</button>
						<a class="btn btn-default" href="DeviceServlet">返回</a>
					</div>
				</div>
			</form>
		</div>
	</div>

</div>


<jsp:include page="foot.jsp"></jsp:include>
