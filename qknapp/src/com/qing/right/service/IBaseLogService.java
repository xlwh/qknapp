package com.qing.right.service;

import com.qing.common.exception.ServiceException;
import com.qing.common.util.Page;
import com.qing.right.dao.domain.BaseLog;

public interface IBaseLogService {
    /**
     * 根据查询条件取得日志信息
     * 
     * @param params
     *            参数
     * @return
     * @throws ServiceException
     */
    @SuppressWarnings("rawtypes")
	public Page<BaseLog> queryBaseLog(Page page, Object[] params) throws ServiceException;

    /**
     * 删除过期的日志
     * 
     * @param expiredDays
     *            过期天数
     * @throws ServiceException
     */
    public void deleteExpiredLog(int expiredDays) throws ServiceException;

    /**
     * 保存或者修改日志
     * 
     * @param baseLog
     * @throws ServiceException
     */
    public void saveOrUpdateLog(BaseLog baseLog) throws ServiceException;
  
    /**
     * 删除日志
     * 
     * @param logId
     * @throws ServiceException
     */
    
    public Boolean delBaseLog(String logId) throws ServiceException;
}
