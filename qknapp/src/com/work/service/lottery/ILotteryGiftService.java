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
import com.work.domain.LotteryGift;
import com.work.vo.LotteryGiftVo;

/**
 * 奖品管理
 * (功能详细描述)
 * @author       huangqingjian/0050
 * @see          [相关类,可选,也可多条,对于重要的类或接口建议注释]
 * @since        二维码系统, 2014年7月4日
 */
public interface ILotteryGiftService {
	/**
	 * 新增或保存
	 * (一句话功能简述)
	 * (功能详细描述)
	 * @author       huangqingjian/0050
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        二维码系统, 2014年7月4日
	 */
	public void saveOrUpdate(LotteryGift gift) throws ServiceException;

	/**
	 * 批量删除礼品
	 * (一句话功能简述)
	 * (功能详细描述)
	 * @author       huangqingjian/0050
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        二维码系统, 2014年7月4日
	 * @param gifts
	 */
	public void deleteGifts(List<LotteryGift> gifts) throws ServiceException;

	/**
	 * 删除单个礼品
	 * (一句话功能简述)
	 * (功能详细描述)
	 * @author       huangqingjian/0050
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        二维码系统, 2014年7月7日
	 * @param gift
	 * @throws ServiceException
	 */
	public void deleteGift(LotteryGift gift) throws ServiceException;

	/**
	 * 根据ID获取礼品
	 * (一句话功能简述)
	 * (功能详细描述)
	 * @author       huangqingjian/0050
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        二维码系统, 2014年7月4日
	 * @param id
	 * @return
	 */
	public LotteryGift getGiftById(String id) throws ServiceException;

	/**
	 * 分页查询礼品列表
	 * (一句话功能简述)
	 * (功能详细描述)
	 * @author       huangqingjian/0050
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        二维码系统, 2014年7月4日
	 * @param page
	 * @param vo
	 */
	public void queryGiftByPage(Page<LotteryGift> page, LotteryGiftVo vo) throws ServiceException;

	/**
	 * 获取所有的礼品
	 * (一句话功能简述)
	 * (功能详细描述)
	 * @author       huangqingjian/0050
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        二维码系统, 2014年7月8日
	 * @return
	 */
	public List<LotteryGift> queryAll() throws ServiceException;
}
