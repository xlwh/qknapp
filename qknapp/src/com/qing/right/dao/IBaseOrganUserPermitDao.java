package com.qing.right.dao;

import java.util.List;

import com.qing.common.exception.DaoException;
import com.qing.right.dao.domain.BaseOrganUserPermit;

public interface IBaseOrganUserPermitDao {
    /**
     * 依据用户ID,取得管理员所有的权限名列表
     * 
     * @param organUserId
     *            管理员ID
     * @return 管理员所有的权限ID列表
     * @throws DaoException
     */
    public List<String> getPermitNameByUserId(String userId) throws DaoException;

    /**
     * 保存管理员的权限
     * 
     * @param organUserId
     *            管理员ID
     * @param permitIdList
     *            权限ID列表
     * @throws BusinessException
     */
    public void saveByOrganUserId(String organUserId, List<String> permitIdList) throws DaoException;

    /**
     * 依据管理员ID,删除所有权限
     * 
     * @param organUserId
     * @throws DaoException
     */
    public void deleteByOrganUserId(String organUserId) throws DaoException;

    /**
     * 依据管理员ID,取得其管理员权限对象的ID列表
     * 
     * @param organUserId
     *            管理员ID
     * @return 管理员权限对象的ID列表
     * @throws DaoException
     */
    List<String> getOrganUserPermitIdByOrganUserId(String organUserId) throws DaoException;

    /**
     * 依据管理员ID,取得其管理员权限对象的ID列表
     * 
     * @param organUserId
     *            管理员ID
     * @return 管理员权限对象的ID列表
     * @throws DaoException
     */
    List<String> getPermitIdByOrganUserId(String organUserId) throws DaoException;

    List<BaseOrganUserPermit> getPermitByOrganUserId(String userId) throws DaoException;

}