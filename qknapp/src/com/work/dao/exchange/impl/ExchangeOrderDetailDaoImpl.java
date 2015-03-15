/*
 * Title:        清清网系统 2014-8-19
 * Description:  [描述模块的功能、作用、使用方法和注意事项]
 * Copyright:    Copyright (c) 2014
 * Company:      清清网
 * @author       luoqinglong
 * @version      2.0  2014-8-19
 */
package com.work.dao.exchange.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import com.qing.common.base.dao.impl.BaseJdbcDao;
import com.qing.common.base.dao.impl.QueryResult;
import com.qing.vo.exchange.ExchangeOrderVO;
import com.work.dao.exchange.IExchangeOrderDetailDao;
import com.work.domain.ExchangeOrder;

/**
 *	兑换订单明细
 * @author       luoqinglong
 * @since        清清网系统, 2014-8-19
 */
@Repository
public class ExchangeOrderDetailDaoImpl extends BaseJdbcDao implements IExchangeOrderDetailDao {

	@Override
	public void findExchangeOrderPage(QueryResult<ExchangeOrder> page, ExchangeOrderVO condition) {
		StringBuilder sql = new StringBuilder("select * from T_EXCHANGE_ORDER where 1=1 ");
		List<Object> list = new ArrayList<Object>();

		if (StringUtils.isNotBlank(condition.getExchangeOrderId())) {
			sql.append(" and EXCHANGE_ORDER_ID = ?");
			list.add(condition.getExchangeOrderId());
		}
		if (StringUtils.isNotBlank(condition.getOrderSn())) {
			sql.append(" and ORDER_SN = ?");
			list.add(condition.getOrderSn());
		}
		sql.append("order by CREATE_TIME DESC");
		this.pageQuery(ExchangeOrder.class, sql.toString(), list.toArray(), page);
	}
}
