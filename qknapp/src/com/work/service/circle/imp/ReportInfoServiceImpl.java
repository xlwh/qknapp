/*
 * Title:        清清网系统 2014年8月30日
 * Description:  [描述模块的功能、作用、使用方法和注意事项]
 * Copyright:    Copyright (c) 2014
 * Company:      清清网
 * @author      zhanghongbin
 * @version      2.0  2014年8月30日
 */
package com.work.service.circle.imp;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.qing.common.exception.DaoException;
import com.qing.common.util.Page;
import com.work.dao.circle.IReportInfo;
import com.work.domain.Message;
import com.work.domain.ReportInfo;
import com.work.service.circle.IReportInfoService;

/**
 * (一句话功能简述)
 * (功能详细描述)
 * @author      zhanghongbin/0050
 * @see          [相关类,可选,也可多条,对于重要的类或接口建议注释]
 * @since        清清网系统, 2014年8月30日
 */

@Component
public class ReportInfoServiceImpl implements IReportInfoService{

	@Resource
	private IReportInfo dao;
	
	
	/**
	 * 覆盖方法/实现方法(选择其一)
	 * (功能详细描述)
	 * @see com.work.service.circle.IReportInfoService#addReportInfo(java.lang.String, java.lang.String)
	 * @author      zhanghongbin/0050
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        清清网系统, 2014年8月30日
	 * @param messageId
	 * @param memberId
	 * @throws DaoException
	 */
	@Override
	public void addReportInfo(ReportInfo report) throws DaoException {
		dao.saveReport(report);
	}

	/**
	 * 覆盖方法/实现方法(选择其一)
	 * (功能详细描述)
	 * @see com.work.service.circle.IReportInfoService#removeMessage(com.work.domain.ReportInfo)
	 * @author      zhanghongbin/0050
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        清清网系统, 2014年8月30日
	 * @param reportInfo
	 * @throws DaoException
	 */
	@Override
	public void removeMessage(ReportInfo reportInfo) throws DaoException {
		
		
	}

	/**
	 * 覆盖方法/实现方法(选择其一)
	 * (功能详细描述)
	 * @see com.work.service.circle.IReportInfoService#recoverMessage(com.work.domain.ReportInfo)
	 * @author      zhanghongbin/0050
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        清清网系统, 2014年8月30日
	 * @param reportInfo
	 * @throws DaoException
	 */
	@Override
	public void recoverMessage(ReportInfo reportInfo) throws DaoException {
		dao.updateReport(reportInfo);
		
	}

	/**
	 * 覆盖方法/实现方法(选择其一)
	 * (功能详细描述)
	 * @see com.work.service.circle.IReportInfoService#listWriting(com.qing.common.util.Page, com.work.domain.ReportInfo)
	 * @author      zhanghongbin/0050
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        清清网系统, 2014年8月30日
	 * @param page
	 * @param reportInfo
	 * @throws DaoException
	 */
	@Override
	public void listWriting(Page<ReportInfo> page, ReportInfo reportInfo) throws DaoException {
		
		
	}

	/**
	 * 覆盖方法/实现方法(选择其一)
	 * (功能详细描述)
	 * @see com.work.service.circle.IReportInfoService#listOreadyPro(com.qing.common.util.Page, com.work.domain.ReportInfo)
	 * @author      zhanghongbin/0050
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        清清网系统, 2014年8月30日
	 * @param page
	 * @param reportInfo
	 * @throws DaoException
	 */
	@Override
	public void listOreadyPro(Page<ReportInfo> page, ReportInfo reportInfo) throws DaoException {
		
		
	}

	/**
	 * 覆盖方法/实现方法(选择其一)
	 * (功能详细描述)
	 * @see com.work.service.circle.IReportInfoService#listErrorPro(com.qing.common.util.Page, com.work.domain.ReportInfo)
	 * @author      zhanghongbin/0050
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        清清网系统, 2014年8月30日
	 * @param page
	 * @param reportInfo
	 * @throws DaoException
	 */
	@Override
	public void listErrorPro(Page<ReportInfo> page, ReportInfo reportInfo) throws DaoException {
		
		
	}

	/**
	 * 覆盖方法/实现方法(选择其一)
	 * (功能详细描述)
	 * @see com.work.service.circle.IReportInfoService#searchMessage(java.lang.String)
	 * @author      zhanghongbin/0050
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        清清网系统, 2014年8月30日
	 * @param reportId
	 * @return
	 */
	@Override
	public Message searchMessage(String reportId) {
		
		return null;
	}

}
