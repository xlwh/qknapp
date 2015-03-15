/**
 *  Copyright (c) 2011, Eryptogram.java TAIHEIOT and/or its affiliates. All rights reserved.
 *
 *  Licensed under the TAIHEIOT License, Version 1.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package com.qing.common.util;

import java.io.Serializable;

import com.qing.common.exception.ClassInstantiateException;

/**
 * Title: ClassUtil工具类<br>
 * Description: ClassUtil工具类, 提供按类初始化与按类名string初始化<br>
 * CreateTime: 2012-08-06 16:39<br>
 * Copyright: Copyright (c) 2012 taiheiot.com<br>
 * 
 * @author guojun
 * @since 1.0
 */
public class ClassUtil implements Serializable
{

    private static final long serialVersionUID = 1L;

    /**
     * 按类初始化
     * 
     * @param entityClass 实体类
     * @return
     */
    public static Object newInstance(Class<?> entityClass)
    {

        Object obj;
        try
        {
            obj = entityClass.newInstance();
        }
        catch (Exception e)
        {
            throw new ClassInstantiateException(e);
        }

        return obj;
    }

    /**
     * 按类名string初始化
     * 
     * @param entityClass 类名string
     * @return
     */
    public static Object newInstance(String className)
    {

        Object obj;
        try
        {
            obj = Class.forName(className).newInstance();
        }
        catch (Exception e)
        {
            throw new ClassInstantiateException(e);
        }

        return obj;
    }

}
