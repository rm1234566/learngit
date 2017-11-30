<%@ page language="java" import="java.text.SimpleDateFormat" import="DAOs.UserDAO" import="DAOs.TipDAO" import="java.util.*" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
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
      int b_id = -1;
      if(request.getParameter("b_id")!=null)b_id = Integer.parseInt(request.getParameter("b_id"));
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
      TipDAO tdao = new TipDAO();
      tdao.getTipsByBid(b_id,0);
      int nrOfTips=tdao.getNrOfTips();
      UserDAO udao = new UserDAO();
  %>
  
  <body style="background:#d1faff">
    <font size="20" style="position:absolute;left:42%;top:3%"><strong>Touch the World</strong></font>
    <font size="2" style="position:absolute;left:80%;top:9%"><%=tishi%>，<%=name%></font>
    <div style="position:absolute;left:90%;top:8%">
      <form method="post" action="safeExit.do"><table><tr><td><input style="display:none" type="text" name="userName" value="<%=name%>" readonly></td></tr>
                                                      <tr><td><input style="background:#d1faff;border:none" type="submit" name="submit" value="安全退出"></td></tr>
      </table></form>
    </div>
    <hr color="#f7e137" width="0.1%" style="height:90%;layout-flow:vertical-ideographic;position:absolute;left:15%;top:5%"/>
    <hr color="#f7e137" width="90%" style="height:0.1%;layout-flow:vertical-ideographic;position:absolute;left:5%;top:10%"/>
    <input type="button" value="返回" onClick="window.location.href='board.jsp'" style="height:47px;position:absolute;left:15.6%;top:5%;width:121px;font-size:20px;background:#fcb0d9;border:none"> 
    <input type="button" value="发帖" onClick="window.location.href='sendTipBoard.jsp?b_id=<%=b_id %>'" style="height:47px;position:absolute;left:22%;top:5%;width:121px;font-size:20px;background:#fcb0d9;border:none">
    <div style="position:absolute;left:15%;top:10%;">
    <table cellspacing="20" style="width: 1530px; ">
          <%for(int i=0;i<nrOfTips;i++){ int tipId=tdao.getTipIdByIndex(i);%>
            <tr style="height: 76px; ">
              <td style="width: 71px; "><img src="images/icon.gif"></td>
              <td style="width: 1022px;"><a href=tip.jsp?name=<%=name%>&id=<%=tipId%>&author=<%=udao.getUserNameByUserId(tdao.getTipByIndex(i).getUserId())%>><%=tdao.getTipByIndex(i).getTitle()%></a></td>
              <td>作者:<%=udao.getUserNameByUserId(tdao.getTipByIndex(i).getUserId())%></td>
              <td style="width: 176px; ">发帖时间:<%=tdao.getTipByIndex(i).getPublishTime()%></td>
            </tr>
          <%}%>
    </table>
    </div>
  </body>
</html>
