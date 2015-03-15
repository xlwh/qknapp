/*
 * Title:        清清网系统 2014年8月19日
 * Description:  [描述模块的功能、作用、使用方法和注意事项]
 * Copyright:    Copyright (c) 2014
 * Company:      清清网
 * @author       Sandy Wong
 * @version      2.0  2014年8月19日
 */
package com.work.service.validate;

import com.qing.common.exception.DaoException;

/**
 * (一句话功能简述)
 * (功能详细描述)
 * @author       Sandy Wong
 * @see          [相关类,可选,也可多条,对于重要的类或接口建议注释]
 * @since        清清网系统, 2014年8月19日
 */
public interface IValidateCodeService {

	/**
	 * 生成验证码
	 * @author       Sandy Wong
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        清清网系统, 2014年9月15日
	 * @param sender
	 * @param function
	 * @return
	 * @throws DaoException
	 */
	String generateValidateCode(String sender, String function) throws DaoException;
	
	/**
	 * 检查验证码
	 * @author       Sandy Wong
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        清清网系统, 2014年9月15日
	 * @param sender
	 * @param code
	 * @param function
	 * @return
	 * @throws DaoException
	 */
	boolean checkValidateCode(String sender, String code, String function) throws DaoException;

	/**
	 * 检验是否超出生成验证码次数
	 * @author       Sandy Wong
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        清清网系统, 2014年9月15日
	 * @param sender
	 * @param function
	 * @return
	 * @throws DaoException
	 */
	boolean canGenerateValidateCode(String sender, String function) throws DaoException;
}
