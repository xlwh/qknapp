/*
 * Title:        清清网系统 2014年7月25日
 * Description:  [描述模块的功能、作用、使用方法和注意事项]
 * Copyright:    Copyright (c) 2014
 * Company:      清清网
 * @author       huangqingjian
 * @version      2.0  2014年7月25日
 */
package com.work.service.job;

import java.util.List;

import com.qing.common.exception.ServiceException;
import com.qing.common.util.Page;
import com.work.domain.PartTimeEngine;

/**
 * 兼职信息业务逻辑层
 * (功能详细描述)
 * @author       huangqingjian/0050
 * @see          [相关类,可选,也可多条,对于重要的类或接口建议注释]
 * @since        清清网系统, 2014年7月25日
 */
public interface IPartTimeEngineService {
	/**
	 * 新增或保存兼职信息
	 * (一句话功能简述)
	 * (功能详细描述)
	 * @author       huangqingjian/0050
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        清清网系统, 2014年7月25日
	 * @param info
	 */
	public void saveOrUpdateInfo(PartTimeEngine info) throws ServiceException;

	/**
	 * 删除兼职信息
	 * (一句话功能简述)
	 * (功能详细描述)
	 * @author       huangqingjian/0050
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        清清网系统, 2014年7月25日
	 * @param infos
	 */
	public void deleteInfo(String[] ids) throws ServiceException;

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
	public PartTimeEngine getPartTimeEngine(String id) throws ServiceException;

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
	public void queryPartTime(Page<PartTimeEngine> page, PartTimeEngine info) throws ServiceException;

	/**
	 * 
	 * 审核数据挖掘采集的信息
	 * (功能详细描述)
	 * @author       huangqingjian/0050
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        清清网系统, 2014年7月30日
	 * @param engine
	 */
	public void saveVerifyEngine(PartTimeEngine engine) throws ServiceException;

	/**
	 * 不分页获取采集的信息
	 * (功能详细描述)
	 * @author       huangqingjian/0050
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        清清网系统, 2014年8月10日
	 * @param info
	 * @return
	 * @throws ServiceException
	 */
	List<PartTimeEngine> queryPartTime(PartTimeEngine info) throws ServiceException;

	/**
	 * 获取已经解析过的URL
	 * (功能详细描述)
	 * @author       huangqingjian/0050
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        清清网系统, 2014年8月10日
	 * @param info
	 * @return
	 * @throws ServiceException
	 */
	List<String> queryPartTimeUrls(PartTimeEngine info) throws ServiceException;

	/**
	 * 批量保存
	 * (一句话功能简述)
	 * (功能详细描述)
	 * @author       huangqingjian/0050
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        清清网系统, 2014年8月10日
	 * @param infos
	 * @throws ServiceException
	 */
	public void saveOrUpdateInfo(List<PartTimeEngine> infos) throws ServiceException;

	/**
	 * 解析兼职信息
	 * (一句话功能简述)
	 * (功能详细描述)
	 * @author       huangqingjian/0050
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        清清网系统, 2014年8月10日
	 */
	public void parsePartTime();

}
