package servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javaBeans.Friend;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DAOs.UserDAO;
import DAOs.FriendDAO;

public class addFriendServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor of the object.
	 */
	public addFriendServlet() {
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
		
		HttpSession session=request.getSession();
		String name=(String)session.getAttribute("name");
		String u_name = request.getParameter("u_name");
		UserDAO udao = new UserDAO();
		FriendDAO fdao = new FriendDAO();
		int u_id_1 = udao.getUserIdByUserName(name);
		int u_id_2 = udao.getUserIdByUserName(u_name);
		boolean isFind = fdao.findFriend(u_id_1, u_id_2);
		
		if(isFind){
			response.setHeader("Refresh","2;URL=http://localhost:8080/myBBS/friendList.jsp");
			out.println("<HTML>");
			out.println("<BODY>");
			out.println("<h3 align=\"center\">好友已存在！</h3><p>");
			out.println("<h3 align=\"center\">2秒后自动跳转到你的好友列表。</h3><p>");
			out.println("</BODY>");
			out.println("</HTML>");
		}else{
			Friend friend = new Friend(u_id_1,u_id_2);
			fdao.addFriend(friend);
			response.setHeader("Refresh","2;URL=http://localhost:8080/myBBS/friendList.jsp");
			out.println("<HTML>");
			out.println("<BODY>");
			out.println("<h3 align=\"center\">添加好友成功！</h3><p>");
			out.println("<h3 align=\"center\">2秒后自动跳转到你的好友列表。</h3><p>");
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
