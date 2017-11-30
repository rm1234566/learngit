<%@ page language="java" import="java.util.*" import="java.text.SimpleDateFormat" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
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
      String title=request.getParameter("title");
      String content=request.getParameter("content");
      String message=(String)request.getAttribute("message");
      HttpSession seeion=request.getSession();
      String name=(String)session.getAttribute("name");
      
      if(title==null) title="";
      if(content==null) content="";
      if(message==null) message="";
   %>
  
  <body style="background:#d1faff">
    <div style="position:absolute;left:90%;top:18%">
      <form method="post" action="safeExit.do"><table><tr><td><input style="display:none" type="text" name="userName" value="<%=name%>" readonly></td></tr>
                                                      <tr><td><input style="background:#d1faff;border:none" type="submit" name="submit" value="安全退出"></td></tr>
      </table></form>
    </div>
    <input type="button" value="首页" onClick="window.location.href='home.jsp'" style="height:47px;position:absolute;left:15%;top:16%;width:121px;font-size:20px;background:#fcb0d9;border:none">
    <font size="20" style="position:absolute;left:40%;top:10%"><strong>Touch the World</strong></font>
    <font size="2" style="position:absolute;left:81%;top:19%"><%=tishi%>，<%=name%></font>
    <hr color="#f7e137" width="90%" style="height:0.1%;layout-flow:vertical-ideographic;position:absolute;left:5%;top:20%"/>
    <font color="red" style="position:absolute;left:43%;top:22%"><strong><%=message%></strong></font>
    <div style="position:absolute;left:15%;top:25%;">
      <form method="post" action="sendTip.do">
        <table bordercolor="black" cellspacing="20" border="1" style="width:1320px;height:651px;table-layout:fixed">
          <tr style="height:60px">
            <td align="center" style="width:130px"><font><strong>标题:</strong></font><br><br>不能超过100字</td>
            <td><input type="text" name="title" value="<%=title%>" style="width:1120px;font-size:16px;height:60px;background:#d1faff"></td>
          </tr>
          <tr>
            <td align="center" style="width: 130px"><font><strong>正文:</strong></font><br><br>不能超过1000字</td>
            <td><textarea name="content" cols="50" rows="20" style="width:1120px;height:470px;font-size:16px;background:#d1faff"><%=content%></textarea></td>
          </tr>
          <tr>
            <td align="center" style="width: 130px"><font><strong>仅好友可见:</strong></font></td>
            <td><input type="radio" name="tip_private" value="yes">是
                <input type="radio" name="tip_private" value="no" checked>否
            </td>
          </tr>
          <tr><td colspan="2" align="right"><input type="submit" name="submit" value="发表" style="width:200px;backgroud:none"></td></tr>
        </table>
      </form>
    </div>
  </body>
</html>
