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

import com.qing.common.exception.ServiceException;
import com.qing.common.util.Page;
import com.work.domain.CollectInfo;

/**
 * 收藏业务逻辑层
 * (功能详细描述)
 * @author       huangqingjian/0050
 * @see          [相关类,可选,也可多条,对于重要的类或接口建议注释]
 * @since        清清网系统, 2014年7月25日
 */
public interface ICollectInfoService {
	/**
	 * 新增或保存收藏
	 * (一句话功能简述)
	 * (功能详细描述)
	 * @author       huangqingjian/0050
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        清清网系统, 2014年7月25日
	 * @param collectInfo
	 */
	public void saveOrUpdateCollectInfo(CollectInfo collectInfo) throws ServiceException;

	/**
	 * 删除收藏
	 * (一句话功能简述)
	 * (功能详细描述)
	 * @author       huangqingjian/0050
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        清清网系统, 2014年7月25日
	 * @param ids
	 */
	public void deleteCollectInfos(String[] ids, String memberId) throws ServiceException;

	/**
	 * 根据ID获取收藏详情
	 * (一句话功能简述)
	 * (功能详细描述)
	 * @author       huangqingjian/0050
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        清清网系统, 2014年7月25日
	 * @param id
	 * @return
	 */
	public CollectInfo getCollectInfo(String id) throws ServiceException;

	/**
	 * 根据查询条件分页查询收藏
	 * (一句话功能简述)
	 * (功能详细描述)
	 * @author       huangqingjian/0050
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        清清网系统, 2014年7月25日
	 * @param page
	 * @param collectInfo
	 */
	public void queryCollectInfos(Page<CollectInfo> page, CollectInfo collectInfo) throws ServiceException;

	/**
	 * 根据查询条件查询所有收藏
	 * (一句话功能简述)
	 * (功能详细描述)
	 * @author       huangqingjian/0050
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        清清网系统, 2014年8月24日
	 * @param collectInfo
	 * @return
	 * @throws ServiceException
	 */
	public List<CollectInfo> queryCollectInfo(CollectInfo collectInfo) throws ServiceException;

	/**
	 * 获取已经存在的信息
	 * (一句话功能简述)
	 * (功能详细描述)
	 * @author       huangqingjian/0050
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        清清网系统, 2014年9月27日
	 * @param infoId
	 * @return
	 * @throws ServiceException 
	 */
	public boolean getExistInfo(String infoId) throws ServiceException;

	/**
	 * (一句话功能简述)
	 * (功能详细描述)
	 * @author       huangqingjian/0050
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        清清网系统, 2014年10月18日
	 * @param collectInfo
	 * @return
	 * @throws ServiceException
	 */
	int getSize(CollectInfo collectInfo) throws ServiceException;

}
