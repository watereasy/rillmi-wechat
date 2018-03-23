package com.cy.wx.card;

/**
 * 卡券二维码
 * @author zhangjianhui
 *
 */
public class CardQR {
	// 卡券ID
	private String card_id;
	// use_custom_code字段为true的卡券必须填写，非自定义code不必填写
	private String code;
	// 指定领取者的openid，只有该用户能领取。bind_openid字段为true的卡券必须填写，非指定openid不必填写
	private String openid;
	// 指定二维码的有效时间，范围是60 ~ 1800秒。不填默认为永久有效
	private Integer expire_seconds;
	// 指定下发二维码，生成的二维码随机分配一个code，领取后不可再次扫描。填写true或false。默认false
	private boolean is_unique_code;
	// 领取场景值，用于领取渠道的数据统计，默认值为0，字段类型为整型，长度限制为60位数字。用户领取卡券后触发的事件推送中会带上此自定义场景值
	private int outer_id;

	public String getCard_id() {
		return card_id;
	}

	public void setCard_id(String card_id) {
		this.card_id = card_id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}

	public Integer getExpire_seconds() {
		return expire_seconds;
	}

	public void setExpire_seconds(Integer expire_seconds) {
		this.expire_seconds = expire_seconds;
	}

	public boolean isIs_unique_code() {
		return is_unique_code;
	}

	public void setIs_unique_code(boolean is_unique_code) {
		this.is_unique_code = is_unique_code;
	}

	public int getOuter_id() {
		return outer_id;
	}

	public void setOuter_id(int outer_id) {
		this.outer_id = outer_id;
	}

}
