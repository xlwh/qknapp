/*
 * Title:        清清网系统 2014-8-25
 * Description:  实践圈举报信息数据库访问接口
 * Copyright:    Copyright (c) 2014
 * Company:      清清网
 * @author      zhanghongbin
 * @version      2.0  2014-8-25
 */
package com.work.dao.circle;

import com.qing.common.exception.DaoException;
import com.qing.common.util.Page;
import com.work.domain.ReportInfo;

/**
 * (一句话功能简述)
 *  实践圈举报信息数据库访问接口
 * @author       zhanghongbin
 * @see          [相关类,可选,也可多条,对于重要的类或接口建议注释]
 * @since        清清网系统, 2014-8-25
 */
public interface IReportInfo {
	
	/**
	 * 添加举报信息
	 * @author       zhanghongbin
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        清清网系统, 2014年8月25日
	 * @param reportinfo
	 * @throws DaoException
	 */
	public void saveReport(ReportInfo reportinfo) throws DaoException;
	
	
	/**
	 * 修改举报信息
	 * @author       zhanghongbin
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        清清网系统, 2014年8月25日
	 * @param reportinfo
	 * @throws DaoException
	 */
	public void updateReport(ReportInfo reportinfo) throws DaoException;
	
	/**
	 * 分页列出所有的举报信息
	 * @author       zhanghongbin
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        清清网系统, 2014年8月25日
	 * @param reportinfo
	 * @throws DaoException
	 */
	
	public void findAllByPage(Page<ReportInfo> page,ReportInfo reportInfo) throws DaoException;
	
}
