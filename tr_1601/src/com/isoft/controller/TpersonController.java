package com.isoft.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.isoft.model.PageBean;
import com.isoft.model.Tperson;
import com.isoft.model.VOPersonAtt;
import com.isoft.model.VOUserPerson;
import com.isoft.service.TpersonService;
import com.isoft.service.TsysuserService;

@Controller
public class TpersonController {
	@Resource
	private TpersonService personservice;
	@Resource
	private TsysuserService userService;
	@RequestMapping(value="updatePerson.do")
	@ResponseBody
	//更新个人信息
	public String updatePerson( Tperson tperson,HttpServletRequest request,HttpServletResponse response) throws UnsupportedEncodingException
	{
	   if(personservice.updateByPrimaryKeySelective(tperson))
	   {
		   VOPersonAtt personc=(VOPersonAtt) request.getSession().getAttribute("tperson1");//获取对象
		   request.getSession().removeAttribute("tperson1");//移除旧的对象
		   
		   personc.settCname(tperson.gettCname());
		   personc.settEmail(tperson.gettEmail());
		   personc.settGender(tperson.gettGender());
		   personc.settMobile(tperson.gettMobile());
		   personc.settDicId(tperson.gettDicId());
		   request.getSession().setAttribute("tperson1",personc);//重新存储
		   return "success";
	   }
	   return "fail";
	}
	@RequestMapping(value="getUsermanage.do")
	@ResponseBody
	//分页获取数据
	public void getUsermanage(HttpServletRequest request,HttpServletResponse response) throws IOException
	{
		response.setCharacterEncoding("UTF-8");
		String username=request.getParameter("tUserName");
		PrintWriter pw =response.getWriter();
		String currentPage=request.getParameter("curpage")==null?"0":request.getParameter("curpage");
		String rows=request.getParameter("pageSize")==null?"5":request.getParameter("pageSize");
		PageBean<VOUserPerson> page =userService.getallcode2(username, currentPage, rows);
		pw.write(new Gson().toJson(page));
		pw.flush();
		pw.close();
	}
}
