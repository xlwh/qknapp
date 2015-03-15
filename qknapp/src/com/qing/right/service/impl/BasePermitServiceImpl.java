package com.qing.right.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.qing.common.base.dao.HibernateDao;
import com.qing.common.base.service.impl.BaseServiceImpl;
import com.qing.common.exception.DaoException;
import com.qing.common.exception.ServiceException;
import com.qing.common.exception.handle.ExceptionHandle;
import com.qing.common.util.ErrorCode;
import com.qing.right.dao.domain.BasePermit;
import com.qing.right.dao.impl.BasePermitDaoImpl;
import com.qing.right.service.IBasePermitService;

@Service
@Transactional
public class BasePermitServiceImpl implements IBasePermitService {
    @Resource
    private BasePermitDaoImpl basePermitDaoImpl;
    @Resource
    private HibernateDao<BasePermit, String> hibernateDao;

    @Resource
    private BaseServiceImpl<BasePermit, String> baseService;

    @Override
    @Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
    public List<Integer> queryPermitByLoginCode(String loginCode) throws ServiceException {
	List<Integer> list = new ArrayList<Integer>();
	try {
	    list = basePermitDaoImpl.queryPermitByLoginCode(loginCode);
	} catch (DaoException e) {
	    ExceptionHandle.throwServiceException(e, ErrorCode.SERVICE_QUERY_ERROR_004, "BasePermitServiceImpl.queryPermitByLoginCode()");
	}

	return list;
    }

    @Override
    public List<BasePermit> findAllPermits() throws ServiceException {
	try {
	    return hibernateDao.findAll(BasePermit.class);
	} catch (DaoException e) {
	    ExceptionHandle.throwServiceException(e, ErrorCode.SERVICE_QUERY_ERROR_004, "BasePermitServiceImpl.findAllPermits()");
	}
	return null;
    }

    /**
     * 保存或者修改权限
     * 
     * @param baseModule
     * @throws ServiceException
     */
    @Override
    public void saveOrUpdateBasePermit(BasePermit basePermit) throws ServiceException {
	try {
	    if (null != basePermit.getPermitId() && basePermit.getPermitId().equals("")) {
		basePermit.setPermitId(null);
	    }

	    baseService.saveOrUpdate(basePermit);
	} catch (ServiceException e) {
	    ExceptionHandle.throwServiceException(e, ErrorCode.DAO_SAVE_ERROR_001, "BaseModuleServiceImpl.saveOrUpdateBaseModule()");
	}
    }

    /**
     * 删除权限
     * 
     * @param baseModule
     * @throws ServiceException
     */
    @Override
    public void delBasePermit(String basePermitId) throws ServiceException {
	try {

	    BasePermit basePermit = baseService.findById(BasePermit.class, basePermitId);
	    baseService.remove((basePermit));
	} catch (ServiceException e) {
	    ExceptionHandle.throwServiceException(e, ErrorCode.DAO_DELETE_ERROR_002, "BaseModuleServiceImpl.delBasePermit()");
	}
    }
}
