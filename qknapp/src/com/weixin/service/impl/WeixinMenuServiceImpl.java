/*
 * Title:        瑾泉二维码系统 2013年12月27日
 * Description:  [描述模块的功能、作用、使用方法和注意事项]
 * Copyright:    Copyright (c) 2012
 * Company:      Tai woo. Co., Ltd
 * @author       huangqingjian/0050
 * @version      1.0  2013年12月27日
 */
package com.weixin.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.qing.common.exception.DaoException;
import com.qing.common.exception.ServiceException;
import com.qing.common.exception.handle.ExceptionHandle;
import com.qing.common.util.ConfigUtil;
import com.qing.common.util.ErrorCode;
import com.weixin.dao.WeixinDaoAble;
import com.weixin.dao.domain.WeixinMenu;
import com.weixin.manager.WeixinUtil;
import com.weixin.pojo.AccessToken;
import com.weixin.pojo.Button;
import com.weixin.pojo.Menu;
import com.weixin.pojo.ViewButton;
import com.weixin.service.WeixinMenuServiceAble;

/**
 * 微信菜单服务类
 * @author       huangqingjian/0050
 * @see          [相关类,可选,也可多条,对于重要的类或接口建议注释]
 * @since        瑾泉二维码系统, 2013年12月27日
 */
@Service
@Repository
public class WeixinMenuServiceImpl implements WeixinMenuServiceAble {

	private static final Logger logger = LoggerFactory.getLogger(WeixinMenuServiceImpl.class);

	@Resource
	private WeixinDaoAble weixinDaoImpl;
	static ConfigUtil util;
	// 第三方用户唯一凭证
	private static final String appId = util.getAppId();
	// 第三方用户唯一凭证密钥
	private static final String appSecret = util.getAppSecret();

	/**
	 * 获取开启的菜单列表
	 * @see com.weixin.service.WeixinMenuServiceAble#getOpenMenu()
	 * @author       huangqingjian/0050
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        瑾泉二维码系统, 2013年12月27日
	 * @return
	 * @throws DaoException
	 */
	@Override
	public List<WeixinMenu> getALLMenu() throws ServiceException {

		try {
			return weixinDaoImpl.getAllMenu();
		} catch (DaoException e) {
			ExceptionHandle.throwServiceException(e, ErrorCode.SERVICE_QUERY_ERROR_004,
					"WeixinMenuServiceImpl.getOpenMenu");
		}
		return null;
	}

	/**
	 * 根据ID获取菜单对象
	 * 覆盖方法/实现方法(选择其一)
	 * (功能详细描述)
	 * @see com.weixin.service.WeixinMenuServiceAble#getMenuById(java.lang.String)
	 * @author       huangqingjian/0050
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        瑾泉二维码系统, 2013年12月30日
	 * @param id
	 * @return
	 * @throws ServiceException
	 */
	@Override
	public WeixinMenu getMenuById(String id) throws ServiceException {

		try {
			return weixinDaoImpl.getMenuById(id);
		} catch (DaoException e) {
			ExceptionHandle.throwServiceException(e, ErrorCode.SERVICE_QUERY_ERROR_004,
					"WeixinMenuServiceImpl.getMenuById");
		}
		return null;
	}

	/**
	 * 保存菜单列表
	 * @see com.weixin.service.WeixinMenuServiceAble#saveMenu(java.util.List)
	 * @author       huangqingjian/0050
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        瑾泉二维码系统, 2013年12月27日
	 * @param WeixinMenus
	 * @throws DaoException
	 */
	@Override
	public void saveMenu(List<WeixinMenu> WeixinMenus) throws ServiceException {
		try {
			weixinDaoImpl.saveMenu(WeixinMenus);
		} catch (DaoException e) {
			ExceptionHandle.throwServiceException(e, ErrorCode.SERVICE_QUERY_ERROR_004,
					"WeixinMenuServiceImpl.saveMenu");
		}
	}

	/**
	 * 覆盖方法/实现方法(选择其一)
	 * 发布菜单到微信服务器
	 * @see com.weixin.service.WeixinMenuServiceAble#publishMenu()
	 * @author       huangqingjian/0050
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        瑾泉二维码系统, 2013年12月27日
	 * @throws ServiceException
	 */
	@Override
	public int publishMenu() throws ServiceException {
		try {
			List<WeixinMenu> list = weixinDaoImpl.getOpenMenu();
			// 调用接口获取access_token
			AccessToken at = WeixinUtil.getAccessToken(appId, appSecret);

			if (null == list || list.size() < 1) {
				logger.info("没有可发布的菜单！");
				return WeixinUtil.removeMenu(at.getToken());
			}

			if (null != at) {
				// 调用接口创建菜单
				int result = WeixinUtil.createMenu(getMenu(list), at.getToken());

				return result;

			}

		} catch (DaoException e) {
			ExceptionHandle.throwServiceException(e, ErrorCode.SERVICE_QUERY_ERROR_004,
					"WeixinMenuServiceImpl.publishMenu");
		}
		return -1;

	}

	/**
	 * 构建微信view菜单
	 * @author       huangqingjian/0050
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        瑾泉二维码系统, 2013年12月27日
	 * @param list
	 * @return
	 */
	private Menu getMenu(List<WeixinMenu> list) {

		Button[] bottons = new Button[list.size()];
		int index = 0;
		for (WeixinMenu weixinMenu : list) {
			ViewButton btn = new ViewButton();
			btn.setName(weixinMenu.getMenuName());
			btn.setType("view");
			btn.setUrl(weixinMenu.getUrl());
			bottons[index++] = btn;
		}

		Menu menu = new Menu();
		menu.setButton(bottons);
		return menu;
	}
}
