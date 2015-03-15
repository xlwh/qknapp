package com.qing.right.dao;

import java.util.List;

import com.qing.common.exception.DaoException;
import com.qing.common.util.Page;
import com.qing.right.dao.domain.BaseOrganUser;
import com.qing.vo.OrganVO;

public interface IBaseOrganUserDao {
    public List<String> getOrganIdByUserId(String userId) throws DaoException;

    /**
     * 如果用户是此部门或父级部门的管理员，则返回管理员ID.
     * 
     * @param userId
     *            用户ID
     * @param organId
     *            部门ID
     * @return 管理员ID
     * @throws DaoException
     */
    public String getAdminIdOfOrgan(String userId, String organId) throws DaoException;

    /**
     * 检查管理员是否重复
     * 
     * @param organId
     * @param userId
     * @return
     * @throws DaoException
     */
    public Boolean checkAdmin(String organId, String userId) throws DaoException;

    /**
     * 查询子组织管理员
     * 
     * @param organId
     * @return
     * @throws DaoException
     */
    @SuppressWarnings("rawtypes")
	public Page queryChild(Page page, String organId) throws DaoException;

    /**
     * 根据组织ID将当前组织和子组织的管理用户都查询出来
     * 
     * @param organId
     * @return
     * @throws DaoException
     */
    public List<BaseOrganUser> getOrganUserList(String organId, List<OrganVO> organVOs) throws DaoException;

    /**
     * 根据组织用户ID将对应的组织管理员信息查询出来
     * 
     * @param organUserId
     * @return
     * @throws DaoException
     */
    public BaseOrganUser getBaseOrganUser(String organUserId) throws DaoException;

    List<BaseOrganUser> getOrganUserListByUserId(String userId) throws DaoException;

}