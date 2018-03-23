package com.cy.wx.card;

/**
 * 团购券
 * @author zhangjianhui
 *
 */
public class GrouponCard extends BaseCard{
	// 团购券专用，团购详情
	private String deal_detail;

	public String getDeal_detail() {
		return deal_detail;
	}

	public void setDeal_detail(String deal_detail) {
		this.deal_detail = deal_detail;
	}

}
