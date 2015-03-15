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
import com.work.dao.complaint.IComplaintInfoDao;
import com.work.dao.job.IPartTimeInfoDao;
import com.work.dao.member.IMemberResumeDao;
import com.work.domain.ComplaintInfo;
import com.work.domain.MemberResume;
import com.work.domain.PartTimeInfo;
import com.work.service.complaint.IComplaintInfoService;

/**
 * 举报业务逻辑层
 * (功能详细描述)
 * @author       huangqingjian/0050
 * @see          [相关类,可选,也可多条,对于重要的类或接口建议注释]
 * @since        清清网系统, 2014年7月25日
 */
@Service
public class ComplaintInfoServiceImpl implements IComplaintInfoService {

	@Resource
	IComplaintInfoDao dao;

	@Resource
	IPartTimeInfoDao partTimeInfoDao;

	@Resource
	IMemberResumeDao memberResumeDao;

	/**
	 *保存或修改举报
	 * (功能详细描述)
	 * @see com.work.service.communication.IComplaintInfoService#saveOrUpdateComplaintInfo(com.work.domain.ComplaintInfo)
	 * @author       huangqingjian/0050
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        清清网系统, 2014年8月24日
	 * @param complaintInfo
	 * @throws ServiceException
	 */
	@Override
	public void saveOrUpdateComplaintInfo(ComplaintInfo complaintInfo) throws ServiceException {
		try {
			if (null == complaintInfo) {
				return;
			}
			Long num = 0L;
			//如果是兼职信息被举报需要在兼职信息基础表举报数量字段+1
			if (InfoTypeEnum.PARTTIME.getIndex() == complaintInfo.getInfoType()) {
				PartTimeInfo partTimeInfo = partTimeInfoDao.getPartTimeInfo(complaintInfo.getInfoId());
				if (null != partTimeInfo) {
					num = partTimeInfo.getReportNum() + 1;
					partTimeInfo.setReportNum(num);
					partTimeInfoDao.saveOrUpdateInfo(partTimeInfo);
				}
			} else if (InfoTypeEnum.RESUME.getIndex() == complaintInfo.getInfoType()) {
				MemberResume memberResume = memberResumeDao.getMemberResume(complaintInfo.getInfoId());
				if (null != memberResume) {
					num = memberResume.getReportNum() + 1;
					memberResume.setReportNum(num);
					memberResumeDao.saveOrUpdateResume(memberResume);
				}
			}
			dao.saveOrUpdateComplaintInfo(complaintInfo);

		} catch (DaoException e) {
			ExceptionHandle.throwServiceException(e, ErrorCode.SERVICE_ERROR,
					"ComplaintInfoServiceImpl.saveOrUpdateComplaintInfo");
		}

	}

	/**
	 * 批量删除举报
	 * (功能详细描述)
	 * @see com.work.service.communication.IComplaintInfoService#deleteComplaintInfos(java.lang.String[])
	 * @author       huangqingjian/0050
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        清清网系统, 2014年8月24日
	 * @param ids
	 * @throws ServiceException
	 */
	@Override
	public void deleteComplaintInfos(String[] ids, String memberId) throws ServiceException {
		List<ComplaintInfo> complaintInfos = new ArrayList<ComplaintInfo>();
		try {
			if (null != ids && ids.length > 0) {
				for (String id : ids) {
					ComplaintInfo complaintInfo = dao.getComplaintInfo(id);

					if (null != complaintInfo) {
						if (memberId.equals(complaintInfo.getMemberId())) {
							setComplaint(complaintInfos, complaintInfo);
						}
					}

				}
			}
			if (!CollectionUtils.isEmpty(complaintInfos)) {
				dao.deleteComplaintInfos(complaintInfos);
			}
		} catch (DaoException e) {
			ExceptionHandle.throwServiceException(e, ErrorCode.SERVICE_ERROR,
					"ComplaintInfoServiceImpl.deleteComplaintInfos");
		}
	}

	/**
	 * 设置举报数量
	 * (一句话功能简述)
	 * (功能详细描述)
	 * @author       huangqingjian/0050
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        清清网系统, 2014年10月3日
	 * @param complaintInfos
	 * @param complaintInfo
	 * @throws DaoException
	 */
	private void setComplaint(List<ComplaintInfo> complaintInfos, ComplaintInfo complaintInfo) throws DaoException {
		Long num = 0L;
		//如果是兼职信息被举报删除需要在兼职信息基础表举报数量字段-1
		if (InfoTypeEnum.PARTTIME.getIndex() == complaintInfo.getInfoType()) {

			PartTimeInfo partTimeInfo = partTimeInfoDao.getPartTimeInfo(complaintInfo.getInfoId());
			if (null != partTimeInfo) {
				num = partTimeInfo.getReportNum() - 1;
				partTimeInfo.setReportNum(num);
				partTimeInfoDao.saveOrUpdateInfo(partTimeInfo);
			}
		} else if (InfoTypeEnum.RESUME.getIndex() == complaintInfo.getInfoType()) {
			MemberResume memberResume = memberResumeDao.getMemberResume(complaintInfo.getInfoId());
			if (null != memberResume) {
				num = memberResume.getReportNum() - 1;
				memberResume.setReportNum(num);
				memberResumeDao.saveOrUpdateResume(memberResume);
			}
		}
		complaintInfos.add(complaintInfo);
	}

	/**
	 * 后台删除举报信息
	 * (一句话功能简述)
	 * (功能详细描述)
	 * @author       huangqingjian/0050
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        清清网系统, 2014年10月3日
	 * @param ids
	 * @throws ServiceException
	 */
	@Override
	public void deleteComplaintInfos(String[] ids) throws ServiceException {
		List<ComplaintInfo> complaintInfos = new ArrayList<ComplaintInfo>();
		try {
			if (null != ids && ids.length > 0) {
				for (String id : ids) {
					ComplaintInfo complaintInfo = dao.getComplaintInfo(id);

					if (null != complaintInfo) {
						setComplaint(complaintInfos, complaintInfo);
					}

				}
			}
			if (!CollectionUtils.isEmpty(complaintInfos)) {
				dao.deleteComplaintInfos(complaintInfos);
			}
		} catch (DaoException e) {
			ExceptionHandle.throwServiceException(e, ErrorCode.SERVICE_ERROR,
					"ComplaintInfoServiceImpl.deleteComplaintInfos");
		}
	}

	/**
	 * 根据ID获取单个举报
	 * (功能详细描述)
	 * @see com.work.service.communication.IComplaintInfoService#getComplaintInfo(java.lang.String)
	 * @author       huangqingjian/0050
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        清清网系统, 2014年8月24日
	 * @param id
	 * @return
	 * @throws ServiceException
	 */
	@Override
	public ComplaintInfo getComplaintInfo(String id) throws ServiceException {
		try {
			return dao.getComplaintInfo(id);
		} catch (DaoException e) {
			ExceptionHandle.throwServiceException(e, ErrorCode.SERVICE_ERROR,
					"ComplaintInfoServiceImpl.getComplaintInfo");
		}
		return null;
	}

	/**
	 * 分页查询举报
	 * 覆盖方法/实现方法(选择其一)
	 * (功能详细描述)
	 * @see com.work.service.communication.IComplaintInfoService#queryComplaintInfos(com.qing.common.util.Page, com.work.domain.ComplaintInfo)
	 * @author       huangqingjian/0050
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        清清网系统, 2014年8月24日
	 * @param page
	 * @param complaintInfo
	 * @throws ServiceException
	 */
	@Override
	public void queryComplaintInfos(Page<ComplaintInfo> page, ComplaintInfo complaintInfo) throws ServiceException {
		try {
			dao.queryComplaintInfo(page, complaintInfo);
		} catch (DaoException e) {
			ExceptionHandle.throwServiceException(e, ErrorCode.SERVICE_ERROR,
					"ComplaintInfoServiceImpl.queryComplaintInfos");
		}
	}

	/**
	 * 查询所有举报
	 * 覆盖方法/实现方法(选择其一)
	 * (功能详细描述)
	 * @see com.work.service.communication.IComplaintInfoService#queryComplaintInfo(com.work.domain.ComplaintInfo)
	 * @author       huangqingjian/0050
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        清清网系统, 2014年8月24日
	 * @param complaintInfo
	 * @return
	 */
	@Override
	public List<ComplaintInfo> queryComplaintInfo(ComplaintInfo complaintInfo) throws ServiceException {
		try {
			return dao.queryComplaintInfo(complaintInfo);
		} catch (DaoException e) {
			ExceptionHandle.throwServiceException(e, ErrorCode.SERVICE_ERROR,
					"ComplaintInfoServiceImpl.queryComplaintInfo");
		}
		return null;
	}

	@Override
	public int getSize(ComplaintInfo complaintInfo) throws ServiceException {
		try {
			return dao.getSize(complaintInfo);
		} catch (DaoException e) {
			ExceptionHandle.throwServiceException(e, ErrorCode.SERVICE_ERROR, "CritiqueInfoServiceImpl.getSize");
		}
		return 0;
	}
}
