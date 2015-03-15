/**
 * Title:        清清网系统 2014年8月16日
 * Description:   实践圈动态的数据库访问接口
 * Copyright:    Copyright (c) 2014
 * Company:      清清网
 * @author      zhanghongbin
 * @version      2.0  2014年8月16日
 */
package com.work.dao.circle;

import java.util.List;

import com.qing.common.exception.DaoException;
import com.qing.common.util.Page;
import com.work.domain.Message;

/**
 * 实践圈动态的数据库访问层接口
 * (功能详细描述)
 * @author      zhanghongbin
 * @see          [相关类,可选,也可多条,对于重要的类或接口建议注释]
 * @since        清清网系统, 2014年8月16日
 */
public interface IMessageDao
{
	/**
	 * 添加实践圈数据
	 * @author       zhanghongbin
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        清清网系统, 2014年8月16日
	 * @param message
	 */
  public void saveMessage(Message message) throws DaoException;
  
  /**
	 * 删除实践圈数据
	 * @author       zhanghongbin
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        清清网系统, 2014年8月16日
	 * @param id     要删除的动态id
	 */
  
  public void deleteMessage(List<Message> messages) throws DaoException;
  
  
  /**
 	 * 删除实践圈一条数据
 	 * @author       zhanghongbin
 	 * @see          相关函数,对于重要的函数建议注释
 	 * @since        清清网系统, 2014年8月16日
 	 * @param id     要删除的动态id
 	 */
   
   public void deleteMessage(Message messages) throws DaoException;
  
  /**
	 * 获取附近的实践圈动态
	 * @author       zhanghongbin
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        清清网系统, 2014年8月16日
	 * @param    latatitude1   	经度
	 * @parame   longtatitude 	纬度开
	 * @return                  获取到的实践圈的数据
	 *
     */
	 
  
   public void  findByArea(Page<Message> page,Message info,final long latatitude1,final long longtatitude)throws DaoException;
   
   /**
	 * 获取某个城市的所有
	 * @author       zhanghongbin
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        清清网系统, 2014年8月16日
	 * @param id     cityinfo   要获取的城市信息
	 */
   public Page<Message> findByCity(Page<Message> page,final String cityinfo) throws DaoException;
   
   /**
	 * 浏览全国各地所有可见动态
	 * @author       zhanghongbin
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        清清网系统, 2014年8月16日
	 * @param id     cityinfo   要获取的城市信息
	 */
   
   public Page<Message> findAll(Page<Message> page) throws DaoException;
   
   /**
	 * 浏览自己在实践圈里面所发的动态
	 * @author       zhanghongbin
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        清清网系统, 2014年8月16日
	 * @param uid    用户id
	 */
   
   public void findByUser(Page<Message> page,Message message,String uid) throws DaoException;
   
   
   /**
  	 * 分页浏览实践圈动态
  	 * @author       zhanghongbin
  	 * @see          相关函数,对于重要的函数建议注释
  	 * @since        清清网系统, 2014年8月16日
  	 * @param 
  	 */
   public void queryMessagetByPage(Page<Message> page,Message message);
   
   /**
 	 * 通过id浏览一条动态
 	 * @author       zhanghongbin
 	 * @see          相关函数,对于重要的函数建议注释
 	 * @since        清清网系统, 2014年8月16日
 	 * @param 
 	 */
   
   public Message findById(String id)  throws DaoException;
   
   
}
