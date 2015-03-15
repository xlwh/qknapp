package com.qing.right.service;

import java.util.List;

import com.qing.common.exception.ServiceException;
import com.qing.common.util.Page;
import com.qing.right.dao.domain.BaseIpType;

public interface IBaseIpTypeService {
    /**
     * 根据查询条件取得IP限制信息
     * 
     * @param params
     *            参数
     * @return
     * @throws ServiceException
     */
    @SuppressWarnings("rawtypes")
	public Page<BaseIpType> queryBaseIpType(Page page, Object[] params) throws ServiceException;

    /**
     * 查询允许访问的IP地址范围
     * 
     * @return 列表，元素格式为字符数组[开始IP,结束IP,用户登录名集合]
     * @throws ServiceException
     */
    @SuppressWarnings("rawtypes")
	public List findAllowedIpRange() throws ServiceException;

    /**
     * 查询允许访问的IP地址段. 例如: 192.168.0.* 192.168.* 192.*
     * 
     * @return 列表，元素格式为字符数组[网段,用户登录名集合]
     * @throws ServiceException
     */
    @SuppressWarnings("rawtypes")
	public List findAllowedIpSegment() throws ServiceException;

    /**
     * 查询禁止访问的IP地址范围
     * 
     * @return 列表，元素格式为字符数组[开始IP,结束IP,用户登录名集合]
     * @throws ServiceException
     */
    @SuppressWarnings("rawtypes")
	public List findRefusedIpRange() throws ServiceException;

    /**
     * 查询禁止访问的IP地址段. 例如: 192.168.0.* 192.168.* 192.*
     * 
     * @return 列表，元素格式为字符数组[网段,用户登录名集合]
     * @throws ServiceException
     */
    @SuppressWarnings("rawtypes")
	public List findRefusedIpSegment() throws ServiceException;

}
