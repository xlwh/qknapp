/*
 * Title:        清清网系统 2014年8月16日
 * Description:  实践圈发表动态相关业务逻辑
 * Copyright:    Copyright (c) 2014
 * Company:      清清网
 * @author       huangqingjian
 * @version      2.0  2014年8月16日
 */
package com.work.service.circle;

import java.util.List;

import com.qing.common.exception.DaoException;
import com.qing.common.util.Page;
import com.work.domain.MemberInfo;
import com.work.domain.Message;

/**
 * 实现在实践圈发动态等的相关业务逻辑接口
 * 
 * @author       huangqingjian/0050
 * @see          com.qing.circle.domain.Message
 * @since        清清网系统, 2014年8月16日
 */
public interface IMessageService
{
     /**
      * 会员发表实践圈动态
      * @parame message    要发表的动态
      * @return            发表是否成功
      * 
      * 
      */
	public boolean addMessage(Message message);
	
	
	/**
	 * 管理员或会员可以删除
	 * @param messages      要删除的实践圈的动态
	 * @return        删除是否成功
	 */
	public boolean deleteMessage(List<Message> messages);
	
	/**
	 * 展示所有公开的动态(根据时间排序，分页展示)
	 * @return      所有可以被展示的公开动态
	 * 
	 */
	public void viewAll(final Page<Message> page,Message message);
	
	
	/**
	 * 展示某个市的实践圈动态（分页）
	 * @parame city     市区
	 * @return          某个城市市区的动态
	 */
	public Page<Message> getByCity(Page<Message> page,final String city) throws DaoException ;
	
	
	/**
	 * 查看附件的兼职圈动态（分页）
	 * @parame    latatitude   经度
	 * @parame    longtatitude 纬度
	 * @return          附近的动态
	 */
	
	public Page<Message> getByArea(final long latitude,final long longtatitude);
	
	
	/**
	 * 查看自己发的兼职圈动态（分页）
	 * 
	 * @parame    user     用户
	 * @return          自己发的动态
	 */
	
	public Page<Message> getByUser(final MemberInfo user);
	
	/**
	 * 通过id删除一条动态
	 * 
	 * @parame   id    用户
	 * @return          
	 */
	public void deleteById(String id)  throws DaoException ;
	
}
