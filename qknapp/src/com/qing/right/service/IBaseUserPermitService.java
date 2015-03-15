package com.qing.right.service;

import java.util.List;

import com.qing.common.exception.ServiceException;

public interface IBaseUserPermitService {

    /**
     * 保存用户权限(通用权限，可授权他人)
     * 
     * @param userId
     *            用户ID
     * @param permitIdList
     *            权限ID列表
     * @throws ServiceException
     */
    public void saveByUserId(String userId, List<String> permitIdList) throws ServiceException;

    /**
     * 依据用户ID,取得用户通用权限ID列表
     * 
     * @param userId
     *            用户ID
     * @return 用户通用权限ID列表
     * @throws ServiceException
     */
    public List<String> getPermitIdByUserId(String userId) throws ServiceException;

    /**
     * 依据用户ID,取得用户通用权限名称列表
     * 
     * @param userId
     *            用户ID
     * @return 权限名称列表
     * @throws ServiceException
     */
    public List<String> getPermitNameByUserId(String userId) throws ServiceException;

    /**
     * 依据用户ID,删除用户权限
     * 
     * @param userId
     *            用户ID
     * @throws ServiceException
     */
    public void deleteByUserId(String userId) throws ServiceException;

    /**
     * 依据用户ID,取得用户权限列表
     * 
     * @param userId
     *            用户ID
     * @return 用户权限列表
     * @throws ServiceException
     */
    public List<String> getUserPermitIdByUserId(String userId) throws ServiceException;

    /**
     * 依据用户ID,取得用户总权限列表
     * 
     * @param userId
     *            用户ID
     * @return 用户总权限列表
     * @throws ServiceException
     */
    public List<String> getUserAllPermitIdsByUserId(String userId) throws ServiceException;

    /**
     * 获取角色权限ID
     * 
     * @author huangqingjian/0050
     * @see 相关函数,对于重要的函数建议注释
     * @since 深烟智能物流管理平台, 2013-1-19
     * @Company: Tai woo. Co., Ltd
     * @param userId
     * @return
     * @throws ServiceException
     */
    List<String> getRolePermitIdByUserId(String userId) throws ServiceException;

    List<String> getRoleNameByUserId(String userId) throws ServiceException;
}
