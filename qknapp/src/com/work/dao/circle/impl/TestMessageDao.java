/*
 * Title:        清清网系统 2014年8月31日
 * Description:  [描述模块的功能、作用、使用方法和注意事项]
 * Copyright:    Copyright (c) 2014
 * Company:      清清网
 * @author       huangqingjian
 * @version      2.0  2014年8月31日
 */
package com.work.dao.circle.impl;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.junit.Test;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import com.qing.common.util.Page;
import com.work.domain.Message;
import com.work.domain.Picture;

/**
 * (一句话功能简述)
 * (功能详细描述)
 * @author       huangqingjian/0050
 * @see          [相关类,可选,也可多条,对于重要的类或接口建议注释]
 * @since        清清网系统, 2014年8月31日
 */
public class TestMessageDao {
	
	@Test
	public void testGetAll(){
		Message message = new Message();
		Page<Message> page = new Page<Message>();
		//dao.queryMessagetByPage(page, message);
		FileSystemXmlApplicationContext ctx = new FileSystemXmlApplicationContext("config/spring/applicationContext.xml");
		MessageDaoImpl mdao = (MessageDaoImpl)ctx.getBean("messageDaoImpl");
		mdao.queryMessagetByPage(page, message);
		System.out.println("记录数："+page.getTotalCount());
		List<Message> lst = page.getResult();
		for(int i=0;i<lst.size();i++){
			Message msg = lst.get(i);
			System.err.println(msg.getContent());
			Set<Picture> pic = msg.getPictures();
			Iterator<Picture> it = pic.iterator();
			while(it.hasNext()){
				Picture pc = it.next();
				System.out.println("这条信息所对应的图片路径是："+pc.getSrc());
			}
		}
		
	}
}
