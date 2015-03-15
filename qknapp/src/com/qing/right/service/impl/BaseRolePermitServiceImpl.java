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
import com.qing.right.dao.domain.BaseRolePermit;
import com.qing.right.dao.domain.BaseUser;
import com.qing.right.dao.impl.BaseRolePermitDaoImpl;
import com.qing.right.service.IBaseRolePermitService;
import com.qing.vo.RolePermitVO;

@Service
@Transactional
public class BaseRolePermitServiceImpl implements IBaseRolePermitService {

    @Resource
    private BaseRolePermitDaoImpl baseRolePermitDaoImpl;

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void saveByRoleId(String roleId, List<String> permitIdList) throws ServiceException {
	try {
	    baseRolePermitDaoImpl.saveByRoleId(roleId, permitIdList);
	} catch (DaoException e) {
	    ExceptionHandle.throwServiceException(e, ErrorCode.SERVICE_QUERY_ERROR_004, "BaseRolePermitServiceImpl.saveByRoleId()");
	}
    }

    @Override
    @Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
    public List<String> getPermitIdByRoleId(String roleId) throws ServiceException {
	List<String> list = null;
	try {
	    list = baseRolePermitDaoImpl.getPermitIdByRoleId(roleId);
	} catch (DaoException e) {
	    ExceptionHandle.throwServiceException(e, ErrorCode.SERVICE_QUERY_ERROR_004, "BaseRolePermitServiceImpl.getPermitIdByRoleId()");
	}
	return list;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void deleteByRoleId(String roleId) throws ServiceException {
	try {
	    baseRolePermitDaoImpl.deleteByRoleId(roleId);
	} catch (DaoException e) {
	    ExceptionHandle.throwServiceException(e, ErrorCode.SERVICE_QUERY_ERROR_004, "BaseRolePermitServiceImpl.deleteByRoleId()");
	}
    }

    @Override
    @Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
    public List<BaseRolePermit> getRolePermitIdByRoleId(String roleId) throws ServiceException {
	List<BaseRolePermit> list = null;
	try {
	    list = baseRolePermitDaoImpl.getRolePermitByRoleId(roleId);
	} catch (DaoException e) {
	    ExceptionHandle.throwServiceException(e, ErrorCode.SERVICE_QUERY_ERROR_004,
		    "BaseRolePermitServiceImpl.getRolePermitIdByRoleId()");
	}
	return list;
    }

    @Override
    @Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
    public List<String> getPermitNameByRoleId(String roleId) throws ServiceException {
	List<String> list = null;
	try {
	    list = baseRolePermitDaoImpl.getPermitNameByRoleId(roleId);
	} catch (DaoException e) {
	    ExceptionHandle
		    .throwServiceException(e, ErrorCode.SERVICE_QUERY_ERROR_004, "BaseRolePermitServiceImpl.getPermitNameByRoleId()");
	}
	return list;
    }

    @Override
    public List<RolePermitVO> getPermitNameAndDesc(BaseUser user) throws ServiceException {
	return null;
    }

    /**
     * 根据角色ID和用户ID删除角色用户
     * 
     * @author huangqingjian/0050
     * @see 相关函数,对于重要的函数建议注释
     * @since 深烟智能物流管理平台, 2013-1-14
     * @Company: Tai woo. Co., Ltd
     * @param roleId
     * @param userIds
     * @throws ServiceException
     */
    @Override
    public void deleteRolePermit(String roleId, String[] PermitIds) throws ServiceException {
	if (null != roleId && null != PermitIds && PermitIds.length > 0) {
	    for (int i = 0; i < PermitIds.length; i++) {
		try {
		    baseRolePermitDaoImpl.deleteByRoleIdAndUserId(roleId, PermitIds[i]);
		} catch (DaoException e) {
		    ExceptionHandle.throwServiceException(e, ErrorCode.SERVICE_QUERY_ERROR_004, "BaseRoleUserServiceImpl.deleteRoleUser()");
		}
	    }
	}
    }
}
