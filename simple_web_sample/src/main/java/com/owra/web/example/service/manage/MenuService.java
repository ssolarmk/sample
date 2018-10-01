package com.owra.web.example.service.manage;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.springframework.stereotype.Service;

import com.owra.web.example.domain.manage.MenuVO;
import com.owra.web.example.mapper.manage.MenuMapper;

@Service("MenuService")
public class MenuService {

	@Resource(name="MenuMapper")
	private MenuMapper mapper;
	
	public List<HashMap<String, Object>> getMenuTreeRetrieve() {
		// TODO Auto-generated method stub
		return mapper.getMenuTreeRetrieve();
	}

	public List<HashMap<String, Object>> codelist(HashMap<String, String> hashmapParam) {
		// TODO Auto-generated method stub
		return mapper.codelist(hashmapParam);
	}

	public int menuCreate(MenuVO menuVO) {
		// TODO Auto-generated method stub
		return mapper.menuCreate(menuVO);
	}

	public int menuUpdate(MenuVO menuVO) {
		// TODO Auto-generated method stub
		return mapper.menuUpdate(menuVO);
	}

	public Integer getMenuCount(MenuVO menuVO) {
		// TODO Auto-generated method stub
		return mapper.getMenuCount(menuVO);
	}

	public int menuDelete(MenuVO menuVO) {
		// TODO Auto-generated method stub
		return mapper.menuDelete(menuVO);
	}

}
