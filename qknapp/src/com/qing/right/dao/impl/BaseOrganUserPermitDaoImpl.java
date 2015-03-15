package com.qing.right.dao.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.qing.common.base.dao.HibernateDao;
import com.qing.common.exception.DaoException;
import com.qing.right.dao.IBaseOrganUserPermitDao;
import com.qing.right.dao.domain.BaseOrganUser;
import com.qing.right.dao.domain.BaseOrganUserPermit;
import com.qing.right.dao.domain.BasePermit;

@Service
@Repository
public class BaseOrganUserPermitDaoImpl implements IBaseOrganUserPermitDao {


    @Resource
    private HibernateDao<BaseOrganUserPermit, String> hibernateDao;

    @Resource
    private HibernateDao<BasePermit, String> basePermitDao;

    @Resource
    private HibernateDao<BaseOrganUser, String> baseOrganUserDao;

    @Resource
    private HibernateDao<String, String> hibernateDaoStr;

    @Override
    public List<String> getPermitNameByUserId(String userId) throws DaoException {

	List<String> list = null;
	try {
	    StringBuilder hql = new StringBuilder(
		    "select  o.basePermit.permitName from BaseOrganUserPermit as o where o.baseOrganUser.baseUser.userId = ? ");
	    list = hibernateDaoStr.findByValues(hql.toString(), new Object[] { userId }, false);
	} catch (Exception e) {
	    e.printStackTrace();
	    throw new DaoException("查询管理员的权限信息失败！");
	}
	return list;
    }

    @Override
    public List<String> getOrganUserPermitIdByOrganUserId(String organUserId) throws DaoException {
	List<String> list = null;
	try {
	    StringBuilder hql = new StringBuilder(
		    "select o.organUserPermitId from BaseOrganUserPermit as o where o.baseOrganUser.organUserId= ?");
	    list = hibernateDaoStr.findByValues(hql.toString(), new Object[] { organUserId }, false);
	} catch (Exception e) {
	    e.printStackTrace();
	    throw new DaoException("依据管理员ID,取得管理员权限ID列表失败！");
	}
	return list;
    }

    @Override
    public List<String> getPermitIdByOrganUserId(String organUserId) throws DaoException {
	List<String> list = null;
	try {
	    StringBuilder hql = new StringBuilder(
		    "select o.basePermit.permitId from BaseOrganUserPermit as o where o.baseOrganUser.organUserId= ?");
	    list = hibernateDaoStr.findByValues(hql.toString(), new Object[] { organUserId }, false);
	} catch (Exception e) {
	    e.printStackTrace();
	    throw new DaoException("依据管理员ID,取得管理员权限ID列表失败！");
	}
	return list;
    }

    /**
     * 获取权限列表 (一句话功能简述) (功能详细描述)
     * 
     * @author huangqingjian/0050
     * @see 相关函数,对于重要的函数建议注释
     * @since 深烟智能物流管理平台, 2013-1-18
     * @Company: Tai woo. Co., Ltd
     * @param userId
     * @return
     * @throws DaoException
     */
    @Override
    public List<BaseOrganUserPermit> getPermitByOrganUserId(String organUserId) throws DaoException {
	List<BaseOrganUserPermit> list = null;
	try {
	    StringBuilder hql = new StringBuilder("from BaseOrganUserPermit as o where o.baseOrganUser.organUserId= ?");
	    list = hibernateDao.findByValues(hql.toString(), new Object[] { organUserId }, false);
	} catch (Exception e) {
	    e.printStackTrace();
	    throw new DaoException("依据管理员ID,取得管理员权限ID列表失败！");
	}
	return list;
    }

    @Override
    public void saveByOrganUserId(String organUserId, List<String> permitIdList) throws DaoException {
	// 依据管理员ID,加载管理员信息
	BaseOrganUser baseOrganUser = baseOrganUserDao.findById(BaseOrganUser.class, organUserId);
	if (baseOrganUser == null) {
	    throw new DaoException("依据编号" + organUserId + "获取管理员信息失败！");
	}

	// 依据管理员ID,删除管理员权限
	deleteByOrganUserId(organUserId);

	
	if (null != permitIdList) {
	    // 重新添加管理员通用权限
	    // 取得需保存的管理员权限列表
	    List<BaseOrganUserPermit> bupList = getSaveBupList(permitIdList, baseOrganUser);

	    // 批量保存
	    hibernateDao.saveOrUpdateAll(bupList);
	}
    }

    @Override
    public void deleteByOrganUserId(String organUserId) throws DaoException {
	List<String> list = getOrganUserPermitIdByOrganUserId(organUserId);
	for (String str : list) {
	    hibernateDaoStr.update(" DELETE BaseOrganUserPermit WHERE organUserPermitId = ? ", new Object[] { str });
	}
    }

    /**
     * 取得需保存的管理员权限列表
     * 
     * @param permitIdList
     *            权限列表
     * @param em
     * @param baseOrganUser
     *            管理员
     * @param map
     * @throws DaoException
     */
    private List<BaseOrganUserPermit> getSaveBupList(List<String> permitIdList, BaseOrganUser baseOrganUser) throws DaoException {
	List<BaseOrganUserPermit> bupList = new ArrayList<BaseOrganUserPermit>();
	// 无历史数据
	for (String id2 : permitIdList) {
	    addBupToList(baseOrganUser, bupList, id2);
	}
	return bupList;
    }

    /**
     * 添加管理员权限对象到列表中
     * 
     * @param em
     * @param baseOrganUser
     *            管理员
     * @param bupList
     *            管理员权限列表
     * @param permitId
     *            权限ID
     * @throws DaoException
     */
    private void addBupToList(BaseOrganUser baseOrganUser, List<BaseOrganUserPermit> bupList, String permitId) throws DaoException {
	BasePermit bp = basePermitDao.findById(BasePermit.class, permitId);
	if (bp == null) {
	    throw new DaoException("依据编号" + permitId + "获取权限信息失败！");
	}
	BaseOrganUserPermit bup = new BaseOrganUserPermit();
	bup.setBaseOrganUser(baseOrganUser);
	bup.setBasePermit(bp);
	bup.setCreateTime(new Timestamp(new Date().getTime()));
	bupList.add(bup);
    }

}