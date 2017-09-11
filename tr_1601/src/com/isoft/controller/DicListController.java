package com.isoft.controller;

import java.io.PrintWriter;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.google.gson.Gson;
import com.isoft.model.PageBean;
import com.isoft.model.TdicCode;
import com.isoft.service.DicListService;
import com.isoft.util.UUIDUtils;

@Controller
public class DicListController {

	@Resource
	private DicListService dicListService;
	/**
	 * 查询数据字典数据类型
	 */
	@RequestMapping(value="getDicType.do")
	public void getDicType(HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		//设置编码
		response.setContentType("text/html;charset=utf-8");
		//调用service的方法，返回值List<TdicCode>
		String dicType = dicListService.getDicTypeSelect();
		//将数据写回页面
		PrintWriter pw=response.getWriter();
		pw.write(dicType);
		pw.flush();
		pw.close();
	}
	
	/**
	 * 加载数据字典所有数据
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value="getAllDicCodeJSON.do")
	public void getAllDicCodeJSON(HttpServletRequest request,HttpServletResponse response) throws Exception{
		//设置编码
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		//获取数据
		String tName = request.getParameter("tName");
		String tType = request.getParameter("tType");
		System.out.println(request.getParameter("curpage"));
		int currPage = Integer.parseInt(request.getParameter("curpage")==null?"0":request.getParameter("curpage"));//==null?"0":request.getParameter("curpage")
		
		int pageSize = Integer.parseInt("5");//这里可以写死

		//封装数据
		tType = tType.equals("mySelAll")?"":tType;
		
		//调用service的getAllDicCode方法，返回值List<TdicCode>
		PageBean<TdicCode> page = dicListService.getAllDicCode2(tName,tType,currPage,pageSize);
		//判断list集合是否为空
		if(page.getTotalCount()>0){
			//将数据写回页面new Gson().toJson(list)
			PrintWriter pw=response.getWriter();
			pw.write(new Gson().toJson(page));
			pw.flush();
			pw.close();
		}else{
			System.out.println("查询失败");
		}
	}
	
	
	
	/**
	 * 添加和更新数据字典
	 * @param dic
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value="addTDicCode.do")
	public void addTDicCode(TdicCode dic, HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		//设置编码
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		String msg = "";
		if(dic.gettDicId().equals("")){
			//执行添加数据字典操作
			dic.settDicId(UUIDUtils.getId());
			dic.settDicCode(UUIDUtils.getId());
			//调用service的方法，返回值List<TdicCode>
			if(dicListService.addTDicCode(dic)){
				msg = "数据字典添加成功！";
			}else{
				msg = "数据字典添加失败！";
			}
		}else{
			//执行更新数据字典操作
			//调用service的方法，返回值List<TdicCode>
			System.out.println(dic.gettDicId());
			if(dicListService.updateTDicCode(dic)){
				msg = "数据字典更新成功！";
			}else{
				msg = "数据字典更新失败！";
			}
		}
		//将数据写回页面
		PrintWriter pw=response.getWriter();
		pw.write(msg);
		pw.flush();
		pw.close();
	}
	
	/**
	 * 将数据展现在更新模态窗
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value="openUpdateWin.do")
	public void openUpdateWin(HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		//设置编码
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		//获取数据字典的id
		String tDicId = request.getParameter("tDicId");

		//调用service的方法，返回值List<TdicCode>
		TdicCode dic = dicListService.selectByPrimaryKey(tDicId);
		
		if(dic!=null){
			//将数据写回页面
			PrintWriter pw=response.getWriter();
			pw.write(new Gson().toJson(dic));
			pw.flush();
			pw.close();
		}
	}
	
	/**
	 * 	删除数据字典
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value="deleteTDicCode.do")
	public void deleteTDicCode(HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		//设置编码
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		//获取数据字典的id
		String tDicId = request.getParameter("tDicId");
		String msg = "";
		//调用service的方法，返回值List<TdicCode>
		if(dicListService.deleteTDicCode(tDicId)){
			msg = "删除成功！";
		}else{
			msg = "删除失败！";
		}
		//将数据写回页面
		PrintWriter pw=response.getWriter();
		pw.write(msg);
		pw.flush();
		pw.close();
	}
	
	
	
	//得到第一个下拉力表里面是code id是用来更新第一个表
	@RequestMapping(value="getroledown.do")
	public void getAllTDicCodeJSON1(TdicCode dicCode,HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter pw = response.getWriter();
		pw.write(new Gson().toJson(dicListService.getDicType2(dicCode)));
		pw.close();
	}
	
	//得到第一个下拉力表里面是code id是用来更新第二个表
	@RequestMapping(value="getroledown2.do")
	public void getAllTDicCodeJSON2(TdicCode dicCode,HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter pw = response.getWriter();
		pw.write(new Gson().toJson(dicListService.getDicType3(dicCode)));
		pw.close();
	}

}
