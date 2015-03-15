/*
 * Title:        清清网系统 2014年7月25日
 * Description:  [描述模块的功能、作用、使用方法和注意事项]
 * Copyright:    Copyright (c) 2014
 * Company:      清清网
 * @author       huangqingjian
 * @version      2.0  2014年7月25日
 */
package com.work.service.job.impl;

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
import com.work.dao.job.IPartTimeTypeDao;
import com.work.domain.PartTimeType;
import com.work.service.job.IPartTimeTypeService;

/**
 * 兼职类型业务逻辑层
 * (功能详细描述)
 * @author       huangqingjian/0050
 * @see          [相关类,可选,也可多条,对于重要的类或接口建议注释]
 * @since        清清网系统, 2014年7月25日
 */
@Service
public class PartTimeTypeServiceImpl implements IPartTimeTypeService {

	@Resource
	IPartTimeTypeDao dao;

	/**
	 * 新增或修改
	 * 覆盖方法/实现方法(选择其一)
	 * (功能详细描述)
	 * @see com.work.service.job.IPartTimeTypeService#saveOrUpdateType(com.work.domain.PartTimeType)
	 * @author       huangqingjian/0050
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        清清网系统, 2014年7月25日
	 * @param Type
	 * @throws ServiceException
	 */
	@Override
	public void saveOrUpdateType(PartTimeType type) throws ServiceException {
		try {
			dao.saveOrUpdateType(type);
		} catch (DaoException e) {
			ExceptionHandle.throwServiceException(e, ErrorCode.SERVICE_ERROR,
					"PartTimeTypeServiceImpl.saveOrUpdateType");
		}
	}

	/**
	 * 批量删除类型
	 * 覆盖方法/实现方法(选择其一)
	 * (功能详细描述)
	 * @see com.work.service.job.IPartTimeTypeService#deleteType(java.util.List)
	 * @author       huangqingjian/0050
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        清清网系统, 2014年7月25日
	 * @param Types
	 * @throws ServiceException
	 */
	@Override
	public void deleteType(String[] ids) throws ServiceException {
		try {
			List<PartTimeType> Types = new ArrayList<PartTimeType>();
			if (null != ids && ids.length > 0) {
				for (String id : ids) {
					Types.add(dao.getPartTimeType(id));
				}
			}
			if (!CollectionUtils.isEmpty(Types)) {
				dao.deleteType(Types);
			}
		} catch (DaoException e) {
			ExceptionHandle.throwServiceException(e, ErrorCode.SERVICE_ERROR, "PartTimeTypeServiceImpl.deleteType");
		}

	}

	/**
	 * 根据ID获取详情
	 * 覆盖方法/实现方法(选择其一)
	 * (功能详细描述)
	 * @see com.work.service.job.IPartTimeTypeService#getPartTimeType(java.lang.String)
	 * @author       huangqingjian/0050
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        清清网系统, 2014年7月25日
	 * @param id
	 * @return
	 * @throws ServiceException
	 */
	@Override
	public PartTimeType getPartTimeType(String id) throws ServiceException {
		PartTimeType Type = null;
		try {
			Type = dao.getPartTimeType(id);
		} catch (DaoException e) {
			ExceptionHandle
					.throwServiceException(e, ErrorCode.SERVICE_ERROR, "PartTimeTypeServiceImpl.getPartTimeType");

		}
		return Type;
	}

	/**
	 * 分页查询兼职类型
	 * (功能详细描述)
	 * @see com.work.service.job.IPartTimeTypeService#queryPartTime(com.qing.common.util.Page, com.work.domain.PartTimeType)
	 * @author       huangqingjian/0050
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        清清网系统, 2014年7月25日
	 * @param page
	 * @param Type
	 * @throws ServiceException
	 */
	@Override
	public void queryPartTime(Page<PartTimeType> page, PartTimeType type) throws ServiceException {
		try {
			dao.queryPartTime(page, type);
		} catch (DaoException e) {
			ExceptionHandle.throwServiceException(e, ErrorCode.SERVICE_ERROR, "PartTimeTypeServiceImpl.queryPartTime");
		}

	}

	/**
	 * 获取所有的兼职类型，不分页
	 * (功能详细描述)
	 * @see com.work.service.job.IPartTimeTypeService#queryPartTime(com.work.domain.PartTimeType)
	 * @author       huangqingjian/0050
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        清清网系统, 2014年8月6日
	 * @param type
	 * @return
	 * @throws ServiceException
	 */
	@Override
	public List<PartTimeType> queryPartTime(PartTimeType type) throws ServiceException {
		try {
			return dao.queryPartTime(type);
		} catch (DaoException e) {
			ExceptionHandle.throwServiceException(e, ErrorCode.SERVICE_ERROR, "PartTimeTypeServiceImpl.queryPartTime");
		}
		return null;
	}

}
