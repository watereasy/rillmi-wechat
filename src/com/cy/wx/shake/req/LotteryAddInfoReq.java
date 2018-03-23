package com.cy.wx.shake.req;

/**
 * 摇一摇 创建红包(请求)
 * @author zhangjianhui
 *
 */
public class LotteryAddInfoReq {
	/** 抽奖活动名称（选择使用模板时，也作为摇一摇消息主标题），最长6个汉字，12个英文字母  */
	private String title;
	/** 抽奖活动描述（选择使用模板时，也作为摇一摇消息副标题），最长7个汉字，14个英文字母 */
	private String desc;
	/** 抽奖开关。0关闭，1开启，默认为1 */
	private int onoff;
	/** 抽奖活动开始时间，unix时间戳，单位秒  */
	private long begin_time;
	/** 抽奖活动结束时间，unix时间戳，单位秒,红包活动有效期最长为91天  */
	private long expire_time;
	/** 红包提供商户公众号的appid，需与预下单中的公众账号appid（wxappid）一致  */
	private String sponsor_appid;
	/** 红包总数，红包总数是录入红包ticket总数的上限，因此红包总数应该大于等于预下单时红包ticket总数  */
	private int total;
	/** 红包关注界面后可以跳转到第三方自定义的页面  */
	private String jump_url;
	/** 开发者自定义的key，用来生成活动抽奖接口的签名参数，长度32位。使用方式见sign生成规则  */
	private String key;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public int getOnoff() {
		return onoff;
	}

	public void setOnoff(int onoff) {
		this.onoff = onoff;
	}

	public long getBegin_time() {
		return begin_time;
	}

	public void setBegin_time(long begin_time) {
		this.begin_time = begin_time;
	}

	public long getExpire_time() {
		return expire_time;
	}

	public void setExpire_time(long expire_time) {
		this.expire_time = expire_time;
	}

	public String getSponsor_appid() {
		return sponsor_appid;
	}

	public void setSponsor_appid(String sponsor_appid) {
		this.sponsor_appid = sponsor_appid;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public String getJump_url() {
		return jump_url;
	}

	public void setJump_url(String jump_url) {
		this.jump_url = jump_url;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}
	
}
