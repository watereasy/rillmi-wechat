package com.cy.wx.shake.req;

import com.cy.wx.shake.DeviceIdentifier;

/**
 * 以设备为维度的数据统计接口(请求)
 * @author zhangjianhui
 *
 */
public class StatisticsDeviceReq {
	
	private DeviceIdentifier device_identifier;
	
	private long begin_date;
	
	private long end_date;

	public DeviceIdentifier getDevice_identifier() {
		return device_identifier;
	}

	public void setDevice_identifier(DeviceIdentifier device_identifier) {
		this.device_identifier = device_identifier;
	}

	public long getBegin_date() {
		return begin_date;
	}

	public void setBegin_date(long begin_date) {
		this.begin_date = begin_date;
	}

	public long getEnd_date() {
		return end_date;
	}

	public void setEnd_date(long end_date) {
		this.end_date = end_date;
	}

}
