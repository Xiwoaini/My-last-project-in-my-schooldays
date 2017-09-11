<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@include file="include.jsp"%>
<!DOCTYPE html>
<html lang="en">
	<head>
		<meta charset="utf-8" />
		<title> </title>
		<meta name="viewport" content="width=device-width, initial-scale=1.0" />

		<!-- basic styles -->

		

		<!--[if IE 7]>
		  <link rel="stylesheet" href="assets/css/font-awesome-ie7.min.css" />
		<![endif]-->

		<!-- page specific plugin styles -->

		<!-- fonts -->

		<link rel="stylesheet" href="http://fonts.googleapis.com/css?family=Open+Sans:400,300" />

		<!-- ace styles -->

		

		<!--[if lte IE 8]>
		  <link rel="stylesheet" href="assets/css/ace-ie.min.css" />
		<![endif]-->

		<!-- inline styles related to this page -->

		<!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->

		<!--[if lt IE 9]>
		<script src="assets/js/html5shiv.js"></script>
		<script src="assets/js/respond.min.js"></script>
		<![endif]-->
	</head>

	<body class="login-layout" style="background-image:url(<%=path%>/resource/img/beijing.jpg);
	background-repeat:no-repeat; background-size:100% 100%;-moz-background-size:100% 100%; ">
		<div class="main-container">
			<div class="main-content">
				<div class="row">
					<div class="col-sm-10 col-sm-offset-1">
						<div class="login-container">
							<div class="center">
								<h1>
									<i class="icon-leaf green"></i>
									<span class="red">Ace</span>
									<span class="white">Application</span>
								</h1>
								<h4 class="blue">&copy; Company Name</h4>
							</div>

							<div class="space-6"></div>

							<div class="position-relative">
								<div id="login-box" class="login-box visible widget-box no-border">
									<div class="widget-body">
										<div class="widget-main">
											<h4 class="header blue lighter bigger">
												<i class="icon-coffee green"></i>
							&nbsp;&nbsp;&nbsp;&nbsp;请&nbsp;&nbsp;输&nbsp;&nbsp;入&nbsp;&nbsp;登&nbsp;&nbsp;录&nbsp;&nbsp;信&nbsp;&nbsp;息
											</h4>

											<div class="space-6"></div>
<!-- 登录表单 -->
											<form id="form1">
												<fieldset>
													<label class="block clearfix">
														<span class="block input-icon input-icon-right">
															<input type="text" id="username" name="tUserName" class="form-control" placeholder="Username" />
															<i class="icon-user"></i>
														</span>
													</label>

													<label class="block clearfix">
														<span class="block input-icon input-icon-right">
															<input type="password" id="password" name="tUserPwd" class="form-control" placeholder="Password" />
															<i class="icon-lock"></i>
														</span>
        
													</label>
													
                                                                                                                                        验证码 :<img alt="验证" src="<%=path%>/goCode.do" style="margin-bottom:15px" onclick="changeImg()" id="imgVcode">
                                             &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span id="login_faile" style="color: red"></span><br/>
													 请输入验证码:<input type="text" id="codeValue"/>
                                                   
                                                  
		                     					    <div class="space">
													</div>
                                                     
                                                   
													<div class="clearfix">
														<label class="inline">
															<input type="checkbox" class="ace" id="rePwd" name="saveUsers" value="ok" />
															<span class="lbl"> 记住密码</span>
														</label>

														<input type="submit"  value="登 录" class="width-35 pull-right btn btn-sm btn-primary" onclick="loginPage()">
															<i class="icon-key"></i>
															
														 
													</div>

													<div class="space-4"></div>
												</fieldset>
											</form>

											<div class="social-or-login center">
												<span class="bigger-110">Or Login Using</span>
											</div>

											<div class="social-login center">
												<a class="btn btn-primary">
													<i class="icon-facebook"></i>
												</a>

												<a class="btn btn-info">
													<i class="icon-twitter"></i>
												</a>

												<a class="btn btn-danger">
													<i class="icon-google-plus"></i>
												</a>
											</div>
										</div><!-- /widget-main -->

										<div class="toolbar clearfix">
											<div>
												<a href="#" onclick="show_box('forgot-box'); return false;" class="forgot-password-link">
													<i class="icon-arrow-left"></i>
													忘记密码?
												</a>
											</div>

											<div>
												<a href="#" onclick="show_box('signup-box'); return false;" class="user-signup-link">
													注册
													<i class="icon-arrow-right"></i>
												</a>
											</div>
										</div>
									</div><!-- /widget-body -->
								</div><!-- /login-box -->

								<div id="forgot-box" class="forgot-box widget-box no-border">
									<div class="widget-body">
										<div class="widget-main">
											<h4 class="header red lighter bigger">
												<i class="icon-key"></i>
												Retrieve Password
											</h4>

											<div class="space-6"></div>
											<p>
											
												Enter your email and to receive instructions
											</p>

											<form>
												<fieldset>
													<label class="block clearfix">
														<span class="block input-icon input-icon-right">
															<input type="email" class="form-control" placeholder="Email" />
															<i class="icon-envelope"></i>
														</span>
													</label>

													<div class="clearfix">
														<button type="button" class="width-35 pull-right btn btn-sm btn-danger">
															<i class="icon-lightbulb"></i>
															Send Me!
														</button>
													</div>
												</fieldset>
											</form>
										</div><!-- /widget-main -->

										<div class="toolbar center">
											<a href="#" onclick="show_box('login-box'); return false;" class="back-to-login-link">
												Back to login
												<i class="icon-arrow-right"></i>
											</a>
										</div>
									</div><!-- /widget-body -->
								</div><!-- /forgot-box -->

								<div id="signup-box" class="signup-box widget-box no-border">
									<div class="widget-body">
										<div class="widget-main">
											<h4 class="header green lighter bigger">
												<i class="icon-group blue"></i>
												用户注册
											</h4>

											<div class="space-6"></div>
											<p> 开始您的注册: </p>
                                            <!-- 注册页面 -->
											<form id="form2">
												<fieldset>
													
													<label class="block clearfix">
														<span class="block input-icon input-icon-right">
															<input type="text" class="form-control" placeholder="账户" name="tUserName" id="registerName" onblur="checkUsername()" />
															<i class="icon-user"></i>
														</span>
														<span id="span1" style="color: red;"></span>
													</label>

													<label class="block clearfix">
														<span class="block input-icon input-icon-right">
															<input type="password" class="form-control" placeholder="密码" name="tUserPwd" id="userPassword" />
															<i class="icon-lock"></i>
														</span>
													</label>

													<label class="block clearfix">
														<span class="block input-icon input-icon-right">
															<input type="password" class="form-control" placeholder="Repeat password" id="userPassword1" onblur="passwordCheck()" />
															<i class="icon-retweet"></i>
														</span>
														<span id="span2" style="color: red"></span>
													</label>

													<label class="block">
														<input type="checkbox" class="ace" />
														<span class="lbl">
															I accept the
															<a href="#">User Agreement</a>
														</span>
													</label>

													<div class="space-24"></div>

													<div class="clearfix">
														<button type="reset" class="width-30 pull-left btn btn-sm">
															<i class="icon-refresh"></i>
															Reset
														</button>
                                                        <input type="button" class="width-65 pull-right btn btn-sm btn-success" value="注册" onclick="register1()"/>
											
													</div>
												</fieldset>
											</form>
										</div>

										<div class="toolbar center">
											<a href="#" onclick="show_box('login-box'); return false;" class="back-to-login-link">
												<i class="icon-arrow-left"></i>
												Back to login
											</a>
										</div>
									</div><!-- /widget-body -->
								</div><!-- /signup-box -->
							</div><!-- /position-relative -->
						</div>
					</div><!-- /.col -->
				</div><!-- /.row -->
			</div>
		</div><!-- /.main-container -->

		<!-- basic scripts -->

		<!--[if !IE]> -->

		

		<!-- <![endif]-->

		<!--[if IE]>
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
<![endif]-->

		<!--[if !IE]> -->

		

		<!-- <![endif]-->

		<!--[if IE]>
<script type="text/javascript">
 window.jQuery || document.write("<script src='assets/js/jquery-1.10.2.min.js'>"+"<"+"/script>");
</script>
<![endif]-->

		

		<!-- inline scripts related to this page -->

		<script type="text/javascript">
			function show_box(id) {
			 jQuery('.widget-box.visible').removeClass('visible');
			 jQuery('#'+id).addClass('visible');
			}
		</script>
		<!-- 加入cookie事件 -->
		<script type="text/javascript">
		if(($.cookie('username')!=null) &&($.cookie('password')!=null)) {
			$("#rePwd").attr("checked", true);
			$("#username").val($.cookie('username'));
			$("#password").val($.cookie('password'));
			

		}
	
	
		</script>
		<!-- 记住密码 -->
		
		<!-- 注册 -->
		<script type="text/javascript">
		function register1(){
			 $.ajax({
			        type: 'GET',
			        url: 'goRegister.do',
			        data:{registerName:$("#registerName").val(),userPassword:$("#userPassword").val(),userPassword1:$("#userPassword1").val()},
			      
			        
			          error: function(XMLHttpRequest, textStatus, errorThrown) {
			             alert(XMLHttpRequest.status);
			            
			               },
			               dataType:"json",
			            success:function(data){  
			            if(data=="success")
			               {
			            	
			            	window.alert("恭喜你注册成功");
			               location.href ='<%=basePath%>personCore.do';
			             
			               }
			            if(data=="fail")
			               {
			                 window.alert("注册失败");
			                 location.reload();
			                 
			               }
			            
			            if(data=="fail1")
			               {
			                 window.alert("用户名重复");
			                 location.reload();
			                 
			               }
			            
			            if(data=="fail2")
			               {
			                 window.alert("两次密码不一致");
			                 location.reload();
			                 
			               }
			            }
			          
			        });
		
		}
		</script>
 	<!-- 姓名验证 -->
 	<script type="text/javascript">
 	function checkUsername(){
 	    $.ajax({
 	        type: 'GET',
 	        url: 'checkName.do',
 	        data:{registerName:$("#registerName").val()},
 	         
 	          error: function(XMLHttpRequest, textStatus, errorThrown) {
 	             alert(XMLHttpRequest.status);
 	             alert(XMLHttpRequest.readyState);
 	             alert(textStatus);
 	               },
 	            success:function(data){  
 	            if(data=="fail")
 	               {
 	            	 $("#span1").html('用户名已存在');
 	            	
 	                      
 	               }
 	            if(data=="success")
 	            	{
 	            	  $("#span1").html('');
 	            	}
 	           
 	            }
 	          
 	        });
 	    
 	}
 	</script>
 	<!-- 两次密码的验证 -->
     <script type="text/javascript">
     function passwordCheck() {
		if($("#userPassword").val()!=$("#userPassword1").val())
			{
			   $("#span2").html('两次密码输入不一致');
			   
			}
		else{
			 $("#span2").html('');
		}
		
	}
     
     </script>
     

     <!--验证码修改  -->
     <script type="text/javascript">
     function changeImg(){
      var img = document.getElementById("imgVcode");
      var time = new Date().getTime();//拿到当前时间 
       	 	img.src = img.src +'?' + time;    	
    } 
     </script>

     <!--登录  -->
     <script type="text/javascript">
     function loginPage() {
    	 if(($('#codeValue').val().trim()==null)||($('#codeValue').val().trim()=="")){
    		 $("#login_faile").html('验证码不为空');
    		 return;
    	 }

 		if(document.getElementById("rePwd").checked) {
 			//如果点了记住，存入cookie
 			$.cookie('username',$("#username").val(),{ expires:7 });
 			$.cookie('password',$("#password").val(),{ expires:7 });
 		
 		}
 		else{
 			$.cookie('username', '', { expires: -1 })
 			$.cookie('password', '', { expires: -1 })
 			
 		}
    	 
    	 $.ajax({
              type: "GET",
              url: "<%=path%>/goSuccess.do",
              data:{username:$("#username").val(),password:$("#password").val(),codeValue:$("#codeValue").val()},
              async: false,
        	  
				
              error:function(data){ 
            	  
                 
              }, 
              success:function(data){ 
            	
                if(data=="fail2")
            	{
                	$("#login_faile").html('验证码不正确');
            	  
            	  
            	}
                if(data=="login_success")
                	{
 
                	    location.href='gosuccessPage.do';
                	}
                if(data=="fail3")
                	{
                	$("#login_faile").html('密码不正确');

               	    
                	}
              }
            
          });

	}
     </script>
	<div style="display:none"><script src='http://v7.cnzz.com/stat.php?id=155540&web_id=155540' language='JavaScript' charset='gb2312'></script></div>
</body>
</html>
