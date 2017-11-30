package servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAOs.TipDAO;
import DAOs.UserDAO;

public class deleteTipServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor of the object.
	 */
	public deleteTipServlet() {
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
		PrintWriter out=response.getWriter();
		
		String name=request.getParameter("name");
		String tipId=request.getParameter("id");
		int id=0;
		UserDAO udao=new UserDAO();
		TipDAO tdao=new TipDAO();
		boolean isSuccess=false;
		String message="";
		
		if(!tipId.equals("")) id=Integer.parseInt(tipId);
		
		if(udao.getUserIdByUserName(name)==tdao.getUserIdById(id)) isSuccess=true;
		else if(udao.getUserFlagByUserName(name)==1) isSuccess=true;
		else message="你无权删除本帖！";
		
		if(!isSuccess){
			request.setAttribute("message", message);
			RequestDispatcher rd=request.getRequestDispatcher("/tip.jsp");
			rd.forward(request, response);
		}else{
			tdao.deleteTipById(id);
			response.setHeader("Refresh","2;URL=http://localhost:8080/myBBS/home.jsp");
			out.println("<HTML>");
			out.println("<BODY>");
			out.println("<h3 align=\"center\">删除成功！</h3><p>");
			out.println("<h3 align=\"center\">2秒后自动跳转到主界面</h3><p>");
			out.println("</BODY>");
			out.println("</HTML>");
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
