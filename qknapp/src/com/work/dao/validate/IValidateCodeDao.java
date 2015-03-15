/*
 * Title:        清清网系统 2014年8月18日
 * Description:  [描述模块的功能、作用、使用方法和注意事项]
 * Copyright:    Copyright (c) 2014
 * Company:      清清网
 * @author       Sandy Wong
 * @version      2.0  2014年8月18日
 */
package com.work.dao.validate;

import java.util.Date;

import com.qing.common.exception.DaoException;
import com.work.domain.ValidateCode;

/**
 * 验证码Dao接口
 * @author       Sandy Wong
 * @see          [相关类,可选,也可多条,对于重要的类或接口建议注释]
 * @since        清清网系统, 2014年8月18日
 */
public interface IValidateCodeDao {

	/**
	 * 保存验证码
	 * @author       Sandy Wong
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        清清网系统, 2014年8月18日
	 * @param code
	 * @throws DaoException
	 */
	void saveOrUpdateCode(ValidateCode code) throws DaoException;

	/**
	 * 删除某个验证码
	 * @author       Sandy Wong
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        清清网系统, 2014年8月18日
	 * @param code
	 * @throws DaoException
	 */
	void deleteCode(ValidateCode code) throws DaoException;
	
	/**
	 * 获取验证码
	 * @author       Sandy Wong
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        清清网系统, 2014年8月18日
	 * @param code
	 * @throws DaoException
	 */
	ValidateCode queryValidateCode(ValidateCode code) throws DaoException;
	
	/**
	 * 删除过期验证码
	 * @author       Sandy Wong
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        清清网系统, 2014年8月18日
	 * @param expireTime
	 * @throws DaoException
	 */
	void deleteExpireCode(Date expireTime) throws DaoException;

	/**
	 * 获取生成验证码的次数
	 * @author       Sandy Wong
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        清清网系统, 2014年9月15日
	 * @param code
	 * @return
	 * @throws DaoException
	 */
	int getSendCount(ValidateCode code) throws DaoException;
}
