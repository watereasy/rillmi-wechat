package com.cy.wx.util;

import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.cy.wx.model.Token;
import comm.CommUtil;
import comm.Constant;
import comm.InitUtil;

public class CommonUtilTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		InitUtil.initAccessToken();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		
	}

	@Before
	public void setUp() throws Exception {
		
	}

	@After
	public void tearDown() throws Exception {
		
	}

	@Test
	public void testGetToken() throws Exception{
		long createTime = Long.valueOf(CommUtil.getProperties(Constant.CREATE_TIME));
		if(System.currentTimeMillis() - createTime > 7000*1000){
			Token token = CommonUtil.getToken(Constant.APPID, Constant.APPSECRET);
			CommUtil.setProperties("accessToken", token.getAccessToken());
			CommUtil.setProperties("createTime", String.valueOf(System.currentTimeMillis()));
		}
		
		System.out.println("tokenAccess:" + CommUtil.getProperties(Constant.ACCESS_TOKEN));
		System.out.println("createTime:" + CommUtil.getProperties(Constant.CREATE_TIME));
		//tokenAccess = token.getAccessToken();
	}
	
	@Test
	public void testGetWXIpList() throws Exception{
		List<String> ipList = CommonUtil.getWXIpList(InitUtil.accessToken);
		System.out.println("微信IP列表：" + ipList.toString());
	}

}
