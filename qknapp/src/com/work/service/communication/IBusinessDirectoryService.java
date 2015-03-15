/*
 * Title:        清清网系统 2014年7月25日
 * Description:  [描述模块的功能、作用、使用方法和注意事项]
 * Copyright:    Copyright (c) 2014
 * Company:      清清网
 * @author       huangqingjian
 * @version      2.0  2014年7月25日
 */
package com.work.service.communication;

import java.util.List;

import com.qing.common.exception.DaoException;
import com.qing.common.exception.ServiceException;
import com.qing.common.util.Page;
import com.work.domain.BusinessDirectory;

/**
 * 企业名录业务逻辑层
 * (功能详细描述)
 * @author       huangqingjian/0050
 * @see          [相关类,可选,也可多条,对于重要的类或接口建议注释]
 * @since        清清网系统, 2014年7月25日
 */
public interface IBusinessDirectoryService {
	/**
	 * 新增或保存企业名录
	 * (一句话功能简述)
	 * (功能详细描述)
	 * @author       huangqingjian/0050
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        清清网系统, 2014年7月25日
	 * @param businessDirectory
	 */
	public void saveOrUpdateBusinessDirectory(BusinessDirectory businessDirectory) throws ServiceException;

	/**
	 * 删除企业名录
	 * (一句话功能简述)
	 * (功能详细描述)
	 * @author       huangqingjian/0050
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        清清网系统, 2014年7月25日
	 * @param ids
	 */
	public void deleteBusinessDirectorys(String[] ids) throws ServiceException;

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
	public BusinessDirectory getBusinessDirectory(String id) throws ServiceException;

	/**
	 * 根据查询条件分页查询企业名录
	 * (一句话功能简述)
	 * (功能详细描述)
	 * @author       huangqingjian/0050
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        清清网系统, 2014年7月25日
	 * @param page
	 * @param businessDirectory
	 */
	public void queryBusinessDirectorys(Page<BusinessDirectory> page, BusinessDirectory businessDirectory)
			throws ServiceException;

	/**
	 * 根据查询条件查询所有企业名录
	 * (一句话功能简述)
	 * (功能详细描述)
	 * @author       huangqingjian/0050
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        清清网系统, 2014年8月24日
	 * @param businessDirectory
	 * @return
	 * @throws ServiceException
	 */
	public List<BusinessDirectory> queryBusinessDirectory(BusinessDirectory businessDirectory) throws ServiceException;

	/**
	 * 检查是否存在
	 * (一句话功能简述)
	 * (功能详细描述)
	 * @author       huangqingjian/0050
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        清清网系统, 2014年10月13日
	 * @param businessDirectory
	 * @return
	 * @throws DaoException
	 */
	public boolean checkExist(BusinessDirectory businessDirectory) throws ServiceException;
}
