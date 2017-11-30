<%@ page language="java" import="java.util.*" import="java.text.SimpleDateFormat" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<jsp:useBean id="user" class="javaBeans.User" scope="request"></jsp:useBean>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>论坛e</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  <script language="JavaScript" type="text/javascript">
    function displayHead(){
    	var imgUrl=getObjectURL(document.getElementById("headUpload").files[0]);
    	document.getElementById("headImage").src=imgUrl;
    }
    //建立一个可存取该file的url
    function getObjectURL(image){
    	var url=null;
    	if(window.creatObjectURL!=undefined){             //basic
    		url=window.createObjectURL(image);
    	}else if(window.URL!=undefined){
    		url=window.URL.createObjectURL(image);
    	}else if(window.webkitURL!=undefined){
    		url=window.webkitURL.createObjectURL(image);
    	}
    	return url;
    }
  </script>
  
  </head>
  
  <% 
      String name=user.getName();
      String password=user.getPassword();
      String age=user.getAge();
      String sex=user.getSex();
      String message=(String)request.getAttribute("message");
      
      if(message==null) message="";
      if(name==null) name="";
      if(password==null) password="";
      if(age==null) age="";
      if(sex==null) sex="";
      
      boolean isSex[]=new boolean[2];
      isSex[0]=false;
      isSex[0]=false;
      if(sex!=null&&sex.equals("man")) isSex[0]=true;
      else if(sex!=null&&sex.equals("woman")) isSex[1]=true;
  %>
  
  <body background="images/register.jpg">
    <div align="center" style="position:absolute;left:60%;top:20%;">
      <h1>用户注册</h1>
      <form  action="register.do" method="post" enctype="multipart/form-data">
        <table cellspacing="50">
          <tr>
            <td colspan="3">
                         用户名：<input type="text" name="name" value="<%=name%>">(名字必须由字符和数字组成，并且长度大于6小于12.)
            </td>
          </tr>
          <tr>
            <td colspan="3">
                         密&nbsp;码：<input type="password" name="password" value="<%=password%>">(密码不能为空.)
            </td>
          </tr>
          <tr>
            <td colspan="3">
                         年&nbsp;龄：<input type="text" name="age" value="<%=age%>">(年龄在0-999之间.)
            </td>
          </tr>
          <tr>
            <td style="width: 61px; ">
                         头&nbsp;&nbsp;像：<input type="text" style="visibility:hidden;">
            </td>
            <td>
              <img src="" id="headImage" name="headDisplay" style="border-style:dashed;width:100px;height:100px;border-width:1;border-radius:150px"><br><br><br>
              <input type="button" value="上传头像" onClick="headUpload.click()" style="width: 105px; ">
            </td>
            <td>
              <input type="file" id="headUpload" name="headUpload" style="display:none;" onChange="displayHead();">
            </td>
          </tr>
          <tr>
            <td colspan="3">
                         性&nbsp;&nbsp;别： <input type="radio" name="sex" value="man" <%if(isSex[0]){ %>checked<%} %>>男
                                <input type="radio" name="sex" value="woman" <%if(isSex[1]){ %>checked<%} %>>女
            </td>
          </tr>
          <tr>
            <td colspan="1">
              <input type="submit"  name="submit" value="提交">
            </td>
            <td colspan="2" align="right">
              <input type="reset"  name="reset" value="重置">
            </td>
          </tr>
          <tr>
            <td colspan="3"><font color="red"><strong><%=message%></strong></font></td>
          </tr>
        </table>
      </form>
    </div>
    <div style="position:absolute;left:48%;top:49%;">
      <a href=http://localhost:8080/myBBS/login.jsp><font size="5">进入登陆界面</font></a>
    </div>
  </body>
</html>
