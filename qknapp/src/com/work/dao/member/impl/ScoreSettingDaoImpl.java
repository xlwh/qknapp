/*
 * Title:        清清网系统 2014年10月9日
 * Description:  [描述模块的功能、作用、使用方法和注意事项]
 * Copyright:    Copyright (c) 2014
 * Company:      清清网
 * @author       Sandy Wong
 * @version      2.0  2014年10月9日
 */
package com.work.dao.member.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.qing.common.base.dao.HibernateDao;
import com.qing.common.exception.DaoException;
import com.qing.common.util.Page;
import com.qing.common.util.StringUtil;
import com.work.dao.member.IScoreSettingDao;
import com.work.domain.ScoreSetting;

/**
 * 积分设置Dao实现
 * @author       Sandy Wong
 * @see          [相关类,可选,也可多条,对于重要的类或接口建议注释]
 * @since        清清网系统, 2014年10月9日
 */
@Repository
public class ScoreSettingDaoImpl implements IScoreSettingDao{
	@Resource
	HibernateDao<ScoreSetting, String> hibernateDao;
	
	/**
	 * 覆盖方法/实现方法(选择其一)
	 * (功能详细描述)
	 * @see com.work.dao.member.IScoreSettingDao#saveOrUpdateSetting(com.work.domain.ScoreSetting)
	 * @author       Sandy Wong
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        清清网系统, 2014年10月9日
	 * @param setting
	 * @throws DaoException
	 */
	@Override
	public void saveOrUpdateSetting(ScoreSetting setting) throws DaoException {
		this.hibernateDao.saveOrUpdate(setting);
	}

	/**
	 * 覆盖方法/实现方法(选择其一)
	 * (功能详细描述)
	 * @see com.work.dao.member.IScoreSettingDao#deleteSetting(com.work.domain.ScoreSetting)
	 * @author       Sandy Wong
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        清清网系统, 2014年10月9日
	 * @param setting
	 * @throws DaoException
	 */
	@Override
	public void deleteSetting(List<ScoreSetting> settings) throws DaoException {
		this.hibernateDao.removeAll(settings);
	}

	/**
	 * 覆盖方法/实现方法(选择其一)
	 * (功能详细描述)
	 * @see com.work.dao.member.IScoreSettingDao#getScoreSetting(java.lang.String)
	 * @author       Sandy Wong
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        清清网系统, 2014年10月9日
	 * @param id
	 * @return
	 * @throws DaoException
	 */
	@Override
	public ScoreSetting getScoreSetting(String id) throws DaoException {
		return this.hibernateDao.findById(ScoreSetting.class, id);
	}

	/**
	 * 覆盖方法/实现方法(选择其一)
	 * (功能详细描述)
	 * @see com.work.dao.member.IScoreSettingDao#getScoreSettingByTarget(java.lang.String)
	 * @author       Sandy Wong
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        清清网系统, 2014年10月9日
	 * @param target
	 * @return
	 * @throws DaoException
	 */
	@Override
	public ScoreSetting getScoreSettingByTarget(String target) throws DaoException {
		ScoreSetting scoreSetting = new ScoreSetting();
		scoreSetting.setTarget(target);
		List<ScoreSetting> settings = this.queryScoreSetting(scoreSetting);
		if (settings != null && !settings.isEmpty()) {
			return settings.get(0);
		}
		return null;
	}

	/**
	 * 覆盖方法/实现方法(选择其一)
	 * (功能详细描述)
	 * @see com.work.dao.member.IScoreSettingDao#queryScoreSetting(com.qing.common.util.Page, com.work.domain.ScoreSetting)
	 * @author       Sandy Wong
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        清清网系统, 2014年10月9日
	 * @param page
	 * @param setting
	 * @throws DaoException
	 */
	@Override
	public void queryScoreSetting(Page<ScoreSetting> page, ScoreSetting setting) throws DaoException {
		StringBuilder hql = new StringBuilder();
		List<Object> parm = new ArrayList<Object>();
		hql.append(" from ScoreSetting where 1=1 ");
		hql.append(generateHQLCondition(setting, parm));
		hql.append(" order by modifyDate desc");
		setPageSize(page, setting);
		hibernateDao.findByPage(page, hql.toString(), parm.toArray());
	}

	/**
	 * 覆盖方法/实现方法(选择其一)
	 * (功能详细描述)
	 * @see com.work.dao.member.IScoreSettingDao#queryScoreSetting(com.work.domain.ScoreSetting)
	 * @author       Sandy Wong
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        清清网系统, 2014年10月9日
	 * @param setting
	 * @return
	 * @throws DaoException
	 */
	@Override
	public List<ScoreSetting> queryScoreSetting(ScoreSetting setting) throws DaoException {
		StringBuilder hql = new StringBuilder();
		List<Object> parm = new ArrayList<Object>();
		hql.append(" from ScoreSetting where 1=1 ");
		hql.append(generateHQLCondition(setting, parm));
		hql.append(" order by modifyDate desc");
		return hibernateDao.findByValues(hql.toString(), parm.toArray(), false);
	}
	
	/**
	 * 设置分页
	 * @author       Sandy Wong
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        清清网系统, 2014年10月8日
	 * @param page
	 * @param setting
	 * @throws DaoException
	 */
	private void setPageSize(Page<ScoreSetting> page, ScoreSetting setting) throws DaoException {
		StringBuilder builder = new StringBuilder();
		List<Object> parm = new ArrayList<Object>();
		builder.append("select count(id)  from ScoreSetting where 1=1 ");
		builder.append(generateHQLCondition(setting, parm));
		page.setTotalCount(hibernateDao.findNumByHQL(builder.toString(), parm.toArray()));
	}

	/**
	 * 查询条件
	 * @author       Sandy Wong
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        清清网系统, 2014年10月8日
	 * @param setting
	 * @param parm
	 * @return
	 */
	private StringBuilder generateHQLCondition(ScoreSetting setting, List<Object> parm) {
		if (null == setting) {
			return new StringBuilder();
		}
		StringBuilder hql = new StringBuilder();
		if (StringUtil.isValidStr(setting.getTarget())) {
			hql.append(" and target = ?");
			parm.add(setting.getTarget());
		}
		if (StringUtil.isValidStr(setting.getDescription())) {
			hql.append(" and description like ?");
			parm.add(StringUtil.addLikeSymbol(setting.getDescription()));
		}
		return hql;
	}
}
