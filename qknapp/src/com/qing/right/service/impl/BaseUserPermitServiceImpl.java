package com.qing.right.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.qing.common.exception.DaoException;
import com.qing.common.exception.ServiceException;
import com.qing.common.exception.handle.ExceptionHandle;
import com.qing.common.util.ErrorCode;
import com.qing.right.dao.impl.BaseRolePermitDaoImpl;
import com.qing.right.dao.impl.BaseRoleUserDaoImpl;
import com.qing.right.dao.impl.BaseUserPermitDaoImpl;
import com.qing.right.service.IBaseUserPermitService;

@Service
@Transactional
public class BaseUserPermitServiceImpl implements IBaseUserPermitService {

    @Resource
    private BaseUserPermitDaoImpl baseUserPermitDaoImpl;
    @Resource
    private BaseRoleUserDaoImpl baseRoleUserDaoImpl;
    @Resource
    private BaseRolePermitDaoImpl baseRolePermitDaoImpl;

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void saveByUserId(String userId, List<String> permitIdList) throws ServiceException {
	try {
	    baseUserPermitDaoImpl.saveByUserId(userId, permitIdList);
	} catch (DaoException e) {
	    ExceptionHandle.throwServiceException(e, ErrorCode.SERVICE_QUERY_ERROR_004, "BaseUserPermitServiceImpl.saveByUserId()");
	}
    }

    @Override
    @Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
    public List<String> getPermitIdByUserId(String userId) throws ServiceException {
	List<String> list = null;
	try {
	    list = baseUserPermitDaoImpl.getPermitIdByUserId(userId);
	} catch (DaoException e) {
	    ExceptionHandle.throwServiceException(e, ErrorCode.SERVICE_QUERY_ERROR_004, "BaseUserPermitServiceImpl.getPermitIdByUserId()");
	}
	return list;
    }

    @Override
    @Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
    public List<String> getRolePermitIdByUserId(String userId) throws ServiceException {
	List<String> permitList = new ArrayList<String>();
	try {
	    List<String> roleList = baseRoleUserDaoImpl.getRoleIdByUserId(userId);

	    if (roleList != null && roleList.size() > 0) {
		for (String roleId : roleList) {
		    permitList.addAll(baseRolePermitDaoImpl.getPermitIdByRoleId(roleId));
		}
	    }
	} catch (DaoException e) {
	    ExceptionHandle.throwServiceException(e, ErrorCode.SERVICE_QUERY_ERROR_004, "BaseUserPermitServiceImpl.getPermitIdByUserId()");
	}
	return permitList;
    }

    @Override
    @Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
    public List<String> getRoleNameByUserId(String userId) throws ServiceException {
	List<String> roleNameList = null;
	try {
	    roleNameList = baseRoleUserDaoImpl.getRoleNameByUserId(userId);

	} catch (DaoException e) {
	    ExceptionHandle.throwServiceException(e, ErrorCode.SERVICE_QUERY_ERROR_004, "BaseUserPermitServiceImpl.getPermitIdByUserId()");
	}
	return roleNameList;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void deleteByUserId(String userId) throws ServiceException {
	try {
	    baseUserPermitDaoImpl.deleteByUserId(userId);
	} catch (DaoException e) {
	    ExceptionHandle.throwServiceException(e, ErrorCode.SERVICE_QUERY_ERROR_004, "BaseUserPermitServiceImpl.deleteByUserId()");
	}
    }

    @Override
    @Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
    public List<String> getUserPermitIdByUserId(String userId) throws ServiceException {
	List<String> list = null;
	try {
	    list = baseUserPermitDaoImpl.getUserPermitIdByUserId(userId);
	} catch (DaoException e) {
	    ExceptionHandle.throwServiceException(e, ErrorCode.SERVICE_QUERY_ERROR_004,
		    "BaseUserPermitServiceImpl.getUserPermitIdByUserId()");
	}
	return list;
    }

    @Override
    @Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
    public List<String> getUserAllPermitIdsByUserId(String userId) throws ServiceException {
	List<String> list = new ArrayList<String>();
	Map<String, String> map = new HashMap<String, String>();
	try {
	    List<String> roleList = baseRoleUserDaoImpl.getRoleIdByUserId(userId);
	    List<String> permitList1 = null;
	    if (roleList != null && roleList.size() > 0) {
		for (String roleId : roleList) {
		    permitList1 = baseRolePermitDaoImpl.getPermitIdByRoleId(roleId);
		    permitToMap(map, permitList1);
		}
	    }

	    permitList1 = getPermitIdByUserId(userId);
	    permitToMap(map, permitList1);

	} catch (DaoException e) {
	    ExceptionHandle.throwServiceException(e, ErrorCode.SERVICE_QUERY_ERROR_004,
		    "BaseUserPermitServiceImpl.getUserAllPermitIdsByUserId()");
	}
	if (map.size() > 0) {
	    Collection<String> c = map.values();
	    for (String id : c) {
		list.add(id);
	    }
	}
	return list;
    }

    /**
     * @param map
     * @param permitList1
     */
    private void permitToMap(Map<String, String> map, List<String> permitList1) {
	if (permitList1 != null && permitList1.size() > 0) {
	    for (String permitId : permitList1) {
		if (!map.containsKey(permitId)) {
		    map.put(permitId, permitId);
		}
	    }
	}
    }

    @Override
    @Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
    public List<String> getPermitNameByUserId(String userId) throws ServiceException {
	List<String> list = null;
	try {
	    list = baseUserPermitDaoImpl.getPermitNameByUserId(userId);
	} catch (DaoException e) {
	    ExceptionHandle
		    .throwServiceException(e, ErrorCode.SERVICE_QUERY_ERROR_004, "BaseUserPermitServiceImpl.getPermitNameByUserId()");
	}
	return list;
    }

}
