<%@ page language="java" import="java.util.*,iot.bean.*"
	pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<jsp:include page="head.jsp"></jsp:include>
<%
int flag;
if(session.getAttribute("loginusertype").equals("管理员"))
{
	session.setAttribute("flag", "1");
}
else if(session.getAttribute("loginusertype").equals("普通用户"))
{
	session.setAttribute("flag", "2");
}
%>

<%
if(session.getAttribute("flag").equals("1")){
%>
<script type="text/javascript">        
          alert("欢迎管理员对监控设备进行修改操作！");     
    </script>
<% 
}
%>
<%
if(session.getAttribute("flag").equals("2")){
%>
<script type="text/javascript">        
          alert("抱歉！普通用户不能对监控设备进行修改操作！");   
          location.href="TgatedeviceServlet";  
    </script>
<% 
}
%>
<div class="container">
	<ol class="breadcrumb">
		<li><a href="head.jsp">Home</a></li>
		<li class="active">编辑终端设备信息</li>
	</ol>

	<div class="panel panel-default">
		<div class="panel-heading">编辑终端设备信息</div>
		<div class="panel-body">
			<jsp:useBean id="tgatedevice" class="iot.bean.Tgatedevice"
				scope="request">
				<jsp:setProperty name="tgatedevice" property="*" />
			</jsp:useBean>
			<!-- form-horizontal：水平排列  ?action=add 带参数时 必须是post-->
			<form class="form-horizontal"
				action="TgatedeviceServlet?action=updateSave" method="post"
				role="form">

				<div class="form-group">
					<label class="col-md-4 control-label">网关id：</label>
					<div class="col-md-4">
						<input class="form-control" name="gid" id="gid"
							placeholder="请输入网关id" value="<%=tgatedevice.getGid() %>"
							type="text" required> <input type="hidden" name="id"
							value="<%=tgatedevice.getId() %>" id="id">
					</div>
				</div>
				<div class="form-group">
					<label class="col-md-4 control-label">设备id：</label>
					<div class="col-md-4">
						<input class="form-control" name="did" id="did"
							placeholder="请输入设备id" value="<%=tgatedevice.getDid() %>"
							type="text" required>
					</div>
				</div>
				<div class="form-group">
					<!--  col-md-4：表示12列占用4列，可以控制宽度 -->
					<label class="col-md-4 control-label">终端设备名称:</label>
					<div class="col-md-4">
						<input class="form-control" name="clientdevicename"
							id="clientdevicename"
							value="<%=tgatedevice.getClientdevicename() %>"
							placeholder="请输入终端设备名称" type="text" required>
					</div>
				</div>
				<div class="form-group">
					<!--  col-md-4：表示12列占用4列，可以控制宽度 -->
					<label class="col-md-4 control-label">终端设备编号:</label>
					<div class="col-md-4">
						<input class="form-control" name="clientdeviceid"
							id="clientdeviceid" value="<%=tgatedevice.getClientdeviceid() %>"
							placeholder="请输入终端设备编号" type="text" required>
					</div>
				</div>
				<div class="form-group">
					<!--  col-md-4：表示12列占用4列，可以控制宽度 -->
					<label class="col-md-4 control-label">是否备用:</label>
					<div class="col-md-4">
						<input class="form-control" name="clientdeviceenabled"
							id="clientdeviceenabled"
							value="<%=tgatedevice.getClientdeviceenabled() %>"
							placeholder="‘1’备用，‘0’不备用" type="text" required>
					</div>
				</div>
				<div class="form-group">
					<label class="col-md-4 control-label"></label>
					<div class="col-md-4">
						<button type="submit" class="btn btn-default">保存</button>
						<a class="btn btn-default" href="TgatedeviceServlet">返回</a>
					</div>
				</div>
			</form>
		</div>
	</div>

</div>


<jsp:include page="foot.jsp"></jsp:include>
