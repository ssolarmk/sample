package com.owra.web.example.mapper.manage;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.stereotype.Repository;

import com.owra.web.example.domain.manage.PermissionVO;

@Repository("PermissionMapper")
public interface PermissionMapper {

	ArrayList<HashMap<String, Object>> siteListMenu1(HashMap<String, Object> hashmapParam);

	void permissionDelete(PermissionVO permissionVO);

	int permissionInsert(PermissionVO input_permissionVO);

}
