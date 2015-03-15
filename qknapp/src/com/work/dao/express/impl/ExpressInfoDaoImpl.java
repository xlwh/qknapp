/*
 * Title:        美芝澳二维码系统 2014年1月23日
 * Description:  [描述模块的功能、作用、使用方法和注意事项]
 * Copyright:    Copyright (c) 2012
 * Company:      Tai woo. Co., Ltd
 * @author       huangqingjian/0050
 * @version      1.0  2014年1月23日
 */
package com.work.dao.express.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.qing.common.base.dao.HibernateDao;
import com.qing.common.exception.DaoException;
import com.work.dao.express.ExpressInfoDaoAble;
import com.work.domain.ExpressDetail;
import com.work.domain.ExpressInfo;
import com.work.domain.SenderInfo;

/**
 * (一句话功能简述)
 * (功能详细描述)
 * @author       huangqingjian/0050
 * @see          [相关类,可选,也可多条,对于重要的类或接口建议注释]
 * @since        美芝澳二维码系统, 2014年1月23日
 */
@Service
@Repository
public class ExpressInfoDaoImpl implements ExpressInfoDaoAble {
	@Resource
	private HibernateDao<ExpressInfo, String> expressInfoDao;
	@Resource
	private HibernateDao<ExpressDetail, String> expressDetailDao;
	@Resource
	private HibernateDao<SenderInfo, String> senderInfoDao;

	/**
	 * 覆盖方法/实现方法(选择其一)
	 * (功能详细描述)
	 * @see com.work.dao.express.impl.mzo.dao.ExpressInfoDaoAble#getALLExpressInfos()
	 * @author       huangqingjian/0050
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        美芝澳二维码系统, 2014年1月23日
	 * @return
	 * @throws DaoException 
	 */
	@Override
	public List<ExpressInfo> getALLExpressInfos() throws DaoException {
		String hql = "from ExpressInfo order by defaultSet desc";

		return expressInfoDao.findByValues(hql, null, false);
	}

	/**
	 * 设置默认模板
	 * 覆盖方法/实现方法(选择其一)
	 * (功能详细描述)
	 * @see com.work.dao.express.impl.mzo.dao.ExpressInfoDaoAble#updateExpressDefaultSet(java.lang.String)
	 * @author       huangqingjian/0050
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        美芝澳二维码系统, 2014年1月25日
	 * @param id
	 * @throws DaoException
	 */
	@Override
	public void updateExpressDefaultSet(String id) throws DaoException {
		String hql = "UPDATE T_EXPRESS_INFO SET DEFAULT_SET = 0  ";

		expressInfoDao.excuteSQL(hql, null);
		String hqlDefault = " UPDATE T_EXPRESS_INFO SET DEFAULT_SET = 1 where EXPRESS_ID=? ";
		expressInfoDao.excuteSQL(hqlDefault, new Object[] { id });

	}

	/**
	 * 设置模板偏移量
	 * 覆盖方法/实现方法(选择其一)
	 * (功能详细描述)
	 * @see com.work.dao.express.impl.mzo.dao.ExpressInfoDaoAble#updateExpressOffSet(java.lang.String, java.lang.Integer, java.lang.Integer)
	 * @author       huangqingjian/0050
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        美芝澳二维码系统, 2014年2月7日
	 * @param id
	 * @param widthOffset
	 * @param heightOffset
	 * @throws DaoException
	 */
	@Override
	public void updateExpressOffSet(String id, Integer widthOffset, Integer heightOffset) throws DaoException {

		String hqlDefault = " UPDATE T_EXPRESS_INFO SET WIDTH_OFFSET = ?,HEIGHT_OFFSET=? WHERE EXPRESS_ID=? ";
		expressInfoDao.excuteSQL(hqlDefault, new Object[] { widthOffset, heightOffset, id });

	}

	/**
	 * 覆盖方法/实现方法(选择其一)
	 * (功能详细描述)
	 * @see com.work.dao.express.impl.mzo.dao.ExpressInfoDaoAble#getExpressInfo(java.lang.String)
	 * @author       huangqingjian/0050
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        美芝澳二维码系统, 2014年1月23日
	 * @param id
	 * @return
	 * @throws DaoException 
	 */
	@Override
	public ExpressInfo getExpressInfo(String id) throws DaoException {
		ExpressInfo expressInfo = expressInfoDao.findById(ExpressInfo.class, id);
		return expressInfo;
	}

	/**
	 * 获取默认模板
	 * 覆盖方法/实现方法(选择其一)
	 * (功能详细描述)
	 * @see com.work.dao.express.impl.mzo.dao.ExpressInfoDaoAble#getDefaultExpressInfo(java.lang.String)
	 * @author       huangqingjian/0050
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        美芝澳二维码系统, 2014年2月21日
	 * @param id
	 * @return
	 * @throws DaoException
	 */
	@Override
	public ExpressInfo getDefaultExpressInfo() throws DaoException {

		String hql = "from ExpressInfo where defaultSet=1";

		List<ExpressInfo> expressInfos = expressInfoDao.findByValues(hql, null, false);
		ExpressInfo expressInfo = null;
		if (null != expressInfos && expressInfos.size() > 0) {
			expressInfo = expressInfos.get(0);
		}
		return expressInfo;
	}

	/**
	 * 根据模板ID获取快递单上的字段信息
	 * (一句话功能简述)
	 * (功能详细描述)
	 * @author       huangqingjian/0050
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        美芝澳二维码系统, 2014年1月24日
	 * @param expressId
	 * @return
	 * @throws DaoException
	 */
	@Override
	public List<ExpressDetail> getExpressDetail(String expressId) throws DaoException {
		String hql = "from ExpressDetail where expressId = ? order by fieldShow desc,fieldLeft,fieldTop";
		List<ExpressDetail> details = expressDetailDao.findByValues(hql, new Object[] { expressId }, false);
		return details;
	}

	/**
	 * 保存模板详细设置
	 * 覆盖方法/实现方法(选择其一)
	 * (功能详细描述)
	 * @see com.work.dao.express.impl.mzo.dao.ExpressInfoDaoAble#saveOrUpdateExpressDetails(java.util.List)
	 * @author       huangqingjian/0050
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        美芝澳二维码系统, 2014年2月7日
	 * @param details
	 * @throws DaoException
	 */
	@Override
	public void saveOrUpdateExpressDetails(List<ExpressDetail> details) throws DaoException {
		expressDetailDao.saveOrUpdateAll(details);
	}

	/**
	 * 获取默认的发货地址
	 * 覆盖方法/实现方法(选择其一)
	 * (功能详细描述)
	 * @see com.work.dao.express.impl.mzo.dao.ExpressInfoDaoAble#getDefaultSenderInfo()
	 * @author       huangqingjian/0050
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        美芝澳二维码系统, 2014年2月21日
	 * @return
	 * @throws DaoException
	 */
	@Override
	public SenderInfo getDefaultSenderInfo() throws DaoException {
		String hql = "from SenderInfo where defaultSet=1";

		List<SenderInfo> senderInfos = senderInfoDao.findByValues(hql, null, false);
		SenderInfo info = null;
		if (null != senderInfos && senderInfos.size() > 0) {
			info = senderInfos.get(0);
		}
		return info;
	}
}
