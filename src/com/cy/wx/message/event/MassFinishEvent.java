package com.cy.wx.message.event;

/**
 * 事件推送群发结果 具体请看"公众平台开发文档=>发送消息=>高级群发接口=>事件推送群发结果"
 * TODO 微信会回调，请在业务层自己实现
 * @author zhangjianhui
 * 
 */
public class MassFinishEvent extends BaseEvent {
	// 群发的消息ID 
	private int MsgID;
	// 群发的结果
	private String Status;
	// group_id下粉丝数；或者openid_list中的粉丝数 
	private int TotalCount;
	// 过滤
	private int FilterCount;
	// 发送成功的粉丝数
	private int SentCount;
	// 发送失败的粉丝数 
	private int ErrorCount;

	public int getMsgID() {
		return MsgID;
	}

	public void setMsgID(int msgID) {
		MsgID = msgID;
	}

	public String getStatus() {
		return Status;
	}

	public void setStatus(String status) {
		Status = status;
	}

	public int getTotalCount() {
		return TotalCount;
	}

	public void setTotalCount(int totalCount) {
		TotalCount = totalCount;
	}

	public int getFilterCount() {
		return FilterCount;
	}

	public void setFilterCount(int filterCount) {
		FilterCount = filterCount;
	}

	public int getSentCount() {
		return SentCount;
	}

	public void setSentCount(int sentCount) {
		SentCount = sentCount;
	}

	public int getErrorCount() {
		return ErrorCount;
	}

	public void setErrorCount(int errorCount) {
		ErrorCount = errorCount;
	}

}
