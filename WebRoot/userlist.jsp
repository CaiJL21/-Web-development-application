<%@ page language="java" import="java.util.*,iot.bean.*,iot.dao.*"
	pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>userlist.jsp</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

</head>

<body>
	<jsp:include page="head.jsp"></jsp:include>

	<div class="container">
		<ol class="breadcrumb">
			<li><a href="#">Home</a></li>
			<li class="active">用户管理</li>
		</ol>

		<div class="btn-toolbar">
			<a class="btn btn-primary" href="useradd.jsp"> <i
				class="glyphicon glyphicon-plus"></i>添加
			</a>
			<!-- <button class="btn">导入</button>
		<button class="btn">导出</button> -->

			<%
				//取出上回输入的查询条件

				String fieldname = "";
				String keyword = "";

				if (request.getAttribute("fieldname") != null && request.getAttribute("keyword") != null) {
					fieldname = request.getAttribute("fieldname").toString();
					keyword = request.getAttribute("keyword").toString();
				}
			%>
			<!--  form-inline:行内块元素显示，会水平排列 pull-right：向右浮动 -->
			<form action="UserServlet" class="form-inline pull-right"
				name="action">
			
				<div class="form-group">
					<label for="fieldname">选择:</label> <select id="fieldname"
						name="fieldname" class="form-control">
						<option value="id" <%=fieldname.equals("id") ? "selected" : ""%>>用户ID</option>
						<option value="username"
							<%=fieldname.equals("username") ? "selected" : ""%>>用户名</option>
						<option value="usertype"
							<%=fieldname.equals("usertype") ? "selected" : ""%>>用户类型</option>
					</select>
				</div>
				<div class="form-group">
					<input type="text" name="keyword" value="<%=keyword%>" id="keyword"
						class="form-control" placeholder="请输入关键词">
				</div>
				<div class="form-group">
					<button type="submit" class="btn btn-default">查询</button>
				</div>
			</form>


		</div>


		<!-- table-striped：奇偶行颜色不同 -->
		<table class="table table-striped">
			<thead>
				<tr>
					<th>ID</th>
					<th>用户名</th>
					<th>用户类型</th>
					<th>操作</th>
				</tr>
			</thead>
			<tbody>

				<%
					ArrayList<Tuser> users = (ArrayList<Tuser>) request.getAttribute("users");
					if (users != null) {
						for (int i = 0; i < users.size(); i++) {
							Tuser user = users.get(i);
				%>
				<tr>
					<td><%=user.getId()%></td>
					<td><%=user.getUsername()%></td>
					<td><%=user.getUsertype()%></td>
					<td><a href="UserServlet?action=update&id=<%=user.getId()%>"><i
							class="glyphicon glyphicon-pencil"></i></a> <a href="UserServlet?action=delete&id=<%=user.getId()%>"
						onclick="javascript:if(confirm('是否确认要删除？')) location.href='UserServlet?action=delete&id=<%=user.getId()%>'"><i
							class="glyphicon glyphicon-remove"></i></a></td>
				</tr>
				<%
					}
					}
				%>

			</tbody>
		</table>

		<!-- 下面是控制分页控件，必须要是ul元素才行 -->
		<ul id='bp-element'></ul>
		<%
			String msg = "";
			if (request.getAttribute("msg") != null) {
				msg = request.getAttribute("msg").toString();
			}
			if (msg.length() > 0) {
				//显示提示信息
		%>
		<div class="alert alert-info" id="statusbar" role="alert">
			<%=msg%></div>
		<%
			}
		%>


	</div>

	<%
		String currentPage = request.getAttribute("currentPage").toString();
		String total = request.getAttribute("total").toString();
	%>
	<script type="text/javascript">
    $(function(){

        var element = $('#bp-element');
        options = {
            bootstrapMajorVersion:3, //对应的bootstrap版本
            currentPage: <%=currentPage%>, //当前页数，获取从后台传过来的值
            numberOfPages: 5, //每页页数
            totalPages:<%=total%>, //总页数，获取从后台传过来的值
            shouldShowPage:true,//是否显示该按钮
            itemTexts: function (type, page, current) {//设置显示的样式，默认是箭头
                switch (type) {
                    case "first":
                        return "首页";
                    case "prev":
                        return "上一页";
                    case "next":
                        return "下一页";
                    case "last":
                        return "末页";
                    case "page":
                        return page;
                }
            },
            //点击事件
            onPageClicked: function (event, originalEvent, type, page) {
            	
                location.href = "UserServlet?page=" + page +"&fieldname=<%=fieldname%>&keyword=<%=keyword%>";
            }
        };
        element.bootstrapPaginator(options);

});
</script>

	<jsp:include page="foot.jsp"></jsp:include>

</body>
</html>
