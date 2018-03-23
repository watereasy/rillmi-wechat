package com.cy.wx.util;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ConnectException;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;

import net.sf.json.JSONException;
import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cy.wx.model.JSapiTicket;
import com.cy.wx.model.Token;

public class JSUtil {
	protected static Logger logger = LoggerFactory.getLogger(JSUtil.class);
	
	private static final String ACCESS_TOKEN_URL = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";
	
	private static final String JSAPI_TICKET = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token=ACCESS_TOKEN&type=jsapi";
	
	private static final String JS_SIGNATURE = "jsapi_ticket=JSAPI_TICKET&noncestr=NONCESTR&timestamp=TIMESTAMP&url=URL";
	
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
		String requestUrl = ACCESS_TOKEN_URL.replace("APPID", appid).replace(
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
				logger.error("获取token失败 errcode:{} errmsg:{}"
						+ jsonObject.getInt("errcode")
						+ jsonObject.getString("errmsg"));
			}
		}
		return token;
	}
	
	public static JSapiTicket getTicket(String accessToken) {
		JSapiTicket ticket = null;
		String requestUrl = JSAPI_TICKET.replace("ACCESS_TOKEN", accessToken);
		JSONObject jsonObject = httpsRequest(requestUrl, "GET", null);
		
		if (null != jsonObject) {
			try {
				ticket = new JSapiTicket();
				ticket.setErrCode(jsonObject.getString("errcode"));
				ticket.setErrMsg(jsonObject.getString("errmsg"));
				ticket.setTicket(jsonObject.getString("ticket"));
				ticket.setExpiresIn(jsonObject.getInt("expires_in"));
			} catch (JSONException e) {
				ticket = null;
				// 获取token失败
				logger.error("获取token失败 errcode:{} errmsg:{}"
						+ jsonObject.getInt("errcode")
						+ jsonObject.getString("errmsg"));
			}
		}
		
		return ticket;
	}
	
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
	private static JSONObject httpsRequest(String requestUrl,
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
	
	public static String getJSignature(String jsapi_ticket, String nonce, String timestamp, String url) {
		String str = JS_SIGNATURE.replace("JSAPI_TICKET", jsapi_ticket).replace("NONCESTR", nonce).replace("TIMESTAMP", timestamp).replace("URL", url);
		String ciphertext = "";
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-1");
			// 对接后的字符串进行sha1加密
			byte[] digest = md.digest(str.toString().getBytes());
			ciphertext = byteToStr(digest);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}

		return ciphertext;
	}
	
	/**
	 * 将字节数组转换为十六进制字符串
	 * 
	 * @param byteArray
	 * @return
	 */
	private static String byteToStr(byte[] byteArray) {
		String strDigest = "";
		for (int i = 0; i < byteArray.length; i++) {
			strDigest += byteToHexStr(byteArray[i]);
		}
		return strDigest;
	}

	/**
	 * 将字节转换为十六进制字符串
	 * 
	 * @param mByte
	 * @return
	 */
	private static String byteToHexStr(byte mByte) {
		char[] Digit = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A',
				'B', 'C', 'D', 'E', 'F' };
		char[] tempArr = new char[2];
		tempArr[0] = Digit[(mByte >>> 4) & 0X0F];
		tempArr[1] = Digit[mByte & 0X0F];

		String s = new String(tempArr);
		return s;
	}
	

	public static void main(String[] args) {
		String appId = "wx2a2ffa820da431ff"; // 公众号提供
		String appSecret = "0d5486069cd321351cc30354c9bad5fb"; // 公众号提供
		Token token = JSUtil.getToken(appId, appSecret); // accessToken需要缓存，默认2小时，过期前请自动更新
		JSapiTicket ticket = JSUtil.getTicket(token.getAccessToken()); // 需要和accessToken同步更新
		
		String nonce = "Wm3WZYTPz0wzccnW"; // 随机字符串
		String timestamp = "1414587457"; // 时间戳
		String url = "http://mp.weixin.qq.com"; // 当前网页的URL，不包含#及其后面部分
		String signature = JSUtil.getJSignature(ticket.getTicket(), nonce, timestamp, url);
		System.out.println("signature:" + signature);
		//TODO 返回前台页面的参数：appId、timestamp、nonce、signature
	}

}
