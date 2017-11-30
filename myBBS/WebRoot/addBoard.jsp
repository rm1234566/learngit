<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<jsp:useBean id="board" class="javaBeans.Board" scope="request"></jsp:useBean>
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
    String name = board.getBoardName();
    String message=(String)request.getAttribute("message");
    
    if(message==null) message="";
    if(name==null) name="";
   %>
  
  <body background="images/register.jpg">
    <div align="center" style="position:absolute;left:60%;top:20%;">
      <h1>增加讨论区</h1>
      <form  action="addBoard.do" method="post" enctype="multipart/form-data">
        <table cellspacing="50">
          <tr>
            <td colspan="3">
                         讨论区名：<input type="text" name="name" value="<%=name%>">
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
      <a href=board.jsp><font size="5">返回</font></a>
    </div>
  </body>
</html>
