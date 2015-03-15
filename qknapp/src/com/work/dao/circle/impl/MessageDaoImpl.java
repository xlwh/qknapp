/*
 * Title:        清清网系统 2014年8月18日
 * Description:  实践圈动态数据库访问接口实现类
 * Copyright:    Copyright (c) 2014
 * Company:      清清网
 * @author       zhanghongbin
 * @version      2.0  2014年8月18日
 */
package com.work.dao.circle.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.qing.common.base.dao.HibernateDao;
import com.qing.common.exception.DaoException;
import com.qing.common.exception.ServiceException;
import com.qing.common.exception.handle.ExceptionHandle;
import com.qing.common.util.ErrorCode;
import com.qing.common.util.Page;
import com.work.dao.circle.IMessageDao;
import com.work.domain.Message;

/**
 * (一句话功能简述) (功能详细描述)
 * 
 * @author zhanghongbin/0050
 * @see [相关类,可选,也可多条,对于重要的类或接口建议注释]
 * @since 清清网系统, 2014年8月18日
 */

@Repository
public class MessageDaoImpl implements IMessageDao {

	@Resource
	HibernateDao<Message, String> hibernateDao;

	/**
	 * 实现方法(选择其一) 保存数据
	 * 
	 * @see com.work.dao.circle.IMessageDao#saveMessage(com.work.domain.Message)
	 * @author zhanghongbin
	 * @see 相关函数,对于重要的函数建议注释
	 * @since 清清网系统, 2014年8月18日
	 * @param message
	 * @throws DaoException
	 */
	@Override
	public void saveMessage(Message message) throws DaoException {
		hibernateDao.saveOrUpdate(message);

	}

	/**
	 * 实现方法(选择其一) 删除实践圈里面的数据
	 * 
	 * @see com.work.dao.circle.IMessageDao#deleteMessage(int)
	 * @author zhanghongbin
	 * @see 相关函数,对于重要的函数建议注释
	 * @since 清清网系统, 2014年8月18日
	 * @param id
	 */
	@Override
	public void deleteMessage(List<Message> messages) throws DaoException {

		hibernateDao.removeAll(messages);
	}

	/**
	 * 实现方法(选择其一) 删除实践圈里面一条动态
	 * 
	 * @see com.work.dao.circle.IMessageDao#deleteMessage(int)
	 * @author zhanghongbin
	 * @see 相关函数,对于重要的函数建议注释
	 * @since 清清网系统, 2014年8月18日
	 * @param id
	 */
	@Override
	public void deleteMessage(Message messages) throws DaoException {

		hibernateDao.remove(messages);
	}

	/**
	 * 实现方法(选择其一) 获得周围的实践圈动态
	 * 
	 * @see com.work.dao.circle.IMessageDao#findByArea(long, long, long, long)
	 * @author zhanghongbin
	 * @see 相关函数,对于重要的函数建议注释
	 * @since 清清网系统, 2014年8月18日
	 * @param latatitude1
	 * @param longtatitude
	 * 
	 * 
	 * @return
	 */
	@Override
	public void findByArea(Page<Message> page, Message message, long latitude,
			long longitude) throws DaoException {
		StringBuilder builder = new StringBuilder();
		List<Object> parm = new ArrayList<Object>();
		builder.append(" from Message where 1=1 ");
		builder.append(" and longtitude>" + (longitude - 0.1) + " ");
		builder.append(" and longtitude<" + (longitude + 0.1) + " ");
		builder.append(" and latitude>" + (latitude - 0.1) + " ");
		builder.append(" and latitude>" + (latitude + 0.1) + " ");
		builder.append(generateHQLCondition(message, parm));
		builder.append(" order by createtime desc");
		try {
			setPageSize(page, message);
			hibernateDao.findByPage(page, builder.toString(), parm.toArray());
		} catch (DaoException e) {
			try {
				ExceptionHandle.throwServiceException(e,
						ErrorCode.DAO_SAVE_ERROR_001,
						"MessageDao.queryMessagetByPage");
			} catch (ServiceException e1) {

				e1.printStackTrace();
			}
		}

	}

	/**
	 * 实现方法(选择其一) 获得某个城市的实践圈动态
	 * 
	 * @see com.work.dao.circle.IMessageDao#findByCity(java.lang.String)
	 * @author zhanghongbin
	 * @see 相关函数,对于重要的函数建议注释
	 * @since 清清网系统, 2014年8月18日
	 * @param city
	 * @return
	 */
	@Override
	public Page<Message> findByCity(Page<Message> page, String cityinfo)
			throws DaoException {
		StringBuffer hql = new StringBuffer();
		hql.append("from Message where city = '" + cityinfo + "'  ");
		page = hibernateDao.findByPage(page, hql.toString(), null);
		setPageSize(page);
		return page;
	}

	/**
	 * 实现方法(选择其一) (功能详细描述)
	 * 
	 * @see com.work.dao.circle.IMessageDao#findAll(java.lang.String)
	 * @author zhanghongbin
	 * @see 相关函数,对于重要的函数建议注释
	 * @since 清清网系统, 2014年8月18日
	 * @param city
	 * @return
	 */
	@Override
	public Page<Message> findAll(Page<Message> page) throws DaoException {
         Page<Message> msg = hibernateDao.findByPage(page, "from Message", null);
        return msg;

	}

	/**
	 * 实现方法(选择其一) (功能详细描述)
	 * 
	 * @see com.work.dao.circle.IMessageDao#findAll(java.lang.String)
	 * @author zhanghongbin
	 * @see 相关函数,对于重要的函数建议注释
	 * @since 清清网系统, 2014年8月18日
	 * @param city
	 * @return
	 */
	@Override
	public void queryMessagetByPage(Page<Message> page, Message message) {
		StringBuilder builder = new StringBuilder();
		List<Object> parm = new ArrayList<Object>();
		builder.append(" from Message where 1=1 ");
		builder.append(generateHQLCondition(message, parm));
		builder.append(" order by createtime desc");
		try {
			setPageSize(page, message);
			hibernateDao.findByPage(page, builder.toString(), parm.toArray());
		} catch (DaoException e) {
			try {
				ExceptionHandle.throwServiceException(e,
						ErrorCode.DAO_SAVE_ERROR_001,
						"MessageDao.queryMessagetByPage");
			} catch (ServiceException e1) {

				e1.printStackTrace();
			}
		}
	}

	/**
	 * 构建查询条件 (一句话功能简述) (功能详细描述)
	 * 
	 * @author huangqingjian/0050
	 * @see 相关函数,对于重要的函数建议注释
	 * @since 二维码系统, 2014年7月5日
	 * @param vo
	 * @return
	 */
	private StringBuilder generateHQLCondition(Message vo, List<Object> parm) {
		StringBuilder builder = new StringBuilder();
		return builder;
	}

	/**
	 * 设置总页数 (一句话功能简述) (功能详细描述)
	 * 
	 * @author huangqingjian/0050
	 * @see 相关函数,对于重要的函数建议注释
	 * @since 二维码系统, 2014年7月5日
	 * @param page
	 * @param vo
	 * @throws DaoException
	 */
	private void setPageSize(Page<Message> page, Message vo)
			throws DaoException {
		StringBuilder builder = new StringBuilder();
		List<Object> parm = new ArrayList<Object>();
		builder.append("select count(id)  from Message where 1=1 ");
		builder.append(generateHQLCondition(vo, parm));
		page.setTotalCount(hibernateDao.findNumByHQL(builder.toString(),
				parm.toArray()));
	}

	/**
	 * 实现方法(选择其一) 获取某个用户的实践圈消息
	 * 
	 * @see com.work.dao.circle.IMessageDao#findByUser(int)
	 * @author zhanghongbin
	 * @see 相关函数,对于重要的函数建议注释
	 * @since 清清网系统, 2014年8月18日
	 * @param uid
	 * @return
	 */
	@Override
	public void findByUser(Page<Message> page, Message message, String uid)
			throws DaoException {
		StringBuilder builder = new StringBuilder();
		List<Object> parm = new ArrayList<Object>();
		builder.append(" from Message where 1=1 ");
		builder.append(" and uid =" + uid + " ");
		builder.append(generateHQLCondition(message, parm));
		builder.append(" order by createtime desc");
		try {
			setPageSize(page, message);
			hibernateDao.findByPage(page, builder.toString(), parm.toArray());
		} catch (DaoException e) {
			try {
				ExceptionHandle.throwServiceException(e,
						ErrorCode.DAO_SAVE_ERROR_001,
						"MessageDao.queryMessagetByPage");
			} catch (ServiceException e1) {

				e1.printStackTrace();
			}
		}
	}

	/**
	 * (一句话功能简述) 设置分页相关参数
	 * 
	 * @author zhanghongbin
	 * @see 相关函数,对于重要的函数建议注释
	 * @since 清清网系统, 2014年8月16日
	 * @param page
	 * @param resume
	 * @throws DaoException
	 */
	private void setPageSize(Page<Message> page) throws DaoException {
		StringBuilder builder = new StringBuilder();
		List<Object> parm = new ArrayList<Object>();
		builder.append("select count(id)  from Message where 1=1 ");
		page.setTotalCount(hibernateDao.findNumByHQL(builder.toString(),
				parm.toArray()));
	}

	/**
	 * 覆盖方法/实现方法(选择其一) (功能详细描述)
	 * 
	 * @see com.work.dao.circle.IMessageDao#findById(java.lang.String)
	 * @author huangqingjian/0050
	 * @see 相关函数,对于重要的函数建议注释
	 * @since 清清网系统, 2014年8月23日
	 * @param id
	 * @return
	 * @throws DaoException
	 */
	@Override
	public Message findById(String id) throws DaoException {
		return hibernateDao.findById(Message.class, id);
	}

}
