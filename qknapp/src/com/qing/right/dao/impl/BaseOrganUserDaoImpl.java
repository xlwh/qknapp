package com.qing.right.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.qing.common.base.dao.HibernateDao;
import com.qing.common.exception.DaoException;
import com.qing.common.util.Page;
import com.qing.right.dao.IBaseOrganUserDao;
import com.qing.right.dao.domain.BaseOrganUser;
import com.qing.vo.OrganVO;

@Service
@Repository
public class BaseOrganUserDaoImpl implements IBaseOrganUserDao {

    @Resource
    private HibernateDao<BaseOrganUser, String> hibernateDao;

    @Resource
    private HibernateDao<String, String> hibernateDaoStr;

    @Override
    public List<String> getOrganIdByUserId(String userId) throws DaoException {
	List<String> list = null;
	try {
	    list = hibernateDaoStr.findByValues("select o.baseOrgan.organId from BaseOrganUser as o where o.baseUser.userId=? ",
		    new Object[] { userId }, false);
	} catch (Exception e) {
	    e.printStackTrace();
	    throw new DaoException("依据用户ID,取得所属组织ID列表失败！");
	}
	return list;
    }

    @Override
    public String getAdminIdOfOrgan(String userId, String organId) throws DaoException {
	List<String> list = null;
	try {
	    list = hibernateDaoStr.findByValues(
		    "select o.organUserId from BaseOrganUser as o where o.baseUser.userId=?1 and o.baseOrgan.organId=?2", new Object[] {
			    userId, organId }, false);
	    if (list != null && list.size() > 0) {
		return list.get(0);
	    }
	} catch (Exception e) {
	    e.printStackTrace();
	    throw new DaoException("依据用户ID,取得所属组织ID列表失败！");
	}
	return null;
    }

    @Override
    public Boolean checkAdmin(String organId, String userId) throws DaoException {
	int count = 0;
	try {
	    count = hibernateDao.findNumByHQL(
		    "select count(o.organUserId) from BaseOrganUser as o where o.baseOrgan.organId=?1 and o.baseUser.userId=?2",
		    new Object[] { organId, userId });

	    if (count > 0) {
		return true;
	    }
	} catch (Exception e) {
	    e.printStackTrace();
	    throw new DaoException("检查管理员是否重复失败！");
	}
	return false;
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
    public Page queryChild(Page page, String organId) throws DaoException {
	List list = null;
	List listCount = null;

	StringBuffer str1 = new StringBuffer();
	StringBuffer str2 = new StringBuffer();
	StringBuffer str3 = new StringBuffer();
	StringBuffer str4 = new StringBuffer();
	StringBuffer sb = new StringBuffer();
	str1.append(" select o from BaseOrganUser o where o.baseOrgan.organId in ");

	str2.append(" select a.organId from BaseOrgan as a where a.organId ='").append(organId)
		.append("' or a.organId in (select b.organId from BaseOrgan as b where b.organFather ='").append(organId)
		.append("') or a.organFather in (select c.organId from BaseOrgan as c where c.organFather ='").append(organId)
		.append("') ");

	str3.append(" order by o.organUserId desc ");

	int count = 0;

	sb.append("select count(o.organUserId) from BaseOrganUser o where o.baseOrgan.organId in ");

	while (true) {
	    str4.append(str2).append(" or a.organFather in (").append(str2).append(")");

	    list = hibernateDaoStr.findByValues(str2.toString(), null, false);

	    listCount = hibernateDaoStr.findByValues(str4.toString(), null, false);

	    str2 = str4;

	    // 比较集合的长度，如果相等的话就退出循环
	    if (listCount.size() == list.size()) {
		break;
	    }
	}

	// 查询列表
	StringBuffer sb2 = new StringBuffer();
	sb2.append(str1).append("(").append(str2).append(")").append(str3);

	hibernateDaoStr.findByPage(page, sb2.toString(), null);

	// 查询总数
	StringBuffer sb3 = new StringBuffer();
	sb3.append(sb).append("(").append(str2).append(")");
	count = hibernateDaoStr.findNumByHQL(sb3.toString(), null);
	page.setTotalCount(count);

	return page;
    }

    /**
     * 根据组织ID将当前组织和子组织的管理用户都查询出来
     * 
     * @param organId
     * @param organVOs
     * @return
     * @throws DaoException
     */
    @Override
    public List<BaseOrganUser> getOrganUserList(String organId, List<OrganVO> organVOs) throws DaoException {
	List<BaseOrganUser> list = null;
	try {
	    StringBuffer sb = new StringBuffer();
	    sb.append("from BaseOrganUser as o where 1 =1 and o.adminLevel <> 1");
	    if (organVOs != null && organVOs.size() > 0) {
		sb.append(" and o.baseOrgan.organId in(");
		for (OrganVO vo : organVOs) {
		    sb.append("'");
		    sb.append(vo.getOrganId());
		    sb.append("',");

		}
		sb.deleteCharAt(sb.length() - 1);
		sb.append(")");
	    }
	    list = hibernateDao.findByValues(sb.toString(), null, false);

	} catch (Exception e) {
	    e.printStackTrace();
	    throw new DaoException("查询管理用户信息失败！");
	}
	return list;
    }

    /**
     * 根据用户ID将管理用户都查询出来
     * 
     * @param organId
     * @return
     * @throws DaoException
     */
    @Override
    public List<BaseOrganUser> getOrganUserListByUserId(String userId) throws DaoException {
	List<BaseOrganUser> list = null;
	try {
	    list = hibernateDao.findByValues("from BaseOrganUser as o where o.baseUser.userId = ?", new Object[] { userId }, false);
	} catch (Exception e) {
	    e.printStackTrace();
	    throw new DaoException("查询管理用户信息失败！");
	}
	return list;
    }

    /**
     * 根据组织用户ID将对应的组织管理员信息查询出来
     * 
     * @param organUserId
     * @return
     * @throws DaoException
     */
    @Override
    public BaseOrganUser getBaseOrganUser(String organUserId) throws DaoException {
	BaseOrganUser bou = null;
	try {
	    bou = hibernateDao.findById(BaseOrganUser.class, organUserId);
	} catch (DaoException e) {
	    e.printStackTrace();
	    throw new DaoException("根据ID获取组织管理员失败！");
	}
	return bou;
    }

}