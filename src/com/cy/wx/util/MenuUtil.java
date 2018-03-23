package com.cy.wx.util;

import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 自定义菜单工具类
 *
 */
public class MenuUtil {
	
	private static Logger logger = LoggerFactory.getLogger(MenuUtil.class);
	// 菜单创建(POST)
	public static final String MENU_CREATE_URL = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=ACCESS_TOKEN";
	// 菜单查询(GET)
	public static final String MENU_GET_URL = "https://api.weixin.qq.com/cgi-bin/menu/get?access_token=ACCESS_TOKEN";
	// 菜单删除(GET)
	public static final String MENU_DELETE_URL = "https://api.weixin.qq.com/cgi-bin/menu/delete?access_token=ACCESS_TOKEN";
	// 个性化菜单接口，条件：用户标签、性别、手机操作系统、地区、语言
	// 个性化菜单创建(POST)
	public static final String MENU_CONDITIONAL_CREATE_URL = "https://api.weixin.qq.com/cgi-bin/menu/addconditional?access_token=ACCESS_TOKEN";
	// 个性化菜单删除(POST)
	public static final String MENU_CONDITIONAL_DELETE_URL = "https://api.weixin.qq.com/cgi-bin/menu/delconditional?access_token=ACCESS_TOKEN";
	// 测试个性化菜单匹配结果(POST)
	public static final String MENU_CONDITIONAL_TRYMATCH_URL = "https://api.weixin.qq.com/cgi-bin/menu/trymatch?access_token=ACCESS_TOKEN";
	// 获取自定义菜单配置接口(GET)
	public static final String MENU_INFO_GET_URL = "https://api.weixin.qq.com/cgi-bin/get_current_selfmenu_info?access_token=ACCESS_TOKEN";
	/**
	 * 创建菜单
	 * 
	 * @param menu 菜单
	 * @param accessToken 凭证
	 * @return true|成功，false|失败
	 */
	public static boolean createMenu(String accessToken, String jsonStr) {
		boolean result = false;
		String requestUrl = MENU_CREATE_URL.replace("ACCESS_TOKEN", accessToken);
		// 发起POST请求创建菜单
		JSONObject jsonObj = CommonUtil.httpsRequest(requestUrl, "POST", jsonStr);
		
		if(jsonObj != null){
			int errorCode = jsonObj.getInt("errcode");
			String errorMsg = jsonObj.getString("errmsg");
			if(errorCode == 0){
				result = true;
				logger.info("创建菜单成功");
			} else {
				result = false;
				logger.error("创建菜单失败 errcode:{} errmsg:{}", errorCode, errorMsg);
			}
		}
		
		return result;
	}
	
	/**
	 * 查询菜单
	 * 
	 * @param accessToken 凭证
	 * @return
	 */
	public static JSONObject getMenu(String accessToken) {
		String requestUrl = MENU_GET_URL.replace("ACCESS_TOKEN", accessToken);
		// 发起GET请求查询菜单
		JSONObject jsonObj = CommonUtil.httpsRequest(requestUrl, "GET", null);
		
		return jsonObj;
	}
	
	/**
	 * 删除菜单
	 * 
	 * @param accessToken 凭证
	 * @return true|成功，false|失败
	 */
	public static boolean deleteMenu(String accessToken) {
		boolean result = false;
		String requestUrl = MENU_DELETE_URL.replace("ACCESS_TOKEN", accessToken);
		// 发起GET请求删除菜单
		JSONObject jsonObj = CommonUtil.httpsRequest(requestUrl, "GET", null);
		
		if(jsonObj != null){
			int errorCode = jsonObj.getInt("errcode");
			String errorMsg = jsonObj.getString("errmsg");
			if(errorCode == 0){
				result = true;
				logger.info("删除菜单成功");
			} else {
				result = false;
				logger.error("删除菜单失败 errcode:{} errmsg:{}", errorCode, errorMsg);
			}
		}
		
		return result;
	}
	
	/**
	 * 创建个性化菜单
	 * @param accessToken
	 * @param jsonStr
	 * @return
	 */
	public static String createConditionalMenu(String accessToken, String jsonStr) {
		String result = "";
		String requestUrl = MENU_CONDITIONAL_CREATE_URL.replace("ACCESS_TOKEN", accessToken);
		// 发起POST请求创建菜单
		JSONObject jsonObj = CommonUtil.httpsRequest(requestUrl, "POST", jsonStr);
		
		if(jsonObj != null){
			if(jsonObj.containsKey("menuid")) {
				result = jsonObj.getString("menuid");
			}else{
				int errorCode = jsonObj.getInt("errcode");
				String errorMsg = jsonObj.getString("errmsg");
				logger.error("创建个性化菜单失败 errcode:{} errmsg:{}", errorCode, errorMsg);
			}
		}
		
		return result;
	}
	
	/**
	 * 删除个性化菜单
	 * 
	 * @param accessToken 凭证
	 * @param menuid 菜单id
	 * @return
	 */
	public static String deleteConditionalMenu(String accessToken, String menuid) {
		String result = "";
		String requestUrl = MENU_CONDITIONAL_DELETE_URL.replace("ACCESS_TOKEN", accessToken);
		String jsonData = "{\"menuid\" : \"%s\" }";
		// 发起请求删除个性化菜单
		JSONObject jsonObj = CommonUtil.httpsRequest(requestUrl, "POST", String.format(jsonData, menuid));
		
		if(jsonObj != null){
			int errorCode = jsonObj.getInt("errcode");
			String errorMsg = jsonObj.getString("errmsg");
			if(errorCode == 0){
				logger.info("删除个性化菜单成功");
			} else {
				logger.error("删除个性化菜单失败 errcode:{} errmsg:{}", errorCode, errorMsg);
			}
		}
		
		return result;
	}
	
	/**
	 * 测试个性化菜单匹配结果
	 * 
	 * @param accessToken 凭证
	 * @param userid:OpenID|微信号
	 * @return
	 */
	public static JSONObject tryMatch(String accessToken, String userid) {
		String requestUrl = MENU_CONDITIONAL_TRYMATCH_URL.replace("ACCESS_TOKEN", accessToken);
		String jsonData = "{\"user_id\" : \"%s\" }";
		// 发起请求删除个性化菜单
		JSONObject jsonObj = CommonUtil.httpsRequest(requestUrl, "POST", String.format(jsonData, userid));
		
		return jsonObj;
	}
	
	/**
	 * 获取自定义菜单配置接口
	 * 
	 * @param accessToken 凭证
	 * @return
	 */
	public static JSONObject getMenuInfo(String accessToken) {
		String requestUrl = MENU_INFO_GET_URL.replace("ACCESS_TOKEN", accessToken);
		// 发起GET请求查询菜单
		JSONObject jsonObj = CommonUtil.httpsRequest(requestUrl, "GET", null);
		
		return jsonObj;
	}

}
