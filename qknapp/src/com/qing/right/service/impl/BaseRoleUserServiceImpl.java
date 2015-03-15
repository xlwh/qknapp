package com.qing.right.service.impl;

import java.util.ArrayList;
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
import com.qing.right.dao.domain.BaseRoleUser;
import com.qing.right.dao.domain.BaseUser;
import com.qing.right.dao.impl.BaseRoleUserDaoImpl;
import com.qing.right.service.IBaseRoleUserService;
import com.qing.vo.RoleUserVO;

@Service
@Transactional
public class BaseRoleUserServiceImpl implements IBaseRoleUserService {

    @Resource
    private BaseRoleUserDaoImpl baseRoleUserDaoImpl;

    @Override
    @Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
    public List<String> getRoleIdByUserId(String userId) throws ServiceException {
	List<String> list = null;
	try {
	    list = baseRoleUserDaoImpl.getRoleIdByUserId(userId);
	} catch (DaoException e) {
	    ExceptionHandle.throwServiceException(e, ErrorCode.SERVICE_QUERY_ERROR_004, "BaseRoleUserServiceImpl.getRoleIdByUserId()");
	}
	return list;
    }

    @Override
    @Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
    public List<RoleUserVO> getRoleUserByRoleId(String roleId) throws ServiceException {
	List<BaseRoleUser> list = null;
	List<RoleUserVO> listRoleUserVOs = new ArrayList<RoleUserVO>();
	try {
	    list = baseRoleUserDaoImpl.getRoleUserByRoleId(roleId);

	    // 将hibernate 实体 转化成页面json 对象
	    for (BaseRoleUser baseRoleUser : list) {
		
		RoleUserVO vo = new RoleUserVO();
		vo.setRoleUserId(baseRoleUser.getRoleUserId());
		vo.setLoginCode(baseRoleUser.getBaseUser().getLoginCode());
		vo.setRoleID(baseRoleUser.getBaseRole().getRoleId());
		vo.setRoleName(baseRoleUser.getBaseRole().getRoleName());
		vo.setUserID(baseRoleUser.getBaseUser().getUserId());
		vo.setUserName(baseRoleUser.getBaseUser().getUserName());
		vo.setOrganID(baseRoleUser.getBaseUser().getBaseOrgan().getOrganId());
		vo.setOrganName(baseRoleUser.getBaseUser().getBaseOrgan().getOrganName());
		listRoleUserVOs.add(vo);
	    }
	} catch (DaoException e) {
	    ExceptionHandle.throwServiceException(e, ErrorCode.SERVICE_QUERY_ERROR_004, "BaseRoleUserServiceImpl.getRoleUserByOrganId()");
	}
	return listRoleUserVOs;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void saveByRoleId(String roleId, List<String> userIdList) throws ServiceException {
	try {
	    baseRoleUserDaoImpl.saveByRoleId(roleId, userIdList);
	} catch (DaoException e) {
	    ExceptionHandle.throwServiceException(e, ErrorCode.SERVICE_QUERY_ERROR_004, "BaseRoleUserServiceImpl.saveByRoleId()");
	}
    }

    @Override
    @Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
    public List<String> getUserIdByRoleId(String roleId) throws ServiceException {
	List<String> list = null;
	try {
	    list = baseRoleUserDaoImpl.getUserIdByRoleId(roleId);
	} catch (DaoException e) {
	    ExceptionHandle.throwServiceException(e, ErrorCode.SERVICE_QUERY_ERROR_004, "BaseRoleUserServiceImpl.getUserIdByRoleId()");
	}
	return list;
    }

    @Override
    @Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
    public List<BaseUser> getBaseUserByRoleId(String roleId, Object[] params) throws ServiceException {
	List<BaseUser> list = null;
	try {
	    list = baseRoleUserDaoImpl.getBaseUserByRoleId(roleId, params);
	} catch (DaoException e) {
	    ExceptionHandle.throwServiceException(e, ErrorCode.SERVICE_QUERY_ERROR_004, "BaseRoleUserServiceImpl.getBaseUserByRoleId()");
	}
	return list;
    }

    @SuppressWarnings("rawtypes")
	@Override
    @Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
    public Page queryBaseUserByRoleId(Page page, String roleId, Object[] params) throws ServiceException {
	try {
	    page = baseRoleUserDaoImpl.queryBaseUserByRoleId(page, roleId, params);
	} catch (DaoException e) {
	    ExceptionHandle.throwServiceException(e, ErrorCode.SERVICE_QUERY_ERROR_004, "BaseRoleUserServiceImpl.queryBaseUserByRoleId()");
	}
	return page;
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
    public void deleteRoleUser(String roleId, String[] userIds) throws ServiceException {
	if (null != roleId && null != userIds && userIds.length > 0) {
	    for (int i = 0; i < userIds.length; i++) {
		try {
		    baseRoleUserDaoImpl.deleteByRoleIdAndUserId(roleId, userIds[i]);
		} catch (DaoException e) {
		    ExceptionHandle.throwServiceException(e, ErrorCode.SERVICE_QUERY_ERROR_004, "BaseRoleUserServiceImpl.deleteRoleUser()");
		}
	    }
	}
    }

}
