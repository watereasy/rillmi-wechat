package com.cy.wx.util;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;

import net.sf.json.JSONArray;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cy.wx.model.Token;

public class CommonUtil {

	protected static Logger logger = LoggerFactory.getLogger(CommonUtil.class);

	/** 凭证获取(GET)*/
	public final static String TOKEN_URL = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";
	/** 微信服务器IP地址列表(GET) */
	public final static String WXIP_URL = "https://api.weixin.qq.com/cgi-bin/getcallbackip?access_token=ACCESS_TOKEN";
	/** 上传图片(POST) */
	public final static String UPLOADIMG_URL = "https://api.weixin.qq.com/cgi-bin/media/uploadimg?access_token=ACCESS_TOKEN";

	/**
	 * 发送https请求
	 * 
	 * @param requestUrl
	 *            请求地址
	 * @param requestMethod
	 *            请求方式（GET、POST）
	 * @param outputStr
	 *            提交的数据
	 * @return JSONObject(通过JSONObject.get(key)的方式获取json对象的属性值)
	 */
	public static JSONObject httpsRequest(String requestUrl,
			String requestMethod, String outputStr) {
		JSONObject jsonObject = null;
		try {
			// 创建SSLContext对象，并使用我们指定的信任管理器初始化
			TrustManager[] tm = { new MyX509TrustManager() };
			SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");
			sslContext.init(null, tm, new java.security.SecureRandom());
			// 从上述SSLContext对象中得到SSLSocketFactory对象
			SSLSocketFactory ssf = sslContext.getSocketFactory();

			URL url = new URL(requestUrl);
			HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
			conn.setSSLSocketFactory(ssf);

			conn.setDoOutput(true);
			conn.setDoInput(true);
			conn.setUseCaches(false);
			// 设置请求方式（GET/POST）
			conn.setRequestMethod(requestMethod);

			// 当outputStr不为null时向输出流写数据
			if (null != outputStr) {
				OutputStream outputStream = conn.getOutputStream();
				// 注意编码格式
				outputStream.write(outputStr.getBytes("UTF-8"));
				outputStream.close();
			}

			// 从输入流读取返回内容
			InputStream inputStream = conn.getInputStream();
			InputStreamReader inputStreamReader = new InputStreamReader(
					inputStream, "utf-8");
			BufferedReader bufferedReader = new BufferedReader(
					inputStreamReader);
			String str = null;
			StringBuffer buffer = new StringBuffer();
			while ((str = bufferedReader.readLine()) != null) {
				buffer.append(str);
			}

			// 释放资源
			bufferedReader.close();
			inputStreamReader.close();
			inputStream.close();
			inputStream = null;
			conn.disconnect();
			jsonObject = JSONObject.fromObject(buffer.toString());
		} catch (ConnectException ce) {
			logger.error("连接超时：{}", ce);
		} catch (Exception e) {
			logger.error("https请求异常：{}", e);
		}
		return jsonObject;
	}
	
	/**
	 * 获取素材http请求
	 * @param requestUrl
	 * @param requestMethod
	 * @param outputStr
	 * @param mediaId
	 * @param downloadPath
	 * @return
	 */
	public static JSONObject httpsRequestMaterial(String requestUrl,
			String requestMethod, String outputStr, String mediaId, String downloadPath) {
		JSONObject jsonObject = null;
		try {
			// 创建SSLContext对象，并使用我们指定的信任管理器初始化
			TrustManager[] tm = { new MyX509TrustManager() };
			SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");
			sslContext.init(null, tm, new java.security.SecureRandom());
			// 从上述SSLContext对象中得到SSLSocketFactory对象
			SSLSocketFactory ssf = sslContext.getSocketFactory();
			
			URL url = new URL(requestUrl);
			HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
			conn.setSSLSocketFactory(ssf);
			
			conn.setDoOutput(true);
			conn.setDoInput(true);
			conn.setUseCaches(false);
			// 设置请求方式（GET/POST）
			conn.setRequestMethod(requestMethod);
			
			// 当outputStr不为null时向输出流写数据
			if (null != outputStr) {
				OutputStream outputStream = conn.getOutputStream();
				// 注意编码格式
				outputStream.write(outputStr.getBytes("UTF-8"));
				outputStream.close();
			}
			
			// 从输入流读取返回内容
			InputStream inputStream = conn.getInputStream();
			String streamType = "";
			BufferedInputStream bis = new BufferedInputStream(conn.getInputStream());
			// 在输入流标注当前位置
			bis.mark(bis.available() + 1);
			// 判断文件流类型
			FileType fileType = FileTypeJudge.getType2(bis);
			streamType = fileType == null ? "" : fileType.toString();
			// 重新读取输入流
			bis.reset();
			StringBuffer buffer = new StringBuffer();
			if(StringUtils.isNotBlank(streamType)){
				String filePath = downloadPath + mediaId + "." + streamType;

				// 将微信服务器返回的输入流写入文件
				FileOutputStream fos = new FileOutputStream(new File(filePath));
				byte[] buf = new byte[1024*8];
				int size = 0;
				while ((size = bis.read(buf)) != -1)
					fos.write(buf, 0, size);
				// 释放资源
				fos.close();
			}else{
				InputStreamReader inputStreamReader = new InputStreamReader(
						bis, "utf-8");
				BufferedReader bufferedReader = new BufferedReader(
						inputStreamReader);
				String str = null;
				while ((str = bufferedReader.readLine()) != null) {
					buffer.append(str);
				}
				// 释放资源
				bufferedReader.close();
				inputStreamReader.close();
			}
			bis.close();
			inputStream.close();
			inputStream = null;
			conn.disconnect();
			jsonObject = JSONObject.fromObject(buffer.length() == 0 ? buffer : buffer.toString());
		} catch (ConnectException ce) {
			logger.error("连接超时：{}", ce);
		} catch (Exception e) {
			logger.error("https请求异常：{}", e);
		}
		return jsonObject;
	}

	/**
	 * 获取接口访问凭证
	 * 
	 * @param appid
	 *            凭证
	 * @param appsecret
	 *            密钥
	 * @return
	 */
	public static Token getToken(String appid, String appsecret) {
		Token token = null;
		String requestUrl = TOKEN_URL.replace("APPID", appid).replace(
				"APPSECRET", appsecret);
		// 发起GET请求获取凭证
		JSONObject jsonObject = httpsRequest(requestUrl, "GET", null);

		if (null != jsonObject) {
			try {
				token = new Token();
				token.setAccessToken(jsonObject.getString("access_token"));
				token.setExpiresIn(jsonObject.getInt("expires_in"));
			} catch (JSONException e) {
				token = null;
				// 获取token失败
				logger.error("获取token失败 errcode:{} errmsg:{}",
						jsonObject.getInt("errcode"),
						jsonObject.getString("errmsg"));
			}
		}
		return token;
	}

	/**
	 * URL编码（utf-8）
	 * 
	 * @param source
	 * @return
	 */
	public static String urlEncodeUTF8(String source) {
		String result = source;
		try {
			result = java.net.URLEncoder.encode(source, "utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 根据内容类型判断文件扩展名
	 * 
	 * @param contentType
	 *            内容类型
	 * @return
	 */
	public static String getFileExt(String contentType) {
		String fileExt = "";
		if ("image/jpeg".equals(contentType))
			fileExt = ".jpg";
		else if ("audio/mpeg".equals(contentType))
			fileExt = ".mp3";
		else if ("audio/amr".equals(contentType))
			fileExt = ".amr";
		else if ("video/mp4".equals(contentType))
			fileExt = ".mp4";
		else if ("video/mpeg4".equals(contentType))
			fileExt = ".mp4";
		return fileExt;
	}
	
	/**
	 * @param accessToken
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static List<String> getWXIpList(String accessToken) {
		List<String> list = new ArrayList<String>();
		String requestUrl = WXIP_URL.replace("ACCESS_TOKEN", accessToken);
		JSONObject jsonObject = httpsRequest(requestUrl, "GET", null);
		if(jsonObject != null) {
			try {
				JSONArray jsonArray = jsonObject.getJSONArray("ip_list");
				list = JSONArray.toList(jsonArray, new String(), new JsonConfig());
			} catch (JSONException e) {
				logger.error("微信服务器IP地址列表失败 errcode:{} errmsg:{}",
						jsonObject.getInt("errcode"),
						jsonObject.getString("errmsg"));
			}
		}
		
		return list;
	}
	
	/**
	 * 上传到微信媒体文件
	 * @param uploadMediaUrl
	 * @param mediaFileUrl
	 * @param outputStr
	 * @return
	 */
	public static String uploadWX(String uploadMediaUrl, String mediaFileUrl, String outputStr) {
		StringBuffer buffer = new StringBuffer();
		// 定义数据分隔符
		String boundary = "------------7da2e536604c8";
		try {
			URL uploadUrl = new URL(uploadMediaUrl);
			HttpURLConnection uploadConn = (HttpURLConnection) uploadUrl.openConnection();
			uploadConn.setDoOutput(true);
			uploadConn.setDoInput(true);
			uploadConn.setRequestMethod("POST");
			// 设置请求头Content-Type
			uploadConn.setRequestProperty("Content-Type", "multipart/form-data;boundary=" + boundary);
			// 获取媒体文件上传的输出流（往微信服务器写数据）
			OutputStream outputStream = uploadConn.getOutputStream();
			
			URL mediaUrl = new URL(mediaFileUrl);
			HttpURLConnection meidaConn = (HttpURLConnection) mediaUrl.openConnection();
			meidaConn.setDoOutput(true);
			meidaConn.setRequestMethod("GET");
			
			// 从请求头中获取内容类型
			String contentType = meidaConn.getHeaderField("Content-Type");
			// 根据内容类型判断文件扩展名
			String fileExt = CommonUtil.getFileExt(contentType);
			// 请求体开始
			outputStream.write(("--" + boundary + "\r\n").getBytes());
			outputStream.write(String.format("Content-Disposition: form-data; name=\"media\"; filename=\"file1%s\"\r\n", fileExt).getBytes());
			outputStream.write(String.format("Content-Type: %s\r\n\r\n", contentType).getBytes());
			
			// 获取媒体文件的输入流（读取文件）
			BufferedInputStream bis = new BufferedInputStream(meidaConn.getInputStream());
			byte[] buf = new byte[8096];
			int size = 0;
			while ((size = bis.read(buf)) != -1) {
				// 将媒体文件写到输出流（往微信服务器写数据）
				outputStream.write(buf, 0, size);
			}
			// 请求体结束
			outputStream.write(("\r\n--" + boundary + "--\r\n").getBytes());
			
			// 当outputStr不为null时向输出流写数据
			if (null != outputStr) {
				// 注意编码格式
				outputStream.write(outputStr.getBytes("UTF-8"));
			}
			
			outputStream.close();
			bis.close();
			meidaConn.disconnect();
			
			// 获取媒体文件上传的输入流（从微信服务器读数据）
			InputStream inputStream = uploadConn.getInputStream();
			InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");
			BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
			String str = null;
			while ((str = bufferedReader.readLine()) != null) {
				buffer.append(str);
			}
			bufferedReader.close();
			inputStreamReader.close();
			// 释放资源
			inputStream.close();
			inputStream = null;
			uploadConn.disconnect();
		} catch (Exception e) {
			logger.error("上传媒体文件失败：{}", e);
		}
		return buffer.toString();
	}
	
	/**
	 * 上传图片
	 * 上传的图片限制文件大小限制1MB，支持jpg/png格式
	 * @param accessToken
	 * @param mediaFileUrl
	 * @return
	 */
	public static String uploadImg(String accessToken, String mediaFileUrl){
		String responseUrl = "";
		String uploadMediaUrl = UPLOADIMG_URL.replace("ACCESS_TOKEN", accessToken);
		JSONObject jsonObj = JSONObject.fromObject(uploadWX(uploadMediaUrl, mediaFileUrl, null));
		if (null != jsonObj) {
			if(jsonObj.containsKey("url")){
				responseUrl = jsonObj.getString("url");
				logger.info("上传图片成功sourceUrl:{}, targetUrl", mediaFileUrl, responseUrl);
			}else{
				int errorCode = jsonObj.getInt("errcode");
				String errorMsg = jsonObj.getString("errmsg");
				logger.error("sourceUrl:{}", mediaFileUrl);
				logger.error("上传图片失败errcode:{}, errmsg{}", errorCode, errorMsg);
			}
		}
		
		return responseUrl;
	}
	
	/**
	 * @description 随即生成指定位数的含验证码字符串
	 * @param bit 指定生成验证码位数
	 * @return
	 */
	public static String random(int bit) {
		if (bit <= 0)
			bit = 6; // 默认6位
		// 因为o和0,l和1很难区分,所以,去掉大小写的o和l
		String str = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijkmnpqrstuvwxyz";// 初始化种子
		return RandomStringUtils.random(bit, str);// 返回6位的字符串
	}
	
	/**
	 * 得到一个字符串的长度,显示的长度,一个汉字或日韩文长度为2,英文字符长度为1
	 * 
	 * @param String
	 *            s 需要得到长度的字符串
	 * @return int 得到的字符串长度
	 */
	public static int length(String s) {
		if (s == null)
			return 0;
		char[] c = s.toCharArray();
		int len = 0;
		for (int i = 0; i < c.length; i++) {
			len++;
			if (!isLetter(c[i])) {
				len++;
			}
		}
		return len;
	}
	
	public static boolean isLetter(char c) {
		int k = 0x80;
		return c / k == 0 ? true : false;
	}
	
	public static void main(String[] args) {
		System.out.println(length("我的4界~"));
	}
}
