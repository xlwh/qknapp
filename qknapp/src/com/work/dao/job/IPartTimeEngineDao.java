/*
 * Title:        清清网系统 2014年7月25日
 * Description:  [描述模块的功能、作用、使用方法和注意事项]
 * Copyright:    Copyright (c) 2014
 * Company:      清清网
 * @author       huangqingjian
 * @version      2.0  2014年7月25日
 */
package com.work.dao.job;

import java.util.List;

import com.qing.common.exception.DaoException;
import com.qing.common.util.Page;
import com.work.domain.PartTimeEngine;

/**
 * 通过数据挖掘引擎的兼职信息数据访问层
 * (功能详细描述)
 * @author       huangqingjian/0050
 * @see          [相关类,可选,也可多条,对于重要的类或接口建议注释]
 * @since        清清网系统, 2014年7月25日
 */
public interface IPartTimeEngineDao {
	/**
	 * 新增或保存兼职信息
	 * (一句话功能简述)
	 * (功能详细描述)
	 * @author       huangqingjian/0050
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        清清网系统, 2014年7月25日
	 * @param info
	 */
	public void saveOrUpdateInfo(PartTimeEngine info) throws DaoException;

	/**
	 * 删除兼职信息
	 * (一句话功能简述)
	 * (功能详细描述)
	 * @author       huangqingjian/0050
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        清清网系统, 2014年7月25日
	 * @param infos
	 */
	public void deleteInfo(List<PartTimeEngine> infos) throws DaoException;

	/**
	 * 根据ID获取兼职详情
	 * (一句话功能简述)
	 * (功能详细描述)
	 * @author       huangqingjian/0050
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        清清网系统, 2014年7月25日
	 * @param id
	 * @return
	 */
	public PartTimeEngine getPartTimeEngine(String id) throws DaoException;

	/**
	 * 根据查询条件分页查询兼职信息
	 * (一句话功能简述)
	 * (功能详细描述)
	 * @author       huangqingjian/0050
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        清清网系统, 2014年7月25日
	 * @param page
	 * @param info
	 */
	public void queryPartTime(Page<PartTimeEngine> page, PartTimeEngine info) throws DaoException;

	/**
	 * 不分页获取所以的审核兼职
	 * (一句话功能简述)
	 * (功能详细描述)
	 * @author       huangqingjian/0050
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        清清网系统, 2014年8月10日
	 * @param info
	 * @return
	 * @throws DaoException
	 */
	List<PartTimeEngine> queryPartTime(PartTimeEngine info) throws DaoException;

	/**
	 * 批量保存
	 * (一句话功能简述)
	 * (功能详细描述)
	 * @author       huangqingjian/0050
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        清清网系统, 2014年8月10日
	 * @param infos
	 * @throws DaoException
	 */
	void saveOrUpdateInfo(List<PartTimeEngine> infos) throws DaoException;

}
