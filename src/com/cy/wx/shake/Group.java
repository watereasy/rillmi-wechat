package com.cy.wx.shake;

/**
 * 设备分组
 * @author zhangjianhui
 *
 */
public class Group {
	
	private int group_id;
	/** 分组名称，不超过100汉字或200个英文字母  */
	private String group_name;

	public int getGroup_id() {
		return group_id;
	}

	public void setGroup_id(int group_id) {
		this.group_id = group_id;
	}

	public String getGroup_name() {
		return group_name;
	}

	public void setGroup_name(String group_name) {
		this.group_name = group_name;
	}

}
