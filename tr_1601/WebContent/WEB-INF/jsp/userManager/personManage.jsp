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
							<li class="active">用户管理</li>
						</ul><!-- .breadcrumb -->

					</div>		
					
					
					
					<!-- 用户管理主体 -->
					
					
					<div class="page-content">

						<div class="row">
							<div class="col-xs-12">
										<div class="table-header">
											用户管理
										</div>
			<div class="widget-box">
											<div class="widget-body">
												<div class="widget-main">
													<form class="form-inline">
													    用户名：<input type="text" id="searchUserNameId" class="input-large" placeholder="用户名称"
													    maxlength="8"
										onkeyup="this.value=this.value.replace(/\s+/g,'')"
													     />
														 <button type="button" class="btn btn-purple btn-sm" onclick="loadPage(pageIndex,pageSize)">
																			<i class="icon-search icon-on-right bigger-110"></i>
																			查询
														 </button>
															
													</form>
												</div>
											</div>
										</div>
										<div class="table-responsive">
											<table id="sample-table-2" class="table table-striped table-bordered table-hover">
												<thead>
													<tr>
														<th class="center">
															<label>
																<input type="checkbox" class="ace" />
																<span class="lbl"></span>
															</label>
														</th>
														<th class="center">用户名</th>
														<th class="center">真实姓名</th>
														<th class="center">权限角色</th>
														<th class="center">性别</th>
														<th class="center">科室</th>
														<th class="center">联系方式</th>
														<th class="center">email</th>
														<th class="center">操作</th>
														<th class="center">共有操作</th>
													
													</tr>
												</thead>

												<tbody id="hetbody">
													<!-- <tr><td class="center"><label><input type="checkbox" class="ace" /><span class="lbl"></span></label></td><td><a href="#">ace.com</a></td><td>Feb 12</td><td class="hidden-480"><span class="label label-sm label-warning">Expiring</span></td><td><div class="visible-md visible-lg hidden-sm hidden-xs btn-group">
												    <button class="btn btn-xs btn-success"><i class="icon-ok bigger-120"></i></button><button class="btn btn-xs btn-info"><i class="icon-edit bigger-120" onclick="openEditWin('123');"></i></button><button class="btn btn-xs btn-danger"><i class="icon-trash bigger-120"></i></button><button class="btn btn-xs btn-warning"><i class="icon-flag bigger-120"></i></button></div></td></tr>
															 -->
																		
												</tbody>
											</table>
											
											<!-- 删除的模态创 -->
											 <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog modal-lg">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-hidden="true">
					&times;
				</button>
				<h4 class="modal-title" id="myModalLabel">
				删除界面
				</h4>
			</div>
			<div class="modal-body">
			  你确定要删除?
			  <input type="hidden" id="delUserId"/>
			  <input type="button" class="btn btn-primary" onclick="deleteUser()" value="确定"/>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal">关闭
				</button>
			</div>
		</div>/.modal-content
	</div>/.modal
</div> 


<!-- 添加权限的模态框 -->
 <div class="modal fade" id="addQuanXianwin" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-hidden="true">
					&times;
				</button>
				<h4 class="modal-title" id="myModalLabel">
				添加权限
				</h4>
			</div>
			<div class="modal-body">
			<form action="">
			  <div class="form-group">
                 <input type="hidden" class="form-control" id="roleId">  
                <input type="hidden" class="form-control" id="roleUserId"><!-- * -->
              </div>  
             
               <div class="form-group">
                <label>用户姓名</label>
                <input type="text" class="form-control" id="realname"><!-- * -->
              </div>  
              
               <div class="form-group">
                <label>权限</label>
                 <select id="myquanxian1" class="selectpicker">
                 </select>
                 <!--  <select id="myquanxian2" class="selectpicker">
                 </select> -->
           
              </div>  
              
           <input type="button" onclick="updateUserRole()" value="确定"/>   
			</form>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal">关闭
				</button>
			</div>
		</div><!-- /.modal-content -->
	</div><!-- /.modal -->
</div>


											
											
										</div>
										</div>
									</div>
									<div id="Pagination" class="center sabrosus"></div>
						
						</div><!-- /.row -->
					
					
					
					
					
					
					
					
					</div><!-- /.page-content -->
				</div><!-- /.main-content --> 

			</div><!-- /.main-container-inner -->
		

		 <%-- <%@include file="footer.jsp" %>	 --%>
</body>
<script type="text/javascript">
	var pageIndex =0;
	var pageSize =5;
	var pageCount="";
	//页面加载时要执行的事件
$(document).ready(function(){

	loadPage(pageIndex,pageSize);
	getSelectDic();
	
	
	
});
//加载分页条数
function mypagination() {
	$("#Pagination").pagination(pageCount,{
		callback:PageCallback,
		prev_text:"<<上一页",
		next_text:"下一页>>",
		items_per_page:pageSize,
		current_page:pageIndex
	});
	function PageCallback(index) {
		
	 loadPage(index,pageSize);
		
	}
}	
//加载主页面
	function loadPage(index,pageSize)
{
	   var mycontent = document.getElementById("hetbody");
	   var htmls ="";
	   mycontent.innerHTML="";
     var searchName=$("#searchUserNameId").val();
	   	    $.get("getUsermanage.do",
	   	    	  {
	   	    	        
	   	    			tUserName:searchName,
	   	    			curpage:index,
	   	    			pageSize:pageSize
		       	   },
	   	    	function (data) {
		       		   
	               var jsonArray = $.parseJSON(data);
	               var sno;
	               
	               
	             //当前页内容	
	              // var arr=jsonArray.list;
	               pageCount= jsonArray.totalCount;
	               pageIndex=jsonArray.currPage;
	               pageSize=jsonArray.pageSize;
	       
	               for(var temp in jsonArray.list)
	               	{
	            	        sno = parseInt(temp)+1;
	               	         
						htmls+='<tr><td class="center"><label><input type="checkbox" class="ace" /><span class="1b1"></span></label></td><td class="center">'+jsonArray.list[temp].tUserName+'</td><td class="center">'+jsonArray.list[temp].tCname+'</td>'
						+'<td class="center">'+jsonArray.list[temp].tRole+'</td>'
						+'<td class="center">'+jsonArray.list[temp].tGender+'</td>'
						+'<td class="center">'+jsonArray.list[temp].tDicId+'</td>'
						+'<td class="center">'+jsonArray.list[temp].tMobile+'</td>'
						+'<td class="center">'+jsonArray.list[temp].tEmail+'</td>';

						if(jsonArray.list[temp].tStatus=='a')
							{
							   
							  htmls+='<td class="center"><button class="btn btn-xs btn-warning" onclick="updateUserStatus(\''+jsonArray.list[temp].tUserId+'\');">未审核</button></td>'
								  
							}
						else{
							  
							 htmls+='<td class="center"><button class="btn btn-xs btn-success" onclick="updateUserStatus(\''+jsonArray.list[temp].tUserId+'\');">审核</button></td>'
			
						} 
							  
						 htmls+='<td class="center"><button  class="btn btn-xs btn-danger" onclick="pwdReset(\''+jsonArray.list[temp].tUserId+'\');">密码重置</button><button  class="btn btn-xs btn-warning" onclick="openQuanxianwin(\''+jsonArray.list[temp].tUserId+'\',\''+jsonArray.list[temp].tRoleDicID+'\',\''+jsonArray.list[temp].tCname+'\',\''+jsonArray.list[temp].tDicId+'\');">权限分配</button><a class="red" href="javascript:openDeleteConfirmWin(\''+jsonArray.list[temp].tUserId+'\');"><i class="icon-trash bigger-120"></i></a></td></tr>'
							
						 
						
	               	
	               	}
						
	               	
	               		
	                   mycontent.innerHTML = htmls;
	                   mypagination();
	                   
	        });
}
//将用户状态从-1改为正1
   function updateUserStatus(myuserId) {
	   $.ajax({
	        type: 'GET',
	        url: 'updateUserStatus.do',
	        data:{tUserId:myuserId,tStatus:1},
	          
	        
	          error: function(XMLHttpRequest, textStatus, errorThrown) {
	             alert(XMLHttpRequest.status);
	             alert(XMLHttpRequest.readyState);
	             alert(textStatus);
	               },
	               dataType:"json",
	            success:function(data){  
	            if(data=="success")
	               {
	            	window.alert("成功");
	            	loadPage(pageIndex,pageSize);
	            	
	               }
	            if(data=="fail")
	               {
	            	window.alert("失败");
	            	loadPage(pageIndex,pageSize);
	                      
	               }
	           
	            }
	          
	        });
	    

}
function pwdReset(myuserId) {
	 $.ajax({
	        type: 'GET',
	        url: 'Resetpwd.do',
	        data:{tUserId:myuserId,tUserPwd:"0000"},
	          
	        
	          error: function(XMLHttpRequest, textStatus, errorThrown) {
	             alert(XMLHttpRequest.status);
	             alert(XMLHttpRequest.readyState);
	             alert(textStatus);
	               },
	               dataType:"json",
	            success:function(data){  
	            if(data=="success")
	               {
	            	window.alert("成功");
	            	loadPage(pageIndex,pageSize);
	            	
	               }
	            if(data=="fail")
	               {
	            	window.alert("失败");
	            	loadPage(pageIndex,pageSize);
	                      
	               }
	           
	            }
	          
	        });
}

function openDeleteConfirmWin(userid) {
	$("#delUserId").val(userid);
	$("#myModal").modal('show');
}
function deleteUser() {
	var myuserid=$("#delUserId").val();
	 $.ajax({
	        type: 'GET',
	        url: 'delUser.do',
	        data:{tUserId:myuserid},
	          
	        async:false,
	          error: function(XMLHttpRequest, textStatus, errorThrown) {
	             alert(XMLHttpRequest.status);
	             alert(XMLHttpRequest.readyState);
	             alert(textStatus);
	               },
	               dataType:"json",
	            success:function(data){  
	            if(data=="success")
	               {
	            	window.alert("成功");
	            	$("#myModal").modal('hide');
	            	loadPage(pageIndex,pageSize);
	            	
	               }
	            if(data=="fail")
	               {
	            	window.alert("失败");
	            	loadPage(pageIndex,pageSize);
	                      
	               }
	           
	            }
	          
	        });
}
//打开模态创
function openQuanxianwin(myuserid,dicCode,realname,dicID) {
	$("#roleUserId").val(myuserid);
	$("#realname").val(realname);
	$("#myquanxian1").val(dicCode);
	$("#addQuanXianwin").modal('show');
	
	
}
//获得模态框的下拉列表
function getSelectDic(){
	var mycontent=document.getElementById("myquanxian1");
	   var htmls ="";
	   mycontent.innerHTML="";
	   htmls+='<option value ="mySelAll" selected="selected">全部</option>';
	$.ajax({
        type: 'GET',
        url: '<%=basePath%>getroledown.do',
        data: {tType:'权限'},
        dataType:'JSON',
          error: function(XMLHttpRequest, textStatus, errorThrown) {
             alert(XMLHttpRequest.status);
             alert(XMLHttpRequest.readyState);
             alert(textStatus);
               },
            success:function(data){  
          
            	  
	       		  var jsonArray = $.parseJSON(data);
               
             var sno;
             for(var temp in jsonArray)
             	{
          	      htmls+='<option value ="'+jsonArray[temp].tDicCode+'">'+jsonArray[temp].tName+'</option>';

             	}
             		//alert(htmls);
                 mycontent.innerHTML = htmls;
            }
          
        });
}



//更新
function updateUserRole() {
	$.ajax({
        type: 'GET',
        url: 'updateUserRolef.do',
        data:{
        	tUserId:$("#roleUserId").val(),
        	tRole:$("#myquanxian1").val(),
        
        },
          
        
          error: function(XMLHttpRequest, textStatus, errorThrown) {
             alert(XMLHttpRequest.status);
             alert(XMLHttpRequest.readyState);
             alert(textStatus);
               },
             dataType:"json",
            success:function(data){  
          	//alert(data);
            if(data=="success")
               {
                 window.alert("更新成功");
	             $("#addQuanXianwin").modal('hide');
	             loadPage(pageIndex,pageSize);

                 
               }
            if(data=="fail")
               {
                 window.alert("更新失败");
                      
               }
           
            }
          
        });

}
	
	</script>
</html>

    