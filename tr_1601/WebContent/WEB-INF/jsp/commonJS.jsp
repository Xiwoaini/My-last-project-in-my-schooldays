<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
function getUpdateDropDown(dicType,htmlObjId,pDicCode)
{
	  var htmlObj = document.getElementById(htmlObjId);
	  var selectStr ="";
	  $.get("${myappurl}getDropDownJSON.do",
			  {
		          tType:dicType
		       },function (data) {
      var jsonArray = $.parseJSON(data);
      for(var temp in jsonArray)
      	{
      	if(jsonArray[temp].tDicCode==pDicCode)
      		{
      		selectStr+='<option selected="selected" value="'+jsonArray[temp].tDicCode+'">'+jsonArray[temp].tName+'</option>';
      		}else{
      			selectStr+='<option value="'+jsonArray[temp].tDicCode+'">'+jsonArray[temp].tName+'</option>';
      		}
      	         
      	}
      htmlObj.innerHTML=selectStr;
      
  });
	}

function selectDropDownCommon(dicType,htmlObjId)
{
	 var htmlObj = document.getElementById(htmlObjId);
	  
	  var selectStr ="";
	  myurl = "${myappurl}getDropDownJSON.do";
	  $.get(myurl,
			  {
		          tType:dicType
		       },function (data) {
         var jsonArray = $.parseJSON(data);
         selectStr+='<option value="myall">全部</option>';
         for(var temp in jsonArray)
         	{
       	  selectStr+='<option value="'+jsonArray[temp].tDicCode+'">'+jsonArray[temp].tName+'</option>';
         	}
         htmlObj.innerHTML=selectStr;
         
     });
	
}

function mypagination(allCountNum){

	  var pageIndex = 0;     //页面索引初始值   
  var pageSize = 10;     //每页显示条数初始化，修改显示条数，修改这里即可   
      
         //分页，PageCount是总条目数，这是必选参数，其它参数都是可选
         $("#Pagination").pagination(allCountNum, {
             callback: PageCallback,  
                prev_text: "« 上一页",
             next_text: "下一页 »",
             items_per_page:pageSize,
            // num_edge_entries: 10,       //两侧首尾分页条目数
               // num_display_entries: 6,    //连续分页主体部分分页条目数
                current_page: pageIndex,   //当前页索引
         });
         //翻页调用   
         function PageCallback(index) {             
      	   loadPage(pageSize,index+1);
      	   
         }  
         //请求数据   
  
}
</script>
</head>
<body>

</body>
</html>