package com.qing.right.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.qing.common.exception.DaoException;
import com.qing.common.exception.ServiceException;
import com.qing.common.exception.handle.ExceptionHandle;
import com.qing.common.util.ErrorCode;
import com.qing.right.dao.impl.BaseOrganUserPermitDaoImpl;
import com.qing.right.service.IBaseOrganUserPermitService;

@Service
@Transactional
public class BaseOrganUserPermitServiceImpl implements IBaseOrganUserPermitService {
    @Resource
    private BaseOrganUserPermitDaoImpl baseOrganUserPermitDaoImpl;

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void saveByOrganUserId(String organUserId, List<String> permitIdList) throws ServiceException {
	try {
	    baseOrganUserPermitDaoImpl.saveByOrganUserId(organUserId, permitIdList);
	} catch (DaoException e) {
	    ExceptionHandle.throwServiceException(e, ErrorCode.SERVICE_QUERY_ERROR_004,
		    "BaseOrganUserPermitServiceImpl.saveByOrganUserId()");
	}
    }

    @Override
    @Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
    public List<String> getPermitNameByUserId(String userId) throws ServiceException {
	List<String> list = null;
	try {
	    list = baseOrganUserPermitDaoImpl.getPermitNameByUserId(userId);
	} catch (DaoException e) {
	    ExceptionHandle.throwServiceException(e, ErrorCode.SERVICE_QUERY_ERROR_004,
		    "BaseOrganUserPermitServiceImpl.getPermitIdByOrganUserId()");
	}

	return list;
    }

    @Override
    @Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
    public List<String> getPermitIdByorganUserId(String organUserId) throws ServiceException {
	List<String> list = null;
	try {
	    list = baseOrganUserPermitDaoImpl.getPermitIdByOrganUserId(organUserId);
	} catch (DaoException e) {
	    ExceptionHandle.throwServiceException(e, ErrorCode.SERVICE_QUERY_ERROR_004,
		    "BaseOrganUserPermitServiceImpl.getPermitIdByOrganUserId()");
	}

	return list;
    }

}
