# 物联网的简单应用开发 #
 (写于2018年03月14日)
 
## 整体思路 ##
基于MVC三层模式设计物联网监控平台的管理，监控，记录数据三大模块，具体实验要求在文档里面可见。

## 工具 ##
本次实验采用Myeclipse[链接](https://pan.baidu.com/s/11_xLsO8SDQze7e2dQTR-zA 密码：upux) 作为开发工具，数据库使用mysql，可视化工具即navicate，采用JDBC技术，[driver](链接：https://pan.baidu.com/s/1UKnIfMcEwjGmRhePxhUx-A 密码：ljo6)，均是永久可下载。

本次实验采用JSP+JavaBean+Sevlet对于数据库的数据进行增删改查，即用户的管理，监控设备的实时监控，数据的展示。
1. 页面的显示使用[bootstrap](https://getbootstrap.com/)自适应屏幕;
2. 数据的记录方面采用[fastjson](https://github.com/alibaba/fastjson)框架，后台返回json数据包，Ajax异步传输调用，定义传输格式(humi: , temp:),温湿度的数据记录;(实验采用模拟服务器模拟数据，即**runserver.bat**和**runsimulator.bat**,可以改变端口号或者网关号来对应不同的设备监控)
3. 在历史数据的显示中采用[Echarts](http://echarts.baidu.com/examples/index.html#chart-type-globe)这个前端数据可视化工具。
  
![](https://raw.githubusercontent.com/CaiJL21/-Web-development-application/master/images/TIM%E6%88%AA%E5%9B%BE20180314011300.png)



### 最后 ###
> 敢于第一个吃螃蟹的人，都是有大毅力和勇气之人。


参考文献： [简书](https://www.jianshu.com/p/q81RER#)   [Cmd markdown使用说明](https://www.zybuluo.com/mdeditor?url=https%3A%2F%2Fwww.zybuluo.com%2Fstatic%2Feditor%2Fmd-help.markdown)


 初次尝试，欢迎指正！




