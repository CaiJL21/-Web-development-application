package iot.servlet;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import iot.bean.tdevice;
import iot.dao.DeviceDao;
import iot.utils.CodeExchange;

/**
 * Servlet implementation class DeviceServlet
 */
@WebServlet("/DeviceServlet")
public class DeviceServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeviceServlet() {
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
		DiskFileItemFactory factory = new DiskFileItemFactory();
		ServletFileUpload upload = new ServletFileUpload(factory);
		upload.setHeaderEncoding("ISO-8859-1");
		tdevice device=new tdevice();
		ArrayList<FileItem> items = null;
		String action=request.getParameter("action");
		if(action!=null){
			if(action.equals("delete")){
				String id=request.getParameter("id");
				if(id!=null&&id.length()>0){
					DeviceDao DeviceDao=new DeviceDao();
					if(DeviceDao.delete(id)){
						request.setAttribute("msg", "删除成功！");
					}else{
						request.setAttribute("msg", "删除失败！");
					}					
					
				}else{
					response.sendRedirect("error.jsp");//设计一个错误页面来显示
					return;
				}
			}else if(action.equals("add")){
				
		try 
		{
			items = (ArrayList<FileItem>) upload.parseRequest(request);
		} 
		catch (FileUploadException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		Iterator<FileItem> it = items.iterator();
		while (it.hasNext()) 
		{
		    FileItem tempitem = it.next();//取得表单中的一个元素
		    String itemName = tempitem.getFieldName();//取得input标签的name属性值
		    if (tempitem.isFormField()) 
		    {
		    	//判断这个元素type是否为text，FCKediter也返回true
		    	String content = CodeExchange.chinese(tempitem.getString());
			    if (itemName.equals("dtid")) 
			    {
			     	device.setDtid(Long.parseLong(content));
			    }
			    if (itemName.equals("devicename")) 
			    {
			    	device.setDevicename(content);
			    }
			    if (itemName.equals("devicecode")) 
			    {
			    	device.setDevicecode(content);
			    }				    	
		    	
		    }
		    else 
		    {
		    	//type是file，上传的文件
		    	File tempfile= new File(request.getSession().getServletContext().getRealPath("/")+"photos\\"+new File(CodeExchange.chinese(tempitem.getName())).getName());
		    	try
		    	{
		    		tempitem.write(tempfile);
		    	} 
		    	catch (Exception e) 
		    	{
		    		// TODO Auto-generated catch block
		    		e.printStackTrace();
		    	}
		    	device.setDevicephoto("photos/"+tempfile.getCanonicalPath().substring(tempfile.getCanonicalPath().lastIndexOf("\\")+1));
		    }
		}
		DeviceDao Devicedao=new DeviceDao();
		if(Devicedao.add(device)){
			request.setAttribute("msg", "添加成功！");
		}else{
			request.setAttribute("msg", "添加失败！");
		}
}else if(action.equals("update")){
				
				String id=request.getParameter("id");
				if(id!=null&&id.length()>0){
					DeviceDao DeviceDao=new DeviceDao();
					ArrayList<tdevice> devices=	DeviceDao.query(" id=" + id);
					if(devices!=null && devices.size()>0){
						tdevice Device=devices.get(0);//获取用户信息
						request.setAttribute("Device",Device);
						//转向修改页面编辑信息
						request.getRequestDispatcher("deviceedit.jsp").forward(request, response);
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
				
				tdevice Device=new tdevice();
				try 
				{
					items = (ArrayList<FileItem>) upload.parseRequest(request);
				} 
				catch (FileUploadException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				Iterator<FileItem> it = items.iterator();
				while (it.hasNext()) 
				{
				    FileItem tempitem = it.next();//取得表单中的一个元素
				    String itemName = tempitem.getFieldName();//取得input标签的name属性值
				    if (tempitem.isFormField()) 
				    {
				    	//判断这个元素type是否为text，FCKediter也返回true
				    	String content = CodeExchange.chinese(tempitem.getString());
				    	if (itemName.equals("id")) 
					    {
				    		Device.setId(Long.parseLong(content));
					    }
					    if (itemName.equals("devicename")) 
					    {
					    	Device.setDevicename(content);
					    }
					    if (itemName.equals("devicecode")) 
					    {
					    	Device.setDevicecode(content);
					    }				    	
				    	
				    }
				    else 
				    {
				    	//type是file，上传的文件
				    	File tempfile= new File(request.getSession().getServletContext().getRealPath("/")+"photos\\"+new File(CodeExchange.chinese(tempitem.getName())).getName());
				    	try
				    	{
				    		tempitem.write(tempfile);
				    	} 
				    	catch (Exception e) 
				    	{
				    		// TODO Auto-generated catch block
				    		e.printStackTrace();
				    	}
				    	Device.setDevicephoto("photos/"+tempfile.getCanonicalPath().substring(tempfile.getCanonicalPath().lastIndexOf("\\")+1));
				    }
				}
				DeviceDao devicedao=new DeviceDao();
				//这里还可以进一步查询用户名是否存在，避免用户名重复
				if(devicedao.update(Device)){
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
		DeviceDao devicedao=new DeviceDao();
		ArrayList<tdevice> devices =new ArrayList<tdevice>();		
		devices = devicedao.queryPage(whereSQL,Integer.parseInt(page));
		request.setAttribute("devices", devices);
		
		int total=devicedao.count(whereSQL);
		request.setAttribute("total", total/devicedao.PAGE_LENGTH + 1);//计算总页数
		
		request.setAttribute("currentPage", Integer.parseInt(page));
		request.getRequestDispatcher("devicelist.jsp").forward(request, response);
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
