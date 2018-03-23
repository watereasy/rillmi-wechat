package com.cy.wx.shake.req;

import com.cy.wx.shake.DeviceIdentifier;

/**
 * 配置设备与门店的关联关系(请求)
 * @author zhangjianhui
 *
 */
public class DeviceApplyUpdateReq {
	
	private DeviceIdentifier device_identifier;
	
	private int poi_id;

	public DeviceIdentifier getDevice_identifier() {
		return device_identifier;
	}

	public void setDevice_identifier(DeviceIdentifier device_identifier) {
		this.device_identifier = device_identifier;
	}

	public int getPoi_id() {
		return poi_id;
	}

	public void setPoi_id(int poi_id) {
		this.poi_id = poi_id;
	}

}
