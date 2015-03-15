/*
 * Title:        清清网系统 2014-8-19
 * Description:  [描述模块的功能、作用、使用方法和注意事项]
 * Copyright:    Copyright (c) 2014
 * Company:      清清网
 * @author       huangqingjian
 * @version      2.0  2014-8-19
 */
package com.work.dao.exchange;

import com.qing.common.base.dao.impl.QueryResult;
import com.qing.vo.exchange.ExchangeOrderVO;
import com.work.domain.ExchangeOrder;

/**
 *兑换订单
 * @author       luoqinglong
 * @since        清清网系统, 2014-8-19
 */
public interface IExchangeOrderDetailDao {
	public void findExchangeOrderPage(QueryResult<ExchangeOrder> result, ExchangeOrderVO condition);
}
