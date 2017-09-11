<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
							<li class="active">个人中心</li>
						</ul><!-- .breadcrumb -->

					</div>		
					
					<h1 align="center">个人中心</h1><br/>
					<div class="container">
					<form id="personForm">
					<input type="hidden" id="myuserId" value="${userPeople.tUserId}"/><!-- * -->
					<input type="hidden" id="mypersonId" value="${tperson1.tPresonId}"/><!-- * -->
  <div class="form-group">
    <label>用户姓名</label>
    <input type="text" class="form-control" id="myCname" placeholder="用户姓名" value="${tperson1.tCname}"><!-- * -->
  </div>
  <div class="form-group">
    <label>性别</label>
    <select class="selectpicker" id="mygender">
    <option value="男" >男</option>
    <option value="女">女</option>
    </select>
  </div>
  <div class="form-group">
    <label>角色</label>
    <input type="text" id="juese" class="form-control" value="${userPeople.tRole}"/><!-- * disable-->
    
  </div>
  
   <div class="form-group">
    <label>手机号</label>
    <input type="text" id="mobilenumber" class="form-control" value="${tperson1.tMobile}"/><!-- * disable-->
    
  </div>
  
   <div class="form-group">
    <label>科室</label>
    <select id="mykeshixiala">
    <c:forEach  items="${dicList}" var="mdl">
   <option value="${mdl.tDicId}" >${mdl.tType}</option>
    </c:forEach>
    </select>
   
  </div>
  
   <div class="form-group">
    <label>电子邮件</label>
    <input type="text" id="myemail" class="form-control" value="${tperson1.tEmail}"/><!-- * disable-->
    
  </div>
  <!-- 
  <div class="form-group">
    <label>设备图片</label>
   <input id="fileToUpload" type="file" name="devicePicName" style="display: none"/>
   <img alt="无头像" src="" id="mypicID">
   <input type="button" value="选择图片" onclick="choosePic()"/>
   <input type="button" value="上传" onclick="ajaxUpload()"/>
  </div>
  
   -->
   
  
  
   <div class="form-group">
   <input type="button" value="提交" onclick="updatePerson()"/>
    <input type="reset"/>
  </div>
</form>
<!-- 图片上传模块 -->
<div class="devicePic">
 <form action="<%=basePath%>myupload.do" enctype="multipart/form-data" method="post">
 <input type="hidden" name="deviceId" value="${tperson1.tPresonId}"/>
 <input type="hidden" name="mytAttid" value="${tperson1.tAttid}"/>
 <input type="hidden" name="attType" value="headicon"/>
  <input id="fileToUpload" type="file" name="devicePicName"/>
   <img alt="无头像" src="" id="mypicID" style="width: 50px;height: 50px">
   <a href="downimg.do?name=${tperson1.tAttname}">点击下载</a>
   <input type="submit" />
 </form>
					</div>
					
					
					</div><!-- /.page-content -->
				</div><!-- /.main-content --> 

			</div><!-- /.main-container-inner -->
		
     </div>
		 <%-- <%@include file="footer.jsp" %>	 --%>
	
</body>
<!-- 把Atta第Id付给了全职变量 更改了性别  把默认图片从上传 -->
<script type="text/javascript">
var attUpdateID='${tperson1.tAttid}';
$(document).ready(function(){
	$("#mygender").val("${tperson1.tGender}");
	imgURL="${tperson1.tAtturl}/${tperson1.tAttname}";
	$("#mypicID").attr("src",imgURL);
})

/**
 *function ajaxUpload() {
	 $.ajax({
			url:'myupload.do',
			secureuri:false,
			fileElementId:'fileToUpload',
			dataType:'json',
			data:{
				deviceId:'${tperson1.tPresonId}',
				mytAttid:attUpdateID,
				attType:'headicon'
			},
			  error: function(XMLHttpRequest, textStatus, errorThrown) {
		             alert(XMLHttpRequest.status);
		             alert(XMLHttpRequest.readyState);
		             alert(textStatus);
		               },

			success:function(data,status)
			{
				$("mypicID").attr("src",data.tAtturl+data.tAttname);
				window.alert("上传成功");
			}
		})
	} 
 */

</script>
<!-- 点击按钮打开上传 -->
<!-- 
function choosePic() {
	$("#fileToUpload").click();
}
 -->
<script type="text/javascript">

//当点击图片时候也会猝发图片上传
/**$(function(){
	$("#mypicID").click(function(){
		$("#fileToUpload").click();
	})
	
})
 */
 
</script>
<!-- 更新 -->
<script type="text/javascript">
function updatePerson(){
	$.ajax({
        type: 'GET',
        url: 'updatePerson.do',
        data: {
        	    tPresonId:$("#mypersonId").val(),

        	     tUserId:$("#myuserId").val(),

        	     tDicId:$("#mykeshixiala").val(),

        	     tGender:$("#mygender").val(),

        	     tMobile:$("#mobilenumber").val(),

        	     tEmail:$("#myemail").val(),

        	     tCname:$("#myCname").val()
        	
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
                 window.alert("更新成功");
                 location.reload();
               }
            if(data=="fail")
               {
                 window.alert("革新失败");
                 location.reload();
                      
               }
           
            }
          
        });

}
</script>
</html>

    