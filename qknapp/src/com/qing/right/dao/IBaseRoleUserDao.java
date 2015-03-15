package com.qing.right.dao;

import java.util.List;

import com.qing.common.exception.DaoException;
import com.qing.common.util.Page;
import com.qing.right.dao.domain.BaseRoleUser;
import com.qing.right.dao.domain.BaseUser;

public interface IBaseRoleUserDao {

    /**
     * 依据用户ID,取得角色ID列表
     * 
     * @param userId
     *            用户ID
     * @return 角色ID列表
     * @throws DaoException
     */
    public List<String> getRoleIdByUserId(String userId) throws DaoException;

    /**
     * 保存角色用户关系
     * 
     * @param roleId
     *            角色ID
     * @param userIdList
     *            用户ID列表
     * @throws DaoException
     */
    public void saveByRoleId(String roleId, List<String> userIdList) throws DaoException;

    /**
     * 依据角色ID,取得用户ID列表
     * 
     * @param roleId
     *            角色ID
     * @return 用户ID列表
     * @throws DaoException
     */
    public List<String> getUserIdByRoleId(String roleId) throws DaoException;

    /**
     * 依据角色ID,取得用户信息列表
     * 
     * @param roleId
     *            角色ID
     * @return 用户信息列表
     * @throws DaoException
     */
    public List<BaseUser> getBaseUserByRoleId(String roleId, Object[] params) throws DaoException;

    /**
     * 依据角色ID,取得包含用户信息
     * 
     * @param roleId
     *            角色ID
     * @param params
     *            参数
     * @return
     * @throws DaoException
     */
    @SuppressWarnings("rawtypes")
	public Page queryBaseUserByRoleId(Page page, String roleId, Object[] params) throws DaoException;

    /**
     * 依据角色ID,取得包含用户信息
     * 
     * @param organId
     *            组织ID
     * @param params
     *            参数
     * @return
     * @throws DaoException
     */
    public List<BaseRoleUser> getRoleUserByRoleId(String roleId) throws DaoException;

    /**
     * 根据角色ID删除角色用户 (一句话功能简述) (功能详细描述)
     * 
     * @author huangqingjian/0050
     * @see 相关函数,对于重要的函数建议注释
     * @since 深烟智能物流管理平台, 2013-1-14
     * @Company: Tai woo. Co., Ltd
     * @param roleId
     * @throws DaoException
     */
    public void deleteByRoleId(String roleId) throws DaoException;

    /**
     * 根据角色ID和用户ID删除角色用户
     * 
     * @author huangqingjian/0050
     * @see 相关函数,对于重要的函数建议注释
     * @since 深烟智能物流管理平台, 2013-1-14
     * @Company: Tai woo. Co., Ltd
     * @param roleId
     * @param userId
     * @throws DaoException
     */
    public void deleteByRoleIdAndUserId(String roleId, String userId) throws DaoException;

    /**
     * 
     * 根据用户ID获取角色用户列表
     * 
     * @author huangqingjian/0050
     * @see 相关函数,对于重要的函数建议注释
     * @since 深烟智能物流管理平台, 2013-1-17
     * @Company: Tai woo. Co., Ltd
     * @param userId
     * @return
     * @throws DaoException
     */
    List<BaseRoleUser> getRoleUserByUserId(String userId) throws DaoException;

    List<String> getRoleNameByUserId(String userId) throws DaoException;
}