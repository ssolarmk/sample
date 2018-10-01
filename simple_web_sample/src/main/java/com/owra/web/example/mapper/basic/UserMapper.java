package com.owra.web.example.mapper.basic;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

@Repository("UserMapper")
public interface UserMapper {

	List<HashMap<String, Object>> getUserListRetrieve(HashMap<String, Object> hashmapParam);

	int getUserListRetrieveCnt(HashMap<String, Object> hashmapParam);

	Object getAuthGrpCdRetrieve(HashMap<String, Object> hashmapParam);

}
