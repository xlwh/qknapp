/*
 * Title:        清清网系统 2014年8月16日
 * Description:  [描述模块的功能、作用、使用方法和注意事项]
 * Copyright:    Copyright (c) 2014
 * Company:      清清网
 * @author       Sandy Wong
 * @version      2.0  2014年8月16日
 */
package com.work.service.member.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.qing.common.exception.DaoException;
import com.qing.common.exception.ServiceException;
import com.qing.common.exception.handle.ExceptionHandle;
import com.qing.common.util.BeanUtils;
import com.qing.common.util.ErrorCode;
import com.qing.common.util.Page;
import com.qing.common.util.StringUtil;
import com.work.dao.member.IMemberResumeDao;
import com.work.domain.MemberInfo;
import com.work.domain.MemberResume;
import com.work.domain.PartTimeType;
import com.work.service.job.IPartTimeTypeService;
import com.work.service.member.IMemberInfoService;
import com.work.service.member.IMemberResumeService;

/**
 * (一句话功能简述)
 * (功能详细描述)
 * @author       Sandy Wong
 * @see          [相关类,可选,也可多条,对于重要的类或接口建议注释]
 * @since        清清网系统, 2014年8月16日
 */
@Service
@Repository
public class MemberResumeServiceImpl implements IMemberResumeService {
	@Resource
	IMemberResumeDao dao;

	@Resource
	IMemberInfoService memberInfoService;

	@Resource
	IPartTimeTypeService partTimeTypeService;

	/**
	 * 覆盖方法/实现方法(选择其一)
	 * (功能详细描述)
	 * @see com.work.service.member.IMemberResumeService#saveOrUpdateResume(com.work.domain.MemberResume)
	 * @author       Sandy Wong
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        清清网系统, 2014年8月16日
	 * @param resume
	 * @throws ServiceException
	 */
	@Override
	public void saveOrUpdateResume(MemberResume resume) throws ServiceException {
		if (null == resume) {
			return;
		}
		try {
			resume.setUpdateTime(new Date());
			dao.saveOrUpdateResume(resume);
		} catch (DaoException e) {
			ExceptionHandle.throwServiceException(e, ErrorCode.SERVICE_ERROR,
					"MemberResumeServiceImpl.saveOrUpdateResume");
		}
	}

	/**
	 * 覆盖方法/实现方法(选择其一)
	 * (功能详细描述)
	 * @see com.work.service.member.IMemberResumeService#deleteResume(java.util.List)
	 * @author       Sandy Wong
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        清清网系统, 2014年8月16日
	 * @param resume
	 * @throws ServiceException
	 */
	@Override
	public void deleteResumes(String[] ids) throws ServiceException {
		List<MemberResume> memberResumes = new ArrayList<MemberResume>();
		MemberResume resume;
		try {
			for (int i = 0; i < ids.length; i++) {
				resume = dao.getMemberResume(ids[i]);
				resume.setMemberInfo(null);
				memberResumes.add(resume);
			}
			dao.deleteResume(memberResumes);
		} catch (DaoException e) {
			ExceptionHandle.throwServiceException(e, ErrorCode.SERVICE_ERROR, "MemberResumeServiceImpl.deleteResumes");
		}

	}

	/**
	 * 覆盖方法/实现方法(选择其一)
	 * (功能详细描述)
	 * @see com.work.service.member.IMemberResumeService#getMemberResume(java.lang.String)
	 * @author       Sandy Wong
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        清清网系统, 2014年8月16日
	 * @param id
	 * @return
	 * @throws ServiceException
	 * @throws ServiceException 
	 */
	@Override
	public MemberResume getMemberResume(String id) throws ServiceException {
		MemberResume resume = null;
		try {
			resume = dao.getMemberResume(id);
		} catch (DaoException e) {
			ExceptionHandle
					.throwServiceException(e, ErrorCode.SERVICE_ERROR, "MemberResumeServiceImpl.getMemberResume");
		}
		return resume;
	}

	/**
	 * 覆盖方法/实现方法(选择其一)
	 * (功能详细描述)
	 * @see com.work.service.member.IMemberResumeService#queryMemberResume(com.qing.common.util.Page, com.work.domain.MemberResume, java.lang.String, java.lang.String)
	 * @author       Sandy Wong
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        清清网系统, 2014年8月16日
	 * @param page
	 * @param resume
	 * @param orderColumn
	 * @param order
	 * @throws ServiceException
	 */
	@Override
	public void queryMemberResume(Page<MemberResume> page, MemberResume resume, boolean titleNotNull)
			throws ServiceException {
		try {
			dao.queryMemberResume(page, resume, titleNotNull);
		} catch (DaoException e) {
			ExceptionHandle.throwServiceException(e, ErrorCode.SERVICE_ERROR,
					"MemberResumeServiceImpl.queryMemberResume");
		}
	}

	/**
	 * 
	 * 覆盖方法/实现方法(选择其一)
	 * (功能详细描述)
	 * @see com.work.service.member.IMemberResumeService#queryMemberResume(com.work.domain.MemberResume, boolean)
	 * @author       Sandy Wong
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        清清网系统, 2014年10月3日
	 * @param resume
	 * @param titleNotNull
	 * @return
	 * @throws ServiceException
	 */
	@Override
	public List<MemberResume> queryMemberResume(MemberResume resume, boolean titleNotNull) throws ServiceException {
		try {
			return dao.queryMemberResume(resume, titleNotNull);
		} catch (DaoException e) {
			ExceptionHandle.throwServiceException(e, ErrorCode.SERVICE_ERROR,
					"MemberResumeServiceImpl.queryMemberResume");
		}
		return new ArrayList<MemberResume>();
	}

	/**
	 * 
	 * 覆盖方法/实现方法(选择其一)
	 * (功能详细描述)
	 * @see com.work.service.member.IMemberResumeService#getPartTimeTypeByMemberId(java.lang.String)
	 * @author       Sandy Wong
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        清清网系统, 2014年10月3日
	 * @param memberId
	 * @return
	 * @throws ServiceException
	 */
	@Override
	public Set<PartTimeType> getPartTimeTypeByMemberId(String memberId) throws ServiceException {
		try {
			return dao.getPartTimeTypeByMemberId(memberId);
		} catch (DaoException e) {
			ExceptionHandle.throwServiceException(e, ErrorCode.SERVICE_ERROR,
					"MemberResumeServiceImpl.getPartTimeTypeByMemberId");
		}
		return new HashSet<PartTimeType>();
	}

	/**
	 * 通过会员ID查询简历
	 * @see com.work.service.member.IMemberResumeService#getMemberResumesByMemberId(java.lang.String)
	 * @author       Sandy Wong
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        清清网系统, 2014年9月25日
	 * @param memberId
	 * @return
	 * @throws ServiceException
	 */
	@Override
	public List<MemberResume> getMemberResumesByMemberId(String memberId) throws ServiceException {
		MemberInfo info = new MemberInfo(memberId);
		MemberResume pattern = new MemberResume();
		pattern.setMemberInfo(info);
		return this.queryMemberResume(pattern, false);
	}

	/**
	 * 得到要保存的简历 （去除空值的copy）
	 * @author       Sandy Wong
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        清清网系统, 2014年9月25日
	 * @param resumeWithUpdateFields
	 * @param partTimeTypeIds
	 * @return
	 * @throws ServiceException
	 */
	@Override
	public MemberResume complementResume(MemberResume resumeWithUpdateFields, List<String> partTimeTypeIds)
			throws ServiceException {
		//处理简历信息
		MemberResume resumeToSave;
		List<PartTimeType> oldPartTimeTypes = new ArrayList<PartTimeType>();
		if (StringUtil.isValidStr(resumeWithUpdateFields.getResumeId())) {
			resumeToSave = this.getMemberResume(resumeWithUpdateFields.getResumeId());
			oldPartTimeTypes = resumeToSave.getPartTimeTypes();
			BeanUtils.copyProperties(resumeWithUpdateFields, resumeToSave);
		} else {
			resumeToSave = resumeWithUpdateFields;
			resumeToSave.setResumeId(null);
		}
		//处理会员信息保存
		MemberInfo infoToSave = memberInfoService.getMemberInfo(resumeWithUpdateFields.getMemberInfo().getMemberId());
		Date passwordUpdate = infoToSave.getPasswordUpdateTime();
		Date lastLoginTime = infoToSave.getLastLoginTime();
		BeanUtils.copyProperties(resumeWithUpdateFields.getMemberInfo(), infoToSave);
		infoToSave.setPasswordUpdateTime(passwordUpdate);
		infoToSave.setLastLoginTime(lastLoginTime);
		resumeToSave.setMemberInfo(infoToSave);
		//处理兼职类型信息保存
		PartTimeType type = null;
		List<PartTimeType> partTimeTypes = new ArrayList<PartTimeType>();
		if (partTimeTypeIds != null) {
			for (String typeId : partTimeTypeIds) {
				type = partTimeTypeService.getPartTimeType(typeId);
				partTimeTypes.add(type);
			}
		} else {
			partTimeTypes = oldPartTimeTypes;
		}
		resumeToSave.setPartTimeTypes(partTimeTypes);
		return resumeToSave;
	}

}
