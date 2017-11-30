<%@ page language="java" import="java.util.*" import="DAOs.UserDAO" import="java.text.SimpleDateFormat" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>论坛</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <%
      Date t=new Date();
	  SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	  String time=sdf.format(t);
	  String a=time.substring(11,13);
	  int b=Integer.parseInt(a);
	  String tishi="";
	  if(b>=0&&b<11) tishi="早上好";
	  else if(b>=11&&b<=14) tishi="中午好";
	  else if(b>14&&b<=16) tishi="下午好";
	  else if(b>16&&b<=18) tishi="傍晚好";
	  else tishi="晚上好";
      HttpSession seeion=request.getSession();
      String name=(String)session.getAttribute("name");
      UserDAO udao=new UserDAO();
      udao.getUsers();
      int nrOfUsers=udao.getNrOfUsers();
      int nrOfPages=0;
      if(nrOfUsers%4!=0) nrOfPages=nrOfUsers/4+1;
      else nrOfPages=nrOfUsers/4;
      int p=1;
      if(request.getParameter("pn")!=null) p=Integer.parseInt(request.getParameter("pn"));
  %>
  
  <body style="background:#d1faff">
    <div style="position:absolute;left:90%;top:8%">
      <form method="post" action="safeExit.do"><table><tr><td><input style="display:none" type="text" name="userName" value="<%=name%>" readonly></td></tr>
                                                      <tr><td><input style="background:#d1faff;border:none" type="submit" name="submit" value="安全退出"></td></tr>
      </table></form>
    </div>
    <hr color="#f7e137" width="0.1%" style="height:90%;layout-flow:vertical-ideographic;position:absolute;left:15%;top:5%"/>
    <hr color="#f7e137" width="90%" style="height:0.1%;layout-flow:vertical-ideographic;position:absolute;left:5%;top:10%"/>
    <input type="button" value="首页" onClick="window.location.href='home.jsp'" style="height:47px;position:absolute;left:15.6%;top:5%;width:121px;font-size:20px;background:#fcb0d9;border:none"> 
    <font size="20" style="position:absolute;left:42%;top:3%"><strong>Touch the World</strong></font>
    <font size="2" style="position:absolute;left:80%;top:9%"><%=tishi%>，<%=name%></font>
    
    <div style="position:absolute;left:15%;top:10%;">
      <%if(p!=nrOfPages){for(int i=0+4*(p-1);i<p*4;i++){%>
        <form method="post" action="addFriend.do">
          <table cellspacing="20" >
            <tr style="height: 76px; ">
              <td><input type ="text" name="u_name" value=<%=udao.getUserByIndex(i).getName() %> style="background:none;border:none" readonly>
              <td><input type="submit" value="加为好友" name="submit" style="font-size:18px;background:#fcb0d9;border:none"></td>
            </tr>
          </table>
        </form>
      <%}}else{ for(int i=0+4*(p-1);i<nrOfUsers;i++){%>
        <form method="post" action="addFriend.do">
          <table cellspacing="20" >
            <tr style="height: 76px; ">
              <td><input type ="text" name="u_name" value=<%=udao.getUserByIndex(i).getName() %> style="background:none;border:none" readonly>
              <td><input type="submit" value="加为好友" name="submit" style="font-size:18px;background:#fcb0d9;border:none"></td>
            </tr>
          </table>
        </form>
      <%}}%>
    </div>
    
    <div style="position:absolute;left:50%;top:90%">
      <%for(int i=0;i<nrOfPages;i++){%>
        <a href=addFriend.jsp?pn=<%=i+1%>><font size="5"><strong><%=i+1%></strong></font></a>
      <%} %>
    </div>
  </body>
</html>
