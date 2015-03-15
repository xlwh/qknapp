/*
 * Title:        清清网系统 2014-8-25
 * Description:  实践圈举报信息数据库访问接口实现类
 * Copyright:    Copyright (c) 2014
 * Company:      清清网
 * @author       zhanghongbin
 * @version      2.0  2014-8-25
 */
package com.work.dao.circle.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.qing.common.base.dao.HibernateDao;
import com.qing.common.exception.DaoException;
import com.qing.common.exception.ServiceException;
import com.qing.common.exception.handle.ExceptionHandle;
import com.qing.common.util.ErrorCode;
import com.qing.common.util.Page;
import com.work.dao.circle.IReportInfo;
import com.work.domain.ReportInfo;

/**
 * (一句话功能简述)
 *  实践圈举报信息数据库访问接口实现类
 * @author       zhanghongbin
 * @see          [相关类,可选,也可多条,对于重要的类或接口建议注释]
 * @since        清清网系统, 2014-8-25
 */

@Repository
public class ReportInfoImpl implements IReportInfo{

	@Resource
	HibernateDao<ReportInfo, String> hibernateDao;
	/**
	 * 实现方法
	 * 添加数据
	 * @see com.work.dao.circle.IReportInfo#saveReport(com.work.domain.ReportInfo)
	 * @author       zhanghongbin
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        清清网系统, 2014-8-25
	 * @param reportinfo
	 * @throws DaoException
	 */
	@Override
	public void saveReport(ReportInfo reportinfo) throws DaoException {
		hibernateDao.save(reportinfo);
		
	}

	/**
	 * 实现方法
	 * 修改举报信息
	 * @see com.work.dao.circle.IReportInfo#updateReport(com.work.domain.ReportInfo)
	 * @author       zhanghongbin
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        清清网系统, 2014-8-25
	 * @param reportinfo
	 * @throws DaoException
	 */
	@Override
	public void updateReport(ReportInfo reportinfo) throws DaoException {
		hibernateDao.saveOrUpdate(reportinfo);
		
	}

	/**
	 * 实现方法
	 * 列出所有的举报信息
	 * @see com.work.dao.circle.IReportInfo#findAllByPage(com.qing.common.util.Page, com.work.domain.ReportInfo)
	 * @author       zhanghongbin
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        清清网系统, 2014-8-25
	 * @param page
	 * @param reportInfo
	 * @throws DaoException
	 */
	@Override
	public void findAllByPage(Page<ReportInfo> page, ReportInfo reportInfo)
			throws DaoException {
		StringBuilder builder = new StringBuilder();
		List<Object> parm = new ArrayList<Object>();
		builder.append(" from Message where 1=1 ");
		builder.append(generateHQLCondition(reportInfo, parm));
		builder.append(" order by createtime desc");
		try {
			setPageSize(page, reportInfo);
			hibernateDao.findByPage(page, builder.toString(), parm.toArray());
		} catch (DaoException e) {
			try {
				ExceptionHandle.throwServiceException(e,
						ErrorCode.DAO_SAVE_ERROR_001,
						"MessageDao.queryMessagetByPage");
			} catch (ServiceException e1) {

				e1.printStackTrace();
			}
		}
		
	}
	
	
	/**
	 * 构建查询条件 (一句话功能简述) (功能详细描述)
	 * 
	 * @author huangqingjian/0050
	 * @see 相关函数,对于重要的函数建议注释
	 * @since 二维码系统, 2014年7月5日
	 * @param vo
	 * @return
	 */
	private StringBuilder generateHQLCondition(ReportInfo vo, List<Object> parm) {
		StringBuilder builder = new StringBuilder();
		
		return builder;
	}

	/**
	 * 设置总页数 (一句话功能简述) (功能详细描述)
	 * 
	 * @author huangqingjian/0050
	 * @see 相关函数,对于重要的函数建议注释
	 * @since 二维码系统, 2014年7月5日
	 * @param page
	 * @param vo
	 * @throws DaoException
	 */
	private void setPageSize(Page<ReportInfo> page, ReportInfo vo)
			throws DaoException {
		StringBuilder builder = new StringBuilder();
		List<Object> parm = new ArrayList<Object>();
		builder.append("select count(report_id)  from ReportInfo where 1=1 ");
		builder.append(generateHQLCondition(vo, parm));
		page.setTotalCount(hibernateDao.findNumByHQL(builder.toString(),
				parm.toArray()));
	}

}
