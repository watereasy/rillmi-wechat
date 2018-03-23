package com.cy.wx.model;

/**
 * 群发结果
 * @author zhangjianhui
 *
 */
public class MassResult {
	// 错误码 
	private int errCode;
	// 错误信息 
	private String errMsg;
	// 消息ID 
	private int msgId;
	// 消息发送后的状态，SEND_SUCCESS表示发送成功
	private String msgStatus;

	public int getErrCode() {
		return errCode;
	}

	public void setErrCode(int errCode) {
		this.errCode = errCode;
	}

	public String getErrMsg() {
		return errMsg;
	}

	public void setErrMsg(String errMsg) {
		this.errMsg = errMsg;
	}

	public int getMsgId() {
		return msgId;
	}

	public void setMsgId(int msgId) {
		this.msgId = msgId;
	}

	public String getMsgStatus() {
		return msgStatus;
	}

	public void setMsgStatus(String msgStatus) {
		this.msgStatus = msgStatus;
	}
	
}
