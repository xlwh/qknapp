/*
 * Title:        清清网系统 2014-8-21
 * Description:  [描述模块的功能、作用、使用方法和注意事项]
 * Copyright:    Copyright (c) 2014
 * Company:      清清网
 * @author       luoqinglong
 * @version      2.0  2014-8-21
 */
package com.work.dao.exchange.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import com.qing.common.base.dao.HibernateDao;
import com.qing.common.base.dao.impl.BaseJdbcDao;
import com.qing.common.base.dao.impl.QueryResult;
import com.qing.common.exception.DaoException;
import com.qing.vo.exchange.ExchangeGiftVO;
import com.work.dao.exchange.IExchangeGiftDao;
import com.work.domain.ExchangeGift;

/**
 * 礼品
 * @author       luoqinglong
 * @since        清清网系统, 2014-8-21
 */
@Repository
public class ExchangeGiftDaoImpl extends BaseJdbcDao implements IExchangeGiftDao {
	@Resource
	private HibernateDao<ExchangeGift, String> exchangeGiftBaseDao;

	@Override
	public ExchangeGift getExchangeGiftById(String id) throws DaoException {
		return this.exchangeGiftBaseDao.findById(ExchangeGift.class, id);
	}

	/**
	 * 分页查询礼品
	 * @see com.work.dao.exchange.IExchangeGiftDao#findExchangeGiftPage(com.qing.common.base.dao.impl.QueryResultVO, com.qing.vo.exchange.ExchangeGiftVO)
	 * @author       luoqinglong
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        清清网系统, 2014-8-21
	 * @param result
	 * @param condition
	 */
	@Override
	public void findExchangeGiftPage(QueryResult<ExchangeGift> result, ExchangeGiftVO condition) {
		StringBuilder sql = new StringBuilder("select * from T_EXCHANGE_GIFT where 1=1 ");
		List<Object> list = new ArrayList<Object>();

		if (StringUtils.isNotBlank(condition.getGiftName())) {
			sql.append(" and GIFT_NAME like '%" + condition.getGiftName() + "%'");
		}
		if (condition.getExchangeType() != null) {
			sql.append(" and EXCHANGE_TYPE = ?");
			list.add(condition.getExchangeType());
		}
		sql.append(" order by CREATE_TIME DESC");
		this.pageQuery(ExchangeGift.class, sql.toString(), list.toArray(), result);

	}

	@Override
	public void delExchageGift(String id) throws DaoException {
		StringBuilder sql = new StringBuilder();
		sql.append(" delete from T_EXCHANGE_GIFT  where GIFT_ID =? ");
		try {
			this.getJdbcTemplate().update(sql.toString(), id);
		} catch (Exception e) {
			throw new DaoException("数据库操作失败", e);
		}
	}
}
