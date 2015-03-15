/*
 * Title:        清清网系统 2014年7月25日
 * Description:  [描述模块的功能、作用、使用方法和注意事项]
 * Copyright:    Copyright (c) 2014
 * Company:      清清网
 * @author       huangqingjian
 * @version      2.0  2014年7月25日
 */
package com.work.service.communication.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.qing.common.exception.DaoException;
import com.qing.common.exception.ServiceException;
import com.qing.common.exception.handle.ExceptionHandle;
import com.qing.common.util.ErrorCode;
import com.qing.common.util.Page;
import com.work.dao.communication.IBusinessDirectoryDao;
import com.work.domain.BusinessDirectory;
import com.work.service.communication.IBusinessDirectoryService;

/**
 * 企业名录业务逻辑层
 * (功能详细描述)
 * @author       huangqingjian/0050
 * @see          [相关类,可选,也可多条,对于重要的类或接口建议注释]
 * @since        清清网系统, 2014年7月25日
 */
@Service
public class BusinessDirectoryServiceImpl implements IBusinessDirectoryService {

	@Resource
	IBusinessDirectoryDao dao;

	/**
	 *保存或修改企业名录
	 * (功能详细描述)
	 * @see com.work.service.communication.IBusinessDirectoryService#saveOrUpdateBusinessDirectory(com.work.domain.BusinessDirectory)
	 * @author       huangqingjian/0050
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        清清网系统, 2014年8月24日
	 * @param businessDirectory
	 * @throws ServiceException
	 */
	@Override
	public void saveOrUpdateBusinessDirectory(BusinessDirectory businessDirectory) throws ServiceException {
		try {
			dao.saveOrUpdateBusinessDirectory(businessDirectory);
		} catch (DaoException e) {
			ExceptionHandle.throwServiceException(e, ErrorCode.SERVICE_ERROR,
					"BusinessDirectoryServiceImpl.saveOrUpdateBusinessDirectory");
		}

	}

	/**
	 * 批量删除企业名录
	 * (功能详细描述)
	 * @see com.work.service.communication.IBusinessDirectoryService#deleteBusinessDirectorys(java.lang.String[])
	 * @author       huangqingjian/0050
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        清清网系统, 2014年8月24日
	 * @param ids
	 * @throws ServiceException
	 */
	@Override
	public void deleteBusinessDirectorys(String[] ids) throws ServiceException {
		List<BusinessDirectory> businessDirectorys = new ArrayList<BusinessDirectory>();
		try {
			if (null != ids && ids.length > 0) {
				for (String id : ids) {
					businessDirectorys.add(dao.getBusinessDirectory(id));
				}
			}
			if (!CollectionUtils.isEmpty(businessDirectorys)) {
				dao.deleteBusinessDirectorys(businessDirectorys);
			}
		} catch (DaoException e) {
			ExceptionHandle.throwServiceException(e, ErrorCode.SERVICE_ERROR,
					"BusinessDirectoryServiceImpl.deleteBusinessDirectorys");
		}
	}

	/**
	 * 根据ID获取单个企业名录
	 * (功能详细描述)
	 * @see com.work.service.communication.IBusinessDirectoryService#getBusinessDirectory(java.lang.String)
	 * @author       huangqingjian/0050
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        清清网系统, 2014年8月24日
	 * @param id
	 * @return
	 * @throws ServiceException
	 */
	@Override
	public BusinessDirectory getBusinessDirectory(String id) throws ServiceException {
		try {
			return dao.getBusinessDirectory(id);
		} catch (DaoException e) {
			ExceptionHandle.throwServiceException(e, ErrorCode.SERVICE_ERROR,
					"BusinessDirectoryServiceImpl.getBusinessDirectory");
		}
		return null;
	}

	/**
	 * 分页查询企业名录
	 * 覆盖方法/实现方法(选择其一)
	 * (功能详细描述)
	 * @see com.work.service.communication.IBusinessDirectoryService#queryBusinessDirectorys(com.qing.common.util.Page, com.work.domain.BusinessDirectory)
	 * @author       huangqingjian/0050
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        清清网系统, 2014年8月24日
	 * @param page
	 * @param businessDirectory
	 * @throws ServiceException
	 */
	@Override
	public void queryBusinessDirectorys(Page<BusinessDirectory> page, BusinessDirectory businessDirectory)
			throws ServiceException {
		try {
			dao.queryBusinessDirectory(page, businessDirectory);
		} catch (DaoException e) {
			ExceptionHandle.throwServiceException(e, ErrorCode.SERVICE_ERROR,
					"BusinessDirectoryServiceImpl.queryBusinessDirectorys");
		}
	}

	/**
	 * 查询所有企业名录
	 * 覆盖方法/实现方法(选择其一)
	 * (功能详细描述)
	 * @see com.work.service.communication.IBusinessDirectoryService#queryBusinessDirectory(com.work.domain.BusinessDirectory)
	 * @author       huangqingjian/0050
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        清清网系统, 2014年8月24日
	 * @param businessDirectory
	 * @return
	 * @throws ServiceException
	 */
	@Override
	public List<BusinessDirectory> queryBusinessDirectory(BusinessDirectory businessDirectory) throws ServiceException {
		try {
			return dao.queryBusinessDirectory(businessDirectory);
		} catch (DaoException e) {
			ExceptionHandle.throwServiceException(e, ErrorCode.SERVICE_ERROR,
					"BusinessDirectoryServiceImpl.queryBusinessDirectory");
		}
		return null;
	}

	/**
	 * 检查是否存在
	 * 覆盖方法/实现方法(选择其一)
	 * (功能详细描述)
	 * @see com.work.service.communication.IBusinessDirectoryService#checkExist(com.work.domain.BusinessDirectory)
	 * @author       huangqingjian/0050
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        清清网系统, 2014年10月13日
	 * @param businessDirectory
	 * @return
	 * @throws ServiceException
	 */
	@Override
	public boolean checkExist(BusinessDirectory businessDirectory) throws ServiceException {

		try {
			if (dao.checkExist(businessDirectory) > 0) {
				return true;
			}
		} catch (DaoException e) {
			ExceptionHandle
					.throwServiceException(e, ErrorCode.SERVICE_ERROR, "BusinessDirectoryServiceImpl.checkExist");
		}
		return false;
	}
}
