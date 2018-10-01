package com.owra.web.common.Interceptor;

import java.util.HashMap;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.owra.web.example.domain.common.SessionVO;
import com.owra.web.example.service.common.CommonService;

import ch.qos.logback.classic.Logger;

@Component
public class AuthInterceptor implements HandlerInterceptor{
	
	static final Logger logger = (Logger) LoggerFactory.getLogger(AuthInterceptor.class);
	
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
		HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
	}
	
	@Resource(name = "CommonService")
	private CommonService mapper;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		//Session
		HttpSession session = request.getSession(false);			

		//Redirect-Url
		String requestURI 		= request.getRequestURI();
		
		if (session == null) {	
			logger.debug((String) request.getAttribute("S_LOGIN_YN"));
			logger.debug("##################isLogon : XXXXXXX");
			
			response.sendRedirect("/login");
			return false;
			
		} else {
			String isLogon 	= (String) session.getAttribute("S_LOGIN_YN");
			logger.debug("##################isLogon : "+isLogon);
			SessionVO member	= (SessionVO) session.getAttribute("S_USER");
			if (isLogon == null || !isLogon.equals("Y") || member == null) 
			{
				response.sendRedirect("/login");
				return false;
			}
			HashMap<String, Object> authMap = new HashMap<String, Object>();
			authMap.put("url", requestURI);
			authMap.put("user_group", member.getUser_group());
			int authCnt = (Integer) mapper.chkAuthRetrieve(authMap);
			
			if(authCnt == 0){
				if("/main".equals(authMap.get("url"))) return true;
				response.sendRedirect("/common/authFalse");
				return false;
			} else {
				return true;
			}
		}
	}
	
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub
		HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
	}
	
}
