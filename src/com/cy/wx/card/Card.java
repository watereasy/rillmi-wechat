package com.cy.wx.card;

import java.util.List;

/**
 * 卡券基类
 * 
 * @author zhangjianhui
 * 
 */
public class Card {
	// 卡券的商户logo，建议像素为300*300
	private String logo_url;
	// Code展示类型，"CODE_TYPE_TEXT"，文本；"CODE_TYPE_BARCODE"，一维码
	// ；"CODE_TYPE_QRCODE"，二维码；
	// "CODE_TYPE_ONLY_QRCODE",二维码无code显示；"CODE_TYPE_ONLY_BARCODE",一维码无code显示
	private String code_type;
	// 商户名字,字数上限为12个汉字
	private String brand_name;
	// 卡券名，字数上限为9个汉字。(建议涵盖卡券属性、服务及金额)
	private String title;
	// 券名，字数上限为18个汉字
	private String sub_title;
	// 券颜色
	private String color;
	// 卡券使用提醒，字数上限为16个汉字
	private String notice;
	// 客服电话
	private String service_phone;
	// 卡券使用说明，字数上限为1024个汉字
	private String description;
	// 使用日期，有效期的信息
	private DateInfo date_info;
	// 商品信息
	private Sku sku;
	// 每人可领券的数量限制,不填写默认为50
	private int get_limit;
	// 是否自定义Code码。填写true或false，默认为false
	private boolean use_custom_code;
	// 是否指定用户领取，填写true或false。默认为false。通常指定特殊用户群体投放卡券或防止刷券时选择指定用户领取
	private boolean bind_openid;
	// 卡券领取页面是否可分享
	private boolean can_share;
	// 卡券是否可转赠
	private boolean can_give_friend;
	// 门店位置ID
	private List<Integer> location_id_list;
	// 自定义跳转外链的入口名字
	private String custom_url_name;
	// 自定义跳转的URL
	private String custom_url;
	// 显示在入口右侧的提示语
	private String custom_url_sub_title;
	// 营销场景的自定义入口名称
	private String promotion_url_name;
	// 入口跳转外链的地址链接
	private String promotion_url;
	// 第三方来源名，例如同程旅游、大众点评
	private String source;

	public String getLogo_url() {
		return logo_url;
	}

	public void setLogo_url(String logo_url) {
		this.logo_url = logo_url;
	}

	public String getCode_type() {
		return code_type;
	}

	public void setCode_type(String code_type) {
		this.code_type = code_type;
	}

	public String getBrand_name() {
		return brand_name;
	}

	public void setBrand_name(String brand_name) {
		this.brand_name = brand_name;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSub_title() {
		return sub_title;
	}

	public void setSub_title(String sub_title) {
		this.sub_title = sub_title;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getNotice() {
		return notice;
	}

	public void setNotice(String notice) {
		this.notice = notice;
	}

	public String getService_phone() {
		return service_phone;
	}

	public void setService_phone(String service_phone) {
		this.service_phone = service_phone;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public DateInfo getDate_info() {
		return date_info;
	}

	public void setDate_info(DateInfo date_info) {
		this.date_info = date_info;
	}

	public Sku getSku() {
		return sku;
	}

	public void setSku(Sku sku) {
		this.sku = sku;
	}

	public int getGet_limit() {
		return get_limit;
	}

	public void setGet_limit(int get_limit) {
		this.get_limit = get_limit;
	}

	public boolean isUse_custom_code() {
		return use_custom_code;
	}

	public void setUse_custom_code(boolean use_custom_code) {
		this.use_custom_code = use_custom_code;
	}

	public boolean isBind_openid() {
		return bind_openid;
	}

	public void setBind_openid(boolean bind_openid) {
		this.bind_openid = bind_openid;
	}

	public boolean isCan_share() {
		return can_share;
	}

	public void setCan_share(boolean can_share) {
		this.can_share = can_share;
	}

	public boolean isCan_give_friend() {
		return can_give_friend;
	}

	public void setCan_give_friend(boolean can_give_friend) {
		this.can_give_friend = can_give_friend;
	}

	public List<Integer> getLocation_id_list() {
		return location_id_list;
	}

	public void setLocation_id_list(List<Integer> location_id_list) {
		this.location_id_list = location_id_list;
	}

	public String getCustom_url_name() {
		return custom_url_name;
	}

	public void setCustom_url_name(String custom_url_name) {
		this.custom_url_name = custom_url_name;
	}

	public String getCustom_url() {
		return custom_url;
	}

	public void setCustom_url(String custom_url) {
		this.custom_url = custom_url;
	}

	public String getCustom_url_sub_title() {
		return custom_url_sub_title;
	}

	public void setCustom_url_sub_title(String custom_url_sub_title) {
		this.custom_url_sub_title = custom_url_sub_title;
	}

	public String getPromotion_url_name() {
		return promotion_url_name;
	}

	public void setPromotion_url_name(String promotion_url_name) {
		this.promotion_url_name = promotion_url_name;
	}

	public String getPromotion_url() {
		return promotion_url;
	}

	public void setPromotion_url(String promotion_url) {
		this.promotion_url = promotion_url;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

}
