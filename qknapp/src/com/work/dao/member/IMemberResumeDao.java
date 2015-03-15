/*
 * Title:        清清网系统 2014年8月16日
 * Description:  [描述模块的功能、作用、使用方法和注意事项]
 * Copyright:    Copyright (c) 2014
 * Company:      清清网
 * @author       Sandy Wong
 * @version      2.0  2014年8月16日
 */
package com.work.dao.member;

import java.util.List;
import java.util.Set;

import com.qing.common.exception.DaoException;
import com.qing.common.util.Page;
import com.work.domain.MemberResume;
import com.work.domain.PartTimeType;

/**
 * 简历dao接口
 * @author       Sandy Wong
 * @see          [相关类,可选,也可多条,对于重要的类或接口建议注释]
 * @since        清清网系统, 2014年8月16日
 */
public interface IMemberResumeDao {
	/**
	 * 保存会员简历
	 * @author       Sandy Wong
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        清清网系统, 2014年8月16日
	 * @param resume
	 * @throws DaoException
	 */
	public void saveOrUpdateResume(MemberResume resume) throws DaoException;

	/**
	 * 删除简历（可多份简历）
	 * @author       Sandy Wong
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        清清网系统, 2014年8月16日
	 * @param resumes
	 * @throws DaoException
	 */
	public void deleteResume(List<MemberResume> resumes) throws DaoException;

	/**
	 * 根据ID获取会员简历 
	 * @author       Sandy Wong
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        清清网系统, 2014年8月16日
	 * @param id
	 * @return
	 * @throws DaoException
	 */
	public MemberResume getMemberResume(String id) throws DaoException;

	/**
	 * 查询简历
	 * @author       Sandy Wong
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        清清网系统, 2014年10月3日
	 * @param page
	 * @param resume
	 * @param titleNotNull
	 * @throws DaoException
	 */
	public void queryMemberResume(Page<MemberResume> page, MemberResume resume, boolean titleNotNull)
			throws DaoException;

	/**
	 * 查询，不分页
	 * @author       Sandy Wong
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        清清网系统, 2014年10月3日
	 * @param resume
	 * @param titleNotNull
	 * @return
	 * @throws DaoException
	 */
	List<MemberResume> queryMemberResume(MemberResume resume, boolean titleNotNull) throws DaoException;

	/**
	 * 根据会员ID获取兼职类型
	 * @author       Sandy Wong
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        清清网系统, 2014年9月16日
	 * @param memberId
	 * @return
	 * @throws DaoException
	 */
	Set<PartTimeType> getPartTimeTypeByMemberId(String memberId) throws DaoException;
}
