/*
 * Title:        瑾泉二维码系统 2013年12月27日
 * Description:  [描述模块的功能、作用、使用方法和注意事项]
 * Copyright:    Copyright (c) 2012
 * Company:      Tai woo. Co., Ltd
 * @author       huangqingjian/0050
 * @version      1.0  2013年12月27日
 */
package com.weixin.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.qing.common.base.dao.HibernateDao;
import com.qing.common.exception.DaoException;
import com.weixin.dao.WeixinDaoAble;
import com.weixin.dao.domain.WeixinMenu;

/**
 * (一句话功能简述)
 * (功能详细描述)
 * @author       huangqingjian/0050
 * @see          [相关类,可选,也可多条,对于重要的类或接口建议注释]
 * @since        瑾泉二维码系统, 2013年12月27日
 */
@Service
@Repository
public class WeixinDaoImpl implements WeixinDaoAble {
	@Resource
	private HibernateDao<WeixinMenu, String> weixinMenuDao;

	/**
	 * 覆盖方法/实现方法(选择其一)
	 * 获取开启的菜单
	 * @see com.weixin.dao.WeixinDaoAble#getOpenMenu()
	 * @author       huangqingjian/0050
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        瑾泉二维码系统, 2013年12月27日
	 * @return
	 * @throws DaoException 
	 */
	@Override
	public List<WeixinMenu> getOpenMenu() throws DaoException {
		String hql = "from WeixinMenu where open=1 and fatherId =0 order by sort";
		return weixinMenuDao.findByValues(hql, null, false);
	}

	/**
	 * 根据ID获取菜单对象
	 * 覆盖方法/实现方法(选择其一)
	 * (功能详细描述)
	 * @see com.weixin.dao.WeixinDaoAble#getMenuById(java.lang.String)
	 * @author       huangqingjian/0050
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        瑾泉二维码系统, 2013年12月30日
	 * @param id
	 * @return
	 * @throws DaoException
	 */
	@Override
	public WeixinMenu getMenuById(String id) throws DaoException {
		return weixinMenuDao.findById(WeixinMenu.class, id);
	}

	/**
	 * 覆盖方法/实现方法(选择其一)
	 * 保存菜单列表
	 * @see com.weixin.dao.WeixinDaoAble#saveMenu(java.util.List)
	 * @author       huangqingjian/0050
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        瑾泉二维码系统, 2013年12月27日
	 * @param WeixinMenus
	 * @throws DaoException 
	 */
	@Override
	public void saveMenu(List<WeixinMenu> WeixinMenus) throws DaoException {
		weixinMenuDao.saveOrUpdateAll(WeixinMenus);

	}

	/**
	 * 覆盖方法/实现方法(选择其一)
	 * 获取所有的菜单
	 * @see com.weixin.dao.WeixinDaoAble#getAllMenu()
	 * @author       huangqingjian/0050
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        瑾泉二维码系统, 2013年12月27日
	 * @return
	 * @throws DaoException
	 */
	@Override
	public List<WeixinMenu> getAllMenu() throws DaoException {
		String hql = "from WeixinMenu where fatherId =0 order by sort";
		return weixinMenuDao.findByValues(hql, null, false);
	}

}
