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
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.qing.common.base.dao.HibernateDao;
import com.qing.common.base.dao.impl.BaseJdbcDao;
import com.qing.common.exception.DaoException;
import com.qing.common.util.Page;
import com.qing.common.util.StringUtil;
import com.qing.constant.Constants;
import com.work.dao.member.IMemberInfoDao;
import com.work.dao.member.IMemberResumeDao;
import com.work.dao.member.IScoreDetailDao;
import com.work.domain.MemberInfo;
import com.work.domain.MemberResume;
import com.work.domain.ScoreDetail;

/**
 * 积分明细dao实现
 * @author       Sandy Wong
 * @see          [相关类,可选,也可多条,对于重要的类或接口建议注释]
 * @since        清清网系统, 2014年8月16日
 */
@Repository
public class ScoreDetailDaoImpl extends BaseJdbcDao implements IScoreDetailDao {
	@Resource
	HibernateDao<ScoreDetail, String> hibernateDao;
	@Resource
	IMemberInfoDao memberInfoDao;
	@Resource
	IMemberResumeDao resumeDao;

	/**
	 * 
	 * 覆盖方法/实现方法(选择其一)
	 * (功能详细描述)
	 * @see com.work.dao.member.IScoreDetailDao#saveOrUpdateDetail(com.work.domain.ScoreDetail)
	 * @author       Sandy Wong
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        清清网系统, 2014年10月8日
	 * @param detail
	 * @throws DaoException
	 */
	@Override
	public void saveOrUpdateDetail(ScoreDetail detail) throws DaoException {
		hibernateDao.saveOrUpdate(detail);
	}

	/**
	 * 
	 * 覆盖方法/实现方法(选择其一)
	 * (功能详细描述)
	 * @see com.work.dao.member.IScoreDetailDao#getScoreDetail(java.lang.String)
	 * @author       Sandy Wong
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        清清网系统, 2014年10月8日
	 * @param id
	 * @return
	 * @throws DaoException
	 */
	@Override
	public ScoreDetail getScoreDetail(String id) throws DaoException {
		return hibernateDao.findById(ScoreDetail.class, id);
	}

	/**
	 * 
	 * 覆盖方法/实现方法(选择其一)
	 * (功能详细描述)
	 * @see com.work.dao.member.IScoreDetailDao#queryScoreDetail(com.qing.common.util.Page, com.work.domain.ScoreDetail)
	 * @author       Sandy Wong
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        清清网系统, 2014年10月8日
	 * @param page
	 * @param detail
	 * @throws DaoException
	 */
	@Override
	public void queryScoreDetail(Page<ScoreDetail> page, ScoreDetail detail) throws DaoException {
		StringBuilder hql = new StringBuilder();
		List<Object> parm = new ArrayList<Object>();
		hql.append(" from ScoreDetail where 1=1 ");
		hql.append(generateHQLCondition(detail, parm));
		hql.append(" order by createTime desc");
		setPageSize(page, detail);
		hibernateDao.findByPage(page, hql.toString(), parm.toArray());
	}

	/**
	 * 不分页查询
	 * @see com.work.dao.member.IScoreDetailDao#queryScoreDetail(com.work.domain.ScoreDetail)
	 * @author       Sandy Wong
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        清清网系统, 2014年9月16日
	 * @param detail
	 * @return
	 * @throws DaoException
	 */
	@Override
	public List<ScoreDetail> queryScoreDetail(ScoreDetail detail) throws DaoException {
		StringBuilder hql = new StringBuilder();
		List<Object> parm = new ArrayList<Object>();
		hql.append(" from ScoreDetail where 1=1 ");
		hql.append(generateHQLCondition(detail, parm));
		hql.append(" order by createTime desc");
		return hibernateDao.findByValues(hql.toString(), parm.toArray(), false);
	}

	/**
	 * 
	 * (一句话功能简述)
	 * (功能详细描述)
	 * @author       Sandy Wong
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        清清网系统, 2014年11月9日
	 * @param detail
	 * @param limitNum
	 * @param afterRanking
	 * @return
	 * @throws DaoException
	 */
	@Override
	public List<Map<String, Object>> getRankOfDetail(ScoreDetail detail, Integer limitNum, ScoreDetail afterRanking)
			throws DaoException {
		StringBuilder sql = new StringBuilder();
		List<Object> parm = new ArrayList<Object>();
		sql.append("SELECT *, @rownum:=@rownum + 1 RANK FROM (  select count(s.MEMBER_ID) NUM, sum(s.score) RECOMMEND_SCORE, m.*");
		sql.append(" from T_SCORE_DETAIL s, T_MEMBER_INFO m");
		sql.append(" where s.MEMBER_ID=m.MEMBER_ID");
		if (detail != null) {
			if (StringUtil.isValidStr(detail.getMemberId())) {
				sql.append(" and s.MEMBER_ID=?");
				parm.add(detail.getMemberId());
			}
			if (StringUtil.isValidStr(detail.getOperateType())) {
				sql.append(" and s.OPERATE_TYPE=?");
				parm.add(detail.getOperateType());
			}
			if (StringUtil.isValidStr(detail.getTarget())) {
				sql.append(" and s.TARGET=?");
				parm.add(detail.getTarget());
			}
		}
		sql.append(" group by s.MEMBER_ID order by NUM desc ) A,(select @rownum:=0)B ");
		if (limitNum != null) {
			sql.append(" limit ?");
			parm.add(limitNum);
		}
		if (afterRanking != null) {
			sql.insert(0, "select a.* from (");
			sql.append(") a where 1=1");
			if (StringUtil.isValidStr(afterRanking.getMemberId())) {
				sql.append(" and a.MEMBER_ID=?");
				parm.add(afterRanking.getMemberId());
			}
			if (StringUtil.isValidStr(afterRanking.getOperateType())) {
				sql.append(" and a.OPERATE_TYPE=?");
				parm.add(afterRanking.getOperateType());
			}
			if (StringUtil.isValidStr(afterRanking.getTarget())) {
				sql.append(" and a.TARGET=?");
				parm.add(afterRanking.getTarget());
			}
		}
		List<Map<String, Object>> resultList = this.getJdbcTemplate().queryForList(sql.toString(),
				parm.toArray());
		return resultList;
	}
	
	@Override
	public void getRankOfDetailWithPage(ScoreDetail detail, Page<Map<String, Object>> page, ScoreDetail afterRanking)
			throws DaoException {
		StringBuilder sql = new StringBuilder();
		List<Object> parm = new ArrayList<Object>();
		sql.append("SELECT *, @rownum:=@rownum + 1 RANK FROM (  select count(s.MEMBER_ID) NUM, sum(s.score) RECOMMEND_SCORE, m.*");
		sql.append(" from T_SCORE_DETAIL s, T_MEMBER_INFO m");
		sql.append(" where s.MEMBER_ID=m.MEMBER_ID");
		if (detail != null) {
			if (StringUtil.isValidStr(detail.getMemberId())) {
				sql.append(" and s.MEMBER_ID=?");
				parm.add(detail.getMemberId());
			}
			if (StringUtil.isValidStr(detail.getOperateType())) {
				sql.append(" and s.OPERATE_TYPE=?");
				parm.add(detail.getOperateType());
			}
			if (StringUtil.isValidStr(detail.getTarget())) {
				sql.append(" and s.TARGET=?");
				parm.add(detail.getTarget());
			}
		}
		sql.append(" group by s.MEMBER_ID order by NUM desc ) A,(select @rownum:=0)B ");
		
		if (afterRanking != null) {
			sql.insert(0, "select a.* from (");
			sql.append(") a where 1=1");
			if (StringUtil.isValidStr(afterRanking.getMemberId())) {
				sql.append(" and a.MEMBER_ID=?");
				parm.add(afterRanking.getMemberId());
			}
			if (StringUtil.isValidStr(afterRanking.getOperateType())) {
				sql.append(" and a.OPERATE_TYPE=?");
				parm.add(afterRanking.getOperateType());
			}
			if (StringUtil.isValidStr(afterRanking.getTarget())) {
				sql.append(" and a.TARGET=?");
				parm.add(afterRanking.getTarget());
			}
			if (StringUtil.isValidStr(afterRanking.getSubjectInfo().getMemberName())) {
				sql.append(" and a.MEMBER_NAME like ?");
				parm.add(StringUtil.addLikeSymbol(afterRanking.getSubjectInfo().getMemberName()));
			}
			if (StringUtil.isValidStr(afterRanking.getSubjectInfo().getRealName())) {
				sql.append(" and a.REAL_NAME like ?");
				parm.add(StringUtil.addLikeSymbol(afterRanking.getSubjectInfo().getRealName()));
			}
			if (StringUtil.isValidStr(afterRanking.getSubjectInfo().getLoginCode())) {
				sql.append(" and a.LOGIN_CODE like ?");
				parm.add(StringUtil.addLikeSymbol(afterRanking.getSubjectInfo().getLoginCode()));
			}
			if (afterRanking.getSubjectInfo().getGender() != null) {
				sql.append(" and a.GENDER = ?");
				parm.add(afterRanking.getSubjectInfo().getGender());
			}
		}
		int totalCount = this.getJdbcTemplate().queryForInt("select count(1) from (" + sql.toString() + ") c", parm.toArray());
		page.setTotalCount(totalCount);
		int totalPage = totalCount % page.getPageSize() > 0 ? totalCount / page.getPageSize() + 1 : totalCount
				/ page.getPageSize();
		page.setTotalPage(totalPage);
		int startIndex = (page.getPageNo() - 1) * page.getPageSize();
		sql.append(" limit ?, ?");
		parm.add(startIndex);
		parm.add(page.getPageSize());
		List<Map<String, Object>> resultList = this.getJdbcTemplate().queryForList(sql.toString(),
				parm.toArray());
		page.setResult(resultList);
	}

	/**
	 * 设置分页
	 * @author       Sandy Wong
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        清清网系统, 2014年10月8日
	 * @param page
	 * @param detail
	 * @throws DaoException
	 */
	private void setPageSize(Page<ScoreDetail> page, ScoreDetail detail) throws DaoException {
		StringBuilder builder = new StringBuilder();
		List<Object> parm = new ArrayList<Object>();
		builder.append("select count(id) from ScoreDetail where 1=1 ");
		builder.append(generateHQLCondition(detail, parm));
		page.setTotalCount(hibernateDao.findNumByHQL(builder.toString(), parm.toArray()));
	}

	/**
	 * 查询条件
	 * @author       Sandy Wong
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        清清网系统, 2014年10月8日
	 * @param detail
	 * @param parm
	 * @return
	 */
	private StringBuilder generateHQLCondition(ScoreDetail detail, List<Object> parm) {
		if (null == detail) {
			return new StringBuilder();
		}
		StringBuilder hql = new StringBuilder();
		if (StringUtil.isValidStr(detail.getMemberId())) {
			hql.append(" and memberId = ?");
			parm.add(detail.getMemberId());
		}
		if (StringUtil.isValidStr(detail.getOperateType())) {
			hql.append(" and operateType = ?");
			parm.add(detail.getOperateType());
		}
		if (StringUtil.isValidStr(detail.getTarget())) {
			hql.append(" and target = ?");
			parm.add(detail.getTarget());
		}
		if (StringUtil.isValidStr(detail.getDetail())) {
			hql.append(" and detail like ?");
			parm.add(StringUtil.addLikeSymbol(detail.getDetail()));
		}
		return hql;
	}

	/**
	 * 分页查询推荐有礼详情
	 * @see com.work.dao.member.IScoreDetailDao#queryRecommendDetail(com.qing.common.util.Page, com.work.domain.ScoreDetail)
	 * @author       Sandy Wong
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        清清网系统, 2014年11月9日
	 * @param page
	 * @param detail
	 * @throws DaoException
	 */
	@Override
	public void queryRecommendDetail(Page<ScoreDetail> page, ScoreDetail detail) throws DaoException {
		StringBuilder hql = new StringBuilder();
		List<Object> parm = new ArrayList<Object>();
		StringBuilder fromHql = new StringBuilder();
		StringBuilder whereHql = new StringBuilder();
		whereHql = generateRecommendHQLCondition(detail, parm, fromHql);
		hql.append("select detail from ").append(fromHql).append(" where ").append(whereHql);
		hql.append(" order by detail.createTime desc");
		setRecommendPageSize(page, detail);
		hibernateDao.findByPage(page, hql.toString(), parm.toArray());
		if (page.getResult() != null && !page.getResult().isEmpty()) {
			for (ScoreDetail resultDetail : page.getResult()) {
				resultDetail.setSubjectInfo(memberInfoDao.getMemberInfo(resultDetail.getMemberId()));
				resultDetail.setObjectInfo(memberInfoDao.getMemberInfo(resultDetail.getObject()));
				MemberResume resume = new MemberResume();
				resume.setMemberInfo(new MemberInfo(resultDetail.getObject()));
				List<MemberResume> resumeList = resumeDao.queryMemberResume(resume, false);
				resultDetail.setObjectResume(resumeList != null && !resumeList.isEmpty() ? resumeList.get(0) : null);
			}
		}
	}

	/**
	 * 不分页查询
	 * @see com.work.dao.member.IScoreDetailDao#queryRecommendDetail(com.work.domain.ScoreDetail)
	 * @author       Sandy Wong
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        清清网系统, 2014年11月9日
	 * @param detail
	 * @return
	 * @throws DaoException
	 */
	@Override
	public List<ScoreDetail> queryRecommendDetail(ScoreDetail detail) throws DaoException {
		StringBuilder hql = new StringBuilder();
		List<Object> parm = new ArrayList<Object>();
		StringBuilder fromHql = new StringBuilder();
		StringBuilder whereHql = new StringBuilder();
		whereHql = generateRecommendHQLCondition(detail, parm, fromHql);
		hql.append(" select detail from ").append(fromHql).append(" where ").append(whereHql);
		hql.append(" order by detail.createTime desc");
		List<ScoreDetail> result = hibernateDao.findByValues(hql.toString(), parm.toArray(), false);
		if (result != null && !result.isEmpty()) {
			for (ScoreDetail resultDetail : result) {
				resultDetail.setSubjectInfo(memberInfoDao.getMemberInfo(resultDetail.getMemberId()));
				resultDetail.setObjectInfo(memberInfoDao.getMemberInfo(resultDetail.getObject()));
				MemberResume resume = new MemberResume();
				resume.setMemberInfo(new MemberInfo(resultDetail.getObject()));
				List<MemberResume> resumeList = resumeDao.queryMemberResume(resume, false);
				resultDetail.setObjectResume(resumeList != null && !resumeList.isEmpty() ? resumeList.get(0) : null);
			}
		}
		return result;
	}

	/**
	 * 设置推荐分页
	 * @author       Sandy Wong
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        清清网系统, 2014年11月6日
	 * @param page
	 * @param detail
	 * @throws DaoException
	 */
	private void setRecommendPageSize(Page<ScoreDetail> page, ScoreDetail detail) throws DaoException {
		StringBuilder builder = new StringBuilder();
		List<Object> parm = new ArrayList<Object>();
		StringBuilder fromHql = new StringBuilder();
		StringBuilder whereHql = new StringBuilder();
		whereHql = generateRecommendHQLCondition(detail, parm, fromHql);
		builder.append("select count(detail.id) from ").append(fromHql).append(" where ").append(whereHql);
		page.setTotalCount(hibernateDao.findNumByHQL(builder.toString(), parm.toArray()));
	}

	/**
	 * 查询推荐有礼详情的条件
	 * @author       Sandy Wong
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        清清网系统, 2014年11月6日
	 * @param detail
	 * @param parm
	 * @return
	 */
	private StringBuilder generateRecommendHQLCondition(ScoreDetail detail, List<Object> parm, StringBuilder fromHql) {
		StringBuilder hql = new StringBuilder();
		hql.append(" target = ?");
		parm.add(Constants.TARGET_RECOMMEND);
		fromHql.append("ScoreDetail detail");
		if (null == detail) {
			return hql;
		}
		if (detail.getSubjectInfo() != null) {
			if (StringUtil.isValidStr(detail.getSubjectInfo().getMemberId())) {
				hql.append(" and memberId = ?");
				parm.add(detail.getSubjectInfo().getMemberId());
			}
			if (StringUtil.isValidStr(detail.getSubjectInfo().getMemberName())) {
				hql.append(" and subjectInfo.memberName like ?");
				parm.add(StringUtil.addLikeSymbol(detail.getSubjectInfo().getMemberName()));
			}
			if (StringUtil.isValidStr(detail.getSubjectInfo().getLoginCode())) {
				hql.append(" and subjectInfo.loginCode like ?");
				parm.add(StringUtil.addLikeSymbol(detail.getSubjectInfo().getLoginCode()));
			}
			if (StringUtil.isValidStr(detail.getSubjectInfo().getRealName())) {
				hql.append(" and subjectInfo.realName like ?");
				parm.add(StringUtil.addLikeSymbol(detail.getSubjectInfo().getRealName()));
			}
			if (StringUtil.isValidStr(detail.getSubjectInfo().getRealName())) {
				hql.append(" and subjectInfo.realName like ?");
				parm.add(StringUtil.addLikeSymbol(detail.getSubjectInfo().getRealName()));
			}
			if (detail.getSubjectInfo().getGender() != null) {
				hql.append(" and subjectInfo.gender = ?");
				parm.add(detail.getSubjectInfo().getGender());
			}
			if (StringUtil.isValidStr(detail.getSubjectInfo().getProvince())) {
				hql.append(" and subjectInfo.province like ?");
				parm.add(StringUtil.addLikeSymbol(detail.getSubjectInfo().getProvince()));
			}
			if (StringUtil.isValidStr(detail.getSubjectInfo().getCity())) {
				hql.append(" and subjectInfo.city like ?");
				parm.add(StringUtil.addLikeSymbol(detail.getSubjectInfo().getCity()));
			}
			if (StringUtil.isValidStr(detail.getSubjectInfo().getRegion())) {
				hql.append(" and subjectInfo.region like ?");
				parm.add(StringUtil.addLikeSymbol(detail.getSubjectInfo().getRegion()));
			}
		}
		if (detail.getObjectInfo() != null) {
			if (StringUtil.isValidStr(detail.getObjectInfo().getMemberId())) {
				hql.append(" and object = ?");
				parm.add(detail.getObjectInfo().getMemberId());
			}
			if (StringUtil.isValidStr(detail.getObjectInfo().getMemberName())) {
				hql.append(" and objectInfo.memberName like ?");
				parm.add(StringUtil.addLikeSymbol(detail.getObjectInfo().getMemberName()));
			}
			if (StringUtil.isValidStr(detail.getObjectInfo().getLoginCode())) {
				hql.append(" and objectInfo.loginCode like ?");
				parm.add(StringUtil.addLikeSymbol(detail.getObjectInfo().getLoginCode()));
			}
			if (StringUtil.isValidStr(detail.getObjectInfo().getRealName())) {
				hql.append(" and objectInfo.realName like ?");
				parm.add(StringUtil.addLikeSymbol(detail.getObjectInfo().getRealName()));
			}
			if (StringUtil.isValidStr(detail.getObjectInfo().getRealName())) {
				hql.append(" and objectInfo.realName like ?");
				parm.add(StringUtil.addLikeSymbol(detail.getObjectInfo().getRealName()));
			}
			if (detail.getObjectInfo().getGender() != null) {
				hql.append(" and objectInfo.gender = ?");
				parm.add(detail.getObjectInfo().getGender());
			}
			if (StringUtil.isValidStr(detail.getObjectInfo().getProvince())) {
				hql.append(" and objectInfo.province like ?");
				parm.add(StringUtil.addLikeSymbol(detail.getObjectInfo().getProvince()));
			}
			if (StringUtil.isValidStr(detail.getObjectInfo().getCity())) {
				hql.append(" and objectInfo.city like ?");
				parm.add(StringUtil.addLikeSymbol(detail.getObjectInfo().getCity()));
			}
			if (StringUtil.isValidStr(detail.getObjectInfo().getRegion())) {
				hql.append(" and objectInfo.region like ?");
				parm.add(StringUtil.addLikeSymbol(detail.getObjectInfo().getRegion()));
			}
		}
		if (detail.getObjectCompleteResume() != null) {
			hql.append(" and object in (select memberId from MemberResume where title is ");
			if (detail.getObjectCompleteResume()) {
				hql.append("not ");
			}
			hql.append("null)");
		}
		if (hql.indexOf("subjectInfo") != -1) {
			fromHql.append(" ,MemberInfo subjectInfo");
			hql.append(" and detail.memberId=subjectInfo.memberId");
		}
		if (hql.indexOf("objectInfo") != -1) {
			fromHql.append(" ,MemberInfo objectInfo");
			hql.append(" and detail.object=objectInfo.memberId");
		}
		return hql;
	}
}
