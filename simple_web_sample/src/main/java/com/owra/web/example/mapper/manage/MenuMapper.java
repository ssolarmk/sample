package com.owra.web.example.mapper.manage;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.owra.web.example.domain.manage.MenuVO;

@Repository("MenuMapper")
public interface MenuMapper {

	List<HashMap<String, Object>> getMenuTreeRetrieve();

	List<HashMap<String, Object>> codelist(HashMap<String, String> hashmapParam);

	int menuCreate(MenuVO menuVO);

	int menuUpdate(MenuVO menuVO);

	Integer getMenuCount(MenuVO menuVO);

	int menuDelete(MenuVO menuVO);

}
