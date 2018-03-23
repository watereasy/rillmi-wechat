package com.cy.wx.manager;

import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cy.wx.menu.Button;
import com.cy.wx.menu.ClickButton;
import com.cy.wx.menu.ComplexButton;
import com.cy.wx.menu.Menu;
import com.cy.wx.menu.ViewButton;
import com.cy.wx.model.Token;
import com.cy.wx.util.CommonUtil;
import com.cy.wx.util.MenuUtil;

/**
 * 菜单管理类
 *
 */
public class MenuManager {
	private static Logger logger = LoggerFactory.getLogger(MenuManager.class);
	
	/**
	 * 定义菜单结构
	 * @return
	 */
	public static Menu getMenu() {
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
		
		return menu;
		
	}
	
	public static void main(String[] args) {
		// 第三方用户唯一凭证
		String appid = "wx2a2ffa820da431ff";
		// 第三方用户唯一凭证密钥
		String appsecret = "0d5486069cd321351cc30354c9bad5fb";
		// 调用接口获取凭证
		Token token = CommonUtil.getToken(appid, appsecret);
		if(token != null){
			// 创建菜单
			Menu menu = getMenu();
			String tokenAccess = token.getAccessToken();
			System.out.println("token:" + tokenAccess);
			System.out.println("menu:" + JSONObject.fromObject(menu).toString());
			boolean result = MenuUtil.createMenu(tokenAccess, JSONObject.fromObject(menu).toString());
			// 判断菜单创建结果
			if(result){
				System.out.println("菜单创建成功！");
				logger.info("菜单创建成功！");
			} else {
				logger.info("菜单创建失败！");
				System.out.println("菜单创建失败！");
			}
		}
	}

}
