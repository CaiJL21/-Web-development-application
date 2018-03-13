package iot.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;

import iot.bean.Json;
import iot.bean.Tgatedevice;
import iot.dao.TgatedeviceDao;

/**
 * Servlet implementation class DevicelistServlet
 */
@WebServlet("/DevicelistServlet")
public class DevicelistServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DevicelistServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Servlet#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 返回json数据
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json; charset=utf-8");
		// 利用Json作为通用输出类，方便前端AJAX调用
		Json j = new Json();
		PrintWriter out = null;
		// JSON.toJSONStringWithDateFormat(object, "yyyy-MM-dd HH:mm:ss");
		try {
			// 统一格式化时间后输出
			String did=request.getParameter("did");
			
			//定义返回数据
			TgatedeviceDao deviceDao=new TgatedeviceDao();
			ArrayList<Tgatedevice> devices =new ArrayList<Tgatedevice>();
			ArrayList<Tgatedevice> devices2 =new ArrayList<Tgatedevice>();
			if(did.equals("1")){
			devices=deviceDao.query("did=1");//条件自己加入,1是温湿度设备
			
			//数据打包
			j.setSuccess(true);
			j.setMsg("获取成功");
			j.setObj(devices);
			}else{
				devices2=deviceDao.query("did=2");//条件自己加入，2是LED灯设备
				
				//数据打包
				j.setSuccess(true);
				j.setMsg("获取成功");
				j.setObj(devices2);
			}
			out = response.getWriter();
			out.write(JSON.toJSONStringWithDateFormat(j, "yyyy-MM-dd HH:mm:ss"));

		} catch (IOException e) {
			j.setSuccess(false);
			e.printStackTrace();
			out = response.getWriter();
			out.write(JSON.toJSONStringWithDateFormat(j, "yyyy-MM-dd HH:mm:ss"));
		} finally {
			if (out != null) {
				out.close();
			}
		}


}

/**
* Initialization of the servlet. <br>
*
* @throws ServletException if an error occurs
*/
public void init() throws ServletException {
// Put your code here
}

}
