package com.cy.wx.shake.req;

import java.util.List;

import com.cy.wx.shake.DeviceIdentifier;

/**
 * 查询设备与页面的关联关系(请求)
 * 
 * @author zhangjianhui
 * 
 */
public class RelationSearchReq {
	/** 查询方式。1： 查询设备的关联关系；2：查询页面的关联关系 */
	private String type;
	/** 指定的设备 ； 当type为1时，此项为必填 */
	private List<DeviceIdentifier> device_identifiers;
	/** 关联关系列表的起始索引值；当type为2时，此项为必填 */
	private int begin;
	/** 待查询的关联关系数量，不能超过50个；当type为2时，此项为必填 */
	private int count;
	/** 指定的页面id；当type为2时，此项为必填 */
	private int page_id;

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

	public int getPage_id() {
		return page_id;
	}

	public void setPage_id(int page_id) {
		this.page_id = page_id;
	}

}
