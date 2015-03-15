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
import com.qing.common.util.Page;
import com.qing.right.dao.impl.BaseIpTypeDaoImpl;
import com.qing.right.service.IBaseIpTypeService;

@Service
@Transactional
public class BaseIpTypeServiceImpl implements IBaseIpTypeService {

    @Resource
    private BaseIpTypeDaoImpl baseIpTypeDaoImpl;

    @SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
    @Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
    public Page queryBaseIpType(Page page, Object[] params) throws ServiceException {
	try {
	    baseIpTypeDaoImpl.queryBaseIpType(page, params);
	} catch (DaoException e) {
	    ExceptionHandle.throwServiceException(e, ErrorCode.SERVICE_QUERY_ERROR_004, "BaseIpTypeServiceImpl.queryBaseIpType()");
	}
	return page;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.sy.right.service.IBaseIpTypeService#findAllowedIpRange()
     */
    @SuppressWarnings("rawtypes")
	@Override
    @Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
    public List findAllowedIpRange() throws ServiceException {
	List list = null;
	try {
	    list = baseIpTypeDaoImpl.findAllowedIpRange();
	} catch (DaoException e) {
	    ExceptionHandle.throwServiceException(e, ErrorCode.SERVICE_QUERY_ERROR_004, "BaseIpTypeServiceImpl.findAllowedIpRange()");
	}
	return list;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.sy.right.service.IBaseIpTypeService#findAllowedIpSegment()
     */
    @SuppressWarnings("rawtypes")
	@Override
    @Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
    public List findAllowedIpSegment() throws ServiceException {
	List list = null;
	try {
	    list = baseIpTypeDaoImpl.findAllowedIpSegment();
	} catch (DaoException e) {
	    ExceptionHandle.throwServiceException(e, ErrorCode.SERVICE_QUERY_ERROR_004, "BaseIpTypeServiceImpl.findAllowedIpRange()");
	}

	return list;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.sy.right.service.IBaseIpTypeService#findRefusedIpRange()
     */
    @SuppressWarnings("rawtypes")
	@Override
    @Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
    public List findRefusedIpRange() throws ServiceException {
	List list = null;
	try {
	    list = baseIpTypeDaoImpl.findRefusedIpRange();
	} catch (DaoException e) {
	    ExceptionHandle.throwServiceException(e, ErrorCode.SERVICE_QUERY_ERROR_004, "BaseIpTypeServiceImpl.findRefusedIpRange()");
	}

	return list;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.sy.right.service.IBaseIpTypeService#findRefusedIpSegment()
     */
    @SuppressWarnings("rawtypes")
	@Override
    @Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
    public List findRefusedIpSegment() throws ServiceException {
	List list = null;
	try {
	    list = baseIpTypeDaoImpl.findRefusedIpSegment();
	} catch (DaoException e) {
	    ExceptionHandle.throwServiceException(e, ErrorCode.SERVICE_QUERY_ERROR_004, "BaseIpTypeServiceImpl.findRefusedIpRange()");
	}

	return list;
    }
}
