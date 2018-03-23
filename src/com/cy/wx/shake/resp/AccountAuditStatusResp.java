package com.cy.wx.shake.resp;

/**
 * 查询审核状态(响应)
 * @author zhangjianhui
 *
 */
public class AccountAuditStatusResp {
	/** 提交申请的时间戳 */
	private long apply_time;
	/** 审核备注，包括审核不通过的原因  */
	private String audit_comment;
	/** 审核状态。0：审核未通过、1：审核中、2：审核已通过；审核会在三个工作日内完成  */
	private int audit_status;
	/** 确定审核结果的时间戳；若状态为审核中，则该时间值为0 */
	private long audit_time;

	public long getApply_time() {
		return apply_time;
	}

	public void setApply_time(long apply_time) {
		this.apply_time = apply_time;
	}

	public String getAudit_comment() {
		return audit_comment;
	}

	public void setAudit_comment(String audit_comment) {
		this.audit_comment = audit_comment;
	}

	public int getAudit_status() {
		return audit_status;
	}

	public void setAudit_status(int audit_status) {
		this.audit_status = audit_status;
	}

	public long getAudit_time() {
		return audit_time;
	}

	public void setAudit_time(long audit_time) {
		this.audit_time = audit_time;
	}
	
}
