package com.cy.wx.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.security.KeyStore;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.net.ssl.SSLContext;

import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.util.PropertyFilter;

import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.config.RequestConfig.Builder;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContexts;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import com.cy.wx.model.RedEnvelope;

public class ClientCustomSSL {
	public final static void main(String[] args) throws Exception {
        KeyStore keyStore  = KeyStore.getInstance("PKCS12");
        String password = "1382588802";
        FileInputStream instream = new FileInputStream(new File("D:/Program Files/Java/jdk1.6.0_45/lib/security/apiclient_cert.p12"));
        try {
            keyStore.load(instream, password.toCharArray());
        } finally {
            instream.close();
        }

        // Trust own CA and all self-signed certs
        SSLContext sslcontext = SSLContexts.custom()
                .loadKeyMaterial(keyStore, password.toCharArray())
                .build();
        // Allow TLSv1 protocol only
        SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(
                sslcontext,
                new String[] { "TLSv1" },
                null,
                SSLConnectionSocketFactory.BROWSER_COMPATIBLE_HOSTNAME_VERIFIER);
        CloseableHttpClient httpclient = HttpClients.custom()
                .setSSLSocketFactory(sslsf)
                .build();
        try {

            HttpPost post = new HttpPost("https://api.mch.weixin.qq.com/mmpaymkttransfers/sendredpack");
            String body = getBody();
            
            //String mimeType = "";
            /**/String charset = "UTF-8";
            Integer connTimeout = 3000;
            Integer readTimeout = 5000;
            
            if (StringUtils.isNotBlank(body)) {
				//HttpEntity entity = new StringEntity(body, ContentType.create(mimeType, charset));
				HttpEntity entity = new StringEntity(body, charset);
				post.setEntity(entity);
			}
			// 设置参数
			Builder customReqConf = RequestConfig.custom();
			if (connTimeout != null) {
				customReqConf.setConnectTimeout(connTimeout);
			}
			if (readTimeout != null) {
				customReqConf.setSocketTimeout(readTimeout);
			}
			post.setConfig(customReqConf.build());

            //System.out.println("executing request" + post.getRequestLine());

            CloseableHttpResponse response = httpclient.execute(post);
            try {
                HttpEntity entity = response.getEntity();

                //System.out.println("----------------------------------------");
                //System.out.println(response.getStatusLine());
                if (entity != null) {
                    //System.out.println("Response content length: " + entity.getContentLength());
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(entity.getContent()));
                    String text;
                    StringBuffer buffer = new StringBuffer();
                    while ((text = bufferedReader.readLine()) != null) {
                    	buffer.append(text);
                        System.out.println(text);
                    }
                   
                }
                EntityUtils.consume(entity);
            } finally {
                response.close();
            }
        } finally {
            httpclient.close();
        }
    }
	
	public static String getBody() throws Exception{
        String nonce_str = CommonUtil.random(30);
        String mch_id = "1382588802";
        String mch_billno = mch_id + String.valueOf(System.currentTimeMillis()) + CommonUtil.random(4);
        String wxappid = "wx63457c110481fdf2";
        String send_name = "天龙8部";
        String re_openid = "oR8rRvhSBAkn07aeeEmtTAg_ob8w"; // 沉睡的森林
        int total_amount = 100;
        int total_num = 1;
        String wishing = "大SASA赛高";
        String client_ip = "111.206.12.22";
        String act_name = "TesT活动";
        String remark = "test的remark";
        String scene_id = null;
        String risk_info = null;
        String consume_mch_id = null;
        String sign = null;
        String apikey = "tlactivitychangyoucomwxapikey487";
        RedEnvelope red = new RedEnvelope();
        red.setNonce_str(nonce_str);
        red.setSign(sign);
        red.setMch_billno(mch_billno);
        red.setMch_id(mch_id);
        red.setWxappid(wxappid);
        red.setSend_name(send_name);
        red.setRe_openid(re_openid);
        red.setTotal_amount(total_amount);
        red.setTotal_num(total_num);
        red.setWishing(wishing);
        red.setClient_ip(client_ip);
        red.setAct_name(act_name);
        red.setRemark(remark);
        red.setScene_id(scene_id);
        red.setRisk_info(risk_info);
        red.setConsume_mch_id(consume_mch_id);
        JsonConfig jsonConfig = new JsonConfig();
	    PropertyFilter filter = new PropertyFilter() {
			public boolean apply(Object object, String fieldName,
					Object fieldValue) {
				return null == fieldValue || "".equals(fieldValue); // 过滤空值
			}
	    };
	    jsonConfig.setJsonPropertyFilter(filter);
	    JSONObject jsonObj = JSONObject.fromObject(red, jsonConfig);
	    //System.out.println(jsonObj.toString());
	    @SuppressWarnings("rawtypes")
		Iterator iterator = jsonObj.keys();
	    String key = "";
	    String value = "";
	    List<String> list = new ArrayList<String>();
	    StringBuilder strBuil = new StringBuilder();
	    while(iterator.hasNext()){
	    	key = (String)iterator.next();
	    	value = jsonObj.getString(key);
	    	strBuil.append(key).append("=").append(value).append("&");
	    	list.add(strBuil.toString());
	    	//strBuil.setLength(0);
	    }
	    strBuil.append("key=").append(apikey);
        //System.out.println(strBuil);
        sign = MD5Util.getMD5(strBuil.toString());
        //System.out.println(sign);
        red.setSign(sign);
        String responseXml = MsgUtil.msgToXml(red);
        System.out.println(responseXml);
        
        return responseXml;
    }
}
