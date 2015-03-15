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
import com.work.dao.communication.IBlackWhiteListDao;
import com.work.domain.BlackWhiteList;
import com.work.service.communication.IBlackWhiteListService;

/**
 * 黑白名单业务逻辑层
 * (功能详细描述)
 * @author       huangqingjian/0050
 * @see          [相关类,可选,也可多条,对于重要的类或接口建议注释]
 * @since        清清网系统, 2014年7月25日
 */
@Service
public class BlackWhiteListServiceImpl implements IBlackWhiteListService {

	@Resource
	IBlackWhiteListDao dao;

	/**
	 *保存或修改黑白名单
	 * (功能详细描述)
	 * @see com.work.service.communication.IBlackWhiteListService#saveOrUpdateBlackWhiteList(com.work.domain.BlackWhiteList)
	 * @author       huangqingjian/0050
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        清清网系统, 2014年8月24日
	 * @param blackWhiteList
	 * @throws ServiceException
	 */
	@Override
	public void saveOrUpdateBlackWhiteList(BlackWhiteList blackWhiteList) throws ServiceException {
		try {
			dao.saveOrUpdateBlackWhiteList(blackWhiteList);
		} catch (DaoException e) {
			ExceptionHandle.throwServiceException(e, ErrorCode.SERVICE_ERROR,
					"BlackWhiteListServiceImpl.saveOrUpdateBlackWhiteList");
		}

	}

	/**
	 * 批量删除黑白名单
	 * (功能详细描述)
	 * @see com.work.service.communication.IBlackWhiteListService#deleteBlackWhiteLists(java.lang.String[])
	 * @author       huangqingjian/0050
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        清清网系统, 2014年8月24日
	 * @param ids
	 * @throws ServiceException
	 */
	@Override
	public void deleteBlackWhiteLists(String[] ids) throws ServiceException {
		List<BlackWhiteList> blackWhiteLists = new ArrayList<BlackWhiteList>();
		try {
			if (null != ids && ids.length > 0) {
				for (String id : ids) {
					blackWhiteLists.add(dao.getBlackWhiteList(id));
				}
			}
			if (!CollectionUtils.isEmpty(blackWhiteLists)) {
				dao.deleteBlackWhiteLists(blackWhiteLists);
			}
		} catch (DaoException e) {
			ExceptionHandle.throwServiceException(e, ErrorCode.SERVICE_ERROR,
					"BlackWhiteListServiceImpl.deleteBlackWhiteLists");
		}
	}

	/**
	 * 根据ID获取单个黑白名单
	 * (功能详细描述)
	 * @see com.work.service.communication.IBlackWhiteListService#getBlackWhiteList(java.lang.String)
	 * @author       huangqingjian/0050
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        清清网系统, 2014年8月24日
	 * @param id
	 * @return
	 * @throws ServiceException
	 */
	@Override
	public BlackWhiteList getBlackWhiteList(String id) throws ServiceException {
		try {
			return dao.getBlackWhiteList(id);
		} catch (DaoException e) {
			ExceptionHandle.throwServiceException(e, ErrorCode.SERVICE_ERROR,
					"BlackWhiteListServiceImpl.getBlackWhiteList");
		}
		return null;
	}

	/**
	 * 分页查询黑白名单
	 * 覆盖方法/实现方法(选择其一)
	 * (功能详细描述)
	 * @see com.work.service.communication.IBlackWhiteListService#queryBlackWhiteLists(com.qing.common.util.Page, com.work.domain.BlackWhiteList)
	 * @author       huangqingjian/0050
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        清清网系统, 2014年8月24日
	 * @param page
	 * @param blackWhiteList
	 * @throws ServiceException
	 */
	@Override
	public void queryBlackWhiteLists(Page<BlackWhiteList> page, BlackWhiteList blackWhiteList) throws ServiceException {
		try {
			dao.queryBlackWhiteList(page, blackWhiteList);
		} catch (DaoException e) {
			ExceptionHandle.throwServiceException(e, ErrorCode.SERVICE_ERROR,
					"BlackWhiteListServiceImpl.queryBlackWhiteLists");
		}
	}

	/**
	 * 查询所有黑白名单
	 * 覆盖方法/实现方法(选择其一)
	 * (功能详细描述)
	 * @see com.work.service.communication.IBlackWhiteListService#queryBlackWhiteList(com.work.domain.BlackWhiteList)
	 * @author       huangqingjian/0050
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        清清网系统, 2014年8月24日
	 * @param blackWhiteList
	 * @return
	 * @throws ServiceException
	 */
	@Override
	public List<BlackWhiteList> queryBlackWhiteList(BlackWhiteList blackWhiteList) throws ServiceException {
		try {
			return dao.queryBlackWhiteList(blackWhiteList);
		} catch (DaoException e) {
			ExceptionHandle.throwServiceException(e, ErrorCode.SERVICE_ERROR,
					"BlackWhiteListServiceImpl.queryBlackWhiteList");
		}
		return null;
	}

	/**
	 * 检查是否存在
	 * 覆盖方法/实现方法(选择其一)
	 * (功能详细描述)
	 * @see com.work.service.communication.IBlackWhiteListService#checkExist(com.work.domain.BlackWhiteList)
	 * @author       huangqingjian/0050
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        清清网系统, 2014年10月13日
	 * @param blackWhiteList
	 * @return
	 * @throws ServiceException
	 */
	@Override
	public boolean checkExist(BlackWhiteList blackWhiteList) throws ServiceException {

		try {
			if (dao.checkExist(blackWhiteList) > 0) {
				return true;
			}
		} catch (DaoException e) {
			ExceptionHandle
					.throwServiceException(e, ErrorCode.SERVICE_ERROR, "BusinessDirectoryServiceImpl.checkExist");
		}
		return false;
	}
}
