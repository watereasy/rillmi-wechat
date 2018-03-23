package com.cy.wx.model;

/**
 * 用户标签
 * @author zhangjianhui
 *
 */
public class UserTag {

	// 分组id，由微信分配 
	private int id;
	// 分组名字，UTF8编码
	private String name;
	// 分组内用户数量 
	private String count;
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCount() {
		return count;
	}

	public void setCount(String count) {
		this.count = count;
	}
}
