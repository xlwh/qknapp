package com.qing.common.base.service.impl;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.qing.common.base.dao.HibernateDao;
import com.qing.common.base.service.BaseService;
import com.qing.common.exception.ServiceException;
import com.qing.common.exception.handle.ExceptionHandle;
import com.qing.common.util.ErrorCode;
import com.qing.common.util.Page;

/**
 * Title: BaseServiceImpl <br>
 * Description: 用于DB操作的service接口的实现类， 注入了HibernateDao和IbatisDao，封装了两种ORM的数据库操作方法 <br>
 * CreateTime: 2012-08-07 16:39 <br>
 * Copyright: Copyright (c) 2012 taiheiot.com<br>
 * 
 * @author guojun
 * @since 1.0
 */
@Service
public class BaseServiceImpl<T, PK extends Serializable> implements BaseService<T, PK> {

    /**
     * 注入HibernateDaoImpl
     */
    @Resource
    private HibernateDao<T, PK> hibernateDao;

   

    /*-------------------------------Hibernate方法-------------------------------------*/
    /**
     * 保存实体对象
     * 
     * @param entity
     *            待保存的实体
     * @throws ServiceException
     *             服务层异常
     */
    @Override
    public void save(T entity) throws ServiceException {
	try {
	    getHibernateDao().save(entity);
	} catch (Exception e) {
	    ExceptionHandle.throwServiceException(e, ErrorCode.SERVICE_SAVE_ERROR_001, "BaseServiceImpl.save()");
	}
    }

    /**
     * 删除实体对象
     * 
     * @param entity
     *            删除对象
     * @throws ServiceException
     *             服务层异常
     */
    @Override
    public void remove(T entity) throws ServiceException {
	try {
	    getHibernateDao().remove(entity);
	} catch (Exception e) {
	    ExceptionHandle.throwServiceException(e, ErrorCode.SERVICE_DELETE_ERROR_002, "BaseServiceImpl.remove()");
	}
    }

    /**
     * 批量删除实体对象
     * 
     * @param entity
     *            删除对象
     * @throws ServiceException
     *             服务层异常
     */
    public void removeAll(final Collection<T> collection) throws ServiceException {
	try {
	    getHibernateDao().removeAll(collection);
	} catch (Exception e) {
	    ExceptionHandle.throwServiceException(e, ErrorCode.SERVICE_DELETE_ERROR_002, "BaseServiceImpl.removeAll()");
	}
    }

    /**
     * 删除实体对象by hql
     * 
     * @param entity
     *            删除对象
     * @throws ServiceException
     *             服务层异常
     */
    public void remove(String hql, Object[] obj) throws ServiceException {
	try {
	    getHibernateDao().remove(hql, obj);
	} catch (Exception e) {
	    ExceptionHandle.throwServiceException(e, ErrorCode.SERVICE_DELETE_ERROR_002, "BaseServiceImpl.remove()");
	}
    }

    /**
     * 保存或更新实体对象，entity的id为空就是增加，entity的id不为null就是修改
     * 
     * @param entity
     *            待保存的实体
     * @return 实体的id
     * @throws ServiceException
     *             服务层异常
     */
    @Override
    public void saveOrUpdate(T entity) throws ServiceException {
	try {
	    
	    getHibernateDao().saveOrUpdate(entity);
	} catch (Exception e) {
	    ExceptionHandle.throwServiceException(e, ErrorCode.SERVICE_SAVE_ERROR_001, "BaseServiceImpl.saveOrUpdate()");
	}
    }

    /**
     * 保存或更新一组实体对象，entity的id为空就是增加，entity的id不为null就是修改
     * 
     * @param entities
     *            待保存的实体集合
     * @throws ServiceException
     *             服务层异常
     */
    @Override
    public void saveOrUpdateAll(List<T> entities) throws ServiceException {
	try {
	    getHibernateDao().saveOrUpdateAll(entities);
	} catch (Exception e) {
	    ExceptionHandle.throwServiceException(e, ErrorCode.SERVICE_UPDATE_ERROR_003, "BaseServiceImpl.saveOrUpdateAll()");
	}
    }

    /**
     * 删除一组实体对象
     * 
     * @param entities
     *            待删除对象集合
     * @throws ServiceException
     *             服务层异常
     */
    @Override
    public void remove(List<T> entities) throws ServiceException {
	try {
	    getHibernateDao().removeAll(entities);
	} catch (Exception e) {
	    ExceptionHandle.throwServiceException(e, ErrorCode.SERVICE_DELETE_ERROR_002, "BaseServiceImpl.remove()");
	}
    }

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
    @Override
    public T findById(Class<T> c, PK id) throws ServiceException {
	try {
	    return getHibernateDao().findById(c, id);
	} catch (Exception e) {
	    ExceptionHandle.throwServiceException(e, ErrorCode.SERVICE_QUERY_ERROR_004, "BaseServiceImpl.findById()");
	    return null;
	}
    }

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
    public List<T> findByValues(final String hql, final Object[] values, final boolean cache) throws ServiceException {
	try {
	    return getHibernateDao().findByValues(hql, values, cache);
	} catch (Exception e) {
	    ExceptionHandle.throwServiceException(e, ErrorCode.SERVICE_QUERY_ERROR_004, "BaseServiceImpl.findByValues()");
	    return null;
	}
    }

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
	    throws ServiceException {
	try {
	    return getHibernateDao().findByValues(hql, values, startIndex, maxResults, cache);
	} catch (Exception e) {
	    ExceptionHandle.throwServiceException(e, ErrorCode.SERVICE_QUERY_ERROR_004, "BaseServiceImpl.findByValues()");
	    return null;
	}
    }

    /**
     * 按名称去查询
     * 
     * @param entityClass
     *            实体
     * @return 集合 throws ServiceException 数据访问异常
     */
    public List<T> findByNamedQuery(final String queryName, final Object[] values) throws ServiceException {
	try {
	    return getHibernateDao().findByNamedQuery(queryName, values);
	} catch (Exception e) {
	    ExceptionHandle.throwServiceException(e, ErrorCode.SERVICE_QUERY_ERROR_004, "BaseServiceImpl.findByNamedQuery()");
	    return null;
	}
    }

    /**
     * 按名称偏移量去查询
     * 
     * @param entityClass
     *            实体
     * @return 集合 throws ServiceException 数据访问异常
     */
    @SuppressWarnings("rawtypes")
	public List findByNamedQuery(final String queryName, final Object[] values, final int startIndex, final int maxResults)
	    throws ServiceException {
	try {
	    return getHibernateDao().findByNamedQuery(queryName, values, startIndex, maxResults);
	} catch (Exception e) {
	    ExceptionHandle.throwServiceException(e, ErrorCode.SERVICE_QUERY_ERROR_004, "BaseServiceImpl.findByNamedQuery()");
	    return null;
	}
    }

    /**
     * 读取实体的总数量
     * 
     * @author guojun
     * 
     * @param hsql
     *            hsql语句
     * @version 创建时间：2012-8-21 下午7:17:20 throws ServiceException 数据访问异常
     */
    public int findNumByHQL(String hsql, final Object[] values) throws ServiceException {
	try {
	    return getHibernateDao().findNumByHQL(hsql, values);
	} catch (Exception e) {
	    ExceptionHandle.throwServiceException(e, ErrorCode.SERVICE_QUERY_ERROR_004, "BaseServiceImpl.totalHQLNum()");
	    return 0;
	}
    }

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
    public int findNumBySQL(String sql, final Object[] values) throws ServiceException {
	try {
	    return getHibernateDao().findNumBySQL(sql, values);
	} catch (Exception e) {
	    ExceptionHandle.throwServiceException(e, ErrorCode.SERVICE_QUERY_ERROR_004, "BaseServiceImpl.totalSQLNum()");
	    return 0;
	}
    }

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
    @Override
    public void executeProcedure(String proc, Object[] values) throws ServiceException {
	try {
	    getHibernateDao().executeProcedure(proc, values);
	} catch (Exception e) {
	    ExceptionHandle.throwServiceException(e, ErrorCode.SERVICE_PRO_ERROR_005, "BaseServiceImpl.executeProcedure()");
	}
    }

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
    @Override
    public Page<T> findByPage(Page<T> page, String hql, Object[] values) throws ServiceException {
	try {
	    return getHibernateDao().findByPage(page, hql, values);
	} catch (Exception e) {
	    ExceptionHandle.throwServiceException(e, ErrorCode.SERVICE_QUERY_ERROR_004, "BaseServiceImpl.findByPage()");
	    return null;
	}
    }

    /*------------------------------------sql方法-----------------------------------------*/
    /**
     * 按原生SQL查询
     * 
     * @param sql
     *            sql语句 throws ServiceException 数据访问异常
     */
    @SuppressWarnings("rawtypes")
	public List findBySQL(final String sql, final Object[] values) throws ServiceException {
	try {
	    return getHibernateDao().findBySQL(sql, values);
	} catch (Exception e) {
	    ExceptionHandle.throwServiceException(e, ErrorCode.SERVICE_QUERY_ERROR_004, "BaseServiceImpl.findBySQL()");
	    return null;
	}
    }

    /**
     * 按原生SQL查询
     * 
     * @param sql
     *            sql语句 throws DaoException 数据访问异常
     */
    @Override
    public void excuteSQL(String Sql, final Object[] values) throws ServiceException {
	try {
	    getHibernateDao().excuteSQL(Sql, values);
	} catch (Exception e) {
	    ExceptionHandle.throwServiceException(e, ErrorCode.SERVICE_UPDATE_ERROR_003, "BaseServiceImpl.excuteSQL()");
	}
    }

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
    @Override
    public List<Object> pageSqlList(final String sql, final Page<Object> page, final Object[] values) throws ServiceException {
	try {
	    return getHibernateDao().pageSqlList(sql, page, values);
	} catch (Exception e) {
	    ExceptionHandle.throwServiceException(e, ErrorCode.SERVICE_QUERY_ERROR_004, "BaseServiceImpl.pageSqlList()");
	    return null;
	}
    }

   


    public HibernateDao<T, PK> getHibernateDao() {
	return hibernateDao;
    }

    public void setHibernateDao(HibernateDao<T, PK> hibernateDao) {
	this.hibernateDao = hibernateDao;
    }

   

}
