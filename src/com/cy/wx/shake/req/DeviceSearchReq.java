package com.cy.wx.shake.req;

import java.util.List;

import com.cy.wx.shake.DeviceIdentifier;

/**
 * 查询设备列表(请求)
 * @author zhangjianhui
 *
 */
public class DeviceSearchReq {
	/** 查询类型。1：查询设备id列表中的设备；2：分页查询所有设备信息；3：分页查询某次申请的所有设备信息  */
	private String type;
	/** 指定的设备 ； 当type为1时，此项为必填  */
	private List<DeviceIdentifier> device_identifiers;
	/** 设备列表的起始索引值  */
	private int begin;
	/** 待查询的设备数量，不能超过50个 */
	private int count;
	/** 批次ID，申请设备ID时所返回的批次ID；当type为3时，此项为必填  */
	private int apply_id;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public List<DeviceIdentifier> getDevice_identifiers() {
		return device_identifiers;
	}

	public void setDevice_identifiers(List<DeviceIdentifier> device_identifiers) {
		this.device_identifiers = device_identifiers;
	}

	public int getBegin() {
		return begin;
	}

	public void setBegin(int begin) {
		this.begin = begin;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public int getApply_id() {
		return apply_id;
	}

	public void setApply_id(int apply_id) {
		this.apply_id = apply_id;
	}
	
}
