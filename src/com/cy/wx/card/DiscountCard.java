package com.cy.wx.card;

/**
 * 折扣券
 * @author zhangjianhui
 *
 */
public class DiscountCard extends BaseCard {
	// 折扣券专用，表示打折额度（百分比）。填30就是七折
	private int discount;

	public int getDiscount() {
		return discount;
	}

	public void setDiscount(int discount) {
		this.discount = discount;
	}

}
