package com.cy.wx.util;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.util.PropertyFilter;

import org.junit.Test;

import com.cy.wx.model.RedEnvelope;

public class RedEnvelopeDemo {

	/**
     * 测试方法.
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        // 密码
        String password = "1382588802";
        // 密钥库
        String keyStorePath = "";
        // 信任库
        String trustStorePath = "C:/Users/zhangjianhui/Downloads/cert/apiclient_cert.p12";
        // 本地起的https服务
        String httpsUrl = "https://api.mch.weixin.qq.com/mmpaymkttransfers/sendredpack";
        // 传输文本
        String xmlStr = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><fruitShop><fruits><fruit><kind>萝卜</kind></fruit><fruit><kind>菠萝</kind></fruit></fruits></fruitShop>";
        HttpsPost.initHttpsURLConnection(password, keyStorePath, trustStorePath);
        // 发起请求
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
	    System.out.println(jsonObj.toString());
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
        System.out.println(strBuil);
        sign = MD5Util.getMD5(strBuil.toString());
        System.out.println(sign);
        red.setSign(sign);
        String responseXml = MsgUtil.msgToXml(red);
        System.out.println(responseXml);
        //HttpsPost.post(httpsUrl, responseXml);
    }
    
    @Test
    public void test01() throws Exception{
    	// 密码
        String password = "1382588802";
        // 密钥库
        String keyStorePath = "";
        // 信任库
        String trustStorePath = "D:/Program Files/Java/jdk1.6.0_45/lib/security/apiclient_cert.p12";
        // 本地起的https服务
        String httpsUrl = "https://api.mch.weixin.qq.com/mmpaymkttransfers/sendredpack";
        // 传输文本
        String xmlStr = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><fruitShop><fruits><fruit><kind>萝卜</kind></fruit><fruit><kind>菠萝</kind></fruit></fruits></fruitShop>";
        //HttpsPost.initHttpsURLConnection(password, keyStorePath, trustStorePath);
        // 发起请求
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
        System.out.println(strBuil);
        sign = MD5Util.getMD5(strBuil.toString());
        System.out.println(sign);
        red.setSign(sign);
        String responseXml = MsgUtil.msgToXml(red);
        System.out.println(responseXml);
        //HttpsPost.post(httpsUrl, responseXml);
    }
}
