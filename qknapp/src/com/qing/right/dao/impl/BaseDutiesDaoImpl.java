package com.qing.right.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.qing.common.base.dao.HibernateDao;
import com.qing.common.exception.DaoException;
import com.qing.common.util.Page;
import com.qing.right.dao.IBaseDutiesDao;
import com.qing.right.dao.domain.BaseDuties;
import com.qing.vo.OrganVO;

@Service
@Repository
public class BaseDutiesDaoImpl implements IBaseDutiesDao {
    @Resource
    private HibernateDao<BaseDuties, String> hibernateDao;

    @Resource
    private HibernateDao<String, String> hibernateDaoStr;

    @Override
    public Map<String, BaseDuties> getDutiedesc() throws DaoException {
	List<BaseDuties> list = hibernateDao.findByValues("from BaseDuties as o where o.dutiedesc is not null", null, false);
	HashMap<String, BaseDuties> map = new HashMap<String, BaseDuties>();
	for (BaseDuties bd : list) {
	    map.put(bd.getDutiedesc(), bd);
	}
	return map;
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
    public Page queryDutiesByOrganId(Page page, String organIds) throws DaoException {
	StringBuilder sql = new StringBuilder(" select o from BaseDuties o where o.baseOrgan.organId in ('" + organIds + "')");

	StringBuilder countSQL = new StringBuilder(" select count(*) from BaseDuties o where o.baseOrgan.organId in ('" + organIds + "')");

	int count = 0;

	// 查询列表
	hibernateDao.findByPage(page, sql.toString(), null);

	// 查询总数
	count = hibernateDao.findNumByHQL(countSQL.toString(), null);
	page.setTotalCount(count);
	return page;
    }

    @Override
    public Page<BaseDuties> queryChild(Page<BaseDuties> page, String organId) throws DaoException {
	List<String> list = null;
	List<String> listCount = null;
	StringBuffer str1 = new StringBuffer();
	StringBuffer str2 = new StringBuffer();
	StringBuffer str3 = new StringBuffer();
	StringBuffer str4 = new StringBuffer();
	StringBuffer sb = new StringBuffer();

	str1.append(" select o from BaseDuties o where o.baseOrgan.organId in ");

	str2.append(" select a.organId from BaseOrgan as a where a.organId ='").append(organId)
		.append("' or a.organId in (select b.organId from BaseOrgan as b where b.organFather ='").append(organId)
		.append("') or a.organFather in (select c.organId from BaseOrgan as c where c.organFather ='").append(organId)
		.append("') ");

	str3.append(" order by o.dutieid desc ");

	int count = 0;

	sb.append("select count(o.dutieid) from BaseDuties o where o.baseOrgan.organId in ");

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
	hibernateDao.findByPage(page, str1 + "(" + str2 + ")" + str3, null);

	// 查询总数
	count = hibernateDao.findNumByHQL(sb + "(" + str2 + ")", null);
	page.setTotalCount(count);
	return page;
    }

    /**
     * 根据组织ID将当前组织和子组织的职位都查询出来
     * 
     * @param organId
     * @param organVOs
     * @return
     * @throws DaoException
     */
    @Override
    public List<BaseDuties> getDutiesList(String organId, List<OrganVO> organVOs) throws DaoException {
	List<BaseDuties> list = null;
	try {
	    StringBuffer sb = new StringBuffer();
	    sb.append("from BaseDuties as o where 1 =1 ");
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
	    throw new DaoException("查询职位信息失败！");
	}
	return list;
    }

    /**
     * 根据职务ID将关联的用户都查询出来
     * 
     * @param organId
     * @return
     * @throws DaoException
     */
    @Override
    public List<String> getUserIdList(String dutiesId) throws DaoException {
	List<String> list = null;
	try {
	    list = hibernateDaoStr.findByValues("select o.userId from BaseUser as o where o.baseDuties.dutieid = ?",
		    new Object[] { dutiesId }, false);
	} catch (Exception e) {
	    e.printStackTrace();
	    throw new DaoException("查询职位信息失败！");
	}
	return list;
    }

    /**
     * 根据职务ID将对应的职务信息查询出来
     * 
     * @param dutiesId
     * @return
     * @throws DaoException
     */
    @Override
    public BaseDuties getBaseDuties(String dutiesId) throws DaoException {
	BaseDuties bd = null;
	try {
	    bd = hibernateDao.findById(BaseDuties.class, dutiesId);
	} catch (DaoException e) {
	    e.printStackTrace();
	    throw new DaoException("根据ID获取职位失败！");
	}
	return bd;
    }
}
