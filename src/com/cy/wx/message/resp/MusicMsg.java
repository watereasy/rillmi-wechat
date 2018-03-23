package com.cy.wx.message.resp;

/**
 * 回复音乐消息
 *
 */
public class MusicMsg extends BaseMsg {
	
	private Music Music;

	public Music getMusic() {
		return Music;
	}

	public void setMusic(Music music) {
		Music = music;
	}
	
}
