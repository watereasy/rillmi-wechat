package com.cy.wx.shake;

public class Page {

	private int page_id;
	/** 不超过6个汉字或12个英文字母 */
	private String title;
	/** 不超过7个汉字或14个英文字母 */
	private String description;
	/** 跳转链接 */
	private String page_url;
	/** 不超过15个汉字或30个英文字母 */
	private String comment;
	/** 在摇一摇页面展示的图片。图片需先上传至微信侧服务器，用“素材管理-上传图片素材”接口上传图片，返回的图片URL再配置在此处 */
	private String icon_url;

	public int getPage_id() {
		return page_id;
	}

	public void setPage_id(int page_id) {
		this.page_id = page_id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPage_url() {
		return page_url;
	}

	public void setPage_url(String page_url) {
		this.page_url = page_url;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getIcon_url() {
		return icon_url;
	}

	public void setIcon_url(String icon_url) {
		this.icon_url = icon_url;
	}
	
}
