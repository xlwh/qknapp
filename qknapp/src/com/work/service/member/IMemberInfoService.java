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

import com.qing.common.exception.DataException;
import com.qing.common.exception.ServiceException;
import com.qing.common.util.Page;
import com.work.domain.MemberInfo;
import com.work.domain.ScoreDetail;

/**
 * 会员信息service借壳
 * @author       Sandy Wong
 * @see          [相关类,可选,也可多条,对于重要的类或接口建议注释]
 * @since        清清网系统, 2014年8月16日
 */
public interface IMemberInfoService {
	/**
	 * 保存会员简历
	 * @author       Sandy Wong
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        清清网系统, 2014年8月16日
	 * @param resume
	 * @throws ServiceException
	 */
	public void saveOrUpdateInfo(MemberInfo info) throws ServiceException;

	/**
	 * 删除会员信息（可多个会员）
	 * @author       Sandy Wong
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        清清网系统, 2014年8月16日
	 * @param resumes
	 * @throws ServiceException
	 */
	public void deleteInfos(String[] ids) throws ServiceException;

	/**
	 * 根据ID获取会员信息
	 * @author       Sandy Wong
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        清清网系统, 2014年8月16日
	 * @param id
	 * @return
	 * @throws ServiceException
	 */
	public MemberInfo getMemberInfo(String id) throws ServiceException;

	/**
	 * 查询会员信息
	 * @author       Sandy Wong
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        清清网系统, 2014年8月16日
	 * @param page
	 * @param info
	 * @param orderColumn 用于排序的列
	 * @param order 排序状态 (desc/asc)
	 * @throws ServiceException
	 */
	public void queryMemberInfo(Page<MemberInfo> page, MemberInfo info, String orderColumn, String order)
			throws ServiceException;

	/**
	 * 发送验证码
	 * @author       Sandy Wong
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        清清网系统, 2014年8月19日
	 * @param info
	 * @param senderType
	 * @param function
	 * @throws ServiceException
	 */
	public void sendValidateCode(MemberInfo info, String senderType, String function) throws ServiceException,
			DataException;

	/**
	 * 注册会员
	 * @author       Sandy Wong
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        清清网系统, 2014年8月19日
	 * @param info
	 * @param validateCode
	 * @param senderType
	 * @return
	 * @throws ServiceException
	 * @throws DataException
	 */
	public MemberInfo registerMemberInfo(MemberInfo info, String validateCode, String senderType)
			throws ServiceException, DataException;

	/**
	 * 重制密码
	 * @author       Sandy Wong
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        清清网系统, 2014年8月21日
	 * @param info
	 * @param validateCode
	 * @param senderType
	 * @param newPassword
	 * @return
	 * @throws ServiceException
	 * @throws DataException
	 */
	public boolean resetPassword(MemberInfo info, String validateCode, String senderType, String newPassword)
			throws ServiceException, DataException;

	/**
	 * 修改密码
	 * @author       Sandy Wong
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        清清网系统, 2014年8月21日
	 * @param memberId
	 * @param oldPassword
	 * @param newPassword
	 * @return
	 * @throws ServiceException
	 * @throws DataException
	 */
	public boolean changePassword(String memberId, String oldPassword, String newPassword) throws ServiceException,
			DataException;

	/**
	 * 不分页查询会员信息
	 * @author       Sandy Wong
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        清清网系统, 2014年9月14日
	 * @param info
	 * @return
	 * @throws ServiceException
	 */
	public List<MemberInfo> queryMemberInfo(MemberInfo info) throws ServiceException;

	/**
	 * 增加积分
	 * @author       Sandy Wong
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        清清网系统, 2014年10月8日
	 * @param detail
	 * @throws ServiceException
	 */
	public void addScore(ScoreDetail detail) throws ServiceException;

	/**
	 * 减少积分
	 * @author       Sandy Wong
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        清清网系统, 2014年10月8日
	 * @param detail
	 * @param withTotalScore
	 * @throws ServiceException
	 */
	public void deleteScore(ScoreDetail detail, boolean withTotalScore) throws ServiceException;
}
