package com.qing.right.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qing.common.exception.DaoException;
import com.qing.common.exception.ServiceException;
import com.qing.common.exception.handle.ExceptionHandle;
import com.qing.common.util.ErrorCode;
import com.qing.right.dao.IBaseOrganDetailDao;
import com.qing.right.dao.domain.BaseOrganDetail;
import com.qing.right.service.IBaseOrganDetailService;

@Service
@Transactional
public class BaseOrganDetailServiceImpl implements IBaseOrganDetailService
{
    @Resource
    private IBaseOrganDetailDao baseOrganDetailDaoImpl;

    @Override
    public BaseOrganDetail getBaseOrganDetailByOrganId(String OrganId) throws ServiceException
    {
        BaseOrganDetail baseOrganDetail = null;
        try
        {
            baseOrganDetail = baseOrganDetailDaoImpl.getBaseOrganDetailByOrganId(OrganId);
        }
        catch (DaoException e)
        {
            ExceptionHandle.throwServiceException(e, ErrorCode.SERVICE_QUERY_ERROR_004,
                    "BaseOrganDetailServiceImpl.getBaseOrganDetailByOrganId" + "根据组织ID查询组织详细信息失败");
        }

        return baseOrganDetail;
    }

    @Override
    public void deleteByOrganId(String OrganId) throws ServiceException
    {
        try
        {
            baseOrganDetailDaoImpl.deleteByOrganId(OrganId);
        }
        catch (DaoException e)
        {
            ExceptionHandle.throwServiceException(e, ErrorCode.SERVICE_DELETE_ERROR_002, "BaseOrganDetailServiceImpl.deleteByOrganId"
                    + "根据组织ID删除组织详细信息失败");
        }

    }

}
