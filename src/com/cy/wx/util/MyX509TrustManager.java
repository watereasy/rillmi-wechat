package com.cy.wx.util;

import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.X509TrustManager;

/**
 * ClassName: MyX509TrustManager
 * 
 * @Description: 信任管理器
 * @date 2014-12-30
 */
public class MyX509TrustManager implements X509TrustManager {

	/** 检查客户端证书 */
	public void checkClientTrusted(X509Certificate[] chain, String authType)
			throws CertificateException {
	}

	/** 检查服务器端证书 */
	public void checkServerTrusted(X509Certificate[] chain, String authType)
			throws CertificateException {
	}

	/** 返回受信任的X509证书数组 */
	public X509Certificate[] getAcceptedIssuers() {
		return null;
	}
}
