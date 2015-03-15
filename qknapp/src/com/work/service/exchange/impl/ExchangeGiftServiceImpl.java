/*
 * Title:        清清网系统 2014-8-21
 * Description:  [描述模块的功能、作用、使用方法和注意事项]
 * Copyright:    Copyright (c) 2014
 * Company:      清清网
 * @author       luoqinglong
 * @version      2.0  2014-8-21
 */
package com.work.service.exchange.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.qing.common.base.dao.HibernateDao;
import com.qing.common.base.dao.impl.QueryResult;
import com.qing.common.exception.DaoException;
import com.qing.common.exception.ServiceException;
import com.qing.common.exception.handle.ExceptionHandle;
import com.qing.common.util.ErrorCode;
import com.qing.vo.exchange.ExchangeGiftVO;
import com.work.dao.exchange.IExchangeGiftDao;
import com.work.domain.ExchangeGift;
import com.work.service.exchange.IExchangeGiftService;

/**
 * 礼品
 * @author       luoqinglong
 * @see          [相关类,可选,也可多条,对于重要的类或接口建议注释]
 * @since        清清网系统, 2014-8-21
 */
@Service
public class ExchangeGiftServiceImpl implements IExchangeGiftService {
	@Resource
	private IExchangeGiftDao exchangeGiftDao;
	@Resource
	private HibernateDao<ExchangeGift, String> exchangeGiftBaseDao;

	@Override
	public ExchangeGift getExchangeGiftById(String id) throws ServiceException {
		ExchangeGift exchangeGift = null;
		try {
			exchangeGift = this.exchangeGiftDao.getExchangeGiftById(id);
		} catch (DaoException e) {
			ExceptionHandle.throwServiceException(e, ErrorCode.SERVICE_QUERY_ERROR_004,
					"ExchangeGiftServiceImpl.getExchangeGiftById(),id:" + id);
		}
		return exchangeGift;
	}

	@Override
	public void findExchangeGiftPage(QueryResult<ExchangeGift> page, ExchangeGiftVO condition) {
		this.exchangeGiftDao.findExchangeGiftPage(page, condition);

	}

	@Override
	public void saveOrUpdate(ExchangeGift exchangeGift) throws ServiceException {
		try {
			this.exchangeGiftBaseDao.saveOrUpdate(exchangeGift);
		} catch (DaoException e) {
			ExceptionHandle.throwServiceException(e, ErrorCode.SERVICE_SAVE_ERROR_001,
					"ExchangeGiftServiceImpl.saveOrUpdate(),exchangeGift:" + exchangeGift.toString());
		}
	}

	@Override
	public boolean delExchageGift(String giftId) throws ServiceException {
		boolean flag = false;
		try {
			this.exchangeGiftDao.delExchageGift(giftId);
			flag = true;
		} catch (DaoException e) {
			ExceptionHandle.throwServiceException(e, ErrorCode.SERVICE_DELETE_ERROR_002,
					"兑换礼品删除失败:ExchangeGiftServiceImpl.delExchageGift(),giftId:" + giftId);
			flag = false;
		}
		return flag;
	}
}
