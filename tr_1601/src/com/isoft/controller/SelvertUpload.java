package com.isoft.controller;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

@Controller
@RequestMapping(value="/upload")
public class SelvertUpload  {
	
	@RequestMapping(value="/uploadFile")
	public void dost(HttpServletRequest request, HttpServletResponse response) {
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;    
		String s2="E:\\upload";
		MultipartFile file = multipartRequest.getFile("file");  
		 File dir = new File(s2, file.getOriginalFilename());
		 if (!dir.exists()) {

		 dir.mkdirs();
		 }
		
		 try {
		//保存
		 file.transferTo(dir);
		 }
		 catch(Exception e){
			 
		 }
		
		
		

	
		  
		  
		  
	}
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
