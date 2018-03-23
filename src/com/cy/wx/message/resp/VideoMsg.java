package com.cy.wx.message.resp;

/**
 * 回复视频消息
 *
 */
public class VideoMsg extends BaseMsg {
	
	private Video Video;

	public Video getVideo() {
		return Video;
	}

	public void setVideo(Video video) {
		Video = video;
	}
	
}
