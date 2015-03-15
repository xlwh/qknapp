package com.qing.common.base.dao.impl;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.hibernate.HibernateException;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.qing.common.base.dao.HibernateDao;
import com.qing.common.exception.DaoException;
import com.qing.common.exception.handle.ExceptionHandle;
import com.qing.common.util.ErrorCode;
import com.qing.common.util.Page;

/**
 * Title: HibernateDaoImpl <br>
 * Description: DAO操作基类,本DAO层实现了通用的数据操作 ) <br>
 * CreateTime: 2012-08-07 16:39 <br>
 * Copyright: Copyright (c) 2012 taiheiot.com<br>
 * 
 * @author guojun
 * 
 * @param <T>
 *            POJO实体对象
 * @param <ID>
 *            ID
 * @version 创建时间：2012-8-21 下午7:17:20
 */

@SuppressWarnings("unchecked")
@Service
public class HibernateDaoImpl<T, PK extends Serializable> extends HibernateDaoSupport implements HibernateDao<T, PK> {

	// -------------------------------------------------------------------------
	// 以下为调用hql方法
	// -------------------------------------------------------------------------
	/**
	 * 保存实体对象
	 * 
	 * @param entity
	 *            待保存的实体 throws DaoException 数据访问异常
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public void save(T entity) throws DaoException {
		try {
			getHibernateTemplate().save(entity);
		} catch (Exception e) {
			ExceptionHandle.throwDaoException(e, ErrorCode.DAO_SAVE_ERROR_001, "HibernateDaoImpl.save()");
		}
	}

	/**
	 * 删除实体对象
	 * 
	 * @param entity
	 *            实体对象 throws DaoException 数据访问异常
	 */
	@Override
	public void remove(T entity) throws DaoException {
		try {
			getHibernateTemplate().delete(entity);
		} catch (Exception e) {
			ExceptionHandle.throwDaoException(e, ErrorCode.DAO_DELETE_ERROR_002, "HibernateDaoImpl.remove()");
		}
	}

	/**
	 * 删除实体对象BY values
	 * 
	 * @param hsql
	 *            HSQL语句
	 * @param values
	 *            values 参数 throws DaoException 数据访问异常
	 */
	@SuppressWarnings("rawtypes")
	@Override
	public void remove(final String hsql, final Object[] values) throws DaoException {
		try {
			getHibernateTemplate().executeFind(new HibernateCallback() {
				@Override
				public Object doInHibernate(Session session) throws HibernateException {
					Query query = session.createQuery(hsql);
					for (int i = 0; null != values && i < values.length; i++) {
						Object value = values[i];
						setObjectFromType(value, query, i);
					}
					query.executeUpdate();
					return null;
				}
			});
		} catch (Exception e) {
			ExceptionHandle.throwDaoException(e, ErrorCode.DAO_DELETE_ERROR_002, "HibernateDaoImpl.remove()");
		}
	}

	/**
	 * 删除实体对象集合
	 * 
	 * @param entity
	 *            实体对象 throws DaoException 数据访问异常
	 */
	@Override
	public void removeAll(final Collection<T> entities) throws DaoException {
		try {
			getHibernateTemplate().deleteAll(entities);
		} catch (Exception e) {
			ExceptionHandle.throwDaoException(e, ErrorCode.DAO_DELETE_ERROR_002, "HibernateDaoImpl.removeAll()");
		}
	}

	/**
	 * 保存或更新实体对象，entity的id为空就是增加，entity的id不为null就是修改
	 * 
	 * @param entity
	 *            保存或更新实体对象 throws DaoException 数据访问异常
	 */
	@Override
	public void saveOrUpdate(T entity) throws DaoException {
		try {
			getHibernateTemplate().saveOrUpdate(entity);
		} catch (Exception e) {
			ExceptionHandle.throwDaoException(e, ErrorCode.DAO_SAVE_ERROR_001, "HibernateDaoImpl.saveOrUpdate()");
		}
	}

	/**
	 * 更新实体对象
	 * 
	 * @param hsql
	 * @param values
	 * @throws DaoException
	 */
	@SuppressWarnings("rawtypes")
	@Override
	public void update(final String hsql, final Object[] values) throws DaoException {
		try {
			getHibernateTemplate().executeFind(new HibernateCallback() {
				@Override
				public Object doInHibernate(Session session) throws HibernateException {
					Query query = session.createQuery(hsql);
					for (int i = 0; null != values && i < values.length; i++) {
						Object value = values[i];
						setObjectFromType(value, query, i);
					}
					query.executeUpdate();
					return null;
				}
			});
		} catch (Exception e) {
			ExceptionHandle.throwDaoException(e, ErrorCode.DAO_DELETE_ERROR_002, "HibernateDaoImpl.update()");
		}
	}

	/**
	 * 保存或更新一组实体对象，entity的id为空就是增加，entity的id不为null就是修改
	 * 
	 * @param entitie
	 *            待保存的实体集合 throws DaoException 数据访问异常
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public void saveOrUpdateAll(final List<T> entitie) throws DaoException {
		try {
			getHibernateTemplate().saveOrUpdateAll(entitie);
			getHibernateTemplate().flush();
		} catch (Exception e) {
			ExceptionHandle.throwDaoException(e, ErrorCode.DAO_SAVE_ERROR_001, "HibernateDaoImpl.saveOrUpdateAll()");
		}
	}

	/**
	 * 查找实体类对象BY ID
	 * 
	 * @param entityClass
	 *            实体Class
	 * @param id
	 *            实体ID
	 * @return 实体对象 throws DaoException 数据访问异常
	 */
	@Override
	public T findById(Class<T> entity, PK id) throws DaoException {
		try {
			return getHibernateTemplate().get(entity, id);
		} catch (Exception e) {
			ExceptionHandle.throwDaoException(e, ErrorCode.DAO_QUERY_ERROR_004, "HibernateDaoImpl.findById()");
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
	 *         throws DaoException 数据访问异常
	 */
	@SuppressWarnings("rawtypes")
	@Override
	public List<T> findByValues(final String hql, final Object[] values, final boolean cache) throws DaoException {
		try {
			return getHibernateTemplate().executeFind(new HibernateCallback() {
				@Override
				public Object doInHibernate(Session session) throws HibernateException {
					Query query = session.createQuery(hql);
					List<T> list = null;
					for (int i = 0; null != values && i < values.length; i++) {
						Object value = values[i];
						setObjectFromType(value, query, i);
					}
					if (cache) {
						list = itrToList(query.iterate());
						session.close();
						return list;
					} else {
						list = query.list();
						session.close();
						return list;
					}
				}
			});
		} catch (Exception e) {
			ExceptionHandle.throwDaoException(e, ErrorCode.DAO_QUERY_ERROR_004, "HibernateDaoImpl.findByValues()");
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
	 *         throws DaoException 数据访问异常
	 */
	@SuppressWarnings("rawtypes")
	@Override
	public List<T> findByValues(final String hql, final Object[] values, final int startIndex, final int maxResults,
			final boolean cache) throws DaoException {
		try {
			return getHibernateTemplate().executeFind(new HibernateCallback() {
				@Override
				public Object doInHibernate(Session session) throws HibernateException {
					List<T> list;
					Query query = session.createQuery(hql);
					for (int i = 0; null != values && i < values.length; i++) {
						Object value = values[i];
						setObjectFromType(value, query, i);
					}
					query.setFirstResult(startIndex);
					query.setMaxResults(maxResults);
					if (cache) {
						list = itrToList(query.iterate());
						session.close();
						return list;
					} else {
						list = query.list();
						session.close();
						return list;
					}
				}
			});
		} catch (Exception e) {
			ExceptionHandle.throwDaoException(e, ErrorCode.DAO_QUERY_ERROR_004, "HibernateDaoImpl.findByValues()");
			return null;
		}
	}

	/**
	 * 获取所有实体集合
	 * 
	 * @param entityClass
	 *            实体
	 * @return 集合 throws DaoException 数据访问异常
	 */
	@Override
	public List<T> findAll(Class<T> entity) throws DaoException {
		List<T> list = null;
		try {
			list = getHibernateTemplate().find("from " + entity.getName());
		} catch (Exception e) {
			ExceptionHandle.throwDaoException(e, ErrorCode.DAO_QUERY_ERROR_004, "HibernateDaoImpl.findAll()");
		}
		return list;
	}

	/**
	 * 按名称去查询
	 * 
	 * @param entityClass
	 *            实体
	 * @return 集合 Example <query name="byname"> <![from User where u.name = ?
	 *         and u.age = ?]]> </query> findByNamedQuery("byNameAndAge",new
	 *         Object[]{"tiger",18}); throws DaoException 数据访问异常
	 */
	@Override
	public List<T> findByNamedQuery(final String queryName, final Object[] values) throws DaoException {
		List<T> list = null;
		try {
			list = getHibernateTemplate().findByNamedQuery(queryName, values);
		} catch (Exception e) {
			ExceptionHandle.throwDaoException(e, ErrorCode.DAO_QUERY_ERROR_004, "HibernateDaoImpl.findByNamedQuery()");
		}
		return list;
	}

	/**
	 * 按名称偏移量去查询
	 * 
	 * @param entityClass
	 *            实体
	 * @return 集合 Example <query name="byname"> <![from User where u.name = ?]]>
	 *         </query> findByNamedQuery("byname","tiger"); throws DaoException
	 *         数据访问异常
	 */
	@SuppressWarnings("rawtypes")
	@Override
	public List<T> findByNamedQuery(final String queryName, final Object[] values, final int startIndex,
			final int maxResults) throws DaoException {
		List<T> list = null;
		try {
			list = getHibernateTemplate().executeFind(new HibernateCallback() {
				@Override
				public Object doInHibernate(Session session) throws HibernateException {
					Query query = session.getNamedQuery(queryName);
					addValueObject(query, values);
					query.setFirstResult(startIndex);
					query.setMaxResults(maxResults);
					return query.list();
				}
			});
		} catch (Exception e) {
			ExceptionHandle.throwDaoException(e, ErrorCode.DAO_QUERY_ERROR_004, "HibernateDaoImpl.findByNamedQuery()");
		}
		return list;
	}

	/**
	 * 模糊查询指定条件对象集合 <br>
	 * 用法：可以实例化一个空的T对象，需要查询某个字段，就set该字段的条件然后调用本方法<br>
	 * 缺点：目前测试貌似只能支持String的模糊查询，虽然有办法重写，但没必要，其他用HQL<br>
	 * 
	 * @param entity
	 *            条件实体
	 * @return 结合 throws DaoException 数据访问异常
	 */
	@Override
	public List<T> findByExample(T entity) throws DaoException {
		try {
			List<T> results = getHibernateTemplate().findByExample(entity);
			return results;
		} catch (Exception e) {
			ExceptionHandle.throwDaoException(e, ErrorCode.DAO_QUERY_ERROR_004, "HibernateDaoImpl.findByExample()");
			return null;
		}
	}

	/**
	 * 读取实体的总数量
	 * 
	 * @author guojun
	 * 
	 * @param clazz
	 *            clazz实体对象
	 * @version 创建时间：2012-8-21 下午7:17:20 throws DaoException 数据访问异常
	 */
	@SuppressWarnings("rawtypes")
	@Override
	public int findNumByHQL(final String hsql, final Object[] values) throws DaoException {
		try {
			Integer integer = (Integer) getHibernateTemplate().execute(new HibernateCallback() {
				@Override
				public Object doInHibernate(Session session) throws HibernateException {
					Query query = session.createQuery(hsql);
					addValueObject(query, values);
					Iterator it = query.list().iterator();
					if (it.hasNext()) {
						Long cnt = (Long) it.next();
						cnt = cnt == null ? 0 : cnt;
						session.close();
						return Integer.valueOf(cnt.toString());
					} else {
						return new Integer(0);
					}
				}

			});
			return integer.intValue();
		} catch (Exception e) {
			ExceptionHandle.throwDaoException(e, ErrorCode.DAO_QUERY_ERROR_004, "HibernateDaoImpl.findNumByHQL()");
			return new Integer(0);
		}
	}

	/**
	 * 持久后的实体merge
	 * 
	 * @param entity
	 *            实体类
	 * @return 持久后的实体类 throws DaoException 数据访问异常
	 */
	@Override
	public T merge(T entity) throws DaoException {
		try {
			T result = getHibernateTemplate().merge(entity);
			return result;
		} catch (Exception e) {
			ExceptionHandle.throwDaoException(e, ErrorCode.DAO_SAVE_ERROR_001, "HibernateDaoImpl.merge()");
			return null;
		}
	}

	/**
	 * 清除实体的锁定状态<br>
	 * 方法未测
	 * 
	 * @param entity
	 *            实体 throws DaoException 数据访问异常
	 */
	@Override
	public void attachClean(T entity) throws DaoException {
		try {
			getHibernateTemplate().lock(entity, LockMode.NONE);
		} catch (Exception e) {
			ExceptionHandle.throwDaoException(e, ErrorCode.DAO_SAVE_ERROR_001, "HibernateDaoImpl.attachClean()");
		}
	}

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
	@SuppressWarnings("rawtypes")
	@Override
	public Page<T> findByPage(final Page<T> page, final String hql, final Object[] values) throws DaoException {
		try {
			return (Page<T>) getHibernateTemplate().execute(new HibernateCallback() {
				@Override
				public Object doInHibernate(Session session) throws HibernateException, SQLException {
					Query query = session.createQuery(hql);
					addValueObject(query, values);
					if (page.isFirstSetted()) {
						query.setFirstResult(page.getFirst());
					}
					if (page.isPageSizeSetted()) {
						query.setMaxResults(page.getPageSize());
						// query.setFetchSize(page.getPageSize());
					}
					page.setResult(query.list());
					return page;
				}
			});
		} catch (Exception e) {
			ExceptionHandle.throwDaoException(e, ErrorCode.DAO_QUERY_ERROR_004, "HibernateDaoImpl.findByPage()");
			return null;
		}
	}

	// --------------------------------------------------------------------------------
	// 以下为调用SQL方法
	// --------------------------------------------------------------------------------
	/**
	 * 按原生SQL查询
	 * 
	 * @param sql
	 *            sql语句 throws DaoException 数据访问异常
	 */
	@SuppressWarnings("rawtypes")
	@Override
	public List findBySQL(final String sql, final Object[] values) throws DaoException {
		try {
			List list = this.getHibernateTemplate().executeFind(new HibernateCallback() {
				@Override
				public Object doInHibernate(Session session) throws HibernateException, SQLException {
					SQLQuery query = session.createSQLQuery(sql);
					addValueObject(query, values);
					return query.list();
				}
			});
			return list;
		} catch (Exception e) {
			ExceptionHandle.throwDaoException(e, ErrorCode.DAO_QUERY_ERROR_004, "HibernateDaoImpl.findBySQL()");
			return null;
		}

	}

	/**
	 * Description:按原生SQL查询, 返回map list
	 * 
	 * @param sql
	 * @param values
	 * @return
	 * @throws DaoException
	 * @see com.qing.common.base.dao.HibernateDao#findMapListBySQL(java.lang.String,
	 *      java.lang.Object[])
	 **/
	@SuppressWarnings("rawtypes")
	@Override
	public List<Map<String, Object>> findMapListBySQL(final String sql, final Object[] values) throws DaoException {
		try {
			List<Map<String, Object>> list = this.getHibernateTemplate().executeFind(new HibernateCallback() {
				@Override
				public Object doInHibernate(Session session) throws HibernateException, SQLException {
					SQLQuery query = (SQLQuery) session.createSQLQuery(sql).setResultTransformer(
							Transformers.ALIAS_TO_ENTITY_MAP);
					addValueObject(query, values);
					return query.list();
				}
			});
			return list;
		} catch (Exception e) {
			ExceptionHandle.throwDaoException(e, ErrorCode.DAO_QUERY_ERROR_004, "HibernateDaoImpl.findBySQL()");
			return null;
		}

	}

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
	@Override
	public List findVOListBySQL(final String sql, final Class obj, final Object[] values) throws DaoException {
		try {
			List<Map<String, Object>> list = this.getHibernateTemplate().executeFind(new HibernateCallback() {
				@Override
				public Object doInHibernate(Session session) throws HibernateException, SQLException {
					SQLQuery query = (SQLQuery) session.createSQLQuery(sql).setResultTransformer(
							Transformers.aliasToBean(obj));
					addValueObject(query, values);
					return query.list();
				}
			});
			return list;
		} catch (Exception e) {
			ExceptionHandle.throwDaoException(e, ErrorCode.DAO_QUERY_ERROR_004, "HibernateDaoImpl.findVOListBySQL()");
			return null;
		}

	}

	/**
	 * 按SQL分页查询.返回VO分页对象
	 * 
	 * @author chen haohao
	 * @param sql
	 *            sql语句
	 * @param page
	 *            page对象
	 * @param values
	 *            object对象列表
	 * @return 分页数据
	 */
	@SuppressWarnings("rawtypes")
	@Override
	public List queryPageSqlVOList(final String sql, final Page<Object> page, final Class obj, final Object[] values)
			throws DaoException {
		try {
			return getHibernateTemplate().executeFind(new HibernateCallback() {
				@Override
				public Object doInHibernate(Session session) throws HibernateException {
					Iterator it = null;
					try {
						SQLQuery query = (SQLQuery) session.createSQLQuery(sql).setResultTransformer(
								Transformers.aliasToBean(obj));

						addValueObject(query, values);
						convertType(query, obj);
						if (page.isFirstSetted()) {
							query.setFirstResult(page.getFirst());
						} else {
							query.setFirstResult((page.getPageNo() - 1) * page.getPageSize());
						}
						if (page.isPageSizeSetted()) {
							query.setMaxResults(page.getPageSize());
						}
						it = query.list().iterator();
					} catch (Exception ex) {
						logger.error(ex);
						ex.printStackTrace();
					} finally {
						session.close();
					}
					if (it == null) {
						return null;
					} else {
						List list = itrToList(it);
						page.setResult(list);
						return list;
					}
				}

			});
		} catch (Exception e) {
			ExceptionHandle
					.throwDaoException(e, ErrorCode.DAO_QUERY_ERROR_004, "HibernateDaoImpl.queryPageSqlVOList()");
			return null;
		}
	}

	/**
	 * 读取实体的总数量by sql
	 * 
	 * @author guojun
	 * 
	 * @param clazz
	 *            clazz实体对象
	 * @version 创建时间：2012-8-21 下午7:17:20 throws DaoException 数据访问异常
	 */
	@SuppressWarnings("rawtypes")
	@Override
	public int findNumBySQL(final String sql, final Object[] values) throws DaoException {

		try {
			Integer integer = (Integer) getHibernateTemplate().execute(new HibernateCallback() {
				@Override
				public Object doInHibernate(Session session) throws HibernateException, SQLException {
					SQLQuery query = session.createSQLQuery(sql);
					addValueObject(query, values);
					query.addScalar("cnt", org.hibernate.type.StandardBasicTypes.INTEGER);
					List topList = query.list();
					Integer count = 0;
					if (null != topList && topList.get(0) != null) {
						count = (Integer) topList.get(0);
						session.close();
					}
					return count;
				}
			});
			return integer.intValue();
		} catch (Exception e) {
			ExceptionHandle.throwDaoException(e, ErrorCode.DAO_QUERY_ERROR_004, "HibernateDaoImpl.totalNum()");
			return 0;
		}
	}

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
	@Override
	@SuppressWarnings("rawtypes")
	public List<T> findStrListBySQL(final String sql, final Object[] values) throws DaoException {
		try {
			List list = (List) getHibernateTemplate().execute(new HibernateCallback() {
				@Override
				public Object doInHibernate(Session session) throws HibernateException, SQLException {
					SQLQuery query = session.createSQLQuery(sql);
					addValueObject(query, values);
					query.addScalar("str", org.hibernate.type.StandardBasicTypes.STRING);
					List topList = query.list();
					session.close();
					return topList;
				}
			});
			return list;
		} catch (Exception e) {
			ExceptionHandle.throwDaoException(e, ErrorCode.DAO_QUERY_ERROR_004, "HibernateDaoImpl.totalNum()");
			return null;
		}
	}

	/**
	 * 按原生SQL查询
	 * 
	 * @param sql
	 *            sql语句 throws DaoException 数据访问异常
	 */
	@SuppressWarnings("rawtypes")
	@Override
	public void excuteSQL(final String sql, final Object[] values) throws DaoException {
		try {
			getHibernateTemplate().execute(new HibernateCallback() {
				@Override
				public Object doInHibernate(Session session) throws HibernateException, SQLException {
					SQLQuery query = session.createSQLQuery(sql);
					addValueObject(query, values);
					query.executeUpdate();
					return null;
				}
			});
		} catch (Exception e) {
			ExceptionHandle.throwDaoException(e, ErrorCode.DAO_SAVE_ERROR_001, "HibernateDaoImpl.excuteSQL()");
		}
	}

	/**
	 * 执行存储过程
	 * 
	 * @param sql
	 *            sql语句 throws DaoException 数据访问异常
	 */
	@SuppressWarnings("rawtypes")
	@Override
	public void executeProcedure(final String sql, final Object[] values) throws DaoException {
		try {
			getHibernateTemplate().execute(new HibernateCallback() {
				@Override
				public Object doInHibernate(Session session) throws HibernateException, SQLException {
					SQLQuery query = session.createSQLQuery(sql);
					addValueObject(query, values);
					query.executeUpdate();
					return null;
				}
			});
		} catch (Exception e) {
			ExceptionHandle.throwDaoException(e, ErrorCode.DAO_SAVE_ERROR_001, "HibernateDaoImpl.executeProcedure()");
		}
	}

	/**
	 * 执行存储过程并返回
	 * 
	 * @param sql
	 *            sql语句 throws DaoException 数据访问异常
	 */
	@Override
	public String queryProcedure(final String sql, final Object[] values) throws DaoException {
		String procedureResult = null;
		try {
			procedureResult = getHibernateTemplate().execute(new HibernateCallback<String>() {
				@SuppressWarnings("rawtypes")
				@Override
				public String doInHibernate(Session session) throws HibernateException, SQLException {
					SQLQuery query = session.createSQLQuery(sql);
					addValueObject(query, values);
					List list = query.list();
					String result = null;
					if (null != list && list.get(0) != null) {
						result = list.get(0).toString();
					}
					return result;
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
			// ExceptionHandle.throwDaoException(e,
			// ErrorCode.DAO_SAVE_ERROR_001,
			// "HibernateDaoImpl.queryProcedure()");
		}
		return procedureResult;
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
	 */
	@SuppressWarnings("rawtypes")
	@Override
	public List queryPageSqlList(final String sql, final Page<Object> page, final Object[] values) throws DaoException {
		try {
			return getHibernateTemplate().executeFind(new HibernateCallback() {
				@Override
				public Object doInHibernate(Session session) throws HibernateException {
					Iterator it = null;
					try {
						Query query = session.createSQLQuery(sql);
						addValueObject(query, values);
						if (page.isFirstSetted()) {
							query.setFirstResult(page.getFirst());
						} else {
							query.setFirstResult((page.getPageNo() - 1) * page.getPageSize());
						}
						if (page.isPageSizeSetted()) {
							query.setMaxResults(page.getPageSize());
						}
						it = query.list().iterator();
					} catch (Exception ex) {
						logger.error(ex);
					} finally {
						session.close();
					}
					if (it == null) {
						return null;
					} else {
						List list = itrToList(it);
						page.setResult(list);
						return list;
					}
				}
			});
		} catch (Exception e) {
			ExceptionHandle.throwDaoException(e, ErrorCode.DAO_QUERY_ERROR_004, "HibernateDaoImpl.findByPage()");
			return null;
		}
	}

	@SuppressWarnings("rawtypes")
	@Override
	public List<Map<String, Object>> queryPageMapListBySQL(final String sql, final Page<Object> page,
			final Object[] values) throws DaoException {
		try {
			List<Map<String, Object>> list = this.getHibernateTemplate().executeFind(new HibernateCallback() {
				@Override
				public Object doInHibernate(Session session) throws HibernateException, SQLException {
					Iterator it = null;
					try {
						SQLQuery query = (SQLQuery) session.createSQLQuery(sql).setResultTransformer(
								Transformers.ALIAS_TO_ENTITY_MAP);
						addValueObject(query, values);
						addValueObject(query, values);
						if (page.isFirstSetted()) {
							query.setFirstResult(page.getFirst());
						}
						if (page.isPageSizeSetted()) {
							query.setMaxResults(page.getPageSize());
						}
						it = query.list().iterator();
					} catch (Exception ex) {
						logger.error(ex);
					} finally {
						session.close();
					}
					if (it == null) {
						return null;
					} else {
						List list = itrToList(it);
						page.setResult(list);
						return list;
					}
				}
			});
			return list;
		} catch (Exception e) {
			ExceptionHandle.throwDaoException(e, ErrorCode.DAO_QUERY_ERROR_004, "HibernateDaoImpl.findBySQL()");
			return null;
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
	 */
	@SuppressWarnings("rawtypes")
	@Override
	public List<Object> pageSqlList(final String sql, final Page<Object> page, final Object[] values)
			throws DaoException {
		try {
			return getHibernateTemplate().executeFind(new HibernateCallback() {
				@Override
				public Object doInHibernate(Session session) throws HibernateException {
					Iterator it = null;
					try {
						Query query = session.createSQLQuery(sql);
						addValueObject(query, values);
						if (page.isFirstSetted()) {
							query.setFirstResult(page.getFirst());
						}
						if (page.isPageSizeSetted()) {
							query.setMaxResults(page.getPageSize());
						}
						it = query.list().iterator();
					} catch (Exception ex) {
						logger.error(ex);
					} finally {
						session.close();
					}
					if (it == null) {
						return null;
					} else {
						List list = itrToList(it);
						page.setResult(list);
						return list;
					}
				}
			});
		} catch (Exception e) {
			ExceptionHandle.throwDaoException(e, ErrorCode.DAO_QUERY_ERROR_004, "HibernateDaoImpl.findByPage()");
			return null;
		}
	}

	// -------------------------------------------------------------------------
	// 以下内部方法
	// -------------------------------------------------------------------------

	/**
	 * 
	 * 
	 * Description:hibernate类型转换
	 * 
	 * @param query
	 * @param obj
	 */
	@SuppressWarnings("rawtypes")
	private void convertType(SQLQuery query, Class obj) {
		Field[] fields = obj.getDeclaredFields();
		for (Field field : fields) {
			if (field.getType().equals(java.lang.Integer.TYPE)) {
				query.addScalar(field.getName(), org.hibernate.type.StandardBasicTypes.INTEGER);
			} else if (field.getType().equals(java.lang.Long.TYPE)) {
				query.addScalar(field.getName(), org.hibernate.type.StandardBasicTypes.LONG);
			} else if (field.getType().equals(java.lang.Double.TYPE)) {
				query.addScalar(field.getName(), org.hibernate.type.StandardBasicTypes.DOUBLE);
			} else if (field.getType().equals(java.lang.Float.TYPE)) {
				query.addScalar(field.getName(), org.hibernate.type.StandardBasicTypes.FLOAT);
			} else if (field.getType().equals(java.lang.Short.TYPE)) {
				query.addScalar(field.getName(), org.hibernate.type.StandardBasicTypes.SHORT);
			} else if (field.getType().getSimpleName().equals("Timestamp")) {
				query.addScalar(field.getName(), org.hibernate.type.StandardBasicTypes.TIMESTAMP);
			} else if (field.getType().getSimpleName().equals("Date")) {
				query.addScalar(field.getName(), org.hibernate.type.StandardBasicTypes.TIMESTAMP);
			} else {
				query.addScalar(field.getName(), org.hibernate.type.StandardBasicTypes.STRING);
			}
		}
	}

	/**
	 * 在查询语句中绑定参数，如果参数数组为空，不做任何绑定
	 * 
	 * @param query
	 *            SQLQuery语句
	 * @param values
	 *            参数数组
	 */
	private void addValueObject(Query query, final Object[] values) {
		for (int i = 0; null != values && i < values.length; i++) {
			query.setParameter(i, values[i]);
		}
	}

	/**
	 * 在查询语句中绑定参数，如果参数数组为空，不做任何绑定
	 * 
	 * @param query
	 *            SQLQuery语句
	 * @param values
	 *            参数数组
	 */
	private void setObjectFromType(Object value, Query query, int i) {
		if (value instanceof Date) {
			query.setDate(i, (Date) value);
		} else if (value instanceof BigDecimal) {
			query.setBigDecimal(i, (BigDecimal) value);
		} else if (value instanceof BigInteger) {
			query.setBigInteger(i, (BigInteger) value);
		} else {
			query.setParameter(i, value);
		}
	}

	/**
	 * 将iterator转化为List
	 * 
	 * @param it
	 * @return list<T>
	 */
	public List<T> itrToList(Iterator<T> it) {
		List<T> list = new ArrayList<T>();
		while (it.hasNext()) {
			T o = it.next();
			list.add(o);
		}
		return list;
	}

	/**
	 * @return Session
	 */
	public Session getCurrentSession() {
		return getHibernateTemplate().getSessionFactory().getCurrentSession();
	}

	/**
	 * 执行存储过程并返回List
	 * 
	 * @param sql
	 *            sql语句 throws DaoException 数据访问异常
	 */
	@SuppressWarnings("rawtypes")
	@Override
	public List queryProcedureList(final String sql, final Object[] values) throws DaoException {

		List list = null;
		try {
			list = getHibernateTemplate().execute(new HibernateCallback<List>() {
				@Override
				public List doInHibernate(Session session) throws HibernateException, SQLException {
					SQLQuery query = (SQLQuery) session.createSQLQuery(sql).setResultTransformer(
							Transformers.ALIAS_TO_ENTITY_MAP);
					addValueObject(query, values);
					List list = query.list();
					if (null != list && list.size() > 0) {
						return list;
					} else {
						return null;
					}
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
			ExceptionHandle
					.throwDaoException(e, ErrorCode.DAO_QUERY_ERROR_004, "HibernateDaoImpl.queryProcedureList()");
		}
		return list;
	}

}