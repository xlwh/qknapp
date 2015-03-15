package com.qing.right.service;

import java.util.List;

import com.qing.common.exception.ServiceException;
import com.qing.vo.OrganVO;

/**
 * @author guojun
 * @version 创建时间：2012-11-26 上午11:43:59
 */
public interface IOrganJdbcService {

    /**
     * 获取organId下面的所有子organids列表
     * 
     * @param organId
     */
    public String getOragnIds(String organId) throws ServiceException;

    /**
     * 获取organId下面的所有组织列表
     * 
     * @param organId
     */
    @SuppressWarnings("rawtypes")
	public List getOragnList(String organId) throws ServiceException;

    /**
     * 获取组织树
     * 
     * @param organId
     * @return
     * @throws ServiceException
     */
    public List<OrganVO> getOrganTree(String organId) throws ServiceException;

    /**
     * 获取所有公司类型的组织树
     * (一句话功能简述)
     * (功能详细描述)
     * @author       huangqingjian/0050
     * @see          相关函数,对于重要的函数建议注释
     * @since        深烟智能物流管理平台, 2013-1-8
     * @param organId
     * @return
     * @throws ServiceException
     */
    public List<OrganVO> getCompanyTree(String organId) throws ServiceException;

}
