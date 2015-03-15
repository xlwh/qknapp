package com.qing.right.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.qing.common.base.dao.HibernateDao;
import com.qing.common.exception.DaoException;
import com.qing.right.dao.IBasePermitDao;
import com.qing.right.dao.domain.BaseRolePermit;

@Service
@Repository
public class BasePermitDaoImpl implements IBasePermitDao {

    @Resource
    private HibernateDao<Integer, String> hibernateDaoInt;

    @Resource
    private HibernateDao<BaseRolePermit, String> hibernateDaoBasePermit;

    @Override
    public List<Integer> queryPermitByLoginCode(String loginCode) throws DaoException {
	List<Integer> permitList = null;

	try {
	    StringBuilder hql = new StringBuilder(
		    " select permitContent from BasePermit where permitId in ( select basePermit.permitId from BaseRolePermit where baseRole.roleId = ( select baseRole.roleId from BaseRoleUser WHERE baseUser.userId = ( select userId from BaseUser where loginCode = ?1 ) ) ) order by permitContent ");
	    permitList = hibernateDaoInt.findByValues(hql.toString(), new Object[] { loginCode }, false);
	} catch (Exception e) {
	    e.printStackTrace();
	    throw new DaoException("根据登陆号查询相关权限失败！");
	}
	return permitList;
    }

    @Override
    public List<BaseRolePermit> queryPermitByRoleId(String roleId) throws DaoException {
	List<BaseRolePermit> permitList = null;

	try {
	    StringBuilder hql = new StringBuilder("from BaseRolePermit brp where brp.baseRole.roleId in ");
	    permitList = hibernateDaoBasePermit.findByValues(hql.toString(), new Object[] { roleId }, false);
	} catch (Exception e) {
	    e.printStackTrace();
	    throw new DaoException("根据登陆号查询相关权限失败！");
	}
	return permitList;
    }
}