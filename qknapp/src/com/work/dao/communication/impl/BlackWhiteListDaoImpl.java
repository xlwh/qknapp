/*
 * Title:        清清网系统 2014年7月25日
 * Description:  [描述模块的功能、作用、使用方法和注意事项]
 * Copyright:    Copyright (c) 2014
 * Company:      清清网
 * @author       huangqingjian
 * @version      2.0  2014年7月25日
 */
package com.work.dao.communication.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.qing.common.base.dao.HibernateDao;
import com.qing.common.exception.DaoException;
import com.qing.common.util.Page;
import com.qing.common.util.StringUtil;
import com.work.dao.communication.IBlackWhiteListDao;
import com.work.domain.BlackWhiteList;

/**
 * 黑白名单数据访问
 * (功能详细描述)
 * @author       huangqingjian/0050
 * @see          [相关类,可选,也可多条,对于重要的类或接口建议注释]
 * @since        清清网系统, 2014年7月25日
 */
@Repository
public class BlackWhiteListDaoImpl implements IBlackWhiteListDao {

	@Resource
	HibernateDao<BlackWhiteList, String> hibernateDao;

	/**
	 * 新增或保存黑白名单
	 * (一句话功能简述)
	 * (功能详细描述)
	 * @author       huangqingjian/0050
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        清清网系统, 2014年7月25日
	 * @param info
	 */
	public void saveOrUpdateBlackWhiteList(BlackWhiteList blackWhiteList) throws DaoException {

		hibernateDao.saveOrUpdate(blackWhiteList);

	}

	/**
	 * 删除黑白名单
	 * (一句话功能简述)
	 * (功能详细描述)
	 * @author       huangqingjian/0050
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        清清网系统, 2014年7月25日
	 * @param infos
	 */
	public void deleteBlackWhiteLists(List<BlackWhiteList> blackWhiteLists) throws DaoException {
		hibernateDao.removeAll(blackWhiteLists);
	}

	/**
	 * 根据ID获取黑白名单详情
	 * (一句话功能简述)
	 * (功能详细描述)
	 * @author       huangqingjian/0050
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        清清网系统, 2014年7月25日
	 * @param id
	 * @return
	 */
	public BlackWhiteList getBlackWhiteList(String id) throws DaoException {
		return hibernateDao.findById(BlackWhiteList.class, id);
	}

	/**
	 * 根据查询条件分页查询黑白名单
	 * (一句话功能简述)
	 * (功能详细描述)
	 * @author       huangqingjian/0050
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        清清网系统, 2014年7月25日
	 * @param page
	 * @param info
	 */
	public void queryBlackWhiteList(Page<BlackWhiteList> page, BlackWhiteList blackWhiteList) throws DaoException {
		StringBuilder builder = new StringBuilder();
		List<Object> parm = new ArrayList<Object>();
		builder.append(" from BlackWhiteList where 1=1 ");
		builder.append(generateHQLCondition(blackWhiteList, parm));
		builder.append(" order by createDate desc");
		setPageSize(page, blackWhiteList);
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
	private StringBuilder generateHQLCondition(BlackWhiteList blackWhiteList, List<Object> parm) {
		StringBuilder builder = new StringBuilder();
		if (null == blackWhiteList) {
			return builder;
		}
		if (null != blackWhiteList.getStatus()) {
			builder.append(" and status = ?");
			parm.add(blackWhiteList.getStatus());
		}
		if (StringUtil.isValidStr(blackWhiteList.getContactPhone())) {
			builder.append(" and contactPhone like ?");
			parm.add(StringUtil.addLikeSymbol(blackWhiteList.getContactPhone()));
		}
		if (StringUtil.isValidStr(blackWhiteList.getContacts())) {
			builder.append(" and contacts like ?");
			parm.add(StringUtil.addLikeSymbol(blackWhiteList.getContacts()));
		}
		if (StringUtil.isValidStr(blackWhiteList.getRemarks())) {
			builder.append(" and remarks like ?");
			parm.add(StringUtil.addLikeSymbol(blackWhiteList.getRemarks()));
		}
		if (StringUtil.isValidStr(blackWhiteList.getProvinceName())) {
			builder.append(" and provinceName like ?");
			parm.add(StringUtil.addLikeSymbol(blackWhiteList.getProvinceName()));
		}
		if (StringUtil.isValidStr(blackWhiteList.getCityName())) {
			builder.append(" and cityName like ?");
			parm.add(StringUtil.addLikeSymbol(blackWhiteList.getCityName()));
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
	private void setPageSize(Page<BlackWhiteList> page, BlackWhiteList blackWhiteList) throws DaoException {
		StringBuilder builder = new StringBuilder();
		List<Object> parm = new ArrayList<Object>();
		builder.append("select count(id)  from BlackWhiteList where 1=1 ");
		builder.append(generateHQLCondition(blackWhiteList, parm));
		page.setTotalCount(hibernateDao.findNumByHQL(builder.toString(), parm.toArray()));
	}

	/**
	 * 不分页查询所有黑白名单
	 * (一句话功能简述)
	 * (功能详细描述)
	 * @author       huangqingjian/0050
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        清清网系统, 2014年8月24日
	 * @param blackWhiteList
	 * @return
	 * @throws DaoException
	 */
	public List<BlackWhiteList> queryBlackWhiteList(BlackWhiteList blackWhiteList) throws DaoException {
		return hibernateDao.findByExample(blackWhiteList);
	}

	/**
	 * 检查是否存在该手机号码
	 * 覆盖方法/实现方法(选择其一)
	 * (功能详细描述)
	 * @see com.work.dao.communication.IBlackWhiteListDao#checkExist(com.work.domain.BlackWhiteList)
	 * @author       huangqingjian/0050
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        清清网系统, 2014年10月13日
	 * @param blackWhiteList
	 * @return
	 * @throws DaoException
	 */
	@Override
	public int checkExist(BlackWhiteList blackWhiteList) throws DaoException {
		List<Object> parm = new ArrayList<Object>();
		StringBuilder hql = new StringBuilder();
		hql.append("select count(id) from BlackWhiteList where contactPhone = ? ");
		parm.add(blackWhiteList.getContactPhone());
		if (StringUtil.isValidStr(blackWhiteList.getId())) {
			hql.append(" and id != ?");
			parm.add(blackWhiteList.getId());
		}
		return hibernateDao.findNumByHQL(hql.toString(), parm.toArray());
	}

}
