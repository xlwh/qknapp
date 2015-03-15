/*
 * Title:        清清网系统 2014年8月16日
 * Description:  分享转发实践圈动态相关业务逻辑接口
 * Copyright:    Copyright (c) 2014
 * Company:      清清网
 * @author       张洪斌
 * @version      2.0  2014年8月16日
 */
package com.work.service.circle;

import com.work.domain.Message;
import com.work.domain.MemberInfo;

/**
 * 分享转发实践圈动态相关业务逻辑接口
 * 
 * @author       张洪斌
 * @see          [相关类,可选,也可多条,对于重要的类或接口建议注释]
 * @since        清清网系统, 2014年8月16日
 */
public interface IShareService
{
     /**
      * 分享到qq空间
      * @parame user     分享者
      * @parame message  分享的动态
      * @return          是否分享成功
      */
	
	public boolean shareToQQZone(MemberInfo user,Message message);
	
	 /**
     * 分享到人人
     * @parame user     分享者
     * @parame message  分享的动态
     * @return          是否分享成功
     */
	
	public boolean shareToRenren(MemberInfo user,Message message);
	
	
	/**
     * 分享到微信朋友圈
     * @parame user     分享者
     * @parame message  分享的动态
     * @return          是否分享成功
     */
	
	public boolean shareToWeixin(MemberInfo user,Message message);
	
	
	/**
     * 分享到微信新浪微博
     * @parame user     分享者
     * @parame message  分享的动态
     * @return          是否分享成功
     */
	
	public boolean shareToSina(MemberInfo user,Message message);
	
	
}
