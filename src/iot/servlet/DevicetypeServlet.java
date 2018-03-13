package iot.servlet;

import java.io.IOException;
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

import iot.bean.tdevicetype;
import iot.dao.TdevicetypeDao;
import iot.utils.CodeExchange;

/**
 * Servlet implementation class DevicetypeServlet
 */
@WebServlet("/DevicetypeServlet")
public class DevicetypeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DevicetypeServlet() {
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
		// TODO Auto-generated method stub
		this.doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String action=request.getParameter("action");
		if(action!=null){
			if(action.equals("delete")){
				String id=request.getParameter("id");
				if(id!=null&&id.length()>0){
					TdevicetypeDao devicetDao=new TdevicetypeDao();
					if(devicetDao.delete(id)){
						request.setAttribute("msg", "删除成功！");
					}else{
						request.setAttribute("msg", "删除失败！");
					}					
					
				}else{
					response.sendRedirect("error.jsp");//设计一个错误页面来显示
					return;
				}
			}else if(action.equals("add")){
				tdevicetype devicetype=new tdevicetype();
				devicetype.setDevicetypecode(CodeExchange.chinese(request.getParameter("devicetypecode")));
				devicetype.setDevicetypename(CodeExchange.chinese(request.getParameter("devicetypename")));
				devicetype.setAddtime(new Timestamp((new Date()).getTime()));
				
				TdevicetypeDao devicetDao=new TdevicetypeDao();
				//这里还可以进一步查询用户名是否存在，避免用户名重复
				if(devicetDao.add(devicetype)){
					request.setAttribute("msg", "添加成功！");
				}else{
					request.setAttribute("msg", "添加失败！");
				}
				
				
			}else if(action.equals("update")){
				
				String id=request.getParameter("id");
				if(id!=null&&id.length()>0){
					TdevicetypeDao devicetDao=new TdevicetypeDao();
					ArrayList<tdevicetype> devicetypes=devicetDao.query(" id=" + id);
					if(devicetypes!=null && devicetypes.size()>0){
						tdevicetype devicetype=devicetypes.get(0);//获取用户信息
						request.setAttribute("devicetype", devicetype);
						//转向修改页面编辑信息
						request.getRequestDispatcher("tdevicetypeedit.jsp").forward(request, response);
						return;
					}else{
						response.sendRedirect("error.jsp");//设计一个错误页面来显示
						return;
					}
					
				}else{
					response.sendRedirect("error.jsp");//设计一个错误页面来显示
					return;
				}
				
			}else if(action.equals("updateSave")){
				
				tdevicetype devicetype=new tdevicetype();
				devicetype.setDevicetypecode(CodeExchange.chinese(request.getParameter("devicetypecode")));
				devicetype.setDevicetypename(CodeExchange.chinese(request.getParameter("devicetypename")));
				devicetype.setAddtime(new Timestamp((new Date()).getTime()));
				//这里还可以进一步校验id的合法性
				devicetype.setId(Long.parseLong(request.getParameter("id")));
				
				TdevicetypeDao devicetDao=new TdevicetypeDao();
				//这里还可以进一步查询用户名是否存在，避免用户名重复
				if(devicetDao.update(devicetype)){
					request.setAttribute("msg", "修改成功！");
				}else{
					request.setAttribute("msg", "修改失败！");
				}
				
			} 
			else{
				//不能识别动作
				response.sendRedirect("error.jsp");//设计一个错误页面来显示
				return;
			}
		}	
		
		//查询条件
		String whereSQL="";
		String keyword=CodeExchange.chinese(request.getParameter("keyword"));
		String fieldname=request.getParameter("fieldname");
		if(keyword!=null&&fieldname!=null&&fieldname.length()>0&&keyword.length()>0){
			//id为整型
			if(fieldname.equals("id")){
				whereSQL=fieldname+"="+keyword;
			}
			//其他为字符串型，要加入单引号
			else
			{
				whereSQL=fieldname+" like '%"+keyword+"%' ";
			}
			//回传给页面显示查询条件，否则会清空
			request.setAttribute("fieldname", fieldname);
			request.setAttribute("keyword", keyword);
			
		}

		//查询页码
		String page=request.getParameter("page");
		if(page==null){
			page="1";
		}
		TdevicetypeDao devicetDao=new TdevicetypeDao();
		ArrayList<tdevicetype> devicetypes =new ArrayList<tdevicetype>();		
		devicetypes = devicetDao.queryPage(whereSQL,Integer.parseInt(page));
		request.setAttribute("devicetypes", devicetypes);
		
		int total=devicetDao.count(whereSQL);
		request.setAttribute("total", total/devicetDao.PAGE_LENGTH + 1);//计算总页数
		
		request.setAttribute("currentPage", Integer.parseInt(page));
		request.getRequestDispatcher("tdevicetypelist.jsp").forward(request, response);

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