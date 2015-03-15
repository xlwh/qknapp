/*
 * Title:        清清网系统 2014年7月25日
 * Description:  [描述模块的功能、作用、使用方法和注意事项]
 * Copyright:    Copyright (c) 2014
 * Company:      清清网
 * @author       huangqingjian
 * @version      2.0  2014年7月25日
 */
package com.work.dao.complaint;

import java.util.List;

import com.qing.common.exception.DaoException;
import com.qing.common.util.Page;
import com.work.domain.CritiqueInfo;

/**
 * 点评数据访问层
 * (功能详细描述)
 * @author       huangqingjian/0050
 * @see          [相关类,可选,也可多条,对于重要的类或接口建议注释]
 * @since        清清网系统, 2014年7月25日
 */
public interface ICritiqueInfoDao {
	/**
	 * 新增或保存点评
	 * (一句话功能简述)
	 * (功能详细描述)
	 * @author       huangqingjian/0050
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        清清网系统, 2014年7月25日
	 * @param info
	 */
	public void saveOrUpdateCritiqueInfo(CritiqueInfo critiqueInfo) throws DaoException;

	/**
	 * 删除点评
	 * (一句话功能简述)
	 * (功能详细描述)
	 * @author       huangqingjian/0050
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        清清网系统, 2014年7月25日
	 * @param infos
	 */
	public void deleteCritiqueInfos(List<CritiqueInfo> critiqueInfos) throws DaoException;

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
	public CritiqueInfo getCritiqueInfo(String id) throws DaoException;

	/**
	 * 根据查询条件分页查询点评
	 * (一句话功能简述)
	 * (功能详细描述)
	 * @author       huangqingjian/0050
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        清清网系统, 2014年7月25日
	 * @param page
	 * @param info
	 */
	public void queryCritiqueInfo(Page<CritiqueInfo> page, CritiqueInfo critiqueInfo) throws DaoException;

	/**
	 * 不分页查询所有点评
	 * (一句话功能简述)
	 * (功能详细描述)
	 * @author       huangqingjian/0050
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        清清网系统, 2014年8月24日
	 * @param CritiqueInfo
	 * @return
	 * @throws DaoException
	 */
	public List<CritiqueInfo> queryCritiqueInfo(CritiqueInfo critiqueInfo) throws DaoException;

	/**
	 * 获取是否已经存在
	 * (一句话功能简述)
	 * (功能详细描述)
	 * @author       huangqingjian/0050
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        清清网系统, 2014年9月27日
	 * @param critiqueInfo
	 * @return
	 * @throws DaoException
	 */
	public int getExistInfo(CritiqueInfo critiqueInfo) throws DaoException;

	/**
	 * 获取是否已经点过卖男人
	 * (一句话功能简述)
	 * (功能详细描述)
	 * @author       huangqingjian/0050
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        清清网系统, 2014年10月6日
	 * @param critiqueInfo
	 * @return
	 * @throws DaoException
	 */
	public int getExistSellMenInfo(CritiqueInfo critiqueInfo) throws DaoException;

	/**
	 * 获取点击总数
	 * (一句话功能简述)
	 * (功能详细描述)
	 * @author       huangqingjian/0050
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        清清网系统, 2014年10月6日
	 * @param critiqueInfo
	 * @return
	 * @throws DaoException
	 */
	public int getSize(CritiqueInfo critiqueInfo) throws DaoException;
}
