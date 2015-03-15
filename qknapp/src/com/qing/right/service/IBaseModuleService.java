package com.qing.right.service;

import java.util.List;
import java.util.Map;

import com.qing.common.exception.ServiceException;
import com.qing.right.dao.domain.BaseModule;
import com.qing.right.dao.domain.BasePermit;
import com.qing.right.dao.domain.BaseRole;
import com.qing.right.dao.domain.BaseUser;
import com.qing.vo.MenuVO;
import com.qing.vo.TreeNode;

public interface IBaseModuleService {

    /**
     * 获取所有的模块权限
     * 
     * @return
     * @throws ServiceException
     */
    public Map<String, BaseModule> getURLMap() throws ServiceException;

    /**
     * 保存或者修改模块
     * 
     * @param baseLog
     * @throws ServiceException
     */
    public void saveOrUpdateBaseModule(BaseModule baseModule) throws ServiceException;

    /**
     * 获取管理员模块权限
     * 
     * @param baseLog
     * @throws ServiceException
     */
    public List<TreeNode> getModuleList(BaseUser buser) throws ServiceException;

    /**
     * 获取角色权限列表
     * 
     * @author huangqingjian/0050
     * @see 相关函数,对于重要的函数建议注释
     * @since 深烟智能物流管理平台, 2013-1-11
     * @Company: Tai woo. Co., Ltd
     * @param role
     * @return
     * @throws ServiceException
     */
    public List<TreeNode> getBaseRoleModuleList(BaseRole role) throws ServiceException;

    /**
     * 获取用户权限列表
     * 
     * @author huangqingjian/0050
     * @see 相关函数,对于重要的函数建议注释
     * @since 深烟智能物流管理平台, 2013-1-11
     * @Company: Tai woo. Co., Ltd
     * @param buser
     * @return
     * @throws ServiceException
     */
    public List<TreeNode> getBaseUserModuleList(BaseUser buser) throws ServiceException;

    /**
     * 获取组织管理员权限列表
     * 
     * @author huangqingjian/0050
     * @see 相关函数,对于重要的函数建议注释
     * @since 深烟智能物流管理平台, 2013-1-11
     * @Company: Tai woo. Co., Ltd
     * @param buser
     * @return
     * @throws ServiceException
     */
    public List<TreeNode> getOrganUserModuleList(BaseUser buser) throws ServiceException;

    public List<String> deleteBaseModule(String[] ids) throws ServiceException;

    /**
     * 获取顶层菜单
     * 
     * @author huangqingjian/0050
     * @see 相关函数,对于重要的函数建议注释
     * @since 深烟智能物流管理平台, 2013-1-23
     * @Company: Tai woo. Co., Ltd
     * @return
     * @throws ServiceException
     */
    List<MenuVO> getTopModuleList() throws ServiceException;

    List<BaseModule> getBaseModules() throws ServiceException;

    List<BasePermit> getBasePermitsByModuleId(String moduleId) throws ServiceException;

}
