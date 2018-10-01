package com.owra.web.example.controller.common;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.owra.web.example.service.common.CommonService;

@Controller
@RequestMapping(value="/common/")
public class CommonController implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1104092362325122191L;
	
	@Resource(name = "CommonService")
	private CommonService mapper;
	
	/**
	 * 코드목록 가져오기
	 * @param hashmapParam
	 * @return json
	 */
	@RequestMapping(value="codelist")
	public @ResponseBody List<HashMap<String, Object>> codeList(@RequestParam HashMap<String, String> hashmapParam){
		List<HashMap<String, Object>> list = new ArrayList<HashMap<String,Object>>();
		try {
			list = (List<HashMap<String, Object>>) mapper.getCodeList(hashmapParam);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	@RequestMapping(value = "authFalse")
	public String authFalseJspReturn() {
		return "/common/authFalse";
	}	
	
}
