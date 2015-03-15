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
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.qing.common.exception.DaoException;
import com.qing.common.exception.ServiceException;
import com.qing.common.exception.handle.ExceptionHandle;
import com.qing.common.util.ErrorCode;
import com.qing.common.util.Page;
import com.work.dao.member.IScoreDetailDao;
import com.work.domain.ScoreDetail;
import com.work.service.member.IScoreDetailService;

/**
 * 积分明细service实现
 * @author       Sandy Wong
 * @see          [相关类,可选,也可多条,对于重要的类或接口建议注释]
 * @since        清清网系统, 2014年10月10日
 */
@Service
@Repository
public class ScoreDetailServiceImpl implements IScoreDetailService {

	@Resource
	IScoreDetailDao dao;

	/**
	 * 覆盖方法/实现方法(选择其一)
	 * (功能详细描述)
	 * @see com.work.service.member.IScoreDetailService#saveOrUpdateDetail(com.work.domain.ScoreDetail)
	 * @author       Sandy Wong
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        清清网系统, 2014年10月11日
	 * @param detail
	 * @throws ServiceException
	 */
	@Override
	public void saveOrUpdateDetail(ScoreDetail detail) throws ServiceException {
		if (null == detail) {
			return;
		}
		try {
			this.dao.saveOrUpdateDetail(detail);
		} catch (DaoException e) {
			ExceptionHandle.throwServiceException(e, ErrorCode.SERVICE_ERROR,
					"ScoreDetailServiceImpl.saveOrUpdateDetail");
		}
	}

	/**
	 * 覆盖方法/实现方法(选择其一)
	 * (功能详细描述)
	 * @see com.work.service.member.IScoreDetailService#getScoreDetail(java.lang.String)
	 * @author       Sandy Wong
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        清清网系统, 2014年10月11日
	 * @param id
	 * @return
	 * @throws ServiceException
	 */
	@Override
	public ScoreDetail getScoreDetail(String id) throws ServiceException {
		try {
			return this.dao.getScoreDetail(id);
		} catch (DaoException e) {
			ExceptionHandle.throwServiceException(e, ErrorCode.SERVICE_ERROR, 
					"ScoreDetailServiceImpl.getScoreDetail");
		}
		return null;
	}

	/**
	 * 覆盖方法/实现方法(选择其一)
	 * (功能详细描述)
	 * @see com.work.service.member.IScoreDetailService#queryScoreDetail(com.qing.common.util.Page, com.work.domain.ScoreDetail)
	 * @author       Sandy Wong
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        清清网系统, 2014年10月11日
	 * @param page
	 * @param detail
	 * @throws ServiceException
	 */
	@Override
	public void queryScoreDetail(Page<ScoreDetail> page, ScoreDetail detail) throws ServiceException {
		try {
			this.dao.queryScoreDetail(page, detail);
		} catch (DaoException e) {
			ExceptionHandle.throwServiceException(e, ErrorCode.SERVICE_ERROR, 
					"ScoreDetailServiceImpl.queryScoreDetail");
		}
	}

	/**
	 * 覆盖方法/实现方法(选择其一)
	 * (功能详细描述)
	 * @see com.work.service.member.IScoreDetailService#queryScoreDetail(com.work.domain.ScoreDetail)
	 * @author       Sandy Wong
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        清清网系统, 2014年10月11日
	 * @param detail
	 * @return
	 * @throws ServiceException
	 */
	@Override
	public List<ScoreDetail> queryScoreDetail(ScoreDetail detail) throws ServiceException {
		try {
			return this.dao.queryScoreDetail(detail);
		} catch (DaoException e) {
			ExceptionHandle.throwServiceException(e, ErrorCode.SERVICE_ERROR,
					"ScoreDetailServiceImpl.queryScoreDetail");
		}
		return new ArrayList<ScoreDetail>();
	}

	/**
	 * 覆盖方法/实现方法(选择其一)
	 * (功能详细描述)
	 * @see com.work.service.member.IScoreDetailService#getNumOfDetail(com.work.domain.ScoreDetail)
	 * @author       Sandy Wong
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        清清网系统, 2014年10月11日
	 * @param detail
	 * @return
	 * @throws ServiceException
	 */
	@Override
	public List<Map<String, Object>> getRankOfDetail(ScoreDetail detail, Integer limitNum, ScoreDetail afterRanking) throws ServiceException {
		try {
			return this.dao.getRankOfDetail(detail, limitNum, afterRanking);
		} catch (DaoException e) {
			ExceptionHandle.throwServiceException(e, ErrorCode.SERVICE_ERROR, "ScoreDetailServiceImpl.getNumOfDetail");
		}
		return null;
	}
	
	/**
	 * 分页查询排名信息
	 * @author       Sandy Wong
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        清清网系统, 2014年11月9日
	 * @param detail
	 * @param page
	 * @param afterSorting
	 * @throws ServiceException
	 */
	public void getRankOfDetailWithPage(ScoreDetail detail, Page<Map<String, Object>> page, ScoreDetail afterRanking)
			throws ServiceException {
		try {
			this.dao.getRankOfDetailWithPage(detail, page, afterRanking);
		} catch (DaoException e) {
			ExceptionHandle.throwServiceException(e, ErrorCode.SERVICE_ERROR, "ScoreDetailServiceImpl.getNumOfDetail");
		}
	}

	/**
	 * 覆盖方法/实现方法(选择其一)
	 * (功能详细描述)
	 * @see com.work.service.member.IScoreDetailService#queryRecommendDetail(com.qing.common.util.Page, com.work.domain.ScoreDetail)
	 * @author       Sandy Wong
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        清清网系统, 2014年11月9日
	 * @param page
	 * @param detail
	 * @throws ServiceException
	 */
	@Override
	public void queryRecommendDetail(Page<ScoreDetail> page, ScoreDetail detail) throws ServiceException {
		try {
			this.dao.queryRecommendDetail(page, detail);
		} catch (DaoException e) {
			ExceptionHandle.throwServiceException(e, ErrorCode.SERVICE_ERROR,
					"ScoreDetailServiceImpl.queryRecommendDetail");
		}
	}

	/**
	 * 覆盖方法/实现方法(选择其一)
	 * (功能详细描述)
	 * @see com.work.service.member.IScoreDetailService#queryRecommendDetail(com.work.domain.ScoreDetail)
	 * @author       Sandy Wong
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        清清网系统, 2014年11月9日
	 * @param detail
	 * @return
	 * @throws ServiceException
	 */
	@Override
	public List<ScoreDetail> queryRecommendDetail(ScoreDetail detail) throws ServiceException {
		try {
			return this.dao.queryRecommendDetail(detail);
		} catch (DaoException e) {
			ExceptionHandle.throwServiceException(e, ErrorCode.SERVICE_ERROR,
					"ScoreDetailServiceImpl.queryRecommendDetail");
		}
		return new ArrayList<ScoreDetail>();
	}
}
