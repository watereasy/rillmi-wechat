package com.cy.wx.message.mass;

/**
 * 分组群发文本
 * @author zhangjianhui
 *
 */
public class MGTextMsg extends MGBaseMsg {

	private MText text;

	public MText getText() {
		return text;
	}

	public void setText(MText text) {
		this.text = text;
	}

}
