package com.owra.web.example.service;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.owra.web.example.domain.common.SessionVO;
import com.owra.web.example.mapper.LoginMapper;

@Service("LoginService")
public class LoginService {

	@Resource(name="LoginMapper")
	private LoginMapper mapper;

	public SessionVO getUserRetrieve(HashMap<String, String> param) {
		
		return mapper.getUserRetrieve(param);
	}

	public List<String> getUserGrpRetrieve(HashMap<String, String> param) {
		// TODO Auto-generated method stub
		return mapper.getUserGrpRetrieve(param);
	}

	public void createloginHist(HashMap<String, Object> loginHist) {
		mapper.createloginHist(loginHist);
	}

	public List<HashMap<String, Object>> getMenuRetrieve(SessionVO loginUserVo) {
		// TODO Auto-generated method stub
		return mapper.getMenuRetrieve(loginUserVo);
	}

	public void lastLoginUpdate(SessionVO loginUserVo) {
		// TODO Auto-generated method stub
		mapper.lastLoginUpdate(loginUserVo);
	}
	
}
