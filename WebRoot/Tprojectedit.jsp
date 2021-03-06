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
          alert("欢迎管理员对监控项目进行修改操作！");     
    </script>
<% 
}
%>
<%
if(session.getAttribute("flag").equals("2")){
%>
<script type="text/javascript">        
          alert("很抱歉！普通用户不能对监控项目进行修改操作！");   
          location.href="TprojectServlet";  
    </script>
<% 
}
%>
<div class="container">
	<ol class="breadcrumb">
		<li><a href="head.jsp">Home</a></li>
		<li class="active">编辑用户</li>
	</ol>

	<div class="panel panel-default">
		<div class="panel-heading">编辑项目信息</div>
		<div class="panel-body">
			<jsp:useBean id="project" class="iot.bean.Tproject" scope="request">
				<jsp:setProperty name="project" property="*" />
			</jsp:useBean>
			<!-- form-horizontal：水平排列  ?action=add 带参数时 必须是post-->
			<form class="form-horizontal"
				action="ProjectServlet?action=updateSave" method="post" role="form">
				<div class="form-group">
					<!--  col-md-4：表示12列占用4列，可以控制宽度 -->
					<label class="col-md-4 control-label">项目名称:</label>
					<div class="col-md-4">
						<input class="form-control" name="projectname" id="projectname"
							value="<%=project.getProjectname() %>" placeholder="请输入项目名称"
							type="text" required>
						<!-- hidden是为了传递不可见的参数 -->
						<input type="hidden" name="id" value="<%=project.getId() %>"
							id="id">
					</div>
				</div>
				<div class="form-group">
					<label class="col-md-4 control-label">监控项目id：</label>
					<div class="col-md-4">
						<input class="form-control" name="uid" id="uid"
							placeholder="请输入监控项目id" value="<%=project.getUid() %>"
							type="text" required>
					</div>
				</div>

				<div class="form-group">
					<label class="col-md-4 control-label"></label>
					<div class="col-md-4">
						<button type="submit" class="btn btn-default">保存</button>
						<a class="btn btn-default" href="TprojectServlet">返回</a>
					</div>
				</div>
			</form>
		</div>
	</div>

</div>


<jsp:include page="foot.jsp"></jsp:include>
