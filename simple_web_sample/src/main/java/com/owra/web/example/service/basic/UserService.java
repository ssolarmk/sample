package com.owra.web.example.service.basic;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.owra.web.example.mapper.basic.UserMapper;

@Service("UserService")
public class UserService {

	@Resource(name="UserMapper")
	private UserMapper mapper;
	
	public List<HashMap<String, Object>> getUserListRetrieve(HashMap<String, Object> hashmapParam) {
		// TODO Auto-generated method stub
		return mapper.getUserListRetrieve(hashmapParam);
	}

	public int getUserListRetrieveCnt(HashMap<String, Object> hashmapParam) {
		// TODO Auto-generated method stub
		return mapper.getUserListRetrieveCnt(hashmapParam);
	}

	public Object getAuthGrpCdRetrieve(HashMap<String, Object> hashmapParam) {
		// TODO Auto-generated method stub
		return mapper.getAuthGrpCdRetrieve(hashmapParam);
	}

}
