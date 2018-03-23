package com.cy.wx.shake.req;

import java.util.List;

/**
 * 摇一摇申请开通(请求)
 * @author zhangjianhui
 *
 */
public class AccountRegisterReq {
	/** 联系人姓名，不超过20汉字或40个英文字母  */
	private String name;
	
	private String phone_number;

	private String email;
	
	private String industry_id;
	
	private List<String> qualification_cert_urls;
	/** 申请理由，不超过250汉字或500个英文字母  */
	private String apply_reason;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone_number() {
		return phone_number;
	}

	public void setPhone_number(String phone_number) {
		this.phone_number = phone_number;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getIndustry_id() {
		return industry_id;
	}

	public void setIndustry_id(String industry_id) {
		this.industry_id = industry_id;
	}

	public List<String> getQualification_cert_urls() {
		return qualification_cert_urls;
	}

	public void setQualification_cert_urls(List<String> qualification_cert_urls) {
		this.qualification_cert_urls = qualification_cert_urls;
	}

	public String getApply_reason() {
		return apply_reason;
	}

	public void setApply_reason(String apply_reason) {
		this.apply_reason = apply_reason;
	}

}
