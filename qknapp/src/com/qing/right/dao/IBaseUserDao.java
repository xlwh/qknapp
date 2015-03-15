package com.qing.right.dao;

import java.util.List;
import java.util.Map;

import com.qing.common.exception.DaoException;
import com.qing.common.exception.ServiceException;
import com.qing.common.util.Page;
import com.qing.right.dao.domain.BaseUser;
import com.qing.vo.OrganVO;

public interface IBaseUserDao {

    @SuppressWarnings("rawtypes")
	public List findUsers(Object[] params, List<OrganVO> organVOs) throws DaoException;

    /**
     * 依据用户登录名，取得用户信息
     * 
     * @param loginCode
     *            用户登录名
     * @return 用户信息
     * @throws DaoException
     */
    public BaseUser findUserByLoginCode(String loginCode) throws DaoException;

    /**
     * 重设用户密码
     * 
     * @param userId
     *            用户ID
     * @param password
     *            用户新密码
     * @throws DaoException
     */
    public void resetPassword(String userId, String password) throws DaoException;

    /**
     * 根据查询条件取得用户信息
     * 
     * @param params
     *            参数
     * @return
     * @throws DaoException
     */
    @SuppressWarnings("rawtypes")
	public Page queryBaseUser(Page page, int stype, Object[] params, Object[] param) throws DaoException;

    /**
     * 查询子组织用户
     * 
     * @param organId
     * @return
     * @throws DaoException
     */
    @SuppressWarnings("rawtypes")
	public Page queryUserByOrganId(Page page, String organId) throws DaoException;

    /**
     * 用户最新一次登录时，更新用户信息
     * 
     * @param loginCode
     *            用户登录名
     * @param ipAddress
     *            IP地址
     * @throws DaoException
     */
    void updateUserByLastLogin(String loginCode, String ipAddress) throws DaoException;

    /**
     * 检查登陆账号是否重复
     * 
     * @param loginCode
     * @return
     * @throws DaoException
     */
    public Boolean checkLoginCode(String loginCode) throws DaoException;

    /**
     * 根据登陆号和密码登陆
     * 
     * @param em
     * @param loginCode
     * @param password
     * @return
     * @throws DaoException
     */
    BaseUser loginCheck(String loginCode, String password) throws DaoException;

    /**
     * 删除用户信息
     * 
     * @param userId
     * @return
     * @throws DaoException
     */
    public Boolean delBaseUser(String userId) throws DaoException;

    /**
     * 根据登陆编号权限获取手持机用户权限
     * 
     * @param loginCode
     * @return
     */
    @SuppressWarnings("rawtypes")
	public List getHandMobilePermitByLoginCode(String loginCode);

    public BaseUser getBaseUser(String userId) throws DaoException;

    /**
     * 根据当前登陆管理员能管理的组织，加载出所有用户
     * 
     * @author huangqingjian/0050
     * @see 相关函数,对于重要的函数建议注释
     * @since 深烟智能物流管理平台, 2013-1-9
     * @param organVOs
     * @return
     * @throws DaoException
     */
    public List<BaseUser> getAllUserByOrganId(List<OrganVO> organVOs) throws DaoException;

    /**
     * 
     * 根据组织ID获取用户列表
     * 
     * @author huangqingjian/0050
     * @see 相关函数,对于重要的函数建议注释
     * @since 深烟智能物流管理平台, 2013-1-9
     * @Company: Tai woo. Co., Ltd
     * @param organId
     * @return
     * @throws ServiceException
     */
    public List<Map<String, Object>> getAllUserVOByOrganId(String organID) throws DaoException;

    /**
     * 根据机器码查询送货组
     * 
     * @param mcnCode
     * @return
     * @throws DaoException
     */
    @SuppressWarnings("rawtypes")
	public Map getRouteCodeByMcnCode(String mcnCode) throws DaoException;

    /**
     * 
     * 
     * Description:根据当前组织ID获取当前组织下所有人
     * 
     * @param organId
     * @return
     * @throws DaoException
     * @throws ServiceException
     */
    public List<BaseUser> getUsersByCurOrganId(String organId) throws DaoException;

    /**
     * 根据登陆号和密码登陆手机端
     * 
     * @param em
     * @param loginCode
     * @param password
     * @return
     * @throws DaoException
     */
    BaseUser loginCheckInPhone(String loginCode, String password) throws DaoException;

}