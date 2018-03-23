package com.cy.wx.shake.req;

import java.util.List;

/**
 * 查询页面列表(请求)
 * @author zhangjianhui
 *
 */
public class PageSearchReq {
	
	private String type;
	
	private List<Integer> page_ids;
	
	private int begin;
	
	private int count;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public List<Integer> getPage_ids() {
		return page_ids;
	}

	public void setPage_ids(List<Integer> page_ids) {
		this.page_ids = page_ids;
	}

	public int getBegin() {
		return begin;
	}

	public void setBegin(int begin) {
		this.begin = begin;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

}
