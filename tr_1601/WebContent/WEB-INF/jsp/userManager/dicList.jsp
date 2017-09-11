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
							<li class="active">数据字典管理</li>
						</ul><!-- .breadcrumb -->

					</div>

					<div class="page-content">

						<div class="row">
							<div class="col-xs-12">
										<div class="table-header">
											数据字典列表
										</div>
			<div class="widget-box">
											<div class="widget-body">
												<div class="widget-main">
													<form class="form-inline">
													    数据项名称：<input type="text" id="myCodeNameID" class="input-large" placeholder="数据名称" />
														数据项类型：<select id="myshujuTypeId" class="selectpicker" data-style="btn-primary" style="width:100px;" onChange="loadPage();"></select>
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
														<th class="center">数据项名称</th>
														<th class="center">数据项类型</th>
														<th class="center">操作</th>
													</tr>
												</thead>

												<tbody id="hetbody">
													<!-- <tr><td class="center"><label><input type="checkbox" class="ace" /><span class="lbl"></span></label></td><td><a href="#">ace.com</a></td><td>Feb 12</td><td class="hidden-480"><span class="label label-sm label-warning">Expiring</span></td><td><div class="visible-md visible-lg hidden-sm hidden-xs btn-group">
												    <button class="btn btn-xs btn-success"><i class="icon-ok bigger-120"></i></button><button class="btn btn-xs btn-info"><i class="icon-edit bigger-120" onclick="openEditWin('123');"></i></button><button class="btn btn-xs btn-danger"><i class="icon-trash bigger-120"></i></button><button class="btn btn-xs btn-warning"><i class="icon-flag bigger-120"></i></button></div></td></tr>
															 -->
																		
												</tbody>
											</table>
										</div>
										</div>
									</div>
									<!-- 分页   -->
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
					数据字典维护
				</h4>
			</div>
			<div class="modal-body">
				<form id="myaddForm" class="form-horizontal" role="form">
					<div class="form-group">
						<label class="col-sm-3 control-label no-padding-right" for="form-field-1"> 数据项名称 </label>
							<input type="hidden" id="tDicId" name="tDicId">
						<div class="col-sm-9">
							<input type="text" id="tName" name="tName" placeholder="数据项名称" class="col-xs-10 col-sm-5" />
						</div>
					</div>
					<div class="space-4"></div>
					<div class="form-group">
						<label class="col-sm-3 control-label no-padding-right" for="form-field-1"> 数据项类型</label>

						<div class="col-sm-9">
							<input type="text" id="tType" name="tType" placeholder="数据项类型" class="col-xs-10 col-sm-5" />
						</div>
					</div>									
				</form>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-primary" onclick="mysubmit();">
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

			
</body>

<script>
 var pageIndex = 0;//页面索引初始值为0才会在页面上选中1
 var pageSize = 5; //几条页面码
 var globaltDicId="";
 var pageCount="";


//当点击修改按钮时触发的事件将信息展示在模态窗中	
function openEditWin(tDicId)
{	addWin();
	$("#tDicId").val(tDicId);
	var editURL="openUpdateWin.do";
	$.ajax({
	    type: "get",
	    url:editURL,
	    data:{
	    	tDicId:tDicId
	    	},
	    dataType: "text",
	    success: function(data) {
	    	var jsonArray = $.parseJSON(data);
	    	$("#tName").val(jsonArray.tName);
	    	$("#tType").val(jsonArray.tType);
	     	$('#myAddModal').modal('show');   
	    }
	});
	
}
	
//添加数据字典和修改数据字典的事件
function mysubmit(){
	   var addurl = "addTDicCode.do";
	$.ajax({
	    type: "get",
	    url:addurl,
	    data:$('#myaddForm').serialize(),
	    success: function(data) {
	    	alert(data);
	    	loadPage();
	     	$('#myAddModal').modal('hide');   
	    }
	});
	
}
	
//调用打开删除的模态窗
function openDeleteConfirm(tDicId)
{
	globaltDicId = tDicId;
	 $("#mydeleteConfirmID").modal("show");
}

//删除事件
function deleteSysUser(){
	var addurl = "deleteTDicCode.do";
	$.ajax({
	    type: "get",
	    url:addurl,
	    data:{
	    	tDicId:globaltDicId
	    	}, 
	    dataType: "text",
	    success: function(data) {
			
     		$("#mydeleteConfirmID").modal("hide");
     		alert(data);
		   	loadPage();
	    }
	});
	
}
//当页面加载时发生的事件或当点击查询按钮时触发的事件
function loadPage(index,pageSize)
{
	   var mycontent = document.getElementById("hetbody");
	   var htmls ="";
	   mycontent.innerHTML="";

	   	    $.get("<%=basePath%>getAllDicCodeJSON.do",
	   	    	  {
	   	    			tName:$("#myCodeNameID").val(),
	   	    			tType:$("#myshujuTypeId").val(),
	   	    			/*将页码数据传到后台 */
	   	    			curpage:index,
	   	    			page_Size:pageSize
		       	   },
	   	    	function (data) {
	               var jsonArray = $.parseJSON(data);
	               var arr =jsonArray.list;
	               pageCount = jsonArray.totalCount;
	              // alert(pageCount);
	               pageIndex = jsonArray.currPage;
	               pageSize = jsonArray.pageSize;
	               var sno;
	               for(var temp in arr)
	               	{
	            	      sno = parseInt(temp)+1;
	               	         
						htmls+='<tr><td class="center"><label><input type="checkbox" class="ace" /><span class="lbl"></span></label></td><td><a href="#">'+sno+'</a></td><td>'+arr[temp].tName+'</td><td class="hidden-480">'+arr[temp].tType+'</td><td><div class="visible-md visible-lg hidden-sm hidden-xs btn-group">'
					    +'<button class="btn btn-xs btn-info"><i class="icon-edit bigger-120" onclick="openEditWin(\''+arr[temp].tDicId+'\');"></i></button><button class="btn btn-xs btn-danger"><i class="icon-trash bigger-120" onclick="openDeleteConfirm(\''+arr[temp].tDicId+'\');"></i></button></div></td></tr>';
	               	}
	               		//alert(htmls);
	                   mycontent.innerHTML = htmls;
	                  // alert(2);
	          	   	 mypagination();
	                   
	        });
	   	
}



//分页
function mypagination(){
	//alert(pageCount);
	$("#Pagination").pagination(pageCount,{
		callback:PageCallback,
		prev_text:"<< 上一页",
		next_text:"下一页   >>",
		items_per_page:pageSize,
		current_page:pageIndex
	});
	function PageCallback(index){
		//alert(index);
		loadPage(index,pageSize);
	}
}



//当点击添加按钮的时候触发打开模态窗事件
function addWin()
{		
	 $("#tName").val("");
	 $("#tType").val("");
     $("#myAddModal").modal("show");

	} 

//数据项类型下拉框
function getSelectDicType()
{	
	  var mycontent = document.getElementById("myshujuTypeId");
	   var htmls ="";
	   mycontent.innerHTML="";
	   htmls+='<option value ="mySelAll" selected="selected" >全部</option>';
	   
	 $.get(
			 "<%=basePath%>getDicType.do",
  	    	 
  	    	function (data) {
              var jsonArray = $.parseJSON(data);
              var sno;
              for(var temp in jsonArray)
              	{
            	   htmls+='<option value ="'+jsonArray[temp].tType+'">'+jsonArray[temp].tType+'</option>';
              	}
              		//alert(htmls);
                  mycontent.innerHTML = htmls;
          });
	}
	
//页面加载时要执行的事件
$(document).ready(function(){
	getSelectDicType();
	loadPage(pageIndex,pageSize);

});

</script>
</html>
 <%@include file="footer.jsp" %>
    