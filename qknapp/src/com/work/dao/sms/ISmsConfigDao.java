/*
 * Title:        二维码系统 2014年7月28日
 * Description:  [描述模块的功能、作用、使用方法和注意事项]
 * Copyright:    Copyright (c) 2012
 * Company:      Tai woo. Co., Ltd
 * @author       huangqingjian/0050
 * @version      1.0  2014年7月28日
 */
package com.work.dao.sms;

import java.util.List;

import com.qing.common.exception.DaoException;
import com.work.domain.SmsConfig;

/**
 * 短信接口配置管理类
 * (一句话功能简述)
 * (功能详细描述)
 * @author       huangqingjian/0050
 * @see          [相关类,可选,也可多条,对于重要的类或接口建议注释]
 * @since        二维码系统, 2014年7月28日
 */
public interface ISmsConfigDao {
	/**
	 * 更新配置项
	 * (一句话功能简述)
	 * (功能详细描述)
	 * @author       huangqingjian/0050
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        二维码系统, 2014年7月28日
	 * @param config
	 * @throws DaoException
	 */
	public void updateConfig(List<SmsConfig> config) throws DaoException;

	/**
	 * 查询所有配置
	 * (一句话功能简述)
	 * (功能详细描述)
	 * @author       huangqingjian/0050
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        二维码系统, 2014年7月28日
	 * @param config
	 * @throws DaoException
	 */
	public List<SmsConfig> queryAllConfig() throws DaoException;

	/**
	 * 根据tag获取参数配置项
	 * (一句话功能简述)
	 * (功能详细描述)
	 * @author       huangqingjian/0050
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        二维码系统, 2014年7月28日
	 * @return
	 * @throws DaoException
	 */
	public List<SmsConfig> getParamByTag(String tag) throws DaoException;
}
