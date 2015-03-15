/*
 * Title:        清清网系统 2014年8月18日
 * Description:  [描述模块的功能、作用、使用方法和注意事项]
 * Copyright:    Copyright (c) 2014
 * Company:      清清网
 * @author       Sandy Wong
 * @version      2.0  2014年8月18日
 */
package com.qing.common.util;

import java.util.Random;

import com.work.dao.validate.IValidateCodeDao;

/**
 * (一句话功能简述)
 * (功能详细描述)
 * @author       Sandy Wong
 * @see          [相关类,可选,也可多条,对于重要的类或接口建议注释]
 * @since        清清网系统, 2014年8月18日
 */
public class ValidateCodeUtil {

	private static IValidateCodeDao dao;
	
	/**
	 * @return Returns the dao.
	 */
	public IValidateCodeDao getDao() {
		return ValidateCodeUtil.dao;
	}
	/**
	 * @param dao The dao to set.
	 */
	public void setDao(IValidateCodeDao dao) {
		ValidateCodeUtil.dao = dao;
	}
	
	
	/**
	 * 生成6位随机数
	 * @author       Sandy Wong
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        清清网系统, 2014年8月18日
	 * @return
	 */
	public static String generateRandomCode() {
		Random random = new Random();

		int randomNum = random.nextInt(899999);
		randomNum = randomNum + 100000;

		String randomString = String.valueOf(randomNum);
		return randomString;
	}
}
