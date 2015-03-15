package com.qing.common.base.service;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import com.qing.common.exception.ServiceException;
import com.qing.common.util.Page;

/**
 * Title: BaseService <br>
 * Description: 用于DB操作的service接口 <br>
 * CreateTime: 2012-08-07 16:39 <br>
 * Copyright: Copyright (c) 2012 taiheiot.com<br>
 * 
 * @author guojun
 * @since 1.0
 */
public interface BaseService<T, PK extends Serializable> {

    /*----------------------Hibernate方法------------------------------*/
    /**
     * 保存实体对象
     * 
     * @param entity
     *            待保存的实体
     * @throws ServiceException
     *             服务层异常
     */
    public void save(T entity) throws ServiceException;

    /**
     * 删除实体对象
     * 
     * @param entity
     *            删除对象
     * @throws ServiceException
     *             服务层异常
     */
    public void remove(final T entity) throws ServiceException;

    /**
     * 删除实体对象by hql
     * 
     * @param entity
     *            删除对象
     * @throws ServiceException
     *             服务层异常
     */
    public void remove(String hql, Object[] obj) throws ServiceException;

    /**
     * 批量删除实体对象
     * 
     * @param entity
     *            删除对象
     * @throws ServiceException
     *             服务层异常
     */
    public void removeAll(final Collection<T> entities) throws ServiceException;

    /**
     * 保存或更新实体对象，entity的id为空就是增加，entity的id不为null就是修改
     * 
     * @param entity
     *            待保存的实体
     * @return 实体的id
     * @throws ServiceException
     *             服务层异常
     */
    public void saveOrUpdate(final T entity) throws ServiceException;

    /**
     * 保存或更新一组实体对象，entity的id为空就是增加，entity的id不为null就是修改
     * 
     * @param entities
     *            待保存的实体集合
     * @throws ServiceException
     *             服务层异常
     */
    public void saveOrUpdateAll(final List<T> entities) throws ServiceException;

    /**
     * 删除一组实体对象
     * 
     * @param entities
     *            待删除对象集合
     * @throws ServiceException
     *             服务层异常
     */
    public void remove(final List<T> entities) throws ServiceException;

    /**
     * 根据实体类的类型和id查询
     * 
     * @param entity
     *            实体类的类型
     * @param id
     *            实体的id
     * @return 查询结果实体
     * @throws ServiceException
     *             服务层异常
     */
    public T findById(Class<T> c, PK id) throws ServiceException;

    /**
     * 查找实体类对象BY values
     * 
     * @param entityClass
     *            实体Class
     * @param values
     *            参数value
     * @param cache
     *            是否采用缓存读取
     * @return 实体对象集合
     * 
     *         throws ServiceException 数据访问异常
     */
    public List<T> findByValues(final String hql, final Object[] values, final boolean cache) throws ServiceException;

    /**
     * 查找偏移量实体类列表对象BY values
     * 
     * @param entityClass
     *            实体Class
     * @param values
     *            参数value
     * @param cache
     *            是否采用缓存读取
     * @return 实体对象集合
     * 
     *         throws ServiceException 数据访问异常
     */
    public List<T> findByValues(final String hql, final Object[] values, final int startIndex, final int maxResults, final boolean cache)
	    throws ServiceException;

    /**
     * 按名称去查询
     * 
     * @param entityClass
     *            实体
     * @return 集合 throws ServiceException 数据访问异常
     */
    public List<T> findByNamedQuery(final String queryName, final Object[] values) throws ServiceException;

    /**
     * 按名称偏移量去查询
     * 
     * @param entityClass
     *            实体
     * @return 集合 throws ServiceException 数据访问异常
     */
    @SuppressWarnings("rawtypes")
	public List findByNamedQuery(final String queryName, final Object[] values, final int startIndex, final int maxResults)
	    throws ServiceException;

    /**
     * 读取实体的总数量
     * 
     * @author guojun
     * 
     * @param hsql
     *            hsql语句
     * @version 创建时间：2012-8-21 下午7:17:20 throws ServiceException 数据访问异常
     */
    public int findNumByHQL(String hsql, final Object[] values) throws ServiceException;

    /**
     * 
     * 按HQL分页查询.
     * 
     * @param page
     *            页面对象
     * @param hql
     *            HQL语句
     * @param values
     *            可变参数列表
     * @throws ServiceException
     *             服务层异常
     */
    public Page<T> findByPage(final Page<T> page, final String hql, final Object[] values) throws ServiceException;

    /*-------------------------------原生态sql方法---------------------------------*/
    /**
     * 按原生SQL查询
     * 
     * @param sql
     *            sql语句 throws ServiceException 数据访问异常
     */
    @SuppressWarnings("rawtypes")
	public List findBySQL(final String sql, final Object[] values) throws ServiceException;

    /**
     * 按原生SQL查询
     * 
     * @param sql
     *            sql语句 throws DaoException 数据访问异常
     */
    public void excuteSQL(final String Sql, final Object[] values) throws ServiceException;

    /**
     * 读取实体的总数量by hsql
     * 
     * @author guojun
     * 
     * @param sql
     *            sql语句
     * @param values
     *            Object对象集合
     * @version 创建时间：2012-8-21 下午7:17:20 throws ServiceException 数据访问异常
     */
    public int findNumBySQL(String sql, final Object[] values) throws ServiceException;

    /**
     * 按SQL分页查询.
     * 
     * @param sql
     *            sql语句
     * @param page
     *            page对象
     * @param values
     *            object对象列表
     * @return 分页数据
     * @throws ServiceException
     *             服务层异常
     */
    public List<Object> pageSqlList(final String sql, final Page<Object> page, final Object[] values) throws ServiceException;

    /**
     * 执行存储过程
     * 
     * @param procKey
     *            配置文件中的存储过程键值
     * @param values
     *            参数数组,可为null
     * @throws ServiceException
     *             服务层异常
     */
    public void executeProcedure(final String procKey, final Object[] values) throws ServiceException;



}
