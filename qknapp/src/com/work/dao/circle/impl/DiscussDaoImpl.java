/*
 * Title:        清清网系统 2014年8月18日
 * Description:  实践圈评论数据库访问对象
 * Copyright:    Copyright (c) 2014
 * Company:      清清网
 * @author       huangqingjian
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
import com.work.dao.circle.IDiscussDao;
import com.work.domain.Discuss;

/**
 * (一句话功能简述)
 * (功能详细描述)
 * @author       huangqingjian/0050
 * @see          [相关类,可选,也可多条,对于重要的类或接口建议注释]
 * @since        清清网系统, 2014年8月18日
 */

@Repository
public class DiscussDaoImpl implements IDiscussDao{

	@Resource
	HibernateDao<Discuss, String> hibernateDao;
	/**
	 * 覆盖方法/实现方法(选择其一)
	 * 保存评论或点赞数据
	 * @see com.work.dao.circle.IDiscussDao#saveDiscuss()
	 * @author       huangqingjian/0050
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        清清网系统, 2014年8月18日
	 * @throws DaoException
	 */
	@Override
	public void saveDiscuss(Discuss discuss) throws DaoException {
		hibernateDao.save(discuss);
		
	}

	/**
	 * 覆盖方法/实现方法(选择其一)
	 * (功能详细描述)
	 * @see com.work.dao.circle.IDiscussDao#deleteDiscuss()
	 * @author       huangqingjian/0050
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        清清网系统, 2014年8月18日
	 * @throws DaoException
	 */
	@Override
	public void deleteDiscuss(Discuss discuss) throws DaoException {
		
		hibernateDao.remove(discuss);
	}

	/**
	 * 覆盖方法/实现方法(选择其一)
	 * 获得某条动态的所有评论
	 * @see com.work.dao.circle.IDiscussDao#findByMessage(int)
	 * @author       huangqingjian/0050
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        清清网系统, 2014年8月18日
	 * @param mid
	 * @throws DaoException
	 */
	@Override
	public void findDiscussByMessage(Page<Discuss> page,String mid,Discuss discuss) throws DaoException {
		StringBuilder builder = new StringBuilder();
		List<Object> parm = new ArrayList<Object>();
		builder.append(" from Message where 1=1 ");
		builder.append(generateHQLCondition(discuss, parm));
		builder.append(" order by createtime desc");
		try {
			setPageSize(page, discuss);
			hibernateDao.findByPage(page, builder.toString(), parm.toArray());
		} catch (DaoException e) {
			try {
				ExceptionHandle.throwServiceException(e, ErrorCode.DAO_SAVE_ERROR_001,
						"MessageDao.queryMessagetByPage");
			} catch (ServiceException e1) {
				 
				e1.printStackTrace();
			}
		}
		
	}
	
	
	/**
	 * 构建查询条件
	 * (一句话功能简述)
	 * (功能详细描述)
	 * @author       huangqingjian/0050
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        二维码系统, 2014年7月5日
	 * @param vo
	 * @return
	 */
	private StringBuilder generateHQLCondition(Discuss vo, List<Object> parm) {
		StringBuilder builder = new StringBuilder();
		return builder;
	}

	/**
	 * 设置总页数
	 * (一句话功能简述)
	 * (功能详细描述)
	 * @author       huangqingjian/0050
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        二维码系统, 2014年7月5日
	 * @param page
	 * @param vo
	 * @throws DaoException
	 */
	private void setPageSize(Page<Discuss> page, Discuss vo) throws DaoException {
		StringBuilder builder = new StringBuilder();
		List<Object> parm = new ArrayList<Object>();
		builder.append("select count(id)  from Discuss where 1=1 ");
		builder.append(generateHQLCondition(vo, parm));
		page.setTotalCount(hibernateDao.findNumByHQL(builder.toString(), parm.toArray()));
	}


	/**
	 * 覆盖方法/实现方法(选择其一)
	 * (功能详细描述)
	 * @see com.work.dao.circle.IDiscussDao#removeAllByMessageId(java.lang.String)
	 * @author       huangqingjian/0050
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        清清网系统, 2014年8月20日
	 * @param messageid
	 * @throws DaoException 
	 */
	@Override
	public void deleteAll(List<Discuss> lst) throws DaoException {
		hibernateDao.removeAll(lst);
		
	}

}
