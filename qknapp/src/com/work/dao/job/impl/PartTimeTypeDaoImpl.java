/*
 * Title:        清清网系统 2014年8月6日
 * Description:  [描述模块的功能、作用、使用方法和注意事项]
 * Copyright:    Copyright (c) 2014
 * Company:      清清网
 * @author       huangqingjian
 * @version      2.0  2014年8月6日
 */
package com.work.dao.job.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.qing.common.base.dao.HibernateDao;
import com.qing.common.exception.DaoException;
import com.qing.common.util.Page;
import com.qing.common.util.StringUtil;
import com.work.dao.job.IPartTimeTypeDao;
import com.work.domain.PartTimeType;

/**
 * 兼职类型数据访问实现
 * (功能详细描述)
 * @author       huangqingjian/0050
 * @see          [相关类,可选,也可多条,对于重要的类或接口建议注释]
 * @since        清清网系统, 2014年8月6日
 */
@Repository
public class PartTimeTypeDaoImpl implements IPartTimeTypeDao {

	@Resource
	HibernateDao<PartTimeType, String> hibernateDao;

	/**
	 * 新增或修改兼职类型
	 * 覆盖方法/实现方法(选择其一)
	 * (功能详细描述)
	 * @see com.work.dao.job.IPartTimeTypeDao#saveOrUpdateType(com.work.domain.PartTimeType)
	 * @author       huangqingjian/0050
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        清清网系统, 2014年7月25日
	 * @param Type
	 * @throws DaoException
	 */
	@Override
	public void saveOrUpdateType(PartTimeType Type) throws DaoException {
		hibernateDao.saveOrUpdate(Type);

	}

	/**
	 * 批量删除兼职类型
	 * 覆盖方法/实现方法(选择其一)
	 * (功能详细描述)
	 * @see com.work.dao.job.IPartTimeTypeDao#deleteType(java.util.List)
	 * @author       huangqingjian/0050
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        清清网系统, 2014年7月25日
	 * @param Types
	 * @throws DaoException
	 */
	@Override
	public void deleteType(List<PartTimeType> Types) throws DaoException {
		hibernateDao.removeAll(Types);
	}

	/**
	 * 根据ID获取详情
	 * 覆盖方法/实现方法(选择其一)
	 * (功能详细描述)
	 * @see com.work.dao.job.IPartTimeTypeDao#getPartTimeType(java.lang.String)
	 * @author       huangqingjian/0050
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        清清网系统, 2014年7月25日
	 * @param id
	 * @return
	 * @throws DaoException
	 */
	@Override
	public PartTimeType getPartTimeType(String id) throws DaoException {
		return hibernateDao.findById(PartTimeType.class, id);
	}

	/**
	 * 分页查询兼职类型
	 * 覆盖方法/实现方法(选择其一)
	 * (功能详细描述)
	 * @see com.work.dao.job.IPartTimeTypeDao#queryPartTime(com.qing.common.util.Page, com.work.domain.PartTimeType)
	 * @author       huangqingjian/0050
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        清清网系统, 2014年7月25日
	 * @param page
	 * @param Type
	 * @throws DaoException
	 */
	@Override
	public void queryPartTime(Page<PartTimeType> page, PartTimeType Type) throws DaoException {

		StringBuilder builder = new StringBuilder();
		List<Object> parm = new ArrayList<Object>();
		builder.append(" from PartTimeType where 1=1 ");
		builder.append(generateHQLCondition(Type, parm));
		builder.append(" order by createDate desc");
		setPageSize(page, Type);
		hibernateDao.findByPage(page, builder.toString(), parm.toArray());

	}

	/**
	 * 构建分页查询条件
	 * (一句话功能简述)
	 * (功能详细描述)
	 * @author       huangqingjian/0050
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        清清网系统, 2014年7月25日
	 * @param Type
	 * @param parm
	 * @return
	 */
	private StringBuilder generateHQLCondition(PartTimeType Type, List<Object> parm) {
		StringBuilder builder = new StringBuilder();
		if (null == Type) {
			return builder;
		}

		if (StringUtil.isValidStr(Type.getTypeName())) {
			builder.append(" and typeName like ?");
			parm.add(StringUtil.addLikeSymbol(Type.getTypeName()));
		}

		return builder;

	}

	/**
	 * 设置分页总数
	 * (一句话功能简述)
	 * (功能详细描述)
	 * @author       huangqingjian/0050
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        清清网系统, 2014年7月25日
	 * @param page
	 * @param Type
	 * @throws DaoException
	 */
	private void setPageSize(Page<PartTimeType> page, PartTimeType Type) throws DaoException {
		StringBuilder builder = new StringBuilder();
		List<Object> parm = new ArrayList<Object>();
		builder.append("select count(typeId)  from PartTimeType where 1=1 ");
		builder.append(generateHQLCondition(Type, parm));
		page.setTotalCount(hibernateDao.findNumByHQL(builder.toString(), parm.toArray()));
	}

	/**
	 *不分页查询所有的兼职
	 * (功能详细描述)
	 * @see com.work.dao.job.IPartTimeTypeDao#queryPartTime(com.work.domain.PartTimeType)
	 * @author       huangqingjian/0050
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        清清网系统, 2014年8月6日
	 * @param type
	 * @throws DaoException
	 */
	@Override
	public List<PartTimeType> queryPartTime(PartTimeType type) throws DaoException {
		StringBuilder builder = new StringBuilder();
		List<Object> parm = new ArrayList<Object>();
		builder.append(" from PartTimeType where 1=1 ");
		builder.append(generateHQLCondition(type, parm));
		builder.append(" order by createDate desc");
		return hibernateDao.findByValues(builder.toString(), parm.toArray(), false);
	}

}
