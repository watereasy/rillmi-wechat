package com.cy.wx.model;

/**
 * 地理位置信息
 * 
 */
public class BaiduPlace implements Comparable<BaiduPlace>{
	// 名称
	private String name;
	// 地址
	private String address;
	// 经度
	private String lng;
	// 纬度
	private String lat;
	// 电话
	private String telephone;
	// 距离
	private int distance;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getLng() {
		return lng;
	}

	public void setLng(String lng) {
		this.lng = lng;
	}

	public String getLat() {
		return lat;
	}

	public void setLat(String lat) {
		this.lat = lat;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public int getDistance() {
		return distance;
	}

	public void setDistance(int distance) {
		this.distance = distance;
	}

	@Override
	public int compareTo(BaiduPlace baiduPlace) {
		return this.distance - baiduPlace.getDistance();
	}
}
