/*
 * Title:        清清网系统 2014年10月10日
 * Description:  [描述模块的功能、作用、使用方法和注意事项]
 * Copyright:    Copyright (c) 2014
 * Company:      清清网
 * @author       Sandy Wong
 * @version      2.0  2014年10月10日
 */
package com.work.service.member;

import java.util.List;
import java.util.Map;

import com.qing.common.exception.ServiceException;
import com.qing.common.util.Page;
import com.work.domain.ScoreDetail;

/**
 * 积分设置service接口
 * @author       Sandy Wong
 * @see          [相关类,可选,也可多条,对于重要的类或接口建议注释]
 * @since        清清网系统, 2014年10月10日
 */
public interface IScoreDetailService {
	/**
	 * 保存积分明细
	 * @author       Sandy Wong
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        清清网系统, 2014年10月8日
	 * @param detail
	 * @throws ServiceException
	 */
	public void saveOrUpdateDetail(ScoreDetail detail) throws ServiceException;

	/**
	 * 根据ID获取积分明细
	 * @author       Sandy Wong
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        清清网系统, 2014年10月8日
	 * @param id
	 * @return
	 * @throws ServiceException
	 */
	public ScoreDetail getScoreDetail(String id) throws ServiceException;

	/**
	 * 查询积分明细
	 * @author       Sandy Wong
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        清清网系统, 2014年10月8日
	 * @param page
	 * @param detail
	 * @throws ServiceException
	 */
	public void queryScoreDetail(Page<ScoreDetail> page, ScoreDetail detail) throws ServiceException;

	/**
	 * 不分页查询积分明细
	 * @author       Sandy Wong
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        清清网系统, 2014年10月8日
	 * @param detail
	 * @return
	 * @throws ServiceException
	 */
	public List<ScoreDetail> queryScoreDetail(ScoreDetail detail) throws ServiceException;

	/**
	 * 获取条数
	 * @author       Sandy Wong
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        清清网系统, 2014年10月12日
	 * @param detail
	 * @param limitNum
	 * @return
	 * @throws ServiceException
	 */
	public List<Map<String, Object>> getRankOfDetail(ScoreDetail detail, Integer limitNum, ScoreDetail afterRanking) throws ServiceException;
	
	/**
	 * 分页查询排名信息
	 * @author       Sandy Wong
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        清清网系统, 2014年11月9日
	 * @param detail
	 * @param page
	 * @param afterSorting
	 * @throws ServiceException
	 */
	void getRankOfDetailWithPage(ScoreDetail detail, Page<Map<String, Object>> page, ScoreDetail afterRanking)
			throws ServiceException;
	
	/**
	 * 分页查询推荐有礼详情
	 * @author       Sandy Wong
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        清清网系统, 2014年11月9日
	 * @param page
	 * @param detail
	 * @throws ServiceException
	 */
	void queryRecommendDetail(Page<ScoreDetail> page, ScoreDetail detail) throws ServiceException;

	/**
	 * 不分页查询推荐有礼详情
	 * @author       Sandy Wong
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        清清网系统, 2014年11月9日
	 * @param detail
	 * @return
	 * @throws ServiceException
	 */
	List<ScoreDetail> queryRecommendDetail(ScoreDetail detail) throws ServiceException;
}
