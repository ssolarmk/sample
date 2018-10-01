package com.owra.web.example.domain.manage;

import java.io.Serializable;

import javax.validation.constraints.*;

import org.apache.ibatis.type.Alias;

@Alias("menuVo")
public class MenuVO implements Serializable{
	
	private static final long serialVersionUID = -5859886073472413513L;
	
	
	/*@NotNull(message="상위메뉴를 선택해주세요")
	private String menu;*/
	@NotNull(message="메뉴ID를 입력해주세요")
	private String menu_cd;
	@NotNull(message="메뉴명을 입력해주세요")
	private String menu_nm;
	private String user_id;
	@NotNull(message="메뉴유형을 선택해주세요")
	private String menu_tp;
	@NotNull(message="URL를 입력해주세요")
	private String menu_url;
	@NotNull(message="정렬순서를 입력해주세요")
	private String menu_ord;
	private String site_gb_cd;
	private String menu_prnt_cd;
	private String upt_dttm;
	private String ent_dttm;
	private String use_yn;
	private String view_yn;
	private String upt_user_id;
	private String old_menu_cd;
	private String menu_use_yn;
	
	
	public String getMenu_use_yn() {
		return menu_use_yn;
	}
	public void setMenu_use_yn(String menu_use_yn) {
		this.menu_use_yn = menu_use_yn;
	}
	public String getOld_menu_cd() {
		return old_menu_cd;
	}
	public void setOld_menu_cd(String old_menu_cd) {
		this.old_menu_cd = old_menu_cd;
	}
	/*public String getMenu() {
		return menu;
	}
	public void setMenu(String menu) {
		this.menu = menu;
	}*/
	public String getMenu_cd() {
		return menu_cd;
	}
	public void setMenu_cd(String menu_cd) {
		this.menu_cd = menu_cd;
	}
	public String getMenu_nm() {
		return menu_nm;
	}
	public void setMenu_nm(String menu_nm) {
		this.menu_nm = menu_nm;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getMenu_tp() {
		return menu_tp;
	}
	public void setMenu_tp(String menu_tp) {
		this.menu_tp = menu_tp;
	}
	public String getMenu_url() {
		return menu_url;
	}
	public void setMenu_url(String menu_url) {
		this.menu_url = menu_url;
	}
	public String getMenu_ord() {
		return menu_ord;
	}
	public void setMenu_ord(String menu_ord) {
		this.menu_ord = menu_ord;
	}
	public String getSite_gb_cd() {
		return site_gb_cd;
	}
	public void setSite_gb_cd(String site_gb_cd) {
		this.site_gb_cd = site_gb_cd;
	}
	public String getMenu_prnt_cd() {
		return menu_prnt_cd;
	}
	public void setMenu_prnt_cd(String menu_prnt_cd) {
		this.menu_prnt_cd = menu_prnt_cd;
	}
	public String getUpt_dttm() {
		return upt_dttm;
	}
	public void setUpt_dttm(String upt_dttm) {
		this.upt_dttm = upt_dttm;
	}
	public String getEnt_dttm() {
		return ent_dttm;
	}
	public void setEnt_dttm(String ent_dttm) {
		this.ent_dttm = ent_dttm;
	}
	public String getUse_yn() {
		return use_yn;
	}
	public void setUse_yn(String use_yn) {
		this.use_yn = use_yn;
	}
	public String getView_yn() {
		return view_yn;
	}
	public void setView_yn(String view_yn) {
		this.view_yn = view_yn;
	}
	public String getUpt_user_id() {
		return upt_user_id;
	}
	public void setUpt_user_id(String upt_user_id) {
		this.upt_user_id = upt_user_id;
	}
	
	
	
}
