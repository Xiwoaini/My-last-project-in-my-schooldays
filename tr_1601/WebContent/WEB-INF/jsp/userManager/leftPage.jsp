<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
  <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div class="sidebar" id="sidebar">
		<ul class="nav nav-list">
			<li class="active">
				<a href="javascript:void(0)">
					<i class="icon-dashboard"></i>
					<span class="menu-text"> 控制台 </span>
				</a>
			</li>
				
		 	<c:if test="${userPeople.tRole=='维护人员' }"> 
				<li class="device_active"> 
					<a href="<%=request.getContextPath()%>/device/selectDeviceAll">
						<i class="icon-desktop"></i>
						<span class="menu-text">设备管理</span>
					</a>
				</li>
				<li >
					<a href="<%=request.getContextPath()%>/equipLog.do">
						<i class="icon-picture"></i>
						<span class="menu-text"> 设备日志管理 </span>
					</a>
				</li> 
		 	</c:if>
		 	
		 	<c:if test="${userPeople.tRole=='管理员' }"> 
				<li class="activeSelect">
					<a href="<%=request.getContextPath()%>/device/selectDeviceAll">
						<i class="icon-desktop"></i>
						<span class="menu-text">设备管理</span>
					</a>
				</li>
				<li>
					<a href="<%=request.getContextPath()%>/equipLog.do">
						<i class="glyphicon glyphicon-list-alt"></i>
						<span class="menu-text"> 设备日志管理 </span>
					</a>
				</li> 
				<li>
					<a href="<%=request.getContextPath()%>/agency.do">
						<i class="glyphicon glyphicon-home"></i>
						<span class="menu-text">供应商管理 </span>
					</a>
				</li> 
				<li>
					<a href="<%=request.getContextPath()%>/dicList.do">
						<i class="glyphicon glyphicon-book"></i>
						<span class="menu-text">数据字典管理</span>
					</a>
				</li> 
				<li>
					<a href="<%=request.getContextPath()%>/personManage.do">
						<i class="glyphicon glyphicon-user"></i>
						<span class="menu-text">用户管理</span>
					</a>
				</li> 
		 	</c:if>
		 	
			<li>
				<a href="javascript:void(0)" id="gkstj" style="display: none" onclick="javascript:$('#gkstj').attr('href','#')">
					<i class="icon-text-width"></i>
					<span class="menu-text">各科室设备统计</span>
				</a>
				 
			</li> 
		</ul><!-- /.nav-list -->
	</div>
</body>
</html>