/*
 * Title:        清清网系统 2014年7月25日
 * Description:  [描述模块的功能、作用、使用方法和注意事项]
 * Copyright:    Copyright (c) 2014
 * Company:      清清网
 * @author       huangqingjian
 * @version      2.0  2014年7月25日
 */
package com.work.service.complaint.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.qing.common.exception.DaoException;
import com.qing.common.exception.ServiceException;
import com.qing.common.exception.handle.ExceptionHandle;
import com.qing.common.util.ErrorCode;
import com.qing.common.util.Page;
import com.qing.enums.InfoTypeEnum;
import com.work.dao.complaint.ICommentInfoDao;
import com.work.dao.job.IPartTimeInfoDao;
import com.work.dao.member.IMemberResumeDao;
import com.work.domain.CommentInfo;
import com.work.domain.MemberResume;
import com.work.domain.PartTimeInfo;
import com.work.service.complaint.ICommentInfoService;

/**
 * 评论业务逻辑层
 * (功能详细描述)
 * @author       huangqingjian/0050
 * @see          [相关类,可选,也可多条,对于重要的类或接口建议注释]
 * @since        清清网系统, 2014年7月25日
 */
@Service
public class CommentInfoServiceImpl implements ICommentInfoService {

	@Resource
	ICommentInfoDao dao;

	@Resource
	IPartTimeInfoDao partTimeInfoDao;
	@Resource
	IMemberResumeDao memberResumeDao;

	/**
	 *保存或修改评论
	 * (功能详细描述)
	 * @see com.work.service.communication.ICommentInfoService#saveOrUpdateCommentInfo(com.work.domain.CommentInfo)
	 * @author       huangqingjian/0050
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        清清网系统, 2014年8月24日
	 * @param commentInfo
	 * @throws ServiceException
	 */
	@Override
	public void saveOrUpdateCommentInfo(CommentInfo commentInfo) throws ServiceException {
		try {
			if (null == commentInfo) {
				return;
			}
			Long num = 0L;
			//如果是兼职信息被评论需要在兼职信息基础表评论数量字段+1
			if (InfoTypeEnum.PARTTIME.getIndex() == commentInfo.getInfoType()) {
				PartTimeInfo partTimeInfo = partTimeInfoDao.getPartTimeInfo(commentInfo.getInfoId());
				if (null != partTimeInfo) {
					num = partTimeInfo.getCommentNum() + 1;
					partTimeInfo.setCommentNum(num);
					partTimeInfoDao.saveOrUpdateInfo(partTimeInfo);
				}
			} else if (InfoTypeEnum.RESUME.getIndex() == commentInfo.getInfoType()) {
				MemberResume memberResume = memberResumeDao.getMemberResume(commentInfo.getInfoId());
				if (null != memberResume) {
					num = memberResume.getCommentNum() + 1;
					memberResume.setCommentNum(num);
					memberResumeDao.saveOrUpdateResume(memberResume);
				}
			}
			commentInfo.setFloorNum(dao.getMaxFloorNum(commentInfo.getInfoId()) + 1L);
			dao.saveOrUpdateCommentInfo(commentInfo);

		} catch (DaoException e) {
			ExceptionHandle.throwServiceException(e, ErrorCode.SERVICE_ERROR,
					"CommentInfoServiceImpl.saveOrUpdateCommentInfo");
		}

	}

	/**
	 * 批量删除评论
	 * (功能详细描述)
	 * @see com.work.service.communication.ICommentInfoService#deleteCommentInfos(java.lang.String[])
	 * @author       huangqingjian/0050
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        清清网系统, 2014年8月24日
	 * @param ids
	 * @throws ServiceException
	 */
	@Override
	public void deleteCommentInfos(String[] ids, String memberId) throws ServiceException {
		List<CommentInfo> commentInfos = new ArrayList<CommentInfo>();
		try {
			if (null != ids && ids.length > 0) {
				for (String id : ids) {
					CommentInfo commentInfo = dao.getCommentInfo(id);

					if (null != commentInfo) {
						if (null != commentInfo.getMember() && memberId.equals(commentInfo.getMember().getMemberId())) {
							Long num = 0L;
							//如果是兼职信息被评论删除需要在兼职信息基础表评论数量字段-1
							if (InfoTypeEnum.PARTTIME.getIndex() == commentInfo.getInfoType()) {

								PartTimeInfo partTimeInfo = partTimeInfoDao.getPartTimeInfo(commentInfo.getInfoId());
								if (null != partTimeInfo) {
									num = partTimeInfo.getCommentNum() - 1;
									partTimeInfo.setCommentNum(num);
									partTimeInfoDao.saveOrUpdateInfo(partTimeInfo);
								}
							} else if (InfoTypeEnum.RESUME.getIndex() == commentInfo.getInfoType()) {
								MemberResume memberResume = memberResumeDao.getMemberResume(commentInfo.getInfoId());
								if (null != memberResume) {
									num = memberResume.getCommentNum() - 1;
									memberResume.setCommentNum(num);
									memberResumeDao.saveOrUpdateResume(memberResume);
								}
							}
							commentInfos.add(commentInfo);
						}
					}
				}
			}
			if (!CollectionUtils.isEmpty(commentInfos)) {
				dao.deleteCommentInfos(commentInfos);
			}
		} catch (DaoException e) {
			ExceptionHandle.throwServiceException(e, ErrorCode.SERVICE_ERROR,
					"CommentInfoServiceImpl.deleteCommentInfos");
		}
	}

	/**
	 * 根据ID获取单个评论
	 * (功能详细描述)
	 * @see com.work.service.communication.ICommentInfoService#getCommentInfo(java.lang.String)
	 * @author       huangqingjian/0050
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        清清网系统, 2014年8月24日
	 * @param id
	 * @return
	 * @throws ServiceException
	 */
	@Override
	public CommentInfo getCommentInfo(String id) throws ServiceException {
		try {
			return dao.getCommentInfo(id);
		} catch (DaoException e) {
			ExceptionHandle.throwServiceException(e, ErrorCode.SERVICE_ERROR, "CommentInfoServiceImpl.getCommentInfo");
		}
		return null;
	}

	/**
	 * 分页查询评论
	 * 覆盖方法/实现方法(选择其一)
	 * (功能详细描述)
	 * @see com.work.service.communication.ICommentInfoService#queryCommentInfos(com.qing.common.util.Page, com.work.domain.CommentInfo)
	 * @author       huangqingjian/0050
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        清清网系统, 2014年8月24日
	 * @param page
	 * @param commentInfo
	 * @throws ServiceException
	 */
	@Override
	public void queryCommentInfos(Page<CommentInfo> page, CommentInfo commentInfo) throws ServiceException {
		try {
			dao.queryCommentInfo(page, commentInfo);
		} catch (DaoException e) {
			ExceptionHandle.throwServiceException(e, ErrorCode.SERVICE_ERROR,
					"CommentInfoServiceImpl.queryCommentInfos");
		}
	}

	/**
	 * 查询所有评论
	 * 覆盖方法/实现方法(选择其一)
	 * (功能详细描述)
	 * @see com.work.service.communication.ICommentInfoService#queryCommentInfo(com.work.domain.CommentInfo)
	 * @author       huangqingjian/0050
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        清清网系统, 2014年8月24日
	 * @param commentInfo
	 * @return
	 */
	@Override
	public List<CommentInfo> queryCommentInfo(CommentInfo commentInfo) throws ServiceException {
		try {
			return dao.queryCommentInfo(commentInfo);
		} catch (DaoException e) {
			ExceptionHandle
					.throwServiceException(e, ErrorCode.SERVICE_ERROR, "CommentInfoServiceImpl.queryCommentInfo");
		}
		return null;
	}

	@Override
	public int getSize(CommentInfo commentInfo) throws ServiceException {
		try {
			return dao.getSize(commentInfo);
		} catch (DaoException e) {
			ExceptionHandle.throwServiceException(e, ErrorCode.SERVICE_ERROR, "CritiqueInfoServiceImpl.getSize");
		}
		return 0;
	}
}
