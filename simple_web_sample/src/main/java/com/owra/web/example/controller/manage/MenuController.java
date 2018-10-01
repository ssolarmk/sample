package com.owra.web.example.controller.manage;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.owra.web.common.util.ValidateUtil;
import com.owra.web.example.domain.common.ReturnDataVO;
import com.owra.web.example.domain.common.SessionVO;
import com.owra.web.example.domain.manage.MenuVO;
import com.owra.web.example.service.manage.MenuService;

@Controller
@RequestMapping("/manage/menu")
public class MenuController implements Serializable{


	/**
	 * 
	 */
	private static final long serialVersionUID = -6355703923059082755L;
	
	@Resource(name="MenuService")
	private MenuService mapper;

	
	@RequestMapping(value="/view")
	public String view() {
		return "manage/menuView";
	}
	
	@RequestMapping("/getMenuTreeRetrieve")
	public @ResponseBody List<HashMap<String, Object>> getMenuTreeRetrieve(){
		HashMap<String, Object> result = new HashMap<>();
		
		List<HashMap<String, Object>> menuList = new ArrayList<>();
		
		menuList = mapper.getMenuTreeRetrieve();
		
		List<HashMap<String, Object>> R = new ArrayList<>();
		List<HashMap<String, Object>> D = new ArrayList<>();
		List<HashMap<String, Object>> P = new ArrayList<>();
		List<HashMap<String, Object>> A = new ArrayList<>();
		
		for(HashMap<String, Object> menu:menuList) {
			if     ("R".equals(menu.get("MENU_TP"))) {
				R.add(menu);
			}
			else if("D".equals(menu.get("MENU_TP"))) {
				menu.put("state", "closed");
				D.add(menu);
			}
			else if("P".equals(menu.get("MENU_TP"))) {
				menu.put("state", "closed");
				P.add(menu);
			}
			else if("A".equals(menu.get("MENU_TP"))) {
				A.add(menu);
			}
		}
		
		List<HashMap<String, Object>> resultP = new ArrayList<>();
		
		for(HashMap<String, Object> menu:P){
			if(A.size() > 0) {
				List<HashMap<String, Object>> subA = new ArrayList<>();
				for(HashMap<String, Object> subMenu:A) {
					if(menu.get("MENU_CD").equals(subMenu.get("MENU_PRNT_CD"))) {
						subA.add(subMenu);
					}
				}
				menu.put("children", subA);
				
				resultP.add(menu);
			}
		}
		
		List<HashMap<String, Object>> resultD = new ArrayList<>();
		
		for(HashMap<String, Object> menu:D){
			if(resultP.size() > 0) {
				List<HashMap<String, Object>> subP = new ArrayList<>();
				for(HashMap<String, Object> subMenu:resultP) {
					if(menu.get("MENU_CD").equals(subMenu.get("MENU_PRNT_CD"))) {
						subP.add(subMenu);
					}
				}
				menu.put("children", subP);
				
				resultD.add(menu);
			}
		}
		
		List<HashMap<String, Object>> resultR = new ArrayList<>();
		for(HashMap<String, Object> menu:R){
			List<HashMap<String, Object>> subD = new ArrayList<>();
			for(HashMap<String, Object> subMenu:resultD) {
				if(menu.get("MENU_CD").equals(subMenu.get("MENU_PRNT_CD"))) {
					subD.add(subMenu);
				}
			}
			menu.put("children", subD);
			
			resultR.add(menu);
		}
		
		return resultR;
	}
	/**
	 * 코드목록 가져오기
	 * @param hashmapParam
	 * @return json
	 */
	@RequestMapping(value="/upMenuRetrieve")
	public @ResponseBody List<HashMap<String, Object>> codeList(@RequestParam HashMap<String, String> hashmapParam){
		List<HashMap<String, Object>> list = new ArrayList<HashMap<String,Object>>();
		try {
			list = (List<HashMap<String, Object>>) mapper.codelist(hashmapParam);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	/**
	 * 코드목록 생성
	 * @param hashmapParam
	 * @return json
	 */
	@RequestMapping(value="/menuCreate", method= RequestMethod.POST)
    public@ResponseBody ReturnDataVO MenuCreate(@ModelAttribute("MenuVO") @Valid MenuVO menuVO, BindingResult bindResult, HttpSession session){
	ReturnDataVO result = new ReturnDataVO();
	SessionVO member = (SessionVO) session.getAttribute("S_USER");
	menuVO.setUser_id(member.getUser_id());
		try {
			result = ValidateUtil.validCheck(bindResult, result);
			
			if(result.getResultCode().equals("V999")){
				return result;
			}
			
			if(mapper.menuCreate(menuVO) == 1){
				result.setResultCode("S000");
			}
		} catch (Exception e) {
			e.printStackTrace();
			result.setResultCode("S999");
			result.setResultMsg("이미 존재하는 코드입니다.");
		}
    	return result;
    }


	/**
	 * 메뉴 수정 저장
	 * @param hashmapParam
	 * @return json
	 */
	@RequestMapping(value="/menuUpdate", method= RequestMethod.POST)
	public@ResponseBody ReturnDataVO MenuUpdate(@ModelAttribute("MenuVO") @Valid MenuVO menuVO, BindingResult bindResult, HttpSession session){
		ReturnDataVO result = new ReturnDataVO();
		SessionVO member = (SessionVO) session.getAttribute("S_USER");
		menuVO.setUser_id(member.getUser_id());
			try {
				result = ValidateUtil.validCheck(bindResult, result);
				
				if(result.getResultCode().equals("V999")){
					return result;
				}
				
				if(mapper.menuUpdate(menuVO) == 1){
					result.setResultCode("S000");
				}
			} catch (Exception e) {
				e.printStackTrace();
				result.setResultCode("S999");
				result.setResultMsg("이미 존재하는 코드입니다.");
			}
	    	return result;
    }
	/**
	 * 메뉴목록 삭제
	 * @param hashmapParam
	 * @return json
	 */
	@RequestMapping(value="/menuDelete", method= RequestMethod.POST)
    public @ResponseBody ReturnDataVO menuDelete(@ModelAttribute("menuVO") @Valid MenuVO menuVO, BindingResult bindResult, HttpSession session){
	ReturnDataVO result = new ReturnDataVO();
	SessionVO member = (SessionVO) session.getAttribute("S_USER");
	menuVO.setUser_id(member.getUser_id());
		try {
			result = ValidateUtil.validCheck(bindResult, result);
			if(result.getResultCode().equals("V999")){
				return result;
			}
			
			int cnt = (Integer)mapper.getMenuCount(menuVO);
			if(cnt != 1){
				result.setResultCode("S999");
				result.setResultMsg("하위코드가 있는 코드는 삭제 할 수 없습니다.");
				return result;
			}
			if(mapper.menuDelete(menuVO) == 1){
				result.setResultCode("S000");
			}
		} catch (Exception e) {
			e.printStackTrace();
			result.setResultCode("S999");
			result.setResultMsg(null);
		}
    	return result;
    }
}
