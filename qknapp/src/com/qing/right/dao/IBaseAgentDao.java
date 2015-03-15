package com.qing.right.dao;

import com.qing.common.exception.DaoException;
import com.qing.common.util.Page;

public interface IBaseAgentDao {
    /**
     * 根据查询条件取得代理设定信息
     * 
     * @param params
     *            参数
     * @return
     * @throws DaoException
     */
    @SuppressWarnings("rawtypes")
	public Page queryBaseAgent(Page page, Object[] params) throws DaoException;

}