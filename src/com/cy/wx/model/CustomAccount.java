package com.cy.wx.model;

/**
 * 客服账号
 * @author zhangjianhui
 *
 */
public class CustomAccount {
	private String id;
	// 客服账号
	private String kf_account;
	// 用户名
	private String nickname;
	// 密码
	private String password;
	// 头像
	private String headimg;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getKf_account() {
		return kf_account;
	}

	public void setKf_account(String kf_account) {
		this.kf_account = kf_account;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getHeadimg() {
		return headimg;
	}

	public void setHeadimg(String headimg) {
		this.headimg = headimg;
	}

}
