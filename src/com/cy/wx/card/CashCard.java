package com.cy.wx.card;

/**
 * 代金券
 * 
 * @author zhangjianhui
 * 
 */
public class CashCard extends BaseCard {
	// 代金券专用，表示起用金额。（单位为分）
	private int least_cost;
	// 代金券专用，表示减免金额。（单位为分）
	private int reduce_cost;

	public int getLeast_cost() {
		return least_cost;
	}

	public void setLeast_cost(int least_cost) {
		this.least_cost = least_cost;
	}

	public int getReduce_cost() {
		return reduce_cost;
	}

	public void setReduce_cost(int reduce_cost) {
		this.reduce_cost = reduce_cost;
	}

}
