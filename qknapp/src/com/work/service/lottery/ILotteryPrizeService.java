/*
 * Title:        二维码系统 2014年7月4日
 * Description:  [描述模块的功能、作用、使用方法和注意事项]
 * Copyright:    Copyright (c) 2012
 * Company:      Tai woo. Co., Ltd
 * @author       huangqingjian/0050
 * @version      1.0  2014年7月4日
 */
package com.work.service.lottery;

import java.util.List;

import com.qing.common.exception.ServiceException;
import com.work.domain.LotteryPrizeSet;
import com.work.vo.LotteryPrizeSetVo;

/**
 * 奖项设置接口
 * (功能详细描述)
 * @author       huangqingjian/0050
 * @see          [相关类,可选,也可多条,对于重要的类或接口建议注释]
 * @since        二维码系统, 2014年7月4日
 */
public interface ILotteryPrizeService {
	/**
	 * 新增或保存奖项
	 * (一句话功能简述)
	 * (功能详细描述)
	 * @author       huangqingjian/0050
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        二维码系统, 2014年7月4日
	 * @param lottery
	 */
	public void saveOrUpdate(LotteryPrizeSet prizeSet) throws ServiceException;

	/**
	 * 获取指定奖项根据ID
	 * (一句话功能简述)
	 * (功能详细描述)
	 * @author       huangqingjian/0050
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        二维码系统, 2014年7月4日
	 * @param id
	 */
	public LotteryPrizeSet getLotteryPrizeSetByID(String id) throws ServiceException;

	/**
	 * 条件查询
	 * (一句话功能简述)
	 * (功能详细描述)
	 * @author       huangqingjian/0050
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        二维码系统, 2014年7月4日
	 * @return
	 */
	public List<LotteryPrizeSet> queryLotteryPrizeSet(LotteryPrizeSetVo vo) throws ServiceException;

	/**
	 * 删除单个奖项
	 * (一句话功能简述)
	 * (功能详细描述)
	 * @author       huangqingjian/0050
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        二维码系统, 2014年7月7日
	 * @param lotterie
	 * @throws ServiceException
	 */
	public void deleteLotteryPrizeSet(LotteryPrizeSet prizeSet) throws ServiceException;

	/**
	 * 获取活动中奖项的总共可用数量
	 * @author       huangqingjian/0050
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        二维码系统, 2014年7月10日
	 * @param lotteryId
	 * @return
	 * @throws ServiceException 
	 */
	public int queryTotalPrize(String lotteryId) throws ServiceException;

	/**
	 * 批量删除
	 * @author       huangqingjian/0050
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        二维码系统, 2014年7月11日
	 * @param prizeSets
	 * @throws ServiceException
	 */
	void deleteLotteryPrizeSet(List<LotteryPrizeSet> prizeSets) throws ServiceException;

}
