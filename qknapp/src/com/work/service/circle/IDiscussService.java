/*
 * Title:        清清网系统 2014年8月16日
 * Description:  实践圈评论点赞等相关业务逻辑接口
 * Copyright:    Copyright (c) 2014
 * Company:      清清网
 * @author       张洪斌
 * @version      2.0  2014年8月16日
 */
package com.work.service.circle;

import com.qing.common.exception.DaoException;
import com.work.domain.Message;
import com.work.domain.MemberInfo;

/**
 * 实践圈评论点赞等相关业务逻辑接口
 * 
 * @author        张洪斌
 * @see          com.qing.cicre.domain.Discuss
 * @since        清清网系统, 2014年8月16日
 */
public interface IDiscussService
{
	/**
	 * 游客点赞（以什么样的一种方式来记录游客呢？）
	 * @param    ?
	 * @param     点赞的动态
	 * @return 点赞是否成功
	 * 
	 * 
	 */
	public void ykDz(Message messageid) throws DaoException ;
	
	/**
	 * 登录会员点赞
	 * @param    user  点赞者
	 * @param     点赞的动态
	 * @return          点赞是否成功 
	 * 
	 */
	
	public void userDz(MemberInfo user,Message message) throws DaoException;
	
	
	/*
	 * 登录会员评论
	 * @param  user   评论者
	 * @param  message  评论的动态
	 * @return           评论是否成功
	 * 
	 */
	
	public void userDisscuss(MemberInfo user,String content,Message message)  throws DaoException;
	
	
	/*
	 * 查询某个会员的点赞数量
	 * @param user     会员
	 * @return          总的点赞数
	 */
	
	public int getDzNum(MemberInfo user);
	
	/*
	 * 清除某个会员积累的点赞数目（兑换奖励和清除）
	 * @param  user    会员
	 * 
	 */
	
	public void clearDzNum(MemberInfo user);
	
}
