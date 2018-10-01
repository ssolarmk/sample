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
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.owra.web.example.domain.common.ReturnDataVO;
import com.owra.web.example.domain.common.SessionVO;
import com.owra.web.example.domain.manage.PermissionVO;
import com.owra.web.example.service.manage.PermissionService;

@Controller
@RequestMapping("/manage/permission")
public class PermissionController implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4599467413630655742L;
	
	@Resource(name="PermissionService")
	private PermissionService mapper;
	
	@RequestMapping(value="/view")
	public String view() {
		return "manage/permissionView";
	}
	
	@RequestMapping(value="/getMenuTreeRetrieve")
    public @ResponseBody List<HashMap<String, Object>> listRetrieve1(@RequestParam HashMap<String, Object> hashmapParam){
    	List<HashMap<String, Object>> resultList = new ArrayList<HashMap<String,Object>>();
    	List<HashMap<String, Object>> resultData = new ArrayList<HashMap<String,Object>>();
    	
    	
    	String auth_grp_cd = (String) hashmapParam.get("auth_grp_cd");
		hashmapParam.put("auth_grp_cd", auth_grp_cd); 
		
		
		resultList =  (ArrayList<HashMap<String, Object>>) mapper.siteListMenu1(hashmapParam);
		for(HashMap<String, Object> map : resultList){
			if("true".equals(map.get("status"))){
				map.put("checked", true);
			} 
			resultData.add(map);
		}
		
		List<HashMap<String, Object>> R = new ArrayList<>();
		List<HashMap<String, Object>> D = new ArrayList<>();
		List<HashMap<String, Object>> P = new ArrayList<>();
		List<HashMap<String, Object>> A = new ArrayList<>();
		
		for(HashMap<String, Object> menu:resultData) {
			if     ("R".equals(menu.get("menuTp"))) {
				R.add(menu);
			}
			else if("D".equals(menu.get("menuTp"))) {
				menu.put("state", "closed");
				D.add(menu);
			}
			else if("P".equals(menu.get("menuTp"))) {
				menu.put("state", "closed");
				P.add(menu);
			}
			else if("A".equals(menu.get("menuTp"))) {
				A.add(menu);
			}
		}
		
		List<HashMap<String, Object>> resultP = new ArrayList<>();
		
		for(HashMap<String, Object> menu:P){
			
			List<HashMap<String, Object>> subA = new ArrayList<>();
			for(HashMap<String, Object> subMenu:A) {
				if(menu.get("id").equals(subMenu.get("parent"))) {
					subA.add(subMenu);
				}
			}
			menu.put("children", subA);
			
			resultP.add(menu);
		}
		
		List<HashMap<String, Object>> resultD = new ArrayList<>();
		
		for(HashMap<String, Object> menu:D){
			List<HashMap<String, Object>> subP = new ArrayList<>();
			for(HashMap<String, Object> subMenu:resultP) {
				if(menu.get("id").equals(subMenu.get("parent"))) {
					subP.add(subMenu);
				}
			}
			menu.put("children", subP);
			
			resultD.add(menu);
		}
		
		List<HashMap<String, Object>> resultR = new ArrayList<>();
		for(HashMap<String, Object> menu:R){
			List<HashMap<String, Object>> subD = new ArrayList<>();
			for(HashMap<String, Object> subMenu:resultD) {
				if(menu.get("id").equals(subMenu.get("parent"))) {
					subD.add(subMenu);
				}
			}
			menu.put("children", subD);
			
			resultR.add(menu);
		}
			
			
			
		return resultR;
    }
	@RequestMapping(value="/update", method= RequestMethod.POST)
    public @ResponseBody ReturnDataVO PermissionUpdate(@ModelAttribute("PermissionVO") @Valid PermissionVO permissionVO, BindingResult bindResult, HttpSession session){
    	ReturnDataVO result = new ReturnDataVO();
    	SessionVO member = (SessionVO) session.getAttribute("S_USER");
    	permissionVO.setUser_id(member.getUser_id());
		try {
			if (bindResult.hasErrors()) {
	            // 에러 출력
	            List<ObjectError> list = bindResult.getAllErrors();
	            for (ObjectError e : list) {
	            	result.setResultMsg(e.getDefaultMessage());
	            }
	            result.setResultCode("V999");
	            
	            return result;
	        }
			
			mapper.permissionDelete(permissionVO);

			String[] menu_cd_list = permissionVO.getMenu_cd().split(",");
			for (int i = 0;i < menu_cd_list.length;i++){
				String menu_cd = menu_cd_list[i];
				PermissionVO input_permissionVO = new PermissionVO();
				
				input_permissionVO.setAuth_grp_cd(permissionVO.getAuth_grp_cd());
				input_permissionVO.setMenu_cd(menu_cd);
				input_permissionVO.setSite_gb_cd(permissionVO.getSite_gb_cd());
				input_permissionVO.setUser_id(permissionVO.getUser_id());
				if(mapper.permissionInsert(input_permissionVO) == 1){
					result.setResultCode("S000");
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			result.setResultCode("S999");
			result.setResultMsg("이미 존재하는 코드입니다.");
		}
    	return result;
    }
}
