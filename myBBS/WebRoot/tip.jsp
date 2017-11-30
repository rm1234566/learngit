<%@ page language="java" import="java.util.*" import="DAOs.TipDAO" import="DAOs.UserDAO" import="DAOs.ReplyDAO" import="java.text.SimpleDateFormat" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
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
	  String c=time.substring(11,13);
	  int b=Integer.parseInt(c);
	  String tishi="";
	  if(b>=0&&b<11) tishi="早上好";
	  else if(b>=11&&b<=14) tishi="中午好";
	  else if(b>14&&b<=16) tishi="下午好";
	  else if(b>16&&b<=18) tishi="傍晚好";
	  else tishi="晚上好";
      String userName=request.getParameter("name");
      TipDAO tdao=new TipDAO();
      ReplyDAO rdao=new ReplyDAO();
      UserDAO udao=new UserDAO();
      int tipId=0;
      if(request.getParameter("id")!=null)tipId=Integer.parseInt(request.getParameter("id"));
      rdao.getReplys(tipId);
      String author=request.getParameter("author");
      String head1="images/"+author+".jpg";
      String isReply=(String)request.getAttribute("isReply");
      String title=request.getParameter("title");
      String content=request.getParameter("content");
      String message=(String)request.getAttribute("message");
      
      if(title==null) title="";
      if(content==null) content="";
      if(message==null) message="";
  %>
  
  <body>
    <form method="post" action="isReply.do"><table><tr style="display:none"><td><input type="text" name="id" value="<%=tipId%>" readonly></td>
                                                                            <td><input type="text" name="author" value="<%=author%>" readonly></td>
                                                                            <td><input type="text" name="name" value="<%=userName%>" readonly></td>
                                                   </tr>
                                                   <tr><td colspan="3"><input style="height:47px;position:absolute;left:16.5%;top:6%;width:121px;font-size:20px;background:#fcb0d9;border:none" type="submit" name="submit" value="回复帖子"></td></tr>
    </table></form>
    <form method="post" action="deleteTip.do"><table><tr style="display:none"><td><input type="text" name="id" value="<%=tipId%>" readonly></td>
                                                                            <td><input type="text" name="author" value="<%=author%>" readonly></td>
                                                                            <td><input type="text" name="name" value="<%=userName%>" readonly></td>
                                                     </tr>
                                                     <tr><td colspan="3"><input style="height:47px;position:absolute;left:23%;top:6%;width:121px;font-size:20px;background:#fcb0d9;border:none" type="submit" name="submit" value="删除帖子"></td></tr><tr style="position:absolute;left:30%;top:8%"><td colspan="3"><font color="red"><strong><%=message%></strong></font></td></tr>
    </table>
    </form>
    <input type="button" value="首&nbsp;&nbsp;页" onClick="window.location.href='home.jsp'" style="height:47px;position:absolute;left:10%;top:6%;width:121px;font-size:20px;background:#fcb0d9;border:none">
    <font size="20" style="position:absolute;left:42%;top:3%"><strong>Touch the World</strong></font>
    <hr color="#f7e137" width="90%" style="height:0.1%;layout-flow:vertical-ideographic;position:absolute;left:5%;top:10%"/>
    <font size="2" style="position:absolute;left:80%;top:9%"><%=tishi%>，<%=userName%></font>
    <div style="position:absolute;left:90%;top:8%">
      <form method="post" action="safeExit.do"><table><tr><td><input style="display:none" type="text" name="userName" value="<%=userName%>" readonly></td></tr>
                                                      <tr><td><input style="background:#ffffff;border:none" type="submit" name="submit" value="安全退出"></td></tr>
      </table></form>
    </div>
    <div style="position:absolute;left:10%;top:12%;">
      <form>
        <table bordercolor="#40eadb" cellspacing="20" border="1" style="width: 1501px; height: 1000px">
          <tr>
            <td align="center" rowspan="2" style="width:100px"><img src=<%=head1%> style="width:100px;height:100px"><br><%=author%><br><font size="5"><strong>楼主</strong></font></td>
            <td style="height: 21px; ">&nbsp;发表于:<%=tdao.getPublishTimeById(tipId)%></td>
          </tr>
          <tr>
            <td>&nbsp;<%=tdao.getContentById(tipId) %></td>
          </tr>
          <%for(int i=0;i<rdao.getNrOfReplys();i++){String a=udao.getUserNameByUserId(rdao.getUserIdById(rdao.getReplyIdByIndex(i,tipId)));String h="images/"+a+".jpg";%>
            <tr>
            <td align="center" rowspan="2" style="width:100px"><img src=<%=h%> style="width:100px;height:100px"><br><%=a%><br><font size="5"><strong><%=i+2%>楼</strong></font></td>
            <td style="height: 21px; ">&nbsp;回复于:<%=rdao.getPublishTimeById(rdao.getReplyIdByIndex(i,tipId))%></td>
          </tr>
          <tr>
            <td>&nbsp;<%=rdao.getContentById(rdao.getReplyIdByIndex(i,tipId))%></td>
          </tr>
          <%} %>
        </table>
      </form>
    </div>
    <%if(isReply!=null&&isReply.equals("true")){ %>
    <div style="position:absolute;left:32%;top:25%;">
      <input style="background:#40eadb;width:60px;height:40px;font-size:20px;align:center" type="text" name="dasda" value="回&nbsp;&nbsp;复" readonly>
      <form method="post" action="reply.do">
        <table style="background:#40eadb;width: 596px;height: 540px" bordercolor="black" cellspacing="10" border="1">
          <tr style="display:none"><td><input type="text" name="id" value="<%=tipId%>" readonly></td>
                                   <td><input type="text" name="author" value="<%=author%>" readonly></td>
                                   <td><input type="text" name="name" value="<%=userName%>" readonly></td>
          </tr>
          <tr>
            <td colspan="2" style="height:21px">标题:<input type="text" name="title" value="<%=title%>" style="width:530px;background:#40eadb"></td>
          </tr>
          <tr>
            <td colspan="2">正文:<textarea name="content" cols="50" rows="20" style="font-size:16px;background:#40eadb; width:565px"><%=content%></textarea></td>
          </tr>
          <tr>
            <td style="height:47px"><input style="background:#40eadb; height: 43px; width: 275px" type="submit" name="submit" value="取消"></td>
            <td style="height:47px"><input style="background:#40eadb; height: 43px; width: 275px" type="submit" name="submit" value="发表"></td>
          </tr>
        </table>
      </form>
      <font color="red"><strong><%=message%></strong></font>
    </div>
    <% }%>
  </body>
</html>
