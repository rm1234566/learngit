package servlets;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import javaBeans.Board;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import DAOs.BoardDAO;

@MultipartConfig(location="F:\\",fileSizeThreshold=1024)

public class addBoardServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final String SAVE_PATH = "F:\\大二下神经病多的专业课\\web开发技术\\MyEclipse2014\\myBBS\\WebRoot\\images\\";

	/**
	 * Constructor of the object.
	 */
	public addBoardServlet() {
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
		
		BoardDAO bdao = new BoardDAO();
		String message="";
		String name=request.getParameter("name");
		Part part = request.getPart("headUpload");
		String h = part.getHeader("content-disposition");
		File f = new File(SAVE_PATH + File.separator);
		boolean isSuccess=false;
		Board board = new Board(name);
		
		if(!f.exists()) {
		    f.mkdirs();
		}
		
		if(name.equals("")) message = "讨论区名不能为空！";
		else if(bdao.findBoard(name)) message = "讨论区已存在！";
		else if(!h.substring(h.lastIndexOf(".")+1,h.length()-1).equals("jpg")) message="请上传jpg格式的图片！";
		else isSuccess=true;
		
		if(!isSuccess){
			request.setAttribute("message", message);
			request.setAttribute("board",board);
		    RequestDispatcher rd=request.getRequestDispatcher("/addBoard.jsp");
			rd.forward(request, response);
		}else{
			int b_id = bdao.getBoardNum() + 1;
			String head = "board_" + b_id + ".jpg";
			part.write(SAVE_PATH + File.separator + head);
			bdao.addBoard(board,head);
		    response.setHeader("Refresh","2;URL=http://localhost:8080/myBBS/board.jsp");
			out.println("<HTML>");
			out.println("<BODY>");
			out.println("<h3 align=\"center\">添加讨论区成功！</h3><p>");
			out.println("<h3 align=\"center\">2秒后自动跳转到讨论区界面</h3><p>");
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
