/*
 * Title:        二维码系统 2014年7月5日
 * Description:  [描述模块的功能、作用、使用方法和注意事项]
 * Copyright:    Copyright (c) 2012
 * Company:      Tai woo. Co., Ltd
 * @author       huangqingjian/0050
 * @version      1.0  2014年7月5日
 */
package com.work.service.lottery;

import java.util.List;

import com.qing.common.exception.ServiceException;
import com.qing.common.util.Page;
import com.work.domain.LotteryPrizeSet;
import com.work.domain.LotteryRecord;
import com.work.domain.LotteryUserSet;
import com.work.vo.LotteryRecordVo;

/**
 * 中奖记录管理类
 * (一句话功能简述)
 * (功能详细描述)
 * @author       huangqingjian/0050
 * @see          [相关类,可选,也可多条,对于重要的类或接口建议注释]
 * @since        二维码系统, 2014年7月5日
 */
public interface ILotteryRecordService {

	/**
	 * 保存中奖记录
	 * (一句话功能简述)
	 * (功能详细描述)
	 * @author       huangqingjian/0050
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        二维码系统, 2014年7月5日
	 * @param record
	 */
	public void saveOrUpdate(LotteryRecord record, LotteryPrizeSet prizeSet, LotteryUserSet userSet)
			throws ServiceException;

	/**
	 * 保存中奖记录
	 * (一句话功能简述)
	 * (功能详细描述)
	 * @author       huangqingjian/0050
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        二维码系统, 2014年7月5日
	 * @param record
	 */
	public void saveOrUpdate(LotteryRecord record) throws ServiceException;

	/**
	 * 分页查询中奖记录
	 * (一句话功能简述)
	 * (功能详细描述)
	 * @author       huangqingjian/0050
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        二维码系统, 2014年7月5日
	 * @param page
	 * @param vo
	 */
	public void queryLotteryRecordByPage(Page<LotteryRecord> page, LotteryRecordVo vo) throws ServiceException;

	/**
	 * 根据查询条件查询中奖记录
	 * (一句话功能简述)
	 * (功能详细描述)
	 * @author       huangqingjian/0050
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        二维码系统, 2014年7月5日
	 * @param vo
	 * @return
	 */
	public List<LotteryRecord> queryLotteryRecord(LotteryRecordVo vo) throws ServiceException;

	/**
	 * 根据ID获取中奖记录
	 * (一句话功能简述)
	 * (功能详细描述)
	 * @author       huangqingjian/0050
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        二维码系统, 2014年7月5日
	 * @param id
	 * @return
	 */
	public LotteryRecord getLotteryRecordByID(String id) throws ServiceException;

	/**
	 * 根据查询条件获取总数
	 * (一句话功能简述)
	 * (功能详细描述)
	 * @author       huangqingjian/0050
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        二维码系统, 2014年7月7日
	 * @param vo
	 * @return
	 * @throws ServiceException
	 */
	int getTotalNum(LotteryRecordVo vo) throws ServiceException;
}
