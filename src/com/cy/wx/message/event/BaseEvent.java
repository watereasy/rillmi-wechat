package com.cy.wx.message.event;

/**
 * 微信事件父类
 *
 */
public class BaseEvent {
	// 开发者微信号
	protected String ToUserName;
	// 发送方帐号（一个OpenID）
	protected String FromUserName;
	// 消息创建时间 （整型）
	protected long CreateTime;
	// 消息类型，event
	protected String MsgType;
	// 事件类型，subscribe(订阅)、unsubscribe(取消订阅)
	protected String Event;

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

	public String getEvent() {
		return Event;
	}

	public void setEvent(String event) {
		Event = event;
	}
	
}
