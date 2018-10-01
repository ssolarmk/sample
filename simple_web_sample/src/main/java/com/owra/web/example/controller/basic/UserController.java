package com.owra.web.example.controller.basic;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.owra.web.example.domain.common.PageingVO;
import com.owra.web.example.domain.common.ReturnDataVO;
import com.owra.web.example.service.basic.UserService;

@Controller
@RequestMapping("/basic/user")
public class UserController implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7083478000856794149L;
	
	
	@Resource(name="UserService")
	private UserService mapper;
	
	@RequestMapping(value="/view")
	public String view() {
		return "basic/userView";
	}
	
	@RequestMapping(value="/userListRetrieve", method= RequestMethod.POST)
    public @ResponseBody HashMap<String, Object> userListRetrieve(@RequestParam HashMap<String, Object> hashmapParam
    		, @RequestParam(value="srch_auth_gr", required=false) List<String> authGrList
    		, @ModelAttribute("pageing") PageingVO pageing){
		
		List<HashMap<String, Object>> resultList = new ArrayList<HashMap<String,Object>>(); 
		HashMap<String, Object> hashmapResult = new HashMap<String, Object>();
		try {
			Integer page = pageing.getPage();
			if (page == 0) page = (Integer) 1;
			Integer rows = pageing.getRows();
			Integer start = (page - 1) * rows;
			Integer end = rows;
			
			
			int authGrListCnt = 0;
			if(authGrList != null){
				authGrListCnt = authGrList.size();
			}else{
				authGrListCnt = 0;
			}
			
			hashmapParam.put("auth_gr_cnt", authGrListCnt);
			hashmapParam.put("auth_gr", authGrList);
			
			hashmapParam.put("start", start);
			hashmapParam.put("end", end);
			
			resultList =  (List<HashMap<String, Object>>) mapper.getUserListRetrieve(hashmapParam);
			int records =  (int) mapper.getUserListRetrieveCnt(hashmapParam);
			
			pageing.setRecords(records);
			pageing.setTotal((int) Math.ceil((double)records / (double)pageing.getRows()));
		

			hashmapResult.put("page", pageing.getPage());
			hashmapResult.put("total", pageing.getTotal());
    		hashmapResult.put("records", pageing.getRecords());
			hashmapResult.put("rows", resultList);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return hashmapResult;
		
	}
	
	@RequestMapping(value="/userAuthGrpCdRetrieve", method= RequestMethod.POST)
    public @ResponseBody ReturnDataVO userAuthGrpCdRetrieve(@RequestParam HashMap<String, Object> hashmapParam){
    	ReturnDataVO result = new ReturnDataVO();
    	try {
    		result.setData(mapper.getAuthGrpCdRetrieve(hashmapParam));
    		result.setResultCode("S000");
    		
    	} catch (Exception e) {
			e.printStackTrace();
			result.setData(null);
    		result.setResultCode("S999");
		}  	
		return result;
    }

}
