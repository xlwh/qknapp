package com.qing.right.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.annotation.Resource;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.qing.common.base.service.impl.BaseServiceImpl;
import com.qing.common.exception.DaoException;
import com.qing.common.exception.ServiceException;
import com.qing.common.exception.handle.ExceptionHandle;
import com.qing.common.util.ErrorCode;
import com.qing.common.util.Page;
import com.qing.constant.Constants;
import com.qing.right.dao.domain.BaseOrgan;
import com.qing.right.dao.domain.BaseUser;
import com.qing.right.dao.impl.BaseUserDaoImpl;
import com.qing.right.service.IBaseUserService;
import com.qing.vo.OrganVO;
import com.qing.vo.UserVO;

@Service
@Transactional
public class BaseUserServiceImpl implements IBaseUserService {

    @Resource
    private BaseUserDaoImpl                   baseUserDaoImpl;
    @Resource
    private BaseServiceImpl<BaseUser, String> baseService;

    @Resource
    public OrganJdbcServiceImpl               organJdbcService;
    @Resource
    public BaseOrganServiceImpl               baseOrganServiceImpl;

    @SuppressWarnings("rawtypes")
	@Override
    @Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
    public List findUsers(Object[] params, String organId) throws ServiceException {
	List list = null;
	try {
	    List<OrganVO> organVOs = baseOrganServiceImpl.getOrganTree(organId);
	    list = baseUserDaoImpl.findUsers(params, organVOs);
	} catch (DaoException e) {
	    ExceptionHandle.throwServiceException(e, ErrorCode.SERVICE_QUERY_ERROR_004, "BaseUserServiceImpl.findUsers()");
	}
	return list;
    }

    @Override
    @Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
    public BaseUser findUserByLoginCode(String loginCode) throws ServiceException {
	BaseUser user = null;
	try {
	    user = baseUserDaoImpl.findUserByLoginCode(loginCode);
	} catch (DaoException e) {
	    ExceptionHandle.throwServiceException(e, ErrorCode.SERVICE_QUERY_ERROR_004, "BaseUserServiceImpl.findUserByLoginCode()");
	}
	return user;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void resetPassword(String userId, String password) throws ServiceException {
	try {
	    baseUserDaoImpl.resetPassword(userId, password);
	} catch (DaoException e) {
	    ExceptionHandle.throwServiceException(e, ErrorCode.SERVICE_QUERY_ERROR_004, "BaseUserServiceImpl.resetPassword()");
	}
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
    @Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
    public Page queryBaseUser(Page page, int stype, Object[] params, Object[] param) throws ServiceException {
	try {
	    baseUserDaoImpl.queryBaseUser(page, stype, params, param);
	} catch (DaoException e) {
	    ExceptionHandle.throwServiceException(e, ErrorCode.SERVICE_QUERY_ERROR_004, "BaseUserServiceImpl.resetPassword()");
	}
	return page;
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
    @Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
    public Page queryUserByOrganId(Page page, String organId) throws ServiceException {
	try {
	    // 不分页
	    page.setPageNo(-1);
	    page.setPageSize(-1);
	    baseUserDaoImpl.queryUserByOrganId(page, organId);
	} catch (DaoException e) {
	    ExceptionHandle.throwServiceException(e, ErrorCode.SERVICE_QUERY_ERROR_004, "BaseUserServiceImpl.queryChild()");
	}
	return page;
    }

    @Override
    @Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
    public Boolean checkLoginCode(String loginCode) throws ServiceException {
	Boolean bool = false;
	try {
	    bool = baseUserDaoImpl.checkLoginCode(loginCode);
	} catch (DaoException e) {
	    ExceptionHandle.throwServiceException(e, ErrorCode.SERVICE_QUERY_ERROR_004, "BaseUserServiceImpl.checkLoginCode()");
	}
	return bool;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void updateUserByLastLogin(String loginCode, String ipAddress) throws ServiceException {
	try {
	    baseUserDaoImpl.updateUserByLastLogin(loginCode, ipAddress);
	} catch (DaoException e) {
	    ExceptionHandle.throwServiceException(e, ErrorCode.SERVICE_QUERY_ERROR_004, "BaseUserServiceImpl.updateUserByLastLogin()");
	}
    }

    @Override
    @Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
    public String loginCheck(String loginCode, String password) throws ServiceException {
	String str = null;
	BaseUser baseUser = null;
	try {
	    baseUser = baseUserDaoImpl.loginCheck(loginCode, password);
	    boolean boo = baseUserDaoImpl.checkLoginCode(loginCode);

	    if (!boo) {
		// 此用户不存在
		str = "1";
	    } else if (baseUser == null) {
		// 密码错误
		str = "2";
	    } else if (baseUser != null && baseUser.getPasswordTerm() != null
		    && baseUser.getPasswordTerm().getTime() < new Date().getTime()) {
		// 用户已过期
		str = "3";
	    } else {
		// 成功
		str = "0";
	    }

	} catch (DaoException e) {
	    ExceptionHandle.throwServiceException(e, ErrorCode.SERVICE_QUERY_ERROR_004, "BaseUserServiceImpl.updateUserByLastLogin()");
	}
	return str;
    }

    /**
     * 保存或者修改用户
     * 
     * @param baseUser
     * @throws ServiceException
     */
    @Override
    public void saveOrUpdateBaseUser(BaseUser baseUser) throws ServiceException {
	try {
	    // 将空字符设置为null，否则""也会当成ID进行更新
	    if ("".equals(baseUser.getUserId())) {
		baseUser.setUserId(null);
	    }
	    baseService.saveOrUpdate(baseUser);
	} catch (ServiceException e) {
	    ExceptionHandle.throwServiceException(e, ErrorCode.DAO_SAVE_ERROR_001, "BaseUserServiceImpl.saveOrUpdateBaseUser()");
	}
    }

    /**
     * 删除用户
     * 
     * @param baseUser
     * @throws ServiceException
     */
    @Override
    public Boolean delBaseUser(String userId) throws ServiceException {
	Boolean bool = false;
	try {
	    bool = baseUserDaoImpl.delBaseUser(userId);
	} catch (DaoException e) {
	    ExceptionHandle.throwServiceException(e, ErrorCode.DAO_SAVE_ERROR_001, "BaseUserServiceImpl.delBaseUser()");
	}
	return bool;
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    @Override
    @Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
    public List<UserVO> getUnrelevUserByOrganId(Page page, String organId) throws ServiceException {
	List<UserVO> list = new ArrayList<UserVO>();
	try {
	    baseUserDaoImpl.queryUserByOrganId(page, organId);
	    List<BaseUser> userBaseUsers = page.getResult();
	    if (null != userBaseUsers) {
		for (BaseUser baseUser : userBaseUsers) {

		    // 如果存在角色用户，则说明该用户已经绑定过角色
		    if (null != baseUser.getBaseRoleUsers() && baseUser.getBaseRoleUsers().size() > 0) {
			continue;
		    }
		    UserVO userVO = new UserVO();
		    BeanUtils.copyProperties(baseUser, userVO);
		    list.add(userVO);
		}

	    }
	} catch (DaoException e) {
	    ExceptionHandle.throwServiceException(e, ErrorCode.SERVICE_QUERY_ERROR_004, "BaseUserServiceImpl.queryChild()");
	}
	return list;
    }

    /**
     * 根据登陆编号权限获取手持机用户权限
     * 
     * @param loginCode
     * @return
     */
    // @Override
    // @SuppressWarnings("rawtypes")
    // public LoginResponse getHandMobilePermitByLoginCode(String loginCode,
    // String password, String machineCode) throws ServiceException {
    // LoginResponse response = new LoginResponse();
    // HessianResult hr = new HessianResult();
    // response.setHessianResult(hr);
    // response.setUpList(new ArrayList<UserPermit>());
    // response.setUserList(new ArrayList<User>());
    // try {
    // // 检查用户是否存在
    // boolean isExist = baseUserDaoImpl.checkLoginCode(loginCode);
    // // 如果用户不存在, 直接返回为空
    // if (!isExist) {
    // hr.setValue(RemoteCode.FAILURE);
    // hr.setErrCode(RemoteErrorCode.USER_NULL);
    // hr.setErrMsg(ConfigureUtils.getErrorConfig(RemoteErrorCode.USER_NULL));
    // return response;
    // }
    //
    // // 如果权限为空, 直接返回为空
    // List list = baseUserDaoImpl.getHandMobilePermitByLoginCode(loginCode);
    //
    // if (null == list || list.size() < 1) {
    // hr.setValue(RemoteCode.FAILURE);
    // hr.setErrCode(RemoteErrorCode.PERMIT_NULL);
    // hr.setErrMsg(ConfigureUtils.getErrorConfig(RemoteErrorCode.PERMIT_NULL));
    // return response;
    // }
    // int permitLen = list.size();
    // UserPermit up = null;
    // // 遍历权限列表
    // for (int i = 0; i < permitLen; i++) {
    // up = new UserPermit();
    // Map map = (Map) list.get(i);
    // // 判定密码是否正确, 不正确直接返回
    // if (i == 0 && null != map.get("PASSWORD")) {
    // if (!map.get("PASSWORD").toString().equals(password)) {
    // hr.setValue(RemoteCode.FAILURE);
    // hr.setErrCode(RemoteErrorCode.PWD_ERR);
    // hr.setErrMsg(ConfigureUtils.getErrorConfig(RemoteErrorCode.PWD_ERR));
    // return response;
    // }
    // }
    // // 权限名称是否正常
    // if (null != map.get("PERMIT_NAME")) {
    // up.setPermitCode(Integer.parseInt(map.get("PERMIT_NAME").toString()));
    // }
    // if (null != map.get("PERMIT_DESC")) {
    // up.setPermitDesc(map.get("PERMIT_DESC").toString());
    // }
    // response.getUpList().add(up);
    // }
    // } catch (DaoException e) {
    // ExceptionHandle.throwServiceException(e, ErrorCode.DAO_QUERY_ERROR_004,
    // "BaseUserServiceImpl.getHandMobilePermitByLoginCode()");
    // }
    //
    // hr.setValue(RemoteCode.SUCCESS);
    // return response;
    // }

    @Override
    public BaseUser getBaseUser(String userId) throws ServiceException {
	BaseUser bu = null;
	try {
	    bu = baseUserDaoImpl.getBaseUser(userId);
	} catch (DaoException e) {
	    ExceptionHandle.throwServiceException(e, ErrorCode.DAO_QUERY_ERROR_004, "BaseUserServiceImpl.getBaseUser()");
	}
	return bu;
    }

    @Override
    public BaseOrgan getLevBaseOrgByUserId(String userId, int levSize) throws ServiceException {
	BaseUser bu = null;
	BaseOrgan baseOrgan = null;
	try {
	    bu = getBaseUser(userId);
	    if (null != bu) {
		String orgLev = bu.getBaseOrgan().getOrganLev();
		if (null != orgLev) {
		    if (orgLev.length() > levSize) {
			// 所属组织即为非顶级组织
			baseOrgan = baseOrganServiceImpl.getBaseOrganByLev(orgLev.substring(0, levSize));
		    } else {
			// 所属组织即为顶级组织
			baseOrgan = bu.getBaseOrgan();
		    }
		}
	    }
	} catch (Exception e) {
	    ExceptionHandle.throwServiceException(e, ErrorCode.DAO_QUERY_ERROR_004, "BaseUserServiceImpl.getTopBaseOrgByUserId()");
	}
	return baseOrgan;
    }

    @Override
    public BaseOrgan getLevBaseOrgByUserInPhone(BaseUser baseUser) throws ServiceException {
	BaseOrgan baseOrgan = null;
	try {
	    if (null != baseUser) {
		String orgLev = baseUser.getBaseOrgan().getOrganLev();
		if (null != orgLev) {
		    if (orgLev.length() > Constants.BRAND_MARK) {
			// 所属组织即为非顶级组织
			baseOrgan = baseOrganServiceImpl.getBaseOrganByLev(orgLev.substring(0, Constants.BRAND_MARK));
		    } else {
			// 所属组织即为顶级组织
			baseOrgan = baseUser.getBaseOrgan();
		    }
		}
	    }
	} catch (Exception e) {
	    ExceptionHandle.throwServiceException(e, ErrorCode.DAO_QUERY_ERROR_004, "BaseUserServiceImpl.getTopBaseOrgByUserId()");
	    return null;
	}
	return baseOrgan;
    }

    @Override
    public BaseOrgan getLevBaseOrgByLoginCode(String loginCode, int levSize) throws ServiceException {
	BaseUser bu = null;
	BaseOrgan baseOrgan = null;
	try {
	    bu = findUserByLoginCode(loginCode);
	    if (null != bu) {
		String orgLev = bu.getBaseOrgan().getOrganLev();
		if (null != orgLev) {
		    if (orgLev.length() > levSize) {
			// 所属组织即为非顶级组织
			baseOrgan = baseOrganServiceImpl.getBaseOrganByLev(orgLev.substring(0, levSize));
		    } else {
			// 所属组织即为顶级组织
			baseOrgan = bu.getBaseOrgan();
		    }
		}
	    }
	} catch (Exception e) {
	    ExceptionHandle.throwServiceException(e, ErrorCode.DAO_QUERY_ERROR_004, "BaseUserServiceImpl.getTopBaseOrgByUserId()");
	}
	return baseOrgan;
    }

    @Override
    public BaseOrgan getLevBaseOrgByOrganId(String organId, int levSize) throws ServiceException {
	BaseOrgan baseOrgan = null;
	try {
	    baseOrgan = baseOrganServiceImpl.getBaseOrgan(organId);
	    if (null != baseOrgan) {
		String orgLev = baseOrgan.getOrganLev();
		if (null != orgLev) {
		    // orgLev.length() > levSize所属组织即为组织或品牌之下的部门；
		    // orgLev.length() =<levSize所属组织即为组织或品牌直接返回当前组织
		    if (orgLev.length() > levSize) {
			baseOrgan = baseOrganServiceImpl.getBaseOrganByLev(orgLev.substring(0, levSize));
		    }
		}
	    }
	} catch (Exception e) {
	    ExceptionHandle.throwServiceException(e, ErrorCode.DAO_QUERY_ERROR_004, "BaseUserServiceImpl.getTopBaseOrgByUserId()");
	}
	return baseOrgan;
    }

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
    @Override
    public List<BaseUser> getAllUserByOrganId(String organId) throws ServiceException {

	List<BaseUser> listBaseUsers = null;
	try {
	    List<OrganVO> list = organJdbcService.getOrganTree(organId);
	    listBaseUsers = baseUserDaoImpl.getAllUserByOrganId(list);
	} catch (DaoException e) {
	    ExceptionHandle.throwServiceException(e, ErrorCode.DAO_SAVE_ERROR_001, "BaseUserServiceImpl.getAllUserByOrganId()");
	}
	return listBaseUsers;
    }

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
    @Override
    public Map<String, List<UserVO>> getAllUserVOByOrganId(String organId) throws ServiceException {

	List<Map<String, Object>> listBaseUsers = null;
	Map<String, List<UserVO>> result = new TreeMap<String, List<UserVO>>();
	try {
	    List<OrganVO> list = baseOrganServiceImpl.getOrganTree(organId);

	    for (OrganVO organVO : list) {
		listBaseUsers = baseUserDaoImpl.getAllUserVOByOrganId(organVO.getOrganId());
		List<UserVO> listVos = new ArrayList<UserVO>();
		for (Iterator<Map<String, Object>> itr = listBaseUsers.iterator(); itr.hasNext();) {
		    Map<String, Object> map = itr.next();
		    UserVO uv = new UserVO();
		    if (null != map.get("USER_TYPE") && !map.get("USER_TYPE").toString().equals("3")) {
			continue;
		    }

		    if (null != map.get("ORGAN_ID")) {
			uv.setOrganName(map.get("ORGAN_ID").toString());
		    }
		    if (null != map.get("USER_ID")) {
			uv.setUserId(map.get("USER_ID").toString());
		    }
		    if (null != map.get("USER_NAME")) {
			uv.setUserName(map.get("USER_NAME").toString());
		    }

		    listVos.add(uv);

		}
		if (listVos.size() > 0) {
		    result.put(organVO.getOrganName(), listVos);
		}

	    }

	} catch (DaoException e) {
	    ExceptionHandle.throwServiceException(e, ErrorCode.DAO_SAVE_ERROR_001, "BaseUserServiceImpl.getAllUserByOrganId()");
	}
	return result;
    }

    /**
     * 根据一组ID或单个ID进行删除
     * 
     * @author wangxiang
     * @since 深烟智能物流管理平台, 2013-1-15
     * @param ids
     * @return
     */
    @Override
    public List<String> deleteBaseUser(String[] ids) throws ServiceException {
	List<String> list = new ArrayList<String>();
	if (null != ids && ids.length > 0) {

	    for (int i = 0; i < ids.length; i++) {
		try {
		    BaseUser busers = baseUserDaoImpl.getBaseUser(ids[i]);
		    baseService.remove(busers);

		} catch (DaoException e) {
		    ExceptionHandle.throwServiceException(e, ErrorCode.DAO_QUERY_ERROR_004, "BaseUserServiceImpl.deleteBaseUser()");
		}
	    }

	}

	return list;
    }

    /**
     * 根据机器码查询送货组
     * 
     * @param mcnCode
     * @return
     * @throws DaoException
     */
    @Override
    @SuppressWarnings("rawtypes")
    public String getRouteCodeByMcnCode(String mcnCode) throws ServiceException {
	Map map = null;
	try {
	    map = baseUserDaoImpl.getRouteCodeByMcnCode(mcnCode);
	} catch (DaoException e) {
	    ExceptionHandle.throwServiceException(e, ErrorCode.DAO_SAVE_ERROR_001, "BaseUserServiceImpl.getRouteCodeByMcnCode()");
	}
	if (null != map && null != map.get("ROUTE_CODE")) {
	    return map.get("ROUTE_CODE").toString();
	} else {
	    return null;
	}
    }

    @Override
    public List<BaseUser> getUsersByCurOrganId(String organId) throws ServiceException {
	List<BaseUser> baseUsers = null;
	try {
	    baseUsers = baseUserDaoImpl.getUsersByCurOrganId(organId);
	} catch (DaoException e) {
	    ExceptionHandle.throwServiceException(e, ErrorCode.SERVICE_QUERY_ERROR_004, "BaseUserServiceImpl.getUsersByCurOrganId()"
		    + "根据当前组织ID获取当前组织下所有人失败");
	}
	return baseUsers;
    }

    @Override
    public BaseUser loginCheckInPhone(String loginCode, String password) throws ServiceException {
	BaseUser baseUser = null;

	try {
	    baseUser = baseUserDaoImpl.loginCheckInPhone(loginCode, password);
	} catch (DaoException e) {
	    ExceptionHandle.throwServiceException(e, ErrorCode.SERVICE_QUERY_ERROR_004, "BaseUserServiceImpl.loginCheckInPhone()"
		    + "根据用户名跟密码登录手机端失败");
	} catch (Exception e) {
	    ExceptionHandle.throwServiceException(e, ErrorCode.SERVICE_QUERY_ERROR_004, "BaseUserServiceImpl.loginCheckInPhone()"
		    + "根据用户名跟密码登录手机端失败");
	}
	return baseUser;

    }
}
