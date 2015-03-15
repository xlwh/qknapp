package com.qing.common.exception;

import java.io.Serializable;

/**
 * Title: ClassInstantiateException <br>
 * Description: ClassInstantiateException类初始化异常类<br>
 * CreateTime: 2012-08-07 16:39 <br>
 * Copyright: Copyright (c) 2012 taiheiot.com<br>
 * 
 * @author guojun
 * @since 1.0
 * @see Exception
 */
public class ClassInstantiateException extends RuntimeException implements Serializable
{

    private static final long serialVersionUID = 1L;

    public ClassInstantiateException(Throwable e)
    {
        super(e);
    }

    public ClassInstantiateException(String e)
    {
        super(e);
    }

}
