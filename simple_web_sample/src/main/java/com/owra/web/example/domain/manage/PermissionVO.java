package com.owra.web.example.domain.manage;

import java.io.Serializable;
import java.util.ArrayList;

import javax.validation.constraints.*;

import org.apache.ibatis.type.Alias;
import org.hibernate.validator.constraints.Length;

@Alias("permissionVO")
public class PermissionVO implements Serializable{
	
	private static final long serialVersionUID = -5859886073472413513L;

	@NotNull(message="사이트구분을 선택해주세요.")
	@Length(min=1, max=50, message="사이트구분을 선택해주세요.")
	private String site_gb_cd;
	@NotNull(message="사용자그룹코드를 선택해주세요.")
	@Length(min=1, max=50, message="사용자그룹코드를 선택해주세요.")
	private String auth_grp_cd;
	@NotNull(message="메뉴코드를 선택해주세요.")
	@Length(min=1, max=10000, message="메뉴코드를 선택해주세요.")
	private String menu_cd;
	private String user_id;
	
	public String getSite_gb_cd() {
		return site_gb_cd;
	}
	public void setSite_gb_cd(String site_gb_cd) {
		this.site_gb_cd = site_gb_cd;
	}
	public String getAuth_grp_cd() {
		return auth_grp_cd;
	}
	public void setAuth_grp_cd(String auth_grp_cd) {
		this.auth_grp_cd = auth_grp_cd;
	}
	public String getMenu_cd() {
		return menu_cd;
	}
	public void setMenu_cd(String menu_cd) {
		this.menu_cd = menu_cd;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
}
