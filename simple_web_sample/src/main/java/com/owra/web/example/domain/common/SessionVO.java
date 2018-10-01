package com.owra.web.example.domain.common;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.type.Alias;

@Alias("sessionVo")
public class SessionVO implements Serializable{
	
	private static final long serialVersionUID = 6301450078844122257L;
	private String user_id;         
	private String user_name;
	private String mbr_no; 
	private String group_code; 
	private List<String> user_group; 
	private String user_grp_cd;
	private String site_gb_cd;
	private List<HashMap<String, Object>>  menu;


	public String getUser_grp_cd() {
		return user_grp_cd;
	}
	public void setUser_grp_cd(String user_grp_cd) {
		this.user_grp_cd = user_grp_cd;
	}
	
	/**
	 * @return the user_id
	 */
	public String getUser_id() {
		return user_id;
	}
	/**
	 * @param user_id the user_id to set
	 */
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	/**
	 * @return the user_name
	 */
	public String getUser_name() {
		return user_name;
	}
	/**
	 * @param user_name the user_name to set
	 */
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	/**
	 * @return the mbr_no
	 */
	public String getMbr_no() {
		return mbr_no;
	}
	/**
	 * @param mbr_no the mbr_no to set
	 */
	public void setMbr_no(String mbr_no) {
		this.mbr_no = mbr_no;
	}
	/**
	 * @return the group_code
	 */
	public String getGroup_code() {
		return group_code;
	}
	/**
	 * @param group_code the group_code to set
	 */
	public void setGroup_code(String group_code) {
		this.group_code = group_code;
	}
	/**
	 * @return the menu
	 */
	public List<HashMap<String, Object>> getMenu() {
		return menu;
	}
	/**
	 * @param menu the menu to set
	 */
	public void setMenu(List<HashMap<String, Object>> menu) {
		this.menu = menu;
	}
	public String getSite_gb_cd() {
		return site_gb_cd;
	}
	public void setSite_gb_cd(String site_gb_cd) {
		this.site_gb_cd = site_gb_cd;
	}
	public List<String> getUser_group() {
		return user_group;
	}
	public void setUser_group(List<String> user_group) {
		this.user_group = user_group;
	}
}
