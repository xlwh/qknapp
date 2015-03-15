package com.qing.common.exception;

/**
 * Title: DataException <br>
 * Description: 数据异常类 <br>
 * CreateTime: 2012-08-07 16:39 <br>
 * Copyright: Copyright (c) 2012 taiheiot.com<br>
 * 
 * @author guojun
 * @since 1.0
 * @see BaseException
 */
public class DataException extends BaseException
{

    private static final long serialVersionUID = 7138217038057726081L;

    public DataException()
    {
    }

    public DataException(String message)
    {
        super(message);
    }

}
