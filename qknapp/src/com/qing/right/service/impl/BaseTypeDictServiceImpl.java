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
import com.qing.right.dao.impl.BaseTypeDictDaoImpl;
import com.qing.right.service.IBaseTypeDictService;

@Service
@Transactional
public class BaseTypeDictServiceImpl implements IBaseTypeDictService {

    @Resource
    private BaseTypeDictDaoImpl baseTypeDictDaoImpl;

    @SuppressWarnings("rawtypes")
	@Override
    @Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
    public List queryBaseTypeDict(Object[] params) throws ServiceException {
	List list = null;

	try {
	    list = baseTypeDictDaoImpl.queryBaseTypeDict(params);
	} catch (DaoException e) {
	    ExceptionHandle.throwServiceException(e, ErrorCode.SERVICE_QUERY_ERROR_004, "BaseTypeDictServiceImpl.queryBaseTypeDict()");
	}
	return list;
    }

}
