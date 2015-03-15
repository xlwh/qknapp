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
import com.work.domain.CommentInfo;

/**
 * 评论数据访问层
 * (功能详细描述)
 * @author       huangqingjian/0050
 * @see          [相关类,可选,也可多条,对于重要的类或接口建议注释]
 * @since        清清网系统, 2014年7月25日
 */
public interface ICommentInfoDao {
	/**
	 * 新增或保存评论
	 * (一句话功能简述)
	 * (功能详细描述)
	 * @author       huangqingjian/0050
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        清清网系统, 2014年7月25日
	 * @param info
	 */
	public void saveOrUpdateCommentInfo(CommentInfo commentInfo) throws DaoException;

	/**
	 * 删除评论
	 * (一句话功能简述)
	 * (功能详细描述)
	 * @author       huangqingjian/0050
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        清清网系统, 2014年7月25日
	 * @param infos
	 */
	public void deleteCommentInfos(List<CommentInfo> commentInfos) throws DaoException;

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
	public CommentInfo getCommentInfo(String id) throws DaoException;

	/**
	 * 根据查询条件分页查询评论
	 * (一句话功能简述)
	 * (功能详细描述)
	 * @author       huangqingjian/0050
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        清清网系统, 2014年7月25日
	 * @param page
	 * @param info
	 */
	public void queryCommentInfo(Page<CommentInfo> page, CommentInfo commentInfo) throws DaoException;

	/**
	 * 不分页查询所有评论
	 * (一句话功能简述)
	 * (功能详细描述)
	 * @author       huangqingjian/0050
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        清清网系统, 2014年8月24日
	 * @param CommentInfo
	 * @return
	 * @throws DaoException
	 */
	public List<CommentInfo> queryCommentInfo(CommentInfo commentInfo) throws DaoException;

	/**
	 * 获取最大的楼层号
	 * (一句话功能简述)
	 * (功能详细描述)
	 * @author       huangqingjian/0050
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        清清网系统, 2014年9月24日
	 * @return
	 * @throws DaoException 
	 */
	public int getMaxFloorNum(String infoId) throws DaoException;

	/**
	 * 获取总数
	 * (一句话功能简述)
	 * (功能详细描述)
	 * @author       huangqingjian/0050
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        清清网系统, 2014年10月18日
	 * @param commentInfo
	 * @return
	 * @throws DaoException
	 */
	int getSize(CommentInfo commentInfo) throws DaoException;

}
