package com.qing.right.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.qing.common.base.dao.HibernateDao;
import com.qing.common.exception.DaoException;
import com.qing.common.util.Page;
import com.qing.right.dao.IBaseRoleUserDao;
import com.qing.right.dao.domain.BaseRole;
import com.qing.right.dao.domain.BaseRoleUser;
import com.qing.right.dao.domain.BaseUser;

@Service
@Repository
public class BaseRoleUserDaoImpl implements IBaseRoleUserDao {

    @Resource
    private HibernateDao<BaseRoleUser, String> hibernateDao;
    @Resource
    private HibernateDao<String, String> hibernateDaoStr;
    @Resource
    private HibernateDao<BaseRole, String> baseRoleDao;
    @Resource
    private HibernateDao<BaseUser, String> baseUserDao;

    @Override
    public List<String> getRoleIdByUserId(String userId) throws DaoException {
	List<String> list = null;
	try {
	    StringBuilder hql = new StringBuilder("select o.baseRole.roleId from BaseRoleUser as o where o.baseUser.userId=?");
	    list = hibernateDaoStr.findByValues(hql.toString(), new Object[] { userId }, false);
	    return list;
	} catch (Exception e) {
	    e.printStackTrace();
	    throw new DaoException("依据用户ID,取得角色ID列表失败！");
	}
    }
    
    @Override
    public List<String> getRoleNameByUserId(String userId) throws DaoException {
	List<String> list = null;
	try {
	    StringBuilder hql = new StringBuilder("select o.baseRole.roleName from BaseRoleUser as o where o.baseUser.userId=?");
	    list = hibernateDaoStr.findByValues(hql.toString(), new Object[] { userId }, false);
	    return list;
	} catch (Exception e) {
	    e.printStackTrace();
	    throw new DaoException("依据用户ID,取得角色ID列表失败！");
	}
    }

    @Override
    public void saveByRoleId(String roleId, List<String> userIdList) throws DaoException {
	try {

	    if (null != userIdList && userIdList.size() > 0) {
		// 依据角色ID,加载角色信息
		BaseRole baseRole = baseRoleDao.findById(BaseRole.class, roleId);
		// // 已建立关系的用户ID
		// List<String> list2 = getUserIdByRoleId(roleId);
		// userIdList.removeAll(list2);

		deleteByRoleId(roleId);

		List<BaseRoleUser> baseRoleUsers = new ArrayList<BaseRoleUser>();
		for (String id : userIdList) {
		    // 依据用户ID,加载用户信息
		    BaseUser baseUser = baseUserDao.findById(BaseUser.class, id);
		    BaseRoleUser baseRoleUser = new BaseRoleUser();
		    baseRoleUser.setBaseRole(baseRole);
		    baseRoleUser.setBaseUser(baseUser);
		    baseRoleUsers.add(baseRoleUser);
		}
		hibernateDao.saveOrUpdateAll(baseRoleUsers);
	    }

	} catch (Exception e) {
	    e.printStackTrace();
	    throw new DaoException("依据角色ID,保存角色用户关系失败！");
	}

    }

    @Override
    public List<BaseRoleUser> getRoleUserByRoleId(String roleId) throws DaoException {
	List<BaseRoleUser> list = null;
	try {
	    StringBuilder hql = new StringBuilder("select o from BaseRoleUser as o where o.baseRole.roleId=?");
	    list = hibernateDao.findByValues(hql.toString(), new Object[] { roleId }, false);
	    return list;
	} catch (Exception e) {
	    e.printStackTrace();
	    throw new DaoException("依据用户ID,取得角色ID列表失败！");
	}
    }
    
    @Override
    public List<BaseRoleUser> getRoleUserByUserId(String userId) throws DaoException {
	List<BaseRoleUser> list = null;
	try {
	    StringBuilder hql = new StringBuilder("select o from BaseRoleUser as o where o.baseUser.userId=?");
	    list = hibernateDao.findByValues(hql.toString(), new Object[] { userId }, false);
	    return list;
	} catch (Exception e) {
	    e.printStackTrace();
	    throw new DaoException("依据用户ID,取得角色ID列表失败！");
	}
    }

    @Override
    public List<String> getUserIdByRoleId(String roleId) throws DaoException {
	List<String> list = null;
	try {
	    StringBuilder hql = new StringBuilder("select o.baseUser.userId from BaseRoleUser as o where o.baseRole.roleId=?");
	    list = hibernateDaoStr.findByValues(hql.toString(), new Object[] { roleId }, false);
	    return list;
	} catch (Exception e) {
	    e.printStackTrace();
	    throw new DaoException("依据角色ID,取得用户ID列表失败！");
	}
    }



    @Override
    public List<BaseUser> getBaseUserByRoleId(String roleId, Object[] params) throws DaoException {
	List<BaseUser> list = null;
	try {
	    // 查询参数
	    StringBuffer sb = new StringBuffer();
	    // sb.append("select new BaseUser(o.baseUser.userId,o.baseUser.loginCode,o.baseUser.userName,o.baseUser.password,o.baseUser.employeeId)");
	    sb.append("select o.baseUser");
	    sb.append(" from BaseRoleUser as o where o.baseRole.roleId='");
	    sb.append(roleId);
	    sb.append("'");

	    if (params != null && params.length > 0 && params[0] != null) {
		sb.append(" and o.baseUser.userId='");
		sb.append(params[0]);
		sb.append("'");
	    }
	    if (params != null && params.length > 1 && params[1] != null) {
		sb.append(" and o.baseUser.loginCode like '");
		sb.append(params[1]);
		sb.append("'");
	    }
	    if (params != null && params.length > 2 && params[2] != null) {
		sb.append(" and o.baseUser.userName like '");
		sb.append(params[2]);
		sb.append("'");
	    }
	    if (params != null && params.length > 3 && params[3] != null) {
		sb.append(" and o.baseUser.employeeId like '");
		sb.append(params[3]);
		sb.append("'");
	    }
	    if (params != null && params.length > 4 && params[4] != null) {
		sb.append(" and o.baseUser.baseOrgan.organName like '");
		sb.append(params[4]);
		sb.append("'");
	    }
	    list = baseUserDao.findByValues(sb.toString(), null, false);
	} catch (Exception e) {
	    e.printStackTrace();
	    throw new DaoException("依据角色ID,取得用户信息列表失败！");
	}
	return list;
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
    public Page queryBaseUserByRoleId(Page page, String roleId, Object[] params) throws DaoException {
	int count = 0;
	try {
	    // 查询参数
	    StringBuffer sb = new StringBuffer();
	    StringBuffer sb2 = new StringBuffer();
	    StringBuffer sb3 = new StringBuffer();
	    sb.append("select o.baseUser,o.roleUserId");
	    sb2.append("select count(o.baseUser)");
	    sb3.append(" from BaseRoleUser as o where o.baseRole.roleId='");
	    sb3.append(roleId);
	    sb3.append("'");

	    if (params != null && params.length > 0 && params[0] != null) {
		sb3.append(" and o.baseUser.userId='");
		sb3.append(params[0]);
		sb3.append("'");
	    }
	    if (params != null && params.length > 1 && params[1] != null) {
		sb3.append(" and o.baseUser.loginCode like '");
		sb3.append(params[1]);
		sb3.append("'");
	    }
	    if (params != null && params.length > 2 && params[2] != null) {
		sb3.append(" and o.baseUser.userName like '");
		sb3.append(params[2]);
		sb3.append("'");
	    }
	    if (params != null && params.length > 3 && params[3] != null) {
		sb3.append(" and o.baseUser.employeeId like '");
		sb3.append(params[3]);
		sb3.append("'");
	    }
	    if (params != null && params.length > 4 && params[4] != null) {
		sb3.append(" and o.baseUser.baseOrgan.organName like '");
		sb3.append(params[4]);
		sb3.append("'");
	    }

	    sb.append(sb3);
	    sb2.append(sb3);
	    // 查询列表
	    hibernateDao.findByPage(page, sb.toString(), null);
	    // 查询总数
	    count = hibernateDao.findNumByHQL(sb2.toString(), null);
	    page.setTotalCount(count);

	} catch (Exception e) {
	    e.printStackTrace();
	    throw new DaoException("依据角色ID,取得用户信息列表失败！");
	}
	return page;
    }

    @Override
    public void deleteByRoleId(String roleId) throws DaoException {
	List<BaseRoleUser> list = getRoleUserByRoleId(roleId);
	hibernateDao.removeAll(list);
    }

    @Override
    public void deleteByRoleIdAndUserId(String roleId, String userId) throws DaoException {
	String hql = "delete BaseRoleUser o where o.baseRole.roleId=? and o.baseUser.userId=?";
	try {
	    hibernateDao.remove(hql, new Object[] { roleId, userId });
	} catch (DaoException e) {
	    throw new DaoException("依据角色ID和用户ID,删除角色用户失败！");
	}
    }
}