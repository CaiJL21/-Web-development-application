<%@ page language="java" import="java.util.*,iot.bean.*"
	pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<jsp:include page="head.jsp"></jsp:include>
<!-- 后面整合到head。jsp -->
<link href="js/bootstrap-switch/css/bootstrap3/bootstrap-switch.min.css"
	rel="stylesheet">
<script src="js/bootstrap-switch/js/bootstrap-switch.min.js"></script>

<script src="js/radialIndicator-master/radialIndicator.min.js"></script>

<style type="text/css">
#led {
	width: 150px;
	height: 150px;
	margin: 5px auto;
	border-radius: 50%;
}

.r {
	background: radial-gradient(#ff0000, #000000);
	box-shadow: 0 0 50px #ff0000;
}

.g {
	background: radial-gradient(#00ff00, #000000);
	box-shadow: 0 0 50px #00ff00;
}

.b {
	background: radial-gradient(#0000ff, #000000);
	box-shadow: 0 0 50px #0000ff;
}
</style>
<script type="text/javascript">

	var socket;

	function send(message) {
		if (!window.WebSocket) {
			return;
		}
		if (socket.readyState == WebSocket.OPEN) {
			socket.send(message);
		} else {
			//alert("WebSocket 连接没有建立成功！");  
			$("#statusbar").attr("class", "alert alert-warning");
			$("#statusbar").text("连接服务器失败！");
		}

	}

	var refreshtimer;

	function refresh() {
		var cmd = {
			"code" : 2,
			"data" : "{\"clientdeviceid\":\"02010001\",\"devicecode\":\"RBGLED\",\"data\":\"\"}"
		};
		send(JSON.stringify(cmd));//发送json格式数据cmd
		cmd = {
			"code" : 2,
			"data" : "{\"clientdeviceid\":\"01010001\",\"devicecode\":\"DHT11\",\"data\":\"\"}"
		};
		send(JSON.stringify(cmd));
	}

	var humiindicator;
	var tempindicator


	$(document).ready(function() {
	$.ajax({
        type: "post",
        data: {
      	  did:1,//设备id，目前用预设的编号
        },
        url: "DevicelistServlet",
        success: function (data) {
           
           for(var i=0;i<data.obj.length;i++){
        	   console.log(data.obj[i].clientdevicename);
        		$("#dhtid").append(" <option value='"+data.obj[i].clientdeviceid+"'>"+data.obj[i].clientdevicename+"</option>");
           }
        }
    });
	$.ajax({
        type: "post",
        data: {
      	  did:2,//设备id，目前用预设的编号
        },
        url: "DevicelistServlet",
        success: function (data) {
           
           for(var i=0;i<data.obj.length;i++){
        	   console.log(data.obj[i].clientdevicename);
        		$("#ledid").append(" <option value='"+data.obj[i].clientdeviceid+"'>"+data.obj[i].clientdevicename+"</option>");
        	   
           }
        }
    });
	
	
	
	
		$('#ledswitch').bootstrapSwitch();

		$('#ledswitch').on('switchChange.bootstrapSwitch', function(event, state) {
			//alert(state);  
		});

		//$('#ledswitch').bootstrapSwitch('state',true);



		//radialIndicator.js轻量级圆形进度插件，根据不同的进度改变颜色






		$('#humi').radialIndicator({
			barColor : {
				0 : '#00FF00',
				100 : '#FF0000',
			},
			barWidth : 10,
			initValue : 40,
			roundCorner : true,
			percentage : true,
			interpolate : true,
		});

		humiindicator = $('#humi').data('radialIndicator');

		//console.log(progObj.current_value);

		//console.log(progObj);

		//progObj.animate(80);

		$('#temp').radialIndicator({
			barColor : "#87CEEB", //天蓝色
			barWidth : 10,
			initValue : 140,
			roundCorner : true,
			minValue : -100, //指定范围大小
			maxValue : 200,
			interpolate : true,
		});

		tempindicator = $('#temp').data('radialIndicator');


		if (!window.WebSocket) {
			window.WebSocket = window.MozWebSocket;
		}
		if (window.WebSocket) {
			socket = new WebSocket("ws://127.0.0.1:7397/websocket"); // 打开一个 web socket
			socket.onmessage = function(event) {
				//var ta = document.getElementById('responseText');  
				//ta.value += event.data+"\r\n";  
				var message = $.parseJSON(event.data);
				switch (message.code) {
				case 2:
					//console.log(message.data);
					var device = $.parseJSON(message.data);
					if (device.devicecode == "RGBLED") {

						//console.log(device.data);
						var rgbled = $.parseJSON(device.data);
						if (rgbled.power == "on") {
							$('#ledswitch').bootstrapSwitch('state', true);
						} else {
							$('#ledswitch').bootstrapSwitch('state', false);
						}
						//console.log(rgbled.color);
						$("#led").attr("class", rgbled.color);


					} else if (device.devicecode == "DHT11") {

						var dth11 = $.parseJSON(device.data);
						humiindicator.value(parseInt(dth11.humi));
						tempindicator.value(parseInt(dth11.temp));
						if(parseInt(dth11.temp)>30){
											var bj=document.getElementById("bjsound");
										    bj.play();
										}

						//记录历史温湿度
						$.ajax({
							type : "post",
							data : {
								gdid :$("#dhtid").val(), //设备id，目前用预设的编号
								recorddata : device.data, //直接存入字符串数据，方便以后扩展不用的传感器数据
							},
							url : "ThistoryDataServlet?action=add", //自己填入servlet地址
							success : function(data) {
								//console.log(data);返回JSON数据
							}
						});



	


					} else {

					}

					break;
				case 3:
					break;
				default:
					break;
				}
			};
			socket.onopen = function(event) {
				//var ta = document.getElementById('responseText');  
				//ta.value = "这里显示服务器推送信息"+"\r\n";
				$("#statusbar").attr("class", "alert alert-info");

				$("#statusbar").text("连接服务器成功！");
				refreshtimer = setInterval(refresh, 5000); // 注意函数名没有引号和括弧！								

			};
			socket.onclose = function(event) {
				//var ta = document.getElementById('responseText');  
				//ta.value = "";  
				//ta.value = "WebSocket 关闭"+"\r\n";  
				$("#statusbar").attr("class", "alert alert-warning");
				$("#statusbar").text("连接服务器失败！");
			};
		} else {

			alert("您的浏览器不支持WebSocket协议！");
		}

		$("#ledr").click(function() {
			var cmd = {
				"code" : 3,
				"data" : "{\"clientdeviceid\":\"02010001\",\"devicecode\":\"RGBLED\",\"data\":\"{\\\"power\\\":\\\"on\\\",\\\"color\\\":\\\"r\\\"}\"}"
			};
			send(JSON.stringify(cmd));
		});

		$("#ledg").click(function() {
			var cmd = {
				"code" : 3,
				"data" : "{\"clientdeviceid\":\"02010001\",\"devicecode\":\"RGBLED\",\"data\":\"{\\\"power\\\":\\\"on\\\",\\\"color\\\":\\\"g\\\"}\"}"
			};
			send(JSON.stringify(cmd));
		});

		$("#ledb").click(function() {
			var cmd = {
				"code" : 3,
				"data" : "{\"clientdeviceid\":\"02010001\",\"devicecode\":\"RGBLED\",\"data\":\"{\\\"power\\\":\\\"on\\\",\\\"color\\\":\\\"b\\\"}\"}"
			};
			send(JSON.stringify(cmd));
		});


	});
</script>
<audio id="bjsound" src="images/alarm.mp3" ></audio>    
<div class="container">
	<ol class="breadcrumb">
		<li><a href="#">Home</a></li>
		<li class="active">实时监控</li>
	</ol>
	<div class="row">
      <div class="col-md-6" >
           <div class="form-group">
				<label for="fieldname">选择LED网关设备:</label> 
				<select class="form-control" id="ledid" name="gdid" >
				
				</select>
			</div>
      </div>
      <div class="col-md-6" >
           <div class="form-group">
				<label for="fieldname">选择DHT11网关设备:</label> 
				<select class="form-control" id="dhtid" name="gdid" >
				
				</select>
			</div>
      </div>
    </div>          

	<div class="col-md-6">
		<div class="panel panel-primary">
			<div class="panel-heading">
				<h3 class="panel-title  text-center">3色LED灯监控</h3>
			</div>
			<div class="panel-body">

				<div class="col-md-8  text-center">
					<div id="led" class="g"></div>

				</div>
				<div class="col-md-4  text-center">
					<div class="btn-group-vertical">
						<div class="switch" data-on="danger" data-off="primary">
							<input id="ledswitch" type="checkbox" checked />
						</div>
						<button type="button" id="ledr" class="btn btn-danger">红色</button>
						<button type="button" id="ledg" class="btn btn-success">绿色</button>
						<button type="button" id="ledb" class="btn btn-primary">蓝色</button>
					</div>

				</div>
			</div>
		</div>
	</div>
	<div class="col-md-6">
		<div class="panel panel-primary">
			<div class="panel-heading">
				<h3 class="panel-title  text-center">温湿度监控</h3>
			</div>
			<div class="panel-body">
				<div class="col-md-6 text-center">
					<div id="temp"></div>
					<h5>温度</h5>
				</div>
				<div class="col-md-6 text-center">
					<div id="humi"></div>
					<h5>湿度</h5>
				</div>

			</div>
		</div>
	</div>

</div>

<div class="container">
	<div class="alert alert-info" id="statusbar" role="alert"></div>
</div>

<jsp:include page="foot.jsp"></jsp:include>





























