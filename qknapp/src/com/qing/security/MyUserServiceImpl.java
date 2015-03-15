package com.qing.security;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.qing.common.exception.DaoException;
import com.qing.constant.Constants;
import com.qing.right.dao.domain.BaseOrgan;
import com.qing.right.dao.domain.BaseUser;
import com.qing.right.dao.impl.BaseOrganUserPermitDaoImpl;
import com.qing.right.dao.impl.BaseRolePermitDaoImpl;
import com.qing.right.dao.impl.BaseRoleUserDaoImpl;
import com.qing.right.dao.impl.BaseUserDaoImpl;
import com.qing.right.dao.impl.BaseUserPermitDaoImpl;
import com.qing.right.service.impl.BaseUserServiceImpl;

@Service("myUserDetailServiceImpl")
@Transactional
public class MyUserServiceImpl implements UserDetailsService {
    private static Log log = LogFactory.getLog(MyUserServiceImpl.class);
    @Resource
    private BaseUserDaoImpl baseUserDaoImpl;
    @Resource
    private BaseUserPermitDaoImpl baseUserPermitDaoImpl;
    @Resource
    private BaseRoleUserDaoImpl baseRoleUserDaoImpl;
    @Resource
    private BaseRolePermitDaoImpl baseRolePermitDaoImpl;
    @Resource
    private BaseOrganUserPermitDaoImpl baseOrganUserPermitDaoImpl;
    @Resource
    private BaseUserServiceImpl baseUserServiceImpl;
    // 超级管理员账号
    public final static String ADMIN_LOGINCODE = "admin";

    // 超级管理员权限
    public final static String ADMIN_PERMIT_LIST = "ROLE_ADMIN";

    // 组织管理员权限
    public final static String ORGAN_USER_PERMIT = "ORGAN_USER_ADMIN";

    @Override
    @Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
    public UserDetails loadUserByUsername(String loginCode) throws UsernameNotFoundException {
	log.info("登录名[" + loginCode + "]");

	BaseUser user = null;
	try {
	    user = baseUserDaoImpl.findUserByLoginCode(loginCode);
	    if (null != user) {
		// 如果本身为品牌用户或集体用户,则品牌ID就是当前用户所在组织,
		BaseOrgan baseOrgan = baseUserServiceImpl.getLevBaseOrgByUserId(user.getUserId(), Constants.BRAND_MARK);
		if (null != baseOrgan) {
		    user.setBrandId(baseOrgan.getOrganId());
		}
	    }

	} catch (Exception e) {
	    log.error("登录时,查询用户[" + loginCode + "]信息失败!", e);
	}
	if (user == null) {
	    throw new UsernameNotFoundException("用户名 " + loginCode + " 不存在!");
	}
	Collection<GrantedAuthority> grantedAuths = obtionGrantedAuthorities(user);

	// 是否有效
	boolean enables = userIsEnable(user);

	// 锁定和超期这两个功能 暂未实现
	boolean credentialsNonExpired = true;
	boolean accountNonLocked = true;

	// 封装成spring security的user
	User userdetail = new User(user.getLoginCode(), user.getPassword(), enables, true, credentialsNonExpired, accountNonLocked,
		grantedAuths);
	return userdetail;
    }

    /**
     * 用户是否有效
     * 
     * @param user
     * @return
     */
    private boolean userIsEnable(BaseUser user) {
	return (user.getStatus() != null && user.getStatus().equals("Y")) ? true : false;
    }

    /**
     * 取得用户的权限
     * 
     * @param user
     *            用户对象
     * @return 权限
     */
    @Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
    private Set<GrantedAuthority> obtionGrantedAuthorities(BaseUser user) {

	// 最终权限列表集合
	Set<GrantedAuthority> authSet = new HashSet<GrantedAuthority>();

	// 如果是超级管理员登陆，直接赋予最高权限
	if (user.getLoginCode().equals(ADMIN_LOGINCODE)) {
	    authSet.add(new SimpleGrantedAuthority(ADMIN_PERMIT_LIST));
	    return authSet;
	}
	// 权限名称MAP
	HashMap<String, String> permitMap = new HashMap<String, String>();
	// 用户基本权限
	List<String> userPermitNameList = null;
	List<String> roleIdList = null;
	// 用户所属角色权限
	List<String> rolePermitNameList = null;

	// 如果是组织管理员，还有组织管理员权限
	List<String> organUserPermitList = null;

	try {
	    // 取得用户所拥有的基本权限
	    userPermitNameList = baseUserPermitDaoImpl.getPermitNameByUserId(user.getUserId());
	    addToMap(permitMap, userPermitNameList);

	    // 取得用户所属角色
	    roleIdList = baseRoleUserDaoImpl.getRoleIdByUserId(user.getUserId());
	    for (String roleId : roleIdList) {
		rolePermitNameList = baseRolePermitDaoImpl.getPermitNameByRoleId(roleId);
		addToMap(permitMap, rolePermitNameList);
	    }

	    organUserPermitList = baseOrganUserPermitDaoImpl.getPermitNameByUserId(user.getUserId());

	    // 如果是组织管理员，则给组织管理员加上ORGAN_USER_ADMIN 权限标示
	    if (null != organUserPermitList && organUserPermitList.size() > 0) {
		authSet.add(new SimpleGrantedAuthority(ORGAN_USER_PERMIT));
	    }
	    addToMap(permitMap, organUserPermitList);

	} catch (DaoException e) {
	    e.printStackTrace();
	    log.error(e.getMessage());
	}

	if (permitMap.size() > 0) {
	    for (String permitName : permitMap.keySet()) {
		authSet.add(new SimpleGrantedAuthority(permitName));
	    }
	}
	return authSet;
    }

    /**
     * @param permitMap
     * @param userPermitNameList
     */
    private void addToMap(HashMap<String, String> permitMap, List<String> userPermitNameList) {
	if (userPermitNameList != null && userPermitNameList.size() > 0) {
	    for (String permitName : userPermitNameList) {
		permitMap.put(permitName, permitName);
	    }
	}
    }
}