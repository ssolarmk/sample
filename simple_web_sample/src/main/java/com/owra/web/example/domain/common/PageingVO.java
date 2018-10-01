package com.owra.web.example.domain.common;

import java.io.Serializable;

public class PageingVO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7227522246573477467L;
	
	/** 현재페이지 */
    private int page = 1;
    
    /** 페이지갯수 */
    private int total = 0;
    
    /** 전체 갯수 */
    private int records = 0;
    
    /** 페이지 크기 **/
    private int rows = 20;

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public int getRecords() {
		return records;
	}

	public void setRecords(int records) {
		this.records = records;
	}

	public int getRows() {
		return rows;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}
}
