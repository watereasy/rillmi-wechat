package com.cy.wx.shake;

public class DeviceIdentifier {
	/** 设备编号，若填了UUID、major、minor，则可不填设备编号，若二者都填，则以设备编号为优先  */
	private int device_id;
	
	private String uuid;
	
	private int major;
	
	private int minor;

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

}
