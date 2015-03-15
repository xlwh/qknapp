package com.qing.right.dao;

import java.util.List;
import java.util.Map;

import com.qing.common.exception.DaoException;
import com.qing.common.util.Page;
import com.qing.right.dao.domain.BaseRole;
import com.qing.vo.OrganVO;

public interface IBaseRoleDao {
    /**
     * 根据组织ID将当前组织和子组织的角色都查询出来
     * 
     * @param organId
     * @return
     * @throws DaoException
     */
    public Page<BaseRole> queryChild(Page<BaseRole> page, String organId) throws DaoException;

    /**
     * 根据当前登陆用户能管理的组织，加载出角色 (一句话功能简述) (功能详细描述)
     * 
     * @author huangqingjian/0050
     * @see 相关函数,对于重要的函数建议注释
     * @since 深烟智能物流管理平台, 2013-1-9
     * @param organVOs
     * @return
     * @throws DaoException
     */
    public List<BaseRole> getAllRoleByOrganId(List<OrganVO> organVOs) throws DaoException;

    /**
     * 根据组织ID获取角色列表
     * 
     * @author huangqingjian/0050
     * @see 相关函数,对于重要的函数建议注释
     * @since 深烟智能物流管理平台, 2013-1-9
     * @Company: Tai woo. Co., Ltd
     * @param organID
     * @return
     * @throws DaoException
     */
    public List<Map<String, Object>> getAllRoleVOByOrganId(String organID) throws DaoException;
}