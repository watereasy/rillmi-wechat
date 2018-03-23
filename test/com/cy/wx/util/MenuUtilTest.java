package com.cy.wx.util;

import java.util.ArrayList;

import net.sf.json.JSONObject;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.cy.wx.menu.Button;
import com.cy.wx.menu.ClickButton;
import com.cy.wx.menu.ComplexButton;
import com.cy.wx.menu.ConditionalMenu;
import com.cy.wx.menu.MatchRule;
import com.cy.wx.menu.Menu;
import com.cy.wx.menu.OtherButton;
import com.cy.wx.menu.ViewButton;
import comm.InitUtil;

public class MenuUtilTest {

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
	public void testCreateMenu() {
		ClickButton btn11 = new ClickButton();
		btn11.setName("阿里巴巴");
		btn11.setType("click");
		btn11.setKey("alibaba");
		ClickButton btn12 = new ClickButton();
		btn12.setName("IBM");
		btn12.setType("click");
		btn12.setKey("ibm");
		ClickButton btn13 = new ClickButton();
		btn13.setName("CSDN");
		btn13.setType("click");
		btn13.setKey("csdn");
		
		ViewButton btn21 = new ViewButton();
		btn21.setName("百度");
		btn21.setType("view");
		btn21.setUrl("http://www.soso.com/");
		ViewButton btn22 = new ViewButton();
		btn22.setName("Google");
		btn22.setType("view");
		btn22.setUrl("http://www.soso.com/");
		ViewButton btn23 = new ViewButton();
		btn23.setName("Hao123");
		btn23.setType("view");
		btn23.setUrl("http://www.soso.com/");
		ViewButton btn24 = new ViewButton();
		btn24.setName("RillMi");
		btn24.setType("view");
		btn24.setUrl("http://www.soso.com/");
		ViewButton btn25 = new ViewButton();
		btn25.setName("搜狗");
		btn25.setType("view");
		btn25.setUrl("http://www.soso.com/");
		
		ViewButton btn31 = new ViewButton();
		btn31.setName("魔兽");
		btn31.setType("view");
		btn31.setUrl("http://www.soso.com/");
		ViewButton btn32 = new ViewButton();
		btn32.setName("英雄联盟");
		btn32.setType("view");
		btn32.setUrl("http://www.soso.com/");
		ClickButton btn33 = new ClickButton();
		btn33.setName("炉石");
		btn33.setType("click");
		btn33.setKey("lushi");
		
		ComplexButton main1 = new ComplexButton();
		main1.setName("技术");
		main1.setSub_button(new Button[]{btn11, btn12, btn13});
		
		ComplexButton main2 = new ComplexButton();
		main2.setName("搜索");
		main2.setSub_button(new Button[]{btn21, btn22, btn23, btn24, btn25});
		
		ComplexButton main3 = new ComplexButton();
		main3.setName("游戏");
		main3.setSub_button(new Button[]{btn31, btn32, btn33});
		
		Menu menu = new Menu();
		menu.setButton(new Button[]{main1, main2, main3});
		MenuUtil.createMenu(InitUtil.accessToken, JSONObject.fromObject(menu).toString());
		// 以后应该会有3、4级菜单
	}
	
	/**
	 * 其他新增按钮类型的请求示例
	 */
	@Test
	public void testCreateMenu2() {
		OtherButton btn11 = new OtherButton();
		btn11.setType("scancode_waitmsg");
		btn11.setName("扫码带提示");
		btn11.setKey("rselfmenu_0_0");
		btn11.setSub_button(new ArrayList<Object>());
		
		OtherButton btn12 = new OtherButton();
		btn12.setType("scancode_push");
		btn12.setName("扫码推事件");
		btn12.setKey("rselfmenu_0_1");
		btn12.setSub_button(new ArrayList<Object>());
		
		ComplexButton main1 = new ComplexButton();
		main1.setName("扫码");
		main1.setSub_button(new Button[]{btn11, btn12});
		
		OtherButton btn21 = new OtherButton();
		btn21.setType("pic_sysphoto");
		btn21.setName("系统拍照发图");
		btn21.setKey("rselfmenu_1_0");
		btn21.setSub_button(new ArrayList<Object>());
		
		OtherButton btn22 = new OtherButton();
		btn22.setType("pic_photo_or_album");
		btn22.setName("拍照或者相册发图");
		btn22.setKey("rselfmenu_1_1");
		btn22.setSub_button(new ArrayList<Object>());
		
		OtherButton btn23 = new OtherButton();
		btn23.setType("pic_weixin");
		btn23.setName("微信相册发图");
		btn23.setKey("rselfmenu_1_2");
		btn23.setSub_button(new ArrayList<Object>());
		
		ComplexButton main2 = new ComplexButton();
		main2.setName("发图");
		main2.setSub_button(new Button[]{btn21, btn22, btn23});
		
		OtherButton main3 = new OtherButton();
		main3.setType("location_select");
		main3.setName("发送位置");
		main3.setKey("rselfmenu_2_0");
		
		Menu menu = new Menu();
		menu.setButton(new Button[]{main1, main2, main3});
		MenuUtil.createMenu(InitUtil.accessToken, JSONObject.fromObject(menu).toString());
	}
	
	@Test
	public void testGetMenu() {
		JSONObject jsonObj = MenuUtil.getMenu(InitUtil.accessToken);
		System.out.println(jsonObj.toString());
	}
	
	@Test
	public void testDeleteMenu() {
		MenuUtil.deleteMenu(InitUtil.accessToken);
	}
	
	@Test
	public void testCreateConditionalMenu(){
		ClickButton main1 = new ClickButton();
		main1.setType("click");
		main1.setName("今日歌曲");
		main1.setKey("V1001_TODAY_MUSIC");
		
		ViewButton btn21 = new ViewButton();
		btn21.setType("view");
		btn21.setName("搜索");
		btn21.setUrl("http://www.soso.com/");
		
		ViewButton btn22 = new ViewButton();
		btn22.setType("view");
		btn22.setName("视频");
		btn22.setUrl("http://v.qq.com/");
		
		ClickButton btn23 = new ClickButton();
		btn23.setType("click");
		btn23.setName("赞一下我们");
		btn23.setKey("V1001_GOOD");
		
		ComplexButton main2 = new ComplexButton();
		main2.setName("菜单");
		main2.setSub_button(new Button[]{btn21, btn22, btn23});
		
		MatchRule matchrule = new MatchRule();
		matchrule.setTag_id("100");
		matchrule.setSex("1");
		matchrule.setCountry("中国");
		matchrule.setProvince("北京");
		matchrule.setCity("");
		matchrule.setClient_platform_type("2");
		matchrule.setLanguage("zh_CN");
		
		ConditionalMenu menu = new ConditionalMenu();
		menu.setButton(new Button[]{main1, main2});
		menu.setMatchrule(matchrule);
		System.out.println(MenuUtil.createConditionalMenu(InitUtil.accessToken, JSONObject.fromObject(menu).toString()));
	}
	
	@Test
	public void testDeleteConditionalMenu() {
		MenuUtil.deleteConditionalMenu(InitUtil.accessToken, "423922461");
	}
	
	@Test
	public void testTryMatch() {
		JSONObject jsonObj = MenuUtil.tryMatch(InitUtil.accessToken, "oNI8hs5zE2DjCN58Mdpiw8xkQYks");
		System.out.println(jsonObj.toString());
	}

	@Test
	public void testGetMenuInfo() {
		JSONObject jsonObj = MenuUtil.getMenuInfo(InitUtil.accessToken);
		System.out.println(jsonObj.toString());
	}
}
