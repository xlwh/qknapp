/*
 * Title:        清清网系统 2014-8-25
 * Description:  实践圈信息举报业务逻辑接口
 * Copyright:    Copyright (c) 2014
 * Company:      清清网
 * @author       zhanghongbin
 * @version      2.0  2014-8-25
 */
package com.work.service.circle;

import com.qing.common.exception.DaoException;
import com.qing.common.util.Page;
import com.work.domain.Message;
import com.work.domain.ReportInfo;

/**
 * 实践圈信息举报业务逻辑接口
 * 
 * @author       zhanghongbin
 * @see          [相关类,可选,也可多条,对于重要的类或接口建议注释]
 * @since        清清网系统, 2014-8-25
 */
public interface IReportInfoService {
	/**
	 * 举报实践圈的信息
	 * 
	 * 
	 * @param  messageId  举报的信息id
	 * @param  memberId   举报者id
	 * @throws DaoException
	 */
	
	public void addReportInfo(ReportInfo report) throws DaoException;
	
	/**
	 * 后台审核（违规信息，删除对应的兼职圈动态）
	 * 
	 * 
	 * @param  messageId  举报的信息id
	 * 
	 * @throws DaoException
	 */
	
	public void removeMessage(ReportInfo reportInfo) throws DaoException;
	
	
	/**
	 * 后台审核（错误举报处理）
	 * 
	 * 
	 * @param  messageId  举报的信息id
	 * 
	 * @throws DaoException
	 */
	
	public void recoverMessage(ReportInfo reportInfo) throws DaoException;
	
	/**
	 * 分页列出等待管理员审核的所有举报信息
	 * 
	 *  
	 */
	
	public void listWriting(Page<ReportInfo> page,ReportInfo reportInfo) throws DaoException; 
	
	
	/**
	 * 分页列出已经被处理的信息
	 * 
	 *  
	 */
	
	public void listOreadyPro(Page<ReportInfo> page,ReportInfo reportInfo) throws DaoException; 
	
	

	/**
	 * 分页列出被错误举报，已经恢复的举报信息
	 * 
	 *  
	 */
	
	public void listErrorPro(Page<ReportInfo> page,ReportInfo reportInfo) throws DaoException; 
	
	/**
	 * 快速定位举报的消息
	 */
	
	public Message searchMessage(String reportId);
	
}
