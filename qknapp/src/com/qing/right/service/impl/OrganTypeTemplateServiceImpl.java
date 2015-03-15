package com.qing.right.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.qing.common.base.service.BaseService;
import com.qing.common.exception.DaoException;
import com.qing.common.exception.ServiceException;
import com.qing.common.exception.handle.ExceptionHandle;
import com.qing.common.util.DateTimeUtils;
import com.qing.common.util.ErrorCode;
import com.qing.common.util.StringUtil;
import com.qing.right.dao.IOrganTypeTemplateDao;
import com.qing.right.dao.domain.BaseOrgan;
import com.qing.right.dao.domain.BaseOrganTypeTemplate;
import com.qing.right.dao.domain.BaseUser;
import com.qing.right.service.IOrganTypeTemplateService;
import com.qing.vo.OrganTypeZtreeVo;

/**
 * 组织类型服务层
 * 
 * @author tantaoxin
 * @version 创建时间：2013-09-12 下午14：06
 * 
 */
@Service
public class OrganTypeTemplateServiceImpl implements IOrganTypeTemplateService {

    @Resource
    private BaseService<BaseOrganTypeTemplate, String> organTypeTemplateBaseServiceImpl;

    @Resource
    private IOrganTypeTemplateDao organTypeTemplateDaoImpl;

    @SuppressWarnings("rawtypes")
	@Resource
    private BaseService baseServiceImpl;

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.ls.service.impl.IOrganTypeTemplateService#saveOrUpdate(com.ls.dao
     * .domain.BaseOrganTypeTemplate, com.thwl.right.dao.domain.BaseUser)
     */
    @SuppressWarnings("unchecked")
	public String saveOrUpdate(BaseOrganTypeTemplate organTypeTemplate, BaseUser buser) throws ServiceException {
	try {
	    if (StringUtils.isNotEmpty(organTypeTemplate.getTypeId())) {
		// 修改
		BaseOrganTypeTemplate organTypeTemplateOld = (BaseOrganTypeTemplate) baseServiceImpl.findById(BaseOrganTypeTemplate.class,
			organTypeTemplate.getTypeId());
		//BeansCloneUtil.copyBeanWithoutNull(organTypeTemplate, organTypeTemplateOld, new Object[] { "TypeId", "Version" });
		if (StringUtil.isNullOrEmpty(organTypeTemplate.getFatherTypeId())) {
		    organTypeTemplate.setFatherTypeId("0");
		}

		// 换级
		String oldTypeLevel = organTypeTemplateOld.getTypeLevel();
		String newTypeLevel = organTypeTemplateDaoImpl.getCurOrganTypeLevel(organTypeTemplate);
		if (!organTypeTemplateOld.getFatherTypeId().equals(organTypeTemplate.getFatherTypeId())) {
		    if (oldTypeLevel.substring(0, 3).equals(newTypeLevel.substring(0, 3)) && newTypeLevel.length() > oldTypeLevel.length()) {
			// 上级类型不能小于等于本节点类型
			return "2";
		    } else {
			organTypeTemplate.setTypeLevel(newTypeLevel);
		    }
		}
		organTypeTemplate.setModifier(buser.getLoginCode());
		organTypeTemplate.setModifyDate(DateTimeUtils.getCurrentTimestamp());
	    } else {
		// 新增
		organTypeTemplate = buildOrganType(organTypeTemplate, buser);
		if (StringUtil.isNullOrEmpty(organTypeTemplate.getFatherTypeId())) {
		    organTypeTemplate.setFatherTypeId("0");
		}
		organTypeTemplate.setTypeLevel(organTypeTemplateDaoImpl.getCurOrganTypeLevel(organTypeTemplate));

		if (organTypeTemplateDaoImpl
			.checkSameTypeValueInSame(organTypeTemplate.getTypeValue(), organTypeTemplate.getFatherTypeId())) {
		    // 同相的分类名称
		    return "3";
		}
	    }

	    organTypeTemplateBaseServiceImpl.saveOrUpdate(organTypeTemplate);
	    // 保存成功
	    return "1";
	} catch (Exception e) {
	    ExceptionHandle.throwServiceException(e, ErrorCode.SERVICE_SAVE_ERROR_001, "OrganTypeTemplateServiceImpl.saveOrUpdate()");
	    // 失败
	    return "0";
	}
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.ls.service.impl.IOrganTypeTemplateService#deleteByIds(java.lang.String
     * )
     */
    public String deleteByIds(String ids) throws ServiceException {

	try {
	    List<BaseOrganTypeTemplate> organTypeTemplates = getDirectSonOrganType(ids);
	    if (null != organTypeTemplates && organTypeTemplates.size() > 0) {
		return "1";
	    }
	    // 2
	    organTypeTemplateDaoImpl.deleteByIds(ids);
	    return "0";
	} catch (DaoException e) {
	    ExceptionHandle.throwServiceException(e, ErrorCode.SERVICE_QUERY_ERROR_004,
		    "OrganTypeTemplateServiceImpl.queryProductTypeInfo()");
	    return "3";
	}
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.ls.service.impl.IOrganTypeTemplateService#queryOrganTypeInfByCon(
     * java.util.Map)
     */
    @SuppressWarnings("rawtypes")
	public List<BaseOrganTypeTemplate> queryOrganTypeInfByCon(Map conditionMap) throws ServiceException {
	List<BaseOrganTypeTemplate> organTypeTemplates = new ArrayList<BaseOrganTypeTemplate>();
	try {
	    organTypeTemplates = organTypeTemplateDaoImpl.queryOrganTypeInfByCon(conditionMap);
	} catch (DaoException e) {
	    ExceptionHandle.throwServiceException(e, ErrorCode.SERVICE_QUERY_ERROR_004,
		    "OrganTypeTemplateServiceImpl.queryOrganTypeInfByCon()");

	}
	return organTypeTemplates;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.ls.service.impl.IOrganTypeTemplateService#getOrganTypeZtree()
     */
    public List<OrganTypeZtreeVo> getOrganTypeZtree(BaseOrgan bo) throws ServiceException {
	List<OrganTypeZtreeVo> ztreeVos = null;
	try {
	    ztreeVos = organTypeTemplateDaoImpl.getOrganTypeTemplateZtree(bo);
	} catch (DaoException e) {
	    ExceptionHandle.throwServiceException(e, ErrorCode.SERVICE_QUERY_ERROR_004, "OrganTypeTemplateServiceImpl.getOrganTypeZtree()"
		    + "生成组织类型树失败！");

	}
	return ztreeVos;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.ls.service.impl.IOrganTypeTemplateService#getAllSonOrganType(java
     * .lang.String)
     */
    public List<BaseOrganTypeTemplate> getAllSonOrganType(String typeLevel) throws ServiceException {
	List<BaseOrganTypeTemplate> organTypeTemplates = null;
	try {
	    organTypeTemplates = organTypeTemplateDaoImpl.getAllSonOrganType(typeLevel);
	} catch (DaoException e) {
	    ExceptionHandle.throwServiceException(e, ErrorCode.SERVICE_QUERY_ERROR_004, "OrganTypeTemplateServiceImpl.getAllSonOrganType()"
		    + "根据组织类别等级获取所有子组织类别失败！");
	}
	return organTypeTemplates;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.ls.service.impl.IOrganTypeTemplateService#getDirectSonOrganType(java
     * .lang.String)
     */
    public List<BaseOrganTypeTemplate> getDirectSonOrganType(String typeIds) throws ServiceException {
	List<BaseOrganTypeTemplate> organTypeTemplates = null;
	try {
	    organTypeTemplates = organTypeTemplateDaoImpl.getDirectSonOrganType(typeIds);
	} catch (DaoException e) {
	    ExceptionHandle.throwServiceException(e, ErrorCode.SERVICE_QUERY_ERROR_004, "ProductTypeServiceImpl.getProductTypeZtree()"
		    + "根据组织类别等级获取所有子组织类别失败！");
	}
	return organTypeTemplates;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.ls.service.impl.IOrganTypeTemplateService#checkTypeValue(java.lang
     * .String, java.lang.String, java.lang.String)
     */
    @SuppressWarnings("rawtypes")
	public String checkTypeValue(String typeValue, String typeName, String typeId) throws ServiceException {
	List temp = new ArrayList();
	String flag = "0";
	Map conditionMap = new HashMap();

	try {
	    // conditionMap.put("typeCode", "'" + typeCode + "'");
	    // conditionMap.put("brandId", "'" + brandId + "'");
	    // temp = productTypeDao.queryProductTypeInfByCon(conditionMap);
	    if (null != temp && temp.size() > 0) {
		// 编号重复
		flag = "1";
	    } else {
		conditionMap.clear();
		// conditionMap.put("typeName", "'" + typeName + "'");
		// conditionMap.put("brandId", "'" + brandId + "'");
		// temp = productTypeDao.queryProductTypeInfByCon(conditionMap);
		if (null != temp && temp.size() > 0) {
		    // 名称重复
		    flag = "2";
		}
	    }
	} catch (Exception e) {
	    ExceptionHandle.throwServiceException(e, ErrorCode.SERVICE_QUERY_ERROR_004, "ProductTypeServiceImpl.checkAndQueryType()");
	}
	return flag;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.ls.service.impl.IOrganTypeTemplateService#buildOrganType(com.ls.dao
     * .domain.BaseOrganTypeTemplate, com.thwl.right.dao.domain.BaseUser)
     */
    public BaseOrganTypeTemplate buildOrganType(BaseOrganTypeTemplate organTypeTemplate, BaseUser buser) {
	organTypeTemplate.setTypeId(null);
	organTypeTemplate.setCreator(buser.getLoginCode());
	organTypeTemplate.setCreateDate(DateTimeUtils.getCurrentTimestamp());
	return organTypeTemplate;
    }

}
