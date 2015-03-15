/*
 * Title:        清清网系统 2014年8月17日
 * Description:  实践圈评论和点赞数据库操作接口
 * Copyright:    Copyright (c) 2014
 * Company:      清清网
 * @author       zhanghongbin
 * @version      2.0  2014年8月17日
 */
package com.work.dao.circle;

import java.util.List;

import com.qing.common.exception.DaoException;
import com.qing.common.util.Page;
import com.work.domain.Discuss;

/**
 * 实践圈点赞和评论数据库访问接口
 * (功能详细描述)
 * @author       zhanghongbin
 * @see          [相关类,可选,也可多条,对于重要的类或接口建议注释]
 * @since        清清网系统, 2014年8月17日
 */
public interface IDiscussDao {
	
	/**
	 * 数据记录保存
	 * (一句话功能简述)
	 * (功能详细描述)
	 * @author       zhanghongbin
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        清清网系统, 2014年8月10日
	 * @param discuss
	 * @throws DaoException
	 */
	public void saveDiscuss(Discuss discuss) throws DaoException;
	
	/**
	 * 清除记录评论
	 * (一句话功能简述)
	 * (功能详细描述)
	 * @author       zhanghongbin
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        清清网系统, 2014年8月10日
	 * @param id
	 * @throws DaoException
	 */
	
	public void deleteDiscuss(Discuss discuss) throws DaoException;
	
	/**
	 * 取得属于某条动态的所有评论
	 * (一句话功能简述)
	 * (功能详细描述)
	 * @author       zhanghongbin
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        清清网系统, 2014年8月10日
	 * @param mid
	 * @throws DaoException
	 */
	
	public void findDiscussByMessage(Page<Discuss> page,String mid,Discuss discuss) throws DaoException;
	
	/**
	 * 删除某条动态时，删除所有相关图片
	 * (一句话功能简述)
	 * (功能详细描述)
	 * @author       zhanghongbin
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        清清网系统, 2014年8月10日
	 * @param mid
	 * @throws DaoException
	 */
	public void deleteAll(List<Discuss> lst)  throws DaoException;
	
}
