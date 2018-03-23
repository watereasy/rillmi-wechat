package com.cy.wx.message.mass;

/**
 * OpenID发送卡券
 * @author zhangjianhui
 *
 */
public class MPCardMsg extends MPBaseMsg {

	private MCard wxcard;

	public MCard getWxcard() {
		return wxcard;
	}

	public void setWxcard(MCard wxcard) {
		this.wxcard = wxcard;
	}
	
}
