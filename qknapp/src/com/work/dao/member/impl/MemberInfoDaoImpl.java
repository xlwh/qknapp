/*
 * Title:        清清网系统 2014年8月16日
 * Description:  [描述模块的功能、作用、使用方法和注意事项]
 * Copyright:    Copyright (c) 2014
 * Company:      清清网
 * @author       Sandy Wong
 * @version      2.0  2014年8月16日
 */
package com.work.dao.member.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.qing.common.base.dao.HibernateDao;
import com.qing.common.exception.DaoException;
import com.qing.common.util.DateTimeUtils;
import com.qing.common.util.Page;
import com.qing.common.util.StringUtil;
import com.work.dao.member.IMemberInfoDao;
import com.work.domain.MemberInfo;

/**
 * 会员信息dao实现
 * @author       Sandy Wong
 * @see          [相关类,可选,也可多条,对于重要的类或接口建议注释]
 * @since        清清网系统, 2014年8月16日
 */
@Repository
public class MemberInfoDaoImpl implements IMemberInfoDao {
	@Resource
	HibernateDao<MemberInfo, String> hibernateDao;

	/**
	 * 覆盖方法/实现方法(选择其一)
	 * (功能详细描述)
	 * @see com.work.dao.member.IMemberInfoDao#saveOrUpdateInfo(com.work.domain.MemberInfo)
	 * @author       Sandy Wong
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        清清网系统, 2014年8月16日
	 * @param info
	 * @throws DaoException
	 */
	@Override
	public void saveOrUpdateInfo(MemberInfo info) throws DaoException {
		hibernateDao.saveOrUpdate(info);
	}

	/**
	 * 覆盖方法/实现方法(选择其一)
	 * (功能详细描述)
	 * @see com.work.dao.member.IMemberInfoDao#deleteInfo(java.util.List)
	 * @author       Sandy Wong
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        清清网系统, 2014年8月16日
	 * @param info
	 * @throws DaoException
	 */
	@Override
	public void deleteInfo(List<MemberInfo> infos) throws DaoException {
		hibernateDao.removeAll(infos);
	}

	/**
	 * 覆盖方法/实现方法(选择其一)
	 * (功能详细描述)
	 * @see com.work.dao.member.IMemberInfoDao#getMemberInfo(java.lang.String)
	 * @author       Sandy Wong
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        清清网系统, 2014年8月16日
	 * @param id
	 * @return
	 * @throws DaoException
	 */
	@Override
	public MemberInfo getMemberInfo(String id) throws DaoException {
		return hibernateDao.findById(MemberInfo.class, id);
	}

	/**
	 * 
	 * 覆盖方法/实现方法(选择其一)
	 * (功能详细描述)
	 * @see com.work.dao.member.IMemberInfoDao#queryMemberInfo(com.qing.common.util.Page, com.work.domain.MemberInfo, java.lang.String, java.lang.String)
	 * @author       Sandy Wong
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        清清网系统, 2014年8月16日
	 * @param page
	 * @param info
	 * @param orderColumn
	 * @param order
	 * @throws DaoException
	 */
	@Override
	public void queryMemberInfo(Page<MemberInfo> page, MemberInfo info, String orderColumn, String order)
			throws DaoException {
		StringBuilder hql = new StringBuilder();
		List<Object> parm = new ArrayList<Object>();
		hql.append(" from MemberInfo where 1=1 ");
		hql.append(generateHQLCondition(info, parm));
		if (orderColumn != null && order != null) {
			hql.append(" order by ").append(orderColumn).append(" ").append(order);
		} else {
			hql.append(" order by lastLoginTime desc"); //默认按最后登录时间查看
		}
		setPageSize(page, info);
		hibernateDao.findByPage(page, hql.toString(), parm.toArray());
	}

	/**
	 * 不分页查询
	 * @see com.work.dao.member.IMemberInfoDao#queryMemberInfo(com.work.domain.MemberInfo)
	 * @author       Sandy Wong
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        清清网系统, 2014年9月16日
	 * @param info
	 * @return
	 * @throws DaoException
	 */
	@Override
	public List<MemberInfo> queryMemberInfo(MemberInfo info) throws DaoException {
		StringBuilder hql = new StringBuilder();
		List<Object> parm = new ArrayList<Object>();
		hql.append(" from MemberInfo where 1=1 ");
		hql.append(generateHQLCondition(info, parm));
		return hibernateDao.findByValues(hql.toString(), parm.toArray(), false);
	}

	/**
	 * 覆盖方法/实现方法(选择其一)
	 * (功能详细描述)
	 * @see com.work.dao.member.IMemberInfoDao#addScore(java.lang.String, int)
	 * @author       Sandy Wong
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        清清网系统, 2014年10月6日
	 * @param memberId
	 * @param score
	 * @throws DaoException
	 */
	@Override
	public void addScore(String memberId, int score) throws DaoException {
		String sql = "update T_MEMBER_INFO set TOTAL_SCORE=TOTAL_SCORE+?, REMAIN_SCORE=REMAIN_SCORE+? where MEMBER_ID=?";
		List<Object> params = new ArrayList<Object>();
		params.add(score);
		params.add(score);
		params.add(memberId);
		this.hibernateDao.excuteSQL(sql, params.toArray());
	}

	/**
	 * 覆盖方法/实现方法(选择其一)
	 * (功能详细描述)
	 * @see com.work.dao.member.IMemberInfoDao#deleteScore(java.lang.String, int, boolean)
	 * @author       Sandy Wong
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        清清网系统, 2014年10月6日
	 * @param memberId
	 * @param score
	 * @param withTotalScore
	 * @throws DaoException
	 */
	@Override
	public void deleteScore(String memberId, int score, boolean withTotalScore) throws DaoException {
		List<Object> params = new ArrayList<Object>();
		String sql = "update T_MEMBER_INFO set REMAIN_SCORE=(case when REMAIN_SCORE>? then REMAIN_SCORE-? else 0 end)";
		params.add(score);
		params.add(score);
		if (withTotalScore) {
			sql += ", TOTAL_SCORE=(case when TOTAL_SCORE>? then TOTAL_SCORE-? else 0 end)";
			params.add(score);
			params.add(score);
		}
		sql += " where MEMBER_ID=?";
		params.add(memberId);
		this.hibernateDao.excuteSQL(sql, params.toArray());
	}

	/**
	 * (一句话功能简述)
	 * (功能详细描述)
	 * @author       Sandy Wong
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        清清网系统, 2014年8月16日
	 * @param page
	 * @param resume
	 * @throws DaoException
	 */
	private void setPageSize(Page<MemberInfo> page, MemberInfo resume) throws DaoException {
		StringBuilder builder = new StringBuilder();
		List<Object> parm = new ArrayList<Object>();
		builder.append("select count(memberId)  from MemberInfo where 1=1 ");
		builder.append(generateHQLCondition(resume, parm));
		page.setTotalCount(hibernateDao.findNumByHQL(builder.toString(), parm.toArray()));
	}

	private StringBuilder generateHQLCondition(MemberInfo info, List<Object> parm) {
		if (null == info) {
			return new StringBuilder();
		}
		StringBuilder hql = new StringBuilder();
		if (StringUtil.isValidStr(info.getMemberId())) {
			hql.append(" and memberId = ?");
			parm.add(info.getMemberId());
		}
		if (StringUtil.isValidStr(info.getLoginCode())) {
			hql.append(" and loginCode = ?");
			parm.add(info.getLoginCode());
		}
		if (info.getLoginType() != null) {
			hql.append(" and loginType = ?");
			parm.add(info.getLoginType());
		}
		if (StringUtil.isValidStr(info.getLoginIp())) {
			hql.append(" and loginIp = ?");
			parm.add(info.getLoginIp());
		}
		if (StringUtil.isValidStr(info.getPassword())) {
			hql.append(" and password = ?");
			parm.add(info.getPassword());
		}
		if (info.getPasswordUpdateTime() != null) {
			hql.append(" and passwordUpdateTime > ?");
			parm.add(info.getPasswordUpdateTime());
		}
		if (info.getStatus() != null) {
			hql.append(" and status = ?");
			parm.add(info.getStatus());
		}
		if (StringUtil.isValidStr(info.getMemberName())) {
			hql.append(" and memberName like ?");
			parm.add(StringUtil.addLikeSymbol(info.getMemberName()));
		}
		if (StringUtil.isValidStr(info.getRealName())) {
			hql.append(" and realName like ?");
			parm.add(StringUtil.addLikeSymbol(info.getRealName()));
		}
		if (StringUtil.isValidStr(info.getEmailAddress())) {
			hql.append(" and emailAddress like ?");
			parm.add(StringUtil.addLikeSymbol(info.getEmailAddress()));
		}
		if (StringUtil.isValidStr(info.getCellphone())) {
			hql.append(" and cellPhone = ?");
			parm.add(info.getCellphone());
		}
		if (info.getGender() != null) {
			hql.append(" and gender = ?");
			parm.add(info.getGender());
		}
		if (info.getBirthday() != null) {
			hql.append(" and birthday = ?");
			parm.add(info.getBirthday());
		}
		if (StringUtil.isValidStr(info.getProvince())) {
			hql.append(" and province like ?");
			parm.add(StringUtil.addLikeSymbol(info.getProvince()));
		}
		if (StringUtil.isValidStr(info.getCity())) {
			hql.append(" and city like ?");
			parm.add(StringUtil.addLikeSymbol(info.getCity()));
		}
		if (StringUtil.isValidStr(info.getRegion())) {
			hql.append(" and region like ?");
			parm.add(StringUtil.addLikeSymbol(info.getRegion()));
		}
		if (info.getHasHealthCard() != null) {
			hql.append(" and hasHealthCard = ?");
			parm.add(info.getHasHealthCard());
		}
		if (StringUtil.isValidStr(info.getFreeTime())) {
			hql.append("and (");
			String[] freeTimes = info.getFreeTime().split(",");
			for (int i = 0; i < freeTimes.length; i++) {
				if (StringUtil.isValidStr(freeTimes[i])) {
					hql.append(" freeTime like ?");
					if (i != freeTimes.length - 1) {
						hql.append(" or ");
					}
					parm.add(StringUtil.addLikeSymbol(freeTimes[i]));
				}
			}
			hql.append(")");

		}
		if (StringUtil.isValidStr(info.getUniversity())) {
			hql.append(" and university like ?");
			parm.add(StringUtil.addLikeSymbol(info.getUniversity()));
		}
		if (info.getHeight() != null) {
			hql.append(" and height >= ?");
			parm.add(info.getHeight());
		}
		if (info.getWeight() != null) {
			hql.append(" and weight <= ?");
			parm.add(info.getWeight());
		}
		if (info.getTotalScore() != null) {
			hql.append(" and totalScore >= ?");
			parm.add(info.getTotalScore());
		}
		if (info.getTotalScore2() != null) {
			hql.append(" and totalScore <= ?");
			parm.add(info.getTotalScore2());
		}
		if (info.getRemainScore() != null) {
			hql.append(" and remainScore >= ?");
			parm.add(info.getRemainScore());
		}
		if (info.getRemainScore2() != null) {
			hql.append(" and remainScore <= ?");
			parm.add(info.getRemainScore2());
		}
		if (info.getCreateTime() != null) {
			hql.append(" and createTime >= ?");
			parm.add(info.getCreateTime());
		}
		if (StringUtil.isValidStr(info.getCreateTime1())) {
			hql.append(" and createTime >= ?");		
			parm.add(DateTimeUtils.parseDateYMDHMS(info.getCreateTime1()));
		}
		if (StringUtil.isValidStr(info.getCreateTime2())) {
			hql.append(" and createTime <= ?");
			parm.add(DateTimeUtils.parseDateYMDHMS(info.getCreateTime2()));
		}
		if (StringUtil.isValidStr(info.getWechat())) {
			hql.append(" and weChat = ?");
			parm.add(info.getWechat());
		}
		if (info.getBirthdayMonth() != null) {
			hql.append(" and month(birthday) >= ?");
			parm.add(info.getBirthdayMonth());
		}
		if (info.getBirthdayMonth2() != null) {
			hql.append(" and month(birthday) <= ?");
			parm.add(info.getBirthdayMonth2());
		}
		return hql;
	}
}
