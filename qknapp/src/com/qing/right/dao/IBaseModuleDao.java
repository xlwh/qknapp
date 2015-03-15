package com.qing.right.dao;

import java.util.List;
import java.util.Map;

import com.qing.common.exception.DaoException;
import com.qing.right.dao.domain.BaseModule;
import com.qing.right.dao.domain.BasePermit;

public interface IBaseModuleDao {

    @SuppressWarnings("rawtypes")
	public List queryTree() throws DaoException;

    public void moveNodeTree(BaseModule smallModule, BaseModule bigModule, BaseModule curModule) throws DaoException;

    public Map<String, BaseModule> getURLMap() throws DaoException;

    /**
     * 加载所有顶层模块
     * 
     * @author huangqingjian/0050
     * @see 相关函数,对于重要的函数建议注释
     * @since 深烟智能物流管理平台, 2013-1-10
     * @Company: Tai woo. Co., Ltd
     * @return
     * @throws DaoException
     */
    public List<Map<String, Object>> getTopModuleList() throws DaoException;

    /**
     * 
     * 根据父模块加载子模块
     * 
     * @author huangqingjian/0050
     * @see 相关函数,对于重要的函数建议注释
     * @since 深烟智能物流管理平台, 2013-1-10
     * @Company: Tai woo. Co., Ltd
     * @param parentId
     * @return
     * @throws DaoException
     */
    public List<BaseModule> getModuleListByParentId(String parentId) throws DaoException;

    public BaseModule getBaseModule(String moduleId) throws DaoException;

    List<BasePermit> getPermitListByParentId(String moduleId) throws DaoException;

    /**
     * 如果是最顶层的菜单，则使用该方法获取菜单等级
     * 
     * @author huangqingjian/0050
     * @see 相关函数,对于重要的函数建议注释
     * @since 深烟智能物流管理平台, 2013-1-23
     * @Company: Tai woo. Co., Ltd
     * @return
     * @throws DaoException
     */
    long getModuleLevel() throws DaoException;

    /**
     * 第二级菜单获取菜单等级的方法
     * 
     * @author huangqingjian/0050
     * @see 相关函数,对于重要的函数建议注释
     * @since 深烟智能物流管理平台, 2013-1-23
     * @Company: Tai woo. Co., Ltd
     * @param parentId
     * @return
     * @throws DaoException
     */
    long getModuleLevel(String parentId) throws DaoException;

    /**
     * 获取类型为0的模块
     * 
     * @author huangqingjian/0050
     * @see 相关函数,对于重要的函数建议注释
     * @since 深烟智能物流管理平台, 2013-1-23
     * @Company: Tai woo. Co., Ltd
     * @return
     * @throws DaoException
     */
    List<BaseModule> getBaseModules() throws DaoException;

}