package com.qing.right.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.qing.common.exception.DaoException;
import com.qing.common.exception.ServiceException;
import com.qing.common.exception.handle.ExceptionHandle;
import com.qing.common.util.ErrorCode;
import com.qing.common.util.Page;
import com.qing.right.dao.impl.BaseAgentDaoImpl;
import com.qing.right.service.IBaseAgentService;

@Service
@Transactional
public class BaseAgentServiceImpl implements IBaseAgentService {

    @Resource
    private BaseAgentDaoImpl baseAgentDaoImpl;

    @SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
    @Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
    public Page queryBaseAgent(Page page, Object[] params) throws ServiceException {
	try {
	    baseAgentDaoImpl.queryBaseAgent(page, params);
	} catch (DaoException e) {
	    ExceptionHandle.throwServiceException(e, ErrorCode.SERVICE_QUERY_ERROR_004, "BaseAgentServiceImpl.queryBaseAgent()");
	}
	return page;
    }

}
