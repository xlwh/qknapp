package com.qing.right.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.qing.common.base.service.impl.BaseServiceImpl;
import com.qing.common.exception.DaoException;
import com.qing.common.exception.ServiceException;
import com.qing.common.exception.handle.ExceptionHandle;
import com.qing.common.util.ErrorCode;
import com.qing.right.dao.domain.BaseModule;
import com.qing.right.dao.domain.BaseOrganUser;
import com.qing.right.dao.domain.BaseOrganUserPermit;
import com.qing.right.dao.domain.BasePermit;
import com.qing.right.dao.domain.BaseRole;
import com.qing.right.dao.domain.BaseRolePermit;
import com.qing.right.dao.domain.BaseRoleUser;
import com.qing.right.dao.domain.BaseUser;
import com.qing.right.dao.domain.BaseUserPermit;
import com.qing.right.dao.impl.BaseModuleDaoImpl;
import com.qing.right.dao.impl.BaseOrganUserDaoImpl;
import com.qing.right.dao.impl.BaseOrganUserPermitDaoImpl;
import com.qing.right.dao.impl.BaseRolePermitDaoImpl;
import com.qing.right.dao.impl.BaseRoleUserDaoImpl;
import com.qing.right.dao.impl.BaseUserPermitDaoImpl;
import com.qing.right.service.IBaseModuleService;
import com.qing.vo.MenuVO;
import com.qing.vo.TreeNode;

@Service
@Transactional
public class BaseModuleServiceImpl implements IBaseModuleService {

    @Resource
    private BaseModuleDaoImpl baseModuleDaoImpl;

    @Resource
    private BaseServiceImpl<BaseModule, String> baseService;

    @Resource
    private BaseRolePermitDaoImpl baseRolePermitDaoImpl;

    @Resource
    private BaseRoleUserDaoImpl baseRoleUserDaoImpl;

    @Resource
    private BaseUserPermitDaoImpl baseUserPermitDaoImpl;

    @Resource
    private BaseOrganUserDaoImpl baseOrganUserDaoImpl;

    @Resource
    private BaseOrganUserPermitDaoImpl baseOrganUserPermitDaoImpl;

    // 当模块类型是0是才在权限设置加载出来
    private static final String MODULETYPE = "0";

    // 当上层模块是0，则是顶层模块
    private static final String TOPMODULETYPE = "0";

    @Override
    @Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
    public Map<String, BaseModule> getURLMap() throws ServiceException {
	Map<String, BaseModule> map = null;
	try {
	    map = baseModuleDaoImpl.getURLMap();
	} catch (DaoException e) {
	    ExceptionHandle.throwServiceException(e, ErrorCode.SERVICE_QUERY_ERROR_004, "BaseModuleServiceImpl.getURLMap()");
	}
	return map;
    }

    /**
     * 获取所有为类型为0的菜单
     * 
     * @see com.qing.right.service.IBaseModuleService#getBaseModules()
     * @author huangqingjian/0050
     * @see 相关函数,对于重要的函数建议注释
     * @since 深烟智能物流管理平台, 2013-1-23
     * @return
     * @throws ServiceException
     */
    @Override
    @Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
    public List<BaseModule> getBaseModules() throws ServiceException {
	List<BaseModule> list = null;
	try {
	    list = baseModuleDaoImpl.getBaseModules();
	} catch (DaoException e) {
	    ExceptionHandle.throwServiceException(e, ErrorCode.SERVICE_QUERY_ERROR_004, "BaseModuleServiceImpl.getURLMap()");
	}
	return list;
    }

    /**
     * 保存或者修改模块
     * 
     * @param baseModule
     * @throws ServiceException
     */
    public void saveOrUpdateBaseModule(BaseModule baseModule) throws ServiceException {
	try {
	    if (null != baseModule.getModuleId() && baseModule.getModuleId().equals("")) {
		baseModule.setModuleId(null);
		baseModule.setModuleType(MODULETYPE);
		if (baseModule.getModuleParentId().equals(TOPMODULETYPE)) {
		    baseModule.setModuleLev(String.valueOf(baseModuleDaoImpl.getModuleLevel()));
		} else {
		    baseModule.setModuleLev(String.valueOf(baseModuleDaoImpl.getModuleLevel(baseModule.getModuleParentId())));
		}
	    }

	    baseService.saveOrUpdate(baseModule);
	} catch (ServiceException e) {
	    ExceptionHandle.throwServiceException(e, ErrorCode.DAO_SAVE_ERROR_001, "BaseModuleServiceImpl.saveOrUpdateBaseModule()");
	} catch (DaoException e) {
	    ExceptionHandle.throwServiceException(e, ErrorCode.DAO_SAVE_ERROR_001, "BaseModuleServiceImpl.saveOrUpdateBaseModule()");
	}
    }

    /**
     * 删除菜单
     * 
     * @param ids
     * @throws ServiceException
     */
    @Override
    @Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
    public List<String> deleteBaseModule(String[] ids) throws ServiceException {
	List<String> list = new ArrayList<String>();
	if (null != ids && ids.length > 0) {

	    for (int i = 0; i < ids.length; i++) {
		try {
		    List<BaseModule> moduleList = baseModuleDaoImpl.getModuleListByParentId(ids[i]);
		    if (null != moduleList && moduleList.size() > 0) {
			list.add(ids[i]);
			continue;
		    }
		    BaseModule module = baseModuleDaoImpl.getBaseModule(ids[i]);
		    baseService.remove(module);

		} catch (DaoException e) {
		    ExceptionHandle.throwServiceException(e, ErrorCode.DAO_QUERY_ERROR_004, "BaseModuleServiceImpl.deleteBaseModule()");
		}
	    }

	}

	return list;
    }

    /**
     * 根据菜单ID将对应的菜单信息查询出来
     * 
     * @param dutiesId
     * @return
     * @throws DaoException
     */

    public BaseModule getBaseModule(String moduleId) throws ServiceException {

	BaseModule bm = null;
	try {
	    bm = baseModuleDaoImpl.getBaseModule(moduleId);
	} catch (DaoException e) {
	    ExceptionHandle.throwServiceException(e, ErrorCode.DAO_QUERY_ERROR_004, "BaseModuleServiceImpl.getBaseModule()");
	}
	return bm;
    }

    @Override
    public List<MenuVO> getTopModuleList() throws ServiceException {
	List<MenuVO> listMenuVOs = new ArrayList<MenuVO>();

	try {
	    List<Map<String, Object>> list = baseModuleDaoImpl.getTopModuleList();
	    for (Iterator<Map<String, Object>> itr = list.iterator(); itr.hasNext();) {
		Map<String, Object> map = itr.next();
		MenuVO vo = new MenuVO();
		if (null != map.get("MODULE_ID")) {
		    vo.setMenuId(map.get("MODULE_ID").toString());
		}
		if (null != map.get("MODULE_NAME")) {
		    vo.setMenuName(map.get("MODULE_NAME").toString());
		}
		listMenuVOs.add(vo);
	    }
	} catch (DaoException e) {
	    ExceptionHandle.throwServiceException(e, ErrorCode.DAO_QUERY_ERROR_004, "BaseModuleServiceImpl.getTopModuleList()");
	}

	return listMenuVOs;
    }

    @Override
    public List<TreeNode> getModuleList(BaseUser buser) throws ServiceException {

	if (!"1".equals(buser.getUserType())) {
	    return getOrganUserModuleList(buser);
	}
	List<TreeNode> topTreeNodes = new ArrayList<TreeNode>();
	try {
	    List<Map<String, Object>> list = baseModuleDaoImpl.getTopModuleList();

	    List<BaseModule> listModules = null;
	    List<BasePermit> listBasePermits = null;
	    for (Iterator<Map<String, Object>> itr = list.iterator(); itr.hasNext();) {
		Map<String, Object> map = itr.next();
		TreeNode node = new TreeNode();
		if (null != map.get("MODULE_ID")) {
		    node.setId(map.get("MODULE_ID").toString());
		}
		if (null != map.get("MODULE_NAME")) {
		    node.setText(map.get("MODULE_NAME").toString());
		}
		topTreeNodes.add(node);
		listModules = baseModuleDaoImpl.getModuleListByParentId(node.getId());
		for (BaseModule baseModule : listModules) {
		    // 为1的模块类型只供给超级管理员使用，不显示给别的用户
		    if (!MODULETYPE.equals(baseModule.getModuleType())) {
			continue;
		    }
		    TreeNode baseModuleNode = new TreeNode();
		    baseModuleNode.setId(baseModule.getModuleId());
		    baseModuleNode.setText(baseModule.getModuleName());
		    listBasePermits = baseModuleDaoImpl.getPermitListByParentId(baseModule.getModuleId());
		    for (BasePermit basePermit : listBasePermits) {
//			if (!MODULETYPE.equals(basePermit.getPermitType())) {
//			    continue;
//			}
			TreeNode basePermitNode = new TreeNode();
			basePermitNode.setId(basePermit.getPermitId());
			basePermitNode.setText(basePermit.getPermitDesc());
			baseModuleNode.addChild(basePermitNode);
		    }
		    node.addChild(baseModuleNode);

		}

	    }
	} catch (Exception e) {
	    ExceptionHandle.throwServiceException(e, ErrorCode.DAO_SAVE_ERROR_001, "BaseModuleServiceImpl.saveOrUpdateBaseModule()");
	}
	return topTreeNodes;
    }

    /**
     * 
     * 根据模块获取权限集合
     * 
     * @see com.qing.right.service.IBaseModuleService#getBasePermitsByModuleId(java.lang.String)
     * @author huangqingjian/0050
     * @see 相关函数,对于重要的函数建议注释
     * @since 深烟智能物流管理平台, 2013-1-27
     * @param moduleId
     * @return
     * @throws ServiceException
     */
    @Override
    public List<BasePermit> getBasePermitsByModuleId(String moduleId) throws ServiceException {
	List<BasePermit> list = null;
	try {
	    list = baseModuleDaoImpl.getPermitListByParentId(moduleId);
	} catch (Exception e) {
	    ExceptionHandle.throwServiceException(e, ErrorCode.DAO_SAVE_ERROR_001, "BaseModuleServiceImpl.saveOrUpdateBaseModule()");
	}
	return list;
    }

    /**
     * 获取组织管理的组织管理权限
     * 
     * @author huangqingjian/0050
     * @see 相关函数,对于重要的函数建议注释
     * @since 深烟智能物流管理平台, 2013-1-10
     * @Company: Tai woo. Co., Ltd
     * @param buser
     * @return
     * @throws ServiceException
     */
    @Override
    public List<TreeNode> getOrganUserModuleList(BaseUser buser) throws ServiceException {
	List<TreeNode> topTreeNodes = new ArrayList<TreeNode>();
	try {
	    List<BaseOrganUser> baseOrganUsers = baseOrganUserDaoImpl.getOrganUserListByUserId(buser.getUserId());

	    if (null == baseOrganUsers || baseOrganUsers.size() <= 0) {
		return null;
	    }

	    List<BaseOrganUserPermit> baseOrganUserPermits = baseOrganUserPermitDaoImpl.getPermitByOrganUserId(baseOrganUsers.get(0)
		    .getOrganUserId());

	    Set<String> permitIds = new HashSet<String>();
	    Set<String> moduleIds = new HashSet<String>();
	    Set<String> topModuleIds = new HashSet<String>();

	    for (BaseOrganUserPermit baseOrganUserPermit : baseOrganUserPermits) {
		permitIds.add(baseOrganUserPermit.getBasePermit().getPermitId());
		moduleIds.add(baseOrganUserPermit.getBasePermit().getBaseModule().getModuleId());
		topModuleIds.add(baseOrganUserPermit.getBasePermit().getBaseModule().getModuleParentId());
	    }

	    // 构造
	    buildPermitTree(topTreeNodes, permitIds, moduleIds, topModuleIds);
	} catch (Exception e) {
	    ExceptionHandle.throwServiceException(e, ErrorCode.DAO_SAVE_ERROR_001, "BaseModuleServiceImpl.saveOrUpdateBaseModule()");
	}
	return topTreeNodes;
    }

    /**
     * 获取普通用户权限树形结构
     * 
     * @author huangqingjian/0050
     * @see 相关函数,对于重要的函数建议注释
     * @since 深烟智能物流管理平台, 2013-1-11
     * @Company: Tai woo. Co., Ltd
     * @param buser
     * @return
     * @throws ServiceException
     */
    @Override
    public List<TreeNode> getBaseUserModuleList(BaseUser buser) throws ServiceException {
	List<TreeNode> topTreeNodes = new ArrayList<TreeNode>();

	try {

	    List<BaseRoleUser> baseRoleUsers = baseRoleUserDaoImpl.getRoleUserByUserId(buser.getUserId());
	    List<BaseUserPermit> baseUserPermits = baseUserPermitDaoImpl.getUserPermitByUserId(buser.getUserId());

	    Set<String> permitIds = new HashSet<String>();
	    Set<String> moduleIds = new HashSet<String>();
	    Set<String> topModuleIds = new HashSet<String>();

	    // 将用户所属的角色权限添加到列表
	    for (BaseRoleUser roleUser : baseRoleUsers) {

		List<BaseRolePermit> baseRolePermits = baseRolePermitDaoImpl.getRolePermitByRoleId(roleUser.getBaseRole().getRoleId());
		for (BaseRolePermit baseRolePermit : baseRolePermits) {
		    permitIds.add(baseRolePermit.getBasePermit().getPermitId());
		    moduleIds.add(baseRolePermit.getBasePermit().getBaseModule().getModuleId());
		    topModuleIds.add(baseRolePermit.getBasePermit().getBaseModule().getModuleParentId());
		}
	    }

	    // 将用户自身的权限添加到权限列表
	    for (BaseUserPermit userPermit : baseUserPermits) {
		permitIds.add(userPermit.getBasePermit().getPermitId());
		moduleIds.add(userPermit.getBasePermit().getBaseModule().getModuleId());
		topModuleIds.add(userPermit.getBasePermit().getBaseModule().getModuleParentId());
	    }

	    // 构造
	    buildPermitTree(topTreeNodes, permitIds, moduleIds, topModuleIds);
	} catch (Exception e) {
	    ExceptionHandle.throwServiceException(e, ErrorCode.DAO_SAVE_ERROR_001, "BaseModuleServiceImpl.saveOrUpdateBaseModule()");
	}
	return topTreeNodes;
    }

    /**
     * 获取角色权限树形结构
     * 
     * @author huangqingjian/0050
     * @see 相关函数,对于重要的函数建议注释
     * @since 深烟智能物流管理平台, 2013-1-11
     * @Company: Tai woo. Co., Ltd
     * @param buser
     * @return
     * @throws ServiceException
     */
    @Override
    public List<TreeNode> getBaseRoleModuleList(BaseRole role) throws ServiceException {
	List<TreeNode> topTreeNodes = new ArrayList<TreeNode>();
	try {
	    List<BaseRolePermit> baseRolePermits = baseRolePermitDaoImpl.getRolePermitByRoleId(role.getRoleId());
	    if (null == baseRolePermits || baseRolePermits.size() <= 0) {
		return null;
	    }

	    Set<String> permitIds = new HashSet<String>();
	    Set<String> moduleIds = new HashSet<String>();
	    Set<String> topModuleIds = new HashSet<String>();

	    // 将角色的权限添加到权限列表
	    for (BaseRolePermit rolePermit : baseRolePermits) {
		permitIds.add(rolePermit.getBasePermit().getPermitId());
		moduleIds.add(rolePermit.getBasePermit().getBaseModule().getModuleId());
		topModuleIds.add(rolePermit.getBasePermit().getBaseModule().getModuleParentId());
	    }

	    // 构造
	    buildPermitTree(topTreeNodes, permitIds, moduleIds, topModuleIds);
	} catch (Exception e) {
	    ExceptionHandle.throwServiceException(e, ErrorCode.DAO_SAVE_ERROR_001, "BaseModuleServiceImpl.saveOrUpdateBaseModule()");
	}
	return topTreeNodes;
    }

    /**
     * 构造权限列表
     * 
     * @author huangqingjian/0050
     * @see 相关函数,对于重要的函数建议注释
     * @since 深烟智能物流管理平台, 2013-1-11
     * @Company: Tai woo. Co., Ltd
     * @param topTreeNodes
     * @param permitIds
     * @param moduleIds
     * @param topModuleIds
     * @throws DaoException
     */
    private void buildPermitTree(List<TreeNode> topTreeNodes, Set<String> permitIds, Set<String> moduleIds, Set<String> topModuleIds)
	    throws DaoException {
	List<Map<String, Object>> list = baseModuleDaoImpl.getTopModuleList();

	List<BaseModule> listModules = null;
	List<BasePermit> listBasePermits = null;
	for (Iterator<Map<String, Object>> itr = list.iterator(); itr.hasNext();) {
	    Map<String, Object> map = itr.next();
	    TreeNode node = new TreeNode();
	    if (null != map.get("MODULE_ID")) {
		node.setId(map.get("MODULE_ID").toString());
	    }
	    if (null != map.get("MODULE_NAME")) {
		node.setText(map.get("MODULE_NAME").toString());
	    }

	    if (!topModuleIds.contains(node.getId())) {
		continue;
	    }
	    topTreeNodes.add(node);
	    listModules = baseModuleDaoImpl.getModuleListByParentId(node.getId());
	    for (BaseModule baseModule : listModules) {

		if (!moduleIds.contains(baseModule.getModuleId())) {
		    continue;
		}
		// 为1的模块类型只供给超级管理员使用，不显示给别的用户
		if (!MODULETYPE.equals(baseModule.getModuleType())) {
		    continue;
		}
		TreeNode baseModuleNode = new TreeNode();
		baseModuleNode.setId(baseModule.getModuleId());
		baseModuleNode.setText(baseModule.getModuleName());
		listBasePermits = baseModuleDaoImpl.getPermitListByParentId(baseModule.getModuleId());
		for (BasePermit basePermit : listBasePermits) {
//		    if (!MODULETYPE.equals(basePermit.getPermitType())) {
//			continue;
//		    }
		    if (!permitIds.contains(basePermit.getPermitId())) {
			continue;
		    }

		    TreeNode basePermitNode = new TreeNode();
		    basePermitNode.setId(basePermit.getPermitId());
		    basePermitNode.setText(basePermit.getPermitDesc());
		    baseModuleNode.addChild(basePermitNode);
		}
		node.addChild(baseModuleNode);

	    }

	}
    }
}
