package com.qing.right.service;

import java.util.List;

import com.qing.common.exception.ServiceException;
import com.qing.right.dao.domain.BaseRolePermit;
import com.qing.right.dao.domain.BaseUser;
import com.qing.vo.RolePermitVO;

public interface IBaseRolePermitService {

    /**
     * 保存角色权限
     * 
     * @param roleId
     *            角色ID
     * @param permitIdList
     *            权限ID列表
     * @throws ServiceException
     */
    public void saveByRoleId(String roleId, List<String> permitIdList) throws ServiceException;

    /**
     * 依据角色ID,取得角色权限ID列表
     * 
     * @param roleId
     *            角色ID
     * @return 角色权限ID列表
     * @throws ServiceException
     */
    public List<String> getPermitIdByRoleId(String roleId) throws ServiceException;

    /**
     * 依据角色ID,删除角色权限
     * 
     * @param roleId
     *            角色ID
     * @throws ServiceException
     */
    public void deleteByRoleId(String roleId) throws ServiceException;

    /**
     * 依据角色ID,取得角色权限列表
     * 
     * @param roleId
     *            角色ID
     * @return 角色权限列表
     * @throws ServiceException
     */
    public List<BaseRolePermit> getRolePermitIdByRoleId(String roleId) throws ServiceException;

    /**
     * 依据角色ID,取得角色权限名称列表
     * 
     * @param roleId
     *            角色ID
     * @return 角色权限名称列表
     * @throws ServiceException
     */
    public List<String> getPermitNameByRoleId(String roleId) throws ServiceException;

    /**
     * 依据当前登陆用户,取得角色权限详细列表
     * 
     * @return 角色权限详细列表
     * @throws ServiceException
     */
    public List<RolePermitVO> getPermitNameAndDesc(BaseUser user) throws ServiceException;

    /**
     * 根绝角色ID和权限ID删除角色权限
     * 
     * @author huangqingjian/0050
     * @see 相关函数,对于重要的函数建议注释
     * @since 深烟智能物流管理平台, 2013-1-14
     * @Company: Tai woo. Co., Ltd
     * @param roleId
     * @param PermitIds
     * @throws ServiceException
     */
    public void deleteRolePermit(String roleId, String[] PermitIds) throws ServiceException;

}
