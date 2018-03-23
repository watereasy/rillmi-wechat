package com.cy.wx.menu;

/**
 * click类型的按钮
 *
 */
public class ClickButton extends Button {
	// click:点击
	private String type;
	
	private String key;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

}
