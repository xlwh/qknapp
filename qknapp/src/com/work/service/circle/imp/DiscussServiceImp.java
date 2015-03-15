/*
 * Title:        清清网系统 2014年8月18日
 * Description:  实践圈评论相关的业务逻辑实现
 * Copyright:    Copyright (c) 2014
 * Company:      清清网
 * @author       zhanghongbin
 * @version      2.0  2014年8月18日
 */
package com.work.service.circle.imp;

import java.util.Date;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.qing.common.exception.DaoException;
import com.work.dao.circle.IDiscussDao;
import com.work.domain.Discuss;
import com.work.domain.MemberInfo;
import com.work.domain.Message;
import com.work.service.circle.IDiscussService;

/**
 * (一句话功能简述)
 * (功能详细描述)
 * @author       zhanghongbin/0050
 * @see          [相关类,可选,也可多条,对于重要的类或接口建议注释]
 * @since        清清网系统, 2014年8月18日
 */

@Service
@Repository
public class DiscussServiceImp implements IDiscussService {

	@Resource
	private Discuss discuss;
	@Resource
	private IDiscussDao discussdao;
	/**
	 * 覆盖方法/实现方法(选择其一)
	 * (功能详细描述)
	 * @see com.work.service.circle.IDiscussService#ykDz(com.work.domain.Message)
	 * @author       zhanghongbin/0050
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        清清网系统, 2014年8月18日
	 * @param message
	 * @return
	 * @throws DaoException 
	 */
	@Override
	public void ykDz(Message message) throws DaoException {
		discuss.setCreatetime(new Date());
		discuss.setIsnew(0);
		discuss.setType(0);
		discussdao.saveDiscuss(discuss);
		
	}

	/**
	 * 覆盖方法/实现方法(选择其一)
	 * 登陆用户点赞
	 * @see com.work.service.circle.IDiscussService#userDz(com.work.domain.MemberInfo, com.work.domain.Message)
	 * @author       zhanghongbin/0050
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        清清网系统, 2014年8月18日
	 * @param user
	 * @param message
	 * @return
	 */
	@Override
	public void userDz(MemberInfo user, Message message) throws DaoException {
		discuss.setCreatetime(new Date());
		discuss.setIsnew(0);
		discuss.setType(0);
		discuss.setUid(user.getMemberId());
		discussdao.saveDiscuss(discuss);
		
	}

	/**
	 * 覆盖方法/实现方法(选择其一)
	 * 用户评论
	 * @see com.work.service.circle.IDiscussService#userDisscuss(com.work.domain.MemberInfo, com.work.domain.Message)
	 * @author       zhanghongbin/0050
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        清清网系统, 2014年8月18日
	 * @param user
	 * @param message
	 * @return
	 */
	@Override
	public void userDisscuss(MemberInfo user,String content, Message message)  throws DaoException{
		discuss.setCreatetime(new Date());
		discuss.setContent(content);
		discuss.setIsnew(0);
		discuss.setType(1);
		discuss.setUid(user.getMemberId());
		discussdao.saveDiscuss(discuss);
		
	}

	/**
	 * 覆盖方法/实现方法(选择其一)
	 * (功能详细描述)
	 * @see com.work.service.circle.IDiscussService#getDzNum(com.work.domain.MemberInfo)
	 * @author       zhanghongbin/0050
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        清清网系统, 2014年8月18日
	 * @param user
	 * @return
	 */
	@Override
	public int getDzNum(MemberInfo user) {
		
		return 0;
	}

	/**
	 * 覆盖方法/实现方法(选择其一)
	 * (功能详细描述)
	 * @see com.work.service.circle.IDiscussService#clearDzNum(com.work.domain.MemberInfo)
	 * @author       zhanghongbin/0050
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        清清网系统, 2014年8月18日
	 * @param user
	 */
	@Override
	public void clearDzNum(MemberInfo user) {
		

	}

	
	public Discuss getDiscuss() {
		return discuss;
	}

	
	public void setDiscuss(Discuss discuss) {
		this.discuss = discuss;
	}
	
	

}
