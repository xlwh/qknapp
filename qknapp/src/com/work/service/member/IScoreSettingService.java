/*
 * Title:        清清网系统 2014年10月10日
 * Description:  [描述模块的功能、作用、使用方法和注意事项]
 * Copyright:    Copyright (c) 2014
 * Company:      清清网
 * @author       Sandy Wong
 * @version      2.0  2014年10月10日
 */
package com.work.service.member;

import java.util.List;

import com.qing.common.exception.ServiceException;
import com.qing.common.util.Page;
import com.work.domain.ScoreSetting;

/**
 * 积分设置service接口
 * @author       Sandy Wong
 * @see          [相关类,可选,也可多条,对于重要的类或接口建议注释]
 * @since        清清网系统, 2014年10月10日
 */
public interface IScoreSettingService {
	/**
	 * 保存积分设置
	 * @author       Sandy Wong
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        清清网系统, 2014年10月8日
	 * @param setting
	 * @throws ServiceException
	 */
	public void saveOrUpdateSetting(ScoreSetting setting) throws ServiceException;
	
	/**
	 * 删除积分设置
	 * @author       Sandy Wong
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        清清网系统, 2014年10月9日
	 * @param ids
	 * @throws ServiceException
	 */
	public void deleteSetting(String[] ids) throws ServiceException;

	/**
	 * 根据ID获取积分设置
	 * @author       Sandy Wong
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        清清网系统, 2014年10月8日
	 * @param id
	 * @return
	 * @throws ServiceException
	 */
	public ScoreSetting getScoreSetting(String id) throws ServiceException;
	
	/**
	 * 根据target获取积分设置
	 * @author       Sandy Wong
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        清清网系统, 2014年10月8日
	 * @param id
	 * @return
	 * @throws ServiceException
	 */
	public ScoreSetting getScoreSettingByTarget(String target) throws ServiceException;

	/**
	 * 查询积分设置
	 * @author       Sandy Wong
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        清清网系统, 2014年10月8日
	 * @param page
	 * @param setting
	 * @throws ServiceException
	 */
	public void queryScoreSetting(Page<ScoreSetting> page, ScoreSetting setting)
			throws ServiceException;

	/**
	 * 不分页查询积分设置
	 * @author       Sandy Wong
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        清清网系统, 2014年10月8日
	 * @param setting
	 * @return
	 * @throws ServiceException
	 */
	public List<ScoreSetting> queryScoreSetting(ScoreSetting setting) throws ServiceException;
}
