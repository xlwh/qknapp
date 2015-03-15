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
import com.work.dao.job.IDataMiningSetDao;
import com.work.domain.DataMiningSet;

/**
 * 数据挖掘设置数据访问实现
 * (功能详细描述)
 * @author       huangqingjian/0050
 * @see          [相关类,可选,也可多条,对于重要的类或接口建议注释]
 * @since        清清网系统, 2014年8月6日
 */
@Repository
public class DataMiningSetDaoImpl implements IDataMiningSetDao {

	@Resource
	HibernateDao<DataMiningSet, String> hibernateDao;

	/**
	 * 新增或修改数据挖掘设置
	 * 覆盖方法/实现方法(选择其一)
	 * (功能详细描述)
	 * @see com.work.dao.job.IDataMiningSetDao#saveOrUpdateType(com.work.domain.DataMiningSet)
	 * @author       huangqingjian/0050
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        清清网系统, 2014年7月25日
	 * @param Type
	 * @throws DaoException
	 */
	@Override
	public void saveOrUpdateSet(DataMiningSet set) throws DaoException {
		hibernateDao.saveOrUpdate(set);

	}

	/**
	 * 批量保存
	 * 覆盖方法/实现方法(选择其一)
	 * (功能详细描述)
	 * @see com.work.dao.job.IDataMiningSetDao#saveOrUpdateSets(java.util.List)
	 * @author       huangqingjian/0050
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        清清网系统, 2014年8月18日
	 * @param sets
	 * @throws DaoException
	 */
	@Override
	public void saveOrUpdateSets(List<DataMiningSet> sets) throws DaoException {
		hibernateDao.saveOrUpdateAll(sets);

	}

	/**
	 * 批量删除数据挖掘设置
	 * 覆盖方法/实现方法(选择其一)
	 * (功能详细描述)
	 * @see com.work.dao.job.IDataMiningSetDao#deleteSet(java.util.List)
	 * @author       huangqingjian/0050
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        清清网系统, 2014年7月25日
	 * @param Sets
	 * @throws DaoException
	 */
	@Override
	public void deleteSet(List<DataMiningSet> sets) throws DaoException {
		hibernateDao.removeAll(sets);
	}

	/**
	 * 根据ID获取详情
	 * 覆盖方法/实现方法(选择其一)
	 * (功能详细描述)
	 * @see com.work.dao.job.IDataMiningSetDao#getDataMiningSet(java.lang.String)
	 * @author       huangqingjian/0050
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        清清网系统, 2014年7月25日
	 * @param id
	 * @return
	 * @throws DaoException
	 */
	@Override
	public DataMiningSet getDataMiningSet(String id) throws DaoException {
		return hibernateDao.findById(DataMiningSet.class, id);
	}

	/**
	 * 分页查询数据挖掘设置
	 * 覆盖方法/实现方法(选择其一)
	 * (功能详细描述)
	 * @see com.work.dao.job.IDataMiningSetDao#queryDataMiningSet(com.qing.common.util.Page, com.work.domain.DataMiningSet)
	 * @author       huangqingjian/0050
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        清清网系统, 2014年7月25日
	 * @param page
	 * @param Set
	 * @throws DaoException
	 */
	@Override
	public void queryDataMiningSet(Page<DataMiningSet> page, DataMiningSet set) throws DaoException {

		StringBuilder builder = new StringBuilder();
		List<Object> parm = new ArrayList<Object>();
		builder.append(" from DataMiningSet where 1=1 ");
		builder.append(generateHQLCondition(set, parm));
		builder.append(" order by createDate desc");
		setPageSize(page, set);
		hibernateDao.findByPage(page, builder.toString(), parm.toArray());

	}

	/**
	 * 构建分页查询条件
	 * (一句话功能简述)
	 * (功能详细描述)
	 * @author       huangqingjian/0050
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        清清网系统, 2014年7月25日
	 * @param Set
	 * @param parm
	 * @return
	 */
	private StringBuilder generateHQLCondition(DataMiningSet set, List<Object> parm) {
		StringBuilder builder = new StringBuilder();
		if (null == set) {
			return builder;
		}

		if (StringUtil.isValidStr(set.getNetName())) {
			builder.append(" and netName like ?");
			parm.add(StringUtil.addLikeSymbol(set.getNetName()));
		}
		if (null != set.getMiningStatus()) {
			builder.append(" and miningStatus = ?");
			parm.add(set.getMiningStatus());
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
	 * @param Set
	 * @throws DaoException
	 */
	private void setPageSize(Page<DataMiningSet> page, DataMiningSet Set) throws DaoException {
		StringBuilder builder = new StringBuilder();
		List<Object> parm = new ArrayList<Object>();
		builder.append("select count(setId)  from DataMiningSet where 1=1 ");
		builder.append(generateHQLCondition(Set, parm));
		page.setTotalCount(hibernateDao.findNumByHQL(builder.toString(), parm.toArray()));
	}

	/**
	 *不分页查询所有的数据挖掘设置
	 * (功能详细描述)
	 * @see com.work.dao.job.IDataMiningSetDao#queryDataMiningSet(com.work.domain.DataMiningSet)
	 * @author       huangqingjian/0050
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        清清网系统, 2014年8月6日
	 * @param Set
	 * @throws DaoException
	 */
	@Override
	public List<DataMiningSet> queryDataMiningSet(DataMiningSet Set) throws DaoException {
		StringBuilder builder = new StringBuilder();
		List<Object> parm = new ArrayList<Object>();
		builder.append(" from DataMiningSet where 1=1 ");
		builder.append(generateHQLCondition(Set, parm));
		return hibernateDao.findByValues(builder.toString(), parm.toArray(), false);
	}

}
