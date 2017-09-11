package com.isoft.controller;

import java.io.File;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.util.IOUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.google.gson.Gson;
import com.isoft.model.PageBean;
import com.isoft.model.Tagency;
import com.isoft.model.Tattenchment;
import com.isoft.service.AgencyService;
import com.isoft.service.AttenchmentService;
import com.isoft.util.UUIDUtils;

@Controller
public class AgencyController {
	
	@Resource(name="agencyService")
	private AgencyService agencyService;
	@Resource
	private AttenchmentService attService;
	/**
	 * 加载和查询供应商页面数据
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value="getAgency.do")
	public void getAgency(HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		//设置编码
		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("utf-8");
		//System.out.println(request.getParameter("curpage"));
		int currPage = Integer.parseInt(request.getParameter("curpage")==null?"0":request.getParameter("curpage"));//==null?"0":request.getParameter("curpage")
		
		int pageSize = Integer.parseInt("5");//request.getParameter("page_Size")
		//接受数据
		String tAgencyName = request.getParameter("tAgencyName");
		//System.out.println(tAgencyName);

		//调用service的方法，返回值List<TdicCode>
		PageBean<Tagency> page = agencyService.getAgency(tAgencyName,currPage,pageSize);
		if(page.getTotalCount()>0){			
			//将数据写回页面
			PrintWriter pw=response.getWriter();
			pw.write(new Gson().toJson(page));
			pw.flush();
			pw.close();
		}else{
			System.out.println("查询失败");
		}
	}
	
	/**
	 * 根据id查询当前设备信息数据
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value="openAgencyUpdateWin.do")
	public void openAgencyUpdateWin(HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		//设置编码
		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("utf-8");
		//接受数据
		String tAgencyId = request.getParameter("tAgencyId");
		//调用service的方法，返回值List<TdicCode>
		Tagency agen = agencyService.openAgencyUpdateWin(tAgencyId);
		if(agen!=null){			
			//将数据写回页面
			PrintWriter pw=response.getWriter();
			pw.write(new Gson().toJson(agen));
			pw.flush();
			pw.close();
		}else{
			System.out.println("查询失败");
		}
	}
	
	/**
	 * 添加和修改供应商信息
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value="addOrUpdateAgency.do")
	public void addOrUpdateAgency(Tagency tagency,HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		//设置编码
		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("utf-8");
		//接受数据
//		String tAgencyId = request.getParameter("tAgencyId");
		//调用service的方法，返回值List<TdicCode>
		String msg = "";
		if(tagency.gettAgencyId().equals("")){
			//执行添加操作
			tagency.settAgencyId(UUIDUtils.getId());
			if(agencyService.addAgency(tagency)){
				msg = "数据字典添加成功！";
			}else{
				msg = "数据字典添加失败！";
			}
		}else{
			//执行更新操作
			if(agencyService.updateAgency(tagency)){
				msg="数据字典更新成功！";
			}else{
				msg="数据字典更新失败！";
			}
		}
		//将数据写回页面
		PrintWriter pw=response.getWriter();
		pw.write(msg);
		pw.flush();
		pw.close();
	}
	
	/**
	 * 删除供应商信息
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value="delAgency.do")
	public void delAgency(HttpServletRequest request,HttpServletResponse response) throws Exception{
		//设置编码
		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("utf-8");
		//接受数据
		String tAgencyId = request.getParameter("tAgencyId");
		//调用service的方法，返回值List<TdicCode>
		PrintWriter pw=response.getWriter();
		if(agencyService.delAgency(tAgencyId)){			
			pw.write("删除成功");
		}else{
			pw.write("删除失败");
		}
	}
	
	/**
	 * 通过供应商的ID去查询对应的附件信息，并展示出来
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value="loadShowFile.do")
	public void loadShowFile(HttpServletRequest request,HttpServletResponse response) throws Exception{
		//设置编码
		response.setContentType("text/html;charset=utf-8");
		//接受数据
		String tAgencyId = request.getParameter("tAgencyId");
		//System.out.println("tAgencyId"+tAgencyId);
		//调用service的方法,返回值List<Tattenchment>
		List<Tattenchment> list = attService.loadShowFile(tAgencyId);
		//将数据写回浏览器
		PrintWriter pw = response.getWriter();
		pw.write(new Gson().toJson(list));
		
	}
	
	/**
	 * 下载文件
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value="downLoadFile.do")
	public void downLoadFile(HttpServletRequest request,HttpServletResponse response) throws Exception{
		//获取下载文件的名称
		String filename = request.getParameter("tAttname");
		System.out.println("filename:"+filename);
		ServletContext context = request.getServletContext();
		//设置文件的mimeType
		String mimeType = context.getMimeType(filename);
		response.setContentType(mimeType);
		//设置下载的头信息
		response.setHeader("content-disposition", "attchment;filename="+filename);
		//对拷流
		//获取输入流
		//String savePath=request.getSession().getServletContext().getRealPath("/")+"uploadFile"+File.separator+"img"+File.separator;
		
		InputStream is = context.getResourceAsStream("/uploadFile/img/"+filename);
		//System.out.println(savePath+filename);
		//获取输出流
		ServletOutputStream os = response.getOutputStream();
		
//		int len = -1;
//		byte[] b = new byte[1024];
//		while((len=is.read(b))!=-1){
//			os.write(b, 0, len);
//		}
		IOUtils.copy(is, os);
		os.close();
		is.close();
	}
	
	/**
	 * 上传文件
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value="uploadFile.do")
	public String uploadFile(@RequestParam(value="f",required=false)MultipartFile file2,HttpServletRequest request,HttpServletResponse response) throws Exception{

		String tAgencyId = request.getParameter("tAgencyId");
		//保存的路径
		String savePath=request.getSession().getServletContext().getRealPath("/")+"uploadFile"+File.separator+"img"+File.separator;//文件存储的路径
		System.out.println("savePath:"+savePath);
		String fileURL = request.getContextPath()+"/uploadFile"+File.separator;//保存在数据库中的路径
		//文件名
		String fileName=file2.getOriginalFilename();
		if(fileName!=null&&fileName!=""){
			File targetFile=new File(savePath,fileName);//获取img中的图片
			if(!targetFile.exists())
			{
			   targetFile.mkdirs();
			}
			//(未考虑存入相同文件时的情况，所以数据库和存放文件的地方会出现相同文件名的情况)
			file2.transferTo(targetFile);//转存文件
			
			//写入数据库
			Tattenchment tatt = new Tattenchment();
			tatt.settAttname(fileName);
			tatt.settType("fuijian");
			tatt.settAtturl(fileURL);
			tatt.settAttid(UUIDUtils.getId());
			tatt.settId(tAgencyId);
			if(attService.insertSelective(tatt)>0){
				System.out.println("上传成功");
			}else{
				System.out.println("上传失败");
			}
		}else{
			System.out.println("上传文件为空");
		}
		return "userManager/agency";
	}
	
	/**
	 * 删除附件
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value="deleteFile.do",produces="application/json")
	@ResponseBody
	public String deleteFile(HttpServletRequest request,HttpServletResponse response) throws Exception{
		//设置编码
		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("utf-8");
		//获取文件的id
		String tAttid = request.getParameter("tAttid");
		Tattenchment att = attService.selectFile(tAttid);
	 
		//删除数据库中的信息和文件夹中存放的文件
		if(attService.deleteFile(tAttid)){
//			String url = att.gettAtturl()+att.gettAttname();
			String url=request.getSession().getServletContext().getRealPath("/")+"uploadFile"
					+File.separator+"img"+File.separator+att.gettAttname();//未使用数据库中的路径
			File file = new File(url);
			if(file.delete()){
			 
				return "111";
			}
		}else{
			return "222";
		 
		}	return "111";
	}

}
