package com.qing.right.dao;

import java.util.List;

import com.qing.common.exception.DaoException;
import com.qing.right.dao.domain.BaseRolePermit;

public interface IBaseRolePermitDao {
    public void saveByRoleId(String roleId, List<String> permitIdList) throws DaoException;

    public List<String> getPermitIdByRoleId(String roleId) throws DaoException;

    public void deleteByRoleId(String roleId) throws DaoException;

    public List<BaseRolePermit> getRolePermitByRoleId(String roleId) throws DaoException;

    public List<String> getPermitNameByRoleId(String roleId) throws DaoException;

    /**
     * 根据角色ID和权限ID删除角色权限
     * 
     * @author huangqingjian/0050
     * @see 相关函数,对于重要的函数建议注释
     * @since 深烟智能物流管理平台, 2013-1-14
     * @Company: Tai woo. Co., Ltd
     * @param roleId
     * @param permitId
     * @throws DaoException
     */
    public void deleteByRoleIdAndUserId(String roleId, String permitId) throws DaoException;

    List<BaseRolePermit> getRolePermitByRoleIds(String roleId) throws DaoException;
}