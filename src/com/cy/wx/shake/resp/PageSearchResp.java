package com.cy.wx.shake.resp;

import java.util.List;

import com.cy.wx.shake.Page;

/**
 * 查询页面列表(响应)
 * @author zhangjianhui
 *
 */
public class PageSearchResp {

	private List<Page> pages;
	
	private int total_count;

	public List<Page> getPages() {
		return pages;
	}

	public void setPages(List<Page> pages) {
		this.pages = pages;
	}

	public int getTotal_count() {
		return total_count;
	}

	public void setTotal_count(int total_count) {
		this.total_count = total_count;
	}
	
}
