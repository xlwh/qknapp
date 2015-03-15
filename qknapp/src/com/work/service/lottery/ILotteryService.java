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
import com.qing.common.util.Page;
import com.work.domain.Lottery;
import com.work.domain.LotteryUserSet;
import com.work.vo.LotteryUserSetVo;
import com.work.vo.LotteryVo;

/**
 * 抽奖活动接口
 * (功能详细描述)
 * @author       huangqingjian/0050
 * @see          [相关类,可选,也可多条,对于重要的类或接口建议注释]
 * @since        二维码系统, 2014年7月4日
 */
public interface ILotteryService {
	/**
	 * 新增或保存抽奖活动
	 * (一句话功能简述)
	 * (功能详细描述)
	 * @author       huangqingjian/0050
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        二维码系统, 2014年7月4日
	 * @param lottery
	 */
	public void saveOrUpdateLottery(Lottery lottery) throws ServiceException;;

	/**
	 * 获取指定抽奖活动根据ID
	 * (一句话功能简述)
	 * (功能详细描述)
	 * @author       huangqingjian/0050
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        二维码系统, 2014年7月4日
	 * @param id
	 */
	public Lottery getLotterByID(String id) throws ServiceException;;

	/**
	 * 根据产品ID获取抽奖活动
	 * (一句话功能简述)
	 * (功能详细描述)
	 * @author       huangqingjian/0050
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        二维码系统, 2014年7月4日
	 * @param productId
	 * @return
	 */
	public Lottery getLotterByProductId(String productId) throws ServiceException;;

	/**
	 * 分页查询
	 * (一句话功能简述)
	 * (功能详细描述)
	 * @author       huangqingjian/0050
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        二维码系统, 2014年7月4日
	 * @return
	 */
	public void queryLotteryPage(Page<Lottery> page, LotteryVo vo) throws ServiceException;;

	/**
	 * 激活与禁用抽奖活动
	 * (一句话功能简述)
	 * (功能详细描述)
	 * @author       huangqingjian/0050
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        二维码系统, 2014年7月4日
	 */
	public void updateLotteryStatus(Lottery lottery) throws ServiceException;;

	/**
	 * 批量删除抽奖活动
	 * (一句话功能简述)
	 * (功能详细描述)
	 * @author       huangqingjian/0050
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        二维码系统, 2014年7月4日
	 * @param lotteries
	 */
	public void deleteLotterys(List<Lottery> lotteries) throws ServiceException;

	/**
	 * 删除单个活动
	 * (一句话功能简述)
	 * (功能详细描述)
	 * @author       huangqingjian/0050
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        二维码系统, 2014年7月7日
	 * @param lotterie
	 * @throws ServiceException
	 */
	void deleteLottery(Lottery lotterie) throws ServiceException;

	/**
	 * 保存活动中用户的可用抽奖次数
	 * (一句话功能简述)
	 * (功能详细描述)
	 * @author       huangqingjian/0050
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        二维码系统, 2014年7月9日
	 * @param userSet
	 */
	public void saveOrupdateLotteryUserSet(LotteryUserSet userSet) throws ServiceException;

	/**
	 * 获取已激活的活动
	 * (一句话功能简述)
	 * (功能详细描述)
	 * @author       huangqingjian/0050
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        二维码系统, 2014年7月9日
	 * @return
	 */
	public Lottery getLotteryByStatus(LotteryVo vo) throws ServiceException;

	/**
	 * 
	 * 查询用户抽奖机会
	 * (一句话功能简述)
	 * (功能详细描述)
	 * @author       huangqingjian/0050
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        二维码系统, 2014年7月10日
	 * @param vo
	 * @return
	 * @throws ServiceException
	 */
	List<LotteryUserSet> queryUserSets(LotteryUserSetVo vo) throws ServiceException;

}
