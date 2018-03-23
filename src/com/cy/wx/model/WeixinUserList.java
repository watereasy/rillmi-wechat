package com.cy.wx.model;

import java.util.List;

/**
 * 公众号关注用户列表
 *
 */
public class WeixinUserList {
	// 关注该公众账号的总用户数
	private long total;
	// 拉取的OPENID个数，最大值为10000 
	private int count;
	// 列表数据，OPENID的列表 
	private List<String> openIdList;
	// 拉取列表的后一个用户的OPENID 
	private String nextOpenId;

	public long getTotal() {
		return total;
	}

	public void setTotal(long total) {
		this.total = total;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public List<String> getOpenIdList() {
		return openIdList;
	}

	public void setOpenIdList(List<String> openIdList) {
		this.openIdList = openIdList;
	}

	public String getNextOpenId() {
		return nextOpenId;
	}

	public void setNextOpenId(String nextOpenId) {
		this.nextOpenId = nextOpenId;
	}

}
