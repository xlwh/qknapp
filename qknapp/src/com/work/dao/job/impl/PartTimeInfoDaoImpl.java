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
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.qing.common.base.dao.HibernateDao;
import com.qing.common.exception.DaoException;
import com.qing.common.util.Page;
import com.qing.common.util.StringUtil;
import com.work.dao.job.IPartTimeInfoDao;
import com.work.domain.PartTimeInfo;
import com.work.domain.PartTimeType;

/**
 * 兼职信息数据访问
 * (功能详细描述)
 * @author       huangqingjian/0050
 * @see          [相关类,可选,也可多条,对于重要的类或接口建议注释]
 * @since        清清网系统, 2014年7月25日
 */
@Repository
public class PartTimeInfoDaoImpl implements IPartTimeInfoDao {

	@Resource
	HibernateDao<PartTimeInfo, String> hibernateDao;

	/**
	 * 新增或修改兼职信息
	 * 覆盖方法/实现方法(选择其一)
	 * (功能详细描述)
	 * @see com.work.dao.job.IPartTimeInfoDao#saveOrUpdateInfo(com.work.domain.PartTimeInfo)
	 * @author       huangqingjian/0050
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        清清网系统, 2014年7月25日
	 * @param info
	 * @throws DaoException
	 */
	@Override
	public void saveOrUpdateInfo(PartTimeInfo info) throws DaoException {
		hibernateDao.saveOrUpdate(info);

	}

	/**
	 * 批量删除兼职信息
	 * 覆盖方法/实现方法(选择其一)
	 * (功能详细描述)
	 * @see com.work.dao.job.IPartTimeInfoDao#deleteInfo(java.util.List)
	 * @author       huangqingjian/0050
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        清清网系统, 2014年7月25日
	 * @param infos
	 * @throws DaoException
	 */
	@Override
	public void deleteInfo(List<PartTimeInfo> infos) throws DaoException {
		hibernateDao.removeAll(infos);
	}

	/**
	 * 根据ID获取详情
	 * 覆盖方法/实现方法(选择其一)
	 * (功能详细描述)
	 * @see com.work.dao.job.IPartTimeInfoDao#getPartTimeInfo(java.lang.String)
	 * @author       huangqingjian/0050
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        清清网系统, 2014年7月25日
	 * @param id
	 * @return
	 * @throws DaoException
	 */
	@Override
	public PartTimeInfo getPartTimeInfo(String id) throws DaoException {
		return hibernateDao.findById(PartTimeInfo.class, id);
	}

	/**
	 * 分页查询兼职信息
	 * 覆盖方法/实现方法(选择其一)
	 * (功能详细描述)
	 * @see com.work.dao.job.IPartTimeInfoDao#queryPartTime(com.qing.common.util.Page, com.work.domain.PartTimeInfo)
	 * @author       huangqingjian/0050
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        清清网系统, 2014年7月25日
	 * @param page
	 * @param info
	 * @throws DaoException
	 */
	@Override
	public void queryPartTime(Page<PartTimeInfo> page, PartTimeInfo info) throws DaoException {

		StringBuilder builder = new StringBuilder();
		List<Object> parm = new ArrayList<Object>();
		builder.append(" from PartTimeInfo where 1=1 ");
		builder.append(generateHQLCondition(info, parm));
		builder.append(" order by stickStatus desc,isRecommend desc,praiseNum desc, createDate desc");
		setPageSize(page, info);
		hibernateDao.findByPage(page, builder.toString(), parm.toArray());

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
	private StringBuilder generateHQLCondition(PartTimeInfo info, List<Object> parm) {
		StringBuilder builder = new StringBuilder();
		if (null == info) {
			return builder;
		}
		if (StringUtil.isValidStr(info.getPartTimeId())) {
			builder.append(" and partTimeId like ?");
			parm.add(StringUtil.addLikeSymbol(info.getPartTimeId()));
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
		if (StringUtil.isValidStr(info.getCellPhone())) {
			builder.append(" and cellPhone = ?");
			parm.add(info.getCellPhone());
		}
		if (StringUtil.isValidStr(info.getAreaName())) {
			builder.append(" and areaName like ?");
			parm.add(StringUtil.addLikeSymbol(info.getAreaName()));
		}
		//根据城市加载兼职，全国性的兼职也需要加载出来
		if (StringUtil.isValidStr(info.getCityName())) {
			builder.append(" and ( cityName like ? or nationwide = 1 )");
			parm.add(StringUtil.addLikeSymbol(info.getCityName()));

		}
		if (StringUtil.isValidStr(info.getProvinceName())) {
			builder.append(" and provinceName like ?");
			parm.add(StringUtil.addLikeSymbol(info.getProvinceName()));
		}
		if (null != info.getGenderReq() && 1 == info.getGenderReq()) {
			builder.append(" and genderReq != 0");
		}
		if (null != info.getGenderReq() && 0 == info.getGenderReq()) {
			builder.append(" and genderReq != 1");
		}
		Set<PartTimeType> partTimeTypes = info.getPartTimeTypes();
		if (null != partTimeTypes && partTimeTypes.size() > 0) {

			int size = partTimeTypes.size();
			Iterator<PartTimeType> iterator = partTimeTypes.iterator();
			if (1 == size) {
				if (iterator.hasNext()) {
					builder.append("  and partTimeType.typeId = ?");
					parm.add(iterator.next().getTypeId());
				}
			} else {
				int i = 0;
				while (iterator.hasNext()) {
					if (0 == i) {
						builder.append(" and (partTimeType.typeId = ?");
						parm.add(iterator.next().getTypeId());
					} else if (i == size - 1) {
						builder.append(" or partTimeType.typeId = ?)");
						parm.add(iterator.next().getTypeId());
					} else {
						builder.append(" or partTimeType.typeId = ?");
						parm.add(iterator.next().getTypeId());
					}
					i++;
				}
			}
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
	private void setPageSize(Page<PartTimeInfo> page, PartTimeInfo info) throws DaoException {
		int size = getPageSize(info);
		page.setTotalCount(size);
	}

	/**
	 * (一句话功能简述)
	 * (功能详细描述)
	 * @author       huangqingjian/0050
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        清清网系统, 2014年10月16日
	 * @param info
	 * @return
	 * @throws DaoException
	 */
	@Override
	public int getPageSize(PartTimeInfo info) throws DaoException {
		StringBuilder builder = new StringBuilder();
		List<Object> parm = new ArrayList<Object>();
		builder.append("select count(partTimeId)  from PartTimeInfo where 1=1 ");
		builder.append(generateHQLCondition(info, parm));
		int size = hibernateDao.findNumByHQL(builder.toString(), parm.toArray());
		return size;
	}

	/**
	 * 置顶兼职信息
	 * 覆盖方法/实现方法(选择其一)
	 * (功能详细描述)
	 * @see com.work.dao.job.IPartTimeInfoDao#setStickStatus(com.work.domain.PartTimeInfo)
	 * @author       huangqingjian/0050
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        清清网系统, 2014年7月25日
	 * @param info
	 * @throws DaoException
	 */
	@Override
	public void setStickStatus(PartTimeInfo info) throws DaoException {
		hibernateDao.saveOrUpdate(info);

	}

}
