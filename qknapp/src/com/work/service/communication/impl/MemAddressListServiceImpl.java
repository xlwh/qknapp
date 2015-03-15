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
import com.work.dao.communication.IMemAddressListDao;
import com.work.domain.MemAddressList;
import com.work.service.communication.IMemAddressListService;

/**
 * 通讯录业务逻辑层
 * (功能详细描述)
 * @author       huangqingjian/0050
 * @see          [相关类,可选,也可多条,对于重要的类或接口建议注释]
 * @since        清清网系统, 2014年7月25日
 */
@Service
public class MemAddressListServiceImpl implements IMemAddressListService {

	@Resource
	IMemAddressListDao dao;

	/**
	 *保存或修改通讯录
	 * (功能详细描述)
	 * @see com.work.service.communication.IMemAddressListService#saveOrUpdateMemAddressList(com.work.domain.MemAddressList)
	 * @author       huangqingjian/0050
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        清清网系统, 2014年8月24日
	 * @param memAddressList
	 * @throws ServiceException
	 */
	@Override
	public void saveOrUpdateMemAddressList(MemAddressList memAddressList) throws ServiceException {
		try {
			dao.saveOrUpdateMemAddressList(memAddressList);
		} catch (DaoException e) {
			ExceptionHandle.throwServiceException(e, ErrorCode.SERVICE_ERROR,
					"MemAddressListServiceImpl.saveOrUpdateMemAddressList");
		}

	}

	/**
	 * 批量删除通讯录
	 * (功能详细描述)
	 * @see com.work.service.communication.IMemAddressListService#deleteMemAddressLists(java.lang.String[])
	 * @author       huangqingjian/0050
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        清清网系统, 2014年8月24日
	 * @param ids
	 * @throws ServiceException
	 */
	@Override
	public void deleteMemAddressLists(String[] ids) throws ServiceException {
		List<MemAddressList> memAddressLists = new ArrayList<MemAddressList>();
		try {
			if (null != ids && ids.length > 0) {
				for (String id : ids) {
					memAddressLists.add(dao.getMemAddressList(id));
				}
			}
			if (!CollectionUtils.isEmpty(memAddressLists)) {
				dao.deleteMemAddressLists(memAddressLists);
			}
		} catch (DaoException e) {
			ExceptionHandle.throwServiceException(e, ErrorCode.SERVICE_ERROR,
					"MemAddressListServiceImpl.deleteMemAddressLists");
		}
	}

	/**
	 * 根据ID获取单个通讯录
	 * (功能详细描述)
	 * @see com.work.service.communication.IMemAddressListService#getMemAddressList(java.lang.String)
	 * @author       huangqingjian/0050
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        清清网系统, 2014年8月24日
	 * @param id
	 * @return
	 * @throws ServiceException
	 */
	@Override
	public MemAddressList getMemAddressList(String id) throws ServiceException {
		try {
			return dao.getMemAddressList(id);
		} catch (DaoException e) {
			ExceptionHandle.throwServiceException(e, ErrorCode.SERVICE_ERROR,
					"MemAddressListServiceImpl.getMemAddressList");
		}
		return null;
	}

	/**
	 * 分页查询通讯录
	 * 覆盖方法/实现方法(选择其一)
	 * (功能详细描述)
	 * @see com.work.service.communication.IMemAddressListService#queryMemAddressLists(com.qing.common.util.Page, com.work.domain.MemAddressList)
	 * @author       huangqingjian/0050
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        清清网系统, 2014年8月24日
	 * @param page
	 * @param memAddressList
	 * @throws ServiceException
	 */
	@Override
	public void queryMemAddressLists(Page<MemAddressList> page, MemAddressList memAddressList) throws ServiceException {
		try {
			dao.queryMemAddressList(page, memAddressList);
		} catch (DaoException e) {
			ExceptionHandle.throwServiceException(e, ErrorCode.SERVICE_ERROR,
					"MemAddressListServiceImpl.queryMemAddressLists");
		}
	}

	/**
	 * 查询所有通讯录
	 * 覆盖方法/实现方法(选择其一)
	 * (功能详细描述)
	 * @see com.work.service.communication.IMemAddressListService#queryMemAddressList(com.work.domain.MemAddressList)
	 * @author       huangqingjian/0050
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        清清网系统, 2014年8月24日
	 * @param memAddressList
	 * @return
	 * @throws ServiceException
	 */
	@Override
	public List<MemAddressList> queryMemAddressList(MemAddressList memAddressList) throws ServiceException {
		try {
			return dao.queryMemAddressList(memAddressList);
		} catch (DaoException e) {
			ExceptionHandle.throwServiceException(e, ErrorCode.SERVICE_ERROR,
					"MemAddressListServiceImpl.queryMemAddressList");
		}
		return null;
	}
}
