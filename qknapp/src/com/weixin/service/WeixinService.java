package com.weixin.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.qing.common.util.ErrorCode;
import com.weixin.manager.XmlTool;
import com.weixin.pojo.Article;
import com.weixin.pojo.NewsMessage;

/**
 * 
 * 处理微信发来的请求。
 * @author       huangqingjian/0050
 * @see          [相关类,可选,也可多条,对于重要的类或接口建议注释]
 * @since        瑾泉二维码系统, 2013年12月27日
 */
public class WeixinService {

	private static final Logger logger = LoggerFactory.getLogger(WeixinService.class);

	/**
	 * 处理微信发来的请求模拟回复一个图文消息
	 * 
	 * @param request
	 * @return
	 */
	public static String processRequest(HttpServletRequest request) {
		String respMessage = null;
		try {

			// xml请求解析 只有用户主动发消息才能获取并回复。
			Map<String, String> requestMap = XmlTool.parseXml(request);

			// 发送方帐号（open_id）
			String fromUserName = requestMap.get("FromUserName");
			// 公众帐号
			String toUserName = requestMap.get("ToUserName");

			NewsMessage message = new NewsMessage();
			message.setToUserName(fromUserName);
			message.setFromUserName(toUserName);
			message.setMsgType("news");
			message.setCreateTime(new Date().getTime());
			message.setFuncFlag(0);
			Article article = new Article();
			article.setTitle("深护皙白防晒套装");
			article.setDescription("启动“反射、分辨、修护”三重防晒系统、缔造肌肤裸妆防御网。");
			//自动回复功能预留，暂时不实现。URL暂时写死。
			article.setPicUrl("http://113.108.52.2:8060/upload/ls_images/upload/productImg/fb92d4d9-9e92-4b58-9ca6-fa6216b9aaaa.png");
			article.setUrl("http://113.108.52.2:8060/jq/company/phone/brandStory.jsp");
			List<Article> articleList = new ArrayList<Article>();
			articleList.add(article);
			// 设置图文消息个数
			message.setArticleCount(articleList.size());
			// 设置图文消息包含的图文集合
			message.setArticles(articleList);

			respMessage = XmlTool.newsMessageToXml(message);
		} catch (Exception e) {
			logger.error(ErrorCode.SERVICE_ERROR, e);
		}

		return respMessage;
	}
}
