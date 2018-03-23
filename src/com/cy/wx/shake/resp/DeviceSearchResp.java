package com.cy.wx.shake.resp;

import java.util.List;

import com.cy.wx.shake.Device;

/**
 * 查询设备列表(响应)
 * @author zhangjianhui
 *
 */
public class DeviceSearchResp {

	private List<Device> devices;
	
	private int total_count;

	public List<Device> getDevices() {
		return devices;
	}

	public void setDevices(List<Device> devices) {
		this.devices = devices;
	}

	public int getTotal_count() {
		return total_count;
	}

	public void setTotal_count(int total_count) {
		this.total_count = total_count;
	}
	
}
