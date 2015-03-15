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
import com.qing.common.util.DateTimeUtils;
import com.qing.common.util.ErrorCode;
import com.qing.common.util.Page;
import com.qing.common.util.StringUtil;
import com.work.domain.LotteryPrizeSet;
import com.work.domain.LotteryRecord;
import com.work.domain.LotteryUserSet;
import com.work.service.lottery.ILotteryPrizeService;
import com.work.service.lottery.ILotteryRecordService;
import com.work.vo.LotteryRecordVo;

/**
 * 中奖记录管理
 * (一句话功能简述)
 * (功能详细描述)
 * @author       huangqingjian/0050
 * @see          [相关类,可选,也可多条,对于重要的类或接口建议注释]
 * @since        二维码系统, 2014年7月5日
 */
@Service
@Repository
public class LotteryRecordServiceImpl implements ILotteryRecordService {
	@Resource
	HibernateDao<LotteryRecord, String> dao;
	@Resource
	HibernateDao<LotteryPrizeSet, String> prizeDao;
	@Resource
	HibernateDao<LotteryUserSet, String> userSetDao;

	@Resource
	ILotteryPrizeService prizeService;

	/**
	 * 保存中奖记录，修改奖品数量和修改会员抽奖机会
	 * (功能详细描述)
	 * @see com.sh.service.ILotteryRecordService#saveOrUpdate(com.sh.dao.domain.LotteryRecord)
	 * @author       huangqingjian/0050
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        二维码系统, 2014年7月5日
	 * @param record
	 * @throws ServiceException
	 */
	@Override
	public void saveOrUpdate(LotteryRecord record, LotteryPrizeSet prizeSet, LotteryUserSet userSet)
			throws ServiceException {
		try {
			if (null != record) {
				dao.saveOrUpdate(record);
			}
			if (null != prizeSet) {
				prizeDao.saveOrUpdate(prizeSet);
			}
			if (null != userSet) {
				userSetDao.saveOrUpdate(userSet);
			}

		} catch (DaoException e) {
			ExceptionHandle.throwServiceException(e, ErrorCode.DAO_SAVE_ERROR_001,
					"LotteryRecordServiceImpl.saveOrUpdate");
		}

	}

	/**
	 * 只保存和修改中奖记录
	 * (功能详细描述)
	 * @see com.sh.service.ILotteryRecordService#saveOrUpdate(com.sh.dao.domain.LotteryRecord)
	 * @author       huangqingjian/0050
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        二维码系统, 2014年7月11日
	 * @param record
	 * @throws ServiceException
	 */
	@Override
	public void saveOrUpdate(LotteryRecord record) throws ServiceException {
		try {
			dao.saveOrUpdate(record);
		} catch (DaoException e) {
			ExceptionHandle.throwServiceException(e, ErrorCode.DAO_SAVE_ERROR_001,
					"LotteryRecordServiceImpl.saveOrUpdate");
		}

	}

	/**
	 * 分页查询中奖记录
	 * 覆盖方法/实现方法(选择其一)
	 * (功能详细描述)
	 * @see com.sh.service.ILotteryRecordService#queryLotteryRecordByPage(com.thwl.common.util.Page, com.sh.vo.LotteryRecordVo)
	 * @author       huangqingjian/0050
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        二维码系统, 2014年7月5日
	 * @param page
	 * @param vo
	 * @throws ServiceException
	 */
	@Override
	public void queryLotteryRecordByPage(Page<LotteryRecord> page, LotteryRecordVo vo) throws ServiceException {

		List<Object> parm = new ArrayList<Object>();
		StringBuilder builder = new StringBuilder();
		builder.append(" from LotteryRecord where 1=1 ");
		builder.append(generateHQLCondition(vo, parm));
		builder.append(" order by createDate desc");
		try {
			setPageSize(page, vo);
			dao.findByPage(page, builder.toString(), parm.toArray());
		} catch (DaoException e) {
			ExceptionHandle.throwServiceException(e, ErrorCode.DAO_SAVE_ERROR_001,
					"LotteryRecordServiceImpl.queryLotteryRecordByPage");
		}
	}

	/**
	 * 根据条件查询中奖记录
	 * 覆盖方法/实现方法(选择其一)
	 * (功能详细描述)
	 * @see com.sh.service.ILotteryRecordService#queryLotteryRecord(com.sh.vo.LotteryRecordVo)
	 * @author       huangqingjian/0050
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        二维码系统, 2014年7月5日
	 * @param vo
	 * @return
	 * @throws ServiceException
	 */
	@Override
	public List<LotteryRecord> queryLotteryRecord(LotteryRecordVo vo) throws ServiceException {
		StringBuilder builder = new StringBuilder();
		List<Object> parm = new ArrayList<Object>();
		List<LotteryRecord> records = null;
		builder.append(" from LotteryRecord where 1=1 ");
		builder.append(generateHQLCondition(vo, parm));
		builder.append(" order by createDate desc");
		try {
			records = dao.findByValues(builder.toString(), parm.toArray(), false);
		} catch (DaoException e) {
			ExceptionHandle.throwServiceException(e, ErrorCode.DAO_SAVE_ERROR_001,
					"LotteryRecordServiceImpl.queryLotteryRecordByPage");
		}
		return records;
	}

	/**
	 * 查询单个中奖记录
	 * 覆盖方法/实现方法(选择其一)
	 * (功能详细描述)
	 * @see com.sh.service.ILotteryRecordService#getLotteryRecordByID(java.lang.String)
	 * @author       huangqingjian/0050
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        二维码系统, 2014年7月5日
	 * @param id
	 * @return
	 */
	@Override
	public LotteryRecord getLotteryRecordByID(String id) throws ServiceException {

		LotteryRecord record = null;
		try {
			record = dao.findById(LotteryRecord.class, id);
		} catch (DaoException e) {
			ExceptionHandle.throwServiceException(e, ErrorCode.DAO_SAVE_ERROR_001,
					"LotteryRecordServiceImpl.getLotteryRecordByID");
		}
		return record;
	}

	/**
	 * 构建查询语句
	 * (一句话功能简述)
	 * (功能详细描述)
	 * @author       huangqingjian/0050
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        二维码系统, 2014年7月5日
	 * @param vo
	 * @return
	 * @throws ServiceException 
	 */
	private StringBuilder generateHQLCondition(LotteryRecordVo vo, List<Object> parm) throws ServiceException {
		StringBuilder builder = new StringBuilder();

		//联系号码，不一定是登录的手机号码
		if (StringUtil.isValidStr(vo.getCellPhone())) {
			builder.append(" and cellPhone = ?");
			parm.add((vo.getCellPhone()));
		}
		//联系人
		if (StringUtil.isValidStr(vo.getContacts())) {
			builder.append(" and contacts like ?");
			parm.add('%' + vo.getContacts() + '%');
		}
		//根据奖项查询中奖记录
		if (StringUtil.isValidStr(vo.getPrizeName())) {
			builder.append(" and lotteryPrizeSet.prizeName like ?");
			parm.add('%' + vo.getPrizeName() + '%');
			
		}
		//登录的手机号码
		if (StringUtil.isValidStr(vo.getCreator())) {
			builder.append(" and creator =?");
			parm.add(vo.getCreator());
		}
		//是否领取
		if (null != vo.getIsExpiry()) {
			builder.append(" and isExpiry =?");
			parm.add(vo.getIsExpiry());
		}
		//是否中奖
		if (null != vo.getIsWinned()) {
			builder.append(" and isWinned =?");
			parm.add(vo.getIsWinned());
		}
		//中奖时间
		if (StringUtil.isValidStr(vo.getCreateDate())) {
			builder.append(" and createDate >=?");
			parm.add(DateTimeUtils.parseDateYMDHMS(vo.getCreateDate()));
		}
		//中奖时间
		if (StringUtil.isValidStr(vo.getEndCreateDate())) {
			builder.append(" and createDate <=?");
			parm.add(DateTimeUtils.parseDateYMDHMS(vo.getEndCreateDate()));
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
	 * @throws ServiceException 
	 */
	private void setPageSize(Page<LotteryRecord> page, LotteryRecordVo vo) throws DaoException, ServiceException {
		StringBuilder builder = new StringBuilder();
		List<Object> parm = new ArrayList<Object>();
		builder.append("select count(lotId)  from LotteryRecord where 1=1 ");
		builder.append(generateHQLCondition(vo, parm));
		page.setTotalCount(dao.findNumByHQL(builder.toString(), parm.toArray()));
	}

	/**
	 * 提供给外部使用，查询总数
	 * 覆盖方法/实现方法(选择其一)
	 * (功能详细描述)
	 * @see com.sh.service.ILotteryRecordService#getTotalNum(com.sh.vo.LotteryRecordVo)
	 * @author       huangqingjian/0050
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        二维码系统, 2014年7月7日
	 * @param vo
	 * @return
	 * @throws ServiceException
	 */
	@Override
	public int getTotalNum(LotteryRecordVo vo) throws ServiceException {
		StringBuilder builder = new StringBuilder();
		List<Object> parm = new ArrayList<Object>();
		builder.append("select count(lotId)  from LotteryRecord where 1=1 ");
		builder.append(generateHQLCondition(vo, parm));
		try {
			return dao.findNumByHQL(builder.toString(), parm.toArray());
		} catch (DaoException e) {
			ExceptionHandle.throwServiceException(e, ErrorCode.DAO_SAVE_ERROR_001,
					"LotteryRecordServiceImpl.getTotalNum");
		}
		return 0;
	}

}
