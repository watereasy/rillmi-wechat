package com.cy.wx.message.event;

/**
 * 模板消息发送结果 具体请看"公众平台开发文档=>发送消息=>模板消息接口=>事件推送"
 * TODO 微信会回调，请在业务层自己实现
 * @author zhangjianhui
 * 
 */
public class TemplateFinishEvent extends BaseEvent {
	// 消息id
	private int MsgID;
	// 发送状态：成功/success|拒收/failed:user block|失败/failed: system failed
	private String Status;
	
	public int getMsgID() {
		return MsgID;
	}
	
	public void setMsgID(int msgID) {
		MsgID = msgID;
	}
	public 
	String getStatus() {
		return Status;
	}
	
	public void setStatus(String status) {
		Status = status;
	} 

}
