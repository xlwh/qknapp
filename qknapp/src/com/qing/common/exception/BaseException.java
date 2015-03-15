package com.qing.common.exception;

/**
 * Title: BaseException <br>
 * Description: 基础异常类<br>
 * CreateTime: 2012-08-07 16:39 <br>
 * Copyright: Copyright (c) 2012 taiheiot.com<br>
 * 
 * @author guojun
 * @since 1.0
 * @see Exception
 */
public class BaseException extends Exception {

	private static final long serialVersionUID = 3358888911029354719L;

	public BaseException() {
	}

	public BaseException(String message) {
		super(message);
	}

	public BaseException(String message, Throwable cause) {
		super(message, cause);
	}

}
