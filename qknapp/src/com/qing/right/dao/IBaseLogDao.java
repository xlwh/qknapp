package com.qing.right.dao;

import com.qing.common.exception.DaoException;
import com.qing.common.util.Page;

public interface IBaseLogDao {
    /**
     * 根据查询条件取得日志信息
     * 
     * @param params
     *            参数
     * @return
     * @throws DaoException
     */
    @SuppressWarnings("rawtypes")
	public Page queryBaseLog(Page page, Object[] params) throws DaoException;

    /**
     * 删除过期的日志
     * 
     * @param expiredDays
     *            过期天数
     * @throws DaoException
     */
    public void deleteExpiredLog(int expiredDays) throws DaoException;
    
    /**
     * 删除日志信息
     * 
     * @param userId
     * @return
     * @throws DaoException
     */
    public Boolean delBaseLog(String logId) throws DaoException;
}