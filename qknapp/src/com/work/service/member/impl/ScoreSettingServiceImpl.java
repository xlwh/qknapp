/*
 * Title:        清清网系统 2014年10月10日
 * Description:  [描述模块的功能、作用、使用方法和注意事项]
 * Copyright:    Copyright (c) 2014
 * Company:      清清网
 * @author       Sandy Wong
 * @version      2.0  2014年10月10日
 */
package com.work.service.member.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.qing.common.exception.DaoException;
import com.qing.common.exception.ServiceException;
import com.qing.common.exception.handle.ExceptionHandle;
import com.qing.common.util.ErrorCode;
import com.qing.common.util.Page;
import com.work.dao.member.IScoreSettingDao;
import com.work.domain.ScoreSetting;
import com.work.service.member.IScoreSettingService;

/**
 * 积分设置service实现
 * @author       Sandy Wong
 * @see          [相关类,可选,也可多条,对于重要的类或接口建议注释]
 * @since        清清网系统, 2014年10月10日
 */
@Service
@Repository
public class ScoreSettingServiceImpl implements IScoreSettingService {

	@Resource
	IScoreSettingDao dao;

	/**
	 * 覆盖方法/实现方法(选择其一)
	 * (功能详细描述)
	 * @see com.work.service.member.IScoreSettingService#saveOrUpdateSetting(com.work.domain.ScoreSetting)
	 * @author       Sandy Wong
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        清清网系统, 2014年10月10日
	 * @param setting
	 * @throws ServiceException
	 */
	@Override
	public void saveOrUpdateSetting(ScoreSetting setting) throws ServiceException {
		if (null == setting) {
			return;
		}
		try {
			setting.setModifyDate(new Date());
			dao.saveOrUpdateSetting(setting);
		} catch (DaoException e) {
			ExceptionHandle.throwServiceException(e, ErrorCode.SERVICE_ERROR,
					"ScoreSettingServiceImpl.saveOrUpdateSetting");
		}

	}

	/**
	 * 覆盖方法/实现方法(选择其一)
	 * (功能详细描述)
	 * @see com.work.service.member.IScoreSettingService#deleteSetting(com.work.domain.ScoreSetting)
	 * @author       Sandy Wong
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        清清网系统, 2014年10月10日
	 * @param ids
	 * @throws ServiceException
	 */
	@Override
	public void deleteSetting(String[] ids) throws ServiceException {
		List<ScoreSetting> settingList = new ArrayList<ScoreSetting>();
		ScoreSetting resume;
		try {
			for (int i = 0; i < ids.length; i++) {
				resume = dao.getScoreSetting(ids[i]);
				settingList.add(resume);
			}
			dao.deleteSetting(settingList);
		} catch (DaoException e) {
			ExceptionHandle.throwServiceException(e, ErrorCode.SERVICE_ERROR, "ScoreSettingServiceImpl.deleteSetting");
		}
	}

	/**
	 * 覆盖方法/实现方法(选择其一)
	 * (功能详细描述)
	 * @see com.work.service.member.IScoreSettingService#getScoreSetting(java.lang.String)
	 * @author       Sandy Wong
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        清清网系统, 2014年10月10日
	 * @param id
	 * @return
	 * @throws ServiceException
	 */
	@Override
	public ScoreSetting getScoreSetting(String id) throws ServiceException {
		ScoreSetting setting = null;
		try {
			setting = dao.getScoreSetting(id);
		} catch (DaoException e) {
			ExceptionHandle
					.throwServiceException(e, ErrorCode.SERVICE_ERROR, "ScoreSettingServiceImpl.getScoreSetting");
		}
		return setting;
	}

	/**
	 * 覆盖方法/实现方法(选择其一)
	 * (功能详细描述)
	 * @see com.work.service.member.IScoreSettingService#getScoreSettingByTarget(java.lang.String)
	 * @author       Sandy Wong
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        清清网系统, 2014年10月10日
	 * @param target
	 * @return
	 * @throws ServiceException
	 */
	@Override
	public ScoreSetting getScoreSettingByTarget(String target) throws ServiceException {
		ScoreSetting setting = null;
		try {
			setting = dao.getScoreSettingByTarget(target);
		} catch (DaoException e) {
			ExceptionHandle.throwServiceException(e, ErrorCode.SERVICE_ERROR,
					"ScoreSettingServiceImpl.getScoreSettingByTarget");
		}
		return setting;
	}

	/**
	 * 覆盖方法/实现方法(选择其一)
	 * (功能详细描述)
	 * @see com.work.service.member.IScoreSettingService#queryScoreSetting(com.qing.common.util.Page, com.work.domain.ScoreSetting)
	 * @author       Sandy Wong
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        清清网系统, 2014年10月10日
	 * @param page
	 * @param setting
	 * @throws ServiceException
	 */
	@Override
	public void queryScoreSetting(Page<ScoreSetting> page, ScoreSetting setting) throws ServiceException {
		try {
			dao.queryScoreSetting(page, setting);
		} catch (DaoException e) {
			ExceptionHandle.throwServiceException(e, ErrorCode.SERVICE_ERROR,
					"ScoreSettingServiceImpl.queryScoreSetting");
		}
	}

	/**
	 * 覆盖方法/实现方法(选择其一)
	 * (功能详细描述)
	 * @see com.work.service.member.IScoreSettingService#queryScoreSetting(com.work.domain.ScoreSetting)
	 * @author       Sandy Wong
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        清清网系统, 2014年10月10日
	 * @param setting
	 * @return
	 * @throws ServiceException
	 */
	@Override
	public List<ScoreSetting> queryScoreSetting(ScoreSetting setting) throws ServiceException {
		try {
			return dao.queryScoreSetting(setting);
		} catch (DaoException e) {
			ExceptionHandle.throwServiceException(e, ErrorCode.SERVICE_ERROR,
					"ScoreSettingServiceImpl.queryScoreSetting");
		}
		return new ArrayList<ScoreSetting>();
	}

}
