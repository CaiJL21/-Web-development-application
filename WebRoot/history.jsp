<%@ page language="java" import="java.util.*,iot.bean.*,iot.dao.*"
	pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<jsp:include page="head.jsp"></jsp:include>
<div class="container">
	<ol class="breadcrumb">
		<li><a href="login.jsp">Home</a></li>
		<li class="active">历史数据</li>
	</ol>
</div>

<div class="container">
	<div class="row">
		<div class="col-md-3">
			<label for="exampleInputName">选择网关设备</label> <select id="gdid"
				name="gdid" class="form-control">
				<%
					ArrayList<Tgatedevice> Machines = (ArrayList<Tgatedevice>) request.getAttribute("gatedevices");
					if (Machines != null) {
						for (int i = 0; i < Machines.size(); i++) {
							Tgatedevice devicetype = Machines.get(i);
				%>
				<option value="<%=devicetype.getClientdeviceid()%>"><%=devicetype.getClientdevicename()%></option>
				<%
					}
					}
				%>
			</select>
		</div>
		<div class="col-md-3">
			<label for="exampleInputEmail">开始时间</label> <input
				class="form-control" name="starttime" id="starttime"
				type="datetime-local">
		</div>
		<div class="col-md-3">
			<label for="exampleInputEmail">结束时间</label> <input
				class="form-control" name="endtime" id="endtime"
				type="datetime-local">
		</div>
		<div class="col-md-3">
			<br />
			<button class="btn btn-default" id="search">查询</button>
		</div>
	</div>
</div>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title></title>
</head>

<body>


	<div class="row">
		<div id="container"
			style="min-width: 450px; height: 400px; margin: 0 auto;overflow:auto;"></div>
	</div>



	<script src="js/jquery-1.11.3.min.js"></script>
	<script src="js/echarts.common.min.js"></script>

	<script type="text/javascript">
	
	
		$(function() {
	
			// 指定图表的配置项和数据
			var myChart = echarts.init(document.getElementById('container'));
	
			$("#search").click(function() {
				$.ajax({
					type : "post",
					data : {
						starttime : $("#starttime").val(),
						endtime : $("#endtime").val(),
						gdid : $("#gdid").val()
					},
					url : "ThistoryDataServlet?action=query",
					success : function(data) {
						if (data.success) {
	
	
							console.log(data);
							var timedata = [];
							var tempdata = [];
							var humidata = [];
	
							for (var i = 0; i < data.obj.length; i++) {
								var d = data.obj[i];
								timedata.push(d.recordtime);
								var rd = $.parseJSON(d.recorddata);
								tempdata.push(rd.temp);
								humidata.push(rd.humi);
							}
	
							//参数设置
	
							option = {
    title : {
        text: '温湿度数据展示',
        subtext: '纯属虚构'
    },
    tooltip : {
        trigger: 'axis'
    },
    legend: {
        data:['温度','湿度']
    },
    toolbox: {
        show : true,
        feature : {
            mark : {show: true},
            dataView : {show: true, readOnly: false},
            magicType : {show: true, type: ['line', 'bar', 'stack', 'tiled']},
            restore : {show: true},
            saveAsImage : {show: true}
        }
    },
    calculable : true,
    xAxis : [
        {
            type : 'category',
            boundaryGap : false,
            data : timedata//['周一','周二','周三','周四','周五','周六','周日']
        }
    ],
    yAxis : [
        {
            type : 'value'
        }
    ],
    series : [
        {
            name:'温度',
            type:'line',
            smooth:true,
            itemStyle: {normal: {areaStyle: {type: 'default'}}},
            data:tempdata//[10, 12, 21, 54, 260, 830, 710]
        },
        {
            name:'湿度',
            type:'line',
            smooth:true,
            itemStyle: {normal: {areaStyle: {type: 'default'}}},
            data:humidata//[1320, 1132, 601, 234, 120, 90, 20]
        }
    ]
};
                    
							myChart.setOption(option); //参数设置方法   
	
						}
					},
	
					error : function(result) {
						alert("服务器异常，请稍后重试-100");
					}
				});
			});
		});
	</script>