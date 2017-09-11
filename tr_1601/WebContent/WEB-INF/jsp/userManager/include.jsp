<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    <%
		String path = request.getContextPath();
		String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
	%>
	<script type="text/javascript" src="<%=path%>/resource/jquery/jquery.min.js"></script>
<script type="text/javascript" src="<%=basePath%>resource/bootstrap/bootstrap.min.js"></script>
<link href="<%=basePath%>resource/bootstrap/bootstrap.min.css" rel="stylesheet" />
<link rel="stylesheet" href="<%=basePath%>resource/css/font-awesome.min.css" />
<link rel="stylesheet" href="<%=basePath%>resource/css/ace.min.css" />
<link rel="stylesheet" href="<%=basePath%>resource/css/ace-rtl.min.css" />
<link rel="stylesheet" href="<%=basePath%>resource/css/ace-skins.min.css" />
<link rel="stylesheet" href="<%=basePath%>resource/bootstrap/bootstrap-datetimepicker.min.css" />
<script type="text/javascript" src="<%=basePath%>resource/bootstrap/bootstrap-datetimepicker.js"></script>
<script type="text/javascript" src="<%=basePath%>resource/bootstrap/bootstrap-datetimepicker.zh-CN.js"></script>
<link rel="stylesheet" href="<%=basePath%>resource/jquery/pagination.css" />
<script type="text/javascript" src="<%=basePath%>resource/jquery/jquery.paging.min.js"></script>
<script type="text/javascript" src="<%=basePath%>resource/uploadFile/ajaxfileupload.js"></script> 
<script type="text/javascript" src="<%=basePath%>resource/jquery/jquery.cookie.js"></script>
 		  		
    	
		
		