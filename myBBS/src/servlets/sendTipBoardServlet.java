package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import javaBeans.Tip;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DAOs.TipDAO;
import DAOs.UserDAO;

public class sendTipBoardServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor of the object.
	 */
	public sendTipBoardServlet() {
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
		
		String title=request.getParameter("title");
		String content=request.getParameter("content");
		String tip_private = request.getParameter("tip_private");
		int b_id = Integer.parseInt(request.getParameter("b_id"));
		Date time=new Date();
	    SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    String publishTime=sdf.format(time);
		HttpSession session=request.getSession();
		String name=(String)session.getAttribute("name");
		UserDAO udao=new UserDAO();
		int id=udao.getUserIdByUserName(name);
		int slient = udao.getSlenceByUserName(name);
		String message="";
		TipDAO tdao=new TipDAO();
		boolean isSuccess=false;
        int tipPrivate = 0;
		
		if(tip_private.equals("yes")) tipPrivate = 1;
	    
		if(title.equals("")||title.length()>100) message="标题不能为空且超过100个字！";
		else if(content.equals("")||content.length()>1000) message="正文不能为空且超过1000个字！";
		else if(slient == 1) message="你已被管理员禁言！";
		else isSuccess=true;
		
		if(!isSuccess){
			String url = "/sendTipBoard.jsp?b_id="+b_id;
			request.setAttribute("message", message);
			RequestDispatcher rd=request.getRequestDispatcher(url);
			rd.forward(request, response);
		}else{
			Tip tip=new Tip(title,content,publishTime,id,b_id,tipPrivate);
			tdao.addTipAndBid(tip,b_id);
			response.setHeader("Refresh","2;URL=http://localhost:8080/myBBS/board.jsp");
			out.println("<HTML>");
			out.println("<BODY>");
			out.println("<h3 align=\"center\">发帖成功！</h3><p>");
			out.println("<h3 align=\"center\">2秒后自动跳转到主页。</h3><p>");
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
