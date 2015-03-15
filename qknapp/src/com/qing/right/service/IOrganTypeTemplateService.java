package com.qing.right.service;

import java.util.List;
import java.util.Map;

import com.qing.common.exception.ServiceException;
import com.qing.right.dao.domain.BaseOrgan;
import com.qing.right.dao.domain.BaseOrganTypeTemplate;
import com.qing.right.dao.domain.BaseUser;
import com.qing.vo.OrganTypeZtreeVo;

public interface IOrganTypeTemplateService {

    /**
     * 新增或修改保存
     * 
     * @param organTypeTemplate
     * @param buser
     * @throws ServiceException
     */
    public String saveOrUpdate(BaseOrganTypeTemplate organTypeTemplate, BaseUser buser) throws ServiceException;

    /**
     * 删除
     * 
     * @param ids
     * @return
     * @throws ServiceException
     */
    public String deleteByIds(String ids) throws ServiceException;

    /**
     * 列表
     * 
     * @param conditionMap
     * @return
     * @throws ServiceException
     */
    @SuppressWarnings("rawtypes")
	public List<BaseOrganTypeTemplate> queryOrganTypeInfByCon(Map conditionMap) throws ServiceException;

    /**
     * 组织类型树
     * 
     * @return
     * @throws ServiceException
     */
    public List<OrganTypeZtreeVo> getOrganTypeZtree(BaseOrgan baseOrgan) throws ServiceException;

    /**
     * 根据组织类别等级获取所有子组织类别
     * 
     * @param typeLevel
     * @return
     * @throws ServiceException
     */
    public List<BaseOrganTypeTemplate> getAllSonOrganType(String typeLevel) throws ServiceException;

    /**
     * 根据组织类别等级获取所有子组织类别
     * 
     * @param typeIds
     * @return
     * @throws ServiceException
     */
    public List<BaseOrganTypeTemplate> getDirectSonOrganType(String typeIds) throws ServiceException;

    /**
     * 是否重复
     * 
     * @param typeValue
     * @param typeName
     * @param typeId
     * @return
     * @throws ServiceException
     */
    public String checkTypeValue(String typeValue, String typeName, String typeId) throws ServiceException;

    public BaseOrganTypeTemplate buildOrganType(BaseOrganTypeTemplate organTypeTemplate, BaseUser buser);

}