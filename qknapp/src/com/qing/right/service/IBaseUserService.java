package com.qing.right.service;

import java.util.List;
import java.util.Map;

import com.qing.common.exception.DaoException;
import com.qing.common.exception.ServiceException;
import com.qing.common.util.Page;
import com.qing.right.dao.domain.BaseOrgan;
import com.qing.right.dao.domain.BaseUser;
import com.qing.vo.UserVO;

public interface IBaseUserService {

    /**
     * 依据参数, 查询用户信息
     * 
     * @param params
     *            参数数组
     * @return 用户对象列表
     * @throws ServiceException
     */
    @SuppressWarnings("rawtypes")
	public List findUsers(Object[] params, String organId) throws ServiceException;

    /**
     * 依据用户登录名，取得用户信息
     * 
     * @param loginCode
     * @return
     * @throws ServiceException
     */
    public BaseUser findUserByLoginCode(String loginCode) throws ServiceException;

    /**
     * 重设密码
     * 
     * @param userId
     *            用户名
     * @param password
     *            新密码
     * @throws ServiceException
     */
    public void resetPassword(String userId, String password) throws ServiceException;

    /**
     * 根据查询条件取得用户信息
     * 
     * @param params
     *            参数
     * @return
     * @throws ServiceException
     */
    @SuppressWarnings("rawtypes")
	public Page<BaseUser> queryBaseUser(Page page, int stype, Object[] params, Object[] param) throws ServiceException;

    /**
     * 查询子组织用户
     * 
     * @param organId
     * @return
     * @throws ServiceException
     */
    @SuppressWarnings("rawtypes")
	public Page<BaseUser> queryUserByOrganId(Page page, String organId) throws ServiceException;

    /**
     * 检查登陆账号是否重复
     * 
     * @param loginCode
     * @return
     * @throws ServiceException
     */
    public Boolean checkLoginCode(String loginCode) throws ServiceException;

    public void updateUserByLastLogin(String loginCode, String ipAddress) throws ServiceException;

    /**
     * 根据登陆号和密码登陆
     * 
     * @param em
     * @param loginCode
     * @param password
     * @return
     * @throws ServiceException
     */
    public String loginCheck(String loginCode, String password) throws ServiceException;

    /**
     * 保存或者修改用户
     * 
     * @param baseUser
     * @throws ServiceException
     */
    public void saveOrUpdateBaseUser(BaseUser baseUser) throws ServiceException;

    /**
     * 删除用户
     * 
     * @param baseUser
     * @throws ServiceException
     */
    public Boolean delBaseUser(String userId) throws ServiceException;

    /**
     * 根据组织ID获取没有绑定角色的用户
     */
    @SuppressWarnings("rawtypes")
	public List<UserVO> getUnrelevUserByOrganId(Page page, String organId) throws ServiceException;

    /**
     * 根据登陆编号权限获取手持机用户权限
     * 
     * @param loginCode
     * @return
     */
    // public LoginResponse getHandMobilePermitByLoginCode(String loginCode,
    // String password, String machineCode) throws ServiceException;

    public BaseUser getBaseUser(String userId) throws ServiceException;

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
    public List<BaseUser> getAllUserByOrganId(String organId) throws ServiceException;

    /**
     * 
     * 根据组织ID获取用户VO列表
     * 
     * @author huangqingjian/0050
     * @see 相关函数,对于重要的函数建议注释
     * @since 深烟智能物流管理平台, 2013-1-9
     * @Company: Tai woo. Co., Ltd
     * @param organId
     * @return
     * @throws ServiceException
     */
    public Map<String, List<UserVO>> getAllUserVOByOrganId(String organId) throws ServiceException;

    /**
     * 根据一组ID或单个ID进行删除
     * 
     * @author wangxiang
     * @since 深烟智能物流管理平台, 2013-1-15
     * @param ids
     * @return
     */
    public List<String> deleteBaseUser(String[] ids) throws ServiceException;

    /**
     * 根据机器码查询送货组
     * 
     * @param mcnCode
     * @return
     * @throws DaoException
     */
    public String getRouteCodeByMcnCode(String mcnCode) throws ServiceException;

    /**
     * 
     * 
     * Description:根据用户ID取得该用户的上级组织
     * 
     * @param userId
     * @param levSize
     *            上级组织级别长度， 如 3 顶级组织 (集团) ,6 品牌
     * @return
     * @throws ServiceException
     */
    public BaseOrgan getLevBaseOrgByUserId(String userId, int levSize) throws ServiceException;

    /**
     * 
     * 
     * Description:根据用户取得该用户的所属品牌
     * 
     * @param baseUser
     * @return
     * @throws ServiceException
     */
    public BaseOrgan getLevBaseOrgByUserInPhone(BaseUser baseUser) throws ServiceException;

    /**
     * 
     * 
     * Description:根据登录code取得该用户的上级组织
     * 
     * @param loginCode
     * @param levSize
     *            上级组织级别长度， 如 3 顶级组织 (集团) ,6 品牌
     * @return
     * @throws ServiceException
     */
    public BaseOrgan getLevBaseOrgByLoginCode(String loginCode, int levSize) throws ServiceException;

    /**
     * 
     * 
     * Description:根据组织ID取得该用户的上级组织
     * 
     * @param organId
     * @param levSize
     *            上级组织级别长度， 如 3 顶级组织 (集团) ,6 品牌
     * @return
     * @throws ServiceException
     */
    public BaseOrgan getLevBaseOrgByOrganId(String organId, int levSize) throws ServiceException;

    /**
     * 
     * 
     * Description:根据当前组织ID获取当前组织下所有人
     * 
     * @param organId
     * @return
     * @throws ServiceException
     */
    public List<BaseUser> getUsersByCurOrganId(String organId) throws ServiceException;

    /**
     * 根据登陆号和密码登陆手机端
     * 
     * @param em
     * @param loginCode
     * @param password
     * @return
     * @throws ServiceException
     */
    public BaseUser loginCheckInPhone(String loginCode, String password) throws ServiceException;

}
