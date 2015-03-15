package com.qing.right.service;

import java.util.List;

import com.qing.common.exception.ServiceException;

public interface IBaseTypeDictService {
    /**
     * 根据父ID取得数据字典信息
     * 
     * @param params
     *            参数
     * @return
     * @throws ServiceException
     */
    @SuppressWarnings("rawtypes")
	public List queryBaseTypeDict(Object[] params) throws ServiceException;
}
