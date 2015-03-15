/*
 * Title:        清清网系统 2014-8-19
 * Description:  [描述模块的功能、作用、使用方法和注意事项]
 * Copyright:    Copyright (c) 2014
 * Company:      清清网
 * @author       luoqinglong
 * @version      2.0  2014-8-19
 */
package com.work.service.exchange.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.qing.common.base.dao.impl.QueryResult;
import com.qing.vo.exchange.ExchangeOrderVO;
import com.work.dao.exchange.IExchangeOrderDetailDao;
import com.work.domain.ExchangeOrder;
import com.work.service.exchange.IExchangeOrderService;

/**
 * 订单处理
 * @author       luoqinglong
 * @see          [相关类,可选,也可多条,对于重要的类或接口建议注释]
 * @since        清清网系统, 2014-8-19
 */
@Service
public class ExchangeOrderServiceImpl implements IExchangeOrderService {
	@Resource
	IExchangeOrderDetailDao exchangeOrderDetailDao;

	@Override
	public void findExchangeOrderPage(QueryResult<ExchangeOrder> page, ExchangeOrderVO condition) {
		this.exchangeOrderDetailDao.findExchangeOrderPage(page, condition);
	}
}
