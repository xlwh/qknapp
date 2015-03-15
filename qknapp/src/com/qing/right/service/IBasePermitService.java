package com.qing.right.service;

import java.util.List;

import com.qing.common.exception.ServiceException;
import com.qing.right.dao.domain.BasePermit;

public interface IBasePermitService {

    /**
     * 根据登陆号查询相关权限
     * 
     * @param loginCode
     * @return
     * @throws ServiceException
     */
    List<Integer> queryPermitByLoginCode(String loginCode) throws ServiceException;

    /**
     * 查询所有权限
     * 
     * @author huangqingjian/0050
     * @see 相关函数,对于重要的函数建议注释
     * @since 深烟智能物流管理平台, 2013-1-15
     * @Company: Tai woo. Co., Ltd
     * @return
     * @throws ServiceException
     */
    List<BasePermit> findAllPermits() throws ServiceException;

    void saveOrUpdateBasePermit(BasePermit basePermit) throws ServiceException;

    void delBasePermit(String basePermitId) throws ServiceException;
}
