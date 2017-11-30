package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import javaBeans.Reply;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAOs.ReplyDAO;
import DAOs.UserDAO;

public class replyServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor of the object.
	 */
	public replyServlet() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
		out.println("<HTML>");
		out.println("  <HEAD><TITLE>A Servlet</TITLE></HEAD>");
		out.println("  <BODY>");
		out.print("    This is ");
		out.print(this.getClass());
		out.println(", using the GET method");
		out.println("  </BODY>");
		out.println("</HTML>");
		out.flush();
		out.close();
	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		
        String isReply="false";
        String sub=request.getParameter("submit");
		
		if(sub.equals("取消")){
			request.setAttribute("isReply", isReply);
		    RequestDispatcher rd=request.getRequestDispatcher("/tip.jsp");
		    rd.forward(request, response);
		}
		
		if(sub.equals("发表")){
			String title=request.getParameter("title");
			String content=request.getParameter("content");
			Date time=new Date();
		    SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		    String publishTime=sdf.format(time);
		    int tipId=0;
		    String name=request.getParameter("name");
		    UserDAO udao=new UserDAO();
		    int userId=udao.getUserIdByUserName(name);
		    if(request.getParameter("id")!=null)tipId=Integer.parseInt(request.getParameter("id"));
		    boolean isSuccess=false;
		    String message="";
		    ReplyDAO rdao=new ReplyDAO();
		    
		    if(title.equals("")||title.length()>100) message="标题不能为空且超过100个字！";
			else if(content.equals("")||content.length()>1000) message="正文不能为空且超过1000个字！";
			else isSuccess=true;
		    
		    if(!isSuccess){
				request.setAttribute("message", message);
				isReply="true";
				request.setAttribute("isReply", isReply);
				RequestDispatcher rd=request.getRequestDispatcher("/tip.jsp");
				rd.forward(request, response);
		    }
		    else{
		    	Reply reply=new Reply(title,content,publishTime,userId,tipId);
		    	rdao.addReply(reply);
		    	request.setAttribute("isReply", isReply);
		    	RequestDispatcher rd=request.getRequestDispatcher("/tip.jsp");
				rd.forward(request, response);
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
