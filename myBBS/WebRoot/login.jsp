<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
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
      String message=(String)request.getAttribute("message");
      String name=request.getParameter("name");
      String password=request.getParameter("password");
      
      if(message==null) message="";
      if(name==null) name="";
      if(password==null) password="";
   %>
  
  <body background="images/bg.jpg">
    <img src="images/k.gif" style="width:35%;height:80%;position:relative;left:60%;top:20%">
    <font size="8" style="position:absolute;left:20%;top:70%">欢迎来到你的论坛</font>
    <div style="position:absolute;left:68%;top:42%;">
      <form method="post" action="login.do">
        <table cellspacing=40>
          <tr>
            <td colspan="2">
              <h1 align="center">用户登录</h1>
            </td>
          </tr>
          <tr>
            <td colspan="2">
                         用户名：<input type="text" name="name" value="<%=name%>">
            </td>
          </tr>
          <tr>
            <td colspan="2">
                         密&nbsp;码：<input type="password" name="password" value="<%=password%>">
            </td>
          </tr>
          <tr>
            <td><input type="submit" name="submit" value="提交"></td>
            <td align="right"><input type="reset" name="reset" value="重置"></td>
          </tr>
          <tr>
            <td colspan="2"><font color="red"><strong><%=message%></strong></font></td>
          </tr>
        </table>
      </form>
    </div>
    <div style="position:absolute;left:78%;top:75%;">
      <a href=http://localhost:8080/myBBS/register.jsp>进入注册界面</a>
    </div>
  </body>
</html>
