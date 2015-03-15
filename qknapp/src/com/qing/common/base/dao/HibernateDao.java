/**
 * 公共的一些类放在common包里面
 */
package com.qing.common.base.dao;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * 自定义DAO异常（这样可以让错误更清楚）
 */
import com.qing.common.exception.DaoException;
import com.qing.common.util.Page;

/**
 * Title: HibernateDao <br>
 * Description: hibernate基本的数据访问操作接口(DAO) <br>
 * CreateTime: 2012-08-07 16:39 <br>
 * Copyright: Copyright (c) 2012 taiheiot.com<br>
 * 
 * @author guojun
 * @since 1.0
 * 
 */

/**
 * 这个接口是一个泛型
 * T：调用这个类的试题类型
 * PK：主键，都是Serializable的子类
 */
public interface HibernateDao<T, PK extends Serializable> {

    /**
     * 保存实体对象
     * 
     * @param entity
     *            待保存的实体 throws DaoException 数据访问异常
     */
    public void save(T entity) throws DaoException;

    /**
     * 删除实体对象
     * 
     * @param entity
     *            实体对象 throws DaoException 数据访问异常
     */
    public void remove(T entity) throws DaoException;

    /**
     * 删除实体对象BY values
     * 
     * @param hsql
     *            HSQL语句
     * @param values
     *            values 参数 throws DaoException 数据访问异常
     */
    public void remove(final String hsql, final Object[] values) throws DaoException;

    /**
     * 删除实体对象集合
     * 
     * @param entity
     *            实体对象 throws DaoException 数据访问异常
     */
    public void removeAll(final Collection<T> entities) throws DaoException;

    /**
     * 更新实体对象
     * 
     * @param hsql
     * @param values
     * @throws DaoException
     */
    public void update(final String hsql, final Object[] values) throws DaoException;

    /**
     * 保存或更新实体对象，entity的id为空就是增加，entity的id不为null就是修改
     * 
     * @param entity
     *            保存或更新实体对象 throws DaoException 数据访问异常
     */
    public void saveOrUpdate(T entity) throws DaoException;

    /**
     * 保存或更新一组实体对象，entity的id为空就是增加，entity的id不为null就是修改
     * 
     * @param entitie
     *            待保存的实体集合 throws DaoException 数据访问异常
     */
    public void saveOrUpdateAll(final List<T> entitie) throws DaoException;

    /**
     * 查找实体类对象BY ID
     * 
     * @param entityClass
     *            实体Class
     * @param id
     *            实体ID
     * @return 实体对象 throws DaoException 数据访问异常
     */
    public T findById(Class<T> entity, PK id) throws DaoException;

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
     *         throws DaoException 数据访问异常
     */
    public List<T> findByValues(final String hql, final Object[] values, final boolean cache) throws DaoException;

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
     *         throws DaoException 数据访问异常
     */
    public List<T> findByValues(final String hql, final Object[] values, final int startIndex, final int maxResults, final boolean cache)
	    throws DaoException;

    /**
     * 获取所有实体集合
     * 
     * @param entityClass
     *            实体
     * @return 集合 throws DaoException 数据访问异常
     */
    public List<T> findAll(Class<T> entity) throws DaoException;

    /**
     * 按名称去查询
     * 
     * @param entityClass
     *            实体
     * @return 集合 Example <query name="byname"> <![from User where u.name = ?
     *         and u.age = ?]]> </query> findByNamedQuery("byNameAndAge",new
     *         Object[]{"tiger",18}); throws DaoException 数据访问异常
     */
    public List<T> findByNamedQuery(final String queryName, final Object[] values) throws DaoException;

    /**
     * 按名称偏移量去查询
     * 
     * @param entityClass
     *            实体
     * @return 集合 Example <query name="byname"> <![from User where u.name = ?]]>
     *         </query> findByNamedQuery("byname","tiger"); throws DaoException
     *         数据访问异常
     */
    public List<T> findByNamedQuery(final String queryName, final Object[] values, final int startIndex, final int maxResults)
	    throws DaoException;

    /**
     * 模糊查询指定条件对象集合 <br>
     * 用法：可以实例化一个空的T对象，需要查询某个字段，就set该字段的条件然后调用本方法<br>
     * 缺点：目前测试貌似只能支持String的模糊查询，虽然有办法重写，但没必要，其他用HQL<br>
     * 
     * @param entity
     *            条件实体
     * @return 结合 throws DaoException 数据访问异常
     */
    public List<T> findByExample(T entity) throws DaoException;

    /**
     * 读取实体的总数量
     * 
     * @author guojun
     * 
     * @param hsql
     *            hsql语句
     * @version 创建时间：2012-8-21 下午7:17:20 throws DaoException 数据访问异常
     */
    public int findNumByHQL(final String sql, final Object[] values) throws DaoException;

    /**
     * 读取实体的总数量by hsql
     * 
     * @author guojun
     * 
     * @param hsql
     *            hsql语句
     * @param values
     *            Object对象集合
     * @version 创建时间：2012-8-21 下午7:17:20 throws DaoException 数据访问异常
     */
    public int findNumBySQL(final String sql, final Object[] values) throws DaoException;

    /**
     * 读取实体的列表by sql
     * 
     * @author guojun
     * 
     * @param hsql
     *            hsql语句
     * @param values
     *            String对象集合
     * @version 创建时间：2012-8-21 下午7:17:20 throws DaoException 数据访问异常
     */
    public List<T> findStrListBySQL(String sql, final Object[] values) throws DaoException;

    /**
     * 持久后的实体merge
     * 
     * @param entity
     *            实体类
     * @return 持久后的实体类 throws DaoException 数据访问异常
     */
    public T merge(T entity) throws DaoException;

    /**
     * 清除实体的锁定状态<br>
     * 方法未测
     * 
     * @param entity
     *            实体 throws DaoException 数据访问异常
     */
    public void attachClean(T entity) throws DaoException;

    /**
     * 按HQL分页查询.
     * 
     * @param page
     *            页面对象
     * @param hql
     *            HQL语句
     * @param values
     *            可变参数列表
     * @return 分页数据 throws DaoException 数据访问异常
     */
    public Page<T> findByPage(final Page<T> page, final String hql, final Object[] values) throws DaoException;

    /**
     * 按原生SQL查询
     * 
     * @param sql
     *            sql语句 throws DaoException 数据访问异常
     */
    @SuppressWarnings("rawtypes")
	public List findBySQL(final String sql, final Object[] values) throws DaoException;

    /**
     * Description:按原生SQL查询, 返回map list
     * 
     * @param sql
     * @param values
     * @return
     * @throws DaoException
     * 
     */
    public List<Map<String, Object>> findMapListBySQL(final String sql, final Object[] values) throws DaoException;

    /**
     * 按原生SQL查询
     * 
     * @param sql
     *            sql语句 throws DaoException 数据访问异常
     */
    public void excuteSQL(final String Sql, final Object[] values) throws DaoException;

    /**
     * 按SQL分页查询.
     * 
     * @param sql
     *            sql语句
     * @param startIndex
     *            开始index
     * @param maxSize
     *            最大分页数量
     * @return 分页数据
     */
    public List<Object> pageSqlList(final String sql, final Page<Object> page, final Object[] values) throws DaoException;

    /**
     * 按SQL分页查询.
     * 
     * @param sql
     *            sql语句
     * @param startIndex
     *            开始index
     * @param maxSize
     *            最大分页数量
     * @return 分页数据
     */
    @SuppressWarnings("rawtypes")
	public List queryPageSqlList(final String sql, final Page<Object> page, final Object[] values) throws DaoException;

    /**
     * 执行存储过程
     * 
     * @param sql
     *            sql语句 throws DaoException 数据访问异常
     */
    public void executeProcedure(final String sql, final Object[] values) throws DaoException;

    /**
     * 执行存储过程并返回
     * 
     * @param sql
     *            sql语句 throws DaoException 数据访问异常
     */
    public String queryProcedure(final String sql, final Object[] values) throws DaoException;

    /**
     * 执行存储过程并返回List
     * 
     * @param sql
     * @param values
     * @return
     * @throws DaoException
     */
    @SuppressWarnings("rawtypes")
	public List queryProcedureList(final String sql, final Object[] values) throws DaoException;

    /**
     * 
     * 
     * Description: 按原生SQL查询, 返回VO对象 list
     * 
     * @author chen haohao
     * @param sql
     * @param object
     *            ：VO对象class
     * @param values
     * @return
     * @throws DaoException
     */
    @SuppressWarnings("rawtypes")
	public List findVOListBySQL(String sql, Class object, Object[] values) throws DaoException;

    /**
     * 
     * 
     * Description: 按原生SQL查询, 返回VO分页对象
     * 
     * @author chen haohao
     * @param sql
     * @param page
     * @param obj
     *            ：VO对象class
     * @param values
     * @return
     * @throws DaoException
     */
    @SuppressWarnings("rawtypes")
	public List queryPageSqlVOList(String sql, Page<Object> page, Class obj, Object[] values) throws DaoException;

    /**
     * 分页查询出Maplist
     * 
     * @param sql
     * @param page
     * @param values
     * @return
     * @throws DaoException
     */
    public List<Map<String, Object>> queryPageMapListBySQL(final String sql, final Page<Object> page, final Object[] values)
	    throws DaoException;

}