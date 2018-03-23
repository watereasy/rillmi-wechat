package com.cy.wx.message.mass;

/**
 * 分组发送卡券
 * @author zhangjianhui
 *
 */
public class MGCardMsg extends MGBaseMsg {

	private MCard wxcard;

	public MCard getWxcard() {
		return wxcard;
	}

	public void setWxcard(MCard wxcard) {
		this.wxcard = wxcard;
	}
	
}
