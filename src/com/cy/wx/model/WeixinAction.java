package com.cy.wx.model;

import net.sf.json.JSONObject;

public class WeixinAction {
	
	private String action_name;
	
	private int expire_seconds;
	
	private JSONObject action_info;

	public String getAction_name() {
		return action_name;
	}

	public void setAction_name(String action_name) {
		this.action_name = action_name;
	}

	public int getExpire_seconds() {
		return expire_seconds;
	}

	public void setExpire_seconds(int expire_seconds) {
		this.expire_seconds = expire_seconds;
	}

	public JSONObject getAction_info() {
		return action_info;
	}

	public void setAction_info(JSONObject action_info) {
		this.action_info = action_info;
	}
	

}
