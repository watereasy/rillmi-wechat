package com.cy.wx.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.cy.wx.message.resp.Article;
import com.cy.wx.message.resp.NewsMsg;
import com.cy.wx.message.resp.TextMsg;
import com.cy.wx.model.BaiduPlace;
import com.cy.wx.model.UserLocation;
import com.cy.wx.util.BaiduMapUtil;
import com.cy.wx.util.MsgUtil;
import com.cy.wx.util.MySQLUtil;

/**
 * 核心服务类
 * 
 */
public class CoreService {
	/**
	 * 处理微信发来的请求
	 * 
	 * @param request
	 * @return xml
	 */
	@SuppressWarnings("unused")
	public static String processRequest(HttpServletRequest request) {
		// xml格式的消息数据
		String respXml = null;
		// 默认返回的文本消息内容
		String respContent = null;
		try {
			// 调用parseXml方法解析请求消息
			Map<String, String> requestMap = MsgUtil.parseXml(request);
			// 发送方帐号
			String fromUserName = requestMap.get("FromUserName");
			// 开发者微信号
			String toUserName = requestMap.get("ToUserName");
			// 消息类型
			String msgType = requestMap.get("MsgType");

			// 回复文本消息
			TextMsg textMsg = new TextMsg();
			textMsg.setToUserName(fromUserName);
			textMsg.setFromUserName(toUserName);
			textMsg.setCreateTime(new Date().getTime());
			textMsg.setMsgType(MsgUtil.RESP_MESSAGE_TYPE_TEXT);

			// 文本消息
			if (msgType.equals(MsgUtil.REQ_MESSAGE_TYPE_TEXT)) {
				String content = requestMap.get("Content").trim();
				if (content.equals("附近")) {
					respContent = getUsage();
				} else if (content.startsWith("附近")) {
					String keyWord = content.replaceAll("附近", "").trim();
					// 获取用户最后一次发送的地理位置
					UserLocation location = MySQLUtil.getLastLocation(request, fromUserName);
					// 未获取到
					if (null == location) {
						respContent = getUsage();
					} else {
						// 根据转换后（纠偏）的坐标搜索周边POI
						List<BaiduPlace> placeList = BaiduMapUtil.searchPlace(keyWord, location.getBd09Lng(), location.getBd09Lat());
						// 未搜索到POI
						if (null == placeList || 0 == placeList.size()) {
							respContent = String.format("/难过，您发送的位置附近未搜索到“%s”信息！", keyWord);
						} else {
							List<Article> articleList = BaiduMapUtil.makeArticleList(placeList, location.getBd09Lng(), location.getBd09Lat());
							// 回复图文消息
							NewsMsg newsMessage = new NewsMsg();
							newsMessage.setToUserName(fromUserName);
							newsMessage.setFromUserName(toUserName);
							newsMessage.setCreateTime(new Date().getTime());
							newsMessage.setMsgType(MsgUtil.RESP_MESSAGE_TYPE_NEWS);
							newsMessage.setArticles(articleList);
							newsMessage.setArticleCount(articleList.size());
							respXml = MsgUtil.msgToXml(newsMessage);
						}
					}
				} else {
					respContent = getUsage();
				}
			}
			// 图片消息
			else if (msgType.equals(MsgUtil.REQ_MESSAGE_TYPE_IMAGE)) {
				respContent = "您发送的是图片消息！";
			}
			// 语音消息
			else if (msgType.equals(MsgUtil.REQ_MESSAGE_TYPE_VOICE)) {
				respContent = "您发送的是语音消息！";
			}
			// 视频消息
			else if (msgType.equals(MsgUtil.REQ_MESSAGE_TYPE_VIDEO)) {
				respContent = "您发送的是视频消息！";
			}
			// 地理位置消息
			else if (msgType.equals(MsgUtil.REQ_MESSAGE_TYPE_LOCATION)) {
				// 用户发送的经纬度
				String lng = requestMap.get("Location_Y");
				String lat = requestMap.get("Location_X");
				// 坐标转换后的经纬度
				String bd09Lng = null;
				String bd09Lat = null;
				// 调用接口转换坐标
				UserLocation userLocation = BaiduMapUtil.convertCoord(lng, lat);
				if (null != userLocation) {
					bd09Lng = userLocation.getBd09Lng();
					bd09Lat = userLocation.getBd09Lat();
				}
				// 保存用户地理位置
				MySQLUtil.saveUserLocation(request, fromUserName, lng, lat, bd09Lng, bd09Lat);

				StringBuffer buffer = new StringBuffer();
				buffer.append("[愉快]").append("成功接收您的位置！").append("\n\n");
				buffer.append("您可以输入搜索关键词获取周边信息了，例如：").append("\n");
				buffer.append("        附近ATM").append("\n");
				buffer.append("        附近KTV").append("\n");
				buffer.append("        附近厕所").append("\n");
				buffer.append("必须以“附近”两个字开头！");
				respContent = buffer.toString();
			}
			// 链接消息
			else if (msgType.equals(MsgUtil.REQ_MESSAGE_TYPE_LINK)) {
				respContent = "您发送的是链接消息！";
			}
			// 事件推送
			else if (msgType.equals(MsgUtil.REQ_MESSAGE_TYPE_EVENT)) {
				// 事件类型
				String eventType = requestMap.get("Event");
				// 关注
				if (eventType.equals(MsgUtil.EVENT_TYPE_SUBSCRIBE)) {
					//respContent = "您好，欢迎关注网址导航！我们致力于打造精品网址聚合应用，为用户提供便捷的上网导航服务。体验生活，从这里开始！";
					respContent = getSubscribeMsg();
				}
				// 取消关注
				else if (eventType.equals(MsgUtil.EVENT_TYPE_UNSUBSCRIBE)) {
					// TODO 取消订阅后用户不会再收到公众账号发送的消息，因此不需要回复
				}
				// 扫描带参数二维码
				else if (eventType.equals(MsgUtil.EVENT_TYPE_SCAN)) {
					// TODO 处理扫描带参数二维码事件
				}
				// 上报地理位置
				else if (eventType.equals(MsgUtil.EVENT_TYPE_LOCATION)) {
					// TODO 处理上报地理位置事件
				}
				// 自定义菜单
				else if (eventType.equals(MsgUtil.EVENT_TYPE_CLICK)) {
					// 时间key值，创建菜单时的key对应
					String eventKey = requestMap.get("EventKey");
					// 根据key值判断用户点击的按钮
					if("alibaba".equals(eventKey)) {
						Article article = new Article();
						article.setTitle("阿里巴巴");
						article.setDescription("你说科技哪家强，中国首富在这里");
						article.setPicUrl("");
						article.setUrl("http://www.alibabagroup.com/cn/global/home");
						List<Article> articleList = new ArrayList<Article>();
						articleList.add(article);
						// 创建图文消息
						NewsMsg newsMessage = new NewsMsg();
						newsMessage.setToUserName(fromUserName);
						newsMessage.setFromUserName(toUserName);
						newsMessage.setCreateTime(new Date().getTime());
						newsMessage.setMsgType(MsgUtil.RESP_MESSAGE_TYPE_NEWS);
						newsMessage.setArticleCount(articleList.size());
						newsMessage.setArticles(articleList);
						respXml = MsgUtil.msgToXml(newsMessage);
					} else if (eventKey.equals("iteye")) {
						textMsg.setContent("ITeye即创办于2003年9月的JavaEye,从最初的以讨论Java技术为主的技术论坛，已经逐渐发展成为涵盖整个软件开发领域的综合性网站。\n\nhttp://www.iteye.com");
						respXml = MsgUtil.msgToXml(textMsg);
					}
				}
				// 事件推送群发结果
				else if (eventType.equals(MsgUtil.EVENT_TYPE_MASSSENDJOBFINISH)) {
					// 群发消息ID
					Long msgId = Long.parseLong(requestMap.get("MsgID"));
					// 群发的结构，为“send success”或“send fail”或“err(num)”
					String status = requestMap.get("Status");
					// tag_id下粉丝数；或者openid_list中的粉丝数
					Long totalCount = Long.parseLong(requestMap.get("TotalCount"));
					// 过滤（过滤是指特定地区、性别的过滤、用户设置拒收的过滤，用户接收已超4条的过滤）后，准备发送的粉丝数，原则上，FilterCount = SentCount + ErrorCount
					Long filterCount = Long.parseLong(requestMap.get("FilterCount"));
					// 发送成功的粉丝数
					Long sentCount = Long.parseLong(requestMap.get("SentCount"));
					// 发送失败的粉丝数
					Long errorCount = Long.parseLong(requestMap.get("ErrorCount"));
					// TODO
					
				}
				// 模板消息发送任务完成
				else if (eventType.equals(MsgUtil.EVENT_TYPE_TEMPLATESENDJOBFINISH)) {
					// 消息ID
					Long msgId = Long.parseLong(requestMap.get("MsgID"));
					// 发送状态 success|failed:user block|failed: system failed
					String status = requestMap.get("Status");
					// TODO
				}
				// 其他自定义菜单事件:公众平台开发文档=>自定义菜单管理=>自定义菜单事件推送
				
			} else {
				respContent = getUsage();
			}
			if (respContent != null) {
				// 设置文本消息的内容
				textMsg.setContent(respContent);
				// 将文本消息对象转换成xml
				respXml = MsgUtil.msgToXml(textMsg);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return respXml;
	}
	
	/**
	 * 关注提示语
	 * 
	 * @return
	 */
	private static String getSubscribeMsg() {
		StringBuffer buffer = new StringBuffer();
		buffer.append("您是否有过出门在外四处找ATM或厕所的经历？").append("\n\n");
		buffer.append("您是否有过出差在外搜寻美食或娱乐场所的经历？").append("\n\n");
		buffer.append("周边搜索为您的出行保驾护航，为您提供专业的周边生活指南，回复“附近”开始体验吧！");
		return buffer.toString();
	}
	
	/**
	 * 使用说明
	 * 
	 * @return
	 */
	private static String getUsage() {
		StringBuffer buffer = new StringBuffer();
		buffer.append("周边搜索使用说明").append("\n\n");
		buffer.append("1）发送地理位置").append("\n");
		buffer.append("点击窗口底部的“+”按钮，选择“位置”，点“发送”").append("\n\n");
		buffer.append("2）指定关键词搜索").append("\n");
		buffer.append("格式：附近+关键词\n例如：附近ATM、附近KTV、附近厕所");
		return buffer.toString();
	}
}
