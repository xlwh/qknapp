/*
 * Title:        清清网系统 2014年7月25日
 * Description:  [描述模块的功能、作用、使用方法和注意事项]
 * Copyright:    Copyright (c) 2014
 * Company:      清清网
 * @author       huangqingjian
 * @version      2.0  2014年7月25日
 */
package com.work.dao.communication;

import java.util.List;

import com.qing.common.exception.DaoException;
import com.qing.common.util.Page;
import com.work.domain.BlackWhiteList;

/**
 * 黑白名单数据访问层
 * (功能详细描述)
 * @author       huangqingjian/0050
 * @see          [相关类,可选,也可多条,对于重要的类或接口建议注释]
 * @since        清清网系统, 2014年7月25日
 */
public interface IBlackWhiteListDao {
	/**
	 * 新增或保存黑白名单
	 * (一句话功能简述)
	 * (功能详细描述)
	 * @author       huangqingjian/0050
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        清清网系统, 2014年7月25日
	 * @param info
	 */
	public void saveOrUpdateBlackWhiteList(BlackWhiteList blackWhiteList) throws DaoException;

	/**
	 * 删除黑白名单
	 * (一句话功能简述)
	 * (功能详细描述)
	 * @author       huangqingjian/0050
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        清清网系统, 2014年7月25日
	 * @param infos
	 */
	public void deleteBlackWhiteLists(List<BlackWhiteList> blackWhiteLists) throws DaoException;

	/**
	 * 根据ID获取黑白名单详情
	 * (一句话功能简述)
	 * (功能详细描述)
	 * @author       huangqingjian/0050
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        清清网系统, 2014年7月25日
	 * @param id
	 * @return
	 */
	public BlackWhiteList getBlackWhiteList(String id) throws DaoException;

	/**
	 * 根据查询条件分页查询黑白名单
	 * (一句话功能简述)
	 * (功能详细描述)
	 * @author       huangqingjian/0050
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        清清网系统, 2014年7月25日
	 * @param page
	 * @param info
	 */
	public void queryBlackWhiteList(Page<BlackWhiteList> page, BlackWhiteList blackWhiteList) throws DaoException;

	/**
	 * 不分页查询所有黑白名单
	 * (一句话功能简述)
	 * (功能详细描述)
	 * @author       huangqingjian/0050
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        清清网系统, 2014年8月24日
	 * @param blackWhiteList
	 * @return
	 * @throws DaoException
	 */
	public List<BlackWhiteList> queryBlackWhiteList(BlackWhiteList blackWhiteList) throws DaoException;

	/**
	 * 检查是否存在该手机号码
	 * (一句话功能简述)
	 * (功能详细描述)
	 * @author       huangqingjian/0050
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        清清网系统, 2014年10月13日
	 * @param blackWhiteList
	 * @return
	 * @throws DaoException
	 */
	int checkExist(BlackWhiteList blackWhiteList) throws DaoException;

}
