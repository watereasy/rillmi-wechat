package com.cy.wx.message.resp;

/**
 * 回复图片消息
 *
 */
public class ImageMsg extends BaseMsg {

	private Image Image;

	public Image getImage() {
		return Image;
	}

	public void setImage(Image image) {
		Image = image;
	}
	
}
