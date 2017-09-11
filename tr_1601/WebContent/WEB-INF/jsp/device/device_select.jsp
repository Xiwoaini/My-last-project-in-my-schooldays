<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>


<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8" />
<title>医院设备控制台</title>
<meta name="keywords" content="" />
<meta name="description" content="" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<!-- basic styles -->
<style type="text/css">
.t_dnumber_css{
color:red;
}
</style>


</head>

<body>
	<%@include file="../userManager/header.jsp"%>

	<div class="main-container" id="main-container">

		<div class="main-container-inner">
			<%@include file="../userManager/leftPage.jsp"%>
<!-- 			<script type="text/javascript">
$('li').removeAttr('active');
$('.device_active').attr('class','active');
</script> -->
			<div class="main-content">
				<div class="breadcrumbs" id="breadcrumbs">

					<ul class="breadcrumb">
						<li><i class="icon-home home-icon"></i> <a href="#">首页</a></li>
						<li class="active">设备管理</li>
					</ul>
				</div>

				<div class="page-content">
					<div class="row">
						<div class="col-xs-12">
							<div class="row">
								<div class="col-xs-12">
									<!-- nav -->
									科室:<select id="tdiccode_name" name="agency">
										<option value="tdiccode_name_ALL" selected="selected">全部</option>
										<c:forEach items="${tdiccode_list }" var="tdiccode">
											<option value="${tdiccode.tName }">${tdiccode.tName }</option>
										</c:forEach>
									</select> 仪器名称: <input type="text" id="deviceName" name="deviceName" maxlength="8"
										onkeyup="this.value=this.value.replace(/\s+/g,'')" placeholder="仪器名称" /> <input
										type="button" class="btn btn-success" value="查询" onclick="selectDevice()" /> <input
										type="button" class="btn btn-primary" value="添加" data-toggle="modal"
										data-target="#addDevice" /> <input type="button" class="btn btn btn-info" value="导出excel"
										onclick="javascript:location.href='../device/exportDeviceInformation.do'" /> <input
										 type="button" class="btn btn btn-info" value="导入excel" data-toggle="modal"
										data-target="#excelImport" />			
									<a href="<%=path%>/device/downLoadFileMoBan.do">导入模板下载</a>

									<!-- nav结束 -->
									<div class="table-responsive">
										<!-- 表格内容 -->
										<table id="sample-table-1" class="table table-striped table-bordered table-hover">
											<thead>
												<tr>
													<th class="center"><label> <input type="checkbox" class="ace" /><span
															class="lbl"></span>
													</label></th>
													<th>序号</th>
													<th>科室</th>
													<th>仪器名称</th>
													<th>仪器购买日期</th>
													<th>上次年检时间</th>
													<th>年检周期(天)</th>
													<th>距离下次检测(天)</th>
													<th>提前预警(天)</th>
													<th>操作</th>
													<th>仪器详细信息</th>
												</tr>
											</thead>

											<tbody id="mytb">
											 
												<c:forEach items="${device_list }" var="device" varStatus="loop">
													<tr>
														<td class="center"><label> <input type="checkbox" class="ace" /> <span
																class="lbl"></span>
														</label></td>
														<td></td>
														
														<td>${tdiccode_list[loop.count-1].tName}</td>
												
														<%-- <td>${device.t_dic_code }</td> --%>
														<td>${device.t_device_name}</td>
														<td>${device.t_buy_date }</td>
														<td>${device.t_check_date }</td>
														<td>${device.t_check_circle }</td>
														<td class="t_dnumber">${device.t_left_day }</td>						 
														<td >${device.t_dnumber }</td>
														 
														<td>
															<div class="visible-md visible-lg hidden-sm hidden-xs btn-group">
																<!-- 设备确认检修 -->
																<button class="btn btn-xs btn-success" onclick="DeviceLog('${device.t_equip_id }','${device.t_dic_code }','${device.t_device_name}','${device.t_check_date }')"
																 data-toggle="modal" data-target="#DeviceLog">
																	<i class="icon-ok bigger-120"></i>
																</button>
																<!-- 设备修改按钮 -->
																<a href="javascript:void(0)" onclick="updateDevice('${device.t_equip_id }')"
																	class="btn btn-xs btn-info" data-toggle="modal" data-target="#updateDevice"> <i
																	class="icon-edit bigger-120"></i>
																</a> <a class="btn btn-xs btn-danger" href="javascript:void(0);"
																	onclick="javascript: 
													window.location='<%=path%>/device/deleteDevice.do?did=${device.t_equip_id }';">
																	<i class="icon-trash bigger-120"></i>
																</a>


															</div>


														</td>
														<td><a href="javascript:void(0)"
															onclick="selectDeviceDetail('${device.t_agency_id }','${device.t_equip_id}')" data-toggle="modal"   
															data-target="#selectDevice">详情</a></td>
													</tr>
												</c:forEach>
	 
											</tbody>
										</table>
										<!-- 分页功能 start -->
										<div id="fenYeDiv" align="center">
											<font size="2">共<span id="pagecount"> ${page.totalPageCount } </span> 页
											</font> <font size="2">第 <span id="pagenow"> ${page.pageNow }</span> 页
											</font>
											<p id="requestpage" hidden></p>
											<a id="firstPage" href="javascript:void(0)" onclick="firstPage()">首页</a> <a id="upPage"
												href="javascript:void(0)" onclick="upPage()">上一页</a> <a id="nextPage"
												href="javascript:void(0)" onclick="nextPage()">下一页</a> <a id="lastPage"
												href="javascript:void(0)" onclick="lastPage()">尾页</a>

										</div>

										<!-- 分页功能 End -->

									</div>
									<!-- /.table-responsive -->
								</div>
								<!-- /span -->
								<!--设备 增加 -->
								<div class="modal fade" id="addDevice" tabindex="-1" role="dialog"
									aria-labelledby="myModalLabel" aria-hidden="true">
									<div class="modal-dialog">
										<div class="modal-content">
											<div class="modal-header">
												<button type="button" class="close" data-dismiss="modal" aria-hidden="true">
													&times;</button>
												<h4 class="modal-title" id="myModalLabel">添加设备信息</h4>
											</div>
											<div class="modal-body">
												<form id="addDeviceForm" class="form-inline" method="post">
													<div class="form-group">
														<label for="exampleInputName2">科室:</label> <select id="t_dic_code" name="t_dic_code">
										 
															<c:forEach items="${tdiccode_list }" var="tdiccode">
																<option value="${tdiccode.tDicCode }">${tdiccode.tName }</option>
															</c:forEach>
														</select>
													</div>
													<div class="form-group">
														<label for="exampleInputEmail2">供应商：</label> <select id="t_agency_name"
															name="t_agency_name">

															<c:forEach items="${agency_list }" var="agency">
																<option value="${agency }">${agency }</option>
															</c:forEach>
														</select>
													</div>
													<br>
													<div class="form-group">
														<div class='col-sm-3'>
															<label for="exampleInputEmail2">仪器名称:</label>
														</div>
														<div class="col-sm-9">
															<input id="t_device_name" name="t_device_name" placeholder="仪器名称" type="text" />
														</div>
													</div>
													<br>
													<div class="form-group">
														<div class='col-sm-3'>
															<label for="exampleInputEmail2">购买日期:</label>
														</div>
														<div class='input-group date col-sm-9' id='datetimepicker1'>
															<input type='text' name="t_buy_date" id="t_buy_date"
																class="form-control form_datetime" /> <span class="input-group-addon"> <span
																class="glyphicon glyphicon-calendar"></span>
															</span>
														</div>
													</div>
													<br>
													<div class="form-group col-sm-6">
														<label for="exampleInputEmail2">提前预警:</label> <input placeholder="提前预警" type="text"
															id="t_dnumber" name="t_dnumber" />
													</div>
													<div class="form-group col-sm-6">
														<label for="exampleInputEmail2">检修周期:</label> <input placeholder="检修周期" type="text"
														id="t_check_circle" name="t_check_circle" />
													</div>
											</div>
											<div class="modal-footer">
												<input type="button" onclick="addDeviceTrue()" class="btn btn-primary" value="确认增加" />
												<input id="closeAddDevice" type="button" data-dismiss="modal" class="btn btn-default"
													value="关闭" />
												</form>
											</div>
										</div>
										<!-- /.modal-content -->
									</div>
									<!-- /.modal -->
								</div>
								<!-- 设备修改update -->
								<div class="modal fade" id="updateDevice" tabindex="-1" role="dialog"
									aria-labelledby="myModalLabel" aria-hidden="true">
									<div class="modal-dialog">
										<div class="modal-content">
											<div class="modal-header">
												<button type="button" class="close" data-dismiss="modal" aria-hidden="true">  
													&times;</button>
												<h4 class="modal-title" id="myModalLabel">设备修改</h4>
											</div>
											<div class="modal-body">
												<form id="updateDeviceForm">
												
														<input hidden name="t_equip_id" id="t_equip_id_update" type="text" />
																科室:&nbsp;&nbsp;<select name="t_dic_code" id="t_dic_code_update">
																	 
																	<c:forEach items="${tdiccode_list }" var="tdiccode">
																		<option value="${tdiccode.tName}">${tdiccode.tName}</option>
																	</c:forEach>
															</select>
									 						供应商：&nbsp;&nbsp;<select name="t_agency_name" id="t_agency_name_update">  
																	<option value="agencyAll" selected="selected">全部</option>
																	<c:forEach items="${agency_list }" var="agency">
																		<option value="${agency }">${agency }</option>
																	</c:forEach>
															</select><br>

														仪器名称:&nbsp;&nbsp;&nbsp;<input id="t_device_name_update"
																name="t_device_name" placeholder="仪器名称" type="text" />
														<br>
																	购买日期:&nbsp;&nbsp;
																	<input type='text' name="t_buy_date" id="t_buy_date_update"
																		class="form-control form_datetime" /> 
																	
																
														<br>
															提前预警:&nbsp;&nbsp;&nbsp;<input placeholder="提前预警" type="text"
																id="t_dnumber_update" name="t_dnumber" />
															
															检修周期:&nbsp;&nbsp;&nbsp;<input placeholder="检修周期" type="text"
																id="t_check_circle_update" name="t_check_circle" />
														
													
											</div>
											<div class="modal-footer">
												<input type="button" class="btn btn-primary" onclick="updateDeviceTrue()" value="确认修改" />
												<input type="button" data-dismiss="modal" class="btn btn-default" id="closeUpdateDevice"
													value="关闭" />
												</form>
											</div>
										</div>
										<!-- /.modal-content -->
									</div>
									<!-- /.modal -->
								</div>
								<!-- 设备详情 -->
								<div class="modal fade" id="selectDevice" tabindex="-1" role="dialog"
									aria-labelledby="myModalLabel" aria-hidden="true">
									<div class="modal-dialog">
										<div class="modal-content">
											<div class="modal-header">
												<button type="button" class="close" data-dismiss="modal" aria-hidden="true">
													&times;</button>
												<h4 class="modal-title" id="myModalLabel">供应商信息</h4>
											</div>
											<div class="modal-body">

												<table>
													<tr>
														<td>
															<div class="form-group">
																<label for="exampleInputName2">供应商名称</label> <input type="text" id="tAgencyName"
																	readonly="readonly" />
															</div>
														</td>
													</tr>
													<tr>
														<td>
															<div class="form-group">
																<label for="exampleInputName2">营业执照编号</label> <input type="text" id="tLicense"
																	readonly="readonly" />
															</div>

														</td>
													</tr>
													<tr>
														<div class="form-group">
															<label for="exampleInputName2">仪器经营许可证</label> <input type="text" id="tPermitNo"
																readonly="readonly" />
														</div>
														<td colspan="2"></td>
													</tr>
													<tr>
														<td colspan="2">
															<div class="form-group">
																<label for="exampleInputName2">仪器图片</label> <img id="deviceImg" src="" alt="当前设备无图片">
															</div>

														</td>
													</tr>
												</table>


											</div>
											<div class="modal-footer">
												 <input type="button"
													data-dismiss="modal" class="btn btn-default" value="关闭" />

											</div>
										</div>
										<!-- /.modal-content -->
									</div>
									<!-- /.modal -->
								</div>
<!-- 设备详情 结束 -->
<!-- 设备检修开始 -->
<div class="modal fade" id="DeviceLog" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
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
				<form id="checkDeviceForm" class="form-horizontal" role="form">
				<input type="text" id="t_dic_code_check" name="t_dic_code_check" hidden="hidden" />
				<input type="text" id="t_equip_id_check" name="t_equip_id_check" hidden="hidden" />
					<div class="form-group">
						<label class="col-sm-3 control-label no-padding-right" for="form-field-1"> 设备名称 </label>
							<input type="hidden" id="tLogId" name="tLogId">
						<div class="col-sm-9">
							<input type="text" id="t_device_name_check" name="t_device_name" placeholder="设备名称" class="col-xs-10 col-sm-5" />
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
							<input type="text" id="t_check_date" name="t_check_date" class="form-control" size="16" readonly>
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
				<button type="button" id="btn" class="btn btn-primary" onclick="checkDeviceTrue()">
					确认检修
				</button>
				<button type="button" id="close_btn_check" class="btn btn-default" data-dismiss="modal">
					取消
				</button>
			</div>
		</div><!-- /.modal-content -->
	</div><!-- /.modal -->
</div>
<!-- 设备检修结束 -->
<!-- excel导入 -->
	<div class="modal fade" id="excelImport" tabindex="-1" role="dialog"
									aria-labelledby="myModalLabel" aria-hidden="true">
									<div class="modal-dialog">
										<div class="modal-content">
											<div class="modal-header">
												<button type="button" class="close" data-dismiss="modal" aria-hidden="true">
													&times;</button>
												<h4 class="modal-title" id="myModalLabel">Excel导入</h4>
											</div>
											<div class="modal-body">
											<form id="excelImportForm" >
										<input type="file" id="upfile" name="upfile"  value="导入excel" /> 
									</form>
											</div>
											<div class="modal-footer">
												<input type="button"
											class="btn" value="确定导入" onclick="excelImportForm()"> 
												<input type="button" data-dismiss="modal" class="btn btn-default" id="closeUpdateDevice"
													value="关闭" />
												</form>
											</div>
										</div>
										<!-- /.modal-content -->
									</div>
									<!-- /.modal -->
								</div>
							</div>
						</div>
					</div>

				</div>
			</div>
			<!-- /.page-content -->
		</div>
		<!-- /.main-content -->

	</div>
	<!-- /.main-container-inner -->
	<div>
		<a href="#" id="btn-scroll-up" class="btn-scroll-up btn btn-sm btn-inverse"> <i
			class="icon-double-angle-up icon-only bigger-110"></i>
		</a>
	</div>
	<!-- /.main-container -->
	
	
	<script type="text/javascript" src="<%=path %>/resource/device/device.js"></script>
	
	<script type="text/javascript">
		var pagenow=null;
		$("#lastPage").val(${page.totalPageCount});
		$("#pagenow").val(${page.pageNow});
		$("#pagecount").val(${page.totalPageCount});

		</script>

	<script type="text/javascript">
		  $(".form_datetime").datetimepicker({
		      format: "yyyy-mm-dd",
		      autoclose: true,
		      todayBtn: true,
		      language:'zh-CN',
		      pickerPosition:"bottom-left",
		     
				  weekStart:1,
			      todayHighlight:1,
			      startView:2,
			      minView:2,
				  forceParse:0 

		    });
  </script>
<script type="text/javascript">
var tmp;
for(var i=0;i<=4;i++){
	tmp=$("#mytb .t_dnumber").eq(i).text();
if(tmp<=5){
	
	$(".t_dnumber").eq(i).addClass('t_dnumber_css');
}
	
}

</script>



</body>

</html>

