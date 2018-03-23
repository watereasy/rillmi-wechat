package com.cy.wx.shake.req;

import net.sf.json.JSONArray;

/**
 * 录入红包（请求）
 * @author zhangjianhui
 *
 */
public class LotterySetPrizeReq {

	private String lottery_id;
	
	private String mchid;
	
	private String sponsor_appid;
	
	private JSONArray prize_info_list ;
	
	public String getLottery_id() {
		return lottery_id;
	}

	public void setLottery_id(String lottery_id) {
		this.lottery_id = lottery_id;
	}

	public String getMchid() {
		return mchid;
	}

	public void setMchid(String mchid) {
		this.mchid = mchid;
	}

	public String getSponsor_appid() {
		return sponsor_appid;
	}

	public void setSponsor_appid(String sponsor_appid) {
		this.sponsor_appid = sponsor_appid;
	}

	public JSONArray getPrize_info_list() {
		return prize_info_list;
	}

	public void setPrize_info_list(JSONArray prize_info_list) {
		this.prize_info_list = prize_info_list;
	}

}
