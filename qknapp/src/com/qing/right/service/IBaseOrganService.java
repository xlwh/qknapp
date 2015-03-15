package com.qing.right.service;

import java.util.List;

import com.qing.common.exception.DaoException;
import com.qing.common.exception.ServiceException;
import com.qing.right.dao.domain.BaseOrgan;
import com.qing.right.dao.domain.BaseOrganDetail;
import com.qing.vo.OrganVO;
import com.qing.vo.ZtreeVo;

public interface IBaseOrganService {

	/**
	 * 依据上级组织ID,取得当前组织最大分级号
	 * 
	 * @param userId
	 * @param organId
	 * @return
	 * @throws ServiceException
	 */
	public String getMaxOrganLev(String organFather, String lev) throws ServiceException;

	/**
	 * 检查同一组织下是否有相同的组织名
	 * 
	 * @param em
	 * @param organFather
	 * @param organName
	 * @return
	 * @throws ServiceException
	 */
	public Boolean checkOrgan(String organFather, String organName) throws ServiceException;

	/**
	 * 保存或者修改组织
	 * 
	 * @param baseOrgan
	 * @return
	 * @throws ServiceException
	 */
	public void saveOrUpdateBaseOrgan(BaseOrgan baseOrgan, BaseOrganDetail baseOrganDetail) throws ServiceException;

	/**
	 * 获取单个组织 (一句话功能简述) (功能详细描述)
	 * 
	 * @author huangqingjian/0050
	 * @see 相关函数,对于重要的函数建议注释
	 * @since 深烟智能物流管理平台, 2013-1-8
	 * @param organId
	 * @return
	 * @throws ServiceException
	 */
	public BaseOrgan getBaseOrgan(String organId) throws ServiceException;

	/**
	 * 根据一组ID或单个ID进行删除 (一句话功能简述) (功能详细描述)
	 * 
	 * @author huangqingjian/0050
	 * @see 相关函数,对于重要的函数建议注释
	 * @since 深烟智能物流管理平台, 2013-1-8
	 * @param ids
	 * @return
	 */
	public List<String> deleteBaseOrgans(String[] ids) throws ServiceException;

	/**
	 * 
	 * 
	 * Description:根据组织ID获得组织树
	 * 
	 * @param organId
	 * @return
	 * @throws ServiceException
	 */
	public List<OrganVO> getOrganTree(String organId) throws ServiceException;

	/**
	 * 
	 * 
	 * Description:根据组织级别获取组织对象
	 * 
	 * @param orgLev
	 * @return
	 * @throws DaoException
	 */
	public BaseOrgan getBaseOrganByLev(String orgLev) throws ServiceException;

	/**
	 * 根据ID判断当前用户是否集团 ，是集团则以List返回当前集团和当前集团下的品牌,否则返回null
	 * 
	 * @param organId
	 * @return
	 * @throws ServiceException
	 */
	public List<BaseOrgan> getBrandGOrBListById(String brandId) throws ServiceException;

	/**
	 * 根据ID判断当前用户是否集团 ，是集团则以String返回当前集团和当前集团下的品牌的Id字符串,是品牌下的返回所在的品牌Id,其它返回null;
	 * 如：'aId','bId','cId' 或 'aId' 或 null
	 * 
	 * @param organId
	 * @return
	 * @throws ServiceException
	 */
	public String getBrandGOrBIdsToStringById(String brandId) throws ServiceException;

	/**
	 * 
	 * 获取组织的儿子节点
	 * 
	 * @param organizationId
	 * @return
	 * @throws ServiceException
	 * 
	 */
	public List<BaseOrgan> getSonOrganList(String organizationId) throws ServiceException;

	/**
	 * 根据组织类别获取最大门店编号
	 * 
	 * @param organType
	 * @return
	 * @throws ServiceException
	 */
	public String getMaxOrganNoByOrganType(String organType) throws ServiceException;

	/**
	 * 
	 * 
	 * Description:根据当前组织级别，获取该组织所在集团的所有品牌/代理商/加盟店
	 * 
	 * @param organLev
	 * @param organType
	 *            指定需查询所有品牌/代理商/加盟店
	 * @return
	 */
	public List<BaseOrgan> getAllDeptByOrgLev(String organLev, String organType) throws ServiceException;

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

	public List<BaseOrgan> getDeptByOrgLev(String organLev, String organType) throws ServiceException;

	/**
	 * 取品牌树
	 * 
	 * @param brandId
	 * @return
	 * @throws ServiceException
	 */
	public List<ZtreeVo> getOrganZTreeByBrandId(BaseOrgan baseOrgan) throws ServiceException;

	/**
	 * 获取组织的儿子节点
	 * 
	 * @param organizationId
	 * @return
	 * @throws ServiceException
	 */
	public String getSonOrganString(String organizationId) throws ServiceException;

	/**
	 * 获取所有组织
	 * (功能详细描述)
	 * @author       huangqingjian/0050
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        瑾泉二维码系统, 2013年12月12日
	 * @return
	 * @throws ServiceException
	 */
	List<BaseOrgan> getAllOrgan() throws ServiceException;

	/**
	 * 根据TYPE获取组织
	 * (功能详细描述)
	 * @author       huangqingjian/0050
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        瑾泉二维码系统, 2013年12月12日
	 * @param type
	 * @return
	 * @throws ServiceException
	 */
	List<BaseOrgan> getOrganByType(String type) throws ServiceException;

	/**
	 * 根据ERPcode获取组织
	 * (功能详细描述)
	 * @author       huangqingjian/0050
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        瑾泉二维码系统, 2013年12月18日
	 * @param erpCode
	 * @return
	 * @throws ServiceException
	 */
	List<BaseOrgan> getOrganByErpCode(String erpCode) throws ServiceException;
}
