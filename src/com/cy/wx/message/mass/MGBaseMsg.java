package com.cy.wx.message.mass;

/**
 * 分组群发消息的基类
 * @author zhangjianhui
 *
 */
public class MGBaseMsg {
	// 用于设定图文消息的接收者
	protected MGFilter filter;
	// 群发的消息类型,图文:mpnews/文本:text/语音:voice/音乐:music/图片:image/视频:video 
	protected String msgtype;

	public MGFilter getFilter() {
		return filter;
	}

	public void setFilter(MGFilter filter) {
		this.filter = filter;
	}

	public String getMsgtype() {
		return msgtype;
	}

	public void setMsgtype(String msgtype) {
		this.msgtype = msgtype;
	}

}
