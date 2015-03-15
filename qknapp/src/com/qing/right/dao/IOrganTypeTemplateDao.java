package com.qing.right.dao;

import java.util.List;
import java.util.Map;

import com.qing.common.exception.DaoException;
import com.qing.common.util.Page;
import com.qing.right.dao.domain.BaseOrgan;
import com.qing.right.dao.domain.BaseOrganTypeTemplate;
import com.qing.vo.OrganTypeZtreeVo;

public interface IOrganTypeTemplateDao {

	/**
	 * 列表
	 * 
	 * @param conditionMap
	 * @return
	 * @throws DaoException
	 */
	@SuppressWarnings("rawtypes")
	public List<BaseOrganTypeTemplate> queryOrganTypeInfByCon(Map conditionMap) throws DaoException;

	/**
	 * 查询出所有有的组织类型
	 * 
	 * @return
	 * @throws DaoException
	 */
	public List<OrganTypeZtreeVo> getOrganTypeTemplateZtree(BaseOrgan baseOrgan) throws DaoException;

	/**
	 * 根据ID查询出级别
	 * 
	 * @param typeId
	 * @return
	 */
	public String getLevelById(String typeId);

	/**
	 * 根据父ID查询出是高级别
	 * 
	 * @param parTypeId
	 * @return
	 */
	public String getMaxLevelByParId(String parTypeId);

	/**
	 * 获得当前Level
	 * 
	 * @param organTypeTemplate
	 * @return
	 */
	public String getCurOrganTypeLevel(BaseOrganTypeTemplate organTypeTemplate);

	/**
	 * 删除
	 * 
	 * @param ids
	 * @return
	 * @throws DaoException
	 */
	public boolean deleteByIds(String ids) throws DaoException;

	public List<BaseOrganTypeTemplate> getAllSonOrganType(String typeLevel) throws DaoException;

	public List<BaseOrganTypeTemplate> getDirectSonOrganType(String typeIds) throws DaoException;

	/**
	 * 分页
	 * 
	 * @param page
	 * @param conditionMap
	 * @return
	 * @throws DaoException
	 */
	@SuppressWarnings("rawtypes")
	public Page queryOrganTypeInfo(Page page, Map conditionMap) throws DaoException;

	/**
	 * 查看节点下是否存在相同的类型值
	 * 
	 * @param stypeValue
	 * @param typeId
	 * @return
	 * @throws DaoException
	 */
	public boolean checkSameTypeValueInSame(String stypeValue, String typeId) throws DaoException;

	/**
	 * 根据类型编码获取类型对象
	 * (功能详细描述)
	 * @author       huangqingjian/0050
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        瑾泉二维码系统, 2013年12月13日
	 * @param type
	 * @return
	 * @throws DaoException
	 */
	List<BaseOrganTypeTemplate> getOrganTypeByType(String type) throws DaoException;

}