package com.cy.wx.message.mass;

/**
 * 用于设定图文消息的接收者 
 * @author zhangjianhui
 *
 */
public class MGFilter {
	// 用于设定是否向全部用户发送,true:所有/false:指定group_id
	private boolean is_to_all;
	// 群发到的分组的group_id,若is_to_all值为true，可不填写group_id
	private String group_id;

	public boolean isIs_to_all() {
		return is_to_all;
	}

	public void setIs_to_all(boolean is_to_all) {
		this.is_to_all = is_to_all;
	}

	public String getGroup_id() {
		return group_id;
	}

	public void setGroup_id(String group_id) {
		this.group_id = group_id;
	}

}
