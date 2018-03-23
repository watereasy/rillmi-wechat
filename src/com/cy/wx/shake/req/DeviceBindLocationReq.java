package com.cy.wx.shake.req;

import com.cy.wx.shake.DeviceIdentifier;

/**
 * 编辑设备信息(请求)
 * @author zhangjianhui
 *
 */
public class DeviceBindLocationReq {
	
	private DeviceIdentifier device_identifier;
	/** 设备的备注信息，不超过15个汉字或30个英文字母  */
	private String comment;

	public DeviceIdentifier getDevice_identifier() {
		return device_identifier;
	}

	public void setDevice_identifier(DeviceIdentifier device_identifier) {
		this.device_identifier = device_identifier;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

}
