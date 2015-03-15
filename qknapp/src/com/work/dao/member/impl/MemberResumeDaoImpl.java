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
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.qing.common.base.dao.HibernateDao;
import com.qing.common.exception.DaoException;
import com.qing.common.util.Page;
import com.qing.common.util.StringUtil;
import com.work.dao.member.IMemberResumeDao;
import com.work.domain.MemberResume;
import com.work.domain.PartTimeType;

/**
 * 会员简历dao实现
 * @author       Sandy Wong
 * @see          [相关类,可选,也可多条,对于重要的类或接口建议注释]
 * @since        清清网系统, 2014年8月16日
 */
@Repository
public class MemberResumeDaoImpl implements IMemberResumeDao {
	@Resource
	HibernateDao<MemberResume, String> hibernateDao;

	/**
	 * 覆盖方法/实现方法(选择其一)
	 * (功能详细描述)
	 * @see com.work.dao.member.IMemberResumeDao#saveOrUpdateResume(com.work.domain.MemberResume)
	 * @author       Sandy Wong
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        清清网系统, 2014年8月16日
	 * @param resume
	 * @throws DaoException
	 */
	@Override
	public void saveOrUpdateResume(MemberResume resume) throws DaoException {
		hibernateDao.saveOrUpdate(resume);
	}

	/**
	 * 覆盖方法/实现方法(选择其一)
	 * (功能详细描述)
	 * @see com.work.dao.member.IMemberResumeDao#deleteResume(java.util.List)
	 * @author       Sandy Wong
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        清清网系统, 2014年8月16日
	 * @param resumes
	 * @throws DaoException
	 */
	@Override
	public void deleteResume(List<MemberResume> resumes) throws DaoException {
		hibernateDao.removeAll(resumes);
	}

	/**
	 * 覆盖方法/实现方法(选择其一)
	 * (功能详细描述)
	 * @see com.work.dao.member.IMemberResumeDao#getMemberResume(java.lang.String)
	 * @author       Sandy Wong
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        清清网系统, 2014年8月16日
	 * @param id
	 * @return
	 * @throws DaoException
	 */
	@Override
	public MemberResume getMemberResume(String id) throws DaoException {
		return hibernateDao.findById(MemberResume.class, id);
	}

	/**
	 * 覆盖方法/实现方法(选择其一)
	 * (功能详细描述)
	 * @see com.work.dao.member.IMemberResumeDao#queryMemberResume(com.qing.common.util.Page, com.work.domain.MemberResume)
	 * @author       Sandy Wong
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        清清网系统, 2014年8月16日
	 * @param page
	 * @param resume
	 * @param titleNotNull
	 * @throws DaoException
	 */
	@Override
	public void queryMemberResume(Page<MemberResume> page, MemberResume resume, boolean titleNotNull)
			throws DaoException {
		StringBuilder hql = new StringBuilder();
		List<Object> parm = new ArrayList<Object>();
		hql.append("select m from MemberResume m left join m.partTimeTypes p where 1=1 ");
		hql.append(generateHQLCondition(resume, parm, titleNotNull));
		hql.append(" group by m.resumeId");
		hql.append(" order by m.stickStatus desc,m.isRecommend desc,m.praiseNum desc, m.updateTime desc");
		setPageSize(page, resume, titleNotNull);
		hibernateDao.findByPage(page, hql.toString(), parm.toArray());
	}

	/**
	 * 
	 * 覆盖方法/实现方法(选择其一)
	 * (功能详细描述)
	 * @see com.work.dao.member.IMemberResumeDao#queryMemberResume(com.work.domain.MemberResume)
	 * @author       Sandy Wong
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        清清网系统, 2014年8月20日
	 * @param resume
	 * @param titleNotNull
	 * @return
	 * @throws DaoException
	 */
	@Override
	public List<MemberResume> queryMemberResume(MemberResume resume, boolean titleNotNull) throws DaoException {
		StringBuilder hql = new StringBuilder();
		List<Object> parm = new ArrayList<Object>();
		hql.append("select m from MemberResume m left join m.partTimeTypes p where 1=1 ");
		hql.append(generateHQLCondition(resume, parm, titleNotNull));
		hql.append(" group by m.resumeId");
		hql.append(" order by m.updateTime desc");
		return hibernateDao.findByValues(hql.toString(), parm.toArray(), false);
	}

	/**
	 * 
	 * 覆盖方法/实现方法(选择其一)
	 * (功能详细描述)
	 * @see com.work.dao.member.IMemberResumeDao#getPartTimeTypeByMemberId(java.lang.String)
	 * @author       Sandy Wong
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        清清网系统, 2014年9月16日
	 * @param memberId
	 * @return
	 * @throws DaoException
	 */
	@Override
	public Set<PartTimeType> getPartTimeTypeByMemberId(String memberId) throws DaoException {
		StringBuilder hql = new StringBuilder();
		List<Object> parm = new ArrayList<Object>();
		hql.append("from MemberResume where memberInfo.id = ?");
		parm.add(memberId);
		List<MemberResume> resumes = hibernateDao.findByValues(hql.toString(), parm.toArray(), false);
		Set<PartTimeType> types = new HashSet<PartTimeType>();
		if (resumes != null && !resumes.isEmpty()) {
			for (MemberResume resume : resumes) {
				types.addAll(resume.getPartTimeTypes());
			}
			return types;
		}
		return types;
	}

	/**
	 * 设置分页
	 * @author       Sandy Wong
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        清清网系统, 2014年8月16日
	 * @param page
	 * @param resume
	 * @throws DaoException
	 */
	private void setPageSize(Page<MemberResume> page, MemberResume resume, boolean titleNotNull) throws DaoException {
		StringBuilder builder = new StringBuilder();
		List<Object> parm = new ArrayList<Object>();
		builder.append("select count(distinct m.resumeId) from MemberResume m left join m.partTimeTypes p where 1=1 ");
		builder.append(generateHQLCondition(resume, parm, titleNotNull));
		page.setTotalCount(hibernateDao.findNumByHQL(builder.toString(), parm.toArray()));
	}

	/**
	 * 生成hql
	 * @author       Sandy Wong
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        清清网系统, 2014年8月21日
	 * @param resume
	 * @param parm
	 * @return
	 */
	private StringBuilder generateHQLCondition(MemberResume resume, List<Object> parm, boolean titleNotNull) {
		if (null == resume) {
			return new StringBuilder();
		}
		StringBuilder hql = new StringBuilder();
		if (StringUtil.isValidStr(resume.getResumeId())) {
			hql.append(" and m.resumeId = ?");
			parm.add(resume.getResumeId());
		}
		if (StringUtil.isValidStr(resume.getMemberInfo().getMemberId())) {
			hql.append(" and m.memberInfo.memberId = ?");
			parm.add(resume.getMemberInfo().getMemberId());
		}
		if (StringUtil.isValidStr(resume.getMemberInfo().getMemberName())) {
			hql.append(" and m.memberInfo.memberName like ?");
			parm.add(StringUtil.addLikeSymbol(resume.getMemberInfo().getMemberName()));
		}
		if (resume.getMemberInfo().getGender() != null) {
			hql.append(" and m.memberInfo.gender = ?");
			parm.add(resume.getMemberInfo().getGender());
		}
		if (StringUtil.isValidStr(resume.getMemberInfo().getProvince())) {
			hql.append(" and m.memberInfo.province like ?");
			parm.add(StringUtil.addLikeSymbol(resume.getMemberInfo().getProvince()));
		}
		if (StringUtil.isValidStr(resume.getMemberInfo().getCity())) {
			hql.append(" and m.memberInfo.city like ?");
			parm.add(StringUtil.addLikeSymbol(resume.getMemberInfo().getCity()));
		}
		if (StringUtil.isValidStr(resume.getMemberInfo().getRegion())) {
			hql.append(" and m.memberInfo.region like ?");
			parm.add(StringUtil.addLikeSymbol(resume.getMemberInfo().getRegion()));
		}
		if (StringUtil.isValidStr(resume.getResumeEmail())) {
			hql.append(" and m.resumeEmail like ?");
			parm.add(StringUtil.addLikeSymbol(resume.getResumeEmail()));
		}
		if (StringUtil.isValidStr(resume.getTitle())) {
			hql.append(" and m.title like ?");
			parm.add(StringUtil.addLikeSymbol(resume.getTitle()));
		}
		if (StringUtil.isValidStr(resume.getDescription())) {
			hql.append(" and title like ?");
			parm.add(StringUtil.addLikeSymbol(resume.getDescription()));
		}
		if (null != resume.getPhoneOpen()) {
			hql.append(" and m.phoneOpen = ?");
			parm.add(resume.getPhoneOpen());
		}
		if (null != resume.getEmployStatus()) {
			hql.append(" and m.employStatus = ?");
			parm.add(resume.getEmployStatus());
		}
		if (null != resume.getUpdateTime()) {
			hql.append(" and m.updateTime >= ?");
			parm.add(resume.getUpdateTime());
		}
		if (null != resume.getPartTimeTypes() && !resume.getPartTimeTypes().isEmpty()) {
			hql.append(" and p.typeName in ( ?");
			Iterator<PartTimeType> iterator = resume.getPartTimeTypes().iterator();
			parm.add(iterator.next().getTypeName());
			while (iterator.hasNext()) {
				hql.append(", ?");
				parm.add(iterator.next().getTypeName());
			}
			hql.append(")");
		}
		if (titleNotNull) {
			hql.append(" and m.title is not null");
		}
		return hql;
	}

}
