package com.cy.wx.message.resp;

/**
 * 回复语音消息
 *
 */
public class VoiceMsg extends BaseMsg {

	private Voice Voice;

	public Voice getVoice() {
		return Voice;
	}

	public void setVoice(Voice voice) {
		Voice = voice;
	}
	
}
