package com.cy.wx.util;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cy.wx.model.WeixinMedia;

/**
 * 素材管理
 * @author zhangjianhui
 *
 */
public class MaterialUtil {

	protected static Logger logger = LoggerFactory.getLogger(MaterialUtil.class);
	
	/**
	 * 新增临时素材
	 * 图片（image）: 1M，支持JPG格式
	 * 语音（voice）：2M，播放长度不超过60s，支持AMR\MP3格式
	 * 视频（video）：10MB，支持MP4格式
	 * 缩略图（thumb）：64KB，支持JPG格式  
	 * 时效3天
	 * @param accessToken 接口访问凭证
	 * @param type 媒体文件类型（image、voice、video和thumb）
	 * @param mediaFileUrl 媒体文件的url
	 */
	public static WeixinMedia addMedia(String accessToken, String type, String mediaFileUrl) {
		WeixinMedia weixinMedia = null;
		// 拼装请求地址
		String requestUrl = "https://api.weixin.qq.com/cgi-bin/media/upload?access_token=ACCESS_TOKEN&type=TYPE";
		requestUrl = requestUrl.replace("ACCESS_TOKEN", accessToken).replace("TYPE", type);
		// 使用JSON解析返回结果
		JSONObject jsonObj = JSONObject.fromObject(CommonUtil.uploadWX(requestUrl, mediaFileUrl, null));
		if (null != jsonObj) {
			if(jsonObj.containsKey("media_id")){
				weixinMedia = new WeixinMedia();
				weixinMedia.setType(jsonObj.getString("type"));
				weixinMedia.setMediaId(jsonObj.getString("media_id"));
				weixinMedia.setCreatedAt(jsonObj.getLong("created_at"));
				
			}else{
				int errorCode = jsonObj.getInt("errcode");
				String errorMsg = jsonObj.getString("errmsg");
				logger.error("上传媒体文件失败 errcode:{} | errmsg:{}", errorCode, errorMsg);
			}
		}
		
		return weixinMedia;
	}
	
	/**
	 * 获取临时素材
	 * 
	 * @param accessToken 接口访问凭证
	 * @param mediaId 媒体文件标识
	 * @param savePath 文件在服务器上的存储路径
	 * @return
	 */
	public static String getMedia(String accessToken, String mediaId, String savePath) {
		String filePath = null;
		// 拼接请求地址
		//String requestUrl = "http://file.api.weixin.qq.com/cgi-bin/media/get?access_token=ACCESS_TOKEN&media_id=MEDIA_ID";
		String requestUrl = "https://api.weixin.qq.com/cgi-bin/media/get?access_token=ACCESS_TOKEN&media_id=MEDIA_ID";
		requestUrl = requestUrl.replace("ACCESS_TOKEN", accessToken).replace("MEDIA_ID", mediaId);
		try {
			URL url = new URL(requestUrl);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setDoInput(true);
			conn.setRequestMethod("GET");

			if (!savePath.endsWith("/")) {
				savePath += "/";
			}
			// 根据内容类型获取扩展名
			String fileExt = CommonUtil.getFileExt(conn.getHeaderField("Content-Type"));
			// 将mediaId作为文件名
			filePath = savePath + mediaId + fileExt;

			BufferedInputStream bis = new BufferedInputStream(conn.getInputStream());
			FileOutputStream fos = new FileOutputStream(new File(filePath));
			byte[] buf = new byte[8096];
			int size = 0;
			while ((size = bis.read(buf)) != -1)
				fos.write(buf, 0, size);
			fos.close();
			bis.close();

			conn.disconnect();
			logger.info("下载媒体文件成功，filePath:{}", filePath);
		} catch (Exception e) {
			filePath = null;
			logger.error("下载媒体文件失败：{}", e);
		}
		
		return filePath;
	}
	
	/**
	 * 新增永久图文素材
	 * @param accessToken
	 * @param jsonData
	 * @return
	 */
	public static WeixinMedia addNews(String accessToken, String jsonData) {
		WeixinMedia weixinMedia = null;
		// 拼装请求地址
		String requestUrl = "https://api.weixin.qq.com/cgi-bin/material/add_news?access_token=ACCESS_TOKEN";
		requestUrl = requestUrl.replace("ACCESS_TOKEN", accessToken);
		JSONObject jsonObj = CommonUtil.httpsRequest(requestUrl, "POST", jsonData);
		if (null != jsonObj) {
			if (jsonObj.containsKey("media_id")) {
				weixinMedia = new WeixinMedia();
				weixinMedia.setMediaId(jsonObj.getString("media_id"));
				logger.info("新增永久图文素材成功 media_id:{}", jsonObj.getString("media_id"));
			} else {
				int errorCode = jsonObj.getInt("errcode");
				String errorMsg = jsonObj.getString("errmsg");
				logger.error("新增永久图文素材失败 errcode:{} errmsg:{}", errorCode,
						errorMsg);
			}
		}

		return weixinMedia;
	}
	
	/**
	 * 新增其他类型永久素材
	 * @param accessToken
	 * @param type
	 * @param mediaFileUrl
	 * @return
	 */
	public static WeixinMedia addMaterial(String accessToken, String mediaFileUrl, String jsonData) {
		WeixinMedia weixinMedia = null;
		// 拼装请求地址
		String requestUrl = "https://api.weixin.qq.com/cgi-bin/material/add_material?access_token=ACCESS_TOKEN&type=TYPE";
		requestUrl = requestUrl.replace("ACCESS_TOKEN", accessToken);
		// 使用JSON解析返回结果
		JSONObject jsonObj = JSONObject.fromObject(CommonUtil.uploadWX(requestUrl, mediaFileUrl, jsonData));
		if (null != jsonObj) {
			if(jsonObj.containsKey("media_id")){
				weixinMedia = new WeixinMedia();
				weixinMedia.setMediaId(jsonObj.getString("media_id"));
				logger.info("新增其他类型永久素材成功 media_id:{} | url(为图片时才有):{}", jsonObj.getString("media_id"), jsonObj.get("url"));
			}else{
				int errorCode = jsonObj.getInt("errcode");
				String errorMsg = jsonObj.getString("errmsg");
				logger.error("新增其他类型永久素材失败 errcode:{} | errmsg:{}", errorCode, errorMsg);
			}
		}
		
		return weixinMedia;
	}
	
	/**
	 * 获取永久素材
	 * @param accessToken
	 * @param mediaId
	 * @param downloadPath 下载路径
	 */
	public static JSONObject getMaterial(String accessToken, String mediaId, String downloadPath){
		// 拼装请求地址
		String requestUrl = "https://api.weixin.qq.com/cgi-bin/material/get_material?access_token=ACCESS_TOKEN";
		requestUrl = requestUrl.replace("ACCESS_TOKEN", accessToken);
		String jsonData = "{\"media_id\":\"%s\"}";
		JSONObject jsonObj = CommonUtil.httpsRequestMaterial(requestUrl, "POST", String.format(jsonData, mediaId), mediaId, downloadPath);
		
		return jsonObj;
	}
	
	/**
	 * 删除永久素材
	 * @param accessToken
	 * @param jsonData
	 * @return
	 */
	public static boolean delMaterial(String accessToken, String mediaId) {
		boolean result = false;
		// 拼装请求地址
		String requestUrl = "https://api.weixin.qq.com/cgi-bin/material/del_material?access_token=ACCESS_TOKEN";
		requestUrl = requestUrl.replace("ACCESS_TOKEN", accessToken);
		String jsonData = "{\"media_id\":\"%s\"}";
		JSONObject jsonObj = CommonUtil.httpsRequest(requestUrl, "POST", String.format(jsonData, mediaId));
		if (null != jsonObj) {
			int errorCode = jsonObj.getInt("errcode");
			String errorMsg = jsonObj.getString("errmsg");
			if (errorCode == 0) {
				result = true;
				logger.info("删除永久素材成功 media_id:{}", mediaId);
			} else {
				logger.error("删除永久素材失败 errcode:{} errmsg:{}", errorCode,
						errorMsg);
			}
		}

		return result;
	}
	
	/**
	 * 修改永久图文素材
	 * @param accessToken
	 * @param jsonData
	 * @return
	 */
	public static boolean updateNews(String accessToken, String jsonData) {
		boolean result = false;
		// 拼装请求地址
		String requestUrl = "https://api.weixin.qq.com/cgi-bin/material/update_news?access_token=ACCESS_TOKEN";
		requestUrl = requestUrl.replace("ACCESS_TOKEN", accessToken);
		JSONObject jsonObj = CommonUtil.httpsRequest(requestUrl, "POST", jsonData);
		if (null != jsonObj) {
			int errorCode = jsonObj.getInt("errcode");
			String errorMsg = jsonObj.getString("errmsg");
			if (errorCode == 0) {
				result = true;
				logger.info("修改永久图文素材成功");
			} else {
				logger.error("修改永久图文素材失败 errcode:{} errmsg:{}", errorCode,
						errorMsg);
			}
		}

		return result;
	}
	
	/**
	 * 获取素材总数
	 * @param accessToken
	 * @return
	 */
	public static JSONObject getMaterialCount(String accessToken){
		String requestUrl = "https://api.weixin.qq.com/cgi-bin/material/get_materialcount?access_token=ACCESS_TOKEN";
		requestUrl = requestUrl.replace("ACCESS_TOKEN", accessToken);
		JSONObject jsonObj = CommonUtil.httpsRequest(requestUrl, "GET", null);
		
		return jsonObj;
	}
	
	/**
	 * 获取素材列表
	 * @param accessToken
	 * @param type 图片(image)、视频(video)、语音 (voice)、图文(news)
	 * @param offset 从全部素材的该偏移位置开始返回，0表示从第一个素材 返回
	 * @param count 返回素材的数量，取值在1到20之间
	 * @return
	 */
	public static JSONObject batchGetMaterial(String accessToken, String type, int offset, int count){
		// 拼装请求地址
		String requestUrl = "https://api.weixin.qq.com/cgi-bin/material/batchget_material?access_token=ACCESS_TOKEN";
		requestUrl = requestUrl.replace("ACCESS_TOKEN", accessToken);
		String jsonData = "{\"type\":\"%s\", \"offset\":%d, \"count\":%d}";
		JSONObject jsonObj = CommonUtil.httpsRequest(requestUrl, "POST", String.format(jsonData, type, offset, count));
		
		return jsonObj;
	}
	
}
