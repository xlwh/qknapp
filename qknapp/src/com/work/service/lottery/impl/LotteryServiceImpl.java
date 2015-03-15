/*
 * Title:        二维码系统 2014年7月4日
 * Description:  [描述模块的功能、作用、使用方法和注意事项]
 * Copyright:    Copyright (c) 2012
 * Company:      Tai woo. Co., Ltd
 * @author       huangqingjian/0050
 * @version      1.0  2014年7月4日
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
import com.qing.common.util.DateTimeUtils;
import com.qing.common.util.ErrorCode;
import com.qing.common.util.Page;
import com.qing.common.util.StringUtil;
import com.work.domain.Lottery;
import com.work.domain.LotteryPrizeSet;
import com.work.domain.LotteryUserSet;
import com.work.service.lottery.ILotteryPrizeService;
import com.work.service.lottery.ILotteryService;
import com.work.vo.LotteryPrizeSetVo;
import com.work.vo.LotteryUserSetVo;
import com.work.vo.LotteryVo;

/**
 * 抽奖管理类
 * (一句话功能简述)
 * (功能详细描述)
 * @author       huangqingjian/0050
 * @see          [相关类,可选,也可多条,对于重要的类或接口建议注释]
 * @since        二维码系统, 2014年7月4日
 */
@Service
@Repository
public class LotteryServiceImpl implements ILotteryService {
	@Resource
	HibernateDao<Lottery, String> lotteryDao;
	@Resource
	HibernateDao<LotteryUserSet, String> userSetDao;

	@Resource
	ILotteryPrizeService prizeService;

	/**
	 * 新增或修改抽奖活动
	 * 覆盖方法/实现方法(选择其一)
	 * (功能详细描述)
	 * @see com.sh.service.ILotteryService#saveOrUpdateLottery(com.sh.dao.domain.Lottery)
	 * @author       huangqingjian/0050
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        二维码系统, 2014年7月4日
	 * @param lottery
	 * @throws ServiceException 
	 */
	@Override
	public void saveOrUpdateLottery(Lottery lottery) throws ServiceException {
		try {
			lotteryDao.saveOrUpdate(lottery);
		} catch (DaoException e) {
			ExceptionHandle.throwServiceException(e, ErrorCode.DAO_SAVE_ERROR_001,
					"LotteryServiceImpl.saveOrUpdateLottery");
		}

	}

	/**
	 * 根据ID获取抽奖活动
	 * 覆盖方法/实现方法(选择其一)
	 * (功能详细描述)
	 * @see com.sh.service.ILotteryService#getLotterByID(java.lang.String)
	 * @author       huangqingjian/0050
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        二维码系统, 2014年7月4日
	 * @param id
	 * @return
	 */
	@Override
	public Lottery getLotterByID(String id) throws ServiceException {
		Lottery lottery = null;
		try {
			lottery = lotteryDao.findById(Lottery.class, id);
		} catch (DaoException e) {
			ExceptionHandle.throwServiceException(e, ErrorCode.DAO_SAVE_ERROR_001, "LotteryServiceImpl.getLotterByID");
		}
		return lottery;
	}

	/**
	 * 根据产品ID获取抽奖活动
	 * 覆盖方法/实现方法(选择其一)
	 * (功能详细描述)
	 * @see com.sh.service.ILotteryService#getLotterByProductId(java.lang.String)
	 * @author       huangqingjian/0050
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        二维码系统, 2014年7月4日
	 * @param productId
	 * @return
	 */
	@Override
	public Lottery getLotterByProductId(String productId) throws ServiceException {

		return null;
	}

	/**
	 * 分页查询抽奖活动
	 * 覆盖方法/实现方法(选择其一)
	 * (功能详细描述)
	 * @see com.sh.service.ILotteryService#queryLotteryPage(com.thwl.common.util.Page, com.sh.vo.LotteryVo)
	 * @author       huangqingjian/0050
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        二维码系统, 2014年7月4日
	 * @param page
	 * @param vo
	 * @return
	 */
	@Override
	public void queryLotteryPage(Page<Lottery> page, LotteryVo vo) throws ServiceException {
		StringBuilder builder = new StringBuilder();
		List<Object> parm = new ArrayList<Object>();
		builder.append(" from Lottery where 1=1 ");
		builder.append(generateHQLCondition(vo, parm));
		builder.append(" order by createDate desc");
		try {
			setPageSize(page, vo);
			lotteryDao.findByPage(page, builder.toString(), parm.toArray());
		} catch (DaoException e) {
			ExceptionHandle.throwServiceException(e, ErrorCode.DAO_SAVE_ERROR_001,
					"LotteryServiceImpl.queryLotteryPage");
		}
	}

	/**
	 * 设置分页总数
	 * (一句话功能简述)
	 * (功能详细描述)
	 * @author       huangqingjian/0050
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        二维码系统, 2014年7月4日
	 * @param page
	 * @param vo
	 * @throws DaoException 
	 */
	private void setPageSize(Page<Lottery> page, LotteryVo vo) throws DaoException {
		StringBuilder builder = new StringBuilder();
		List<Object> parm = new ArrayList<Object>();
		builder.append("select count(lotteryId)  from Lottery where 1=1 ");
		builder.append(generateHQLCondition(vo, parm));
		page.setTotalCount(lotteryDao.findNumByHQL(builder.toString(), parm.toArray()));
	}

	/**
	 * 构建分页查询条件
	 * (一句话功能简述)
	 * (功能详细描述)
	 * @author       huangqingjian/0050
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        二维码系统, 2014年7月4日
	 * @param vo
	 * @return
	 */
	private StringBuilder generateHQLCondition(LotteryVo vo, List<Object> parm) {
		StringBuilder builder = new StringBuilder();
		if (StringUtil.isValidStr(vo.getLotteryName())) {
			builder.append(" and lotteryName like ?");
			parm.add('%' + vo.getLotteryName() + '%');
		}
		if (StringUtil.isValidStr(vo.getBeginTime())) {
			builder.append(" and beginTime <= ?");
			parm.add(DateTimeUtils.parseDateYMDHMS(vo.getBeginTime()));
		}
		if (StringUtil.isValidStr(vo.getEndTime())) {
			builder.append(" and endTime >= ?");
			parm.add(DateTimeUtils.parseDateYMDHMS(vo.getEndTime()));
		}
		if (null != vo.getStatus()) {
			builder.append(" and status = ?");
			parm.add(vo.getStatus());
		}

		return builder;

	}

	/**
	 * 查询用户抽奖机会条件
	 * (一句话功能简述)
	 * (功能详细描述)
	 * @author       huangqingjian/0050
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        二维码系统, 2014年7月10日
	 * @param vo
	 * @param parm
	 * @return
	 */
	private StringBuilder generateHQLCondition(LotteryUserSetVo vo, List<Object> parm) {
		StringBuilder builder = new StringBuilder();
		if (StringUtil.isValidStr(vo.getLotteryId())) {
			builder.append(" and lotteryId = ?");
			parm.add(vo.getLotteryId());
		}
		if (StringUtil.isValidStr(vo.getCellPhone())) {
			builder.append(" and cellPhone = ?");
			parm.add(vo.getCellPhone());
		}
		if (StringUtil.isValidStr(vo.getPrintCodeInfo())) {
			builder.append(" and printCodeInfo = ?");
			parm.add(vo.getPrintCodeInfo());
		}
		if (StringUtil.isValidStr(vo.getCheckCode())) {
			builder.append(" and checkCode = ?");
			parm.add(vo.getCheckCode());
		}
		if (null != vo.getEffectiveTime()) {
			builder.append(" and effectiveTime >= ?");
			parm.add(vo.getEffectiveTime());
		}

		return builder;

	}

	/**
	 * 更新抽奖活动状态
	 * 覆盖方法/实现方法(选择其一)
	 * (功能详细描述)
	 * @see com.sh.service.ILotteryService#updateLotteryStatus(com.sh.dao.domain.Lottery)
	 * @author       huangqingjian/0050
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        二维码系统, 2014年7月4日
	 * @param lottery
	 */
	@Override
	public void updateLotteryStatus(Lottery lottery) throws ServiceException {
		try {
			//如果是激活，需要将其他活动禁用，活动只开启一个
			if (1 == lottery.getStatus().intValue()) {
				updateLotteryDisable();
			}
			lotteryDao.saveOrUpdate(lottery);
		} catch (DaoException e) {
			ExceptionHandle.throwServiceException(e, ErrorCode.DAO_SAVE_ERROR_001,
					"LotteryServiceImpl.updateLotteryStatus");
		}

	}

	/**
	 * 将所有活动置为禁用
	 * (一句话功能简述)
	 * (功能详细描述)
	 * @author       huangqingjian/0050
	 * @throws DaoException 
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        二维码系统, 2014年7月4日
	 */
	private void updateLotteryDisable() throws DaoException {
		String hql = "update Lottery set status=0";
		lotteryDao.update(hql, null);

	}

	/**
	 * 批量删除抽奖活动
	 * 覆盖方法/实现方法(选择其一)
	 * (功能详细描述)
	 * @see com.sh.service.ILotteryService#deleteLottery(java.util.List)
	 * @author       huangqingjian/0050
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        二维码系统, 2014年7月4日
	 * @param lotteries
	 */
	@Override
	public void deleteLotterys(List<Lottery> lotteries) throws ServiceException {
		try {
			lotteryDao.removeAll(lotteries);
		} catch (DaoException e) {
			ExceptionHandle.throwServiceException(e, ErrorCode.DAO_SAVE_ERROR_001, "LotteryServiceImpl.deleteLottery");
		}

	}

	@Override
	public void deleteLottery(Lottery lotterie) throws ServiceException {
		try {
			LotteryPrizeSetVo vo = new LotteryPrizeSetVo();
			vo.setLotteryId(lotterie.getLotteryId());
			List<LotteryPrizeSet> list = prizeService.queryLotteryPrizeSet(vo);
			//将关联的奖项也删除
			if (null != list && list.size() > 0) {
				prizeService.deleteLotteryPrizeSet(list);
			}
			lotteryDao.remove(lotterie);
		} catch (DaoException e) {
			ExceptionHandle.throwServiceException(e, ErrorCode.DAO_SAVE_ERROR_001, "LotteryServiceImpl.deleteLottery");
		}

	}

	/**
	 * 保存用户抽奖机会
	 * 覆盖方法/实现方法(选择其一)
	 * (功能详细描述)
	 * @see com.sh.service.ILotteryService#saveOrupdateLotteryUserSet(com.sh.dao.domain.LotteryUserSet)
	 * @author       huangqingjian/0050
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        二维码系统, 2014年7月9日
	 * @param userSet
	 * @throws ServiceException 
	 */
	@Override
	public void saveOrupdateLotteryUserSet(LotteryUserSet userSet) throws ServiceException {
		try {
			userSetDao.saveOrUpdate(userSet);
		} catch (DaoException e) {
			ExceptionHandle.throwServiceException(e, ErrorCode.DAO_SAVE_ERROR_001,
					"LotteryServiceImpl.saveOrupdateLotteryUserSet");
		}

	}

	/**
	 * 查询用户抽奖机会
	 * 覆盖方法/实现方法(选择其一)
	 * (功能详细描述)
	 * @see com.sh.service.ILotteryService#queryUserSets(com.sh.vo.LotteryUserSetVo)
	 * @author       huangqingjian/0050
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        二维码系统, 2014年7月10日
	 * @param vo
	 * @return
	 * @throws ServiceException
	 */
	@Override
	public List<LotteryUserSet> queryUserSets(LotteryUserSetVo vo) throws ServiceException {
		StringBuilder builder = new StringBuilder();
		List<Object> parm = new ArrayList<Object>();
		builder.append("from LotteryUserSet where usableNum > 0 ");
		builder.append(generateHQLCondition(vo, parm));
		List<LotteryUserSet> list = null;
		try {
			list = userSetDao.findByValues(builder.toString(), parm.toArray(), false);
		} catch (DaoException e) {
			ExceptionHandle.throwServiceException(e, ErrorCode.DAO_SAVE_ERROR_001, "LotteryServiceImpl.queryUserSets");
		}
		return list;
	}

	/**
	 * 根据状态查询激活的活动
	 * 覆盖方法/实现方法(选择其一)
	 * (功能详细描述)
	 * @see com.sh.service.ILotteryService#getLotteryByStatus()
	 * @author       huangqingjian/0050
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        二维码系统, 2014年7月9日
	 * @return
	 * @throws ServiceException 
	 */
	@Override
	public Lottery getLotteryByStatus(LotteryVo vo) throws ServiceException {
		Lottery lottery = null;
		StringBuilder builder = new StringBuilder();
		List<Object> parm = new ArrayList<Object>();
		builder.append("from Lottery where 1=1 ");
		builder.append(generateHQLCondition(vo, parm));
		try {
			List<Lottery> list = lotteryDao.findByValues(builder.toString(), parm.toArray(), false);

			if (null != list && list.size() > 0) {
				lottery = list.get(0);
			}
		} catch (DaoException e) {
			ExceptionHandle.throwServiceException(e, ErrorCode.DAO_SAVE_ERROR_001,
					"LotteryServiceImpl.getLotteryByStatus");
		}

		return lottery;
	}
}
