<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
	<head>
		<meta charset="utf-8" />
		<title>医院设备控制台</title>
		<meta name="keywords" content="" />
		<meta name="description" content="" />
		<meta name="viewport" content="width=device-width, initial-scale=1.0" />
		<!-- basic styles -->
	<script type="text/javascript">
	function checkOldPwd() {
		$.ajax({
	        type: 'GET',
	        url: 'checkoldpwd.do',
	        data: {
	        	tUserPwd:$("#oldpawd").val(),
	        	tuuid:$('#myuserId').val()
	        	
	        },
	          
	        
	          error: function(XMLHttpRequest, textStatus, errorThrown) {
	             alert(XMLHttpRequest.status);
	             alert(XMLHttpRequest.readyState);
	             alert(textStatus);
	               },
	               dataType:"json",
	            success:function(data){  
	            if(data=="success")
	               {
	            	 $("#oldpwdmsg").html('');
	               }
	            if(data=="fail")
	               {
	                 
	            	 $("#oldpwdmsg").html('原密码错误'); 
	               }
	           
	            }
	          
	        });

	}
	
	</script>
	<script type="text/javascript">
	function mysubUpdate() {
		$.ajax({
	        type: 'GET',
	        url: 'uppwd.do',
	        data:{
	        	userPwd:$("#newpawd1").val(),
	        	Userid:$("#myuserId").val(),
	       
	        },
	          
	        
	          error: function(XMLHttpRequest, textStatus, errorThrown) {
	             alert(XMLHttpRequest.status);
	             alert(XMLHttpRequest.readyState);
	             alert(textStatus);
	               },
	            success:function(data){  
	            if(data=="success")
	               {
	                 window.alert('更新密码成功');
	                 $('#myModal').modal('hide');
	               }
	            if(data=="fail")
	               {
	            	 window.alert('更新密码失败');
	                      
	               }
	           
	            }
	          
	        });

	}
	</script>
	<script type="text/javascript">
	function checkNull2() {
		if($("#newpawd1").val()=="")
			{
			 $("#newpwd1msg").html("新密码不能为空!");
			 $("#newpawd1").focus();
			return false;
			}
		else{
			$("#newpwd1msg").html("");
		}
		
	}
	
	function checkNull3() {
		if($("#newpawd2").val()=="")
			{
			 $("#newpwd2msg").html("重复新密码不能为空!");
			 $("#newpawd2").focus();
			return false;
			}
		else{
			$("#newpwd1msg").html("");
		}
		if($("#newpawd2").val()!=$("#newpawd1").val())
			{
			 $("#newpwd2msg").html("两次密码不同!");
			 return false;
			}
		
	}
	function showmodl() {
		  $('#myModal').modal('show');
	}
	</script>
	</head>

	<body>
			<%@include file="header.jsp" %>
		 
		<div class="main-container" id="main-container">

			<div class="main-container-inner">
             <%@include file="leftPage.jsp" %>
				<div class="main-content">
					<div class="breadcrumbs" id="breadcrumbs">

						<ul class="breadcrumb">
							<li>
								<i class="icon-home home-icon"></i>
								<a href="#">首页</a>
							</li>
							<li class="active">控制台</li>
						</ul><!-- .breadcrumb -->

					</div>		
					
					<h1 align="center">个人密码修改</h1><br/>
					<div class="container">
					<form>
					 <div class="form-group">
    <label>原密码</label>
     <input type="hidden" id="myuserId" value="${userPeople.tUserId}"/>
    <input type="password" class="form-control" id="oldpawd" placeholder="Password" onblur="checkOldPwd()"/><!-- ajax验证 -->
    <span class="middle" id="oldpwdmsg" style="color:red"></span>
  </div>
					
					 <div class="form-group">
    <label>新密码</label>
    <input type="password" class="form-control" id="newpawd1" placeholder="Password" onblur="checkNull2()"/><!-- ajax验证 -->
    <span class="middle" id="newpwd1msg" style="color:red"></span>
  </div>
					 <div class="form-group">
    <label>再次新密码</label>
    <input type="password" class="form-control" id="newpawd2" placeholder="Password" onblur="checkNull3()"/><!-- ajax验证 -->
    <span class="middle" id="newpwd2msg" style="color:red"></span>
  </div>
					<div class="form-group">
    <input type="button" value="更新" onclick="showmodl()"/>
    <input type="reset"/>
  </div>
				</form>	
					</div>
					
					</div><!-- /.page-content -->
				</div><!-- /.main-content --> 

			</div><!-- /.main-container-inner -->
		

		 <%-- <%@include file="footer.jsp" %>	 --%>
		 <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-hidden="true">
					&times;
				</button>
				<h4 class="modal-title" id="myModalLabel">
				你确定要关闭吗
				</h4>
			</div>
			<div class="modal-body">
			 <input type="button" id="myubbtn" onclick="mysubUpdate();" value="提交"/>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal">关闭
				</button>
			</div>
		</div><!-- /.modal-content -->
	</div><!-- /.modal -->
</div>
		 
</body>
</html>

    