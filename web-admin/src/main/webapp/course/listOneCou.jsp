<%@ page contentType="text/html; charset=UTF-8" pageEncoding="utf-8"%>
<%@ page import="com.course.model.*"%>

<%
  Integer course_no = (Integer) request.getAttribute("course_no"); 
  PhyCouService phyCouSvc = new PhyCouService();
  PhyCouVO phyCouVO = null;
  if (course_no != null) {
  	 phyCouVO = phyCouSvc.getOneCou(course_no);
  } else {
	  phyCouVO = (PhyCouVO) request.getAttribute("phyCouVO"); 
  }
%>

<html>
<head>
<title>課程資料</title>

<style>
  table#table-1 {
	background-color: #CCCCFF;
    border: 2px solid black;
    text-align: center;
  }
  table#table-1 h4 {
    color: red;
    display: block;
    margin-bottom: 1px;
  }
  h4 {
    color: blue;
    display: inline;
  }
</style>

<style>
  table {
	width: 1700px;
	background-color: white;
	margin-top: 5px;
	margin-bottom: 5px;
  }
  table, th, td {
    border: 1px solid #CCCCFF;
  }
  th, td {
    padding: 5px;
    text-align: center;
  }
  #info {
    width:400px;
  }
  #pageHead {
    width: 1700px;
    height: 23%;
  }
</style>

</head>
<body bgcolor='white'>
<img src="<%= request.getContextPath()%>/images/JihaoshiPageHead.jpg" id="pageHead">

<table id="table-1">
	<tr><td>
		 <h3>課程資料</h3>
		 <h4><a href="select_page.jsp" style="margin: 10px auto;text-decoration: none; color: blue; font-weight: 700">回課程管理頁</a>
		 <a href="${ctxPath}/index.jsp" style="margin: auto 30px;text-decoration: none; color: blue; font-weight: 700"> 回首頁 </a>
		 </h4>
	</td></tr>
</table>

<table>
	<tr>
		<th>實體課程編號</th>
		<th>實體課程名稱</th>
		<th>上課時數</th>
		<th>課程費用</th>
		<th>授課教師</th>
		<th>開課日期</th>
		<th>上課教室</th>
		<th id="info">課程簡介</th>
		<th>課程狀態</th>		
		<th>上架日期</th>		
		<th>編輯時間</th>		
		<th>報名開始日期</th>
		<th>報名結束日期</th>
		<th>人數上限</th>
		<th>人數下限</th>
		<th>目前報名人數</th>
		<th>照片</th>	
	
	</tr>
	<tr>
		<td><%=phyCouVO.getCourse_no()%></td>
		<td><%=phyCouVO.getCourse_name()%></td>
		<td><%=phyCouVO.getCourse_hr()%></td>
		<td><%=phyCouVO.getCourse_price()%></td>
		<td><%=phyCouVO.getCourse_teacher()%></td>
		<td><%=phyCouVO.getCourse_date()%></td>
		<td><%=phyCouVO.getCourse_location()%></td>
		<td><%=phyCouVO.getCourse_info()%></td>
		<td><%=phyCouVO.getCourse_status()%></td>
		<td><%=phyCouVO.getCreate_date()%></td>
		<td><%=phyCouVO.getUpdate_time()%></td>
		<td><%=phyCouVO.getSign_up_start_day()%></td>
		<td><%=phyCouVO.getSign_up_end_day()%></td>
		<td><%=phyCouVO.getMax_sign_up_people()%></td>
		<td><%=phyCouVO.getMin_sign_up_people()%></td>
		<td><%=phyCouVO.getCurrent_sign_up_people()%></td>
		<td><img src="<%=request.getContextPath()%>/course/DBGifReader?course_no=<%=phyCouVO.getCourse_no()%>" width="150px"></td>
		  
</table>

</body>
</html>
