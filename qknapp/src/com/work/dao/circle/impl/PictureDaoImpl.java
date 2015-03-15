/*
 * Title:        清清网系统 2014年8月18日
 * Description:  图片数据库访问接口实现
 * Copyright:    Copyright (c) 2014
 * Company:      清清网
 * @author       huangqingjian
 * @version      2.0  2014年8月18日
 */
package com.work.dao.circle.impl;

import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.qing.common.base.dao.HibernateDao;
import com.qing.common.exception.DaoException;
import com.work.dao.circle.IPictureDao;
import com.work.domain.Picture;

/**
 * (一句话功能简述)
 * (功能详细描述)
 * @author      zhanghongbin
 * @see          [相关类,可选,也可多条,对于重要的类或接口建议注释]
 * @since        清清网系统, 2014年8月18日
 */

@Repository
public class PictureDaoImpl implements IPictureDao{

	@Resource
	HibernateDao<Picture, String> hibernateDao;
	/**
	 * 覆盖方法/实现方法(选择其一)
	 * 保存图片
	 * @see com.work.dao.circle.IPictureDao#savePicture(com.work.domain.Picture)
	 * @author      zhanghongbin
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        清清网系统, 2014年8月18日
	 * @param picture
	 * @throws DaoException 
	 */
	@Override
	public void savePicture(Picture picture) throws DaoException {
		hibernateDao.saveOrUpdate(picture);
		
	}

	/**
	 * 覆盖方法/实现方法(选择其一)
	 * 删除图片
	 * @see com.work.dao.circle.IPictureDao#deletePicture(com.work.domain.Picture)
	 * @author      zhanghongbin
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        清清网系统, 2014年8月18日
	 * @param picture
	 * @throws DaoException 
	 */
	@Override
	public void deletePicture(Set<Picture> picture) throws DaoException {
		hibernateDao.removeAll(picture);
		
	}

	/**
	 * 覆盖方法/实现方法(选择其一)
	 * (功能详细描述)
	 * @see com.work.dao.circle.IPictureDao#findByMessageId(java.lang.String)
	 * @author      zhanghongbin
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        清清网系统, 2014年8月20日
	 * @param messageid
	 * @return
	 * @throws DaoException
	 */
	@Override
	public List<?> findByMessageId(String messageid) throws DaoException {
		
		return hibernateDao.findBySQL("from Picture where ", null);
	}

}
