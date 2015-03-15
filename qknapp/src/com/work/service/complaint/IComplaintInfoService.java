/*
 * Title:        清清网系统 2014年7月25日
 * Description:  [描述模块的功能、作用、使用方法和注意事项]
 * Copyright:    Copyright (c) 2014
 * Company:      清清网
 * @author       huangqingjian
 * @version      2.0  2014年7月25日
 */
package com.work.service.complaint;

import java.util.List;

import com.qing.common.exception.ServiceException;
import com.qing.common.util.Page;
import com.work.domain.ComplaintInfo;

/**
 * 举报业务逻辑层
 * (功能详细描述)
 * @author       huangqingjian/0050
 * @see          [相关类,可选,也可多条,对于重要的类或接口建议注释]
 * @since        清清网系统, 2014年7月25日
 */
public interface IComplaintInfoService {
	/**
	 * 新增或保存举报
	 * (一句话功能简述)
	 * (功能详细描述)
	 * @author       huangqingjian/0050
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        清清网系统, 2014年7月25日
	 * @param complaintInfo
	 */
	public void saveOrUpdateComplaintInfo(ComplaintInfo complaintInfo) throws ServiceException;

	/**
	 * 删除举报
	 * (一句话功能简述)
	 * (功能详细描述)
	 * @author       huangqingjian/0050
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        清清网系统, 2014年7月25日
	 * @param ids
	 */
	public void deleteComplaintInfos(String[] ids, String memberId) throws ServiceException;

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
	public ComplaintInfo getComplaintInfo(String id) throws ServiceException;

	/**
	 * 根据查询条件分页查询举报
	 * (一句话功能简述)
	 * (功能详细描述)
	 * @author       huangqingjian/0050
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        清清网系统, 2014年7月25日
	 * @param page
	 * @param complaintInfo
	 */
	public void queryComplaintInfos(Page<ComplaintInfo> page, ComplaintInfo complaintInfo) throws ServiceException;

	/**
	 * 根据查询条件查询所有举报
	 * (一句话功能简述)
	 * (功能详细描述)
	 * @author       huangqingjian/0050
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        清清网系统, 2014年8月24日
	 * @param complaintInfo
	 * @return
	 * @throws ServiceException
	 */
	public List<ComplaintInfo> queryComplaintInfo(ComplaintInfo complaintInfo) throws ServiceException;

	/**
	 * 后台删除举报信息
	 * (一句话功能简述)
	 * (功能详细描述)
	 * @author       huangqingjian/0050
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        清清网系统, 2014年10月3日
	 * @param ids
	 * @throws ServiceException
	 */
	void deleteComplaintInfos(String[] ids) throws ServiceException;

	/**
	 * (一句话功能简述)
	 * (功能详细描述)
	 * @author       huangqingjian/0050
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        清清网系统, 2014年10月18日
	 * @param complaintInfo
	 * @return
	 * @throws ServiceException
	 */
	int getSize(ComplaintInfo complaintInfo) throws ServiceException;

}
