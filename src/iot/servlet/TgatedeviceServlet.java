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

import iot.bean.Tgatedevice;
import iot.dao.TgatedeviceDao;
import iot.utils.CodeExchange;

/**
 * Servlet implementation class TgatedeviceServlet
 */
@WebServlet("/TgatedeviceServlet")
public class TgatedeviceServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TgatedeviceServlet() {
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

		//有删除、添加和修改传递action参数
				String action=request.getParameter("action");
				if(action!=null){
					if(action.equals("delete")){
						String id=request.getParameter("id");
						if(id!=null&&id.length()>0){
							TgatedeviceDao tgatedeviceDao=new TgatedeviceDao();
							if(tgatedeviceDao.delete(id)){
								request.setAttribute("msg", "删除成功！");
							}else{
								request.setAttribute("msg", "删除失败！");
							}					
							
						}else{
							response.sendRedirect("error.jsp");//设计一个错误页面来显示
							return;
						}
					}else if(action.equals("add")){
						Tgatedevice tgatedevice2=new Tgatedevice();
						tgatedevice2.setGid(Long.parseLong(request.getParameter("gid")));
						tgatedevice2.setDid(Long.parseLong(request.getParameter("did")));
						tgatedevice2.setClientdevicename(CodeExchange.chinese(request.getParameter("clientdevicename")));
						tgatedevice2.setClientdeviceid(CodeExchange.chinese(request.getParameter("clientdeviceid")));
						tgatedevice2.setClientdeviceenabled(CodeExchange.chinese(request.getParameter("clientdeviceenabled")));
						tgatedevice2.setAddtime(new Timestamp((new Date()).getTime()));
						TgatedeviceDao tgatedeviceDao=new TgatedeviceDao();
						//这里还可以进一步查询用户名是否存在，避免用户名重复
						if(tgatedeviceDao.add(tgatedevice2)){
							request.setAttribute("msg", "添加成功！");
						}else{
							request.setAttribute("msg", "添加失败！");
						}
						
						
					}else if(action.equals("update")){
						
						String id=request.getParameter("id");
						if(id!=null&&id.length()>0){
							TgatedeviceDao tgatedeviceDao=new TgatedeviceDao();
							ArrayList<Tgatedevice> tgatedevices=	tgatedeviceDao.query(" id=" + id);
							if(tgatedevices!=null && tgatedevices.size()>0){
								Tgatedevice tgatedevice2=tgatedevices.get(0);//获取用户信息
								request.setAttribute("tgatedevice", tgatedevice2);
								//转向修改页面编辑信息
								request.getRequestDispatcher("Tgatedeviceedit.jsp").forward(request, response);
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
						Tgatedevice tgatedevice2=new Tgatedevice();
						tgatedevice2.setGid(Long.parseLong(request.getParameter("gid")));
						tgatedevice2.setDid(Long.parseLong(request.getParameter("did")));
						tgatedevice2.setClientdevicename(CodeExchange.chinese(request.getParameter("clientdevicename")));
						tgatedevice2.setClientdeviceid(CodeExchange.chinese(request.getParameter("clientdeviceid")));
						tgatedevice2.setClientdeviceenabled(CodeExchange.chinese(request.getParameter("clientdeviceenabled")));
						tgatedevice2.setId(Long.parseLong(request.getParameter("id")));
						TgatedeviceDao tgatedeviceDao=new TgatedeviceDao();
						//这里还可以进一步查询用户名是否存在，避免用户名重复
						if(tgatedeviceDao.add(tgatedevice2)){
							request.setAttribute("msg", "修改成功！");
						}else{
							request.setAttribute("msg", "添加失败！");
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
				TgatedeviceDao tgatedeviceDao=new TgatedeviceDao();
				ArrayList<Tgatedevice> users =new ArrayList<Tgatedevice>();		
				users = tgatedeviceDao.queryPage(whereSQL,Integer.parseInt(page));
				request.setAttribute("tgatedevices", users);
				
				int total=tgatedeviceDao.count(whereSQL);
				request.setAttribute("total", total/tgatedeviceDao.PAGE_LENGTH + 1);//计算总页数
				
				request.setAttribute("currentPage", Integer.parseInt(page));
				request.getRequestDispatcher("Tgatedevicelist.jsp").forward(request, response);
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
