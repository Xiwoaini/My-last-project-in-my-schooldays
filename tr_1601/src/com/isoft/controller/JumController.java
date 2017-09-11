package com.isoft.controller;

import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.isoft.model.TdicCode;
import com.isoft.service.TpersonService;
import com.isoft.service.TsysuserService;
import com.isoft.service.AgencyService;
import com.isoft.service.DicListService;
import com.isoft.service.EquipLogService;


@Controller
public class JumController {
	@Resource
	private TpersonService personservice;
	@Resource
	private TsysuserService usersevice;
	@Resource
	private DicListService dicListService;
	
	@Resource
	private EquipLogService equipLogService;
	
	@Resource
	private AgencyService agencyService;
	
	@RequestMapping(value="dicList.do")
	public String dicList(HttpServletRequest request,HttpServletResponse response) throws UnsupportedEncodingException
	{
//		跳转login.jsp
		return "userManager/dicList";
	}
	
	@RequestMapping(value="equipLog.do")
	public String equipLog(HttpServletRequest request,HttpServletResponse response) throws UnsupportedEncodingException
	{
		return "userManager/equipLog";
	}

	
	@RequestMapping(value="agency.do")
	public String agency(HttpServletRequest request,HttpServletResponse response) throws UnsupportedEncodingException
	{	

//		跳转login.jsp
		return "userManager/agency";
	}
	
	@RequestMapping(value="personCore.do")
	public String personCore(HttpServletRequest request,HttpServletResponse response) throws UnsupportedEncodingException
	{
		//在跳转加入一个类型的下拉列表
		List<TdicCode> DicList= personservice.selectTdlist();
		request.setAttribute("dicList",DicList);
		//将总页数加载进去
//		跳转login.jsp
		return "userManager/personCore";
	}
	
	
	@RequestMapping(value="changePwd.do")
	public String changePwd(HttpServletRequest request,HttpServletResponse response) throws UnsupportedEncodingException
	{
//		跳转修改密码
		return "userManager/changePwd";
	}
	@RequestMapping(value="mylogout.do")
	public String logout(HttpServletRequest request,HttpServletResponse response) throws UnsupportedEncodingException
	{
//		跳回主页删除User的
		request.getSession().removeAttribute("userPeople");
		request.getSession().removeAttribute("tperson1");
		return "redirect:/index.jsp";
	}
	
	
	@RequestMapping(value="personManage.do")
	public String personManage(HttpServletRequest request,HttpServletResponse response) throws UnsupportedEncodingException
	{

//		跳转修改密码
		return "userManager/personManage";
	}
	

	@RequestMapping(value="help1.do")
	public String help1(HttpServletRequest request,HttpServletResponse response) throws UnsupportedEncodingException
	{
//		跳转修改密码
		return "userManager/changePwd";
	}
	
}
