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
							<li class="active">设备日志管理</li>
						</ul><!-- .breadcrumb -->

					</div>

					<div class="page-content">

						<div class="row">
							<div class="col-xs-12">
										<div class="table-header">
											设备维护日志
										</div>
			<div class="widget-box">
											<div class="widget-body">
												<div class="widget-main">
													<form id="selectForm" class="form-inline">
													    设备名称：<input type="text" id="equipdeviceID" class="input-large" name="t_device_name" placeholder="设备名称" />
														维护人员：<input type="text" id="equipPersonId" class="input-large" name="tCname" placeholder="维护人员姓名"/>
														 <button type="button" class="btn btn-purple btn-sm" onclick="loadPage()">
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
														<th class="center">序号</th>
														<th class="center">设备名称</th>
														<th class="center">维护人员</th>
														<th class="center">维护日期</th>
														<th class="center">操作</th>
													</tr>
												</thead>

												<tbody id="hetbody">

																		
												</tbody>
											</table>
										</div>
										</div>
									</div>
									<!-- 分页  -->
									<div id="Pagination" class="center sabrosus"></div>
						
						</div><!-- /.row -->
					</div><!-- /.page-content -->
				</div><!-- /.main-content --> 

			</div><!-- /.main-container-inner -->
		<div>
			<a href="#" id="btn-scroll-up" class="btn-scroll-up btn btn-sm btn-inverse">
				<i class="icon-double-angle-up icon-only bigger-110"></i>
			</a>
		</div><!-- /.main-container -->
<div class="modal fade" id="myAddModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-hidden="true">
					&times;
				</button>
				<h4 class="modal-title" id="myModalLabel">
					设备维修日志信息
				</h4>
			</div>
			<div class="modal-body">
				<form id="myaddForm" class="form-horizontal" role="form">
					<div class="form-group">
						<label class="col-sm-3 control-label no-padding-right" for="form-field-1"> 设备名称 </label>
							<input type="hidden" id="tLogId" name="tLogId">
						<div class="col-sm-9">
							<input type="text" id="t_device_name" name="t_device_name" placeholder="设备名称" class="col-xs-10 col-sm-5" />
						</div>
					</div>
					
					<div class="form-group">
						<label class="col-sm-3 control-label no-padding-right" for="form-field-1"> 维修人员</label>

						<div class="col-sm-9">
							<input type="text" id="tCname" name="tCname" placeholder="维修人员" class="col-xs-10 col-sm-5" />
						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-md-3">维修日期</label>
						<div class="col-md-9">
							<div id="fixdateID" class="input-group date form_date col-md-10"
							data-date-format="yyyy-MM-dd" data-link-field="dtp_input2" data-link-format="yyyy-mm-dd">
							<input type="text" id="tLogAddtime" name="tLogAddtime" class="form-control" size="16" readonly>
							<span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
							</div>
						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-md-3">维修日志记录</label>
						<div class="col-md-9">
							<textarea class="form-control" rows="10" id="tFixContent" name="tFixContent"></textarea>
						</div>
					</div>									
				</form>
			</div>
			<div class="modal-footer">
				<button type="button" id="btn" class="btn btn-primary" onclick="mysubmit();">
					确定
				</button>
				<button type="button" class="btn btn-default" data-dismiss="modal">
					取消
				</button>
			</div>
		</div><!-- /.modal-content -->
	</div><!-- /.modal -->
</div>

<div class="modal fade" id="mydeleteConfirmID" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-hidden="true">
					&times;
				</button>
				<h4 class="modal-title" id="myModalLabel">
					删除消息
				</h4>
			</div>
			<div class="modal-body">
				确定要删除数据么？
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal">关闭
				</button>
				<button type="button" class="btn btn-primary" onclick="deleteSysUser()">
					提交
				</button>
			</div>
		</div><!-- /.modal-content -->
	</div><!-- /.modal -->
</div>

		 <%@include file="footer.jsp" %>	
</body>

<script>

 var globaltLogId="";
 var pageIndex = 0;
 var pageSize = 5;
 var pageCount = "";
 
 //分页
function mypage(){
	 $("#Pagination").pagination(pageCount,{
		 callback:PageCallback,
		 prev_text:"<< 上一页",
	     next_text:"下一页   >>",
		 current_page:pageIndex,
	     items_per_page:pageSize
	 });
	 function PageCallback(index){
		 loadPage(index,pageSize);
	 }
 }
 
//当点击修改按钮时触发的事件将信息展示在模态窗中	
function openEditWin(tLogId)
{	addWin();
	$("#tLogId").val(tLogId);
	var editURL="openEquiLogpUpdateWin.do";
	$.ajax({
	    type: "get",
	    url:editURL,
	    data:{
	    	tLogId:tLogId
	    	},
	    dataType: "text",
	    success: function(data) {
	    	var jsonArray = $.parseJSON(data);
	    	$("#t_device_name").val(jsonArray.t_device_name);
	    	$("#tCname").val(jsonArray.tCname);
	    	$("#tLogAddtime").val(jsonArray.tLogAddtime);
	    	$("#tFixContent").val(jsonArray.tFixContent);
	     	$('#myAddModal').modal('show');   
	    }
	})
	
}
	
//修改设备日志管理信息的事件
function mysubmit(){
	   var addurl = "addOrUpdateEquipLog.do";
	$.ajax({
	    type: "get",
	    url:addurl,
	    data:$('#myaddForm').serialize(),
	    success: function(data) {
	    	alert(data);
	    	loadPage();
	     	$('#myAddModal').modal('hide');   
	    }
	})
	
} 
	
//调用打开删除的模态窗
 function openDeleteConfirm(tLogId)
{
	globaltLogId = tLogId;
	$("#mydeleteConfirmID").modal("show");
} 

//删除事件
function deleteSysUser(){
	var addurl = "delteEquipLog.do";
	$.ajax({
	    type: "get",
	    url:addurl,
	    data:{tLogId:globaltLogId}, 
	    dataType: "text",
	    success: function(data) {
     		$("#mydeleteConfirmID").modal("hide");
     		alert(data);
		   	loadPage();
	    }
	})
	
}

//当页面加载时发生的事件或当点击查询按钮时触发的事件
function loadPage(index,pageSize)
{		//alert(2);
	   var mycontent = document.getElementById("hetbody");
	   var htmls ="";
	   mycontent.innerHTML="";
		$.ajax({
		    type: "get",
		    url:"<%=basePath%>getEquipLog.do",
		    data:{
		    	curpage:index,
   	    		page_Size:pageSize,
    	    	t_device_name:$("#equipdeviceID").val(),
   	    		tCname:$("#equipPersonId").val() 
		    },
		    success: function(data) {
		    	//alert(data);
	               var jsonArray = $.parseJSON(data);
	               var arr = jsonArray.list;
	               pageCount=jsonArray.totalPage;
	               pageIndex = jsonArray.currPage;
	               pageSize = jsonArray.pageSize;
	               var sno;
	               for(var temp in arr)
	               	{
	            	        sno = parseInt(temp)+1;
	               	         
						htmls+='<tr><td class="center"><label><input type="checkbox" class="ace" /><span class="lbl"></span></label></td><td><a href="#">'+sno+'</a></td><td>'+arr[temp].t_device_name+'</td><td class="hidden-480">'+arr[temp].tCname+'</td><td class="hidden-480">'+arr[temp].tLogAddtime+'</td><td><div class="visible-md visible-lg hidden-sm hidden-xs btn-group">'
					    +'<button class="btn btn-xs btn-info"><i class="icon-edit bigger-120" onclick="openEditWin(\''+arr[temp].tLogId+'\');"></i></button><button class="btn btn-xs btn-danger"><i class="icon-trash bigger-120" onclick="openDeleteConfirm(\''+arr[temp].tLogId+'\');"></i></button><a class="blue" href="javascript:openAtWin(\''+arr[temp].tLogId+'\')">详情</a></div></td></tr>';
	               	}
	               		//alert(htmls);
	                   mycontent.innerHTML = htmls; 
	                   mypage();
		    }
		});

}

//当点击添加按钮的时候触发打开模态窗事件
function addWin()
{		
      $("#myAddModal").modal("show");

} 

//点击详情事件
function openAtWin(obj){
	openEditWin(obj);
	$("#btn").attr("disabled",true);
}


	
//页面加载时要执行的事件
$(document).ready(function(){
	//alert(2);
	/* getSelectDicType(); */
	loadPage(pageIndex,pageSize);
	
});

//日期
$("#fixdateID").datetimepicker({
		      language:'zh-CN',
			  weekStart:1,
		      todayBtn: true,
		      autoclose: true,
		      todayHighlight:1,
		      startView:2,
		      minView:2,
			  forceParse:0 
})

</script>
</html>

    