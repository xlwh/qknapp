package com.qing.right.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.qing.common.base.dao.HibernateDao;
import com.qing.common.exception.DaoException;
import com.qing.common.exception.handle.ExceptionHandle;
import com.qing.common.util.ErrorCode;
import com.qing.common.util.Page;
import com.qing.common.util.StringUtil;
import com.qing.right.dao.IOrganTypeTemplateDao;
import com.qing.right.dao.domain.BaseOrgan;
import com.qing.right.dao.domain.BaseOrganTypeTemplate;
import com.qing.vo.OrganTypeZtreeVo;

/**
 * 组织类型数据库访问层
 * 
 * @author tantaoxin
 * @version 创建时间：2013-09-12 下午14：04
 * 
 */
@Repository
public class OrganTypeTemplateDaoImpl implements IOrganTypeTemplateDao {
	@Resource
	private HibernateDao<BaseOrganTypeTemplate, String> organTypeTemplateHibernateDaoImpl;

	private JdbcTemplate jdbcTemplate;

	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	/**
	 * 列表
	 * 
	 * @param conditionMap
	 * @return
	 * @throws DaoException
	 */
	@SuppressWarnings("rawtypes")
	public List<BaseOrganTypeTemplate> queryOrganTypeInfByCon(Map conditionMap) throws DaoException {
		String hql = new String();
		hql = covertMapTohql(conditionMap, "normalQuery");
		List<BaseOrganTypeTemplate> organTypeTemplates = new ArrayList<BaseOrganTypeTemplate>();
		organTypeTemplates = organTypeTemplateHibernateDaoImpl.findByValues(hql, null, false);
		return organTypeTemplates;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ls.dao.impl.IOrganTypeTemplateDao#getOrganTypeTemplateZtree()
	 */
	public List<OrganTypeZtreeVo> getOrganTypeTemplateZtree(BaseOrgan bo) throws DaoException {
		StringBuffer hql = new StringBuffer();
		List<BaseOrganTypeTemplate> organTypeTemplates = null;
		List<OrganTypeZtreeVo> ztreeVos = null;
		hql.append("  from BaseOrganTypeTemplate o where 1=1 ");
		if (!"0".equals(bo.getOrganId())) {
			if (StringUtil.isValidStr(bo.getOrganTypeId())) {
				hql.append(" and o.fatherTypeId='");
				hql.append(bo.getOrganTypeId());
				hql.append("'");
			} else {
				hql.append(" and o.fatherTypeId='error'");
			}
		}
		hql.append(" order by o.typeLevel");
		try {
			organTypeTemplates = organTypeTemplateHibernateDaoImpl.findByValues(hql.toString(), null, false);
			if (null != organTypeTemplates && organTypeTemplates.size() > 0) {
				ztreeVos = covertOrganTypeToZtree(organTypeTemplates);
			}
		} catch (DaoException e) {
			ExceptionHandle.throwDaoException(e, ErrorCode.DAO_QUERY_ERROR_004,
					"OrganTypeTemplateDaoImpl.getOrganTypeTemplateZtree()" + "生成组织类型树失败！");

		}
		return ztreeVos;
	}

	private List<OrganTypeZtreeVo> covertOrganTypeToZtree(List<BaseOrganTypeTemplate> organTypeTemplates) {
		List<OrganTypeZtreeVo> ztreeVos = new ArrayList<OrganTypeZtreeVo>();
		for (BaseOrganTypeTemplate organTypeTemplate : organTypeTemplates) {
			OrganTypeZtreeVo ztreeVo = new OrganTypeZtreeVo();
			ztreeVo.setId(organTypeTemplate.getTypeId());
			ztreeVo.setpId(organTypeTemplate.getFatherTypeId());
			ztreeVo.setTypeValue(organTypeTemplate.getTypeValue());
			ztreeVo.setName(organTypeTemplate.getTypeName());
			ztreeVo.setLevels(organTypeTemplate.getTypeLevel());
			ztreeVos.add(ztreeVo);
		}
		return ztreeVos;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ls.dao.impl.IOrganTypeTemplateDao#getLevelById(java.lang.String)
	 */
	public String getLevelById(String typeId) {
		String level = "";
		String sql = "SELECT bott.TYPE_LEVEL FROM T_BASE_ORGAN_TYPE_TEMPLATE bott WHERE bott.TYPE_ID = ?";
		try {
			// jdbcTemplate.queryForObject当查询为空时会抛出异常
			level = jdbcTemplate.queryForObject(sql, String.class, typeId);
		} catch (EmptyResultDataAccessException e) {
			level = "";
		}
		return level;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.ls.dao.impl.IOrganTypeTemplateDao#getMaxLevelByParId(java.lang.String
	 * )
	 */
	public String getMaxLevelByParId(String parTypeId) {
		String level = "";
		String sql = "SELECT MAX(bott.TYPE_LEVEL) FROM T_BASE_ORGAN_TYPE_TEMPLATE bott WHERE bott.FATHER_TYPE_ID = ?";
		try {
			level = jdbcTemplate.queryForObject(sql, String.class, parTypeId);
		} catch (EmptyResultDataAccessException e) {
			level = "";
		}
		return level;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.ls.dao.impl.IOrganTypeTemplateDao#getCurOrganTypeLevel(com.ls.dao
	 * .domain.BaseOrganTypeTemplate)
	 */
	public String getCurOrganTypeLevel(BaseOrganTypeTemplate organTypeTemplate) {
		String level = "";
		level = getMaxLevelByParId(organTypeTemplate.getFatherTypeId()) == null ? ""
				: getMaxLevelByParId(organTypeTemplate.getFatherTypeId());
		if ("".equals(level)) {
			level = getLevelById(organTypeTemplate.getFatherTypeId()) == null ? "" : getLevelById(organTypeTemplate
					.getFatherTypeId());
			level += "001";
		} else {
			// 由于000转成long时会变成0，所以在字符000加1，之后再截取
			level = (StringUtil.getStrTolong(1 + level) + 1) + "";
			level = level.substring(1);
		}
		return level;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ls.dao.impl.IOrganTypeTemplateDao#deleteByIds(java.lang.String)
	 */
	public boolean deleteByIds(String ids) throws DaoException {
		StringBuffer hql = new StringBuffer();
		hql.append(" delete from BaseOrganTypeTemplate o where 1=1 ");
		hql.append(" and o.typeId ").append(" in ").append(" ( ").append(ids).append(" ) ");
		try {
			organTypeTemplateHibernateDaoImpl.remove(hql.toString(), null);
			return true;
		} catch (Exception e) {
			ExceptionHandle.throwDaoException(e, ErrorCode.DAO_DELETE_ERROR_002,
					"OrganTypeTemplateDaoImpl.deleteByIds()");
			return false;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.ls.dao.impl.IOrganTypeTemplateDao#getAllSonOrganType(java.lang.String
	 * )
	 */
	public List<BaseOrganTypeTemplate> getAllSonOrganType(String typeLevel) throws DaoException {
		StringBuffer hql = new StringBuffer();
		List<BaseOrganTypeTemplate> organTypeTemplates = null;
		hql.append(" from  BaseOrganTypeTemplate ").append(" WHERE 1=1 ").append(" and typeLevel like ").append("'")
				.append(typeLevel).append("%").append("'").append(" order by typeLevel");
		try {
			organTypeTemplates = organTypeTemplateHibernateDaoImpl.findByValues(hql.toString(), null, false);
		} catch (DaoException e) {
			ExceptionHandle.throwDaoException(e, ErrorCode.DAO_QUERY_ERROR_004,
					"OrganTypeTemplateDaoImpl.getAllSonOrganType()" + "根据组织类别等级获取子所有子组织类别！");

		}

		return organTypeTemplates;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.ls.dao.impl.IOrganTypeTemplateDao#getDirectSonOrganType(java.lang
	 * .String)
	 */
	public List<BaseOrganTypeTemplate> getDirectSonOrganType(String typeIds) throws DaoException {
		StringBuffer hql = new StringBuffer();
		List<BaseOrganTypeTemplate> organTypeTemplates = null;
		hql.append(" from  BaseOrganTypeTemplate ").append(" WHERE 1=1 ").append(" and fatherTypeId in ( ")
				.append(typeIds).append(")");
		try {
			organTypeTemplates = organTypeTemplateHibernateDaoImpl.findByValues(hql.toString(), null, false);
		} catch (DaoException e) {
			ExceptionHandle.throwDaoException(e, ErrorCode.DAO_QUERY_ERROR_004,
					"OrganTypeTemplateDaoImpl.getDirectSonOrganType()" + "根据组织类别ID获取直接子组织类别！");

		}

		return organTypeTemplates;
	}

	/**
	 * 
	 * 
	 * Description:把查询map转为HQL，fuzzyQuery 为模糊查询，normalQuery普通查询
	 * 
	 * @param conditionMap
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	private String covertMapTohql(Map conditionMap, String type) {
		StringBuffer hql = new StringBuffer();
		hql.append(" from BaseOrganTypeTemplate o where 1=1 ");
		if (!conditionMap.isEmpty()) {
			for (Object key : conditionMap.keySet()) {
				hql.append(" and ");
				if ("fuzzyQuery".equals(type)) {
					if ("brandId".equals(key)) {
						hql.append(key).append(" in ").append(" ( ").append(conditionMap.get(key)).append(" ) ");
					} else {
						hql.append(key).append(" like ").append(" '%").append(conditionMap.get(key)).append("%' ");
					}

				} else {
					hql.append(key).append(" in ").append(" ( ").append(conditionMap.get(key)).append(" ) ");
				}

			}
			// sb = sb.delete(sb.lastIndexOf("and"), sb.lastIndexOf("and") + 3);
		}
		return hql.toString();

	}

	/**
	 * 
	 * 
	 * Description:把统计map转为HQL,fuzzyQuery 为模糊查询，normalQuery普通查询
	 * 
	 * @param conditionMap
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	private String covertMapToCounthql(Map conditionMap, String type) {
		StringBuffer hql = new StringBuffer();
		hql.append(" select count(o.typeId) from BaseOrganTypeTemplate o where 1=1 ");
		if (!conditionMap.isEmpty()) {
			for (Object key : conditionMap.keySet()) {
				hql.append(" and ");
				if ("fuzzyQuery".equals(type)) {
					if ("brandId".equals(key)) {
						hql.append(key).append(" in ").append(" ( ").append(conditionMap.get(key)).append(" ) ");
					} else {
						hql.append(key).append(" like ").append(" '%").append(conditionMap.get(key)).append("%'");
					}
				} else {
					hql.append(key).append(" in ").append(" ( ").append(conditionMap.get(key)).append(" )");
				}
			}
		}
		return hql.toString();

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.ls.dao.impl.IOrganTypeTemplateDao#queryOrganTypeInfo(com.thwl.common
	 * .util.Page, java.util.Map)
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Page queryOrganTypeInfo(Page page, Map conditionMap) throws DaoException {
		String sb1 = new String();
		String sb2 = new String();
		sb1 = covertMapTohql(conditionMap, "fuzzyQuery");
		sb2 = covertMapToCounthql(conditionMap, "fuzzyQuery");
		int count = 0;

		// 查询列表
		organTypeTemplateHibernateDaoImpl.findByPage(page, sb1.toString(), null);

		// 查询总数
		count = organTypeTemplateHibernateDaoImpl.findNumByHQL(sb2.toString(), null);
		if (count > 0) {
			page.setTotalCount(count);
		} else {
			page.setTotalCount(count);
			page.setPageNo(0);
		}
		return page;
	}

	/**
	 * 查看节点下是否存在相同的类型值
	 * 
	 * @param stypeValue
	 * @param typeId
	 * @return
	 * @throws DaoException
	 */
	public boolean checkSameTypeValueInSame(String stypeValue, String typeId) throws DaoException {
		StringBuffer hql = new StringBuffer();
		List<BaseOrganTypeTemplate> organTypeTemplates = null;
		hql.append(" from  BaseOrganTypeTemplate  WHERE 1=1  and fatherTypeId = '").append(typeId).append("'");
		hql.append(" and typeValue='").append(stypeValue).append("'");
		try {
			organTypeTemplates = organTypeTemplateHibernateDaoImpl.findByValues(hql.toString(), null, false);
			if (organTypeTemplates != null && organTypeTemplates.size() > 0) {
				return true;
			} else {
				return false;
			}
		} catch (DaoException e) {
			ExceptionHandle.throwDaoException(e, ErrorCode.DAO_QUERY_ERROR_004,
					"OrganTypeTemplateDaoImpl.getDirectSonOrganType()" + "查看节点下是否存在相同的类型值！");
			return true;
		}
	}

	@Override
	public List<BaseOrganTypeTemplate> getOrganTypeByType(String type) throws DaoException {
		StringBuffer hql = new StringBuffer();
		List<BaseOrganTypeTemplate> organTypeTemplates = null;
		hql.append(" from  BaseOrganTypeTemplate ").append(" where typeValue = ").append(type).append(")");
		try {
			organTypeTemplates = organTypeTemplateHibernateDaoImpl.findByValues(hql.toString(), null, false);
		} catch (DaoException e) {
			ExceptionHandle.throwDaoException(e, ErrorCode.DAO_QUERY_ERROR_004,
					"OrganTypeTemplateDaoImpl.getDirectSonOrganType()" + "根据组织类别ID获取直接子组织类别！");

		}

		return organTypeTemplates;
	}
}
