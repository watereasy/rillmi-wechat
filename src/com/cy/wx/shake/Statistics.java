package com.cy.wx.shake;

/**
 * 摇一摇数据统计
 * @author zhangjianhui
 *
 */
public class Statistics {
	/** 点击摇周边消息的次数 */
	private long click_pv;
	/** 点击摇周边消息的人数  */
	private long click_uv;
	/** 当天0点对应的时间戳  */
	private long ftime;
	/** 摇周边的次数  */
	private long shake_pv;
	/** 摇周边的人数   */
	private long shake_uv;

	public long getClick_pv() {
		return click_pv;
	}

	public void setClick_pv(long click_pv) {
		this.click_pv = click_pv;
	}

	public long getClick_uv() {
		return click_uv;
	}

	public void setClick_uv(long click_uv) {
		this.click_uv = click_uv;
	}

	public long getFtime() {
		return ftime;
	}

	public void setFtime(long ftime) {
		this.ftime = ftime;
	}

	public long getShake_pv() {
		return shake_pv;
	}

	public void setShake_pv(long shake_pv) {
		this.shake_pv = shake_pv;
	}

	public long getShake_uv() {
		return shake_uv;
	}

	public void setShake_uv(long shake_uv) {
		this.shake_uv = shake_uv;
	}

}
