<%@ page language="java" import="java.util.*" import="DAOs.UserDAO" import="DAOs.CollectDAO" import="DAOs.TipDAO" import="java.text.SimpleDateFormat" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
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
      String search_mess=(String)request.getAttribute("search_mess");
      if(search_mess == null) search_mess = "";
      String head="images/"+name+".jpg";
      UserDAO udao=new UserDAO();
      TipDAO tdao=new TipDAO();
      int userId=udao.getUserIdByUserName(name);
      int userFlag = udao.getUserFlagByUserName(name);
      tdao.getTipsByPrivate(0);
      int nrOfTips=tdao.getNrOfTips();
      int nrOfPages=0;
      if(nrOfTips%4!=0) nrOfPages=nrOfTips/4+1;
      else nrOfPages=nrOfTips/4;
      int p=1;
      if(request.getParameter("pn")!=null) p=Integer.parseInt(request.getParameter("pn"));
      String gender=udao.getGenderByUserNmae(name);
      boolean isSex[]=new boolean[2];
      isSex[0]=false;
      isSex[0]=false;
      if(gender!=null&&gender.equals("man")) isSex[0]=true;
      else if(gender!=null&&gender.equals("woman")) isSex[1]=true;
      CollectDAO cdao = new CollectDAO();
   %>
  
  <body style="background:#d1faff">
    <div style="position:absolute;left:90%;top:8%">
      <form method="post" action="safeExit.do"><table><tr><td><input style="display:none" type="text" name="userName" value="<%=name%>" readonly></td></tr>
                                                      <tr><td><input style="background:#d1faff;border:none" type="submit" name="submit" value="安全退出"></td></tr>
      </table></form>
    </div>
    <hr color="#f7e137" width="0.1%" style="height:90%;layout-flow:vertical-ideographic;position:absolute;left:15%;top:5%"/>
    <hr color="#f7e137" width="90%" style="height:0.1%;layout-flow:vertical-ideographic;position:absolute;left:5%;top:10%"/>
    <input type="button" value="添加好友" onClick="window.location.href='addFriend.jsp'" style="height:47px;position:absolute;left:8%;top:5%;width:121px;font-size:20px;background:#fcb0d9;border:none"> 
    <input type="button" value="你的好友" onClick="window.location.href='friendList.jsp'" style="height:47px;position:absolute;left:1.5%;top:5%;width:121px;font-size:20px;background:#fcb0d9;border:none"> 
    <input type="button" value="朋友圈" onClick="window.location.href='pengyouquan.jsp'" style="height:47px;position:absolute;left:70%;top:5%;width:121px;font-size:20px;background:#fcb0d9;border:none"> 
    <input type="button" value="发帖" onClick="window.location.href='sendTip.jsp'" style="height:47px;position:absolute;left:15.6%;top:5%;width:121px;font-size:20px;background:#fcb0d9;border:none"> 
    <input type="button" value="讨论区" onClick="window.location.href='board.jsp'" style="height:47px;position:absolute;left:22%;top:5%;width:121px;font-size:20px;background:#fcb0d9;border:none">
    <input type="button" value="收藏列表" onClick="window.location.href='user_collect.jsp'" style="height:47px;position:absolute;left:28.5%;top:5%;width:121px;font-size:20px;background:#fcb0d9;border:none">
    <%if(userFlag == 1){ %>
    <input type="button" value="禁言功能" onClick="window.location.href='user_slient.jsp'" style="height:47px;position:absolute;left:35%;top:5%;width:121px;font-size:20px;background:#fcb0d9;border:none">
    <% } %>
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
    <div style="position:absolute;left:15%;top:16.5%;">
          <%if(p!=nrOfPages){for(int i=0+4*(p-1);i<p*4;i++){ int tipId=tdao.getTipIdByIndex(i);boolean isFindCollect = cdao.findCollect(userId,tipId);%>
          <form method="post" action="addCollect.do">
          <table cellspacing="20" style="width: 1530px; ">
            <tr style="height: 76px; ">
              <td style="width: 71px; "><img src="images/icon.gif"></td>
              <td style="width: 700px;"><a href=tip.jsp?name=<%=name%>&id=<%=tipId%>&author=<%=udao.getUserNameByUserId(tdao.getTipByIndex(i).getUserId())%>><%=tdao.getTipByIndex(i).getTitle()%></a></td>
              <td><input type="submit" name="submit" value="收藏"></td>
              <td><input type="text" name="tip_id" style="display:none;" value=<%=tipId%>></td>
              <%if(isFindCollect){%><td><font style="color:red"><strong>已收藏！</strong></font></td><%}else{%>
              <td><font style="color:red"><strong>未收藏！</strong></font></td><%} %>
              <td>作者:<%=udao.getUserNameByUserId(tdao.getTipByIndex(i).getUserId())%></td>
              <td style="width: 176px; ">发帖时间:<%=tdao.getTipByIndex(i).getPublishTime()%></td>
            </tr>
          </table>
          </form>
          <%}}else{ for(int i=0+4*(p-1);i<nrOfTips;i++){ int tipId=tdao.getTipIdByIndex(i);boolean isFindCollect = cdao.findCollect(userId,tipId);%>
          <form method="post" action="addCollect.do">
          <table cellspacing="20" style="width: 1530px; ">
            <tr style="height: 76px; ">
              <td style="width: 71px; "><img src="images/icon.gif"></td>
              <td style="width: 700px;"><a href=tip.jsp?name=<%=name%>&id=<%=tipId%>&author=<%=udao.getUserNameByUserId(tdao.getTipByIndex(i).getUserId())%>><%=tdao.getTipByIndex(i).getTitle()%></a></td>
              <td><input type="submit" name="submit" value="收藏"></td>
              <td><input type="text" name="tip_id" style="display:none;" value=<%=tipId%>></td>
              <%if(isFindCollect){%><td><font style="color:red"><strong>已收藏！</strong></font></td><%}else{%>
              <td><font style="color:red"><strong>未收藏！</strong></font></td><%} %>
              <td>作者:<%=udao.getUserNameByUserId(tdao.getTipByIndex(i).getUserId())%></td>
              <td style="width: 176px; ">发帖时间:<%=tdao.getTipByIndex(i).getPublishTime()%></td>
            </tr>
          </table>
          </form>
          <% }}%>
    </div>
    <div style="position:absolute;left:50%;top:90%">
      <%for(int i=0;i<nrOfPages;i++){%>
        <a href=home.jsp?pn=<%=i+1%>><font size="5"><strong><%=i+1%></strong></font></a>
      <%} %>
    </div>
    
    <div style="position:absolute;left:16.8%;top:14%">
      <form method="post" action="search.do">
        <table style="width:200%">
          <tr>
            <td><input type="text" name="search" placeholder="请输入帖子标题" style="width:100%;height:180%"></td>
            <td></td>
            <td><input type="submit" name="submit" value="搜索" style="width:50%;height:180%;cursor:pointer;"></td>
            <td><font style="color:red"><strong><%=search_mess%></strong></font></td>
          </tr>
        </table>
      </form>
    </div>
    
  </body>
</html>
