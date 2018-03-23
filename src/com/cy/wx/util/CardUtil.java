package com.cy.wx.util;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cy.wx.model.WeixinQRCode;

/**
 * 微信卡券接口
 * @author zhangjianhui
 *
 */
public class CardUtil {
	protected static Logger logger = LoggerFactory.getLogger(CardUtil.class);
	/** 文本 */
	public static final String CODE_TYPE_TEXT = "CODE_TYPE_TEXT";
	/** 一维码 */
	public static final String CODE_TYPE_BARCODE = "CODE_TYPE_BARCODE";
	/** 二维码 */
	public static final String CODE_TYPE_QRCODE = "CODE_TYPE_QRCODE";
	/** 二维码无code显示 */
	public static final String CODE_TYPE_ONLY_QRCODE = "CODE_TYPE_ONLY_QRCODE";
	/** 一维码无code显示 */
	public static final String CODE_TYPE_ONLY_BARCODE = "CODE_TYPE_ONLY_BARCODE";
	
	/** 固定日期区间 */
	public static final String DATE_TYPE_FIX_TIME_RANGE = "DATE_TYPE_FIX_TIME_RANGE";
	/** 固定时长(自领取后按天算) */
	public static final String DATE_TYPE_FIX_TERM = "DATE_TYPE_FIX_TERM";
	
	/** 团购券类型 */
	public static final String CARD_TYPE_GROUPON = "GROUPON";
	/** 代金券类型 */
	public static final String CARD_TYPE_CASH = "CASH";
	/** 折扣券类型 */
	public static final String CARD_TYPE_DISCOUNT = "DISCOUNT";
	/** 礼品券类型 */
	public static final String CARD_TYPE_GIFT = "GIFT";
	/** 一般优惠券类型 */
	public static final String CARD_TYPE_GENERAL_COUPON = "GENERAL_COUPON";
	
	/**
	 * 上传卡券LOGO
	 * @param accessToken
	 * @param mediaFileUrl
	 * @return
	 */
	public static String uploadImg(String accessToken, String mediaFileUrl){
		return CommonUtil.uploadImg(accessToken, mediaFileUrl);
	}
	
	/**
	 * 获取卡券颜色列表
	 * @param accessToken
	 * @return
	 */
	public static JSONArray getColor(String accessToken){
		JSONArray jsonArr = new JSONArray();
		// 拼接请求地址
		String requestUrl = "https://api.weixin.qq.com/card/getcolors?access_token=ACCESS_TOKEN";
		requestUrl = requestUrl.replace("ACCESS_TOKEN", accessToken);
		JSONObject jsonObj = CommonUtil.httpsRequest(requestUrl, "GET", null);
		if (null != jsonObj) {
			int errorCode = jsonObj.getInt("errcode");
			String errorMsg = jsonObj.getString("errmsg");
			if (0 == errorCode) {
				jsonArr = jsonObj.getJSONArray("colors");
				logger.info("获取卡券颜色列表成功");
			} else {
				logger.error("获取卡券颜色列表失败 errcode:{} errmsg:{}", errorCode, errorMsg);
			}
		}
		
		return jsonArr;
	}
	
	/**
	 * 创建卡券
	 * @param accessToken
	 * @param jsonData
	 * @return
	 */
	public static String createCard(String accessToken, String jsonData){
		String cardId = "";
		// 拼接请求地址
		String requestUrl = "https://api.weixin.qq.com/card/create?access_token=ACCESS_TOKEN";
		requestUrl = requestUrl.replace("ACCESS_TOKEN", accessToken);
		JSONObject jsonObj = CommonUtil.httpsRequest(requestUrl, "POST", jsonData);
		if (null != jsonObj) {
			int errorCode = jsonObj.getInt("errcode");
			String errorMsg = jsonObj.getString("errmsg");
			if (0 == errorCode) {
				cardId = jsonObj.getString("card_id");
				logger.info("创建卡券成功 card_id:{}" + cardId);
			} else {
				logger.error("创建卡券失败 errcode:{} errmsg:{}", errorCode, errorMsg);
			}
		}
		
		return cardId;
	}
	
	/**
	 * 创建卡券二维码
	 * @param accessToken
	 * @param jsonData
	 * @return
	 */
	public static WeixinQRCode createCardQR(String accessToken, String jsonData){
		WeixinQRCode weixinQRCode = null;
		// 拼接请求地址
		String requestUrl = "https://api.weixin.qq.com/card/qrcode/create?access_token=ACCESS_TOKEN";
		requestUrl = requestUrl.replace("ACCESS_TOKEN", accessToken);
		JSONObject jsonObj = CommonUtil.httpsRequest(requestUrl, "POST", jsonData);
		if (null != jsonObj) {
			int errorCode = jsonObj.getInt("errcode");
			String errorMsg = jsonObj.getString("errmsg");
			if (0 == errorCode) {
				weixinQRCode = new WeixinQRCode();
				weixinQRCode.setTicket(jsonObj.getString("ticket"));
				weixinQRCode.setExpireSeconds(jsonObj.getInt("expire_seconds"));
				weixinQRCode.setUrl(jsonObj.getString("url"));
				logger.info("创建卡券二维码成功 ticket:{} expire_seconds:{}", weixinQRCode.getTicket(), weixinQRCode.getExpireSeconds());
			} else {
				logger.error("创建卡券二维码失败 errcode:{} errmsg:{}", errorCode, errorMsg);
			}
		}
		
		return weixinQRCode;
	}
	
	/**
	 * 图文消息群发卡券HTML文本内容
	 * @param accessToken
	 * @param cardId
	 * @return
	 */
	public static String getCardHtml(String accessToken, String cardId){
		String content = "";
		// 拼接请求地址
		String requestUrl = "https://api.weixin.qq.com/card/create?access_token=ACCESS_TOKEN";
		requestUrl = requestUrl.replace("ACCESS_TOKEN", accessToken);
		String jsonMsg = "{\"card_id\":\"%s\"}";
		JSONObject jsonObj = CommonUtil.httpsRequest(requestUrl, "POST", String.format(jsonMsg, cardId));
		if (null != jsonObj) {
			int errorCode = jsonObj.getInt("errcode");
			String errorMsg = jsonObj.getString("errmsg");
			if (0 == errorCode) {
				content = jsonObj.getString("content");
				logger.info("图文消息群发卡券HTML文本内容成功 card_id:{}" + cardId);
			} else {
				logger.error("图文消息群发卡券HTML文本内容失败 errcode:{} errmsg:{}", errorCode, errorMsg);
			}
		}
		
		return content;
	}
	
	/**
	 * 设置测试白名单
	 * @param accessToken
	 * @param jsonData
	 * @return
	 */
	public static boolean setTestwhitelist(String accessToken, String jsonData){
		boolean result = false;
		String requestUrl = "https://api.weixin.qq.com/card/testwhitelist/set?access_token=ACCESS_TOKEN";
		requestUrl = requestUrl.replace("ACCESS_TOKEN", accessToken);
		JSONObject jsonObj = CommonUtil.httpsRequest(requestUrl, "POST", jsonData);
		if (null != jsonObj) {
			int errorCode = jsonObj.getInt("errcode");
			String errorMsg = jsonObj.getString("errmsg");
			if (0 == errorCode) {
				result = true;
				logger.info("设置测试白名单成功");
			} else {
				logger.error("设置测试白名单失败 errcode:{} errmsg:{}", errorCode, errorMsg);
			}
		}
		
		return result;
	}
	
	/**
	 * 核销Code
	 * @param accessToken
	 * @param jsonData
	 * @return
	 */
	public static JSONObject consumeCode(String accessToken, String jsonData){
		String requestUrl = "https://api.weixin.qq.com/card/code/consume?access_token=ACCESS_TOKEN";
		requestUrl = requestUrl.replace("ACCESS_TOKEN", accessToken);
		JSONObject jsonObj = CommonUtil.httpsRequest(requestUrl, "POST", jsonData);
		if (null != jsonObj) {
			int errorCode = jsonObj.getInt("errcode");
			String errorMsg = jsonObj.getString("errmsg");
			if (0 == errorCode) {
				logger.info("核销Code成功");
			} else {
				logger.error("核销Code失败 errcode:{} errmsg:{}", errorCode, errorMsg);
			}
		}
		
		return jsonObj;
	}
	
	/**
	 * 卡券Code解码
	 * @param accessToken
	 * @param encryptCode
	 * @return
	 */
	public static String decryptCode(String accessToken, String encryptCode){
		String code = "";
		// 拼接请求地址
		String requestUrl = "https://api.weixin.qq.com/card/code/decrypt?access_token=ACCESS_TOKEN";
		requestUrl = requestUrl.replace("ACCESS_TOKEN", accessToken);
		String jsonMsg = "{\"encrypt_code\":\"%s\"}";
		JSONObject jsonObj = CommonUtil.httpsRequest(requestUrl, "POST", String.format(jsonMsg, encryptCode));
		if (null != jsonObj) {
			int errorCode = jsonObj.getInt("errcode");
			String errorMsg = jsonObj.getString("errmsg");
			if (0 == errorCode) {
				code = jsonObj.getString("code");
				logger.info("卡券Code解码成功 encryptCode:{}" + encryptCode);
			} else {
				logger.error("卡券Code解码失败 errcode:{} errmsg:{}", errorCode, errorMsg);
			}
		}
		
		return code;
	}
	
	/**
	 * 查询code
	 * @param accessToken
	 * @param code
	 * @return
	 */
	public static JSONObject getCode(String accessToken, String code){
		// 拼接请求地址
		String requestUrl = "https://api.weixin.qq.com/card/code/get?access_token=ACCESS_TOKEN";
		requestUrl = requestUrl.replace("ACCESS_TOKEN", accessToken);
		String jsonMsg = "{\"code\":\"%s\"}";
		JSONObject jsonObj = CommonUtil.httpsRequest(requestUrl, "POST", String.format(jsonMsg, code));
		if (null != jsonObj) {
			int errorCode = jsonObj.getInt("errcode");
			String errorMsg = jsonObj.getString("errmsg");
			if (0 == errorCode) {
				logger.info("卡券查询code成功 code:{}" + code);
			} else {
				logger.error("卡券查询code失败 errcode:{} errmsg:{}", errorCode, errorMsg);
			}
		}
		
		return jsonObj;
	}

}
