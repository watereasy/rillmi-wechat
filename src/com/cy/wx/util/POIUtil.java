package com.cy.wx.util;

import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cy.wx.model.WeixinPOI;

/**
 * 微信门店接口
 * @author zhangjianhui
 *
 */
public class POIUtil {
	protected static Logger logger = LoggerFactory.getLogger(POIUtil.class);
	
	/**
	 * 上传图片
	 * @param accessToken
	 * @param mediaFileUrl
	 * @return
	 */
	public static String uploadImg(String accessToken, String mediaFileUrl){
		return CommonUtil.uploadImg(accessToken, mediaFileUrl);
	}
	
	/**
	 * 创建门店
	 * @param accessToken
	 * @param jsonData
	 * @return
	 */
	public static boolean addPOI(String accessToken, String jsonData){
		boolean result = false;
		// 拼装请求地址
		String requestUrl = "http://api.weixin.qq.com/cgi-bin/poi/addpoi?access_token=TOKEN";
		requestUrl = requestUrl.replace("ACCESS_TOKEN", accessToken);
		JSONObject jsonObj = CommonUtil.httpsRequest(requestUrl, "POST", jsonData);
		if (null != jsonObj) {
			int errorCode = jsonObj.getInt("errcode");
			String errorMsg = jsonObj.getString("errmsg");
			if (errorCode == 0) {
				result = true;
				logger.info("创建门店成功");
			} else {
				logger.error("创建门店失败 errcode:{} errmsg:{}", errorCode, errorMsg);
			}
		}
		
		return result;
	}
	
	/**
	 * 查询门店信息
	 * @param accessToken
	 * @param poiId
	 * @return
	 */
	public static WeixinPOI getPOI(String accessToken, String poiId){
		WeixinPOI poi = new WeixinPOI();
		// 拼装请求地址
		String requestUrl = "http://api.weixin.qq.com/cgi-bin/poi/getpoi?access_token=TOKEN";
		requestUrl = requestUrl.replace("ACCESS_TOKEN", accessToken);
		String jsonMsg = "{\"poi_id\":\"%s\"}";
		JSONObject jsonObj = CommonUtil.httpsRequest(requestUrl, "POST", String.format(jsonMsg, poiId));
		if (null != jsonObj) {
			int errorCode = jsonObj.getInt("errcode");
			String errorMsg = jsonObj.getString("errmsg");
			if (errorCode == 0) {
				JSONObject jsonBuss = jsonObj.getJSONObject("business");
				JSONObject jsonBase = jsonBuss.getJSONObject("base_info");
				poi = (WeixinPOI)JSONObject.toBean(jsonBase, WeixinPOI.class);
				logger.info("查询门店信息成功poiId：{}", poiId);
			} else {
				logger.error("查询门店信息失败" + poiId + " errcode:{} errmsg:{}", errorCode, errorMsg);
			}
		}
		
		return poi;
	}
	
	/**
	 * 查询门店列表
	 * @param accessToken
	 * @param begin 开始位置，0 即为从第一条开始查询
	 * @param limit 回数据条数，最大允许50，默认为20
	 * @return
	 */
	public static JSONObject getPOILis(String accessToken, int begin, int limit){
		// 拼装请求地址
		String requestUrl = "https://api.weixin.qq.com/cgi-bin/poi/getpoilist?access_token=TOKEN";
		requestUrl = requestUrl.replace("ACCESS_TOKEN", accessToken);
		String jsonMsg = "{\"begin\":%d, \"limit\":%d}";
		JSONObject jsonObj = CommonUtil.httpsRequest(requestUrl, "POST", String.format(jsonMsg, begin, limit));
		if (null != jsonObj) {
			int errorCode = jsonObj.getInt("errcode");
			String errorMsg = jsonObj.getString("errmsg");
			if (errorCode == 0) {
				logger.info("查询门店列表成功");
			} else {
				logger.error("查询门店列表失败errcode:{} errmsg:{}", errorCode, errorMsg);
			}
		}
		
		return jsonObj;
	}
	
	/**
	 * 修改门店服务信息
	 * @param accessToken
	 * @param jsonData
	 * @return
	 */
	public static boolean updatePOI(String accessToken, String jsonData){
		boolean result = false;
		// 拼装请求地址
		String requestUrl = "https://api.weixin.qq.com/cgi-bin/poi/updatepoi?access_token=TOKEN";
		requestUrl = requestUrl.replace("ACCESS_TOKEN", accessToken);
		JSONObject jsonObj = CommonUtil.httpsRequest(requestUrl, "POST", jsonData);
		if (null != jsonObj) {
			int errorCode = jsonObj.getInt("errcode");
			String errorMsg = jsonObj.getString("errmsg");
			if (errorCode == 0) {
				result = true;
				logger.info("修改门店服务信息成功");
			} else {
				logger.error("修改门店服务信息失败errcode:{} errmsg:{}", errorCode, errorMsg);
			}
		}
		
		return result;
	}
	
	/**
	 * 删除门店
	 * @param accessToken
	 * @param poiId
	 * @return
	 */
	public static boolean delPOI(String accessToken, String poiId){
		boolean result = false;
		// 拼装请求地址
		String requestUrl = "https://api.weixin.qq.com/cgi-bin/poi/delpoi?access_token=TOKEN";
		requestUrl = requestUrl.replace("ACCESS_TOKEN", accessToken);
		String jsonMsg = "{\"poi_id\":\"%s\"}";
		JSONObject jsonObj = CommonUtil.httpsRequest(requestUrl, "POST", String.format(jsonMsg, poiId));
		if (null != jsonObj) {
			int errorCode = jsonObj.getInt("errcode");
			String errorMsg = jsonObj.getString("errmsg");
			if (errorCode == 0) {
				result = true;
				logger.info("删除门店成功");
			} else {
				logger.error("删除门店失败errcode:{} errmsg:{}", errorCode, errorMsg);
			}
		}
		
		return result;
	}
	
	/**
	 * 门店类目表
	 * @param accessToken
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static List<String> getPOICategory(String accessToken){
		List<String> resultLis = new ArrayList<String>();
		// 拼装请求地址
		String requestUrl = "http://api.weixin.qq.com/cgi-bin/api_getwxcategory?access_token=TOKEN";
		requestUrl = requestUrl.replace("ACCESS_TOKEN", accessToken);
		JSONObject jsonObj = CommonUtil.httpsRequest(requestUrl, "POST", null);
		if (null != jsonObj) {
			if (jsonObj.containsKey("category_list")) {
				resultLis = JSONArray.toList(jsonObj.getJSONArray("category_list"), new ArrayList<String>(), new JsonConfig());
				logger.info("查询门店列表成功");
			} else {
				int errorCode = jsonObj.getInt("errcode");
				String errorMsg = jsonObj.getString("errmsg");
				logger.error("查询门店列表失败errcode:{} errmsg:{}", errorCode, errorMsg);
			}
		}
		
		return resultLis;
	}
	
}
