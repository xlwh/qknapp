package com.qing.right.dao;

import java.util.List;

import com.qing.common.exception.DaoException;
import com.qing.right.dao.domain.BaseUserPermit;

public interface IBaseUserPermitDao {

    public void saveByUserId(String userId, List<String> permitIdList) throws DaoException;

    public List<String> getPermitIdByUserId(String userId) throws DaoException;

    public void deleteByUserId(String userId) throws DaoException;

    public List<String> getUserPermitIdByUserId(String userId) throws DaoException;

    public List<String> getPermitNameByUserId(String userId) throws DaoException;

    List<BaseUserPermit> getUserPermitByUserId(String userId) throws DaoException;

    List<BaseUserPermit> getBaseUserPermitByUserId(String userId) throws DaoException;

}