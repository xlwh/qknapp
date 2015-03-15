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
import com.qing.common.util.StringUtil;
import com.work.dao.complaint.ICritiqueInfoDao;
import com.work.domain.CritiqueInfo;

/**
 * 点评数据访问
 * (功能详细描述)
 * @author       huangqingjian/0050
 * @see          [相关类,可选,也可多条,对于重要的类或接口建议注释]
 * @since        清清网系统, 2014年7月25日
 */
@Repository
public class CritiqueInfoDaoImpl implements ICritiqueInfoDao {

	@Resource
	HibernateDao<CritiqueInfo, String> hibernateDao;

	/**
	 * 新增或保存点评
	 * (一句话功能简述)
	 * (功能详细描述)
	 * @author       huangqingjian/0050
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        清清网系统, 2014年7月25日
	 * @param info
	 */
	public void saveOrUpdateCritiqueInfo(CritiqueInfo CritiqueInfo) throws DaoException {

		hibernateDao.saveOrUpdate(CritiqueInfo);

	}

	/**
	 * 删除点评
	 * (一句话功能简述)
	 * (功能详细描述)
	 * @author       huangqingjian/0050
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        清清网系统, 2014年7月25日
	 * @param infos
	 */
	public void deleteCritiqueInfos(List<CritiqueInfo> CritiqueInfos) throws DaoException {
		hibernateDao.removeAll(CritiqueInfos);
	}

	/**
	 * 根据ID获取点评详情
	 * (一句话功能简述)
	 * (功能详细描述)
	 * @author       huangqingjian/0050
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        清清网系统, 2014年7月25日
	 * @param id
	 * @return
	 */
	public CritiqueInfo getCritiqueInfo(String id) throws DaoException {
		return hibernateDao.findById(CritiqueInfo.class, id);
	}

	/**
	 * 根据查询条件分页查询点评
	 * (一句话功能简述)
	 * (功能详细描述)
	 * @author       huangqingjian/0050
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        清清网系统, 2014年7月25日
	 * @param page
	 * @param info
	 */
	public void queryCritiqueInfo(Page<CritiqueInfo> page, CritiqueInfo CritiqueInfo) throws DaoException {
		StringBuilder builder = new StringBuilder();
		List<Object> parm = new ArrayList<Object>();
		builder.append(" from CritiqueInfo where 1=1 ");
		builder.append(generateHQLCondition(CritiqueInfo, parm));
		builder.append(" order by createDate desc");
		setPageSize(page, CritiqueInfo);
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
	private StringBuilder generateHQLCondition(CritiqueInfo critiqueInfo, List<Object> parm) {
		StringBuilder builder = new StringBuilder();
		if (null == critiqueInfo) {
			return builder;
		}
		if (null != critiqueInfo.getInfoType()) {
			builder.append(" and infoType = ?");
			parm.add(critiqueInfo.getInfoType());
		}
		if (null != critiqueInfo.getInfoId()) {
			builder.append(" and infoId = ?");
			parm.add(critiqueInfo.getInfoId());
		}
		if (null != critiqueInfo.getMemberId()) {
			builder.append(" and memberId = ?");
			parm.add(critiqueInfo.getMemberId());
		}
		if (null != critiqueInfo.getType()) {
			builder.append(" and type = ?");
			parm.add(critiqueInfo.getType());
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
	private void setPageSize(Page<CritiqueInfo> page, CritiqueInfo critiqueInfo) throws DaoException {
		int size = getSize(critiqueInfo);
		page.setTotalCount(size);
	}

	/**
	 * 获取总数
	 * (一句话功能简述)
	 * (功能详细描述)
	 * @author       huangqingjian/0050
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        清清网系统, 2014年10月6日
	 * @param critiqueInfo
	 * @return
	 * @throws DaoException
	 */
	@Override
	public int getSize(CritiqueInfo critiqueInfo) throws DaoException {
		StringBuilder builder = new StringBuilder();
		List<Object> parm = new ArrayList<Object>();
		builder.append("select count(critiqueId)  from CritiqueInfo where 1=1 ");
		builder.append(generateHQLCondition(critiqueInfo, parm));
		return hibernateDao.findNumByHQL(builder.toString(), parm.toArray());
	}

	/**
	 * 不分页查询所有点评
	 * (一句话功能简述)
	 * (功能详细描述)
	 * @author       huangqingjian/0050
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        清清网系统, 2014年8月24日
	 * @param critiqueInfo
	 * @return
	 * @throws DaoException
	 */
	public List<CritiqueInfo> queryCritiqueInfo(CritiqueInfo critiqueInfo) throws DaoException {
		return hibernateDao.findByExample(critiqueInfo);
	}

	/**
	 * 获取是否已经存在点赞或者无兴趣
	 * 覆盖方法/实现方法(选择其一)
	 * (功能详细描述)
	 * @see com.work.dao.complaint.ICritiqueInfoDao#getExistInfo(com.work.domain.CritiqueInfo)
	 * @author       huangqingjian/0050
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        清清网系统, 2014年9月27日
	 * @param critiqueInfo
	 * @return
	 * @throws DaoException
	 */
	@Override
	public int getExistInfo(CritiqueInfo critiqueInfo) throws DaoException {
		List<Object> parm = new ArrayList<Object>();
		StringBuilder hql = new StringBuilder();
		hql.append("select count(infoId) from CritiqueInfo where infoId=? and type in(0,5) ");
		parm.add(critiqueInfo.getInfoId());
		if (StringUtil.isValidStr(critiqueInfo.getMemberId())) {
			hql.append(" and memberId = ? ");
			parm.add(critiqueInfo.getMemberId());
		} else {
			hql.append(" and userIp = ? ");
			parm.add(critiqueInfo.getUserIp());
		}
		return hibernateDao.findNumByHQL(hql.toString(), parm.toArray());
	}

	/**
	 * 获取是否已经点过卖男人
	 * (一句话功能简述)
	 * (功能详细描述)
	 * @author       huangqingjian/0050
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        清清网系统, 2014年10月6日
	 * @param critiqueInfo
	 * @return
	 * @throws DaoException
	 */
	@Override
	public int getExistSellMenInfo(CritiqueInfo critiqueInfo) throws DaoException {
		List<Object> parm = new ArrayList<Object>();
		StringBuilder hql = new StringBuilder();
		hql.append("select count(type) from CritiqueInfo where type = 1 ");
		if (StringUtil.isValidStr(critiqueInfo.getMemberId())) {
			hql.append(" and memberId = ? ");
			parm.add(critiqueInfo.getMemberId());
		} else {
			hql.append(" and userIp = ? ");
			parm.add(critiqueInfo.getUserIp());
		}
		return hibernateDao.findNumByHQL(hql.toString(), parm.toArray());
	}
}
