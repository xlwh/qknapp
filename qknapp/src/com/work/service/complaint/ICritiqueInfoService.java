/*
 * Title:        清清网系统 2014年7月25日
 * Description:  [描述模块的功能、作用、使用方法和注意事项]
 * Copyright:    Copyright (c) 2014
 * Company:      清清网
 * @author       huangqingjian
 * @version      2.0  2014年7月25日
 */
package com.work.service.complaint;

import java.util.List;

import com.qing.common.exception.DaoException;
import com.qing.common.exception.ServiceException;
import com.qing.common.util.Page;
import com.work.domain.CritiqueInfo;

/**
 * 点评业务逻辑层
 * (功能详细描述)
 * @author       huangqingjian/0050
 * @see          [相关类,可选,也可多条,对于重要的类或接口建议注释]
 * @since        清清网系统, 2014年7月25日
 */
public interface ICritiqueInfoService {
	/**
	 * 新增或保存点评
	 * (一句话功能简述)
	 * (功能详细描述)
	 * @author       huangqingjian/0050
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        清清网系统, 2014年7月25日
	 * @param critiqueInfo
	 */
	public void saveOrUpdateCritiqueInfo(CritiqueInfo critiqueInfo) throws ServiceException;

	/**
	 * 删除点评
	 * (一句话功能简述)
	 * (功能详细描述)
	 * @author       huangqingjian/0050
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        清清网系统, 2014年7月25日
	 * @param ids
	 */
	public void deleteCritiqueInfos(String[] ids, String memberId) throws ServiceException;

	/**
	 * 根据ID获取点评详情
	 * (一句话功能简述)
	 * (功能详细描述)
	 * @author       huangqingjian/0050
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        清清网系统, 2014年7月25日
	 * @param id
	 * @return
	 */
	public CritiqueInfo getCritiqueInfo(String id) throws ServiceException;

	/**
	 * 根据查询条件分页查询点评
	 * (一句话功能简述)
	 * (功能详细描述)
	 * @author       huangqingjian/0050
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        清清网系统, 2014年7月25日
	 * @param page
	 * @param critiqueInfo
	 */
	public void queryCritiqueInfos(Page<CritiqueInfo> page, CritiqueInfo critiqueInfo) throws ServiceException;

	/**
	 * 根据查询条件查询所有点评
	 * (一句话功能简述)
	 * (功能详细描述)
	 * @author       huangqingjian/0050
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        清清网系统, 2014年8月24日
	 * @param critiqueInfo
	 * @return
	 * @throws ServiceException
	 */
	public List<CritiqueInfo> queryCritiqueInfo(CritiqueInfo critiqueInfo) throws ServiceException;

	/**
	 * 判断点赞或者无兴趣否已经存在
	 * (一句话功能简述)
	 * (功能详细描述)
	 * @author       huangqingjian/0050
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        清清网系统, 2014年9月27日
	 * @param critiqueInfo
	 * @return
	 * @throws DaoException 
	 * @throws ServiceException 
	 */
	boolean getExistInfo(CritiqueInfo critiqueInfo) throws ServiceException;

	/**
	 * 获取是否已经点过卖男人
	 * (一句话功能简述)
	 * (功能详细描述)
	 * @author       huangqingjian/0050
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        清清网系统, 2014年10月6日
	 * @param critiqueInfo
	 * @return
	 * @throws ServiceException
	 */
	boolean getExistSellMenInfo(CritiqueInfo critiqueInfo) throws ServiceException;
	
	/**
	 * 获取点击总数
	 * (一句话功能简述)
	 * (功能详细描述)
	 * @author       huangqingjian/0050
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        清清网系统, 2014年10月6日
	 * @param critiqueInfo
	 * @return
	 * @throws ServiceException
	 */
	public int getSize(CritiqueInfo critiqueInfo) throws ServiceException;

}
