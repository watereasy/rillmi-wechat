package com.cy.wx.shake.resp;

/**
 * 创建红包(相应)
 * @author zhangjianhui
 *
 */
public class LotteryAddInfoResp {
	/** 生成的红包活动id */
	private String lottery_id;
	/** 生成的模板页面ID */
	private int page_id;

	public String getLottery_id() {
		return lottery_id;
	}

	public void setLottery_id(String lottery_id) {
		this.lottery_id = lottery_id;
	}

	public int getPage_id() {
		return page_id;
	}

	public void setPage_id(int page_id) {
		this.page_id = page_id;
	}

}
