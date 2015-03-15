package com.qing.right.service;

import java.util.List;

import com.qing.common.exception.DaoException;
import com.qing.common.exception.ServiceException;
import com.qing.common.util.Page;
import com.qing.right.dao.domain.BaseUser;
import com.qing.vo.RoleUserVO;

public interface IBaseRoleUserService {
    /**
     * 依据用户ID,取得用户所属角色ID列表
     * 
     * @param userId
     *            用户ID
     * @return 用户所属角色ID列表
     * @throws DaoException
     */
    public List<String> getRoleIdByUserId(String userId) throws ServiceException;

    /**
     * 保存角色用户关系
     * 
     * @param roleId
     *            角色ID
     * @param userIdList
     *            用户ID列表
     * @throws ServiceException
     */
    public void saveByRoleId(String roleId, List<String> userIdList) throws ServiceException;

    /**
     * 依据角色ID,取得用户ID列表
     * 
     * @param roleId
     *            角色ID
     * @return 用户ID列表
     * @throws ServiceException
     */
    public List<String> getUserIdByRoleId(String roleId) throws ServiceException;

    /**
     * 依据角色ID,取得用户信息列表
     * 
     * @param roleId
     *            角色ID
     * @return 用户信息列表
     * @throws ServiceException
     */
    public List<BaseUser> getBaseUserByRoleId(String roleId, Object[] params) throws ServiceException;

    /**
     * 依据角色ID,取得包含用户信息
     * 
     * @param roleId
     *            角色ID
     * @param params
     *            参数
     * @return
     * @throws ServiceException
     */
    @SuppressWarnings("rawtypes")
	public Page queryBaseUserByRoleId(Page page, String roleId, Object[] params) throws ServiceException;

    /**
     * 依据角色ID,取得包含用户信息
     * 
     * @param organId
     *            组织ID
     * @param params
     *            参数
     * @return
     * @throws ServiceException
     */
    public List<RoleUserVO> getRoleUserByRoleId(String roleId) throws ServiceException;

    /**
     * 
     * 根据角色ID和用户ID删除角色
     * 
     * @author huangqingjian/0050
     * @see 相关函数,对于重要的函数建议注释
     * @since 深烟智能物流管理平台, 2013-1-14
     * @Company: Tai woo. Co., Ltd
     * @param roleId
     * @param userIds
     * @throws ServiceException
     */
    public void deleteRoleUser(String roleId, String[] userIds) throws ServiceException;
}
