package com.qing.right.dao;

import java.util.List;

import com.qing.common.exception.DaoException;

public interface IBaseTypeDictDao {
    /**
     * 根据父ID取得数据字典信息
     * 
     * @param params
     *            参数
     * @return
     * @throws DaoException
     */
    @SuppressWarnings("rawtypes")
	public List queryBaseTypeDict(Object[] params) throws DaoException;
}