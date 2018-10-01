package com.owra.web.example.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.owra.web.common.LoginManager;
import com.owra.web.example.domain.common.ReturnDataVO;
import com.owra.web.example.domain.common.SessionVO;
import com.owra.web.example.service.LoginService;

@Controller
public class LoginController implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1665314029625894394L;

	@Resource(name="LoginService")
	private LoginService mapper;
	
	@RequestMapping(value="/login")
	public String loginPage() {
		System.out.println("ll");
		return "login";
	}
	
	@RequestMapping(value="checkLoginUser", method=RequestMethod.POST)
	public @ResponseBody ReturnDataVO checkLoginUser(@RequestParam HashMap<String, String> param, HttpServletRequest request, HttpServletResponse response){
		ReturnDataVO result = new ReturnDataVO();
		HttpSession session = request.getSession(true);
		SessionVO loginUserVo = new SessionVO();
		LoginManager loginManager = LoginManager.getInstance();
		param.put("cnct_ip", request.getRemoteAddr());
		loginUserVo = mapper.getUserRetrieve(param);
		System.out.println(loginUserVo);
		List<String> authGrp = new ArrayList<String>();
		try {
			if(loginUserVo != null ){
				authGrp = (List<String>) mapper.getUserGrpRetrieve(param);
				if(authGrp.size() < 1){
					HashMap<String, Object> loginHist = new HashMap<String, Object>();
					loginHist.put("user_id", param.get("user_id"));
					loginHist.put("IP", request.getRemoteAddr());
					loginHist.put("cnct_scs_yn", "N");
					mapper.createloginHist(loginHist);
					result.setResultCode("S999");
					result.setResultMsg("로그인 권한이 없습니다.");
					return result;
				}
				loginUserVo.setUser_group(authGrp);
				result.setResultCode("S000");
				result.setResultMsg("success");
				List<HashMap<String, Object>> menuList = new ArrayList<HashMap<String,Object>>();
				menuList = mapper.getMenuRetrieve(loginUserVo);
				
				loginUserVo.setMenu(menuList);
				session.setAttribute("S_USER", loginUserVo);
				session.setAttribute("S_LOGIN_YN"	, "Y");
				session.setAttribute("theme", "default");
				//최근로그인insert
				mapper.lastLoginUpdate(loginUserVo);
				HashMap<String, Object> loginHist = new HashMap<String, Object>();
				loginHist.put("user_id", loginUserVo.getUser_id());
				loginHist.put("ip", request.getRemoteAddr());
				loginHist.put("cnct_scs_yn", "Y");
				mapper.createloginHist(loginHist);
				loginManager.setSession(session, (String) loginUserVo.getUser_id());
			} else {
				HashMap<String, Object> loginHist = new HashMap<String, Object>();
				loginHist.put("user_id", param.get("user_id"));
				loginHist.put("ip", request.getRemoteAddr());
				loginHist.put("cnct_scs_yn", "N");
				mapper.createloginHist(loginHist);
				result.setResultCode("S999");
				result.setResultMsg("아이디/패스워드/IP가 등록되어 있지 않습니다.");
			}
		} catch (Exception e) {
			HashMap<String, Object> loginHist = new HashMap<String, Object>();
			loginHist.put("user_id", param.get("user_id"));
			loginHist.put("ip", request.getRemoteAddr());
			loginHist.put("cnct_scs_yn", "N");
			mapper.createloginHist(loginHist);
			e.printStackTrace();
			result.setResultCode("S999");
			result.setResultMsg("에러 발생..");
		}
		return result ;
	}
	@RequestMapping(value="/logout")
	public String logout(HttpServletRequest request, HttpServletResponse response, ModelMap model, RedirectAttributes redirectAttributes) throws Exception {
		HttpSession session = request.getSession(true);
		try
		{
			if(session == null)
			{
				return "redirect:/loginPage.ke";
			}
			
			String isLogon 	= (String) session.getAttribute("S_LOGIN_YN");
			SessionVO loginUserVo =  (SessionVO) session.getAttribute("S_USER");
			
			if(isLogon != null && isLogon.equals("Y") && loginUserVo != null && !("").equals(loginUserVo.getUser_id()))
			{
				session.setAttribute("S_USER", 	null);
				session.setAttribute("S_LOGIN_YN", 	null);

				session.removeAttribute("S_USER");
				session.removeAttribute("S_LOGIN_YN");

				//쿠키 제거
				for(Cookie cookie : request.getCookies())
				{
					if(cookie.getName().startsWith("owra_c_"))
					{
						cookie.setValue(null);
						cookie.setMaxAge(0);
						response.addCookie(cookie);
					}
				}
				
				session.invalidate();

			}
			
		}catch(IllegalStateException ise) {
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		
		redirectAttributes.addFlashAttribute("logoutMag", "로그아웃 되었습니다.");
		return "redirect:/login" ;
	}
}
