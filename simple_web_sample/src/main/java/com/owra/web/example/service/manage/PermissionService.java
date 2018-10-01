package com.owra.web.example.service.manage;

import java.util.ArrayList;
import java.util.HashMap;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.owra.web.example.domain.manage.PermissionVO;
import com.owra.web.example.mapper.manage.PermissionMapper;

@Service("PermissionService")
public class PermissionService {
	
	@Resource(name="PermissionMapper")
	private PermissionMapper mapper;

	public ArrayList<HashMap<String, Object>> siteListMenu1(HashMap<String, Object> hashmapParam) {
		// TODO Auto-generated method stub
		return mapper.siteListMenu1(hashmapParam);
	}

	public void permissionDelete(PermissionVO permissionVO) {
		// TODO Auto-generated method stub
		mapper.permissionDelete(permissionVO);
		
	}

	public int permissionInsert(PermissionVO input_permissionVO) {
		// TODO Auto-generated method stub
		return mapper.permissionInsert(input_permissionVO);
	}

}
