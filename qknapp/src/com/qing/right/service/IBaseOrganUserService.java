package com.qing.right.service;

import java.util.List;

import com.qing.common.exception.DaoException;
import com.qing.common.exception.ServiceException;
import com.qing.common.util.Page;
import com.qing.right.dao.domain.BaseOrganUser;
import com.qing.vo.OrganUserVO;

public interface IBaseOrganUserService {
    /**
     * 依据用户ID,取得用户所属组织ID列表
     * 
     * @param userId
     *            用户ID
     * @return 用户所属组织ID列表
     * @throws DaoException
     */
    public List<String> getOrganIdByUserId(String userId) throws ServiceException;

    /**
     * 如果用户是此部门或父级部门的管理员，则返回管理员ID.
     * 
     * @param userId
     *            用户登录名
     * @param organId
     *            部门ID
     * @return 管理员ID
     * @throws ServiceException
     */
    public String getAdminIdOfOrgan(String userId, String organId) throws ServiceException;

    /**
     * 检查管理员是否重复
     * 
     * @param userId
     * @param organId
     * @return
     * @throws ServiceException
     */
    public Boolean checkAdmin(String organId, String userId) throws ServiceException;

    /**
     * 查询子组织管理员
     * 
     * @param organId
     * @return
     * @throws ServiceException
     */
    @SuppressWarnings("rawtypes")
	public Page<BaseOrganUser> queryChild(Page page, String organId) throws ServiceException;
    
    /**
     * 保存或者修改组织管理员
     * 
     * @param baseOrganUser
     * @throws ServiceException
     */
    public void saveOrUpdateBaseOrganUser(BaseOrganUser baseOrganUser) throws ServiceException;
    
    /**
     * 根据组织ID将当前组织和子组织的管理用户都查询出来
     * 
     * @param organId
     * @return
     * @throws DaoException
     */
    public List<OrganUserVO> getOrganUserList(String organId) throws ServiceException;
    
    /**
     * 根据组织用户ID将对应的组织管理员信息查询出来
     * 
     * @param organUserId
     * @return
     * @throws DaoException
     */
    public BaseOrganUser getBaseOrganUser(String organUserId) throws ServiceException;
    
    /**
     * 根据组织用户ID将对应的组织管理员信息删除
     * 
     * @param ids
     * @return
     * @throws DaoException
     */
    public List<String> deleteBaseOrganUser(String[] ids) throws ServiceException;
    
    
}
