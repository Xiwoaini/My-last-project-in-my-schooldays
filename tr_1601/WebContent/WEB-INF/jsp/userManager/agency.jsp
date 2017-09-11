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
							<li class="active">供应商管理</li>
						</ul><!-- .breadcrumb -->

					</div>

					<div class="page-content">

						<div class="row">
							<div class="col-xs-12">
										<div class="table-header">
											供应商列表
										</div>
			<div class="widget-box">
											<div class="widget-body">
												<div class="widget-main">
													<form id="selectForm" class="form-inline">
													    供应商：<input type="text" id="tAgencyName" class="input-large" name="tAgencyName" placeholder="供应商名称" />
														 <button type="button" class="btn btn-purple btn-sm" onclick="loadPage()">
																			<i class="icon-search icon-on-right bigger-110"></i>
																			查询
														 </button>
														<button type="button" class="btn btn-sm btn-success" onclick="addWin();">
															<i class="icon-ok bigger-110"></i>
															添加
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
														<th class="center">经销商名称</th>
														<th class="center">营业执照编号</th>
														<th class="center">医疗器械经营许可证编号</th>
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
					供应商信息
				</h4>
			</div>
			<div class="modal-body">
				<form id="myaddForm" class="form-horizontal" role="form">
					<div class="form-group">
						<label class="col-sm-3 control-label no-padding-right" for="form-field-1"> 供应商名称 </label>
							<input type="hidden" id="tAgencyId" name="tAgencyId">
						<div class="col-sm-9">
							<input type="text" id="tAgencyNameId" name="tAgencyName" placeholder="供应商名称" class="col-xs-10 col-sm-5" />
						</div>
					</div>
					
					<div class="form-group">
						<label class="col-sm-3 control-label no-padding-right" for="form-field-1"> 营业执照编号</label>

						<div class="col-sm-9">
							<input type="text" id="tLicense" name="tLicense" placeholder="营业执照编号" class="col-xs-10 col-sm-5" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-3 control-label no-padding-right" for="form-field-1">仪器经营许可证</label>
						<div class="col-sm-9">
							<input type="text" id="tPermitNo" name="tPermitNo" placeholder="仪器经营许可证" class="col-xs-10 col-sm-5" />
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

<!-- 附件列表   -->
<div class="modal fade" id="updataFileModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-hidden="true">
					&times;
				</button>
				<h4 class="modal-title" id="myModalLabel">
					供应商附件列表
				</h4>
			</div>
			<div class="modal-body">
				<form id="myaddForm" method="post" action="<%=basePath%>uploadFile.do" class="form-horizontal" enctype="multipart/form-data" >
					<input type="hidden" id="myTagencyId" name="tAgencyId"/>
					
					<div class="form-group" id="myattShowID">

					</div>
					<div class="form-group">
						<label class="col-sm-3 control-label no-padding-right" for="form-field-1">附件上传</label>
						<div class="col-sm-9">
							<input type="file" name="f"  class="from-control" />
						</div>
					</div>		
				
			
					<div class="modal-footer">
						<button type="submit" id="btn" class="btn btn-primary" ><!--onclick="myajaxUpload();  -->
							上传
						</button>
						<button type="button" class="btn btn-default" data-dismiss="modal">
							取消
						</button>
					</div>
				</form>
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

<div class="modal fade" id="mydeleteFile" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
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
				确定要删除该附件么？
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal">关闭
				</button>
				<button type="button" class="btn btn-primary" onclick="deleteFile()">
					提交
				</button>
			</div>
		</div><!-- /.modal-content -->
	</div><!-- /.modal -->
</div>

		 <%@include file="footer.jsp" %>	
</body>

<script>

 var globaltAgencyId="";
 var pageIndex = 0;
 var pageSize = 5;
 var pageCount = "";
 var myTAttid = "";
 
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
function openEditWin(tAgencyId)
{	addWin();
	$("#tAgencyId").val(tAgencyId);
	var editURL="openAgencyUpdateWin.do";
	$.ajax({
	    type: "get",
	    url:editURL,
	    data:{
	    	tAgencyId:tAgencyId
	    	},
	    dataType: "text",
	    success: function(data) {
	    	var jsonArray = $.parseJSON(data);
/* 	    	alert(jsonArray);
	    	alert(jsonArray.tAgencyName); */
	    	$("#tAgencyNameId").val(jsonArray.tAgencyName);
	    	$("#tLicense").val(jsonArray.tLicense);
	    	$("#tPermitNo").val(jsonArray.tPermitNo);
	     	$('#myAddModal').modal('show');   
	    }
	})
	
}
	
//修改和添加供应商信息的事件
function mysubmit(){
	   var addurl = "addOrUpdateAgency.do";
	$.ajax({
	    type: "get",
	    url:addurl,
	    data:$('#myaddForm').serialize(),
	    success: function(data) {
	    	alert(data);
	     	$('#myAddModal').modal('hide');   
	    	loadPage();///////////////
	    }
	})
	
} 
	
//调用打开删除的模态窗
 function openDeleteConfirm(tAgencyId)
{
	globaltAgencyId = tAgencyId;
	$("#mydeleteConfirmID").modal("show");//
} 

//删除事件
function deleteSysUser(){
	var addurl = "delAgency.do";
	$.ajax({
	    type: "get",
	    url:addurl,
	    data:{tAgencyId:globaltAgencyId}, 
	    dataType: "text",
	    success: function(data) {
     		alert(data);
     		$("#mydeleteConfirmID").modal("hide");
		   	loadPage();
	    }
	})
	
}

//当页面加载时发生的事件或当点击查询按钮时触发的事件
function loadPage(index,pageSize)
{		 

	   var mycontent = document.getElementById("hetbody");
	   var htmls ="";
	   mycontent.innerHTML="";
		$.ajax({
		    type: "get",
		    url:"<%=basePath%>getAgency.do",
		    data:{
		    	curpage:index,//pageIndex
   	    		page_Size:pageSize,
   	    		tAgencyName:$("#tAgencyName").val(),   	    		
		    },
		    success: function(data) {
		    	  
	               var jsonArray = $.parseJSON(data);
	               var arr = jsonArray.list;
	               pageCount = jsonArray.totalCount;
	               pageIndex = jsonArray.currPage;
	               pageSize = jsonArray.pageSize;
	               var sno;
	               for(var temp in arr)
	               	{
	            	    sno = parseInt(temp)+1;     
						htmls+='<tr><td class="center"><label><input type="checkbox" class="ace" /><span class="lbl"></span></label></td><td><a href="#">'+sno+'</a></td><td>'+arr[temp].tAgencyName+'</td><td class="hidden-480">'+arr[temp].tLicense+'</td><td class="hidden-480">'+arr[temp].tPermitNo+'</td><td><div class="visible-md visible-lg hidden-sm hidden-xs btn-group">'
					    +'<button class="btn btn-xs btn-info"><i class="icon-edit bigger-120" onclick="openEditWin(\''+arr[temp].tAgencyId+'\');"></i></button><button class="btn btn-xs btn-danger"><i class="icon-trash bigger-120" onclick="openDeleteConfirm(\''+arr[temp].tAgencyId+'\');"></i></button><a class="blue" href="javascript:openAtWin(\''+arr[temp].tAgencyId+'\')">附件列表</a></div></td></tr>';
	               	}
	               		 
	                   mycontent.innerHTML = htmls; 
	               	mypage();
		    }
		});

}

//当点击添加按钮的时候触发打开模态窗事件
function addWin()
{	
		$("#tAgencyNameId").val("");
		$("#tLicense").val("");
		$("#tPermitNo").val("");
	    $("#myAddModal").modal("show");

} 

//点击附件列表事件
function openAtWin(tAgencyId){
	globaltAgencyId=tAgencyId;
	$("#myTagencyId").val(tAgencyId);
	loadAttPage(tAgencyId);
	$("#updataFileModal").modal("show");
	//$("#btn").attr("disabled",true);
}
//当点击附件列表时将可上传的附件展现出来
function loadAttPage(tAgencyId){
	
	var fileObj=document.getElementById("myattShowID");
	var htmls="";
	fileObj.innerHTML="";
	$.get("<%=basePath%>loadShowFile.do",
		{
		tAgencyId:tAgencyId
	},function(data){
		var jsonArray = $.parseJSON(data);
		var sno;
		for(var temp in jsonArray){
			sno = parseInt(temp)+1;
			htmls+='<div class="col-md-12"><div class="form-group"><div class="control-label col-md-3"></div><div class="col-md-9">'+
			'<a href="downLoadFile.do?tAttname='+jsonArray[temp].tAttname+'">'+sno+'.'+jsonArray[temp].tAttname+'</a>'+
			'&nbsp&nbsp&nbsp&nbsp<a class="red" href="javascript:openDeleteFile(\''+jsonArray[temp].tAttid+'\');"><i class="icon-trash bigger-120"></i></a></div></div></div>';
		}
		fileObj.innerHTML=htmls;
	});
}

//点击附件的删除按钮时打开删除模态窗
function openDeleteFile(tAttid){
	myTAttid = tAttid; 
	$("#mydeleteFile").modal("show");
}

//点击附件的删除按钮时触发的事件
function deleteFile(){
 	$.get("<%=basePath%>deleteFile.do",
			{tAttid:myTAttid},
			function(data){
				if(data!=null){
					location.href='<%=basePath%>agency.do';
				}

			},'json');
}
//当点击上传文件时触发的事件(未用用的还是form表单提交上传的)
function myajaxUpload(){
	$.ajaxFileUpload({
		url:"<%=basePath%>uploadFile.do",
		secureuri:false,
		dataType:"text",
		data:{
			tAttname:"tAttname",//file标签的name
			tId:globaltAgencyId,
			tType:"fujian"
		},
		success:function(data,status){
			$("#updataFileModal").modal("hidden");
			loadAttPage(globaltAgencyId);
			alert("上传成功");
		}
	});
	
}
<%-- function ajaxUpload(){
	$.ajaxFileUpload({
		url:"<%=basePath%>myuploadFile.do",
		secureuri:false,
		fileElementld:"myFujianID",//file标签的id
		dataType:"html",
		data:{
			myObjectID:globaltAgencyId,
			attType:"fujian"
		},
		success:function(data,status){
			alert(data);
		}
	});
	
} --%>

	
//页面加载时要执行的事件
$(document).ready(function(){
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

    