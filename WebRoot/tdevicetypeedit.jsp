<%@ page language="java" import="java.util.*,iot.bean.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<jsp:include page="head.jsp"></jsp:include>

<div class="container">
	<ol class="breadcrumb">
		<li><a href="#">Home</a></li>
		<li class="active">编辑设备类型</li>
	</ol>

	<div class="panel panel-default">
		<div class="panel-heading">编辑设备类型信息</div>
		<div class="panel-body">
		    <jsp:useBean id="devicetype" class="iot.bean.tdevicetype" scope="request">
		       <jsp:setProperty name="devicetype" property="*" />
		    </jsp:useBean>
			<!-- form-horizontal：水平排列  ?action=add 带参数时 必须是post-->
			<form class="form-horizontal" action="DevicetypeServlet?action=updateSave" method="post" role="form" >
				<div class="form-group">
				   <!--  col-md-4：表示12列占用4列，可以控制宽度 -->
					<label class="col-md-4 control-label">设备类型号：</label>
					<div class="col-md-4">
						<input class="form-control" name="devicetypecode" id="devicetypecode" value="<%=devicetype.getDevicetypecode() %>" placeholder="请输入设备类型号" type="text" required>
					    <!-- hidden是为了传递不可见的参数 -->
					    <input type="hidden" name="id"  value="<%=devicetype.getId() %>"  id="id" >
					</div>
				</div>
				<div class="form-group">
					<label class="col-md-4 control-label">设备类型名：</label>
					<div class="col-md-4">
						<input class="form-control" name="devicetypename" id="devicetypename"  value="<%=devicetype.getDevicetypename() %>"  placeholder="请输设备类型名" type="text" required>
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
		</div>
	</div>

</div>


<jsp:include page="foot.jsp"></jsp:include>
