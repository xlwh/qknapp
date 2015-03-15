package com.qing.right.dao;

import com.qing.common.exception.DaoException;
import com.qing.right.dao.domain.BaseOrganDetail;

public interface IBaseOrganDetailDao
{
    /**
     * 
     * 
     * Description:根据组织ID查询组织详细信息
     * @param OrganId
     * @return
     * @throws DaoException
     */
    public BaseOrganDetail getBaseOrganDetailByOrganId(String OrganId) throws DaoException;

    /**
     * 
     * 
     * Description:根据组织ID删除某个组织详细信息
     * @param OrganId
     * @throws DaoException
     */
    public void deleteByOrganId(String OrganId) throws DaoException;
}
