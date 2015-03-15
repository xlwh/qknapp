/*
 * Title:        清清网系统 2014年8月2日
 * Description:  [描述模块的功能、作用、使用方法和注意事项]
 * Copyright:    Copyright (c) 2014
 * Company:      清清网
 * @author       huangqingjian
 * @version      2.0  2014年8月2日
 */
package com.work.service.datamining.impl;

import java.util.List;

import org.htmlparser.Node;
import org.htmlparser.NodeFilter;
import org.htmlparser.Parser;
import org.htmlparser.filters.AndFilter;
import org.htmlparser.filters.HasAttributeFilter;
import org.htmlparser.filters.NodeClassFilter;
import org.htmlparser.filters.OrFilter;
import org.htmlparser.nodes.TextNode;
import org.htmlparser.tags.Div;
import org.htmlparser.tags.LinkTag;
import org.htmlparser.util.NodeList;
import org.htmlparser.util.ParserException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.qing.common.util.DateTimeUtils;
import com.qing.common.util.StringUtil;
import com.qing.enums.NetTypeEnum;
import com.work.domain.DataMiningSet;
import com.work.domain.PartTimeEngine;
import com.work.service.datamining.IJobInfoMiningService;

/**
 *1010兼职网解析实现
 * (功能详细描述)
 * @author       huangqingjian/0050
 * @see          [相关类,可选,也可多条,对于重要的类或接口建议注释]
 * @since        清清网系统, 2014年8月2日
 */
public class ZeroMiningServiceImpl implements IJobInfoMiningService {
	private static final Logger logger = LoggerFactory.getLogger(ZeroMiningServiceImpl.class);

	private static final String ENCODING = "gb2312";

	private static final String UPDATETIME = "更新时间：";
	private static final String JOBADDRESS = "工作地点：";
	private static final String CONTACTS = "联系人：";
	private static final String CONTACTS_ADDRESS = "联系地址：";
	private static final String PHONE = "手机：";
	private static final String MOBILE = "电话：";
	private static final String DETAIL = "见详情";

	/**
	 * 1010兼职网实现
	 * (功能详细描述)
	 * @see com.work.service.datamining.IJobInfoMiningService#extractLinks(com.work.domain.DataMiningSet)
	 * @author       huangqingjian/0050
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        清清网系统, 2014年8月2日
	 * @param set
	 */
	@Override
	public void extractLinks(DataMiningSet set, List<PartTimeEngine> engines) {

		if (null == set) {
			logger.info("配置项为空！");
			return;
		}

		Parser parser;
		try {
			int pageNum = set.getPageNum();
			String url = null;
			for (int k = 1; k <= pageNum; k++) {
				//分页的URL拼接
				url = set.getUrl() + k;
				// 1、构造一个Parser，并设置相关的属性  
				parser = new Parser(url);
				parser.setEncoding(ENCODING);
				//2、创建第二个Filter，过滤<a>标签  
				NodeFilter aNodeFilter = new NodeClassFilter(LinkTag.class);

				//3、获取div class属性为   Linklist
				AndFilter linkFilter = new AndFilter(new NodeFilter[] { aNodeFilter,
						new HasAttributeFilter("class", "Linklist") });

				//3、使用parser根据filter来取得所有符合条件的节点  
				NodeList nodeList = parser.extractAllNodesThatMatch(linkFilter);
				//4、对取得的Node进行处理  
				for (int i = 0; i < nodeList.size(); i++) {
					Node node = nodeList.elementAt(i);
					String linkURL = "";
					String title = "";
					//如果链接类型为<a />  
					if (node instanceof LinkTag) {
						LinkTag link = (LinkTag) node;
						linkURL = link.getLink();
						title = link.getStringText();

						logger.info("获取兼职标题{}，解析的URL为{}", new Object[] { title, linkURL });
						parserJob(set, title, linkURL, engines);

						//如果时间间隔大于0需要间隔几秒再去获取兼职信息，以免操作过快被封
						if (set.getParseInterval() > 0) {
							Thread.sleep(set.getParseInterval());
						}
					}

				}
			}
		} catch (ParserException e) {
			logger.error("ParserException:", e);
		} catch (InterruptedException e) {
			logger.error("InterruptedException:", e);
		}

	}

	/**
	 * 解析兼职详情
	 * (一句话功能简述)
	 * (功能详细描述)
	 * @author       huangqingjian/0050
	 * @throws ParserException 
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        清清网系统, 2014年8月2日
	 */
	private void parserJob(DataMiningSet set, String title, String url, List<PartTimeEngine> engines)
			throws ParserException {
		//获取兼职详情的过滤条件
		OrFilter linkFilter = new OrFilter(new NodeFilter[] { new HasAttributeFilter("class", "d_left"),
				new HasAttributeFilter("id", "content") });
		NodeList nodeList;

		// 1、构造一个Parser，并设置相关的属性  
		Parser parser = new Parser(url);
		parser.setEncoding(ENCODING);
		nodeList = parser.extractAllNodesThatMatch(linkFilter);
		parseNodes(set, title, url, nodeList, engines);

	}

	/**
	 * 
	 * 解析具体节点的内容
	 * (一句话功能简述)
	 * (功能详细描述)
	 * @author       huangqingjian/0050
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        清清网系统, 2014年8月2日
	 * @param set
	 * @param title
	 * @param url
	 * @param nodeList
	 */
	private void parseNodes(DataMiningSet set, String title, String url, NodeList nodeList, List<PartTimeEngine> engines) {

		PartTimeEngine engine = new PartTimeEngine();
		engine.setPartTimeTitle(title.replaceAll("&nbsp;", "").replaceAll("\"", "").trim());
		engine.setCityName(set.getCity());
		engine.setProvinceName(set.getProvince());
		engine.setIsExist(0);
		engine.setIsVerify(0);
		engine.setSourceUrl(url);
		engine.setSource(NetTypeEnum.ZEROMINING.getName());
		engine.setPartTimeTypeName(set.getPartTimeTypeName());
		engine.setRecruitmentNumber(DETAIL);
		engine.setGenderReq(2);
		engine.setWorkTime(DETAIL);
		engine.setSalary(DETAIL);
		engines.add(engine);
		//4、对取得的Node进行处理  
		for (int i = 0; i < nodeList.size(); i++) {
			Node node = nodeList.elementAt(i);
			//如果节点类型为DIV则进行解析
			if (node instanceof Div) {
				Div div = (Div) node;
				NodeList list = div.getChildren();
				for (int j = 0; j < list.size(); j++) {
					Node textNode = list.elementAt(j);

					//只获取文本节点
					if (textNode instanceof TextNode) {
						String txt = textNode.getText().replaceAll("&nbsp;", "").replaceAll("\"", "").trim();
						if (StringUtil.isValidStr(txt)) {
							setEngine(engine, txt);
						}
					}
				}
			}

		}
	}

	/**
	 * 设置对应的字段
	 * (一句话功能简述)
	 * (功能详细描述)
	 * @author       huangqingjian/0050
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        清清网系统, 2014年8月2日
	 * @param engine
	 * @param txt
	 */
	private void setEngine(PartTimeEngine engine, String txt) {
		if (txt.startsWith(UPDATETIME)) {
			txt = txt.replace(UPDATETIME, "2014-");
			engine.setCreateDate(DateTimeUtils.parseDateYMDHM(txt));
		} else if (txt.startsWith(JOBADDRESS)) {
			engine.setAreaName(txt.replace(JOBADDRESS, ""));
		} else if (txt.startsWith(CONTACTS)) {
			engine.setContacts(txt.replace(CONTACTS, ""));
			engine.setCreator(txt.replace(CONTACTS, ""));
		} else if (txt.startsWith(CONTACTS_ADDRESS)) {
			engine.setAddress(txt.replace(CONTACTS_ADDRESS, ""));
		} else if (txt.startsWith(PHONE)) {
			engine.setCellPhone(txt.replace(PHONE, ""));
		} else if (txt.startsWith(MOBILE)) {
			engine.setCellPhone(txt.replace(MOBILE, ""));
		} else {
			String content = engine.getPartTimeContent();
			if (!StringUtil.isValidStr(content)) {
				content = "";
			}
			content += txt + "<br>";
			engine.setPartTimeContent(content);
		}
	}
}
