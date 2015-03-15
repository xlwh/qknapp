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
import com.work.domain.CommentInfo;

/**
 * 评论业务逻辑层
 * (功能详细描述)
 * @author       huangqingjian/0050
 * @see          [相关类,可选,也可多条,对于重要的类或接口建议注释]
 * @since        清清网系统, 2014年7月25日
 */
public interface ICommentInfoService {
	/**
	 * 新增或保存评论
	 * (一句话功能简述)
	 * (功能详细描述)
	 * @author       huangqingjian/0050
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        清清网系统, 2014年7月25日
	 * @param commentInfo
	 */
	public void saveOrUpdateCommentInfo(CommentInfo commentInfo) throws ServiceException;

	/**
	 * 删除评论
	 * (一句话功能简述)
	 * (功能详细描述)
	 * @author       huangqingjian/0050
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        清清网系统, 2014年7月25日
	 * @param ids
	 */
	public void deleteCommentInfos(String[] ids, String memberId) throws ServiceException;

	/**
	 * 根据ID获取评论详情
	 * (一句话功能简述)
	 * (功能详细描述)
	 * @author       huangqingjian/0050
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        清清网系统, 2014年7月25日
	 * @param id
	 * @return
	 */
	public CommentInfo getCommentInfo(String id) throws ServiceException;

	/**
	 * 根据查询条件分页查询评论
	 * (一句话功能简述)
	 * (功能详细描述)
	 * @author       huangqingjian/0050
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        清清网系统, 2014年7月25日
	 * @param page
	 * @param commentInfo
	 */
	public void queryCommentInfos(Page<CommentInfo> page, CommentInfo commentInfo) throws ServiceException;

	/**
	 * 根据查询条件查询所有评论
	 * (一句话功能简述)
	 * (功能详细描述)
	 * @author       huangqingjian/0050
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        清清网系统, 2014年8月24日
	 * @param commentInfo
	 * @return
	 * @throws ServiceException
	 */
	public List<CommentInfo> queryCommentInfo(CommentInfo commentInfo) throws ServiceException;

	/**
	 * (一句话功能简述)
	 * (功能详细描述)
	 * @author       huangqingjian/0050
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        清清网系统, 2014年10月18日
	 * @param commentInfo
	 * @return
	 * @throws ServiceException
	 */
	int getSize(CommentInfo commentInfo) throws ServiceException;

}
