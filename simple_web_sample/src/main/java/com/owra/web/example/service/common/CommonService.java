package com.owra.web.example.service.common;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.owra.web.example.mapper.common.CommonMapper;

@Service("CommonService")
public class CommonService {

	@Resource(name="CommonMapper")
	private CommonMapper mapper;

	public List<HashMap<String, Object>> getCodeList(HashMap<String, String> hashmapParam) {
		// TODO Auto-generated method stub
		return mapper.getCodeList(hashmapParam);
	}

	public Integer chkAuthRetrieve(HashMap<String, Object> authMap) {
		// TODO Auto-generated method stub
		return mapper.chkAuthRetrieve(authMap);
	}
	
}
