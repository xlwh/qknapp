/*
 * Title:        二维码系统 2014年7月8日
 * Description:  [描述模块的功能、作用、使用方法和注意事项]
 * Copyright:    Copyright (c) 2012
 * Company:      Tai woo. Co., Ltd
 * @author       huangqingjian/0050
 * @version      1.0  2014年7月8日
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
import com.qing.common.util.StringUtil;
import com.work.domain.LotteryPrizeSet;
import com.work.service.lottery.ILotteryPrizeService;
import com.work.vo.LotteryPrizeSetVo;

/**
 * 奖项设置实现类
 * (功能详细描述)
 * @author       huangqingjian/0050
 * @see          [相关类,可选,也可多条,对于重要的类或接口建议注释]
 * @since        二维码系统, 2014年7月8日
 */
@Service
@Repository
public class LotteryPrizeServiceImpl implements ILotteryPrizeService {
	@Resource
	HibernateDao<LotteryPrizeSet, String> dao;

	/**
	 * 保存或修改
	 * (功能详细描述)
	 * @see com.sh.service.ILotteryPrizeService#saveOrUpdate(com.sh.dao.domain.LotteryPrizeSet)
	 * @author       huangqingjian/0050
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        二维码系统, 2014年7月8日
	 * @param lottery
	 * @throws ServiceException
	 */
	@Override
	public void saveOrUpdate(LotteryPrizeSet Prize) throws ServiceException {
		try {
			dao.saveOrUpdate(Prize);
		} catch (DaoException e) {
			ExceptionHandle.throwServiceException(e, ErrorCode.DAO_ERROR, "LotteryPrizeServiceImpl.saveOrUpdate");
		}

	}

	/**
	 * 根据ID获取奖项
	 * (功能详细描述)
	 * @see com.sh.service.ILotteryPrizeService#getLotteryPrizeSetByID(java.lang.String)
	 * @author       huangqingjian/0050
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        二维码系统, 2014年7月8日
	 * @param id
	 * @return
	 * @throws ServiceException
	 */
	@Override
	public LotteryPrizeSet getLotteryPrizeSetByID(String id) throws ServiceException {
		LotteryPrizeSet prizeSet = null;
		try {
			prizeSet = dao.findById(LotteryPrizeSet.class, id);
		} catch (DaoException e) {
			ExceptionHandle.throwServiceException(e, ErrorCode.DAO_ERROR,
					"LotteryPrizeServiceImpl.getLotteryPrizeSetByID");
		}
		return prizeSet;
	}

	/**
	 * 条件查询
	 * (功能详细描述)
	 * @see com.sh.service.ILotteryPrizeService#queryLotteryPage(com.sh.vo.LotteryPrizeSetVo)
	 * @author       huangqingjian/0050
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        二维码系统, 2014年7月8日
	 * @param vo
	 * @return
	 * @throws ServiceException
	 */
	@Override
	public List<LotteryPrizeSet> queryLotteryPrizeSet(LotteryPrizeSetVo vo) throws ServiceException {
		StringBuilder builder = new StringBuilder();
		List<Object> parm = new ArrayList<Object>();
		List<LotteryPrizeSet> records = null;
		builder.append(" from LotteryPrizeSet where 1=1 ");
		builder.append(generateHQLCondition(vo, parm));
		builder.append(" order by totalNum asc");
		try {
			records = dao.findByValues(builder.toString(), parm.toArray(), false);
		} catch (DaoException e) {
			ExceptionHandle.throwServiceException(e, ErrorCode.DAO_ERROR,
					"LotteryPrizeServiceImpl.queryLotteryRecordByPage");
		}
		return records;
	}

	/**
	 * 获取活动中奖项的总共可用数量
	 * @see com.sh.service.ILotteryPrizeService#queryTotalPrize(java.lang.String)
	 * @author       huangqingjian/0050
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        二维码系统, 2014年7月10日
	 * @param lotteryId
	 * @return
	 * @throws ServiceException
	 */
	@Override
	public int queryTotalPrize(String lotteryId) throws ServiceException {
		StringBuilder builder = new StringBuilder();
		builder.append("select sum(totalNum) - sum(usedNum) from LotteryPrizeSet where lotteryId=?");
		try {
			return dao.findNumByHQL(builder.toString(), new Object[] { lotteryId });
		} catch (DaoException e) {
			ExceptionHandle.throwServiceException(e, ErrorCode.DAO_ERROR, "LotteryPrizeServiceImpl.queryTotalPrize");
		}
		return 0;
	}

	/**
	 * 构建查询条件
	 * (一句话功能简述)
	 * (功能详细描述)
	 * @author       huangqingjian/0050
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        二维码系统, 2014年7月8日
	 * @param vo
	 * @param parm
	 * @return
	 */
	private StringBuilder generateHQLCondition(LotteryPrizeSetVo vo, List<Object> parm) {
		StringBuilder builder = new StringBuilder();
		//奖项名称
		if (StringUtil.isValidStr(vo.getPrizeName())) {
			builder.append(" and prizeName like ?");
			parm.add("%" + vo.getPrizeName() + "%");
		}
		//活动ID
		if (StringUtil.isValidStr(vo.getLotteryId())) {
			builder.append(" and lottery.lotteryId =?");
			parm.add(vo.getLotteryId());
		}
		//礼品ID
		if (StringUtil.isValidStr(vo.getLotteryGiftId())) {
			builder.append(" and lotteryGift.giftId =?");
			parm.add(vo.getLotteryGiftId());
		}

		return builder;
	}

	/**
	 * 删除一项
	 * (功能详细描述)
	 * @see com.sh.service.ILotteryPrizeService#deleteLottery(com.sh.dao.domain.Lottery)
	 * @author       huangqingjian/0050
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        二维码系统, 2014年7月8日
	 * @param lotterie
	 * @throws ServiceException
	 */
	@Override
	public void deleteLotteryPrizeSet(LotteryPrizeSet prizeSet) throws ServiceException {
		try {
			dao.remove(prizeSet);
		} catch (DaoException e) {
			ExceptionHandle.throwServiceException(e, ErrorCode.DAO_ERROR,
					"LotteryPrizeServiceImpl.deleteLotteryPrizeSet");
		}

	}

	/**
	 * 批量删除奖项设置
	 * 覆盖方法/实现方法(选择其一)
	 * (功能详细描述)
	 * @see com.sh.service.ILotteryPrizeService#deleteLotteryPrizeSet(java.util.List)
	 * @author       huangqingjian/0050
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        二维码系统, 2014年7月11日
	 * @param prizeSets
	 * @throws ServiceException
	 */
	@Override
	public void deleteLotteryPrizeSet(List<LotteryPrizeSet> prizeSets) throws ServiceException {
		try {
			dao.removeAll(prizeSets);
		} catch (DaoException e) {
			ExceptionHandle.throwServiceException(e, ErrorCode.DAO_ERROR,
					"LotteryPrizeServiceImpl.deleteLotteryPrizeSet");
		}

	}
}
