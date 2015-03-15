package com.qing.right.dao;

import java.util.List;

import com.qing.common.exception.DaoException;
import com.qing.right.dao.domain.BaseOrgan;

public interface IBaseOrganDao {

	public String getOrganParentId(String organId) throws DaoException;

	/**
	 * 依据上级组织ID,取得当前组织最大分级号
	 * 
	 * @param organFather
	 * @return
	 * @throws DaoException
	 */
	public String getMaxOrganLev(String organFather) throws DaoException;

	/**
	 * 检查同一组织下是否有相同的组织名
	 * 
	 * @param em
	 * @param organFather
	 * @param organName
	 * @return
	 * @throws DaoException
	 */
	public Boolean checkOrgan(String organFather, String organName) throws DaoException;

	public BaseOrgan getBaseOrgan(String organId) throws DaoException;

	/**
	 * 
	 * 
	 * Description:根据组织id取得创建时间等于或大于的该组织，既可能为同级或下级组织
	 * 
	 * @param organId
	 * @return
	 * @throws DaoException
	 */
	@SuppressWarnings("rawtypes")
	public List getBelowBaseOrganById(String organId) throws DaoException;

	/**
	 * 
	 * 
	 * Description:根据组织级别获取组织对象
	 * 
	 * @param orgLev
	 * @return
	 * @throws DaoException
	 */
	public BaseOrgan getBaseOrganByLev(String orgLev) throws DaoException;

	/**
	 * 根据ID获取下级组织
	 * 
	 * @param organId
	 * @return
	 * @throws DaoException
	 */
	public List<BaseOrgan> getJustSubOrganById(String organId) throws DaoException;

	/**
	 * Description:获取该组织的下一级子组织
	 * 
	 * @param fatherOrganId
	 * @return
	 * @throws DaoException
	 * 
	 */
	public List<BaseOrgan> getSubOrganById(String organId) throws DaoException;

	/**
	 * 根据组织类型查找最大的门店编号
	 * 
	 * @param organType
	 * @return
	 * @throws DaoException
	 */
	public String getMaxOrganNoByOrganType(String organType) throws DaoException;

	/**
	 * 
	 * 
	 * Description:根据当前组织级别，获取该组织所在集团的所有品牌/代理商/加盟店
	 * 
	 * @param organLev
	 * @param organType
	 *            指定需查询所有品牌/代理商/加盟店
	 * @return
	 * @throws DaoException
	 */
	public List<BaseOrgan> getAllDeptByOrgLev(String organLev, String organType) throws DaoException;

	/**
	 * 
	 * 
	 * Description:Description:根据当前组织级别，获取该组织下属组织
	 * 
	 * @param organLev
	 * @param organType
	 *            指定需查询该组织下所有品牌 /代理商/加盟店
	 * @return
	 * @throws DaoException
	 */

	public List<BaseOrgan> getDeptByOrgLev(String organLev, String organType) throws DaoException;

	/**
	 * 根据ID获取品牌
	 * 
	 * @param organId
	 * @return
	 * @throws DaoException
	 */
	public List<BaseOrgan> getBrandOgan(String organId) throws DaoException;

	/**
	 * 获取所有组织
	 * (功能详细描述)
	 * @author       huangqingjian/0050
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        瑾泉二维码系统, 2013年12月12日
	 * @return
	 * @throws DaoException
	 */
	List<BaseOrgan> getAllOrgan() throws DaoException;

	/**
	 * 根据组织类型获取组织
	 * (功能详细描述)
	 * @author       huangqingjian/0050
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        瑾泉二维码系统, 2013年12月12日
	 * @param type
	 * @return
	 * @throws DaoException
	 */
	List<BaseOrgan> getOrganByType(String type) throws DaoException;

	/**
	 * 根据ERPcode获取组织列表
	 * (功能详细描述)
	 * @author       huangqingjian/0050
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        瑾泉二维码系统, 2013年12月18日
	 * @param erpCode
	 * @return
	 * @throws DaoException
	 */
	List<BaseOrgan> getOrganByERPCode(String erpCode) throws DaoException;

}