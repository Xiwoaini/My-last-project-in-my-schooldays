<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="include.jsp" %>   	
<style type="text/css">
	.headImgDiv{width:40px; height:40px;}
	.headImgDiv img{width:100%;height:100%;}
</style> 
    	
    	<div class="navbar navbar-default" id="navbar">

			<div class="navbar-container" id="navbar-container">
				<div class="navbar-header pull-left">
					<a href="#" class="navbar-brand">
						<small>
							<i class="icon-leaf"></i>
							
						</small>
					</a><!-- /.brand -->
				</div><!-- /.navbar-header -->
				<div class="navbar-header pull-right" role="navigation">
					<ul class="nav navbar-nav">
						<li>
				               <div style="padding:5px;" class="headImgDiv"><img id="myHeadIconID" class="nav-user-photo" src="<%=path %>/${myappurl}resource/img/avatar5.png" alt="无头像" /></div>
						</li>
						<li class="dropdown">
							<a href="#" class="dropdown-toggle" data-toggle="dropdown">
								<span class="user-info">${userPeople.tUserName}</span>
								<i class="icon-caret-down"></i>
								<span class="user-info">${userPeople.tRole}</span>
							</a>
							<ul class="dropdown-menu" style="min-width:50%; align:center">
								<li><a href="<%=request.getContextPath()%>/personCore.do">个人中心</a></li>
								<li><a href="<%=request.getContextPath()%>/changePwd.do">密码修改</a></li>
								<li><a href="<%=request.getContextPath()%>/mylogout.do">退出系统</a></li>
							</ul>
						</li>
					</ul>
			</div><!-- /.navbar-header -->
		</div><!-- /.container -->
	</div>
<script>
   $(document).ready(function(){
	   		var imgURL="${tperson1.tAtturl}/${tperson1.tAttname}";
	   		if(imgURL!="/")
	   			{
	   		 		$("#myHeadIconID").attr("src",imgURL);  
	   			}
   });

</script>		
		
		