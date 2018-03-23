package com.cy.wx.menu;

/**
 * 个性化菜单
 * @author zhangjianhui
 *
 */
public class ConditionalMenu extends Menu {
	
	private MatchRule matchrule;

	public MatchRule getMatchrule() {
		return matchrule;
	}

	public void setMatchrule(MatchRule matchrule) {
		this.matchrule = matchrule;
	}

}
