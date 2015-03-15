/*
 * Title:        清清网系统 2014-8-20
 * Description:  [描述模块的功能、作用、使用方法和注意事项]
 * Copyright:    Copyright (c) 2014
 * Company:      清清网
 * @author       luoqinglong
 * @version      2.0  2014-8-20
 */
package com.qing.common.base.dao.impl;

import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

/**
 * 	使用spring JdbcDao
 * @author       luoqinglong
 * @since        清清网系统, 2014-8-20
 */
public class BaseJdbcDao extends JdbcDaoSupport {

	protected static Logger log = Logger.getLogger(BaseJdbcDao.class);
	public static final String PATTERN_DATE_TIME = "yyyy-MM-dd HH:mm:ss";
	public static final String PATTERN_DATE = "HH:mm:ss";

	/**
	 * 使用默认的jdbc查询总记录条数
	 * @author  luoqinglong
	 */

	public int queryCount(String sql, Object[] args) {
		return Long.valueOf(this.queryCount(this.getJdbcTemplate(), sql, args)).intValue();
	}

	/**
	 * 
	 * 分页查询
	 * @author       luoqinglong
	 * @since        清清网系统, 2014-8-20
	 */
	protected <T> QueryResult<T> pageQuery(Class<T> clazz, String sql, Object[] args, QueryResult<T> result) {
		return this.pageQuery(this.getJdbcTemplate(), clazz, sql, args, result);
	}

	/**
	 * 查询所有记录
	 * @author       luoqinglong
	 * @since        清清网系统, 2014-8-20
	 */
	protected <T> List<T> queryList(Class<T> clazz, String sql, Object[] args) {
		return this.queryList(this.getJdbcTemplate(), clazz, sql, args);
	}

	protected void delById() {

	}

	/****************************************************************************
	 * 
	 * begin*****使用自定义jdbcTemplate
	 * 
	 * *************************************************************************/

	/**
	 * 查询总记录条数
	 * (功能详细描述)
	 * @author       luoqinglong
	 * @since        清清网系统, 2014-8-20
	 */
	public int queryCount(JdbcTemplate jdbcTemplate, String sql, Object[] args) {
		String countQuery = " select count(*) " + BaseJdbcDao.removeSelect(BaseJdbcDao.removeOrders(sql));
		return Long.valueOf(jdbcTemplate.queryForLong(countQuery, args)).intValue();
	}

	/**
	 * 
	 * 分页查询
	 * @author       luoqinglong
	 * @since        清清网系统, 2014-8-20
	 */
	protected <T> QueryResult<T> pageQuery(JdbcTemplate jdbcTemplate, Class<T> clazz, String sql, Object[] args,
			QueryResult<T> result) {
		Integer totalCount = this.queryCount(sql, args);
		if (totalCount < 1) {
			result.setTotalCount(0);
			result.setTotalPage(0);
			result.setResult(new ArrayList<T>());
		} else {
			result.setTotalCount(totalCount);
			StringBuilder sb = new StringBuilder(sql);
			sb.append(" limit ").append(
					BaseJdbcDao.getStartOfPage(result.getPageNo(), result.getPageSize(), totalCount));
			sb.append(",").append(result.getPageSize());
			List<T> list = this.queryList(jdbcTemplate, clazz, sb.toString(), args);

			result.setResult(list);
			int totalPage = totalCount % result.getPageSize() > 0 ? totalCount / result.getPageSize() + 1 : totalCount
					/ result.getPageSize();
			result.setTotalPage(totalPage);
		}
		return result;
	}

	/**
	 * 查询记录，基于SQL 
	 * @param clazz 返回值集合类型  注意clazz的field和查询结果的column对应，忽略大小写
	 * @param sql   查询语句
	 * @param args  参数列表
	 * @return      实体集合，该实体不需要配置文件映射，一般的pojo
	 */
	protected <T> List<T> queryList(JdbcTemplate jdbcTemplate, Class<T> clazz, String sql, Object[] args) {
		List<T> rs = new ArrayList<T>();
		List<Map<String, Object>> mapList = null;
		if (args != null && args.length > 0) {
			mapList = jdbcTemplate.queryForList(sql, args);
		} else {
			try {
				mapList = jdbcTemplate.queryForList(sql);
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
		rs = this.convertColToObj(mapList, clazz);
		return rs;
	}

	/****************************************************************************
	 * 
	 * end*****使用自定义jdbcTemplate
	 * 
	 * *************************************************************************/

	protected static String removeSelect(String hql) {
		int beginPos = hql.toLowerCase().indexOf("from");
		return hql.substring(beginPos);
	}

	protected static String removeOrders(String hql) {
		Pattern p = Pattern.compile("order\\s*by[\\w|\\W|\\s|\\S]*", Pattern.CASE_INSENSITIVE);
		Matcher m = p.matcher(hql);
		StringBuffer sb = new StringBuffer();
		while (m.find()) {
			m.appendReplacement(sb, "");
		}
		m.appendTail(sb);
		return sb.toString();
	}

	protected <T> List<T> convertColToObj(List<Map<String, Object>> mapList, Class<T> clazz) {
		List<T> rs = null;
		if (mapList != null && !mapList.isEmpty()) {
			try {
				rs = new ArrayList<T>();
				Object value;
				for (Map<String, Object> map : mapList) {

					T obj = clazz.newInstance();
					Set<String> keys = map.keySet();
					for (String key : keys) {
						value = map.get(key);
						if (value != null) {
							BaseJdbcDao.setProperty(obj, String.valueOf(key), value);
						}
					}
					rs.add(obj);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return rs;
	}

	@SuppressWarnings("rawtypes")
	protected static void setProperty(Object bean, String name, Object value) {
		if (bean != null && name != null && value != null) {

			Class clazz = bean.getClass();
			Method[] methods = clazz.getMethods();
			if (methods != null) {
				for (Method method : methods) {
					if (method.getName().startsWith("set")
							&& name.toLowerCase().replaceAll("_", "")
									.equals(method.getName().substring(3).toLowerCase().replaceAll("_", ""))) {
						Class[] types = method.getParameterTypes();
						if (types != null && types.length > 0) {
							Object val = BaseJdbcDao.convertValue(types[0], String.valueOf(value));
							try {
								method.invoke(bean, val);
								break;
							} catch (Exception e) {
								e.printStackTrace();
							}

						}
					}
				}
			}
		}
	}

	@SuppressWarnings("rawtypes")
	public static Object convertValue(Class clazz, String value) {
		Object rs = null;
		if (clazz != null && value != null) {
			if (String.class.equals(clazz)) {
				rs = value;
			} else if (Integer.class.equals(clazz) || Integer.TYPE.equals(clazz)) {
				rs = BaseJdbcDao.getInteger(value);
			} else if (Short.class.equals(clazz) || Short.TYPE.equals(clazz)) {
				rs = BaseJdbcDao.getShort(value);
			} else if (Long.class.equals(clazz) || Long.TYPE.equals(clazz)) {
				rs = BaseJdbcDao.getLong(value);
			} else if (Double.class.equals(clazz) || Double.TYPE.equals(clazz)) {
				rs = BaseJdbcDao.getDouble(value);
			} else if (Float.class.equals(clazz) || Float.TYPE.equals(clazz)) {
				rs = BaseJdbcDao.getFloat(value);
			} else if (BigDecimal.class.equals(clazz)) {
				rs = BaseJdbcDao.getBigDecimal(value);
			} else if (Byte.class.equals(clazz) || Byte.TYPE.equals(clazz)) {
				rs = BaseJdbcDao.getByte(value);
			} else if (Timestamp.class.equals(clazz) || Date.class.equals(clazz)) {
				if (Timestamp.class.equals(clazz)) {
					if (value.indexOf(":") != -1) {
						rs = BaseJdbcDao.getTimestamp(value, BaseJdbcDao.PATTERN_DATE_TIME);
					} else if (value.indexOf("-") != -1) {
						rs = BaseJdbcDao.getTimestamp(value, BaseJdbcDao.PATTERN_DATE);
					} else {
						rs = BaseJdbcDao.getDate(value, "yyyyMMddHHmmss");
					}
				} else {
					if (value.indexOf(":") != -1) {
						rs = BaseJdbcDao.getDate(value, BaseJdbcDao.PATTERN_DATE_TIME);
					} else if (value.indexOf("-") != -1) {
						rs = BaseJdbcDao.getDate(value, BaseJdbcDao.PATTERN_DATE);
					} else {
						rs = BaseJdbcDao.getDate(value, "yyyyMMddHHmmss");
					}
				}

			}
		}

		return rs;
	}

	/**
	 * 从字符中解析Long
	 * @param value
	 * @return
	 */
	public static Long getLong(String value) {
		Long rs = null;
		try {
			if (StringUtils.isNotBlank(value)) {
				rs = Long.valueOf(value);
			}
		} catch (Exception e) {
			BaseJdbcDao.log.error("getLong error...", e);
		}
		return rs;
	}

	/**
	 * 从字符中解析Integer
	 * @param value
	 * @return
	 */
	public static Integer getInteger(String value) {
		Integer rs = null;
		try {
			if (StringUtils.isNotBlank(value)) {
				rs = Integer.valueOf(value.split("\\.")[0]);
			}
		} catch (Exception e) {
			BaseJdbcDao.log.error("getInteger error...", e);
		}
		return rs;
	}

	public static Short getShort(String value) {
		Short rs = null;
		try {
			if (StringUtils.isNotBlank(value)) {
				rs = Short.valueOf(value);
			}
		} catch (Exception e) {
			BaseJdbcDao.log.error("getShort error...", e);
		}
		return rs;
	}

	public static Double getDouble(String value) {
		Double rs = null;
		try {
			if (StringUtils.isNotBlank(value)) {
				rs = Double.valueOf(value);
			}
		} catch (Exception e) {
			BaseJdbcDao.log.error("getDouble error...", e);
		}
		return rs;
	}

	public static Float getFloat(String value) {
		Float rs = null;
		try {
			if (StringUtils.isNotBlank(value)) {
				rs = Float.valueOf(value);
			}
		} catch (Exception e) {
			BaseJdbcDao.log.error("getFloat error...", e);
		}
		return rs;
	}

	public static BigDecimal getBigDecimal(String value) {
		BigDecimal rs = null;
		try {
			if (StringUtils.isNotBlank(value)) {
				rs = new BigDecimal(value);
			}
		} catch (Exception e) {
			BaseJdbcDao.log.error("getBigDecimal error...", e);
		}
		return rs;
	}

	public static Byte getByte(String value) {
		Byte rs = null;
		try {
			if (StringUtils.isNotBlank(value)) {
				rs = Byte.valueOf(value);
			}
		} catch (Exception e) {
			BaseJdbcDao.log.error("getByte error...", e);
		}
		return rs;
	}

	public static Date getDate(String value, String pattern) {
		Date rs = null;
		if (StringUtils.isNotBlank(value)) {
			SimpleDateFormat format = null;
			if (pattern != null) {
				format = new SimpleDateFormat(pattern);
			} else {
				format = new SimpleDateFormat(BaseJdbcDao.PATTERN_DATE_TIME);
			}
			try {
				return format.parse(value);
			} catch (Exception e) {
				BaseJdbcDao.log.error("getDate error:", e);
				return null;
			}
		}
		return rs;
	}

	public static Timestamp getTimestamp(String value, String pattern) {
		Timestamp rs = null;
		if (StringUtils.isNotBlank(value)) {
			return new Timestamp(BaseJdbcDao.getDate(value).getTime());
		}
		return rs;
	}

	public static Date getDate(String value) {
		try {
			SimpleDateFormat format = new SimpleDateFormat(BaseJdbcDao.PATTERN_DATE_TIME);
			return format.parse(value);
		} catch (Exception e) {
			BaseJdbcDao.log.error("getDate error:", e);
			return null;
		}
	}

	public static Timestamp getTimestamp(String value) {
		Timestamp rs = null;
		if (StringUtils.isNotBlank(value)) {
			return new Timestamp(BaseJdbcDao.getDate(value).getTime());
		}
		return rs;
	}

	private static Integer getStartOfPage(Integer pageNo, Integer pageSize, Integer totalCount) {
		int totalPage = totalCount % pageSize > 0 ? totalCount / pageSize + 1 : totalCount / pageSize;
		if (pageNo < 1) {
			return 0;
		}
		if (pageNo > totalPage) {
			return (totalPage - 1) * pageSize;
		}
		return (pageNo - 1) * pageSize;
	}

}
