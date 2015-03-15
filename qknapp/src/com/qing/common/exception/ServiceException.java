package com.qing.common.exception;

/**
 * Title: ServiceException <br>
 * Description: 业务服务模块异常类 <br>
 * CreateTime: 2012-08-07 16:39 <br>
 * Copyright: Copyright (c) 2012 taiheiot.com<br>
 * 
 * @author guojun
 * @since 1.0
 * @see BaseException
 */
public class ServiceException extends BaseException
{

    private static final long serialVersionUID = -7086855509188011754L;

    public ServiceException()
    {
    }

    public ServiceException(String message)
    {
        super(message);
    }
}
