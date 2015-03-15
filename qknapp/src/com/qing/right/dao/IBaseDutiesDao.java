package com.qing.right.dao;

import java.util.List;
import java.util.Map;

import com.qing.common.exception.DaoException;
import com.qing.common.util.Page;
import com.qing.right.dao.domain.BaseDuties;
import com.qing.vo.OrganVO;

public interface IBaseDutiesDao {
    public Map<String, BaseDuties> getDutiedesc() throws DaoException;

    /**
     * 查询子组织用户
     * 
     * @param organId
     * @return
     * @throws DaoException
     */
    @SuppressWarnings("rawtypes")
	public Page queryDutiesByOrganId(Page page, String organId) throws DaoException;

    /**
     * 根据组织ID将当前组织和子组织的职位都查询出来
     * 
     * @param organId
     * @return
     * @throws DaoException
     */
    public Page<BaseDuties> queryChild(Page<BaseDuties> page, String organId) throws DaoException;

    /**
     * 根据组织ID将当前组织和子组织的职位都查询出来
     * 
     * @param organId
     * @return
     * @throws DaoException
     */
    public List<BaseDuties> getDutiesList(String organId, List<OrganVO> organVOs) throws DaoException;

    /**
     * 根据职务ID将对应的职务信息查询出来
     * 
     * @param dutiesId
     * @return
     * @throws DaoException
     */
    public BaseDuties getBaseDuties(String dutiesId) throws DaoException;

    /**
     * 获取关联用户的ID
     * 
     * @author huangqingjian/0050
     * @see 相关函数,对于重要的函数建议注释
     * @since 深烟智能物流管理平台, 2013-1-17
     * @Company: Tai woo. Co., Ltd
     * @param dutiesId
     * @return
     * @throws DaoException
     */
    List<String> getUserIdList(String dutiesId) throws DaoException;
}
