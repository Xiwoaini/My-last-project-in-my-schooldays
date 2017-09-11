//basePath

var basePath=getRealPath();
 function getRealPath(){

                      var localObj = window.location;
              var contextPath = localObj.pathname.split("/")[1];
      var basePath = localObj.protocol + "//" + localObj.host + "/"+ contextPath;

                        return basePath ;

                       }

// var tmp=$("#t_dnumber_css").html();
// if(tmp<=5){
// 	$("#t_dnumber_css").addClass('t_dnumber_css');
// }

 
//查询
function selectDevice(){
	$.ajax({
		type : "GET",
		url : basePath+"/device/selectDeviceManager.do",
		data : {
			dname : $("#deviceName").val(),
			pageNow:$('#requestpage').val(),
			tdiccode_name:$("#tdiccode_name").val()
		},
		 dataType: "json",
		error : function(data) {
			
		},
		success : function(data) {
/*window.history.pushState('200', '啦啦', 'selectDeviceManager.do?dname=$("#deviceName").val()&&pageNow=$('#requestpage').val()&&tdiccode_name=$("#tdiccode_name").val()');*/
		
			
		var jsonArray=$.parseJSON(data);
			var html = "";		
				$("#lastPage").val(jsonArray.page.totalPageCount);
				
				$("#pagecount").val(jsonArray.page.totalPageCount);
				
				document.getElementById("pagecount").innerHTML = jsonArray.page.totalPageCount;
				if(jsonArray.page.pageNow==0||jsonArray.d_list.length==0){
					$("#pagenow").val(jsonArray.page.pageNow);
					document.getElementById("pagenow").innerHTML = '0';
				}
				else{
					$("#pagenow").val(jsonArray.page.pageNow);
					document.getElementById("pagenow").innerHTML = jsonArray.page.pageNow;
				}
				
				document.getElementById("mytb").innerHTML = html;
				
			 
		for (var temp in jsonArray.d_list) {

			html += '<tr ><td hidden>'+jsonArray.d_list[temp].t_equip_id+'</td>';
			html += '<td class="center"><label> <input type="checkbox" class="ace" /> <span class="lbl"></span></label></td>';
			html += '<td>'+(parseInt(temp)+1)+'</td>' ;
			
			if(jsonArray.d_list[temp].t_dic_code==undefined){
				html +='<td ></td>';
			}
			else{
				html += '<td>' + jsonArray.d_list[temp].t_dic_code + '</td>' ;
			}
			
			if(jsonArray.d_list[temp].t_device_name==undefined){
				html +='<td ></td>';
			}
			else{
				html += '<td>'+jsonArray.d_list[temp].t_device_name+'</td>';
			}
			
			if(jsonArray.d_list[temp].t_buy_date==undefined){
				html +='<td ></td>';
			}
			else{
				html += '<td >'+jsonArray.d_list[temp].t_buy_date+'</td>';
			}
			if(jsonArray.d_list[temp].t_check_date==undefined){
				html +='<td ></td>';
			}
			else{
				html += '<td >'+jsonArray.d_list[temp].t_check_date+'</td>';
			}
			if(jsonArray.d_list[temp].t_check_circle==undefined){
				html +='<td ></td>';
			}
			else{
				html += '<td >'+jsonArray.d_list[temp].t_check_circle+'</td>';
			}
			if(jsonArray.d_list[temp].t_left_day==undefined){
				html +='<td ></td>';
			}
			else{
				if(jsonArray.d_list[temp].t_left_day<=5){
					html += '<td class="t_dnumber_css">'+jsonArray.d_list[temp].t_left_day+'</td>';
				}
				else{
					html += '<td >'+jsonArray.d_list[temp].t_left_day+'</td>';
				}	
				
			}
			if(jsonArray.d_list[temp].t_dnumber==undefined){
				html +='<td ></td>';
			}
			else{
				
					html += '<td>'+jsonArray.d_list[temp].t_dnumber+'</td>';
		 
			}
		
			html += '<td ><div class="visible-md visible-lg hidden-sm hidden-xs btn-group">';
html +='<button class="btn btn-xs btn-success" onclick="DeviceLog(\''+jsonArray.d_list[temp].t_equip_id+'\',\''+jsonArray.d_list[temp].t_dic_code+'\',\''+jsonArray.d_list[temp].t_device_name+'\',\''+jsonArray.d_list[temp].t_check_date+'\')" data-toggle="modal" data-target="#DeviceLog" "><i class="icon-ok bigger-120"></i></button>';
			html +='<a href="javascript:void(0)" class="btn btn-xs btn-info" data-toggle="modal" onclick="updateDevice(\''+jsonArray.d_list[temp].t_equip_id+'\')" data-target="#updateDevice">';
			html +='<i class="icon-edit bigger-120"></i> </a>';
		
			 html +='<a class="btn btn-xs btn-danger" href="javascript:void(0) "';
			html +='onclick="javascript:location.href=\'../../tr_1601/device/deleteDevice.do?did='+jsonArray.d_list[temp].t_equip_id+'\';">';
			html +='<i class="icon-trash bigger-120"></i></a></div></td>';
			html += '<td ><a href="javascript:void(0)" data-toggle="modal" onclick="selectDeviceDetail(\''+jsonArray.d_list[temp].t_agency_id+'\',\''+jsonArray.d_list[temp].t_equip_id+'\')" data-target="#selectDevice">详情</a></td>';
			html+='</tr>';


		
		document.getElementById("mytb").innerHTML = html;
		
	}
			
		}

	});


}



//确认添加设备
function addDeviceTrue() {

	$.get(basePath+'/device/addDevice.do',$("#addDeviceForm").serialize(), function(result){
		  
		if (data = "add_success") {
			alert('添加成功！');
			document.getElementById("closeAddDevice").click();
			location.href=basePath+'/device/selectDeviceAll.do';
			return ;
		}
		if (data = "add_error") {
			alert('添加失败！');
			document.getElementById("closeAddDevice").click();
			return ;
		}
		if (data = "date_exception") {
			alert('请输入正确日期,不能小于等于0且不能大于365！');
			document.getElementById("closeAddDevice").click();
			return ;
		}
		if (data = "date_error") {
			alert('请输入正确日期天数！');
			document.getElementById("closeAddDevice").click();
			return ;
		}
	 
	},'json');
}


//设备详情
function selectDeviceDetail(t_agency_id,t_equip_id){
	$("#deviceImg").attr('src','');
	  $.ajax({
		  type:'POST',
		  url:basePath+'/device/selectDeviceDetail.do',
		  data:{
			  t_agency_id: t_agency_id,
			  t_equip_id: t_equip_id
		  },
		
		  dataType:'json',
		  error:function(data){
			 
		  },
		success:function(data){
			var jsonArray=$.parseJSON(data);
		
			//供应商名称
		$("#tAgencyName").val(jsonArray.tagency.tAgencyName);
			//营业执照
		$("#tLicense").val(jsonArray.tagency.tLicense);
		//经营许可证
		$("#tPermitNo").val(jsonArray.tagency.tPermitNo);
	 
	 $("#deviceImg").attr('src',basePath+jsonArray.tattenchment.tAtturl+jsonArray.tattenchment.tAttname);
		  }
		
		  
		  
	  });
	  
}


//修改设备
function updateDevice(eid){
	  $.ajax({
		  type:'POST',
		  url:basePath+'/device/updateDevice.do',
		  data:{
			  t_equip_id: eid
		  },
		
		  dataType:'json',
		  error:function(data){
			 alert(data);
		  },
		success:function(data){
		
			var jsonArray=$.parseJSON(data);
			
			 $("#t_equip_id_update").val(jsonArray.t_equip_id);
			  
			  var t_dic_code_selectedValue = jsonArray.t_dic_code; 
			  var t_agency_name_selectedValue = jsonArray.t_agency_id; 
			  changeSelected(t_dic_code_selectedValue,t_agency_name_selectedValue);
			 $("#t_device_name_update").val(jsonArray.t_device_name); 
			 $("#t_check_circle_update").val(jsonArray.t_check_circle);
			 $("#t_buy_date_update").val(jsonArray.t_buy_date);
			 $("#t_dnumber_update").val(jsonArray.t_dnumber);
			 $("#t_check_circle_update").val(jsonArray.t_check_circle);
		
		  }
		  
	  }); 

	  
}
function changeSelected(t_dic_code_selectedValue,t_agency_name_selectedValue){ 
	
    jsSelectItemByValue(document.getElementById("t_dic_code_update"),t_dic_code_selectedValue);
    jsSelectItemByValue(document.getElementById("t_agency_name_update"),t_agency_name_selectedValue);  
}  
    
    function jsSelectItemByValue(objSelect,objItemText) {  
	   //遍历选择框
    for(var i=0;i<objSelect.options.length;i++) {  
    	
        if(objSelect.options[i].value == objItemText) {  
        	//把这个值默认选中
            objSelect.options[i].selected = true;  
            break;  
        }  
    }  
}  
function updateDeviceTrue(){

	  $.get(basePath+"/device/updateDeviceTrue.do", $("#updateDeviceForm").serialize(), function(result){ 
		    if(result="update_success"){
		    	alert('更新成功!');
		    	document.getElementById("closeUpdateDevice").click();   
		    }
		  },'json');
	  
}

//序号
$(function(){
   
    var len = $('table tr').length;
    for(var i = 1;i<len;i++){
        $('table tr:eq('+i+') td:nth-child(2)').text(i);
    }
        
});

//导入excel
function excelImportForm(){
	var obj_file = document.getElementById("upfile");
	if (obj_file.value == "") {
		alert("请先选择上传文件");
		return ;
	}
	if(!checkFileType()==true){
		alert('文件格式不正确')
		return;
	}
		 
	  var formData = new FormData($("#excelImportForm")[0]);
	 
	 $.ajax({
			type : 'POST',
			url : basePath+'/device/uploadDeviceInformation.do',
			data : formData,
			  async: false, 
	          cache: false, 
	          contentType: false, 
	          processData: false, 
			dataType : 'json',
			error : function(data) {
				alert('导入出错！');
			},
			success : function(data) {
				if (data = "export_success") {
					alert('导入成功！');
				location.href=basePath+'/device/selectDeviceAll.do';
				}
				else if(data = "export_error"){
					alert('导入失败，请检查格式或下载模板！');
				}
				else{
					alert('导入失败！');
				}
			}
		});

	 
	 
}
//判断
function checkFileType(){
	var obj_file = document.getElementById("upfile");
	var fileType=obj_file.value.substr(obj_file.value.lastIndexOf(".")).toLowerCase();
	
	if((fileType!=".xls")||(fileType!=".xls")){
		return false;
	}
	
	return checkfile(obj_file);
	
}
function checkfile(obj_file) {
	var maxsize = 2 * 1024 * 1024; //2M  
	var errMsg = "上传的附件文件不能超过2M！！！";
	var tipMsg = "您的浏览器暂不支持计算上传文件的大小，确保上传文件不要超过2M，建议使用IE、FireFox、Chrome浏览器,以免崩溃";
	var browserCfg = {};
	var ua = window.navigator.userAgent;
	if (ua.indexOf("MSIE") >= 1) {
		browserCfg.ie = true;
	} else if (ua.indexOf("Firefox") >= 1) {
		browserCfg.firefox = true;
	} else if (ua.indexOf("Chrome") >= 1) {
		browserCfg.chrome = true;
	}
	try {
	
		var filesize = 0;
		if (browserCfg.firefox || browserCfg.chrome) {
			filesize = obj_file.files[0].size;
		} else if (browserCfg.ie) {
			var obj_img = document.getElementById('tempimg');
			obj_img.dynsrc = obj_file.value;
			filesize = obj_img.fileSize;
		} else {
			alert(tipMsg);
			return true;
			 
		}
		if (filesize == -1) {
			alert(tipMsg);
			return true;
		} else if (filesize > maxsize) {
			alert(errMsg);
			return false;
		} else {
			
			return true;
		}
	} catch (e) {
 
		return false;
	}
}



//设备检修 
function DeviceLog(t_equip_id,t_dic_code,t_device_name,t_check_date){

	  $.ajax({
			type : 'POST',
			url : basePath+'/device/checkDevice.do',
			data : {
				t_dic_code:t_dic_code
			},
			dataType : 'json',
			error : function(data) {
				
			},
			success : function(data) {
				if(data==""){
					  $("#tCname").val('未知...');	
				}
				else{
					  $("#tCname").val(data);	
				}
				$("#t_equip_id_check").val(t_equip_id);
				$("#t_dic_code_check").val(t_dic_code);
			    $("#t_device_name_check").val(t_device_name);
			    $("#t_check_date").val(new Date().getFullYear()+'年'+(new Date().getMonth()+1)+'月'
			    		+new Date().getDate()+'日');
			  //  $("#t_check_date").val(t_check_date);
			}
		});
}
//设备检修
function checkDeviceTrue(){
$.get(basePath+"/device/checkDeviceTrue.do",$("#checkDeviceForm").serialize(), function(result){
	  if(result=="check_success"){
			alert('检修完成!');
	  }
	  if(result=="check_error"){
		  alert('出现异常，请重试!');
		 	document.getElementById("close_btn_check").click();   
		  return;
	  }
  location.href=basePath+'/device/selectDeviceAll.do';
},'json'); 


}

//检测文本框是否变化
function checkChange(){

	var page=1;
	  $('#requestpage').val(1);
	  selectDevice();
}
//首页
function firstPage() {
var page=1;
  $('#requestpage').val(page);
  selectDevice();
}
//上一页
function upPage() {
 var page = parseInt($('#pagenow').val()) - 1;
    if (page <= 0) {
        page = 1;
    }
    $('#requestpage').val(page);
    selectDevice();
}
//下一页
function nextPage() {
	
	if(isNaN(parseInt($("#pagenow").val()))){
		pagenow=1;
		 var page = pagenow + 1;
		    
		    if (page == 0) {
		        page = 1;
		    }
		
	}
	else{
		var page = parseInt($('#pagenow').val()) + 1;
	    
	    if (page >= parseInt($('#pagecount').val())) {
	        page = $('#pagecount').val();
	    }
	    if (page == 0) {
	        page = 1;
	    }
	}
    
    $('#requestpage').val(page);
    selectDevice();
}
//尾页
function lastPage() {
var page=$('#lastPage').val();
 if(page==1){
	  $('#requestpage').val(1);  
  }
  if((page=="")||(page==null)){
	page=null;
	}
 
	 $('#requestpage').val(page);
	 selectDevice();
}

