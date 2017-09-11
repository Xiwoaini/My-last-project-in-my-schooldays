package com.isoft.util;



import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.isoft.model.TsysUSer;

//验证session过滤器
@WebFilter(filterName="loginFilter",urlPatterns="*.do")
public class GuoLv extends HttpServlet implements Filter {  
    /**
	 * 
	 */
	private static final long serialVersionUID = -3570894039919069542L;

	public void destroy() {  
    }  
  
    public void doFilter(ServletRequest sRequest, ServletResponse sResponse,        
            FilterChain filterChain) throws IOException, ServletException{  
        //转换成http请求
        HttpServletRequest request = (HttpServletRequest) sRequest;        
        HttpServletResponse response = (HttpServletResponse) sResponse;        
        HttpSession session = request.getSession();        
        String url=request.getServletPath();    
        String contextPath=request.getContextPath();    
        if(url.equals("")) url+="/";    
        if((url.startsWith("/")
        		&&!url.startsWith("/goLoginPage.do"))
        		&&!url.startsWith("/goRegister.do")
        		&&!url.startsWith("/goSuccess.do")
        		&&!url.startsWith("/goCode.do")
        		&&!url.startsWith("/checkName.do")
        		){
        	//若访问后台资源 过滤到login    
            TsysUSer user=(TsysUSer)session.getAttribute("userPeople");
            
             if(user==null){//转入管理员登陆页面    /
                  response.sendRedirect(contextPath);   
                  return;    
             }    
             
        }    
          filterChain.doFilter(sRequest, sResponse);      
    }    
  
    public void init(FilterConfig arg0) throws ServletException {  
    }  
}  
