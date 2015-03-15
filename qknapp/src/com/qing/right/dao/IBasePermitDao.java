package com.qing.right.dao;

import java.util.List;

import com.qing.common.exception.DaoException;
import com.qing.right.dao.domain.BaseRolePermit;

public interface IBasePermitDao {

    /**
     * 根据登陆号查询相关权限
     * 
     * @param em
     * @param loginCode
     * @return
     * @throws DaoException
     */
    List<Integer> queryPermitByLoginCode(String loginCode) throws DaoException;

    List<BaseRolePermit> queryPermitByRoleId(String RoleId) throws DaoException;
}