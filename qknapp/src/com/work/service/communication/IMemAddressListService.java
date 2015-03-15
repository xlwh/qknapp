/*
 * Title:        清清网系统 2014年7月25日
 * Description:  [描述模块的功能、作用、使用方法和注意事项]
 * Copyright:    Copyright (c) 2014
 * Company:      清清网
 * @author       huangqingjian
 * @version      2.0  2014年7月25日
 */
package com.work.service.communication;

import java.util.List;

import com.qing.common.exception.ServiceException;
import com.qing.common.util.Page;
import com.work.domain.MemAddressList;

/**
 * 通讯录业务逻辑层
 * (功能详细描述)
 * @author       huangqingjian/0050
 * @see          [相关类,可选,也可多条,对于重要的类或接口建议注释]
 * @since        清清网系统, 2014年7月25日
 */
public interface IMemAddressListService {
	/**
	 * 新增或保存通讯录
	 * (一句话功能简述)
	 * (功能详细描述)
	 * @author       huangqingjian/0050
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        清清网系统, 2014年7月25日
	 * @param memAddressList
	 */
	public void saveOrUpdateMemAddressList(MemAddressList memAddressList) throws ServiceException;

	/**
	 * 删除通讯录
	 * (一句话功能简述)
	 * (功能详细描述)
	 * @author       huangqingjian/0050
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        清清网系统, 2014年7月25日
	 * @param ids
	 */
	public void deleteMemAddressLists(String[] ids) throws ServiceException;

	/**
	 * 根据ID获取通讯录详情
	 * (一句话功能简述)
	 * (功能详细描述)
	 * @author       huangqingjian/0050
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        清清网系统, 2014年7月25日
	 * @param id
	 * @return
	 */
	public MemAddressList getMemAddressList(String id) throws ServiceException;

	/**
	 * 根据查询条件分页查询通讯录
	 * (一句话功能简述)
	 * (功能详细描述)
	 * @author       huangqingjian/0050
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        清清网系统, 2014年7月25日
	 * @param page
	 * @param memAddressList
	 */
	public void queryMemAddressLists(Page<MemAddressList> page, MemAddressList memAddressList) throws ServiceException;

	/**
	 * 根据查询条件查询所有通讯录
	 * (一句话功能简述)
	 * (功能详细描述)
	 * @author       huangqingjian/0050
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        清清网系统, 2014年8月24日
	 * @param memAddressList
	 * @return
	 * @throws ServiceException
	 */
	public List<MemAddressList> queryMemAddressList(MemAddressList memAddressList) throws ServiceException;

}
