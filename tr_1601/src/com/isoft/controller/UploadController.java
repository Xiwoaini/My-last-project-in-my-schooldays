package com.isoft.controller;
/**
 * 提交到SVN
 */
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonFormat.Value;
import com.google.gson.Gson;
import com.isoft.model.Tattenchment;
import com.isoft.model.VOPersonAtt;
import com.isoft.service.IUploadService;

@Controller
public class UploadController {
@Resource
private IUploadService uploadService;

@RequestMapping(value="myupload.do")
public String upload(@RequestParam(value="devicePicName",required =false) MultipartFile file,HttpServletRequest request,HttpServletResponse response)
{
	
   String deviceId =request.getParameter("deviceId");//获取t_id
   String tAttid=request.getParameter("mytAttid");//获取从Voperson获取的tAttid
   String attType=request.getParameter("attType");//获取图片的类型
   String savePath=request.getSession().getServletContext().getRealPath("/")+"resource"+File.separator+"img"+File.separator;//文件存储的路径
   String FileURL=request.getContextPath()+"/resource/img"+File.separator;//文件的上传路径
   String fileName=file.getOriginalFilename();//文件名
   if(fileName==""||fileName==null)
   {
	   System.out.println("你提交的是空");
	   return "userManager/personCore";
	   
   }

   File targetFile=new File(savePath,fileName);//获取img中的图片
   if(!targetFile.exists())
   {
	   targetFile.mkdirs();
   }
   //加入文件不存在则创建他
   try {
	   file.transferTo(targetFile);//转存文件
	   Tattenchment att =new Tattenchment();
	   att.settAttname(fileName);
	   att.settAtturl(FileURL);
	   att.settType(attType);
	  att.settId(deviceId);
	   //将属性都存在该对象上
	   
	   if(uploadService.checkFile(deviceId))
	   {
		   att.settAttid(tAttid);//将图片id记住
		   if(uploadService.updateFile(att))
		   {
//			   request.setAttribute("attment", att);
			   VOPersonAtt person=(VOPersonAtt) request.getSession().getAttribute("tperson1");//获取对象
			   request.getSession().removeAttribute("tperson1");//移除旧的对象
			   person.settAtturl(FileURL);//设置新属性
			   person.settType(attType);
			   person.settAttname(fileName);
			   request.getSession().setAttribute("tperson1",person);//重新存储
			   return "userManager/personCore";
			   
		   }
		   else{
			   response.getWriter().write("myfail");
			   return "userManager/personCore";
		   }
	   }
	   else{
		   att.settAttid(UUID.randomUUID().toString());
		   if(uploadService.uploadFile(att))
		   {
			   //response.getWriter().write(new Gson().toJson(att));
//			   VOPersonAtt person=(VOPersonAtt) request.getSession().getAttribute("tperson1");
//			   request.getSession().removeAttribute("tperson1");
//			   person.settAtturl(FileURL);
//			   person.settType(attType);
//			   person.settAttname(fileName);
//			   person.settAttid(att.gettAttid());
//			   request.getSession().setAttribute("tperson1",person);
			   request.getSession().removeAttribute("tperson1");
			   request.getSession().removeAttribute("userPeople");
			   return "userManager/login";
		   }
		   else{
			   response.getWriter().write("myfail");
			   return "userManager/login";
		   }
	   }
	 
	
}
   
   catch (Exception e) {
	// TODO: handle exception
}
  return "userManager/personCore";
}
//文件的下载
@RequestMapping(value="downimg.do")
public void downimg(HttpServletRequest request,HttpServletResponse response) throws IOException
{
	ServletContext context=request.getServletContext();
	String filename=request.getParameter("name");
	filename= new String(filename.getBytes("iso8859-1"),"UTF-8");
	String type=context.getMimeType(filename);
	 response.setContentType(type);
	 response.setHeader("content-disposition","attchment;filename="+new String(filename.getBytes("gbk"),"iso8859-1"));
		//对口流 
     InputStream is= context.getResourceAsStream("/resource/img/"+filename);
     OutputStream os=response.getOutputStream();
     IOUtils.copy(is, os);
	}

}
