/*
 * Title:        清清网系统 2014年8月18日
 * Description:  实践圈动态相关的业务逻辑接口实现
 * Copyright:    Copyright (c) 2014
 * Company:      清清网
 * @author       zhanghongbin
 * @version      2.0  2014年8月18日
 */
package com.work.service.circle.imp;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.qing.common.exception.DaoException;
import com.qing.common.util.Page;
import com.work.dao.circle.impl.MessageDaoImpl;
import com.work.domain.MemberInfo;
import com.work.domain.Message;
import com.work.service.circle.IMessageService;

/**
 * (一句话功能简述)
 * (功能详细描述)
 * @author       zhanghongbin/0050
 * @see          [相关类,可选,也可多条,对于重要的类或接口建议注释]
 * @since        清清网系统, 2014年8月18日
 */
@Component
public class MessageServiceImpl implements IMessageService {

	@Resource
	private MessageDaoImpl messagedao;
	
	
	/**
	 * 实现方法
	 * 会员在实践圈里面发表动态
	 * @see com.work.service.circle.IMessageService#addMessage(com.work.domain.Message)
	 * @author       zhanghongbin/0050
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        清清网系统, 2014年8月18日
	 * @param message
	 * @return
	 */
	@Override
	public boolean addMessage(Message message) {
		try {
			
			messagedao.saveMessage(message);
			return true;
		} catch (DaoException e) {
			System.out.println("发表实践圈动态失败");
			e.printStackTrace();
		}
		return false;
	}

	

	/**
	 * 实现方法
	 * 浏览全国各地的实践圈动态
	 * @see com.work.service.circle.IMessageService#viewAll(com.qing.common.util.Page)
	 * @author       zhanghongbin/0050
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        清清网系统, 2014年8月18日
	 * @param page
	 * @return
	 */
	@Override
	public void viewAll(Page<Message> page,Message message) {
		
		messagedao.queryMessagetByPage(page, message);
	}

	/**
	 * 实现方法
	 * 查找某个城市的实践圈消息
	 * @see com.work.service.circle.IMessageService#getByCity(com.work.domain.CityInfo)
	 * @author       zhanghongbin/0050
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        清清网系统, 2014年8月18日
	 * @param city
	 * @return
	 * @throws DaoException 
	 */
	@Override
	public Page<Message> getByCity(Page<Message> page,String city) throws DaoException {
		
			return messagedao.findByCity(page, city);
		
	}

	/**
	 * 实现方法
	 * 查找附近的实践圈动态
	 * @see com.work.service.circle.IMessageService#getByArea(long, long)
	 * @author       zhanghongbin/0050
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        清清网系统, 2014年8月18日
	 * @param latitude
	 * @param longtatitude
	 * @return
	 */
	@Override
	public Page<Message> getByArea(long latitude, long longtatitude) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * 实现方法
	 * (功能详细描述)
	 * @see com.work.service.circle.IMessageService#getByUser(com.work.domain.MemberInfo)
	 * @author       zhanghongbin/0050
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        清清网系统, 2014年8月18日
	 * @param user
	 * @return
	 */
	@Override
	public Page<Message> getByUser(MemberInfo user) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * 覆盖方法/实现方法(选择其一)
	 * (功能详细描述)
	 * @see com.work.service.circle.IMessageService#deleteById(java.lang.String)
	 * @author       huangqingjian/0050
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        清清网系统, 2014年8月23日
	 * @param id
	 * @throws DaoException 
	 */
	@Override
	public void deleteById(String id) throws DaoException {
		Message message = messagedao.findById(id);
		messagedao.deleteMessage(message);
		
	}



	/**
	 * 覆盖方法/实现方法(选择其一)
	 * (功能详细描述)
	 * @see com.work.service.circle.IMessageService#deleteMessage(java.util.List)
	 * @author       huangqingjian/0050
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        清清网系统, 2014-8-25
	 * @param messages
	 * @return
	 */
	@Override
	public boolean deleteMessage(List<Message> messages) {
		
		return false;
	}

}
