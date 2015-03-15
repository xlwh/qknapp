package com.qing.right.service;

import java.util.List;
import java.util.Map;

import com.qing.common.exception.ServiceException;
import com.qing.common.util.Page;
import com.qing.right.dao.domain.BaseRole;
import com.qing.vo.RoleVO;

public interface IBaseRoleService {

    /**
     * 根据组织ID将当前组织和子组织的角色都查询出来
     * 
     * @param organId
     * @return
     * @throws ServiceException
     */
    public Page<RoleVO> queryChild(String organId, Page<BaseRole> page) throws ServiceException;

    /**
     * 保存或者修改角色
     * 
     * @param baseOrgan
     * @return
     * @throws ServiceException
     */
    public void saveOrUpdateRole(BaseRole baseRole) throws ServiceException;

    /**
     * 根据组织ID获取所有角色 (一句话功能简述) (功能详细描述)
     * 
     * @author huangqingjian/0050
     * @see 相关函数,对于重要的函数建议注释
     * @since 深烟智能物流管理平台, 2013-1-9
     * @param organId
     * @return
     * @throws ServiceException
     */
    public List<BaseRole> getAllRoleByOrganId(String organId) throws ServiceException;

    /**
     * 
     * 获取角色 列表
     * 
     * @author huangqingjian/0050
     * @see 相关函数,对于重要的函数建议注释
     * @since 深烟智能物流管理平台, 2013-1-9
     * @Company: Tai woo. Co., Ltd
     * @param organId
     * @return
     * @throws ServiceException
     */
    public Map<String, List<RoleVO>> getAllRoleVOByOrganId(String organId) throws ServiceException;

    /**
     * 删除角色
     * 
     * @author huangqingjian/0050
     * @see 相关函数,对于重要的函数建议注释
     * @since 深烟智能物流管理平台, 2013-1-11
     * @Company: Tai woo. Co., Ltd
     * @param RoleId
     * @return
     * @throws ServiceException
     */
    List<String> deleteRoleById(String RoleId) throws ServiceException;

    /**
     * 获取角色实体
     * 
     * @author huangqingjian/0050
     * @see 相关函数,对于重要的函数建议注释
     * @since 深烟智能物流管理平台, 2013-1-11
     * @Company: Tai woo. Co., Ltd
     * @param roleId
     * @return
     * @throws ServiceException
     */
    BaseRole getBaseRole(String roleId) throws ServiceException;
}
