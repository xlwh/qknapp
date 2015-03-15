/*
 * Title:        清清网系统 2014-8-21
 * Description:  [描述模块的功能、作用、使用方法和注意事项]
 * Copyright:    Copyright (c) 2014
 * Company:      清清网
 * @author       luoqinglong
 * @version      2.0  2014-8-21
 */
package com.work.dao.exchange;

import com.qing.common.base.dao.impl.QueryResult;
import com.qing.common.exception.DaoException;
import com.qing.vo.exchange.ExchangeGiftVO;
import com.work.domain.ExchangeGift;

/**
 * 礼品
 * @author       luoqinglong
 * @see          [相关类,可选,也可多条,对于重要的类或接口建议注释]
 * @since        清清网系统, 2014-8-21
 */
public interface IExchangeGiftDao {

	public ExchangeGift getExchangeGiftById(String id) throws DaoException;

	/**
	 * 分页查询礼品
	 * @author       luoqinglong
	 * @since        清清网系统, 2014-8-21
	 */
	public void findExchangeGiftPage(QueryResult<ExchangeGift> result, ExchangeGiftVO condition);

	public void delExchageGift(String id) throws DaoException;;
}
