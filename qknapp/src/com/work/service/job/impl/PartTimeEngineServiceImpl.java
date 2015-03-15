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
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.qing.common.exception.DaoException;
import com.qing.common.exception.ServiceException;
import com.qing.common.exception.handle.ExceptionHandle;
import com.qing.common.util.ErrorCode;
import com.qing.common.util.Page;
import com.work.dao.job.IPartTimeEngineDao;
import com.work.dao.job.IPartTimeInfoDao;
import com.work.domain.DataMiningSet;
import com.work.domain.PartTimeEngine;
import com.work.domain.PartTimeInfo;
import com.work.factory.JobFactoryManager;
import com.work.service.datamining.IJobInfoMiningService;
import com.work.service.job.IDataMiningSetService;
import com.work.service.job.IPartTimeEngineService;

/**
 * 兼职信息业务逻辑层
 * (功能详细描述)
 * @author       huangqingjian/0050
 * @see          [相关类,可选,也可多条,对于重要的类或接口建议注释]
 * @since        清清网系统, 2014年7月25日
 */
@Service
@Repository
public class PartTimeEngineServiceImpl implements IPartTimeEngineService {

	private static final Logger logger = LoggerFactory.getLogger(PartTimeEngineServiceImpl.class);
	@Resource
	IPartTimeEngineDao dao;

	@Resource
	IPartTimeInfoDao infoDao;

	@Resource
	IDataMiningSetService service;

	/**
	 * 新增或修改
	 * 覆盖方法/实现方法(选择其一)
	 * (功能详细描述)
	 * @see com.work.service.job.IPartTimeEngineService#saveOrUpdateInfo(com.work.domain.PartTimeEngine)
	 * @author       huangqingjian/0050
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        清清网系统, 2014年7月25日
	 * @param info
	 * @throws ServiceException
	 */
	@Override
	public void saveOrUpdateInfo(PartTimeEngine info) throws ServiceException {
		try {
			dao.saveOrUpdateInfo(info);
		} catch (DaoException e) {
			ExceptionHandle.throwServiceException(e, ErrorCode.SERVICE_ERROR,
					"PartTimeEngineServiceImpl.saveOrUpdateInfo");
		}
	}

	/**
	 * 批量保存
	 * 覆盖方法/实现方法(选择其一)
	 * (功能详细描述)
	 * @see com.work.service.job.IPartTimeEngineService#saveOrUpdateInfo(java.util.List)
	 * @author       huangqingjian/0050
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        清清网系统, 2014年8月10日
	 * @param infos
	 * @throws ServiceException
	 */
	@Override
	public void saveOrUpdateInfo(List<PartTimeEngine> infos) throws ServiceException {
		try {
			dao.saveOrUpdateInfo(infos);
		} catch (DaoException e) {
			ExceptionHandle.throwServiceException(e, ErrorCode.SERVICE_ERROR,
					"PartTimeEngineServiceImpl.saveOrUpdateInfo");
		}
	}

	/**
	 * 覆盖方法/实现方法(选择其一)
	 * (功能详细描述)
	 * @see com.work.service.job.IPartTimeEngineService#deleteInfo(java.util.List)
	 * @author       huangqingjian/0050
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        清清网系统, 2014年7月25日
	 * @param infos
	 * @throws ServiceException
	 */
	@Override
	public void deleteInfo(String[] ids) throws ServiceException {
		try {
			List<PartTimeEngine> infos = new ArrayList<PartTimeEngine>();
			if (null != ids && ids.length > 0) {
				for (String id : ids) {
					infos.add(dao.getPartTimeEngine(id));
				}
			}
			if (!CollectionUtils.isEmpty(infos)) {
				dao.deleteInfo(infos);
			}
		} catch (DaoException e) {
			ExceptionHandle.throwServiceException(e, ErrorCode.SERVICE_ERROR, "PartTimeEngineServiceImpl.deleteInfo");
		}

	}

	/**
	 * 根据ID获取详情
	 * 覆盖方法/实现方法(选择其一)
	 * (功能详细描述)
	 * @see com.work.service.job.IPartTimeEngineService#getPartTimeEngine(java.lang.String)
	 * @author       huangqingjian/0050
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        清清网系统, 2014年7月25日
	 * @param id
	 * @return
	 * @throws ServiceException
	 */
	@Override
	public PartTimeEngine getPartTimeEngine(String id) throws ServiceException {
		PartTimeEngine info = null;
		try {
			info = dao.getPartTimeEngine(id);
		} catch (DaoException e) {
			ExceptionHandle.throwServiceException(e, ErrorCode.SERVICE_ERROR,
					"PartTimeEngineServiceImpl.getPartTimeEngine");

		}
		return info;
	}

	/**
	 * 分页查询兼职信息
	 * (功能详细描述)
	 * @see com.work.service.job.IPartTimeEngineService#queryPartTime(com.qing.common.util.Page, com.work.domain.PartTimeEngine)
	 * @author       huangqingjian/0050
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        清清网系统, 2014年7月25日
	 * @param page
	 * @param info
	 * @throws ServiceException
	 */
	@Override
	public void queryPartTime(Page<PartTimeEngine> page, PartTimeEngine info) throws ServiceException {
		try {
			dao.queryPartTime(page, info);
		} catch (DaoException e) {
			ExceptionHandle
					.throwServiceException(e, ErrorCode.SERVICE_ERROR, "PartTimeEngineServiceImpl.queryPartTime");
		}

	}

	/**
	 * 审核兼职信息
	 * (功能详细描述)
	 * @see com.work.service.job.IPartTimeEngineService#verifyEngine(com.work.domain.PartTimeEngine)
	 * @author       huangqingjian/0050
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        清清网系统, 2014年7月30日
	 * @param engine
	 */
	@Override
	public void saveVerifyEngine(PartTimeEngine engine) throws ServiceException {

		if (1 == engine.getIsVerify()) {

			try {
				if (0 == engine.getIsExist()) {
					PartTimeInfo info = new PartTimeInfo();
					BeanUtils.copyProperties(engine, info);
					info.setCreateDate(new Date());
					info.setPartTimeId(null);
					infoDao.saveOrUpdateInfo(info);
				}
				engine.setIsExist(1);
				dao.saveOrUpdateInfo(engine);
			} catch (DaoException e) {
				ExceptionHandle.throwServiceException(e, ErrorCode.SERVICE_ERROR,
						"PartTimeEngineServiceImpl.saveVerifyEngine");
			}
		}

	}

	/**
	 * 不分页获取采集的兼职信息
	 * 覆盖方法/实现方法(选择其一)
	 * (功能详细描述)
	 * @see com.work.service.job.IPartTimeEngineService#queryPartTime(com.work.domain.PartTimeEngine)
	 * @author       huangqingjian/0050
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        清清网系统, 2014年8月10日
	 * @param info
	 * @return
	 * @throws ServiceException
	 */
	@Override
	public List<PartTimeEngine> queryPartTime(PartTimeEngine info) throws ServiceException {
		try {
			return dao.queryPartTime(info);
		} catch (DaoException e) {
			ExceptionHandle
					.throwServiceException(e, ErrorCode.SERVICE_ERROR, "PartTimeEngineServiceImpl.queryPartTime");
		}
		return null;
	}

	/**
	 * 获取已经解析过的URL
	 * 覆盖方法/实现方法(选择其一)
	 * (功能详细描述)
	 * @see com.work.service.job.IPartTimeEngineService#queryPartTimeUrls(com.work.domain.PartTimeEngine)
	 * @author       huangqingjian/0050
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        清清网系统, 2014年8月10日
	 * @param info
	 * @return
	 * @throws ServiceException
	 */
	@Override
	public List<String> queryPartTimeUrls(PartTimeEngine info) throws ServiceException {
		List<String> list = null;
		try {
			list = new ArrayList<String>();
			List<PartTimeEngine> engines = dao.queryPartTime(info);
			if (CollectionUtils.isEmpty(engines)) {
				return null;
			}
			for (PartTimeEngine partTimeEngine : engines) {
				list.add(partTimeEngine.getSourceUrl());
			}
		} catch (DaoException e) {
			ExceptionHandle
					.throwServiceException(e, ErrorCode.SERVICE_ERROR, "PartTimeEngineServiceImpl.queryPartTime");
		}
		return list;
	}

	/**
	 * 解析兼职信息
	 * 覆盖方法/实现方法(选择其一)
	 * (功能详细描述)
	 * @see com.work.service.job.IPartTimeEngineService#parsePartTime()
	 * @author       huangqingjian/0050
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        清清网系统, 2014年8月10日
	 */
	@Override
	public void parsePartTime() {
		try {
			DataMiningSet set = new DataMiningSet();
			set.setMiningStatus(1);
			List<DataMiningSet> sets = service.queryDataMiningSet(set);
			List<PartTimeEngine> engines = new ArrayList<PartTimeEngine>();

			//获取已经存在的兼职信息
			List<String> urls = queryPartTimeUrls(null);

			for (DataMiningSet dataMiningSet : sets) {

				IJobInfoMiningService miningService = JobFactoryManager.createJobMining(dataMiningSet.getNetName());
				miningService.extractLinks(dataMiningSet, engines);
				int totalPartTime = engines.size();
				if (!CollectionUtils.isEmpty(urls)) {
					List<PartTimeEngine> existPartTimeEngines = new ArrayList<PartTimeEngine>();
					for (PartTimeEngine partTimeEngine : engines) {
						//如果已经存在则添加到待删除集合。
						if (urls.contains(partTimeEngine.getSourceUrl())) {
							existPartTimeEngines.add(partTimeEngine);
						}
					}
					//刪除已经存在的条数
					engines.removeAll(existPartTimeEngines);
					logger.info("总共获取兼职{}条，已经存在{}条，新兼职{}条！", new Object[] { totalPartTime, existPartTimeEngines.size(),
							engines.size() });
				} else {
					logger.info("总共获取兼职{}条，已经存在{}条，新兼职{}条！", new Object[] { totalPartTime, 0, totalPartTime });
				}
				saveOrUpdateInfo(engines);
				engines.clear();

			}

			logger.info("成功添加到待审核兼职表{}条记录！", engines.size());
		} catch (Exception e) {
			logger.error("Exception：", e);
		}
	}
}
