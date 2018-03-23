package com.cy.wx.menu;

/**
 * view类型的按钮
 *
 */
public class ViewButton extends Button{
	// view:跳转URL
	private String type;
	// 必须有效
	private String url;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

}
