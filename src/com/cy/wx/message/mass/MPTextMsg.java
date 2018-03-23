package com.cy.wx.message.mass;

/**
 * OpenID群发文本
 * @author zhangjianhui
 *
 */
public class MPTextMsg extends MPBaseMsg {

	private MText text;

	public MText getText() {
		return text;
	}

	public void setText(MText text) {
		this.text = text;
	}

}
