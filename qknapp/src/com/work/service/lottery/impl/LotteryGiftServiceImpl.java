/*
 * Title:        二维码系统 2014年7月5日
 * Description:  [描述模块的功能、作用、使用方法和注意事项]
 * Copyright:    Copyright (c) 2012
 * Company:      Tai woo. Co., Ltd
 * @author       huangqingjian/0050
 * @version      1.0  2014年7月5日
 */
package com.work.service.lottery.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.qing.common.base.dao.HibernateDao;
import com.qing.common.exception.DaoException;
import com.qing.common.exception.ServiceException;
import com.qing.common.exception.handle.ExceptionHandle;
import com.qing.common.util.ErrorCode;
import com.qing.common.util.Page;
import com.qing.common.util.StringUtil;
import com.work.domain.LotteryGift;
import com.work.domain.LotteryPrizeSet;
import com.work.service.lottery.ILotteryGiftService;
import com.work.service.lottery.ILotteryPrizeService;
import com.work.vo.LotteryGiftVo;
import com.work.vo.LotteryPrizeSetVo;

/**
 * 抽奖礼品管理
 * (一句话功能简述)
 * (功能详细描述)
 * @author       huangqingjian/0050
 * @see          [相关类,可选,也可多条,对于重要的类或接口建议注释]
 * @since        二维码系统, 2014年7月5日
 */
@Service
@Repository
public class LotteryGiftServiceImpl implements ILotteryGiftService {
	@Resource
	HibernateDao<LotteryGift, String> lotteryGiftDao;
	@Resource
	ILotteryPrizeService prizeService;

	/**
	 * 新增或修改
	 * 覆盖方法/实现方法(选择其一)
	 * (功能详细描述)
	 * @see com.sh.service.ILotteryGiftService#saveOrUpdate(com.sh.dao.domain.LotteryGift)
	 * @author       huangqingjian/0050
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        二维码系统, 2014年7月5日
	 * @param gift
	 * @throws ServiceException 
	 */
	@Override
	public void saveOrUpdate(LotteryGift gift) throws ServiceException {
		try {
			lotteryGiftDao.saveOrUpdate(gift);
		} catch (DaoException e) {
			ExceptionHandle.throwServiceException(e, ErrorCode.DAO_SAVE_ERROR_001,
					"LotteryGiftServiceImpl.saveOrUpdate");
		}

	}

	/**
	 * 批量删除抽奖礼品
	 * 覆盖方法/实现方法(选择其一)
	 * (功能详细描述)
	 * @see com.sh.service.ILotteryGiftService#deleteGift(java.util.List)
	 * @author       huangqingjian/0050
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        二维码系统, 2014年7月5日
	 * @param gifts
	 * @throws ServiceException 
	 */
	@Override
	public void deleteGifts(List<LotteryGift> gifts) throws ServiceException {
		try {
			lotteryGiftDao.removeAll(gifts);
		} catch (DaoException e) {
			ExceptionHandle.throwServiceException(e, ErrorCode.DAO_SAVE_ERROR_001, "LotteryGiftServiceImpl.deleteGift");
		}

	}

	/**
	 * 获取单个礼品信息
	 * 覆盖方法/实现方法(选择其一)
	 * (功能详细描述)
	 * @see com.sh.service.ILotteryGiftService#getGiftById(java.lang.String)
	 * @author       huangqingjian/0050
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        二维码系统, 2014年7月5日
	 * @param id
	 * @return
	 * @throws ServiceException 
	 */
	@Override
	public LotteryGift getGiftById(String id) throws ServiceException {
		LotteryGift gift = null;
		try {
			gift = lotteryGiftDao.findById(LotteryGift.class, id);
		} catch (DaoException e) {
			ExceptionHandle
					.throwServiceException(e, ErrorCode.DAO_SAVE_ERROR_001, "LotteryGiftServiceImpl.getGiftById");
		}
		return gift;
	}

	/**
	 * 覆盖方法/实现方法(选择其一)
	 * (功能详细描述)
	 * @see com.sh.service.ILotteryGiftService#queryGiftByPage(com.thwl.common.util.Page, com.sh.vo.LotteryGiftVo)
	 * @author       huangqingjian/0050
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        二维码系统, 2014年7月5日
	 * @param page
	 * @param vo
	 * @throws ServiceException 
	 */
	@Override
	public void queryGiftByPage(Page<LotteryGift> page, LotteryGiftVo vo) throws ServiceException {
		StringBuilder builder = new StringBuilder();
		List<Object> parm = new ArrayList<Object>();
		builder.append(" from LotteryGift where 1=1 ");
		builder.append(generateHQLCondition(vo, parm));
		builder.append(" order by createDate desc");
		try {
			setPageSize(page, vo);
			lotteryGiftDao.findByPage(page, builder.toString(), parm.toArray());
		} catch (DaoException e) {
			ExceptionHandle.throwServiceException(e, ErrorCode.DAO_SAVE_ERROR_001,
					"LotteryGiftServiceImpl.queryGiftByPage");
		}
	}

	/**
	 * 构建查询条件
	 * (一句话功能简述)
	 * (功能详细描述)
	 * @author       huangqingjian/0050
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        二维码系统, 2014年7月5日
	 * @param vo
	 * @return
	 */
	private StringBuilder generateHQLCondition(LotteryGiftVo vo, List<Object> parm) {
		StringBuilder builder = new StringBuilder();
		if (StringUtil.isValidStr(vo.getGiftName())) {
			builder.append(" and giftName like ?");
			parm.add("%" + vo.getGiftName() + "%");
		}
		return builder;
	}

	/**
	 * 设置总页数
	 * (一句话功能简述)
	 * (功能详细描述)
	 * @author       huangqingjian/0050
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        二维码系统, 2014年7月5日
	 * @param page
	 * @param vo
	 * @throws DaoException
	 */
	private void setPageSize(Page<LotteryGift> page, LotteryGiftVo vo) throws DaoException {
		StringBuilder builder = new StringBuilder();
		List<Object> parm = new ArrayList<Object>();
		builder.append("select count(giftId)  from LotteryGift where 1=1 ");
		builder.append(generateHQLCondition(vo, parm));
		page.setTotalCount(lotteryGiftDao.findNumByHQL(builder.toString(), parm.toArray()));
	}

	/**
	 * 删除礼品
	 * (功能详细描述)
	 * @see com.sh.service.ILotteryGiftService#deleteGift(com.sh.dao.domain.LotteryGift)
	 * @author       huangqingjian/0050
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        二维码系统, 2014年7月7日
	 * @param gift
	 * @throws ServiceException
	 */
	@Override
	public void deleteGift(LotteryGift gift) throws ServiceException {
		try {
			LotteryPrizeSetVo vo = new LotteryPrizeSetVo();
			vo.setLotteryGiftId(gift.getGiftId());
			List<LotteryPrizeSet> list = prizeService.queryLotteryPrizeSet(vo);
			//将关联的奖项也删除
			if (null != list && list.size() > 0) {
				prizeService.deleteLotteryPrizeSet(list);
			}
			lotteryGiftDao.remove(gift);
		} catch (DaoException e) {
			ExceptionHandle.throwServiceException(e, ErrorCode.DAO_SAVE_ERROR_001, "LotteryGiftServiceImpl.deleteGift");
		}

	}

	/**
	 * 获取礼品列表
	 * 覆盖方法/实现方法(选择其一)
	 * (功能详细描述)
	 * @see com.sh.service.ILotteryGiftService#queryAll()
	 * @author       huangqingjian/0050
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        二维码系统, 2014年7月8日
	 * @return
	 * @throws ServiceException 
	 */
	@Override
	public List<LotteryGift> queryAll() throws ServiceException {

		List<LotteryGift> gifts = null;
		try {
			gifts = lotteryGiftDao.findAll(LotteryGift.class);
		} catch (DaoException e) {
			ExceptionHandle.throwServiceException(e, ErrorCode.DAO_SAVE_ERROR_001, "LotteryGiftServiceImpl.deleteGift");
		}
		return gifts;
	}
}
