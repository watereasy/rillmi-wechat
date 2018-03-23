package com.cy.wx.model;

import java.util.List;

/**
 * 用户标签中的成员
 * @author zhangjianhui
 *
 */
public class UserTagMember {
	
	public UserTagMember() {
		super();
	}

	public UserTagMember(int tagid, List<String> openid_list) {
		super();
		this.tagid = tagid;
		this.openid_list = openid_list;
	}

	/** 标签ID */
	private int tagid;
	/** 粉丝列表 */
	private List<String> openid_list;

	public int getTagid() {
		return tagid;
	}

	public void setTagid(int tagid) {
		this.tagid = tagid;
	}

	public List<String> getOpenid_list() {
		return openid_list;
	}

	public void setOpenid_list(List<String> openid_list) {
		this.openid_list = openid_list;
	}

}
