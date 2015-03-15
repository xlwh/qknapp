package com.qing.right.service;

import java.util.List;

import com.qing.common.exception.ServiceException;

public interface IBaseOrganUserPermitService {

    /**
     * 依据用户ID,取得管理员所有的权限名称列表
     * 
     * @param organUserId
     *            管理员ID
     * @return 管理员所有的权限ID列表
     * @throws ServiceException
     */
    public List<String> getPermitNameByUserId(String userId) throws ServiceException;

    /**
     * 保存管理员的权限
     * 
     * @param organUserId
     *            管理员ID
     * @param permitIdList
     *            权限ID列表
     * @throws ServiceException
     */
    public void saveByOrganUserId(String organUserId, List<String> permitIdList) throws ServiceException;

    /**
     * 
     * 依据用户ID,取得管理员所有的权限ID列表
     * @author       huangqingjian/0050
     * @see          相关函数,对于重要的函数建议注释
     * @since        深烟智能物流管理平台, 2013-1-17
     * @Company:      Tai woo. Co., Ltd	
     * @param organUserId
     * @return
     * @throws ServiceException
     */
    List<String> getPermitIdByorganUserId(String organUserId) throws ServiceException;
    


}
