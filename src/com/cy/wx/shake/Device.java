package com.cy.wx.shake;

public class Device {
	
	private int device_id;
	
	private String uuid;
	
	private int major;
	
	private int minor;
	
	private int status;
	/** 设备最近一次被摇到的日期（最早只能获取前一天的数据）；新申请的设备该字段值为0  */
	private long last_active_time;
	
	private int poi_id;
	/** 摇周边页面唯一ID  */
	private int page_id;

	private String common;

	public int getDevice_id() {
		return device_id;
	}

	public void setDevice_id(int device_id) {
		this.device_id = device_id;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public int getMajor() {
		return major;
	}

	public void setMajor(int major) {
		this.major = major;
	}

	public int getMinor() {
		return minor;
	}

	public void setMinor(int minor) {
		this.minor = minor;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public long getLast_active_time() {
		return last_active_time;
	}

	public void setLast_active_time(long last_active_time) {
		this.last_active_time = last_active_time;
	}

	public int getPoi_id() {
		return poi_id;
	}

	public void setPoi_id(int poi_id) {
		this.poi_id = poi_id;
	}

	public int getPage_id() {
		return page_id;
	}

	public void setPage_id(int page_id) {
		this.page_id = page_id;
	}

	public String getCommon() {
		return common;
	}

	public void setCommon(String common) {
		this.common = common;
	}
	
}
