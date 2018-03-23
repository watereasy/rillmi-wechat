package com.cy.wx.card;

/**
 * 商品信息
 * @author zhangjianhui
 *
 */
public class Sku {
	// 卡券库存的数量，不支持填写0，上限为100000000
	private int quantity;

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
}
