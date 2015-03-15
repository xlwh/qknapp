/*
 * Title:        清清网系统 2014年7月25日
 * Description:  [描述模块的功能、作用、使用方法和注意事项]
 * Copyright:    Copyright (c) 2014
 * Company:      清清网
 * @author       huangqingjian
 * @version      2.0  2014年7月25日
 */
package com.work.service.complaint.impl;

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
import com.work.dao.complaint.ICollectInfoDao;
import com.work.domain.CollectInfo;
import com.work.service.complaint.ICollectInfoService;

/**
 * 收藏业务逻辑层
 * (功能详细描述)
 * @author       huangqingjian/0050
 * @see          [相关类,可选,也可多条,对于重要的类或接口建议注释]
 * @since        清清网系统, 2014年7月25日
 */
@Service
public class CollectInfoServiceImpl implements ICollectInfoService {

	@Resource
	ICollectInfoDao dao;

	/**
	 *保存或修改收藏
	 * (功能详细描述)
	 * @see com.work.service.communication.ICollectInfoService#saveOrUpdateCollectInfo(com.work.domain.CollectInfo)
	 * @author       huangqingjian/0050
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        清清网系统, 2014年8月24日
	 * @param collectInfo
	 * @throws ServiceException
	 */
	@Override
	public void saveOrUpdateCollectInfo(CollectInfo collectInfo) throws ServiceException {
		try {
			if (null == collectInfo) {
				return;
			}
			dao.saveOrUpdateCollectInfo(collectInfo);

		} catch (DaoException e) {
			ExceptionHandle.throwServiceException(e, ErrorCode.SERVICE_ERROR,
					"CollectInfoServiceImpl.saveOrUpdateCollectInfo");
		}

	}

	/**
	 * 批量删除收藏
	 * (功能详细描述)
	 * @see com.work.service.communication.ICollectInfoService#deleteCollectInfos(java.lang.String[])
	 * @author       huangqingjian/0050
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        清清网系统, 2014年8月24日
	 * @param ids
	 * @throws ServiceException
	 */
	@Override
	public void deleteCollectInfos(String[] ids, String memberId) throws ServiceException {
		List<CollectInfo> collectInfos = new ArrayList<CollectInfo>();
		try {
			if (null != ids && ids.length > 0) {
				for (String id : ids) {
					CollectInfo collectInfo = dao.getCollectInfo(id);

					if (null != collectInfo) {
						if (null != collectInfo.getMemberId() && memberId.equals(collectInfo.getMemberId())) {
							collectInfos.add(collectInfo);
						}
					}

				}
			}
			if (!CollectionUtils.isEmpty(collectInfos)) {
				dao.deleteCollectInfos(collectInfos);
			}
		} catch (DaoException e) {
			ExceptionHandle.throwServiceException(e, ErrorCode.SERVICE_ERROR,
					"CollectInfoServiceImpl.deleteCollectInfos");
		}
	}

	/**
	 * 根据ID获取单个收藏
	 * (功能详细描述)
	 * @see com.work.service.communication.ICollectInfoService#getCollectInfo(java.lang.String)
	 * @author       huangqingjian/0050
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        清清网系统, 2014年8月24日
	 * @param id
	 * @return
	 * @throws ServiceException
	 */
	@Override
	public CollectInfo getCollectInfo(String id) throws ServiceException {
		try {
			return dao.getCollectInfo(id);
		} catch (DaoException e) {
			ExceptionHandle.throwServiceException(e, ErrorCode.SERVICE_ERROR, "CollectInfoServiceImpl.getCollectInfo");
		}
		return null;
	}

	/**
	 * 分页查询收藏
	 * 覆盖方法/实现方法(选择其一)
	 * (功能详细描述)
	 * @see com.work.service.communication.ICollectInfoService#queryCollectInfos(com.qing.common.util.Page, com.work.domain.CollectInfo)
	 * @author       huangqingjian/0050
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        清清网系统, 2014年8月24日
	 * @param page
	 * @param collectInfo
	 * @throws ServiceException
	 */
	@Override
	public void queryCollectInfos(Page<CollectInfo> page, CollectInfo collectInfo) throws ServiceException {
		try {
			dao.queryCollectInfo(page, collectInfo);
		} catch (DaoException e) {
			ExceptionHandle.throwServiceException(e, ErrorCode.SERVICE_ERROR,
					"CollectInfoServiceImpl.queryCollectInfos");
		}
	}

	/**
	 * 查询所有收藏
	 * 覆盖方法/实现方法(选择其一)
	 * (功能详细描述)
	 * @see com.work.service.communication.ICollectInfoService#queryCollectInfo(com.work.domain.CollectInfo)
	 * @author       huangqingjian/0050
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        清清网系统, 2014年8月24日
	 * @param collectInfo
	 * @return
	 */
	@Override
	public List<CollectInfo> queryCollectInfo(CollectInfo collectInfo) throws ServiceException {
		try {
			return dao.queryCollectInfo(collectInfo);
		} catch (DaoException e) {
			ExceptionHandle
					.throwServiceException(e, ErrorCode.SERVICE_ERROR, "CollectInfoServiceImpl.queryCollectInfo");
		}
		return null;
	}

	/**
	 * 判断信息是否已经存在
	 * 覆盖方法/实现方法(选择其一)
	 * (功能详细描述)
	 * @see com.work.service.complaint.ICollectInfoService#getExistInfo(java.lang.String)
	 * @author       huangqingjian/0050
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        清清网系统, 2014年9月27日
	 * @param infoId
	 * @return
	 * @throws ServiceException
	 */
	@Override
	public boolean getExistInfo(String infoId) throws ServiceException {

		int count;
		try {
			count = dao.getExistCollectInfo(infoId);
			if (count > 0) {
				return true;
			}
		} catch (DaoException e) {
			ExceptionHandle.throwServiceException(e, ErrorCode.SERVICE_ERROR, "CollectInfoServiceImpl.getExistInfo");
		}

		return false;

	}

	@Override
	public int getSize(CollectInfo collectInfo) throws ServiceException {
		try {
			return dao.getSize(collectInfo);
		} catch (DaoException e) {
			ExceptionHandle.throwServiceException(e, ErrorCode.SERVICE_ERROR, "CritiqueInfoServiceImpl.getSize");
		}
		return 0;
	}
}
