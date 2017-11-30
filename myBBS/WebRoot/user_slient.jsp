<%@ page language="java" import="java.util.* " import="DAOs.UserDAO" import="java.text.SimpleDateFormat" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
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
      String head="images/"+name+".jpg";
      UserDAO udao=new UserDAO();
      String gender=udao.getGenderByUserNmae(name);
      boolean isSex[]=new boolean[2];
      isSex[0]=false;
      isSex[0]=false;
      if(gender!=null&&gender.equals("man")) isSex[0]=true;
      else if(gender!=null&&gender.equals("woman")) isSex[1]=true;
      udao.getUsers();
      int nrOfUsers=udao.getNrOfUsers();
      int nrOfPages=0;
      if(nrOfUsers%4!=0) nrOfPages=nrOfUsers/4+1;
      else nrOfPages=nrOfUsers/4;
      int p=1;
      if(request.getParameter("pn")!=null) p=Integer.parseInt(request.getParameter("pn"));
      String status = "";
   %>
  
  <body  style="background:#d1faff">
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
    
    <div style="position:absolute;left:0%;top:11.5%">
      <form method="post" action="alterUserInfor.do" enctype="multipart/form-data">
        <table cellspacing="20">
          <tr>
            <td colspan="2"><img src=<%=head%> style="width:244px;height:150px"></td>
          </tr>
          <tr>
            <td colspan="2">用户名:<input type="text" name="name" value="<%=name%>" readonly style="width:189px;background:#d1faff"></td>
          </tr>
          <tr>
            <td colspan="2">密&nbsp;码:<input style="width:189px;background:#d1faff" type="password" name="password" value="<%=udao.getPasswordByUserNmae(name)%>"></td>
          </tr>
          <tr>
            <td colspan="2">年&nbsp;龄:<input style="width:189px;background:#d1faff" type="text" name="age" value="<%=udao.getAgeByUserNmae(name)%>"></td>
          </tr>
          <tr>
            <td style="width: 120px; ">
                         头&nbsp;像：
            </td>
            <td>
              <img src="" id="headImage" name="headDisplay" style="border-style:dashed;width:100px;height:100px;border-width:1;border-radius:200px"><br><br>
              <input type="button" value="上传头像" onClick="headUpload.click()" style="width: 105px; ">
            </td>
          </tr>
            <tr>
              <td colspan="2">
                <input type="file" id="headUpload" name="headUpload" style="display:none;" onChange="displayHead();">
              </td>
            </tr>
          <tr>
            <td colspan="2">注册时间:<input style="background:#d1faff" type="text" name="regTime" value="<%=udao.getRegTimeByUserNmae(name)%>" readonly></td>
          </tr>
          <tr>
            <td colspan="2" style="width: 98px; ">性&nbsp;别:<input style="background:#d1faff" type="radio" name="sex" value="man" <%if(isSex[0]){ %>checked<%} %>>男&nbsp;<input type="radio" name="sex" value="woman" <%if(isSex[1]){ %>checked<%} %>>女</td>
          </tr>
          <tr>
            <td colspan="2" align="right"><input type="submit" name="submit" value="确认修改" style="height:40px;border:none;background:#d1faff"></td>
          </tr>
        </table>
      </form>
    </div>
    
    <div style="position:absolute;left:15%;top:10%;">
        <%if(p!=nrOfPages){for(int i=0+4*(p-1);i<p*4;i++){
          int slience = udao.getSlenceByUserName(udao.getUserByIndex(i).getName());
          if(slience == 1) status = "禁言中";
          else status = "自由发言";%>
          <form method="post" action="setSlient.do">
          <table cellspacing="20" >
          <tr style="height: 76px; ">
            <td><input type ="text" name="u_name" value=<%=udao.getUserByIndex(i).getName() %> style="background:none;border:none" readonly>
            <td>状态：<%=status%></td>
          </tr>
          <tr>
            <%if(slience == 1){ %>
            <td><input type="submit" value="解禁" name="submit" style="font-size:18px;background:#fcb0d9;border:none"></td>
            <%} else{%>
            <td><input type="submit" value="禁言" name="submit" style="font-size:18px;background:#fcb0d9;border:none"></td>
            <%} %>
          </tr>
          </table>
          </form>
        <%}}else{ for(int i=0+4*(p-1);i<nrOfUsers;i++){
          int slience = udao.getSlenceByUserName(udao.getUserByIndex(i).getName());
          if(slience == 1) status = "禁言中";
          else status = "自由发言";
        %>
          <form method="post" action="setSlient.do">
          <table cellspacing="20" >
          <tr style="height: 76px; ">
            <td>用户名：<input type ="text" name="u_name" value="<%=udao.getUserByIndex(i).getName() %>" style="background:none;border:none" readonly></td>
            <td>状态：<%=status%></td>
          </tr>
          <tr>
            <%if(slience == 1){ %>
            <td><input type="submit" value="解禁" name="submit" style="font-size:18px;background:#fcb0d9;border:none"></td>
            <%} else{%>
            <td><input type="submit" value="禁言" name="submit" style="font-size:18px;background:#fcb0d9;border:none"></td>
            <%} %>
          </tr>
          </table>
          </form>
        <% }}%>
    </div>
    
    <div style="position:absolute;left:50%;top:90%">
      <%for(int i=0;i<nrOfPages;i++){%>
        <a href=user_slient.jsp?pn=<%=i+1%>><font size="5"><strong><%=i+1%></strong></font></a>
      <%} %>
    </div>
    
  </body>
</html>
