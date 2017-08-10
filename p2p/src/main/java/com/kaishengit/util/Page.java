package com.kaishengit.util;

import java.util.List;


public class Page<T> {
	//总页数
	private int pageTotal;
	
	//当前页码
	private int pageNo;
	
	//每页显示数量
	private int pageSize = 3;
	
	//每页的数据
	private List<T> items;
	
	//起始行号
	private int start;
	
	//总数据数量
	private int totals;
	

	public Page(int pageNo, int totals){
		int pageTotal = totals / pageSize;
		if(totals % pageSize != 0) {
			pageTotal ++;
		}
		if(pageNo < 1) {
			pageNo = 1;
		}
		//防止数据总量为0，那么pagetotal就为0
		if(pageTotal < 1) {
			pageTotal = 1;
		}
		//pageTotal = (pageTotal == 0 ? 1: pageTotal);
		//当前页码不能超过总页码
		if(pageNo > pageTotal) {
			pageNo = pageTotal;
		}
		
		int start = (pageNo - 1) * pageSize;
		
		this.start = start;
		this.pageNo = pageNo;
		this.pageTotal = pageTotal;
		this.totals = totals;
	}
	
	
	
	public int getStart() {
		return start;
	}



	public void setStart(int start) {
		this.start = start;
	}



	public int getPageTotal() {
		return pageTotal;
	}
	
	public void setPageTotal(int pageTotal) {
		this.pageTotal = pageTotal;
	}

	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public List<T> getItems() {
		return items;
	}

	public void setItems(List<T> items) {
		this.items = items;
	}
	
}
