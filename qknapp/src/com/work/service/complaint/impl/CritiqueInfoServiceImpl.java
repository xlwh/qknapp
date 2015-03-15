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
import com.qing.enums.CritiqueEnum;
import com.qing.enums.InfoTypeEnum;
import com.work.dao.complaint.ICritiqueInfoDao;
import com.work.dao.job.IPartTimeInfoDao;
import com.work.dao.member.IMemberResumeDao;
import com.work.domain.CritiqueInfo;
import com.work.domain.MemberResume;
import com.work.domain.PartTimeInfo;
import com.work.service.complaint.ICritiqueInfoService;

/**
 * 点评业务逻辑层
 * (功能详细描述)
 * @author       huangqingjian/0050
 * @see          [相关类,可选,也可多条,对于重要的类或接口建议注释]
 * @since        清清网系统, 2014年7月25日
 */
@Service
public class CritiqueInfoServiceImpl implements ICritiqueInfoService {

	@Resource
	ICritiqueInfoDao dao;

	@Resource
	IPartTimeInfoDao partTimeInfoDao;

	@Resource
	IMemberResumeDao memberResumeDao;

	/**
	 *保存或修改点评
	 * (功能详细描述)
	 * @see com.work.service.communication.ICritiqueInfoService#saveOrUpdateCritiqueInfo(com.work.domain.CritiqueInfo)
	 * @author       huangqingjian/0050
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        清清网系统, 2014年8月24日
	 * @param critiqueInfo
	 * @throws ServiceException
	 */
	@Override
	public void saveOrUpdateCritiqueInfo(CritiqueInfo critiqueInfo) throws ServiceException {
		try {
			if (null == critiqueInfo) {
				return;
			}
			if (null != critiqueInfo.getInfoType()) {
				//给对应兼职的数量统计增加1
				if (InfoTypeEnum.PARTTIME.getIndex() == critiqueInfo.getInfoType()) {
					PartTimeInfo partTimeInfo = partTimeInfoDao.getPartTimeInfo(critiqueInfo.getInfoId());
					setPartTimeInfoUp(critiqueInfo, partTimeInfo);
					partTimeInfoDao.saveOrUpdateInfo(partTimeInfo);
				} else if (InfoTypeEnum.RESUME.getIndex() == critiqueInfo.getInfoType()) {
					//给对应简历的数量统计增加1
					MemberResume memberResume = memberResumeDao.getMemberResume(critiqueInfo.getInfoId());
					setMemberResumeUp(critiqueInfo, memberResume);
					memberResumeDao.saveOrUpdateResume(memberResume);
				}
			}
			dao.saveOrUpdateCritiqueInfo(critiqueInfo);

		} catch (DaoException e) {
			ExceptionHandle.throwServiceException(e, ErrorCode.SERVICE_ERROR,
					"CritiqueInfoServiceImpl.saveOrUpdateCritiqueInfo");
		}

	}

	/**
	 * 设置兼职信息字段增加
	 * (一句话功能简述)
	 * (功能详细描述)
	 * @author       huangqingjian/0050
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        清清网系统, 2014年9月27日
	 * @param critiqueInfo
	 * @param partTimeInfo
	 */
	private void setPartTimeInfoUp(CritiqueInfo critiqueInfo, PartTimeInfo partTimeInfo) {
		Long num;
		if (CritiqueEnum.BROWSE.getIndex() == critiqueInfo.getType()) {
			num = partTimeInfo.getBrowseNum() + 1;
			partTimeInfo.setBrowseNum(num);
		}
		if (CritiqueEnum.NOTINTERESTED.getIndex() == critiqueInfo.getType()) {
			num = partTimeInfo.getNotInterestedNum() + 1;
			partTimeInfo.setNotInterestedNum(num);
		}
		if (CritiqueEnum.CONSULTATION.getIndex() == critiqueInfo.getType()) {
			num = partTimeInfo.getConsultationNum() + 1;
			partTimeInfo.setConsultationNum(num);
		}
		if (CritiqueEnum.PRAISE.getIndex() == critiqueInfo.getType()) {
			num = partTimeInfo.getPraiseNum() + 1;
			partTimeInfo.setPraiseNum(num);
		}
	}

	/**
	 * 增加到简历信息对象
	 * (一句话功能简述)
	 * (功能详细描述)
	 * @author       huangqingjian/0050
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        清清网系统, 2014年10月1日
	 * @param critiqueInfo
	 * @param memberResume
	 */
	private void setMemberResumeUp(CritiqueInfo critiqueInfo, MemberResume memberResume) {
		Long num;
		if (CritiqueEnum.BROWSE.getIndex() == critiqueInfo.getType()) {
			num = memberResume.getBrowseNum() + 1;
			memberResume.setBrowseNum(num);
		}
		if (CritiqueEnum.NOTINTERESTED.getIndex() == critiqueInfo.getType()) {
			num = memberResume.getNotInterestedNum() + 1;
			memberResume.setNotInterestedNum(num);
		}
		if (CritiqueEnum.CONSULTATION.getIndex() == critiqueInfo.getType()) {
			num = memberResume.getConsultationNum() + 1;
			memberResume.setConsultationNum(num);
		}
		if (CritiqueEnum.PRAISE.getIndex() == critiqueInfo.getType()) {
			num = memberResume.getPraiseNum() + 1;
			memberResume.setPraiseNum(num);
		}
	}

	/**
	 * 设置兼职的数量，减少
	 * (一句话功能简述)
	 * (功能详细描述)
	 * @author       huangqingjian/0050
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        清清网系统, 2014年9月27日
	 * @param critiqueInfo
	 * @param partTimeInfo
	 */
	private void setPartTimeInfoDown(CritiqueInfo critiqueInfo, PartTimeInfo partTimeInfo) {
		Long num;
		if (CritiqueEnum.BROWSE.getIndex() == critiqueInfo.getType()) {
			num = partTimeInfo.getBrowseNum() - 1;
			partTimeInfo.setBrowseNum(num);
		}
		if (CritiqueEnum.NOTINTERESTED.getIndex() == critiqueInfo.getType()) {
			num = partTimeInfo.getNotInterestedNum() - 1;
			partTimeInfo.setNotInterestedNum(num);
		}
		if (CritiqueEnum.CONSULTATION.getIndex() == critiqueInfo.getType()) {
			num = partTimeInfo.getConsultationNum() - 1;
			partTimeInfo.setConsultationNum(num);
		}
		if (CritiqueEnum.PRAISE.getIndex() == critiqueInfo.getType()) {
			num = partTimeInfo.getPraiseNum() - 1;
			partTimeInfo.setPraiseNum(num);
		}
	}

	/**
	 * 设置简历统计减少数量
	 * (一句话功能简述)
	 * (功能详细描述)
	 * @author       huangqingjian/0050
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        清清网系统, 2014年10月1日
	 * @param critiqueInfo
	 * @param memberResume
	 */
	private void setMemberResumeDown(CritiqueInfo critiqueInfo, MemberResume memberResume) {
		Long num;
		if (CritiqueEnum.BROWSE.getIndex() == critiqueInfo.getType()) {
			num = memberResume.getBrowseNum() - 1;
			memberResume.setBrowseNum(num);
		}
		if (CritiqueEnum.NOTINTERESTED.getIndex() == critiqueInfo.getType()) {
			num = memberResume.getNotInterestedNum() - 1;
			memberResume.setNotInterestedNum(num);
		}
		if (CritiqueEnum.CONSULTATION.getIndex() == critiqueInfo.getType()) {
			num = memberResume.getConsultationNum() - 1;
			memberResume.setConsultationNum(num);
		}
		if (CritiqueEnum.PRAISE.getIndex() == critiqueInfo.getType()) {
			num = memberResume.getPraiseNum() - 1;
			memberResume.setPraiseNum(num);
		}
	}

	/**
	 * 批量删除点评
	 * (功能详细描述)
	 * @see com.work.service.communication.ICritiqueInfoService#deleteCritiqueInfos(java.lang.String[])
	 * @author       huangqingjian/0050
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        清清网系统, 2014年8月24日
	 * @param ids
	 * @throws ServiceException
	 */
	@Override
	public void deleteCritiqueInfos(String[] ids, String memberId) throws ServiceException {
		List<CritiqueInfo> critiqueInfos = new ArrayList<CritiqueInfo>();
		try {
			if (null != ids && ids.length > 0) {
				for (String id : ids) {
					CritiqueInfo critiqueInfo = dao.getCritiqueInfo(id);
					if (null != critiqueInfo.getMemberId() && memberId.equals(critiqueInfo.getMemberId())) {
						//给对应兼职的数量统计减少1
						if (InfoTypeEnum.PARTTIME.getIndex() == critiqueInfo.getInfoType()) {
							PartTimeInfo partTimeInfo = partTimeInfoDao.getPartTimeInfo(critiqueInfo.getInfoId());
							setPartTimeInfoDown(critiqueInfo, partTimeInfo);
							partTimeInfoDao.saveOrUpdateInfo(partTimeInfo);
						} else if (InfoTypeEnum.RESUME.getIndex() == critiqueInfo.getInfoType()) {
							//给对应简历的数量统计增加1
							MemberResume memberResume = memberResumeDao.getMemberResume(critiqueInfo.getInfoId());
							setMemberResumeDown(critiqueInfo, memberResume);
							memberResumeDao.saveOrUpdateResume(memberResume);
						}
						if (null != critiqueInfo) {
							critiqueInfos.add(critiqueInfo);
						}

					}
				}
			}
			if (!CollectionUtils.isEmpty(critiqueInfos)) {
				dao.deleteCritiqueInfos(critiqueInfos);
			}
		} catch (DaoException e) {
			ExceptionHandle.throwServiceException(e, ErrorCode.SERVICE_ERROR,
					"CritiqueInfoServiceImpl.deleteCritiqueInfos");
		}
	}

	/**
	 * 根据ID获取单个点评
	 * (功能详细描述)
	 * @see com.work.service.communication.ICritiqueInfoService#getCritiqueInfo(java.lang.String)
	 * @author       huangqingjian/0050
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        清清网系统, 2014年8月24日
	 * @param id
	 * @return
	 * @throws ServiceException
	 */
	@Override
	public CritiqueInfo getCritiqueInfo(String id) throws ServiceException {
		try {
			return dao.getCritiqueInfo(id);
		} catch (DaoException e) {
			ExceptionHandle
					.throwServiceException(e, ErrorCode.SERVICE_ERROR, "CritiqueInfoServiceImpl.getCritiqueInfo");
		}
		return null;
	}

	/**
	 * 分页查询点评
	 * 覆盖方法/实现方法(选择其一)
	 * (功能详细描述)
	 * @see com.work.service.communication.ICritiqueInfoService#queryCritiqueInfos(com.qing.common.util.Page, com.work.domain.CritiqueInfo)
	 * @author       huangqingjian/0050
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        清清网系统, 2014年8月24日
	 * @param page
	 * @param critiqueInfo
	 * @throws ServiceException
	 */
	@Override
	public void queryCritiqueInfos(Page<CritiqueInfo> page, CritiqueInfo critiqueInfo) throws ServiceException {
		try {
			dao.queryCritiqueInfo(page, critiqueInfo);
		} catch (DaoException e) {
			ExceptionHandle.throwServiceException(e, ErrorCode.SERVICE_ERROR,
					"CritiqueInfoServiceImpl.queryCritiqueInfos");
		}
	}

	/**
	 * 查询所有点评
	 * 覆盖方法/实现方法(选择其一)
	 * (功能详细描述)
	 * @see com.work.service.communication.ICritiqueInfoService#queryCritiqueInfo(com.work.domain.CritiqueInfo)
	 * @author       huangqingjian/0050
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        清清网系统, 2014年8月24日
	 * @param critiqueInfo
	 * @return
	 */
	@Override
	public List<CritiqueInfo> queryCritiqueInfo(CritiqueInfo critiqueInfo) throws ServiceException {
		try {
			return dao.queryCritiqueInfo(critiqueInfo);
		} catch (DaoException e) {
			ExceptionHandle.throwServiceException(e, ErrorCode.SERVICE_ERROR,
					"CritiqueInfoServiceImpl.queryCritiqueInfo");
		}
		return null;
	}

	/**
	 * 判断点赞或者无兴趣否已经存在
	 * 覆盖方法/实现方法(选择其一)
	 * (功能详细描述)
	 * @see com.work.service.complaint.ICritiqueInfoService#getExistInfo(com.work.domain.CritiqueInfo)
	 * @author       huangqingjian/0050
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        清清网系统, 2014年9月27日
	 * @param critiqueInfo
	 * @return
	 * @throws ServiceException 
	 * @throws DaoException
	 */
	@Override
	public boolean getExistInfo(CritiqueInfo critiqueInfo) throws ServiceException {
		try {
			if (dao.getExistInfo(critiqueInfo) > 0) {
				return true;
			}
		} catch (DaoException e) {
			ExceptionHandle.throwServiceException(e, ErrorCode.SERVICE_ERROR, "CritiqueInfoServiceImpl.getExistInfo");
		}
		return false;
	}

	/**
	 * 获取是否已经点过卖男人
	 * 覆盖方法/实现方法(选择其一)
	 * (功能详细描述)
	 * @see com.work.service.complaint.ICritiqueInfoService#getExistSellMenInfo(com.work.domain.CritiqueInfo)
	 * @author       huangqingjian/0050
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        清清网系统, 2014年10月6日
	 * @param critiqueInfo
	 * @return
	 * @throws ServiceException
	 */
	@Override
	public boolean getExistSellMenInfo(CritiqueInfo critiqueInfo) throws ServiceException {
		try {
			if (dao.getExistSellMenInfo(critiqueInfo) > 0) {
				return true;
			}
		} catch (DaoException e) {
			ExceptionHandle.throwServiceException(e, ErrorCode.SERVICE_ERROR, "CritiqueInfoServiceImpl.getExistInfo");
		}
		return false;
	}

	/**
	 * 覆盖方法/实现方法(选择其一)
	 * (功能详细描述)
	 * @see com.work.service.complaint.ICritiqueInfoService#getSize(com.work.domain.CritiqueInfo)
	 * @author       huangqingjian/0050
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        清清网系统, 2014年10月6日
	 * @param critiqueInfo
	 * @return
	 * @throws ServiceException
	 */
	@Override
	public int getSize(CritiqueInfo critiqueInfo) throws ServiceException {
		try {
			return dao.getSize(critiqueInfo);
		} catch (DaoException e) {
			ExceptionHandle.throwServiceException(e, ErrorCode.SERVICE_ERROR, "CritiqueInfoServiceImpl.getSize");
		}
		return 0;
	}

}
