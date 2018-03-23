package com.cy.wx.message.req;

/**
 * 微信请求消息父类
 *
 */
public class BaseMsg {
	// 开发者微信号
	protected String ToUserName;
	// 发送方帐号（一个OpenID）
	protected String FromUserName;
	// 消息创建时间 （整型）
	protected long CreateTime;
	// 消息类型
	protected String MsgType;
	// 消息id，64位整型
	protected long MsgId;

	public String getToUserName() {
		return ToUserName;
	}

	public void setToUserName(String toUserName) {
		ToUserName = toUserName;
	}

	public String getFromUserName() {
		return FromUserName;
	}

	public void setFromUserName(String fromUserName) {
		FromUserName = fromUserName;
	}

	public long getCreateTime() {
		return CreateTime;
	}

	public void setCreateTime(long createTime) {
		CreateTime = createTime;
	}

	public String getMsgType() {
		return MsgType;
	}

	public void setMsgType(String msgType) {
		MsgType = msgType;
	}

	public long getMsgId() {
		return MsgId;
	}

	public void setMsgId(long msgId) {
		MsgId = msgId;
	}

}
