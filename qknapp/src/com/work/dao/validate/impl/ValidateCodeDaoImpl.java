/*
 * Title:        清清网系统 2014年8月18日
 * Description:  [描述模块的功能、作用、使用方法和注意事项]
 * Copyright:    Copyright (c) 2014
 * Company:      清清网
 * @author       Sandy Wong
 * @version      2.0  2014年8月18日
 */
package com.work.dao.validate.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.qing.common.base.dao.HibernateDao;
import com.qing.common.exception.DaoException;
import com.qing.common.util.StringUtil;
import com.work.dao.validate.IValidateCodeDao;
import com.work.domain.ValidateCode;

/**
 * 验证码Dao实现
 * @author       Sandy Wong
 * @see          [相关类,可选,也可多条,对于重要的类或接口建议注释]
 * @since        清清网系统, 2014年8月18日
 */
@Repository
public class ValidateCodeDaoImpl implements IValidateCodeDao {
	
	@Resource
	HibernateDao<ValidateCode, String> hibernateDao;
	
	/**
	 * 
	 * 覆盖方法/实现方法(选择其一)
	 * (功能详细描述)
	 * @see com.work.dao.validate.IValidateCodeDao#saveOrUpdateInfo(com.work.domain.ValidateCode)
	 * @author       Sandy Wong
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        清清网系统, 2014年8月18日
	 * @param info
	 * @throws DaoException
	 */
	@Override
	public void saveOrUpdateCode(ValidateCode code) throws DaoException
	{
		if (code.getId() == null) {
			code.setCreateTime(new Date());
		}
		hibernateDao.save(code);
	}

	/**
	 * 覆盖方法/实现方法(选择其一)
	 * (功能详细描述)
	 * @see com.work.dao.validate.IValidateCodeDao#deleteCode(com.work.domain.ValidateCode)
	 * @author       Sandy Wong
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        清清网系统, 2014年8月18日
	 * @param code
	 * @throws DaoException
	 */
	@Override
	public void deleteCode(ValidateCode code) throws DaoException {
		hibernateDao.remove(code);
	}

	/**
	 * 覆盖方法/实现方法(选择其一)
	 * (功能详细描述)
	 * @see com.work.dao.validate.IValidateCodeDao#queryValidateCode(com.work.domain.ValidateCode)
	 * @author       Sandy Wong
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        清清网系统, 2014年8月18日
	 * @param code
	 * @throws DaoException
	 */
	@Override
	public ValidateCode queryValidateCode(ValidateCode code) throws DaoException {
		if (null == code || !StringUtil.isValidStr(code.getSender()) || !StringUtil.isValidStr(code.getCode())) {
			return null;
		}
		List<Object> params = new ArrayList<Object>();
		String hql = "from ValidateCode where sender=? and code=? and expireTime > ?";
		params.add(code.getSender().trim());
		params.add(code.getCode().trim());
		params.add(new Date());
		if (StringUtil.isValidStr(code.getFunction())) {
			hql += " and function=?";
			params.add(code.getFunction().trim());
		}
		
		List<ValidateCode> result = hibernateDao.findByValues(hql, params.toArray(), false);
		if (null == result || result.isEmpty()) {
			return null;
		}
		return result.get(0);
	}

	/**
	 * 覆盖方法/实现方法(选择其一)
	 * (功能详细描述)
	 * @see com.work.dao.validate.IValidateCodeDao#deleteExpireCode(java.sql.Date)
	 * @author       Sandy Wong
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        清清网系统, 2014年8月18日
	 * @param expireTime
	 * @throws DaoException
	 */
	@Override
	public void deleteExpireCode(Date expireTime) throws DaoException {
		String sql = "delete from T_VALIDATE_CODE where expireTime < ?";
		hibernateDao.excuteSQL(sql, new Object[]{expireTime});
	}
	
	@Override
	public int getSendCount(ValidateCode code) throws DaoException{
		List<Object> params = new ArrayList<Object>();
		String hql = "select count(id) from ValidateCode where sender=?";
		params.add(code.getSender().trim());
		if (StringUtil.isValidStr(code.getFunction())) {
			hql += " and function = ?";
			params.add(code.getFunction().trim());
		}
		if (code.getCreateTime() != null) {
			hql += " and createTime > ?";
			params.add(code.getCreateTime());
		}
		return hibernateDao.findNumByHQL(hql, params.toArray());
	}
}
