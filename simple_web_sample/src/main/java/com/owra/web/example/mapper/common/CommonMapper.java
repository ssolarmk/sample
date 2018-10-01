package com.owra.web.example.mapper.common;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

@Repository("CommonMapper")
public interface CommonMapper {

	List<HashMap<String, Object>> getCodeList(HashMap<String, String> hashmapParam);

	Integer chkAuthRetrieve(HashMap<String, Object> authMap);

}
