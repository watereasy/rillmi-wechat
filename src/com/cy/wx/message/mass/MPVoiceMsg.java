package com.cy.wx.message.mass;

/**
 * OpenID发送语音
 * @author zhangjianhui
 *
 */
public class MPVoiceMsg extends MPBaseMsg {

	private MVoice voice;

	public MVoice getVoice() {
		return voice;
	}

	public void setVoice(MVoice voice) {
		this.voice = voice;
	}
	
}
