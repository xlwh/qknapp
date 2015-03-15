/*
 * Title:        二维码系统 2014年7月28日
 * Description:  [描述模块的功能、作用、使用方法和注意事项]
 * Copyright:    Copyright (c) 2012
 * Company:      Tai woo. Co., Ltd
 * @author       huangqingjian/0050
 * @version      1.0  2014年7月28日
 */
package com.work.service.sms.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.qing.common.exception.DaoException;
import com.qing.common.exception.ServiceException;
import com.qing.common.exception.handle.ExceptionHandle;
import com.qing.common.util.ErrorCode;
import com.work.dao.sms.ISmsConfigDao;
import com.work.domain.SmsConfig;
import com.work.service.sms.ISmsConfigService;

/**
 * (一句话功能简述)
 * (功能详细描述)
 * @author       huangqingjian/0050
 * @see          [相关类,可选,也可多条,对于重要的类或接口建议注释]
 * @since        二维码系统, 2014年7月28日
 */
@Service
@Repository
public class SmsConfigServiceImpl implements ISmsConfigService {

	@Resource
	ISmsConfigDao configDao;

	/**
	 * 更新配置项
	 * (功能详细描述)
	 * @see com.sh.service.ISmsConfigService#updateConfig(java.util.List)
	 * @author       huangqingjian/0050
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        二维码系统, 2014年7月28日
	 * @param config
	 * @throws ServiceException
	 */
	@Override
	public void updateConfig(List<SmsConfig> config) throws ServiceException {
		try {
			configDao.updateConfig(config);
		} catch (DaoException e) {
			ExceptionHandle.throwServiceException(e, ErrorCode.SERVICE_ERROR, "SmsConfigServiceImpl.updateConfig");
		}
	}

	/**
	 * 覆盖方法/实现方法(选择其一)
	 * (功能详细描述)
	 * @see com.sh.service.ISmsConfigService#queryAllConfig()
	 * @author       huangqingjian/0050
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        二维码系统, 2014年7月28日
	 * @return
	 * @throws ServiceException
	 */
	@Override
	public List<SmsConfig> queryAllConfig() throws ServiceException {
		try {
			List<SmsConfig> list = configDao.queryAllConfig();
			return list;
		} catch (DaoException e) {
			ExceptionHandle.throwServiceException(e, ErrorCode.SERVICE_ERROR, "SmsConfigServiceImpl.queryAllConfig");
		}
		return null;
	}

	/**
	 * 覆盖方法/实现方法(选择其一)
	 * (功能详细描述)
	 * @see com.sh.service.ISmsConfigService#getParamByTag(java.lang.String)
	 * @author       huangqingjian/0050
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        二维码系统, 2014年7月28日
	 * @param tag
	 * @return
	 * @throws ServiceException
	 */
	@Override
	public SmsConfig getParamByTag(String tag) throws ServiceException {
		SmsConfig config = null;
		try {
			List<SmsConfig> list = configDao.getParamByTag(tag);
			if (!CollectionUtils.isEmpty(list)) {
				config = list.get(0);
			}

		} catch (DaoException e) {
			ExceptionHandle.throwServiceException(e, ErrorCode.SERVICE_ERROR, "SmsConfigServiceImpl.getParamByTag");
		}
		return config;
	}

}
