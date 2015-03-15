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
import com.work.dao.communication.IMemAddressListDao;
import com.work.domain.MemAddressList;

/**
 * 通讯录数据访问
 * (功能详细描述)
 * @author       huangqingjian/0050
 * @see          [相关类,可选,也可多条,对于重要的类或接口建议注释]
 * @since        清清网系统, 2014年7月25日
 */
@Repository
public class MemAddressListDaoImpl implements IMemAddressListDao {

	@Resource
	HibernateDao<MemAddressList, String> hibernateDao;

	/**
	 * 新增或保存通讯录
	 * (一句话功能简述)
	 * (功能详细描述)
	 * @author       huangqingjian/0050
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        清清网系统, 2014年7月25日
	 * @param info
	 */
	public void saveOrUpdateMemAddressList(MemAddressList MemAddressList) throws DaoException {

		hibernateDao.saveOrUpdate(MemAddressList);

	}

	/**
	 * 删除通讯录
	 * (一句话功能简述)
	 * (功能详细描述)
	 * @author       huangqingjian/0050
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        清清网系统, 2014年7月25日
	 * @param infos
	 */
	public void deleteMemAddressLists(List<MemAddressList> MemAddressLists) throws DaoException {
		hibernateDao.removeAll(MemAddressLists);
	}

	/**
	 * 根据ID获取通讯录详情
	 * (一句话功能简述)
	 * (功能详细描述)
	 * @author       huangqingjian/0050
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        清清网系统, 2014年7月25日
	 * @param id
	 * @return
	 */
	public MemAddressList getMemAddressList(String id) throws DaoException {
		return hibernateDao.findById(MemAddressList.class, id);
	}

	/**
	 * 根据查询条件分页查询通讯录
	 * (一句话功能简述)
	 * (功能详细描述)
	 * @author       huangqingjian/0050
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        清清网系统, 2014年7月25日
	 * @param page
	 * @param info
	 */
	public void queryMemAddressList(Page<MemAddressList> page, MemAddressList MemAddressList) throws DaoException {
		StringBuilder builder = new StringBuilder();
		List<Object> parm = new ArrayList<Object>();
		builder.append(" from MemAddressList where 1=1 ");
		builder.append(generateHQLCondition(MemAddressList, parm));
		builder.append(" order by createDate desc");
		setPageSize(page, MemAddressList);
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
	private StringBuilder generateHQLCondition(MemAddressList memAddressList, List<Object> parm) {
		StringBuilder builder = new StringBuilder();
		if (null == memAddressList) {
			return builder;
		}
		if (null != memAddressList.getStarTarget()) {
			builder.append(" and starTarget = ?");
			parm.add(memAddressList.getStarTarget());
		}
		if (StringUtil.isValidStr(memAddressList.getContactPhone())) {
			builder.append(" and contactPhone like ?");
			parm.add(StringUtil.addLikeSymbol(memAddressList.getContactPhone()));
		}
		if (StringUtil.isValidStr(memAddressList.getMemberId())) {
			builder.append(" and memberId = ?");
			parm.add(memAddressList.getMemberId());
		}
		if (StringUtil.isValidStr(memAddressList.getProvinceName())) {
			builder.append(" and provinceName like ?");
			parm.add(StringUtil.addLikeSymbol(memAddressList.getProvinceName()));
		}
		if (StringUtil.isValidStr(memAddressList.getCityName())) {
			builder.append(" and cityName like ?");
			parm.add(StringUtil.addLikeSymbol(memAddressList.getCityName()));
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
	private void setPageSize(Page<MemAddressList> page, MemAddressList memAddressList) throws DaoException {
		StringBuilder builder = new StringBuilder();
		List<Object> parm = new ArrayList<Object>();
		builder.append("select count(listId)  from MemAddressList where 1=1 ");
		builder.append(generateHQLCondition(memAddressList, parm));
		page.setTotalCount(hibernateDao.findNumByHQL(builder.toString(), parm.toArray()));
	}

	/**
	 * 不分页查询所有通讯录
	 * (一句话功能简述)
	 * (功能详细描述)
	 * @author       huangqingjian/0050
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        清清网系统, 2014年8月24日
	 * @param memAddressList
	 * @return
	 * @throws DaoException
	 */
	public List<MemAddressList> queryMemAddressList(MemAddressList memAddressList) throws DaoException {
		return hibernateDao.findByExample(memAddressList);
	}

}
