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
import com.work.dao.communication.IBusinessDirectoryDao;
import com.work.domain.BusinessDirectory;

/**
 * 企业名录数据访问
 * (功能详细描述)
 * @author       huangqingjian/0050
 * @see          [相关类,可选,也可多条,对于重要的类或接口建议注释]
 * @since        清清网系统, 2014年7月25日
 */
@Repository
public class BusinessDirectoryDaoImpl implements IBusinessDirectoryDao {

	@Resource
	HibernateDao<BusinessDirectory, String> hibernateDao;

	/**
	 * 新增或保存企业名录
	 * (一句话功能简述)
	 * (功能详细描述)
	 * @author       huangqingjian/0050
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        清清网系统, 2014年7月25日
	 * @param info
	 */
	public void saveOrUpdateBusinessDirectory(BusinessDirectory businessDirectory) throws DaoException {

		hibernateDao.saveOrUpdate(businessDirectory);

	}

	/**
	 * 删除企业名录
	 * (一句话功能简述)
	 * (功能详细描述)
	 * @author       huangqingjian/0050
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        清清网系统, 2014年7月25日
	 * @param infos
	 */
	public void deleteBusinessDirectorys(List<BusinessDirectory> businessDirectorys) throws DaoException {
		hibernateDao.removeAll(businessDirectorys);
	}

	/**
	 * 根据ID获取企业名录详情
	 * (一句话功能简述)
	 * (功能详细描述)
	 * @author       huangqingjian/0050
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        清清网系统, 2014年7月25日
	 * @param id
	 * @return
	 */
	public BusinessDirectory getBusinessDirectory(String id) throws DaoException {
		return hibernateDao.findById(BusinessDirectory.class, id);
	}

	/**
	 * 根据查询条件分页查询企业名录
	 * (一句话功能简述)
	 * (功能详细描述)
	 * @author       huangqingjian/0050
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        清清网系统, 2014年7月25日
	 * @param page
	 * @param info
	 */
	public void queryBusinessDirectory(Page<BusinessDirectory> page, BusinessDirectory businessDirectory)
			throws DaoException {
		StringBuilder builder = new StringBuilder();
		List<Object> parm = new ArrayList<Object>();
		builder.append(" from BusinessDirectory where 1=1 ");
		builder.append(generateHQLCondition(businessDirectory, parm));
		builder.append(" order by createDate desc");
		setPageSize(page, businessDirectory);
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
	private StringBuilder generateHQLCondition(BusinessDirectory businessDirectory, List<Object> parm) {
		StringBuilder builder = new StringBuilder();
		if (null == businessDirectory) {
			return builder;
		}
		if (null != businessDirectory.getStatus()) {
			builder.append(" and status = ?");
			parm.add(businessDirectory.getStatus());
		}
		if (null != businessDirectory.getId()) {
			builder.append(" and id = ?");
			parm.add(businessDirectory.getId());
		}
		if (StringUtil.isValidStr(businessDirectory.getContactPhone())) {
			builder.append(" and contactPhone like ?");
			parm.add(StringUtil.addLikeSymbol(businessDirectory.getContactPhone()));
		}
		if (StringUtil.isValidStr(businessDirectory.getContacts())) {
			builder.append(" and contacts like ?");
			parm.add(StringUtil.addLikeSymbol(businessDirectory.getContacts()));
		}
		if (StringUtil.isValidStr(businessDirectory.getRemarks())) {
			builder.append(" and remarks like ?");
			parm.add(StringUtil.addLikeSymbol(businessDirectory.getRemarks()));
		}
		if (StringUtil.isValidStr(businessDirectory.getNoteName())) {
			builder.append(" and noteName like ?");
			parm.add(StringUtil.addLikeSymbol(businessDirectory.getNoteName()));
		}
		if (StringUtil.isValidStr(businessDirectory.getProvinceName())) {
			builder.append(" and provinceName like ?");
			parm.add(StringUtil.addLikeSymbol(businessDirectory.getProvinceName()));
		}
		if (StringUtil.isValidStr(businessDirectory.getCityName())) {
			builder.append(" and cityName like ?");
			parm.add(StringUtil.addLikeSymbol(businessDirectory.getCityName()));
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
	private void setPageSize(Page<BusinessDirectory> page, BusinessDirectory businessDirectory) throws DaoException {
		StringBuilder builder = new StringBuilder();
		List<Object> parm = new ArrayList<Object>();
		builder.append("select count(id)  from BusinessDirectory where 1=1 ");
		builder.append(generateHQLCondition(businessDirectory, parm));
		page.setTotalCount(hibernateDao.findNumByHQL(builder.toString(), parm.toArray()));
	}

	/**
	 * 不分页查询所有企业名录
	 * (一句话功能简述)
	 * (功能详细描述)
	 * @author       huangqingjian/0050
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        清清网系统, 2014年8月24日
	 * @param businessDirectory
	 * @return
	 * @throws DaoException
	 */
	public List<BusinessDirectory> queryBusinessDirectory(BusinessDirectory businessDirectory) throws DaoException {
		return hibernateDao.findByExample(businessDirectory);
	}

	/**
	 * 检查是否存在该手机号码
	 * 覆盖方法/实现方法(选择其一)
	 * (功能详细描述)
	 * @see com.work.dao.communication.IBusinessDirectoryDao#checkExist(com.work.domain.BusinessDirectory)
	 * @author       huangqingjian/0050
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        清清网系统, 2014年10月13日
	 * @param businessDirectory
	 * @return
	 * @throws DaoException
	 */
	@Override
	public int checkExist(BusinessDirectory businessDirectory) throws DaoException {
		List<Object> parm = new ArrayList<Object>();
		StringBuilder hql = new StringBuilder();
		hql.append("select count(id) from BusinessDirectory where contactPhone = ?  ");
		parm.add(businessDirectory.getContactPhone());
		if (StringUtil.isValidStr(businessDirectory.getId())) {
			hql.append(" and id != ?");
			parm.add(businessDirectory.getId());
		}
		return hibernateDao.findNumByHQL(hql.toString(), parm.toArray());
	}

}
