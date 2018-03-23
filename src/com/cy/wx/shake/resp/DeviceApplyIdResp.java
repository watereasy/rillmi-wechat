package com.cy.wx.shake.resp;

/**
 * 申请设备ID(响应)
 * @author zhangjianhui
 *
 */
public class DeviceApplyIdResp {
	/** 申请的批次ID，可用在“查询设备列表”接口按批次查询本次申请成功的设备ID */
	private int apply_id;
	/** 审核状态。0：审核未通过、1：审核中、2：审核已通过 */
	private int audit_status;
	/** 审核备注 */
	private String audit_comment;

	public int getApply_id() {
		return apply_id;
	}

	public void setApply_id(int apply_id) {
		this.apply_id = apply_id;
	}

	public int getAudit_status() {
		return audit_status;
	}

	public void setAudit_status(int audit_status) {
		this.audit_status = audit_status;
	}

	public String getAudit_comment() {
		return audit_comment;
	}

	public void setAudit_comment(String audit_comment) {
		this.audit_comment = audit_comment;
	}

}
