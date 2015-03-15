/*
 * Title:        清清网系统 2014年8月16日
 * Description:  [描述模块的功能、作用、使用方法和注意事项]
 * Copyright:    Copyright (c) 2014
 * Company:      清清网
 * @author       Sandy Wong
 * @version      2.0  2014年8月16日
 */
package com.work.dao.member;

import java.util.List;

import com.qing.common.exception.DaoException;
import com.qing.common.util.Page;
import com.work.domain.ScoreSetting;

/**
 * 积分设置dao接口
 * @author       Sandy Wong
 * @see          [相关类,可选,也可多条,对于重要的类或接口建议注释]
 * @since        清清网系统, 2014年10月8日
 */
public interface IScoreSettingDao {
	
	/**
	 * 保存积分设置
	 * @author       Sandy Wong
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        清清网系统, 2014年10月8日
	 * @param setting
	 * @throws DaoException
	 */
	public void saveOrUpdateSetting(ScoreSetting setting) throws DaoException;
	
	/**
	 * 删除积分设置
	 * @author       Sandy Wong
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        清清网系统, 2014年10月9日
	 * @param setting
	 * @throws DaoException
	 */
	public void deleteSetting(List<ScoreSetting> setting) throws DaoException;

	/**
	 * 根据ID获取积分设置
	 * @author       Sandy Wong
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        清清网系统, 2014年10月8日
	 * @param id
	 * @return
	 * @throws DaoException
	 */
	public ScoreSetting getScoreSetting(String id) throws DaoException;
	
	/**
	 * 根据target获取积分设置
	 * @author       Sandy Wong
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        清清网系统, 2014年10月8日
	 * @param id
	 * @return
	 * @throws DaoException
	 */
	public ScoreSetting getScoreSettingByTarget(String target) throws DaoException;

	/**
	 * 查询积分设置
	 * @author       Sandy Wong
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        清清网系统, 2014年10月8日
	 * @param page
	 * @param setting
	 * @throws DaoException
	 */
	public void queryScoreSetting(Page<ScoreSetting> page, ScoreSetting setting)
			throws DaoException;

	/**
	 * 不分页查询积分设置
	 * @author       Sandy Wong
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        清清网系统, 2014年10月8日
	 * @param setting
	 * @return
	 * @throws DaoException
	 */
	public List<ScoreSetting> queryScoreSetting(ScoreSetting setting) throws DaoException;
}
