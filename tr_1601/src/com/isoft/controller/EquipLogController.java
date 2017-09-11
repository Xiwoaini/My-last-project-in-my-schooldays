package com.isoft.controller;

import java.io.PrintWriter;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.google.gson.Gson;
import com.isoft.model.PageBean;
import com.isoft.model.TequipLog;
import com.isoft.model.VOTequipLog;
import com.isoft.service.EquipLogService;

@Controller
public class EquipLogController {
	@Resource
	private EquipLogService equipLogService;
	/**
	 * 加载设备维护日志所有数据
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value="getEquipLog.do")
	public void getEquipLog(HttpServletRequest request,HttpServletResponse response) throws Exception{
		//设置编码
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		String t_device_name = request.getParameter("t_device_name");
		String tCname = request.getParameter("tCname");
		
		int currPage = Integer.parseInt(request.getParameter("curpage")==null?"0":request.getParameter("curpage"));//==null?"0":request.getParameter("curpage")
		
		int pageSize = Integer.parseInt("5");//page_Size
		
		//调用service的方法，返回值List<TdicCode>
		PageBean<VOTequipLog> page = equipLogService.getEquipLog(tCname,t_device_name,currPage,pageSize);
		//将数据写回页面
		PrintWriter pw=response.getWriter();
		pw.write(new Gson().toJson(page));
		pw.flush();
		pw.close();
	}
	/**
	 * 通过id查询数据显示到修改删除窗口
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value="openEquiLogpUpdateWin.do")
	public void openEquiLogpUpdateWin(HttpServletRequest request,HttpServletResponse response) throws Exception{
		//设置编码
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		String tLogId = request.getParameter("tLogId");
		//调用service的方法，返回值List<TdicCode>
		VOTequipLog vo = equipLogService.getEquipLogById(tLogId);
		//将数据写回页面
		PrintWriter pw=response.getWriter();
		pw.write(new Gson().toJson(vo));
		pw.flush();
		pw.close();
	}
	
	/**
	 * 修改日志
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value="addOrUpdateEquipLog.do")
	public void addOrUpdateEquipLog(TequipLog equip,HttpServletRequest request,HttpServletResponse response) throws Exception{
		//设置编码
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		//调用service的方法，返回值List<TdicCode>
		PrintWriter pw=response.getWriter();
		if(equipLogService.addOrUpdateEquipLog(equip)){			
			pw.write("更新成功");
		}else{
			pw.write("更新失败");			
		}
		pw.flush();
		pw.close();
	}
	
	/**
	 * 删除日志
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value="delteEquipLog.do")
	public void delteEquipLog(HttpServletRequest request,HttpServletResponse response) throws Exception{
		//设置编码
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		//调用service的方法，返回值List<TdicCode>
		PrintWriter pw=response.getWriter();
		if(equipLogService.delteEquipLog(request.getParameter("tLogId"))){			
			pw.write("删除成功");
		}else{
			pw.write("删除失败");			
		}
		pw.flush();
		pw.close();
	}
}
