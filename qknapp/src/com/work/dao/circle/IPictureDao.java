/*
 * Title:        清清网系统 2014年8月16日
 * Description:  图片的存储数据库访问接口
 * Copyright:    Copyright (c) 2014
 * Company:      清清网
 * @author       zhanghongbin
 * @version      2.0  2014年8月16日
 */
package com.work.dao.circle;

import java.util.List;
import java.util.Set;

import com.qing.common.exception.DaoException;
import com.work.domain.Picture;

/**
 * 图片的数据库访问接口
 * 
 * @author       zhanghongbin
 * @see          [相关类,可选,也可多条,对于重要的类或接口建议注释]
 * @since        清清网系统, 2014年8月16日
 */
public interface IPictureDao
{
	/**
	 * 插入图片数据
	 * @author       zhanghongbin
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        清清网系统, 2014年8月16日
	 * @param picture     要插入的图片
	 */
	
	public void savePicture(Picture picture) throws DaoException;
	
	/**
	 * 删除图片
	 * @author       zhanghongbin
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        清清网系统, 2014年8月16日
	 * @param picture     要删除的图片
	 */
	
	public void deletePicture(Set<Picture> picture) throws DaoException;
	
	/**
	 * 查找某条动态所属的所有图片
	 * @author       zhanghongbin
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        清清网系统, 2014年8月16日
	 * @param picture     要删除的图片
	 */
	public List<?> findByMessageId(String messageid) throws DaoException;;
}
