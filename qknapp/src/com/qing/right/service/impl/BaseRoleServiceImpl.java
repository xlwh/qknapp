package com.qing.right.service.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.annotation.Resource;

import org.springframework.beans.BeanUtils;
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
import com.qing.right.dao.domain.BaseRole;
import com.qing.right.dao.domain.BaseRoleUser;
import com.qing.right.dao.impl.BaseRoleDaoImpl;
import com.qing.right.dao.impl.BaseRoleUserDaoImpl;
import com.qing.right.service.IBaseRoleService;
import com.qing.vo.OrganVO;
import com.qing.vo.RoleVO;

@Service
@Transactional
public class BaseRoleServiceImpl implements IBaseRoleService {
    @Resource
    private BaseRoleDaoImpl baseRoleDaoImpl;
    @Resource
    private BaseServiceImpl<BaseRole, String> baseService;
    @Resource
    public OrganJdbcServiceImpl organJdbcService;
    @Resource
    public BaseRoleUserDaoImpl baseRoleUserDaoImpl;
    @Resource
    public BaseOrganServiceImpl baseOrganServiceImpl;

    @Override
    @Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
    public Page<RoleVO> queryChild(String organId, Page<BaseRole> page) throws ServiceException {
	Page<RoleVO> rolePage = null;
	try {
	    // 不分页
	    page.setPageNo(-1);
	    page.setPageSize(-1);
	    baseRoleDaoImpl.queryChild(page, organId);
	    rolePage = new Page<RoleVO>();
	    BeanUtils.copyProperties(page, rolePage);
	    List<RoleVO> RoleVoList = new ArrayList<RoleVO>();
	    for (BaseRole role : page.getResult()) {
		RoleVO rv = new RoleVO();
		BeanUtils.copyProperties(role, rv);
		rv.setOrganID(role.getBaseOrgan().getOrganId());
		rv.setOrganName(role.getBaseOrgan().getOrganName());
		if (Constants.ENABLE_FLAG.equals(rv.getOkFlag())) {
		    rv.setOkFlag(Constants.ENABLE_STR);
		} else {
		    rv.setOkFlag(Constants.DISABLE_STR);
		}
		RoleVoList.add(rv);
	    }
	    rolePage.setResult(RoleVoList);

	} catch (DaoException e) {
	    ExceptionHandle.throwServiceException(e, ErrorCode.SERVICE_QUERY_ERROR_004, "BaseRoleServiceImpl.queryChild()");
	}
	return rolePage;
    }

    /**
     * 保存或者修改角色
     * 
     * @param baseOrgan
     * @return
     * @throws ServiceException
     */
    @Override
    public void saveOrUpdateRole(BaseRole baseRole) throws ServiceException {
	try {
	    // 将空字符设置为null，否则""也会当成ID进行更新
	    if ("".equals(baseRole.getRoleId())) {
		baseRole.setRoleId(null);
	    }
	    baseService.saveOrUpdate(baseRole);
	} catch (ServiceException e) {
	    ExceptionHandle.throwServiceException(e, ErrorCode.DAO_SAVE_ERROR_001, "BaseRoleServiceImpl.saveOrUpdateRole()");
	}

    }

    /**
     * 
     * 根据组织ID获取所有角色，包括子组织的
     * 
     * @see com.qing.right.service.IBaseRoleService#getAllRoleByOrganId(java.lang.String)
     * @author huangqingjian/0050
     * @see 相关函数,对于重要的函数建议注释
     * @since 深烟智能物流管理平台, 2013-1-9
     * @param organId
     * @return
     * @throws ServiceException
     */
    @Override
    public List<BaseRole> getAllRoleByOrganId(String organId) throws ServiceException {

	List<BaseRole> listBaseRoles = null;
	try {
	    List<OrganVO> list = organJdbcService.getOrganTree(organId);
	    listBaseRoles = baseRoleDaoImpl.getAllRoleByOrganId(list);
	} catch (DaoException e) {
	    ExceptionHandle.throwServiceException(e, ErrorCode.DAO_SAVE_ERROR_001, "BaseRoleServiceImpl.getAllRoleByOrganId()");
	}
	return listBaseRoles;
    }

    /**
     * 根据当前登陆管理员能管理的组织，加载出所有用户
     * 
     * @author huangqingjian/0050
     * @see 相关函数,对于重要的函数建议注释
     * @since 深烟智能物流管理平台, 2013-1-9
     * @param organVOs
     * @return
     * @throws DaoException
     */
    @Override
    public Map<String, List<RoleVO>> getAllRoleVOByOrganId(String organId) throws ServiceException {

	List<Map<String, Object>> listBaseRoles = null;
	Map<String, List<RoleVO>> result = new TreeMap<String, List<RoleVO>>();
	try {
	    List<OrganVO> list = baseOrganServiceImpl.getOrganTree(organId);
	    for (OrganVO organVO : list) {
		listBaseRoles = baseRoleDaoImpl.getAllRoleVOByOrganId(organVO.getOrganId());
		List<RoleVO> listVos = new ArrayList<RoleVO>();
		for (Iterator<Map<String, Object>> itr = listBaseRoles.iterator(); itr.hasNext();) {
		    Map<String, Object> map = itr.next();
		    RoleVO rv = new RoleVO();

		    if (null != map.get("ORGAN_ID")) {
			rv.setOrganID(map.get("ORGAN_ID").toString());
		    }
		    if (null != map.get("ROLE_ID")) {
			rv.setRoleId(map.get("ROLE_ID").toString());
		    }
		    if (null != map.get("ROLE_NAME")) {
			rv.setRoleName(map.get("ROLE_NAME").toString());
		    }
		    listVos.add(rv);

		}
		if (listVos.size() > 0) {
		    result.put(organVO.getOrganName(), listVos);
		}

	    }

	} catch (DaoException e) {
	    ExceptionHandle.throwServiceException(e, ErrorCode.DAO_SAVE_ERROR_001, "BaseRoleServiceImpl.getAllUserByOrganId()");
	}
	return result;
    }

    @Override
    public List<String> deleteRoleById(String roleId) throws ServiceException {
	List<String> list = new ArrayList<String>();
	try {
	    BaseRole role = baseService.findById(BaseRole.class, roleId);
	    List<BaseRoleUser> listRoleUsers;

	    listRoleUsers = baseRoleUserDaoImpl.getRoleUserByRoleId(roleId);
	    if (null != role && listRoleUsers.size() > 0) {
		list.add(roleId);
	    } else {
		baseService.remove(role);
	    }
	} catch (DaoException e) {
	    ExceptionHandle.throwServiceException(e, ErrorCode.DAO_DELETE_ERROR_002, "BaseRoleServiceImpl.deleteRoleById()");
	}

	return list;
    }

    /**
     * 
     * 获取角色
     * 
     * @author huangqingjian/0050
     * @see 相关函数,对于重要的函数建议注释
     * @since 深烟智能物流管理平台, 2013-1-11
     * @Company: Tai woo. Co., Ltd
     * @param roleId
     * @return
     */
    @Override
    public BaseRole getBaseRole(String roleId) throws ServiceException {
	BaseRole role = baseService.findById(BaseRole.class, roleId);
	return role;
    }
}
