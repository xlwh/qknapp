package com.qing.right.dao;

import java.util.List;

import com.qing.common.exception.DaoException;
import com.qing.common.util.Page;

public interface IBaseIpTypeDao {
    /**
     * 根据查询条件取得IP限制信息
     * 
     * @param params
     *            参数
     * @return
     * @throws DaoException
     */
    @SuppressWarnings("rawtypes")
	public Page queryBaseIpType(Page page, Object[] params) throws DaoException;

    /**
     * 查询允许访问的IP地址范围
     * 
     * @return 列表，元素格式为字符数组[开始IP,结束IP,用户登录名集合]
     * @throws DaoException
     */
    @SuppressWarnings("rawtypes")
	public List findAllowedIpRange() throws DaoException;

    /**
     * 查询允许访问的IP地址段. 例如: 192.168.0.* 192.168.* 192.*
     * 
     * @return 列表，元素格式为字符数组[网段,用户登录名集合]
     * @throws DaoException
     */
    @SuppressWarnings("rawtypes")
	public List findAllowedIpSegment() throws DaoException;

    /**
     * 查询禁止访问的IP地址范围
     * 
     * @return 列表，元素格式为字符数组[开始IP,结束IP,用户登录名集合]
     * @throws DaoException
     */
    @SuppressWarnings("rawtypes")
	public List findRefusedIpRange() throws DaoException;

    /**
     * 查询禁止访问的IP地址段. 例如: 192.168.0.* 192.168.* 192.*
     * 
     * @return 列表，元素格式为字符数组[网段,用户登录名集合]
     * @throws DaoException
     */
    @SuppressWarnings("rawtypes")
	public List findRefusedIpSegment() throws DaoException;

}