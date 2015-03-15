/*
 * Title:        清清网系统 2014年7月25日
 * Description:  [描述模块的功能、作用、使用方法和注意事项]
 * Copyright:    Copyright (c) 2014
 * Company:      清清网
 * @author       huangqingjian
 * @version      2.0  2014年7月25日
 */
package com.work.dao.complaint.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.qing.common.base.dao.HibernateDao;
import com.qing.common.exception.DaoException;
import com.qing.common.util.Page;
import com.qing.common.util.StringUtil;
import com.work.dao.complaint.ICommentInfoDao;
import com.work.domain.CommentInfo;

/**
 * 评论数据访问
 * (功能详细描述)
 * @author       huangqingjian/0050
 * @see          [相关类,可选,也可多条,对于重要的类或接口建议注释]
 * @since        清清网系统, 2014年7月25日
 */
@Repository
public class CommentInfoDaoImpl implements ICommentInfoDao {

	@Resource
	HibernateDao<CommentInfo, String> hibernateDao;

	/**
	 * 新增或保存评论
	 * (一句话功能简述)
	 * (功能详细描述)
	 * @author       huangqingjian/0050
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        清清网系统, 2014年7月25日
	 * @param info
	 */
	public void saveOrUpdateCommentInfo(CommentInfo CommentInfo) throws DaoException {

		hibernateDao.saveOrUpdate(CommentInfo);

	}

	/**
	 * 删除评论
	 * (一句话功能简述)
	 * (功能详细描述)
	 * @author       huangqingjian/0050
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        清清网系统, 2014年7月25日
	 * @param infos
	 */
	public void deleteCommentInfos(List<CommentInfo> CommentInfos) throws DaoException {
		hibernateDao.removeAll(CommentInfos);
	}

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
	public CommentInfo getCommentInfo(String id) throws DaoException {
		return hibernateDao.findById(CommentInfo.class, id);
	}

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
	public void queryCommentInfo(Page<CommentInfo> page, CommentInfo CommentInfo) throws DaoException {
		StringBuilder builder = new StringBuilder();
		List<Object> parm = new ArrayList<Object>();
		builder.append(" from CommentInfo c where 1=1 ");
		builder.append(generateHQLCondition(CommentInfo, parm));
		builder.append(" order by floorNum,createDate desc");
		setPageSize(page, CommentInfo);
		hibernateDao.findByPage(page, builder.toString(), parm.toArray());
	}

	/**
	 * 构建分页查询条件
	 * (一句话功能简述)
	 * (功能详细描述)
	 * @author       huangqingjian/0050
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        清清网系统, 2014年7月25日
	 * @param info
	 * @param parm
	 * @return
	 */
	private StringBuilder generateHQLCondition(CommentInfo commentInfo, List<Object> parm) {
		StringBuilder builder = new StringBuilder();
		if (null == commentInfo) {
			return builder;
		}
		if (null != commentInfo.getInfoType()) {
			builder.append(" and c.infoType = ?");
			parm.add(commentInfo.getInfoType());
		}
		if (null != commentInfo.getProStatus()) {
			builder.append(" and c.proStatus = ?");
			parm.add(commentInfo.getProStatus());
		}
		if (null != commentInfo.getInfoId()) {
			builder.append(" and c.infoId = ?");
			parm.add(commentInfo.getInfoId());
		}
		if (null != commentInfo.getMember() && StringUtil.isValidStr(commentInfo.getMember().getMemberId())) {
			builder.append(" and c.member.memberId = ?");
			parm.add(commentInfo.getMember().getMemberId());
		}

		return builder;

	}

	/**
	 * 设置分页总数
	 * (一句话功能简述)
	 * (功能详细描述)
	 * @author       huangqingjian/0050
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        清清网系统, 2014年7月25日
	 * @param page
	 * @param info
	 * @throws DaoException
	 */
	private void setPageSize(Page<CommentInfo> page, CommentInfo commentInfo) throws DaoException {
		int size = getSize(commentInfo);
		page.setTotalCount(size);
	}

	/**
	 * (一句话功能简述)
	 * (功能详细描述)
	 * @author       huangqingjian/0050
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        清清网系统, 2014年10月18日
	 * @param commentInfo
	 * @return
	 * @throws DaoException
	 */
	@Override
	public int getSize(CommentInfo commentInfo) throws DaoException {
		StringBuilder builder = new StringBuilder();
		List<Object> parm = new ArrayList<Object>();
		builder.append("select count(commentId)  from CommentInfo c where 1=1 ");
		builder.append(generateHQLCondition(commentInfo, parm));
		int size = hibernateDao.findNumByHQL(builder.toString(), parm.toArray());
		return size;
	}

	/**
	 * 不分页查询所有评论
	 * (一句话功能简述)
	 * (功能详细描述)
	 * @author       huangqingjian/0050
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        清清网系统, 2014年8月24日
	 * @param commentInfo
	 * @return
	 * @throws DaoException
	 */
	public List<CommentInfo> queryCommentInfo(CommentInfo commentInfo) throws DaoException {
		return hibernateDao.findByExample(commentInfo);
	}

	/**
	 * 获取当前最大楼层
	 * (一句话功能简述)
	 * (功能详细描述)
	 * @author       huangqingjian/0050
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        清清网系统, 2014年9月24日
	 * @return
	 * @throws DaoException 
	 */
	@Override
	public int getMaxFloorNum(String infoId) throws DaoException {
		StringBuilder hql = new StringBuilder();
		hql.append("select max(floorNum) from CommentInfo where infoId=?");

		return hibernateDao.findNumByHQL(hql.toString(), new Object[] { infoId });
	}

}
