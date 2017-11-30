package servlets;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.regex.Pattern;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import DAOs.UserDAO;

@MultipartConfig(location="F:\\",fileSizeThreshold=1024)

public class alterUserInforServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final String SAVE_PATH = "E:\\大二下神经病多的专业课\\web开发技术\\MyEclipse2014\\myBBS\\WebRoot\\images\\";

	/**
	 * Constructor of the object.
	 */
	public alterUserInforServlet() {
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
		
		String userPassword=request.getParameter("password");
		String name=request.getParameter("name");
		String age=request.getParameter("age");
		String sex=request.getParameter("sex");
		int userAge=0;
		if(!age.equals("")) userAge=Integer.parseInt(age); 
		UserDAO udao=new UserDAO();
		Part part = request.getPart("headUpload");
		String h = part.getHeader("content-disposition");
		File f = new File(SAVE_PATH + File.separator);
		
		if(!f.exists()) {
		    f.mkdirs();
		}
		
		if(h.substring(h.lastIndexOf(".")+1,h.length()-1).equals("jpg")){
			String filename = name+".jpg";
			part.write(SAVE_PATH + File.separator + filename);
		}
		if(!userPassword.equals("")) udao.UpdatePasswordByUserNmae(name,userPassword);
		if(Pattern.compile("^[0-9]{1,3}$").matcher(age).find()) udao.UpdateAgeByUserNmae(userAge, name);
		if(!sex.equals("")) udao.UpdateGenderByUserNmae(sex, name);
		
		RequestDispatcher rd=request.getRequestDispatcher("/home.jsp");
		rd.forward(request, response);
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
