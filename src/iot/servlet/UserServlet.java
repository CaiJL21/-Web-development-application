package iot.servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import iot.bean.Tuser;
import iot.dao.UserDao;
import iot.utils.CodeExchange;

/**
 * Servlet implementation class UserServlet
 */
@WebServlet("/UserServlet")
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserServlet() {
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
		super.destroy(); // Just puts "destroy" string in log
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
				String action=request.getParameter("action");//通过传递action的参数值来决定执行哪个动作
				if(action!=null){
					if(action.equals("delete")){
						String id=request.getParameter("id");
						if(id!=null&&id.length()>0){
							UserDao userDao=new UserDao();
							if(userDao.delete(id)){
								request.setAttribute("msg", "删除成功！");
							}else{
								request.setAttribute("msg", "删除失败！");
							}					
							
						}else{
							response.sendRedirect("error.jsp");//设计一个错误页面来显示
							return;
						}
					}else if(action.equals("add")){
						Tuser user=new Tuser();
						user.setUsername(CodeExchange.chinese(request.getParameter("username")));
						user.setPassword(CodeExchange.chinese(request.getParameter("password")));
						user.setUsertype(CodeExchange.chinese(request.getParameter("usertype")));
						UserDao userDao=new UserDao();
						//这里还可以进一步查询用户名是否存在，避免用户名重复
						if(userDao.add(user)){
							request.setAttribute("msg", "添加成功！");
						}else{
							request.setAttribute("msg", "添加失败！");
						}
						
						
					}else if(action.equals("update")){
						
						String id=request.getParameter("id");
						if(id!=null&&id.length()>0){
							UserDao userDao=new UserDao();
							ArrayList<Tuser> users=	userDao.query(" id=" + id);
							if(users!=null && users.size()>0){
								Tuser user=users.get(0);//获取用户信息
								request.setAttribute("user", user);
								//转向修改页面编辑信息
								request.getRequestDispatcher("useredit.jsp").forward(request, response);
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
						
						Tuser user=new Tuser();
						user.setUsername(CodeExchange.chinese(request.getParameter("username")));
						user.setPassword(CodeExchange.chinese(request.getParameter("password")));
						user.setUsertype(CodeExchange.chinese(request.getParameter("usertype")));
						//这里还可以进一步校验id的合法性
						user.setId(Long.parseLong(request.getParameter("id")));
						
						UserDao userDao=new UserDao();
						//这里还可以进一步查询用户名是否存在，避免用户名重复
						if(userDao.update(user)){
							request.setAttribute("msg", "修改成功！");
						}else{
							request.setAttribute("msg", "修改失败！");
						}
						
					}else if(action.equals("login")){
						
						Tuser user=new Tuser();
						user.setUsername(CodeExchange.chinese(request.getParameter("username")));
						user.setPassword(CodeExchange.chinese(request.getParameter("password")));
						
						UserDao userDao=new UserDao();
						ArrayList<Tuser> users=	userDao.query(" username='" + user.getUsername() +"' and password='"+user.getPassword()+"'");
						if(users!=null && users.size()>0){
							user=users.get(0);//获取用户信息
							HttpSession session = request.getSession();
							//用户登录信息存到session
							session.setAttribute("loginusername", user.getUsername());
							session.setAttribute("loginusertype", user.getUsertype());
							//转向修改页面编辑信息
							request.getRequestDispatcher("index.jsp").forward(request, response);
							return;
						}else{
							request.setAttribute("msg", "登录失败！");
							request.getRequestDispatcher("login.jsp").forward(request, response);
							return;
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
				UserDao userDao=new UserDao();
				ArrayList<Tuser> users =new ArrayList<Tuser>();		
				users = userDao.queryPage(whereSQL,Integer.parseInt(page));
				request.setAttribute("users", users);
				
				int total=userDao.count(whereSQL);
				request.setAttribute("total", total/userDao.PAGE_LENGTH + 1);//计算总页数
				
				request.setAttribute("currentPage", Integer.parseInt(page));
				request.getRequestDispatcher("userlist.jsp").forward(request, response);

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

