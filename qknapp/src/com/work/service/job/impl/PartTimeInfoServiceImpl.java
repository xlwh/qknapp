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

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.qing.common.exception.DaoException;
import com.qing.common.exception.ServiceException;
import com.qing.common.exception.handle.ExceptionHandle;
import com.qing.common.util.ErrorCode;
import com.qing.common.util.Page;
import com.work.dao.job.IPartTimeInfoDao;
import com.work.domain.PartTimeInfo;
import com.work.service.job.IPartTimeInfoService;

/**
 * 兼职信息业务逻辑层
 * (功能详细描述)
 * @author       huangqingjian/0050
 * @see          [相关类,可选,也可多条,对于重要的类或接口建议注释]
 * @since        清清网系统, 2014年7月25日
 */
@Service
@Repository
public class PartTimeInfoServiceImpl implements IPartTimeInfoService {

	@Resource
	IPartTimeInfoDao dao;

	/**
	 * 新增或修改
	 * 覆盖方法/实现方法(选择其一)
	 * (功能详细描述)
	 * @see com.work.service.job.IPartTimeInfoService#saveOrUpdateInfo(com.work.domain.PartTimeInfo)
	 * @author       huangqingjian/0050
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        清清网系统, 2014年7月25日
	 * @param info
	 * @throws ServiceException
	 */
	@Override
	public void saveOrUpdateInfo(PartTimeInfo info) throws ServiceException {
		try {
			if (null != info) {
				dao.saveOrUpdateInfo(info);
			}

		} catch (DaoException e) {
			ExceptionHandle.throwServiceException(e, ErrorCode.SERVICE_ERROR,
					"PartTimeInfoServiceImpl.saveOrUpdateInfo");
		}
	}

	/**
	 * 覆盖方法/实现方法(选择其一)
	 * (功能详细描述)
	 * @see com.work.service.job.IPartTimeInfoService#deleteInfo(java.util.List)
	 * @author       huangqingjian/0050
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        清清网系统, 2014年7月25日
	 * @param infos
	 * @throws ServiceException
	 */
	@Override
	public void deleteInfo(String[] ids) throws ServiceException {
		try {
			List<PartTimeInfo> infos = new ArrayList<PartTimeInfo>();
			if (!CollectionUtils.isEmpty(infos)) {
				dao.deleteInfo(infos);
			}
		} catch (DaoException e) {
			ExceptionHandle.throwServiceException(e, ErrorCode.SERVICE_ERROR, "PartTimeInfoServiceImpl.deleteInfo");
		}

	}

	/**
	 * 根据ID获取详情
	 * 覆盖方法/实现方法(选择其一)
	 * (功能详细描述)
	 * @see com.work.service.job.IPartTimeInfoService#getPartTimeInfo(java.lang.String)
	 * @author       huangqingjian/0050
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        清清网系统, 2014年7月25日
	 * @param id
	 * @return
	 * @throws ServiceException
	 */
	@Override
	public PartTimeInfo getPartTimeInfo(String id) throws ServiceException {
		PartTimeInfo info = null;
		try {
			info = dao.getPartTimeInfo(id);
		} catch (DaoException e) {
			ExceptionHandle
					.throwServiceException(e, ErrorCode.SERVICE_ERROR, "PartTimeInfoServiceImpl.getPartTimeInfo");

		}
		return info;
	}

	/**
	 * 分页查询兼职信息
	 * (功能详细描述)
	 * @see com.work.service.job.IPartTimeInfoService#queryPartTime(com.qing.common.util.Page, com.work.domain.PartTimeInfo)
	 * @author       huangqingjian/0050
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        清清网系统, 2014年7月25日
	 * @param page
	 * @param info
	 * @throws ServiceException
	 */
	@Override
	public void queryPartTime(Page<PartTimeInfo> page, PartTimeInfo info) throws ServiceException {
		try {
			dao.queryPartTime(page, info);
		} catch (DaoException e) {
			ExceptionHandle.throwServiceException(e, ErrorCode.SERVICE_ERROR, "PartTimeInfoServiceImpl.queryPartTime");
		}

	}

	@Override
	public int getPageSize(PartTimeInfo info) throws ServiceException {
		try {
			return dao.getPageSize(info);
		} catch (DaoException e) {
			ExceptionHandle.throwServiceException(e, ErrorCode.SERVICE_ERROR, "PartTimeInfoServiceImpl.getPageSize");
		}
		return 0;
	}

}
