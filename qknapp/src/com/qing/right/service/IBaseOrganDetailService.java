package com.qing.right.service;

import com.qing.common.exception.ServiceException;
import com.qing.right.dao.domain.BaseOrganDetail;

/**
 * 
 * Description:组织详细信息Service接口
 * @author chen,hao-hao
 * @date 2013-8-2 下午4:27:18
 * @since 1.0
 */
public interface IBaseOrganDetailService
{
    /**
     * 
     * 
     * Description:根据组织ID查询组织详细信息
     * @param OrganId
     * @return
     * @throws ServiceException
     */
    public BaseOrganDetail getBaseOrganDetailByOrganId(String OrganId) throws ServiceException;

    /**
     * 
     * 
     * Description:根据组织ID删除某个组织详细信息
     * @param OrganId
     * @throws ServiceException
     */
    public void deleteByOrganId(String OrganId) throws ServiceException;
}
