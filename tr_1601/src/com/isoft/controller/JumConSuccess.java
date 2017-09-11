package com.isoft.controller;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Random;
import java.util.UUID;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.isoft.model.Tattenchment;
import com.isoft.model.Tperson;
import com.isoft.model.TsysUSer;
import com.isoft.model.VOPersonAtt;
import com.isoft.service.IUploadService;
import com.isoft.service.TpersonService;
import com.isoft.service.TsysuserService;

//跳转首页
@Controller
public class JumConSuccess {
	@Resource
	private TsysuserService userService;
	@Resource
	private TpersonService personService;
	@Resource
	private IUploadService upserrvice;
	@RequestMapping(value="goLoginPage.do")
	public String goToLoginPage(HttpServletRequest request,HttpServletResponse response) throws UnsupportedEncodingException
	{
		if(request.getSession().getAttribute("userPeople")!=null){
			return "userManager/main";
			
		}
		
//		跳转login.jsp
		return "userManager/login";
	}
	
	
	@RequestMapping(value="gosuccessPage.do")
	public String goTosuccessPage(HttpServletRequest request,HttpServletResponse response) throws UnsupportedEncodingException
	{
//		跳转login.jsp
		return "userManager/main";
	}
	
	
	@RequestMapping(value="goRegister.do")
	@ResponseBody
	public String goRegisterPage(HttpServletRequest request,HttpServletResponse response) throws UnsupportedEncodingException
	{
//		注册用户
		String registerName =request.getParameter("registerName");
	    String userPassword=request.getParameter("userPassword");
	    String userPassword1=request.getParameter("userPassword1");
	    if(userService.selectByuserName(registerName))
	    {
	    	return "fail1";
	    }
	    if(!userPassword.equals(userPassword1))
	    {
	    	return "fail2";
	    }
	    TsysUSer user =new TsysUSer();
	    user.settUserName(registerName);
	    user.settUserPwd(userPassword);
	    if(userService.insertSelective(user))
	    {
	    	//获取假的t_role
	    	TsysUSer user1=userService.asUserRole(user.gettUserId());
	    	Tperson person =new Tperson();
	    	person.settPresonId("p"+new Random().nextInt(300));
	    	person.settUserId(user.gettUserId());
	    	if(personService.insertSelective(person))//添加到person表格
	    	{
	    		Tattenchment attment=new Tattenchment();
	    		attment.settAttid(UUID.randomUUID().toString());
	    		attment.settId(person.gettPresonId());
	    		if(upserrvice.uploadFile(attment))
	    		{
	    			request.getSession().removeAttribute("tperson1");
	    			request.getSession().removeAttribute("userPeople");
	    			VOPersonAtt person1=personService.selectByuserId(user.gettUserId());
	    			request.getSession().setAttribute("tperson1",person1);
		    		request.getSession().setAttribute("userPeople",user1);
		    		return "success";
	    		}
	    		
	    	}
	    }
		return "fail";
	}
	//登录
	@RequestMapping(value="goSuccess.do")
	@ResponseBody
	public String goSuccessPage(HttpServletRequest request,HttpServletResponse response,HttpSession session) throws UnsupportedEncodingException
	{
		if(request.getServletContext().getAttribute("userPeoplehuang")!=null){
			request.getSession().removeAttribute("userPeople");
			request.getSession().removeAttribute("tperson1");
			request.getSession().invalidate();
			 
			request.getServletContext().removeAttribute("userPeoplehuang");
		
		}
		          
		
		TsysUSer user= new TsysUSer();
		String username=request.getParameter("username");
		String password=request.getParameter("password");
		String rCode=request.getParameter("codeValue");
		user.settUserName(username);
		user.settUserPwd(password);
		String sCode= (String) request.getSession().getAttribute("sessionCode");
		 request.getSession().removeAttribute("sessionCode");
	
		 
		 //判断两次验证码是否
//		   if(!rCode.equalsIgnoreCase(sCode))
//		   {
//			   //输入不一致
//			    return "fail2";
//		   }
		   
		   TsysUSer sessionUser=userService.selectByuser(user);
		   if(sessionUser!=null)
		   {
			   String userid=sessionUser.gettUserId();
			   VOPersonAtt tperson1=personService.selectByuserId(userid);
			   if(tperson1!=null)
			   {
				   request.getSession().setAttribute("tperson1",tperson1);
			   }
			   TsysUSer user1=userService.asUserRole(userid);//此时改属性t_role已经被替换
			   if(user1!=null)
			   {
				   request.getServletContext().setAttribute("userPeoplehuang","huang");
				   request.getSession().setAttribute("userPeople",user1);
			   }
//			   request.getSession().setAttribute("userPeople",sessionUser);
		 
			 
			   return "login_success";
		   }
		   return "login_success";
		
//      return "fail3";
		
	}
	
	
	@RequestMapping(value="goCode.do")
	public void goCodePage(HttpServletRequest request,HttpServletResponse response) throws IOException
	{
//随机图片		int charNum = 4;
		int width = 30 * 4;
		int height = 30;

		// 1. 创建一张内存图片
		BufferedImage bufferedImage = new BufferedImage(width, height,
				BufferedImage.TYPE_INT_RGB);

		// 2.获得绘图对象
		Graphics graphics = bufferedImage.getGraphics();

		// 3、绘制背景颜色
		graphics.setColor(Color.YELLOW);
		graphics.fillRect(0, 0, width, height);

		// 4、绘制图片边框
		graphics.setColor(Color.BLUE);
		graphics.drawRect(0, 0, width - 1, height - 1);

		// 5、输出验证码内容
		graphics.setColor(Color.RED);
		graphics.setFont(new Font("宋体", Font.BOLD, 20));

		// 随机输出4个字符
		Graphics2D graphics2d = (Graphics2D) graphics;
		 String s = "ABCDEFGHGKLMNPQRSTUVWXYZ23456789";
		Random random = new Random();
		//session中要用到
		String msg="";
		int x = 5;
		for (int i = 0; i < 4; i++) {
			int index = random.nextInt(32);
			String content = String.valueOf(s.charAt(index));
			msg+=content;
			double theta = random.nextInt(45) * Math.PI / 180;
			//让字体扭曲
            graphics2d.rotate(theta, x, 18);
			graphics2d.drawString(content, x, 18);
			graphics2d.rotate(-theta, x, 18);
			x += 30;
		}
          //将验证码放入session中
	request.getSession().setAttribute("sessionCode",msg);
		// 6、绘制干扰线
		graphics.setColor(Color.GRAY);
		for (int i = 0; i < 5; i++) {
			int x1 = random.nextInt(width);
			int x2 = random.nextInt(width);

			int y1 = random.nextInt(height);
			int y2 = random.nextInt(height);
			graphics.drawLine(x1, y1, x2, y2);
		}

		// 释放资源
		graphics.dispose();

		// 图片输出 ImageIO
		ImageIO.write(bufferedImage, "jpg", response.getOutputStream());
		
	}

	// 姓名验证
	@RequestMapping(value="checkName.do")
	@ResponseBody
	public String checkUserName(HttpServletRequest request,HttpServletResponse response)
	{
		String name=request.getParameter("registerName");//获取值
		if(userService.selectByuserName(name))
		{
			return "fail";
		}
		return "success";
	}
	
	//密码校验
		@RequestMapping(value="checkoldpwd.do")
		@ResponseBody
		public String checkoldpwd(HttpServletRequest request,HttpServletResponse response) throws UnsupportedEncodingException
		{
			request.setCharacterEncoding("utf-8");
			TsysUSer user =new TsysUSer();
			String userid=request.getParameter("tuuid");
			String pwd=request.getParameter("tUserPwd");
			user.settUserId(userid);
			user.settUserPwd(pwd);
			if(userService.selUserByPwd(user))
			{
				return "success";
			}
			return "fail";
		}
		//密码更新
		@RequestMapping(value="uppwd.do")
		@ResponseBody
		public String uppwd(HttpServletRequest request,HttpServletResponse response)
		{
			TsysUSer user = new TsysUSer();
			String pwd1=request.getParameter("userPwd");
			String userid=request.getParameter("Userid");
			user.settUserId(userid);
			user.settUserPwd(pwd1);
			if(userService.updatePassword(user))
			{
				return "success";
			}
			return "fail";
		}
		//更新用户状态
		@RequestMapping(value="updateUserStatus.do")
		@ResponseBody
		public String updateUserStatus(TsysUSer user,HttpServletRequest request,HttpServletResponse response)
		{
			if(userService.updateStatus(user))
			{
				return "success";
			}
			return "fail"; 
		}
		
		//密码初始化
				@RequestMapping(value="Resetpwd.do")
				@ResponseBody
				public String Resetpwd(TsysUSer user,HttpServletRequest request,HttpServletResponse response)
				{
					if(userService.updatePassword(user))
					{
						return "success";
					}
					return "fail"; 
				}
	            //二级删除	
				@RequestMapping(value="delUser.do")
				@ResponseBody
				public String delUser(HttpServletRequest request,HttpServletResponse response)
				{
					String userid=request.getParameter("tUserId");
					if(userService.deleteByPrimaryKey(userid))
					{
						if(personService.DelFromUseId(userid))
						{
							return "success";
						}
					}
					return "fail";
				}
				//更新	
				@RequestMapping(value="updateUserRolef.do")
				@ResponseBody
				public String updateUserRolef(Tperson person,TsysUSer user, HttpServletRequest request,HttpServletResponse response)
				{
					if(userService.updateUserRole(user))
					{
						
						
							return "success";
						
					}
					return "fail";
				}
}
