package com.isoft.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.google.gson.Gson;
import com.isoft.model.Device;
import com.isoft.model.Tagency;
import com.isoft.model.TdicCode;
import com.isoft.service.DeviceService;
import com.isoft.util.UUIDUtils;

/**
 * @author f
 * @描述 设备管理控制
 * */
@Controller
@RequestMapping(value="/device")
public class DeviceController {

	//注入
	@Resource(name="deviceServiceImpl")
	private DeviceService deviceService;

//	设备管理数据展现 t_device
	@RequestMapping(value="/selectDeviceAll")
	public String selectDeviceAll(HttpServletRequest request,HttpServletResponse response) throws NumberFormatException, ParseException {
		
		
		//下拉框数据
		List<Tagency> agency_list=deviceService.selectTagencyAll();
		
		List<Device> device_list=deviceService.selectDeviceAll(request);
		//查所有科室
		List<TdicCode> tdiccode_list=deviceService.selecTdicCodesAll();
		request.setAttribute("agency_list", agency_list);
		request.setAttribute("device_list", device_list);
		request.setAttribute("tdiccode_list", tdiccode_list);
		return "device/device_select";
	}
	
	
	
//	设备管理数据添加 t_device
	@RequestMapping(value="/addDevice.do",produces="application/json")
	@ResponseBody
	public String addDevice(Device device,HttpServletRequest request,HttpServletResponse response) throws ParseException {
			  return deviceService.addDevice(device,request.getParameter("t_agency_name"));
		  
			
		
	}
//	设备管理数据修改 t_device
	@RequestMapping(value="/updateDevice.do")
	@ResponseBody
	public String updateDevice(HttpServletRequest request,HttpServletResponse response) {
		//先进行查询

		Gson gson=new Gson();
		

		 return gson.toJson(deviceService.updateDevice(request.getParameter("t_equip_id")));
	}
//	设备管理数据修改 t_device
	@RequestMapping(value="/updateDeviceTrue.do")
	@ResponseBody
	public String updateDeviceTrue(Device device,HttpServletRequest request,HttpServletResponse response) {
		//先进行查询
		deviceService.updateDeviceTrue(device);
		
//		写到前台
		return "update_success";
	}
//	设备管理数据删除 t_device
	@RequestMapping(value="/deleteDevice.do")
	public String deleteDevice(HttpServletRequest request,HttpServletResponse response) throws NumberFormatException, ParseException {
		String did=request.getParameter("did");
		if(deviceService.deleteDevice(did)){
			selectDeviceAll(request,response);
			return "device/device_select";
		}
		else{
			selectDeviceAll(request,response);
			return "device/device_select";
		}
	
		
	}
//	设备管理数据查询 t_device 模糊查询
	@RequestMapping(value="/selectDeviceManager.do",produces="application/json")
	@ResponseBody
	public String selectDeviceManager(HttpServletRequest request,HttpServletResponse response) throws NumberFormatException, ParseException {
 String json=	deviceService.selectDeviceLike(request);
		return json;
	}
//	设备信息详情 t_device
	@RequestMapping(value="/selectDeviceDetail.do")
	@ResponseBody
	public String selectDeviceDetail(HttpServletRequest request,HttpServletResponse response) {
	
		return deviceService.selectDeviceDetail(request.getParameter("t_agency_id"),request.getParameter("t_equip_id"));
	}
//	设备图片上传功能 t_attenchment
	@RequestMapping(value="/uploadDeviceImage.do")
	public String uploadDeviceImage(HttpServletRequest request,HttpServletResponse response) {
		
	
		return "device/device_select";
	}
//	设备信息Excel导入  t_device
	@Transactional
	@RequestMapping(value="/uploadDeviceInformation.do",method={RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public String uploadDeviceInformation(HttpServletRequest request,HttpServletResponse response) throws Exception {
		  MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;    
	     
		  InputStream in =null;  
	        List<List<Object>> listob = null;  
	       
	        MultipartFile file = multipartRequest.getFile("upfile");  
	        if(file.isEmpty()){  
	        	return "export_error";
	        }  
	     
	        try{
	        	 in = file.getInputStream();  
	 	        listob = deviceService.getDeviceListByExcel(in,file.getOriginalFilename());  
	 	     
	 	        List<Device> list_device=new ArrayList<Device>();
	 	       
	 	        for (int i = 0; i < listob.size(); i++) { 
	 	         
	 	            List<Object> lo = listob.get(i);  
	 	            Device d = new Device(); 
	 	            d.setT_equip_id(UUIDUtils.getId());
	 	            d.setT_dic_code(String.valueOf(lo.get(1)));  
	 	            d.setT_device_name(String.valueOf(lo.get(2)));  
	 	            d.setT_buy_date(String.valueOf(lo.get(3)));  
	 	            d.setT_check_date(String.valueOf(lo.get(4))); 
	 	            d.setT_check_circle(String.valueOf(lo.get(5)));
	 	            d.setT_left_day(String.valueOf(lo.get(6))); 
	 	            d.setT_dnumber(String.valueOf(lo.get(7))); 
	 	      
	 	            d.setT_agency_id("1");
	 	            list_device.add(d);
	 	            deviceService.addDeviceExcel(d);
	 	            
	 	        }  
	 	    
	 	        return "export_success";
	        }
	        catch(Exception e){
	        	TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
	        	 return "export_error";
	        }
	       

		
	}
//	设备信息Excel导出 t_device
	@RequestMapping(value="/exportDeviceInformation.do")
	public void exportDeviceInformation(HttpServletRequest request, HttpServletResponse response, Model model) throws Exception {

		List<Device> list = new ArrayList<Device>();
		list = this.deviceService.excelExport();

		HSSFWorkbook wb = deviceService.exportDevice(list);
		response.setContentType("application/vnd.ms-excel");
		response.setHeader("Content-disposition", "attachment;filename=deviceDetail.xls");
		OutputStream ouputStream = response.getOutputStream();
		wb.write(ouputStream);
		ouputStream.flush();
		ouputStream.close();
	}


//	设备确认检修日志功能 t_equip_log
	@RequestMapping(value="/checkDevice.do",produces="application/json")
	@ResponseBody
	public String checkDevice(HttpServletRequest request,HttpServletResponse response) {
		
		String s=deviceService.selectCheckPerson(request.getParameter("t_dic_code"));
		if(s==null){
		s="";	
		}
		return s;
	}
	//设备确认检修
	@RequestMapping(value="/checkDeviceTrue.do",produces="application/json")
	@ResponseBody
	public String checkDeviceTrue(HttpServletRequest request,HttpServletResponse response) throws Exception {
		
	String tmp=deviceService.checkDeviceTrue(request);
			return tmp;
	}
	//文件模板下载
	@RequestMapping(value="downLoadFileMoBan")
	public void downLoadFileMoBan(HttpServletRequest request,HttpServletResponse response) throws Exception {
	
		ServletContext context=request.getServletContext();
		 response.setContentType("multipart/form-data");
		 response.setHeader("content-disposition","attchment;filename=moban.xls");
			//对口流 
		 
	     InputStream is= context.getResourceAsStream("/doc/moban/moban.xls");
	     OutputStream os=response.getOutputStream();
	     IOUtils.copy(is, os);
	
		 
	}
	
}
 