<%@ page language="java" import="java.util.*" import="DAOs.BoardDAO" import="DAOs.UserDAO" import="java.text.SimpleDateFormat" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
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
      UserDAO udao = new UserDAO();
      BoardDAO bdao = new BoardDAO();
      bdao.getBoards();
      int nrOfBoards = bdao.getNrOfBoards();
      int flag = udao.getUserFlagByUserName(name);
  %>
  
  <body background="images/boardbg.jpg">
    <input type="button" value="首页" onClick="window.location.href='home.jsp'" style="height:47px;position:absolute;left:15%;top:15%;width:121px;font-size:20px;background:#fcb0d9;border:none"> 
    <%if(flag == 1){ %>
    <input type="button" value="增加讨论区" onClick="window.location.href='addBoard.jsp'" style="height:47px;position:absolute;left:21.4%;top:15%;width:121px;font-size:20px;background:#fcb0d9;border:none">
    <%} %>
    <font size="2" style="position:absolute;left:81%;top:19%"><%=tishi%>，<%=name%></font>
    <div style="position:absolute;left:90%;top:18%">
      <form method="post" action="safeExit.do"><table><tr><td><input style="display:none" type="text" name="userName" value="<%=name%>" readonly></td></tr>
                                                      <tr><td><input style="background:#ffffff;border:none" type="submit" name="submit" value="安全退出"></td></tr>
      </table></form>
    </div>
    <font size="20" style="position:absolute;left:40%;top:10%"><strong>Touch the World</strong></font>
    <hr color="#f7e137" width="90%" style="height:0.1%;layout-flow:vertical-ideographic;position:absolute;left:5%;top:20%"/>
    
    <%for(int i = 0;i < nrOfBoards; i++){
      int position = 19 + i * 11;
      int position2 = 15 + i * 11;
      String b_name = bdao.getBoardByIndex(i).getBoardName();
      String b_head = bdao.getBoardByIndex(i).getBoardHead();
      int b_id = bdao.getBoardIdByBname(b_name);
    %>
      <img src="images/<%=b_head%>" style="width:200px;height:200px;position:absolute;left:<%=position2%>%;top:25%">
      <a href="singleBoard.jsp?b_id=<%=b_id%>" style="position:absolute;left:<%=position%>%;top:50%"><font><strong><%=b_name%></strong></font></a>
    <%} %>
    
  </body>
</html>
