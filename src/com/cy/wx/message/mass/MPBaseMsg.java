package com.cy.wx.message.mass;

import java.util.List;

/**
 * OpenID群发消息的基类
 * @author zhangjianhui
 *
 */
public class MPBaseMsg {
	// OpenID列表,最多10000个
	protected List<String> touser;
	// 群发的消息类型,图文:mpnews/文本:text/语音:voice/音乐:music/图片:image/视频:video 
	protected String msgtype;
	
	public List<String> getTouser() {
		return touser;
	}
	
	public void setTouser(List<String> touser) {
		this.touser = touser;
	}
	
	public String getMsgtype() {
		return msgtype;
	}
	
	public void setMsgtype(String msgtype) {
		this.msgtype = msgtype;
	}
	
}
