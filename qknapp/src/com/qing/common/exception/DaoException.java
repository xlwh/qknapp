package com.qing.common.exception;

/**
 * Title: DaoException <br>
 * Description: 数据访问模块异常类 <br>
 * CreateTime: 2012-08-07 16:39 <br>
 * Copyright: Copyright (c) 2012 taiheiot.com<br>
 * 
 * @author guojun
 * @since 1.0
 * @see BaseException
 */
public class DaoException extends BaseException {

	private static final long serialVersionUID = 2357594864159475349L;

	public DaoException() {
	}

	public DaoException(String message) {
		super(message);
	}

	public DaoException(String message, Throwable cause) {
		super(message, cause);
	}
}
