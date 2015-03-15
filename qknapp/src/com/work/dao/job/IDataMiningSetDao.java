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
import com.work.domain.DataMiningSet;

/**
 * 数据挖掘设置数据访问层
 * (功能详细描述)
 * @author       huangqingjian/0050
 * @see          [相关类,可选,也可多条,对于重要的类或接口建议注释]
 * @since        清清网系统, 2014年7月25日
 */
public interface IDataMiningSetDao {
	/**
	 * 新增或保存数据挖掘设置
	 * (一句话功能简述)
	 * (功能详细描述)
	 * @author       huangqingjian/0050
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        清清网系统, 2014年7月25日
	 * @param Set
	 */
	public void saveOrUpdateSet(DataMiningSet set) throws DaoException;

	/**
	 * 批量保存
	 * (一句话功能简述)
	 * (功能详细描述)
	 * @author       huangqingjian/0050
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        清清网系统, 2014年8月18日
	 * @param set
	 * @throws DaoException
	 */
	public void saveOrUpdateSets(List<DataMiningSet> set) throws DaoException;

	/**
	 * 删除兼职類型
	 * (一句话功能简述)
	 * (功能详细描述)
	 * @author       huangqingjian/0050
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        清清网系统, 2014年7月25日
	 * @param Sets
	 */
	public void deleteSet(List<DataMiningSet> sets) throws DaoException;

	/**
	 * 根据ID获取数据挖掘设置
	 * (一句话功能简述)
	 * (功能详细描述)
	 * @author       huangqingjian/0050
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        清清网系统, 2014年7月25日
	 * @param id
	 * @return
	 */
	public DataMiningSet getDataMiningSet(String id) throws DaoException;

	/**
	 * 根据查询条件分页查询数据挖掘设置
	 * (一句话功能简述)
	 * (功能详细描述)
	 * @author       huangqingjian/0050
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        清清网系统, 2014年7月25日
	 * @param page
	 * @param Set
	 */
	public void queryDataMiningSet(Page<DataMiningSet> page, DataMiningSet set) throws DaoException;

	/**
	 * 不分页查询所以的数据挖掘设置
	 * (一句话功能简述)
	 * (功能详细描述)
	 * @author       huangqingjian/0050
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        清清网系统, 2014年8月6日
	 * @param Set
	 * @throws DaoException
	 */
	public List<DataMiningSet> queryDataMiningSet(DataMiningSet set) throws DaoException;

}
