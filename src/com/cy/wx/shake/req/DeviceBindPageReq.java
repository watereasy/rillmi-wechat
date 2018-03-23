package com.cy.wx.shake.req;

import java.util.List;

import com.cy.wx.shake.DeviceIdentifier;

/**
 * 配置设备与页面的关联关系(请求)
 * @author zhangjianhui
 *
 */
public class DeviceBindPageReq {

	private DeviceIdentifier device_identifier;
	/** 待关联的页面列表  */
	private List<Integer> page_ids;

	public DeviceIdentifier getDevice_identifier() {
		return device_identifier;
	}

	public void setDevice_identifier(DeviceIdentifier device_identifier) {
		this.device_identifier = device_identifier;
	}

	public List<Integer> getPage_ids() {
		return page_ids;
	}

	public void setPage_ids(List<Integer> page_ids) {
		this.page_ids = page_ids;
	}
	
}
