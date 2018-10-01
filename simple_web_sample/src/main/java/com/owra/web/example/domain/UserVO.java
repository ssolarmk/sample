package com.owra.web.example.domain;

import java.io.Serializable;
import java.util.HashMap;

import org.apache.ibatis.type.Alias;

@Alias("user")
public class UserVO implements Serializable{
	
	private static final long serialVersionUID = 6528577352673373333L;
	private String user_id;         
	private String user_name;       
	private String user_password;   
	private String user_pw;   
	private String mbr_no;          
	private String biz_no;         
	private String group_code;      
	private String reg_id;          
	private String reg_date;        
	private String last_login_date; 
	private String last_login_time; 
	private String last_login_ip;   
	private String chg_date;        
	private String chg_time;        
	private String chg_id;          
	private String user_group;      
	private String tran_job;        
	private String edi_job;         
	private String bill_job;        
	private String etc_job;         
	private String mis_job;         
	private String admin_yn;        
	private String user_status;     
	private String enc_passwd;      
	private String user_grp_cd;
	private String site_gb_cd; 
	private String cnct_ip;
	
	public String getUser_grp_cd() {
		return user_grp_cd;
	}
	public void setUser_grp_cd(String user_grp_cd) {
		this.user_grp_cd = user_grp_cd;
	}
	private HashMap<String, Object> menu;
	
	private int start = 0;
	private int end = 10;
	
	private String sidx;
	private String sord;
	private String search_value; // 검색어 값
	private String search_index; // 검색어 선택 값

	
	public String getUser_pw() {
		return user_pw;
	}
	public void setUser_pw(String user_pw) {
		this.user_pw = user_pw;
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
	 * @return the user_password
	 */
	public String getUser_password() {
		return user_password;
	}
	/**
	 * @param user_password the user_password to set
	 */
	public void setUser_password(String user_password) {
		this.user_password = user_password;
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
	 * @return the biz_no
	 */
	public String getBiz_no() {
		return biz_no;
	}
	/**
	 * @param biz_no the biz_no to set
	 */
	public void setBiz_no(String biz_no) {
		this.biz_no = biz_no;
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
	 * @return the reg_id
	 */
	public String getReg_id() {
		return reg_id;
	}
	/**
	 * @param reg_id the reg_id to set
	 */
	public void setReg_id(String reg_id) {
		this.reg_id = reg_id;
	}
	/**
	 * @return the reg_date
	 */
	public String getReg_date() {
		return reg_date;
	}
	/**
	 * @param reg_date the reg_date to set
	 */
	public void setReg_date(String reg_date) {
		this.reg_date = reg_date;
	}
	/**
	 * @return the last_login_date
	 */
	public String getLast_login_date() {
		return last_login_date;
	}
	/**
	 * @param last_login_date the last_login_date to set
	 */
	public void setLast_login_date(String last_login_date) {
		this.last_login_date = last_login_date;
	}
	/**
	 * @return the last_login_time
	 */
	public String getLast_login_time() {
		return last_login_time;
	}
	/**
	 * @param last_login_time the last_login_time to set
	 */
	public void setLast_login_time(String last_login_time) {
		this.last_login_time = last_login_time;
	}
	/**
	 * @return the last_login_ip
	 */
	public String getLast_login_ip() {
		return last_login_ip;
	}
	/**
	 * @param last_login_ip the last_login_ip to set
	 */
	public void setLast_login_ip(String last_login_ip) {
		this.last_login_ip = last_login_ip;
	}
	/**
	 * @return the chg_date
	 */
	public String getChg_date() {
		return chg_date;
	}
	/**
	 * @param chg_date the chg_date to set
	 */
	public void setChg_date(String chg_date) {
		this.chg_date = chg_date;
	}
	/**
	 * @return the chg_time
	 */
	public String getChg_time() {
		return chg_time;
	}
	/**
	 * @param chg_time the chg_time to set
	 */
	public void setChg_time(String chg_time) {
		this.chg_time = chg_time;
	}
	/**
	 * @return the chg_id
	 */
	public String getChg_id() {
		return chg_id;
	}
	/**
	 * @param chg_id the chg_id to set
	 */
	public void setChg_id(String chg_id) {
		this.chg_id = chg_id;
	}
	/**
	 * @return the user_group
	 */
	public String getUser_group() {
		return user_group;
	}
	/**
	 * @param user_group the user_group to set
	 */
	public void setUser_group(String user_group) {
		this.user_group = user_group;
	}
	/**
	 * @return the tran_job
	 */
	public String getTran_job() {
		return tran_job;
	}
	/**
	 * @param tran_job the tran_job to set
	 */
	public void setTran_job(String tran_job) {
		this.tran_job = tran_job;
	}
	/**
	 * @return the edi_job
	 */
	public String getEdi_job() {
		return edi_job;
	}
	/**
	 * @param edi_job the edi_job to set
	 */
	public void setEdi_job(String edi_job) {
		this.edi_job = edi_job;
	}
	/**
	 * @return the bill_job
	 */
	public String getBill_job() {
		return bill_job;
	}
	/**
	 * @param bill_job the bill_job to set
	 */
	public void setBill_job(String bill_job) {
		this.bill_job = bill_job;
	}
	/**
	 * @return the etc_job
	 */
	public String getEtc_job() {
		return etc_job;
	}
	/**
	 * @param etc_job the etc_job to set
	 */
	public void setEtc_job(String etc_job) {
		this.etc_job = etc_job;
	}
	/**
	 * @return the mis_job
	 */
	public String getMis_job() {
		return mis_job;
	}
	/**
	 * @param mis_job the mis_job to set
	 */
	public void setMis_job(String mis_job) {
		this.mis_job = mis_job;
	}
	/**
	 * @return the admin_yn
	 */
	public String getAdmin_yn() {
		return admin_yn;
	}
	/**
	 * @param admin_yn the admin_yn to set
	 */
	public void setAdmin_yn(String admin_yn) {
		this.admin_yn = admin_yn;
	}
	/**
	 * @return the user_status
	 */
	public String getUser_status() {
		return user_status;
	}
	/**
	 * @param user_status the user_status to set
	 */
	public void setUser_status(String user_status) {
		this.user_status = user_status;
	}
	/**
	 * @return the enc_passwd
	 */
	public String getEnc_passwd() {
		return enc_passwd;
	}
	/**
	 * @param enc_passwd the enc_passwd to set
	 */
	public void setEnc_passwd(String enc_passwd) {
		this.enc_passwd = enc_passwd;
	}
	/**
	 * @return the start
	 */
	public int getStart() {
		return start;
	}
	/**
	 * @param start the start to set
	 */
	public void setStart(int start) {
		this.start = start;
	}
	/**
	 * @return the end
	 */
	public int getEnd() {
		return end;
	}
	/**
	 * @param end the end to set
	 */
	public void setEnd(int end) {
		this.end = end;
	}
	/**
	 * @return the sidx
	 */
	public String getSidx() {
		return sidx;
	}
	/**
	 * @param sidx the sidx to set
	 */
	public void setSidx(String sidx) {
		this.sidx = sidx;
	}
	/**
	 * @return the sord
	 */
	public String getSord() {
		return sord;
	}
	/**
	 * @param sord the sord to set
	 */
	public void setSord(String sord) {
		this.sord = sord;
	}
	/**
	 * @return the search_value
	 */
	public String getSearch_value() {
		return search_value;
	}
	/**
	 * @param search_value the search_value to set
	 */
	public void setSearch_value(String search_value) {
		this.search_value = search_value;
	}
	/**
	 * @return the search_index
	 */
	public String getSearch_index() {
		return search_index;
	}
	/**
	 * @param search_index the search_index to set
	 */
	public void setSearch_index(String search_index) {
		this.search_index = search_index;
	}
	/**
	 * @return the menu
	 */
	public HashMap<String, Object> getMenu() {
		return menu;
	}
	/**
	 * @param menu the menu to set
	 */
	public void setMenu(HashMap<String, Object> menu) {
		this.menu = menu;
	}
	public String getSite_gb_cd() {
		return site_gb_cd;
	}
	public void setSite_gb_cd(String site_gb_cd) {
		this.site_gb_cd = site_gb_cd;
	}
	/**
	 * @return the cnct_ip
	 */
	public String getCnct_ip() {
		return cnct_ip;
	}
	/**
	 * @param cnct_ip the cnct_ip to set
	 */
	public void setCnct_ip(String cnct_ip) {
		this.cnct_ip = cnct_ip;
	}
}
