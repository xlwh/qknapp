/*
 * Title:        二维码系统 2014年7月28日
 * Description:  [描述模块的功能、作用、使用方法和注意事项]
 * Copyright:    Copyright (c) 2012
 * Company:      Tai woo. Co., Ltd
 * @author       huangqingjian/0050
 * @version      1.0  2014年7月28日
 */
package com.work.dao.sms.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.qing.common.base.dao.HibernateDao;
import com.qing.common.exception.DaoException;
import com.work.dao.sms.ISmsConfigDao;
import com.work.domain.SmsConfig;

/**
 * 短信接口配置数据访问层
 * (功能详细描述)
 * @author       huangqingjian/0050
 * @see          [相关类,可选,也可多条,对于重要的类或接口建议注释]
 * @since        二维码系统, 2014年7月28日
 */
@Repository
public class SmsConfigDaoImpl implements ISmsConfigDao {

	@Resource
	HibernateDao<SmsConfig, String> dao;

	/**
	 * 更新配置项
	 * 覆盖方法/实现方法(选择其一)
	 * (功能详细描述)
	 * @see com.work.dao.sms.sh.dao.ISmsConfigDao#updateConfig(com.sh.dao.domain.SmsConfig)
	 * @author       huangqingjian/0050
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        二维码系统, 2014年7月28日
	 * @param config
	 * @throws DaoException
	 */
	@Override
	public void updateConfig(List<SmsConfig> configs) throws DaoException {
		dao.saveOrUpdateAll(configs);

	}

	/**
	 * 获取所有的配置项
	 * 覆盖方法/实现方法(选择其一)
	 * (功能详细描述)
	 * @see com.work.dao.sms.sh.dao.ISmsConfigDao#queryAllConfig()
	 * @author       huangqingjian/0050
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        二维码系统, 2014年7月28日
	 * @return
	 * @throws DaoException
	 */
	@Override
	public List<SmsConfig> queryAllConfig() throws DaoException {
		return dao.findAll(SmsConfig.class);
	}

	/**
	 * 根据tag值获取对应的值
	 * 覆盖方法/实现方法(选择其一)
	 * (功能详细描述)
	 * @see com.work.dao.sms.sh.dao.ISmsConfigDao#getParamByTag(java.lang.String)
	 * @author       huangqingjian/0050
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        二维码系统, 2014年7月28日
	 * @param tag
	 * @return
	 * @throws DaoException
	 */
	@Override
	public List<SmsConfig> getParamByTag(String tag) throws DaoException {
		StringBuilder builder = new StringBuilder();
		builder.append("from SmsConfig where smsParamTag=? ");
		return dao.findByValues(builder.toString(), new Object[] { tag }, false);
	}
}
