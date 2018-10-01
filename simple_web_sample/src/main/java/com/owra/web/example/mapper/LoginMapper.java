package com.owra.web.example.mapper;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.owra.web.example.domain.common.SessionVO;

@Repository("LoginMapper")
public interface LoginMapper {

	SessionVO getUserRetrieve(HashMap<String, String> param);

	List<String> getUserGrpRetrieve(HashMap<String, String> param);

	void createloginHist(HashMap<String, Object> loginHist);

	List<HashMap<String, Object>> getMenuRetrieve(SessionVO loginUserVo);

	void lastLoginUpdate(SessionVO loginUserVo);

}
