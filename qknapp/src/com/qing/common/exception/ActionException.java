package com.qing.common.exception;

/**
 * Title: ActionException <br>
 * Description: 页面控制模块异常类 <br>
 * CreateTime: 2012-08-07 16:39 <br>
 * Copyright: Copyright (c) 2012 taiheiot.com<br>
 * 
 * @author guojun
 * @since 1.0
 * @see BaseException
 */
public class ActionException extends BaseException
{

    private static final long serialVersionUID = -7553293136071625348L;

    public ActionException()
    {
    }

    public ActionException(String message)
    {
        super(message);
    }

}
