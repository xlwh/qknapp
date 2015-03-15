package com.qing.right.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.qing.common.base.service.impl.BaseServiceImpl;
import com.qing.common.exception.DaoException;
import com.qing.common.exception.ServiceException;
import com.qing.common.exception.handle.ExceptionHandle;
import com.qing.common.util.ErrorCode;
import com.qing.common.util.Page;
import com.qing.right.dao.domain.BaseLog;
import com.qing.right.dao.impl.BaseLogDaoImpl;
import com.qing.right.service.IBaseLogService;

@Service
@Transactional
public class BaseLogServiceImpl implements IBaseLogService {
    @Resource
    private BaseLogDaoImpl baseLogDaoImpl;
    @Resource
    private BaseServiceImpl<BaseLog, String> baseService;

    @SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
    @Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
    public Page queryBaseLog(Page page, Object[] params) throws ServiceException {
	try {
	    baseLogDaoImpl.queryBaseLog(page, params);
	} catch (DaoException e) {
	    ExceptionHandle.throwServiceException(e, ErrorCode.SERVICE_QUERY_ERROR_004, "BaseIpTypeServiceImpl.queryBaseLog()");
	}
	return page;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void deleteExpiredLog(int expiredDays) throws ServiceException {
	try {
	    baseLogDaoImpl.deleteExpiredLog(expiredDays);
	} catch (DaoException e) {
	    ExceptionHandle.throwServiceException(e, ErrorCode.SERVICE_QUERY_ERROR_004, "BaseIpTypeServiceImpl.deleteExpiredLog()");
	}
    }

    /**
     * 保存或者修改日志
     * 
     * @param baseLog
     * @throws ServiceException
     */
    public void saveOrUpdateLog(BaseLog baseLog) throws ServiceException {
	try {
	    baseService.saveOrUpdate(baseLog);
	} catch (ServiceException e) {
	    ExceptionHandle.throwServiceException(e, ErrorCode.SERVICE_QUERY_ERROR_004, "BaseLogServiceImpl.saveOrUpdateLog()");
	}
    }
    /**
     * 删除日志信息
     * 
     * @param baseLog
     * @throws ServiceException
     */
    public Boolean delBaseLog(String logId) throws ServiceException{
   	Boolean bool = false;
   	try {
   	    bool = baseLogDaoImpl.delBaseLog(logId);
   	} catch (DaoException e) {
   	    ExceptionHandle.throwServiceException(e, ErrorCode.DAO_SAVE_ERROR_001, "BaseLogServiceImpl.delBaseLog()");
   	}
   	return bool;
       }
}
