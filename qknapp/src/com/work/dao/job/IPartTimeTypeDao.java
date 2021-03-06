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
import com.work.domain.PartTimeType;

/**
 * 兼职类型数据访问层
 * (功能详细描述)
 * @author       huangqingjian/0050
 * @see          [相关类,可选,也可多条,对于重要的类或接口建议注释]
 * @since        清清网系统, 2014年7月25日
 */
public interface IPartTimeTypeDao {
	/**
	 * 新增或保存兼职类型
	 * (一句话功能简述)
	 * (功能详细描述)
	 * @author       huangqingjian/0050
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        清清网系统, 2014年7月25日
	 * @param Type
	 */
	public void saveOrUpdateType(PartTimeType type) throws DaoException;

	/**
	 * 删除兼职類型
	 * (一句话功能简述)
	 * (功能详细描述)
	 * @author       huangqingjian/0050
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        清清网系统, 2014年7月25日
	 * @param Types
	 */
	public void deleteType(List<PartTimeType> types) throws DaoException;

	/**
	 * 根据ID获取兼职类型
	 * (一句话功能简述)
	 * (功能详细描述)
	 * @author       huangqingjian/0050
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        清清网系统, 2014年7月25日
	 * @param id
	 * @return
	 */
	public PartTimeType getPartTimeType(String id) throws DaoException;

	/**
	 * 根据查询条件分页查询兼职类型
	 * (一句话功能简述)
	 * (功能详细描述)
	 * @author       huangqingjian/0050
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        清清网系统, 2014年7月25日
	 * @param page
	 * @param Type
	 */
	public void queryPartTime(Page<PartTimeType> page, PartTimeType type) throws DaoException;

	/**
	 * 不分页查询所以的兼职类型
	 * (一句话功能简述)
	 * (功能详细描述)
	 * @author       huangqingjian/0050
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        清清网系统, 2014年8月6日
	 * @param type
	 * @throws DaoException
	 */
	public List<PartTimeType> queryPartTime(PartTimeType type) throws DaoException;

}
