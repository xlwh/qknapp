/*
 * Title:        清清网系统 2014年7月25日
 * Description:  [描述模块的功能、作用、使用方法和注意事项]
 * Copyright:    Copyright (c) 2014
 * Company:      清清网
 * @author       huangqingjian
 * @version      2.0  2014年7月25日
 */
package com.work.dao.job.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.qing.common.base.dao.HibernateDao;
import com.qing.common.exception.DaoException;
import com.qing.common.util.Page;
import com.qing.common.util.StringUtil;
import com.work.dao.job.IPartTimeEngineDao;
import com.work.domain.PartTimeEngine;

/**
 * 数据挖掘引擎兼职信息数据访问
 * (功能详细描述)
 * @author       huangqingjian/0050
 * @see          [相关类,可选,也可多条,对于重要的类或接口建议注释]
 * @since        清清网系统, 2014年7月25日
 */
@Service
@Repository
public class PartTimeEngineDaoImpl implements IPartTimeEngineDao {

	@Resource
	HibernateDao<PartTimeEngine, String> hibernateDao;

	/**
	 * 新增或修改兼职信息
	 * 覆盖方法/实现方法(选择其一)
	 * (功能详细描述)
	 * @see com.work.dao.job.IPartTimeEngineDao#saveOrUpdateInfo(com.work.domain.PartTimeEngine)
	 * @author       huangqingjian/0050
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        清清网系统, 2014年7月25日
	 * @param info
	 * @throws DaoException
	 */
	@Override
	public void saveOrUpdateInfo(PartTimeEngine info) throws DaoException {
		hibernateDao.saveOrUpdate(info);
	}

	/**
	 * 批量保存
	 * 覆盖方法/实现方法(选择其一)
	 * (功能详细描述)
	 * @see com.work.dao.job.IPartTimeEngineDao#saveOrUpdateInfo(java.util.List)
	 * @author       huangqingjian/0050
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        清清网系统, 2014年8月10日
	 * @param infos
	 * @throws DaoException
	 */
	@Override
	public void saveOrUpdateInfo(List<PartTimeEngine> infos) throws DaoException {
		hibernateDao.saveOrUpdateAll(infos);
	}

	/**
	 * 批量删除兼职信息
	 * 覆盖方法/实现方法(选择其一)
	 * (功能详细描述)
	 * @see com.work.dao.job.IPartTimeEngineDao#deleteInfo(java.util.List)
	 * @author       huangqingjian/0050
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        清清网系统, 2014年7月25日
	 * @param infos
	 * @throws DaoException
	 */
	@Override
	public void deleteInfo(List<PartTimeEngine> infos) throws DaoException {
		hibernateDao.removeAll(infos);
	}

	/**
	 * 根据ID获取详情
	 * 覆盖方法/实现方法(选择其一)
	 * (功能详细描述)
	 * @see com.work.dao.job.IPartTimeEngineDao#getPartTimeEngine(java.lang.String)
	 * @author       huangqingjian/0050
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        清清网系统, 2014年7月25日
	 * @param id
	 * @return
	 * @throws DaoException
	 */
	@Override
	public PartTimeEngine getPartTimeEngine(String id) throws DaoException {
		return hibernateDao.findById(PartTimeEngine.class, id);
	}

	/**
	 * 分页查询兼职信息
	 * 覆盖方法/实现方法(选择其一)
	 * (功能详细描述)
	 * @see com.work.dao.job.IPartTimeEngineDao#queryPartTime(com.qing.common.util.Page, com.work.domain.PartTimeEngine)
	 * @author       huangqingjian/0050
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        清清网系统, 2014年7月25日
	 * @param page
	 * @param info
	 * @throws DaoException
	 */
	@Override
	public void queryPartTime(Page<PartTimeEngine> page, PartTimeEngine info) throws DaoException {

		StringBuilder builder = new StringBuilder();
		List<Object> parm = new ArrayList<Object>();
		builder.append(" from PartTimeEngine where 1=1 ");
		builder.append(generateHQLCondition(info, parm));
		builder.append(" order by createDate desc");
		setPageSize(page, info);
		hibernateDao.findByPage(page, builder.toString(), parm.toArray());

	}

	/**
	 * 不分页获取待审核兼职
	 * 覆盖方法/实现方法(选择其一)
	 * (功能详细描述)
	 * @see com.work.dao.job.IPartTimeEngineDao#queryPartTime(com.work.domain.PartTimeEngine)
	 * @author       huangqingjian/0050
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        清清网系统, 2014年8月10日
	 * @param info
	 * @return
	 * @throws DaoException
	 */
	@Override
	public List<PartTimeEngine> queryPartTime(PartTimeEngine info) throws DaoException {
		StringBuilder builder = new StringBuilder();
		List<Object> parm = new ArrayList<Object>();
		builder.append(" from PartTimeEngine where 1=1 ");
		builder.append(generateHQLCondition(info, parm));
		builder.append(" order by createDate desc");
		return hibernateDao.findByValues(builder.toString(), parm.toArray(), false);

	}

	/**
	 * 构建分页查询条件
	 * (一句话功能简述)
	 * (功能详细描述)
	 * @author       huangqingjian/0050
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        清清网系统, 2014年7月25日
	 * @param info
	 * @param parm
	 * @return
	 */
	private StringBuilder generateHQLCondition(PartTimeEngine info, List<Object> parm) {
		StringBuilder builder = new StringBuilder();
		if (null == info) {
			return builder;
		}

		if (StringUtil.isValidStr(info.getPartTimeTitle())) {
			builder.append(" and partTimeTitle like ?");
			parm.add(StringUtil.addLikeSymbol(info.getPartTimeTitle()));
		}
		if (null != info.getBeginTime()) {
			builder.append(" and beginTime <= ?");
			parm.add(info.getBeginTime());
		}
		if (null != info.getEndTime()) {
			builder.append(" and endTime >= ?");
			parm.add(info.getEndTime());
		}
		if (StringUtil.isValidStr(info.getPartTimeContent())) {
			builder.append(" and partTimeContent like ?");
			parm.add(StringUtil.addLikeSymbol(info.getPartTimeContent()));
		}
		if (null != info.getPartTimeType()) {
			if (StringUtil.isValidStr(info.getPartTimeType().getTypeName())) {
				builder.append(" and partTimeType.typeName like ?");
				parm.add(StringUtil.addLikeSymbol(info.getPartTimeType().getTypeName()));
			}
		}
		if (StringUtil.isValidStr(info.getSource())) {
			builder.append(" and source like ?");
			parm.add(StringUtil.addLikeSymbol(info.getSource()));
		}
		if (StringUtil.isValidStr(info.getAreaName())) {
			String areaName = info.getAreaName();
			if (info.getAreaName().length() > 2) {
				areaName = info.getAreaName().substring(0, 2);
			}
			builder.append(" and areaName like ?");
			parm.add(StringUtil.addLikeSymbol(areaName));
		}
		if (StringUtil.isValidStr(info.getCityName())) {
			String cityName = info.getCityName();
			if (info.getCityName().length() > 2) {
				cityName = info.getCityName().substring(0, 2);
			}
			builder.append(" and cityName like ?");
			parm.add(StringUtil.addLikeSymbol(cityName));
		}
		if (StringUtil.isValidStr(info.getProvinceName())) {
			String provinceName = info.getProvinceName();
			if (info.getProvinceName().length() > 2) {
				provinceName = info.getProvinceName().substring(0, 2);
			}
			builder.append(" and provinceName like ?");
			parm.add(StringUtil.addLikeSymbol(provinceName));
		}
		if (null != info.getIsVerify()) {
			builder.append(" and isVerify = ?");
			parm.add(info.getIsVerify());
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
	 * @param info
	 * @throws DaoException
	 */
	private void setPageSize(Page<PartTimeEngine> page, PartTimeEngine info) throws DaoException {
		StringBuilder builder = new StringBuilder();
		List<Object> parm = new ArrayList<Object>();
		builder.append("select count(partTimeId)  from PartTimeEngine where 1=1 ");
		builder.append(generateHQLCondition(info, parm));
		page.setTotalCount(hibernateDao.findNumByHQL(builder.toString(), parm.toArray()));
	}

}
