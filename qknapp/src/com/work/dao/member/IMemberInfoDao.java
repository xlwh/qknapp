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

import com.qing.common.exception.DaoException;
import com.qing.common.util.Page;
import com.work.domain.MemberInfo;

/**
 * 会员信息dao接口
 * @author       Sandy Wong
 * @see          [相关类,可选,也可多条,对于重要的类或接口建议注释]
 * @since        清清网系统, 2014年8月16日
 */
public interface IMemberInfoDao {
	/**
	 * 保存会员简历
	 * @author       Sandy Wong
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        清清网系统, 2014年8月16日
	 * @param resume
	 * @throws DaoException
	 */
	public void saveOrUpdateInfo(MemberInfo info) throws DaoException;

	/**
	 * 删除会员信息（可多个会员）
	 * @author       Sandy Wong
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        清清网系统, 2014年8月16日
	 * @param resumes
	 * @throws DaoException
	 */
	public void deleteInfo(List<MemberInfo> info) throws DaoException;

	/**
	 * 根据ID获取会员信息
	 * @author       Sandy Wong
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        清清网系统, 2014年8月16日
	 * @param id
	 * @return
	 * @throws DaoException
	 */
	public MemberInfo getMemberInfo(String id) throws DaoException;

	/**
	 * 查询会员信息
	 * @author       Sandy Wong
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        清清网系统, 2014年8月16日
	 * @param page
	 * @param info
	 * @param orderColumn 用于排序的列
	 * @param order 排序状态 (desc/asc)
	 * @throws DaoException
	 */
	public void queryMemberInfo(Page<MemberInfo> page, MemberInfo info, String orderColumn, String order)
			throws DaoException;

	/**
	 * 不分页查询会员信息
	 * @author       Sandy Wong
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        清清网系统, 2014年9月14日
	 * @param info
	 * @return
	 * @throws DaoException
	 */
	List<MemberInfo> queryMemberInfo(MemberInfo info) throws DaoException;

	/**
	 * 增加积分
	 * @author       Sandy Wong
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        清清网系统, 2014年10月6日
	 * @param memberId
	 * @param score
	 * @throws DaoException
	 */
	void addScore(String memberId, int score) throws DaoException;

	/**
	 * 减少积分
	 * @author       Sandy Wong
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        清清网系统, 2014年10月6日
	 * @param memberId
	 * @param score
	 * @param withTotalScore
	 * @throws DaoException
	 */
	void deleteScore(String memberId, int score, boolean withTotalScore) throws DaoException;
}
