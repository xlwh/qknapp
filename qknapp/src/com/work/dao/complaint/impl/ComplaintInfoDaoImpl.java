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
import com.work.dao.complaint.IComplaintInfoDao;
import com.work.domain.ComplaintInfo;

/**
 * 举报数据访问
 * (功能详细描述)
 * @author       huangqingjian/0050
 * @see          [相关类,可选,也可多条,对于重要的类或接口建议注释]
 * @since        清清网系统, 2014年7月25日
 */
@Repository
public class ComplaintInfoDaoImpl implements IComplaintInfoDao {

	@Resource
	HibernateDao<ComplaintInfo, String> hibernateDao;

	/**
	 * 新增或保存举报
	 * (一句话功能简述)
	 * (功能详细描述)
	 * @author       huangqingjian/0050
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        清清网系统, 2014年7月25日
	 * @param info
	 */
	public void saveOrUpdateComplaintInfo(ComplaintInfo ComplaintInfo) throws DaoException {

		hibernateDao.saveOrUpdate(ComplaintInfo);

	}

	/**
	 * 删除举报
	 * (一句话功能简述)
	 * (功能详细描述)
	 * @author       huangqingjian/0050
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        清清网系统, 2014年7月25日
	 * @param infos
	 */
	public void deleteComplaintInfos(List<ComplaintInfo> ComplaintInfos) throws DaoException {
		hibernateDao.removeAll(ComplaintInfos);
	}

	/**
	 * 根据ID获取举报详情
	 * (一句话功能简述)
	 * (功能详细描述)
	 * @author       huangqingjian/0050
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        清清网系统, 2014年7月25日
	 * @param id
	 * @return
	 */
	public ComplaintInfo getComplaintInfo(String id) throws DaoException {
		return hibernateDao.findById(ComplaintInfo.class, id);
	}

	/**
	 * 根据查询条件分页查询举报
	 * (一句话功能简述)
	 * (功能详细描述)
	 * @author       huangqingjian/0050
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        清清网系统, 2014年7月25日
	 * @param page
	 * @param info
	 */
	public void queryComplaintInfo(Page<ComplaintInfo> page, ComplaintInfo ComplaintInfo) throws DaoException {
		StringBuilder builder = new StringBuilder();
		List<Object> parm = new ArrayList<Object>();
		builder.append(" from ComplaintInfo where 1=1 ");
		builder.append(generateHQLCondition(ComplaintInfo, parm));
		builder.append(" order by createDate desc");
		setPageSize(page, ComplaintInfo);
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
	private StringBuilder generateHQLCondition(ComplaintInfo complaintInfo, List<Object> parm) {
		StringBuilder builder = new StringBuilder();
		if (null == complaintInfo) {
			return builder;
		}
		if (null != complaintInfo.getInfoType()) {
			builder.append(" and infoType = ?");
			parm.add(complaintInfo.getInfoType());
		}
		if (null != complaintInfo.getInfoId()) {
			builder.append(" and infoId = ?");
			parm.add(complaintInfo.getInfoId());
		}
		if (null != complaintInfo.getMemberId()) {
			builder.append(" and memberId = ?");
			parm.add(complaintInfo.getMemberId());
		}
		if (null != complaintInfo.getProStatus()) {
			builder.append(" and proStatus = ?");
			parm.add(complaintInfo.getProStatus());
		}
		if (StringUtil.isValidStr(complaintInfo.getReportType())) {
			builder.append(" and reportType like ?");
			parm.add(StringUtil.addLikeSymbol(complaintInfo.getReportType()));
		}
		if (StringUtil.isValidStr(complaintInfo.getContactInfo())) {
			builder.append(" and contactInfo like ?");
			parm.add(StringUtil.addLikeSymbol(complaintInfo.getContactInfo()));
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
	private void setPageSize(Page<ComplaintInfo> page, ComplaintInfo complaintInfo) throws DaoException {
		int size = getSize(complaintInfo);
		page.setTotalCount(size);
	}

	/**
	 * (一句话功能简述)
	 * (功能详细描述)
	 * @author       huangqingjian/0050
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        清清网系统, 2014年10月18日
	 * @param complaintInfo
	 * @return
	 * @throws DaoException
	 */
	@Override
	public int getSize(ComplaintInfo complaintInfo) throws DaoException {
		StringBuilder builder = new StringBuilder();
		List<Object> parm = new ArrayList<Object>();
		builder.append("select count(reportId)  from ComplaintInfo where 1=1 ");
		builder.append(generateHQLCondition(complaintInfo, parm));
		int size = hibernateDao.findNumByHQL(builder.toString(), parm.toArray());
		return size;
	}

	/**
	 * 不分页查询所有举报
	 * (一句话功能简述)
	 * (功能详细描述)
	 * @author       huangqingjian/0050
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        清清网系统, 2014年8月24日
	 * @param complaintInfo
	 * @return
	 * @throws DaoException
	 */
	public List<ComplaintInfo> queryComplaintInfo(ComplaintInfo complaintInfo) throws DaoException {
		return hibernateDao.findByExample(complaintInfo);
	}

}
