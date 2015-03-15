/*
 * Title:        瑾泉二维码系统 2013年12月27日
 * Description:  [描述模块的功能、作用、使用方法和注意事项]
 * Copyright:    Copyright (c) 2012
 * Company:      Tai woo. Co., Ltd
 * @author       huangqingjian/0050
 * @version      1.0  2013年12月27日
 */
package com.weixin.service;

import java.util.List;

import com.qing.common.exception.DaoException;
import com.qing.common.exception.ServiceException;
import com.weixin.dao.domain.WeixinMenu;

/**
 * 微信菜单服务类
 * (功能详细描述)
 * @author       huangqingjian/0050
 * @see          [相关类,可选,也可多条,对于重要的类或接口建议注释]
 * @since        瑾泉二维码系统, 2013年12月27日
 */
public interface WeixinMenuServiceAble {
	/**
	 * 获取开启的一级菜单
	 * (一句话功能简述)
	 * (功能详细描述)
	 * @author       huangqingjian/0050
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        瑾泉二维码系统, 2013年12月27日
	 * @return
	 * @throws DaoException 
	 */
	public List<WeixinMenu> getALLMenu() throws ServiceException;

	/**
	 * 保存菜单列表到数据库
	 * (一句话功能简述)
	 * (功能详细描述)
	 * @author       huangqingjian/0050
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        瑾泉二维码系统, 2013年12月27日
	 * @param WeixinMenus
	 * @throws DaoException 
	 */
	public void saveMenu(List<WeixinMenu> WeixinMenus) throws ServiceException;

	/**
	 * 发布菜单到微信服务器
	 * (一句话功能简述)
	 * (功能详细描述)
	 * @author       huangqingjian/0050
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        瑾泉二维码系统, 2013年12月27日
	 * @throws ServiceException
	 */
	public int publishMenu() throws ServiceException;

	/**
	 * 根据ID获取菜单对象
	 * (一句话功能简述)
	 * (功能详细描述)
	 * @author       huangqingjian/0050
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        瑾泉二维码系统, 2013年12月30日
	 * @return
	 * @throws ServiceException
	 */
	WeixinMenu getMenuById(String id) throws ServiceException;
}
