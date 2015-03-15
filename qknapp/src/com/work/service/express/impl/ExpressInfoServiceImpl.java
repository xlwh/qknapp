/*
 * Title:        美芝澳二维码系统 2014年1月24日
 * Description:  [描述模块的功能、作用、使用方法和注意事项]
 * Copyright:    Copyright (c) 2012
 * Company:      Tai woo. Co., Ltd
 * @author       huangqingjian/0050
 * @version      1.0  2014年1月24日
 */
package com.work.service.express.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.qing.common.exception.DaoException;
import com.qing.common.exception.ServiceException;
import com.qing.common.exception.handle.ExceptionHandle;
import com.qing.common.util.ErrorCode;
import com.work.dao.express.ExpressInfoDaoAble;
import com.work.domain.ExpressDetail;
import com.work.domain.ExpressInfo;
import com.work.domain.SenderInfo;
import com.work.service.express.ExpressInfoServiceAble;
import com.work.vo.ExpressInfoVo;

/**
 * 快递单模板服务类
 * (一句话功能简述)
 * (功能详细描述)
 * @author       huangqingjian/0050
 * @see          [相关类,可选,也可多条,对于重要的类或接口建议注释]
 * @since        美芝澳二维码系统, 2014年1月24日
 */
@Service
@Repository
public class ExpressInfoServiceImpl implements ExpressInfoServiceAble {

	@Resource
	ExpressInfoDaoAble expressInfoDaoAble;

	/**
	 * 获取所有的快递单模板，不包括快递单字段详情
	 * 覆盖方法/实现方法(选择其一)
	 * (功能详细描述)
	 * @see com.work.service.express.impl.mzo.service.ExpressInfoServiceAble#getALLExpressInfos()
	 * @author       huangqingjian/0050
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        美芝澳二维码系统, 2014年1月24日
	 * @return
	 * @throws DaoException
	 */
	@Override
	public List<ExpressInfo> getALLExpressInfos() throws ServiceException {

		try {
			return expressInfoDaoAble.getALLExpressInfos();
		} catch (DaoException e) {
			ExceptionHandle.throwServiceException(e, ErrorCode.SERVICE_QUERY_ERROR_004,
					"ExpressInfoServiceImpl.getALLExpressInfos");
		}
		return null;
	}

	/**
	 * 
	 * 获取单个快递单模板包括详情
	 * 覆盖方法/实现方法(选择其一)
	 * (功能详细描述)
	 * @see com.work.service.express.impl.mzo.service.ExpressInfoServiceAble#getExpressInfo(java.lang.String)
	 * @author       huangqingjian/0050
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        美芝澳二维码系统, 2014年1月24日
	 * @param id
	 * @return
	 * @throws DaoException
	 */
	@Override
	public ExpressInfoVo getExpressInfo(String id) throws ServiceException {
		ExpressInfoVo vo = new ExpressInfoVo();

		try {
			ExpressInfo info = expressInfoDaoAble.getExpressInfo(id);
			BeanUtils.copyProperties(info, vo);
			vo.setDetails(expressInfoDaoAble.getExpressDetail(id));
		} catch (DaoException e) {
			ExceptionHandle.throwServiceException(e, ErrorCode.SERVICE_QUERY_ERROR_004,
					"ExpressInfoServiceImpl.getExpressInfo");
		}
		return vo;
	}

	@Override
	public ExpressInfoVo getDefaultExpressInfo() throws ServiceException {
		ExpressInfoVo vo = new ExpressInfoVo();

		try {
			BeanUtils.copyProperties(expressInfoDaoAble.getDefaultExpressInfo(), vo);
			vo.setDetails(expressInfoDaoAble.getExpressDetail(vo.getExpressId()));
		} catch (DaoException e) {
			ExceptionHandle.throwServiceException(e, ErrorCode.SERVICE_QUERY_ERROR_004,
					"ExpressInfoServiceImpl.getDefaultExpressInfo");
		}
		return vo;
	}

	/**
	 * 设置默认模板
	 * 覆盖方法/实现方法(选择其一)
	 * (功能详细描述)
	 * @see com.work.service.express.impl.mzo.service.ExpressInfoServiceAble#updateExpressDefaultSet(java.lang.String)
	 * @author       huangqingjian/0050
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        美芝澳二维码系统, 2014年1月25日
	 * @param id
	 * @throws ServiceException
	 */
	@Override
	public void updateExpressDefaultSet(String id) throws ServiceException {
		try {
			expressInfoDaoAble.updateExpressDefaultSet(id);
		} catch (DaoException e) {
			ExceptionHandle.throwServiceException(e, ErrorCode.SERVICE_QUERY_ERROR_004,
					"ExpressInfoServiceImpl.updateExpressDefaultSet");
		}
	}

	/**
	 * 设置模板偏移量
	 * 覆盖方法/实现方法(选择其一)
	 * (功能详细描述)
	 * @see com.work.service.express.impl.mzo.service.ExpressInfoServiceAble#updateExpressOffSet(java.lang.String, java.lang.Integer, java.lang.Integer)
	 * @author       huangqingjian/0050
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        美芝澳二维码系统, 2014年2月7日
	 * @param id
	 * @param widthOffset
	 * @param heightOffset
	 * @throws ServiceException
	 */
	@Override
	public void updateExpressOffSet(String id, Integer widthOffset, Integer heightOffset) throws ServiceException {
		try {
			expressInfoDaoAble.updateExpressOffSet(id, widthOffset, heightOffset);
		} catch (DaoException e) {
			ExceptionHandle.throwServiceException(e, ErrorCode.SERVICE_QUERY_ERROR_004,
					"ExpressInfoServiceImpl.updateExpressDefaultSet");
		}
	}

	/**
	 * 保存模板详细设置
	 * 覆盖方法/实现方法(选择其一)
	 * (功能详细描述)
	 * @see com.work.service.express.impl.mzo.service.ExpressInfoServiceAble#saveOrUpdateExpressDetails(java.util.List)
	 * @author       huangqingjian/0050
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        美芝澳二维码系统, 2014年2月7日
	 * @param details
	 * @throws ServiceException
	 */
	@Override
	public void saveOrUpdateExpressDetails(List<ExpressDetail> details) throws ServiceException {
		try {
			expressInfoDaoAble.saveOrUpdateExpressDetails(details);
		} catch (DaoException e) {
			ExceptionHandle.throwServiceException(e, ErrorCode.SERVICE_QUERY_ERROR_004,
					"ExpressInfoServiceImpl.saveOrUpdateExpressDetails");
		}
	}

	/**
	 * 获取默认发货人地址
	 * (一句话功能简述)
	 * (功能详细描述)
	 * @author       huangqingjian/0050
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        美芝澳二维码系统, 2014年2月21日
	 * @return
	 * @throws ServiceException
	 */
	@Override
	public SenderInfo getDefaultSenderInfo() throws ServiceException {
		try {
			return expressInfoDaoAble.getDefaultSenderInfo();
		} catch (DaoException e) {
			ExceptionHandle.throwServiceException(e, ErrorCode.SERVICE_QUERY_ERROR_004,
					"ExpressInfoServiceImpl.getDefaultSenderInfo");
		}
		return null;
	}
}
