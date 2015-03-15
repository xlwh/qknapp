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
import com.qing.right.dao.IBaseUserPermitDao;
import com.qing.right.dao.domain.BasePermit;
import com.qing.right.dao.domain.BaseUser;
import com.qing.right.dao.domain.BaseUserPermit;

@Service
@Repository
public class BaseUserPermitDaoImpl implements IBaseUserPermitDao {

    @Resource
    private HibernateDao<BaseUserPermit, String> hibernateDao;
    @Resource
    private HibernateDao<String, String> hibernateDaoStr;
    @Resource
    private HibernateDao<BasePermit, String> basePermitDao;
    @Resource
    private HibernateDao<BaseUser, String> baseUserDao;

    @Override
    public List<String> getPermitIdByUserId(String userId) throws DaoException {
	List<String> list = null;
	try {
	    StringBuilder hql = new StringBuilder("select o.basePermit.permitId from BaseUserPermit as o where o.baseUser.userId=?");
	    list = hibernateDaoStr.findByValues(hql.toString(), new Object[] { userId }, false);
	} catch (Exception e) {
	    e.printStackTrace();
	    throw new DaoException("依据用户ID,取得权限ID列表失败！");
	}
	return list;
    }

    @Override
    public List<BaseUserPermit> getUserPermitByUserId(String userId) throws DaoException {
	List<BaseUserPermit> list = null;
	try {
	    StringBuilder hql = new StringBuilder("select o.basePermit from BaseUserPermit as o where o.baseUser.userId=?");
	    list = hibernateDao.findByValues(hql.toString(), new Object[] { userId }, false);
	} catch (Exception e) {
	    e.printStackTrace();
	    throw new DaoException("依据用户ID,取得权限列表失败！");
	}
	return list;
    }

    @Override
    public List<BaseUserPermit> getBaseUserPermitByUserId(String userId) throws DaoException {
	List<BaseUserPermit> list = null;
	try {
	    StringBuilder hql = new StringBuilder("select o.basePermit from BaseUserPermit as o where o.baseUser.userId=?");
	    list = hibernateDao.findByValues(hql.toString(), new Object[] { userId }, false);
	} catch (Exception e) {
	    e.printStackTrace();
	    throw new DaoException("依据用户ID,取得权限列表失败！");
	}
	return list;
    }

    @Override
    public List<String> getPermitNameByUserId(String userId) throws DaoException {
	List<String> list = null;
	try {
	    StringBuilder hql = new StringBuilder("select o.basePermit.permitName from BaseUserPermit as o where o.baseUser.userId=?");
	    list = hibernateDaoStr.findByValues(hql.toString(), new Object[] { userId }, false);
	} catch (Exception e) {
	    e.printStackTrace();
	    throw new DaoException("依据用户ID,取得权限ID列表失败！");
	}
	return list;
    }

    @Override
    public List<String> getUserPermitIdByUserId(String userId) throws DaoException {
	List<String> list = null;
	try {
	    StringBuilder hql = new StringBuilder("select o.userPermitId from BaseUserPermit as o where o.baseUser.userId=?");
	    list = hibernateDaoStr.findByValues(hql.toString(), new Object[] { userId }, false);
	} catch (Exception e) {
	    e.printStackTrace();
	    throw new DaoException("依据用户ID,取得用户权限列表失败！");
	}
	return list;
    }

    @Override
    public void deleteByUserId(String userId) throws DaoException {
	List<String> list = getUserPermitIdByUserId(userId);
	for (String id : list) {
	    StringBuilder hql = new StringBuilder(" DELETE FROM BaseUserPermit WHERE  userPermitId = ?");
	    hibernateDao.update(hql.toString(), new Object[] { id });
	}
    }

    @Override
    public void saveByUserId(String userId, List<String> permitIdList) throws DaoException {
	// 依据用户ID,加载用户信息
	BaseUser baseUser = baseUserDao.findById(BaseUser.class, userId);
	if (baseUser == null) {
	    throw new DaoException("依据编号" + userId + "获取用户信息失败！");
	}

	// 依据用户ID,删除用户通用权限(permitOther='N')
	deleteByUserId(userId);

	if (null == permitIdList || permitIdList.size() < 1) {
	    return;
	}
	// 重新添加用户通用权限
	// 取得需保存的用户权限列表
	List<BaseUserPermit> bupList = getSaveBupList(permitIdList, baseUser);

	// 批量保存
	hibernateDao.saveOrUpdateAll(bupList);
    }

    /**
     * 取得需保存的用户权限列表
     * 
     * @param permitIdList
     *            权限列表
     * @param em
     * @param baseUser
     *            用户
     * @param map
     * @throws DaoException
     */
    private List<BaseUserPermit> getSaveBupList(List<String> permitIdList, BaseUser baseUser) throws DaoException {
	List<BaseUserPermit> bupList = new ArrayList<BaseUserPermit>();
	// 无历史数据
	for (String id2 : permitIdList) {
	    addBupToList(baseUser, bupList, id2);
	}
	return bupList;
    }

    /**
     * 添加用户权限对象到列表中
     * 
     * @param em
     * @param baseUser
     *            用户
     * @param bupList
     *            用户权限列表
     * @param permitId
     *            权限ID
     * @throws DaoException
     */
    private void addBupToList(BaseUser baseUser, List<BaseUserPermit> bupList, String permitId) throws DaoException {

	if (StringUtils.isEmpty(permitId)) {
	    return;
	}
	BasePermit bp = basePermitDao.findById(BasePermit.class, permitId);
	if (bp == null) {
	    throw new DaoException("依据编号" + permitId + "获取权限信息失败！");
	}
	BaseUserPermit bup = new BaseUserPermit();
	bup.setBaseUser(baseUser);
	bup.setBasePermit(bp);
	bup.setCreateTime(new Timestamp(new Date().getTime()));
	bupList.add(bup);
    }
}