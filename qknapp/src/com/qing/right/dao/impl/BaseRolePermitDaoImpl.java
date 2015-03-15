package com.qing.right.dao.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.qing.common.base.dao.HibernateDao;
import com.qing.common.exception.DaoException;
import com.qing.right.dao.IBaseRolePermitDao;
import com.qing.right.dao.domain.BasePermit;
import com.qing.right.dao.domain.BaseRole;
import com.qing.right.dao.domain.BaseRolePermit;

@Service
@Repository
public class BaseRolePermitDaoImpl implements IBaseRolePermitDao {

    @Resource
    private HibernateDao<BaseRolePermit, String> hibernateDao;
    @Resource
    private HibernateDao<BasePermit, String> basePermitDao;
    @Resource
    private HibernateDao<BaseRole, String> baseRoleDao;
    @Resource
    private HibernateDao<String, String> hibernateDaoStr;

    @SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
    public List<String> getPermitIdByRoleId(String roleId) throws DaoException {
	StringBuilder hql = new StringBuilder("select o.basePermit.permitId from BaseRolePermit as o where o.baseRole.roleId=?");
	List list = hibernateDaoStr.findByValues(hql.toString(), new Object[] { roleId }, false);
	return list;
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
    public List<String> getPermitNameByRoleId(String roleId) throws DaoException {
	StringBuilder hql = new StringBuilder("select o.basePermit.permitName from BaseRolePermit as o where o.baseRole.roleId=?");
	List list = hibernateDaoStr.findByValues(hql.toString(), new Object[] { roleId }, false);
	return list;
    }

    @Override
    public List<BaseRolePermit> getRolePermitByRoleId(String roleId) throws DaoException {
	List<BaseRolePermit> list = null;
	try {
	    StringBuilder hql = new StringBuilder("from BaseRolePermit as o where o.baseRole.roleId=?");
	    list = hibernateDao.findByValues(hql.toString(), new Object[] { roleId }, false);
	    return list;
	} catch (Exception e) {
	    e.printStackTrace();
	    throw new DaoException("依据角色ID,取得角色权限列表失败！");
	}
    }

    @Override
    public List<BaseRolePermit> getRolePermitByRoleIds(String roleId) throws DaoException {
	List<BaseRolePermit> list = null;
	try {
	    StringBuilder hql = new StringBuilder("from BaseRolePermit as o where o.baseRole.roleId in ?");
	    list = hibernateDao.findByValues(hql.toString(), new Object[] { roleId }, false);
	    return list;
	} catch (Exception e) {
	    e.printStackTrace();
	    throw new DaoException("依据角色ID,取得角色权限列表失败！");
	}
    }

    @Override
    public void deleteByRoleId(String roleId) throws DaoException {
	List<BaseRolePermit> list = getRolePermitByRoleId(roleId);
	hibernateDao.removeAll(list);
    }

    @Override
    public void saveByRoleId(String roleId, List<String> permitIdList) throws DaoException {
	// 依据角色ID,加载角色信息
	BaseRole baseRole = baseRoleDao.findById(BaseRole.class, roleId);
	if (baseRole == null) {
	    throw new DaoException("依据编号" + roleId + "获取角色信息失败！");
	}

	// 依据角色ID,删除角色权限
	deleteByRoleId(roleId);

	// 重新添加角色权限
	// 取得需保存的角色权限列表
	// 如果基础权限列表不为空，则保存角色权限列表
	if (null != permitIdList && permitIdList.size() > 0) {
	    List<BaseRolePermit> bupList = getSaveBupList(permitIdList, baseRole);
	    // 批量保存
	    hibernateDao.saveOrUpdateAll(bupList);
	}
    }

    /**
     * 取得需保存的角色权限列表
     * 
     * @param permitIdList
     *            权限列表
     * @param em
     * @param baseRole
     *            角色
     * @param map
     * @throws DaoException
     */
    private List<BaseRolePermit> getSaveBupList(List<String> permitIdList, BaseRole baseRole) throws DaoException {
	List<BaseRolePermit> bupList = new ArrayList<BaseRolePermit>();
	// 无历史数据
	for (String id2 : permitIdList) {
	    addBupToList(baseRole, bupList, id2);
	}
	return bupList;
    }

    /**
     * 添加角色权限对象到列表中
     * 
     * @param em
     * @param baseRole
     *            角色
     * @param bupList
     *            角色权限列表
     * @param permitId
     *            权限ID
     * @throws DaoException
     */
    private void addBupToList(BaseRole baseRole, List<BaseRolePermit> bupList, String permitId) throws DaoException {
	if (StringUtils.isEmpty(permitId)) {
	    return;
	}
	BasePermit bp = basePermitDao.findById(BasePermit.class, permitId);
	if (bp == null) {
	    throw new DaoException("依据编号" + permitId + "获取权限信息失败！");
	}
	BaseRolePermit bup = new BaseRolePermit();
	bup.setBaseRole(baseRole);
	bup.setBasePermit(bp);
	bup.setCreateTime(new Timestamp(new Date().getTime()));
	bupList.add(bup);
    }

    @Override
    public void deleteByRoleIdAndUserId(String roleId, String permitId) throws DaoException {
	String hql = "delete BaseRolePermit o where o.baseRole.roleId=? and o.basePermit.permitId=?";
	try {
	    hibernateDao.remove(hql, new Object[] { roleId, permitId });
	} catch (DaoException e) {
	    throw new DaoException("依据角色ID和用户ID,删除角色权限失败！");
	}
    }
}