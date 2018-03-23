package com.cy.wx.test;

import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.util.PropertyFilter;

import com.cy.wx.model.CustomAccount;
import com.cy.wx.util.AdvancedUtil;

public class Test {

	public static void main(String[] args) {
		test01();
	}
	
	public static void test01() {
		JsonConfig jsonConfig = new JsonConfig();
	    PropertyFilter filter = new PropertyFilter() {
			public boolean apply(Object object, String fieldName,
					Object fieldValue) {
				return null == fieldValue; // 过滤空值
			}
	    };
	    jsonConfig.setJsonPropertyFilter(filter);
		
		String accessToken = "DsvYODioXk2gw0ZuVSjlYQV0Sp4Qa6CMYBtu-9NYe1xJhnNREh8k82x6-hPyc28xB_6rH1K47J84QDdb-GChQ-uhKaelpa8GFKzUzrcTbSM";
		CustomAccount ca = new CustomAccount();
		ca.setKf_account("test1@test");
		ca.setNickname("客服1");
		ca.setPassword("pswmd5");
		String jsonMsg = JSONObject.fromObject(ca, jsonConfig).toString();
		System.out.println(jsonMsg);
		boolean result = AdvancedUtil.createCustomAccount(accessToken, jsonMsg);
		System.out.println(result);
	
	}
	
	// 过滤对象中空域
	public static void test02() {
		JsonConfig jsonConfig = new JsonConfig();
	    PropertyFilter filter = new PropertyFilter() {
			public boolean apply(Object object, String fieldName,
					Object fieldValue) {
				return null == fieldValue;
			}
	    };
	    jsonConfig.setJsonPropertyFilter(filter);
	}

}
