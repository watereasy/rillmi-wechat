package com.cy.wx.shake;

/**
 * 设备信息
 * @author zhangjianhui
 *
 */
public class BeaconInfo {
	/** Beacon信号与手机的距离，单位为米  */
	private double distance;
	
	private String uuid;
	
	private int major;
	
	private int minor;

	public double getDistance() {
		return distance;
	}

	public void setDistance(double distance) {
		this.distance = distance;
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
