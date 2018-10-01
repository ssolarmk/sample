package com.owra.web.example.domain.common;

import java.io.Serializable;

public class ReturnDataVO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1533024539259264563L;
	
	private String resultMsg;
	private String resultCode;
	private String returnVal;
	private Object data = null;
	
	public String getResultMsg() {
		return resultMsg;
	}
	public void setResultMsg(String resultMsg) {
		this.resultMsg = resultMsg;
	}
	public String getResultCode() {
		return resultCode;
	}
	public void setResultCode(String resultCode) {
		this.resultCode = resultCode;
	}
	/**
	 * @return the returnVal
	 */
	public String getReturnVal() {
		return returnVal;
	}
	/**
	 * @param returnVal the returnVal to set
	 */
	public void setReturnVal(String returnVal) {
		this.returnVal = returnVal;
	}
	/**
	 * @return the data
	 */
	public Object getData() {
		return data;
	}
	/**
	 * @param data the data to set
	 */
	public void setData(Object data) {
		this.data = data;
	}

}
