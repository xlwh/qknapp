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
import com.work.dao.job.IDataMiningSetDao;
import com.work.domain.DataMiningSet;
import com.work.service.job.IDataMiningSetService;

/**
 * 数据挖掘设置业务逻辑层
 * (功能详细描述)
 * @author       huangqingjian/0050
 * @see          [相关类,可选,也可多条,对于重要的类或接口建议注释]
 * @since        清清网系统, 2014年7月25日
 */
@Service
public class DataMiningSetServiceImpl implements IDataMiningSetService {

	@Resource
	IDataMiningSetDao dao;

	/**
	 * 新增或修改
	 * 覆盖方法/实现方法(选择其一)
	 * (功能详细描述)
	 * @see com.work.service.job.IDataMiningSetService#saveOrUpdateSet(com.work.domain.DataMiningSet)
	 * @author       huangqingjian/0050
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        清清网系统, 2014年7月25日
	 * @param Set
	 * @throws ServiceException
	 */
	@Override
	public void saveOrUpdateSet(DataMiningSet set) throws ServiceException {
		try {
			dao.saveOrUpdateSet(set);
		} catch (DaoException e) {
			ExceptionHandle.throwServiceException(e, ErrorCode.SERVICE_ERROR,
					"DataMiningSetServiceImpl.saveOrUpdateSet");
		}
	}

	@Override
	public void saveOrUpdateSets(List<DataMiningSet> sets) throws ServiceException {
		try {
			dao.saveOrUpdateSets(sets);
		} catch (DaoException e) {
			ExceptionHandle.throwServiceException(e, ErrorCode.SERVICE_ERROR,
					"DataMiningSetServiceImpl.saveOrUpdateSet");
		}
	}

	/**
	 * 批量删除类型
	 * 覆盖方法/实现方法(选择其一)
	 * (功能详细描述)
	 * @see com.work.service.job.IDataMiningSetService#deleteSet(java.util.List)
	 * @author       huangqingjian/0050
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        清清网系统, 2014年7月25日
	 * @param Sets
	 * @throws ServiceException
	 */
	@Override
	public void deleteSet(String[] ids) throws ServiceException {
		try {
			List<DataMiningSet> Sets = new ArrayList<DataMiningSet>();
			if (null != ids && ids.length > 0) {
				for (String id : ids) {
					Sets.add(dao.getDataMiningSet(id));
				}
			}
			if (!CollectionUtils.isEmpty(Sets)) {
				dao.deleteSet(Sets);
			}
		} catch (DaoException e) {
			ExceptionHandle.throwServiceException(e, ErrorCode.SERVICE_ERROR, "DataMiningSetServiceImpl.deleteSet");
		}

	}

	/**
	 * 根据ID获取详情
	 * 覆盖方法/实现方法(选择其一)
	 * (功能详细描述)
	 * @see com.work.service.job.IDataMiningSetService#getDataMiningSet(java.lang.String)
	 * @author       huangqingjian/0050
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        清清网系统, 2014年7月25日
	 * @param id
	 * @return
	 * @throws ServiceException
	 */
	@Override
	public DataMiningSet getDataMiningSet(String id) throws ServiceException {
		DataMiningSet set = null;
		try {
			set = dao.getDataMiningSet(id);
		} catch (DaoException e) {
			ExceptionHandle.throwServiceException(e, ErrorCode.SERVICE_ERROR,
					"DataMiningSetServiceImpl.getDataMiningSet");

		}
		return set;
	}

	/**
	 * 分页查询数据挖掘设置
	 * (功能详细描述)
	 * @see com.work.service.job.IDataMiningSetService#queryDataMiningSet(com.qing.common.util.Page, com.work.domain.DataMiningSet)
	 * @author       huangqingjian/0050
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        清清网系统, 2014年7月25日
	 * @param page
	 * @param Set
	 * @throws ServiceException
	 */
	@Override
	public void queryDataMiningSet(Page<DataMiningSet> page, DataMiningSet set) throws ServiceException {
		try {
			dao.queryDataMiningSet(page, set);
		} catch (DaoException e) {
			ExceptionHandle.throwServiceException(e, ErrorCode.SERVICE_ERROR,
					"DataMiningSetServiceImpl.queryDataMiningSet");
		}

	}

	/**
	 * 获取所有的数据挖掘设置，不分页
	 * (功能详细描述)
	 * @see com.work.service.job.IDataMiningSetService#queryDataMiningSet(com.work.domain.DataMiningSet)
	 * @author       huangqingjian/0050
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        清清网系统, 2014年8月6日
	 * @param Set
	 * @return
	 * @throws ServiceException
	 */
	@Override
	public List<DataMiningSet> queryDataMiningSet(DataMiningSet set) throws ServiceException {
		try {
			return dao.queryDataMiningSet(set);
		} catch (DaoException e) {
			ExceptionHandle.throwServiceException(e, ErrorCode.SERVICE_ERROR,
					"DataMiningSetServiceImpl.queryDataMiningSet");
		}
		return null;
	}

}
