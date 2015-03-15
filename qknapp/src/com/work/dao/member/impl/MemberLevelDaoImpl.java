/*
 * Title:        清清网系统 2014-8-23
 * Description:  [描述模块的功能、作用、使用方法和注意事项]
 * Copyright:    Copyright (c) 2014
 * Company:      清清网
 * @author       luoqinglong
 * @version      2.0  2014-8-23
 */
package com.work.dao.member.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.qing.common.base.dao.HibernateDao;
import com.qing.common.exception.DaoException;
import com.work.dao.member.IMemberLevelDao;
import com.work.domain.MemberLevel;

/**
 *会员等级
 * @author       luoqinglong
 * @since        清清网系统, 2014-8-23
 */
@Repository
public class MemberLevelDaoImpl implements IMemberLevelDao {
	@Resource
	private HibernateDao<MemberLevel, String> memberLevelBaseDao;

	@Override
	public List<MemberLevel> findAllMemberLevel() throws DaoException {
		return this.memberLevelBaseDao.findAll(MemberLevel.class);
	}
}
