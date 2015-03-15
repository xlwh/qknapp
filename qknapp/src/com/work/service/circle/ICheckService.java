/*
 * Title:        清清网系统 2014年8月16日
 * Description:  实践圈的的一些权限检查以及控制
 * Copyright:    Copyright (c) 2014
 * Company:      清清网
 * @author       张洪斌
 * @version      2.0  2014年8月16日
 */
package com.work.service.circle;

import javax.servlet.http.HttpServletRequest;

import com.work.domain.MemberInfo;
import com.work.domain.Message;


/**
 * 实践圈的的一些权限检查以及控制
 * 
 * @author      张洪斌
 * @see          [相关类,可选,也可多条,对于重要的类或接口建议注释]
 * @since        清清网系统, 2014年8月16日
 */
public interface ICheckService
{
	/**
	 * 检查是否是游客还是登录的会员(检查session)
	 * @return 检查结果
	 */
	
	public boolean isLogin(HttpServletRequest request);
	
	
	/**
	 * 检查这个用户是否可以评论
	 * （1）检查是否已经登录
	 * (2)检查动态是否允许评论
	 * @parame  user     会员
	 * @message message  要检查的动态
	 */
	public boolean discussable(MemberInfo user,Message message);
}
