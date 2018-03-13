package iot.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;

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
import iot.bean.Thistorydata;
import iot.dao.RecorddataDao;
import iot.dao.TgatedeviceDao;
import iot.utils.CodeExchange;

/**
 * Servlet implementation class HistoryServlet
 */
@WebServlet("/HistoryServlet")
public class HistoryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HistoryServlet() {
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
					String action = request.getParameter("action");
					if (action != null) {
						if(action.equals("add")) {
						Thistorydata real = new Thistorydata();
						real.setGdid(Long.parseLong(request.getParameter("gdid")));
						real.setRecorddata(CodeExchange.chinese(request
								.getParameter("recorddata")));
						real.setRecordtime(new Timestamp((new Date()).getTime()));
				        RecorddataDao historyDao = new RecorddataDao();
						// 这里还可以进一步查询用户名是否存在，避免用户名重复
						if (historyDao.add(real)) {
							request.setAttribute("msg", "添加成功！");
						} else {
							request.setAttribute("msg", "添加失败！");
						}
						}
						
						else if(action.equals("query")){
					
				
					TgatedeviceDao deviceDao=new TgatedeviceDao();
					ArrayList<Tgatedevice> devices =new ArrayList<Tgatedevice>();
		devices=deviceDao.query("gid=1");
		request.setAttribute("gatedevices", devices);
		request.getRequestDispatcher("history.jsp").forward(request, response);
		return;
				
					
					
					}
					out = response.getWriter();
					out.write(JSON.toJSONStringWithDateFormat(j, "yyyy-MM-dd HH:mm:ss"));

				}
				}
				catch (IOException e) {
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