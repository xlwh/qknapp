package com.qing.right.service.impl;

import java.util.ArrayList;
import java.util.List;

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
import com.qing.constant.Constants;
import com.qing.right.dao.domain.BaseOrganUser;
import com.qing.right.dao.impl.BaseOrganDaoImpl;
import com.qing.right.dao.impl.BaseOrganUserDaoImpl;
import com.qing.right.service.IBaseOrganUserService;
import com.qing.vo.OrganUserVO;
import com.qing.vo.OrganVO;

@Service
@Transactional
public class BaseOrganUserServiceImpl implements IBaseOrganUserService {

    @Resource
    private BaseOrganUserDaoImpl baseOrganUserDaoImpl;
    @Resource
    private BaseOrganDaoImpl baseOrganDaoImpl;

    @Resource
    private BaseServiceImpl<BaseOrganUser, String> baseService;
    @Resource
    public BaseOrganServiceImpl baseOrganServiceImpl;

    @Override
    @Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
    public List<String> getOrganIdByUserId(String userId) throws ServiceException {
	List<String> list = null;
	try {
	    list = baseOrganUserDaoImpl.getOrganIdByUserId(userId);
	} catch (DaoException e) {
	    ExceptionHandle.throwServiceException(e, ErrorCode.SERVICE_QUERY_ERROR_004, "BaseOrganUserServiceImpl.getOrganIdByUserId()");
	}
	return list;
    }

    @Override
    @Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
    public String getAdminIdOfOrgan(String userId, String organId) throws ServiceException {
	String organUserId = null;
	try {
	    // 判断用户是否是当前组织的管理员
	    // 递归判断用户是否是父组织的管理员
	    String organParentId = null;
	    do {
		organUserId = baseOrganUserDaoImpl.getAdminIdOfOrgan(userId, organId);
		if (organUserId != null)
		    return organUserId;

		organParentId = baseOrganDaoImpl.getOrganParentId(organId);
		organId = organParentId;
	    } while (organParentId != null);
	} catch (DaoException e) {
	    ExceptionHandle.throwServiceException(e, ErrorCode.SERVICE_QUERY_ERROR_004, "BaseOrganUserServiceImpl.getAdminIdOfOrgan()");
	}
	return null;
    }

    @Override
    @Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
    public Boolean checkAdmin(String organId, String userId) throws ServiceException {
	Boolean bool = false;

	try {
	    bool = baseOrganUserDaoImpl.checkAdmin(organId, userId);
	} catch (DaoException e) {
	    ExceptionHandle.throwServiceException(e, ErrorCode.SERVICE_QUERY_ERROR_004, "BaseOrganUserServiceImpl.checkAdmin()");
	}
	return bool;
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
    @Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
    public Page queryChild(Page page, String organId) throws ServiceException {
	try {
	    baseOrganUserDaoImpl.queryChild(page, organId);
	} catch (DaoException e) {
	    ExceptionHandle.throwServiceException(e, ErrorCode.SERVICE_QUERY_ERROR_004, "BaseOrganUserServiceImpl.queryChild()");
	}
	return page;
    }

    /**
     * 保存/修改组织管理用户
     * 
     * @param baseOrganUser
     * @return
     * @throws DaoException
     */
    @Override
    @Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
    public void saveOrUpdateBaseOrganUser(BaseOrganUser baseOrganUser) throws ServiceException {
	try {
	    if ("".equals(baseOrganUser.getOrganUserId())) {
		baseOrganUser.setOrganUserId(null);
	    }
	    baseService.saveOrUpdate(baseOrganUser);
	} catch (ServiceException e) {
	    ExceptionHandle.throwServiceException(e, ErrorCode.DAO_SAVE_ERROR_001, "BaseOrganUserServiceImpl.saveOrUpdateBaseOrganUser()");
	}
    }

    /**
     * 根据组织ID将当前组织和子组织的管理用户都查询出来
     * 
     * @param organId
     * @return
     * @throws DaoException
     */
    @Override
    @Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
    public List<OrganUserVO> getOrganUserList(String organId) throws ServiceException {
	List<BaseOrganUser> list = null;
	List<OrganUserVO> organUserlist = new ArrayList<OrganUserVO>();
	try {
	    // 取得该组织下所有子组织
	    List<OrganVO> organVOs = baseOrganServiceImpl.getOrganTree(organId);
	    list = baseOrganUserDaoImpl.getOrganUserList(organId, organVOs);

	    for (BaseOrganUser baseOrganUser : list) {

		OrganUserVO organUserVO = new OrganUserVO();
		organUserVO.setOrganUserId(baseOrganUser.getOrganUserId());
		organUserVO.setOrganId(baseOrganUser.getBaseOrgan().getOrganId());
		organUserVO.setUserId(baseOrganUser.getBaseUser().getUserId());
		organUserVO.setOrganName(baseOrganUser.getBaseOrgan().getOrganName());
		organUserVO.setLoginCode(baseOrganUser.getBaseUser().getLoginCode());
		organUserVO.setStatus(baseOrganUser.getBaseUser().getStatus());
		if (Constants.ENABLE_FLAG.equals(organUserVO.getStatus())) {
		    organUserVO.setStatus(Constants.ENABLE_STR);
		} else {
		    organUserVO.setStatus(Constants.DISABLE_STR);
		}
		organUserVO.setUserName(baseOrganUser.getBaseUser().getUserName());
		organUserVO.setPassword(baseOrganUser.getBaseUser().getPassword());

		organUserlist.add(organUserVO);
	    }

	} catch (Exception e) {
	    ExceptionHandle.throwServiceException(e, ErrorCode.SERVICE_QUERY_ERROR_004, "BaseOrganUserServiceImpl.getOrganUserList()");
	}
	return organUserlist;
    }

    /**
     * 根据组织用户ID将对应的组织管理员信息查询出来
     * 
     * @param organUserId
     * @return
     * @throws DaoException
     */
    @Override
    public BaseOrganUser getBaseOrganUser(String organUserId) throws ServiceException {
	BaseOrganUser bou = null;
	try {
	    bou = baseOrganUserDaoImpl.getBaseOrganUser(organUserId);
	} catch (DaoException e) {
	    ExceptionHandle.throwServiceException(e, ErrorCode.DAO_QUERY_ERROR_004, "BaseOrganUserServiceImpl.getBaseOrganUser()");
	}
	return bou;
    }

    /**
     * 根据组织用户ID将对应的组织管理员信息删除
     * 
     * @param ids
     * @return
     * @throws DaoException
     */
    @Override
    public List<String> deleteBaseOrganUser(String[] ids) throws ServiceException {
	List<String> list = new ArrayList<String>();
	if (null != ids && ids.length > 0) {

	    for (int i = 0; i < ids.length; i++) {
		try {
		    BaseOrganUser baseOrganUser = baseOrganUserDaoImpl.getBaseOrganUser(ids[i]);
		    baseService.remove(baseOrganUser);
		} catch (DaoException e) {
		    ExceptionHandle.throwServiceException(e, ErrorCode.DAO_QUERY_ERROR_004,
			    "BaseOrganUserServiceImpl.deleteBaseOrganUser()");
		}
	    }

	}

	return list;
    }

}
