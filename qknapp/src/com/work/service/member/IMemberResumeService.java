/*
 * Title:        清清网系统 2014年8月16日
 * Description:  [描述模块的功能、作用、使用方法和注意事项]
 * Copyright:    Copyright (c) 2014
 * Company:      清清网
 * @author       Sandy Wong
 * @version      2.0  2014年8月16日
 */
package com.work.service.member;

import java.util.List;
import java.util.Set;

import com.qing.common.exception.ServiceException;
import com.qing.common.util.Page;
import com.work.domain.MemberResume;
import com.work.domain.PartTimeType;

/**
 * 会员简历service借壳
 * @author       Sandy Wong
 * @see          [相关类,可选,也可多条,对于重要的类或接口建议注释]
 * @since        清清网系统, 2014年8月16日
 */
public interface IMemberResumeService {
	/**
	 * 保存会员简历
	 * @author       Sandy Wong
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        清清网系统, 2014年8月16日
	 * @param resume
	 * @throws ServiceException
	 */
	public void saveOrUpdateResume(MemberResume resume) throws ServiceException;

	/**
	 * 删除会员简历（可多个简历）
	 * @author       Sandy Wong
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        清清网系统, 2014年8月16日
	 * @param resumes
	 * @throws ServiceException
	 */
	public void deleteResumes(String[] ids) throws ServiceException;

	/**
	 * 根据ID获取会员简历
	 * @author       Sandy Wong
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        清清网系统, 2014年8月16日
	 * @param id
	 * @return
	 * @throws ServiceException
	 */
	public MemberResume getMemberResume(String id) throws ServiceException;

	/**
	 * 查询会员简历
	 * @author       Sandy Wong
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        清清网系统, 2014年10月3日
	 * @param page
	 * @param resume
	 * @param titleNotNull
	 * @throws ServiceException
	 */
	public void queryMemberResume(Page<MemberResume> page, MemberResume resume, boolean titleNotNull)
			throws ServiceException;

	/**
	 * 不分页查询
	 * @author       Sandy Wong
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        清清网系统, 2014年10月3日
	 * @param resume
	 * @param titleNotNull
	 * @return
	 * @throws ServiceException
	 */
	public List<MemberResume> queryMemberResume(MemberResume resume, boolean titleNotNull) throws ServiceException;

	/**
	 * 根据会员ID获取兼职类型
	 * @author       Sandy Wong
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        清清网系统, 2014年9月16日
	 * @param memberId
	 * @return
	 */
	public Set<PartTimeType> getPartTimeTypeByMemberId(String memberId) throws ServiceException;

	/**
	 * 通过会员ID查询简历
	 * @author       Sandy Wong
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        清清网系统, 2014年9月25日
	 * @param memberId
	 * @return
	 * @throws ServiceException
	 */
	List<MemberResume> getMemberResumesByMemberId(String memberId) throws ServiceException;

	/**
	 * 得到要保存的简历 （去除空值的copy）
	 * @author       Sandy Wong
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        清清网系统, 2014年10月3日
	 * @param resumeWithUpdateFields
	 * @param partTimeTypeIds
	 * @return
	 * @throws ServiceException
	 */
	MemberResume complementResume(MemberResume resumeWithUpdateFields, List<String> partTimeTypeIds)
			throws ServiceException;
}
