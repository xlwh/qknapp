/*
 * Title:        清清网系统 2014年7月25日
 * Description:  [描述模块的功能、作用、使用方法和注意事项]
 * Copyright:    Copyright (c) 2014
 * Company:      清清网
 * @author       huangqingjian
 * @version      2.0  2014年7月25日
 */
package com.work.dao.complaint.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.qing.common.base.dao.HibernateDao;
import com.qing.common.exception.DaoException;
import com.qing.common.util.Page;
import com.work.dao.complaint.ICollectInfoDao;
import com.work.domain.CollectInfo;

/**
 * 收藏数据访问
 * (功能详细描述)
 * @author       huangqingjian/0050
 * @see          [相关类,可选,也可多条,对于重要的类或接口建议注释]
 * @since        清清网系统, 2014年7月25日
 */
@Repository
public class CollectInfoDaoImpl implements ICollectInfoDao {

	@Resource
	HibernateDao<CollectInfo, String> hibernateDao;

	/**
	 * 新增或保存收藏
	 * (一句话功能简述)
	 * (功能详细描述)
	 * @author       huangqingjian/0050
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        清清网系统, 2014年7月25日
	 * @param info
	 */
	public void saveOrUpdateCollectInfo(CollectInfo CollectInfo) throws DaoException {

		hibernateDao.saveOrUpdate(CollectInfo);

	}

	/**
	 * 删除收藏
	 * (一句话功能简述)
	 * (功能详细描述)
	 * @author       huangqingjian/0050
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        清清网系统, 2014年7月25日
	 * @param infos
	 */
	public void deleteCollectInfos(List<CollectInfo> CollectInfos) throws DaoException {
		hibernateDao.removeAll(CollectInfos);
	}

	/**
	 * 根据ID获取收藏详情
	 * (一句话功能简述)
	 * (功能详细描述)
	 * @author       huangqingjian/0050
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        清清网系统, 2014年7月25日
	 * @param id
	 * @return
	 */
	public CollectInfo getCollectInfo(String id) throws DaoException {
		return hibernateDao.findById(CollectInfo.class, id);
	}

	/**
	 * 根据查询条件分页查询收藏
	 * (一句话功能简述)
	 * (功能详细描述)
	 * @author       huangqingjian/0050
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        清清网系统, 2014年7月25日
	 * @param page
	 * @param info
	 */
	public void queryCollectInfo(Page<CollectInfo> page, CollectInfo CollectInfo) throws DaoException {
		StringBuilder builder = new StringBuilder();
		List<Object> parm = new ArrayList<Object>();
		builder.append(" from CollectInfo where 1=1 ");
		builder.append(generateHQLCondition(CollectInfo, parm));
		builder.append(" order by createDate desc");
		setPageSize(page, CollectInfo);
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
	private StringBuilder generateHQLCondition(CollectInfo collectInfo, List<Object> parm) {
		StringBuilder builder = new StringBuilder();
		if (null == collectInfo) {
			return builder;
		}
		if (null != collectInfo.getInfoType()) {
			builder.append(" and infoType = ?");
			parm.add(collectInfo.getInfoType());
		}
		if (null != collectInfo.getInfoId()) {
			builder.append(" and infoId = ?");
			parm.add(collectInfo.getInfoId());
		}
		if (null != collectInfo.getMemberId()) {
			builder.append(" and memberId = ?");
			parm.add(collectInfo.getMemberId());
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
	private void setPageSize(Page<CollectInfo> page, CollectInfo collectInfo) throws DaoException {
		int size = getSize(collectInfo);
		page.setTotalCount(size);
	}

	/**
	 * 根据条件获取总数
	 * (一句话功能简述)
	 * (功能详细描述)
	 * @author       huangqingjian/0050
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        清清网系统, 2014年10月18日
	 * @param collectInfo
	 * @return
	 * @throws DaoException
	 */
	@Override
	public int getSize(CollectInfo collectInfo) throws DaoException {
		StringBuilder builder = new StringBuilder();
		List<Object> parm = new ArrayList<Object>();
		builder.append("select count(collectId)  from CollectInfo where 1=1 ");
		builder.append(generateHQLCondition(collectInfo, parm));
		int size = hibernateDao.findNumByHQL(builder.toString(), parm.toArray());
		return size;
	}

	/**
	 * 不分页查询所有收藏
	 * (一句话功能简述)
	 * (功能详细描述)
	 * @author       huangqingjian/0050
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        清清网系统, 2014年8月24日
	 * @param collectInfo
	 * @return
	 * @throws DaoException
	 */
	public List<CollectInfo> queryCollectInfo(CollectInfo collectInfo) throws DaoException {
		return hibernateDao.findByExample(collectInfo);
	}

	/**
	 * 判断这个收藏是否存在
	 * 覆盖方法/实现方法(选择其一)
	 * (功能详细描述)
	 * @see com.work.dao.complaint.ICollectInfoDao#getExistCollectInfo(java.lang.String)
	 * @author       huangqingjian/0050
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        清清网系统, 2014年9月27日
	 * @param infoId
	 * @return
	 * @throws DaoException
	 */
	@Override
	public int getExistCollectInfo(String infoId) throws DaoException {
		StringBuilder hql = new StringBuilder();
		hql.append("select count(infoId) from CollectInfo where infoId=?");

		return hibernateDao.findNumByHQL(hql.toString(), new Object[] { infoId });
	}

}
