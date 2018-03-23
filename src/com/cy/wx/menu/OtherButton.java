package com.cy.wx.menu;

import java.util.List;

/**
 * 其他类型按钮
 * @author zhangjianhui
 *
 */
public class OtherButton extends Button{
	/** scancode_waitmsg:扫码带提示|scancode_push:扫码推事件|pic_sysphoto:系统拍照发图
	// pic_photo_or_album:拍照或者相册发图|pic_weixin:微信相册发图|location_select:地理位置选择
	// 仅支持微信iPhone5.4.1以上版本，和Android5.4以上版本的微信用户 */
	private String type;
	
	private String key;
	
	private List<Object> sub_button;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public List<Object> getSub_button() {
		return sub_button;
	}

	public void setSub_button(List<Object> sub_button) {
		this.sub_button = sub_button;
	}

}
