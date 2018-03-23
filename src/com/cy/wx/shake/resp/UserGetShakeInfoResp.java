package com.cy.wx.shake.resp;

import com.cy.wx.shake.BeaconInfo;

/**
 * 获取摇周边的设备及用户信息(请求)
 * @author zhangjianhui
 *
 */
public class UserGetShakeInfoResp {
	
	private int page_id;
	
	private BeaconInfo beacon_info;
	
	private String openid;
	
	private int poi_id;
	
	public int getPage_id() {
		return page_id;
	}

	public void setPage_id(int page_id) {
		this.page_id = page_id;
	}

	public BeaconInfo getBeacon_info() {
		return beacon_info;
	}

	public void setBeacon_info(BeaconInfo beacon_info) {
		this.beacon_info = beacon_info;
	}

	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}

	public int getPoi_id() {
		return poi_id;
	}

	public void setPoi_id(int poi_id) {
		this.poi_id = poi_id;
	}

}
