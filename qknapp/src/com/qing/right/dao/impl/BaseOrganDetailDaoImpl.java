package com.qing.right.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.qing.common.base.dao.HibernateDao;
import com.qing.common.exception.DaoException;
import com.qing.common.exception.handle.ExceptionHandle;
import com.qing.common.util.ErrorCode;
import com.qing.right.dao.IBaseOrganDetailDao;
import com.qing.right.dao.domain.BaseOrganDetail;

@Service
@Repository
public class BaseOrganDetailDaoImpl implements IBaseOrganDetailDao
{

    @Resource
    private HibernateDao<BaseOrganDetail, String> hibernateDaoBaseOrganDetail;

    @Override
    public BaseOrganDetail getBaseOrganDetailByOrganId(String OrganId) throws DaoException
    {
        List<BaseOrganDetail> baseOrganDetails = new ArrayList<BaseOrganDetail>();
        StringBuffer hql = new StringBuffer();
        hql.append("from BaseOrganDetail bod where bod.organId = ?");
        try
        {
            baseOrganDetails = hibernateDaoBaseOrganDetail.findByValues(hql.toString(), new Object[]
            { OrganId }, false);
        }
        catch (DaoException e)
        {
            ExceptionHandle.throwDaoException(e, ErrorCode.DAO_QUERY_ERROR_004, "BaseOrganDetailDaoImpl.getBaseOrganDetailByOrganId"
                    + "根据组织ID查询组织详细信息失败");

        }
        if (baseOrganDetails.size() > 0)
        {
            return baseOrganDetails.get(0);
        }
        else
        {
            return null;
        }

    }

    @Override
    public void deleteByOrganId(String OrganId) throws DaoException
    {
        StringBuffer hql = new StringBuffer();
        hql.append("delete from BaseOrganDetail bod where bod.organId = ?");
        try
        {
            hibernateDaoBaseOrganDetail.remove(hql.toString(), new Object[]
            { OrganId });
        }
        catch (DaoException e)
        {
            ExceptionHandle.throwDaoException(e, ErrorCode.DAO_DELETE_ERROR_002, "BaseOrganDetailDaoImpl.deleteByOrganId"
                    + "根据组织ID删除组织详细信息失败");
        }
    }

}