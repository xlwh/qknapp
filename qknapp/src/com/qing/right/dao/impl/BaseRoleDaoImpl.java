package com.qing.right.dao.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.qing.common.base.dao.HibernateDao;
import com.qing.common.exception.DaoException;
import com.qing.common.util.Page;
import com.qing.right.dao.IBaseRoleDao;
import com.qing.right.dao.domain.BaseRole;
import com.qing.vo.OrganVO;

@Service
@Repository
public class BaseRoleDaoImpl implements IBaseRoleDao {

    @Resource
    private HibernateDao<BaseRole, String> hibernateDao;

    @Resource
    private HibernateDao<String, String> hibernateDaoStr;

    private JdbcTemplate jdbcTemplate;

    public void setDataSource(DataSource dataSource) {
	this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Page<BaseRole> queryChild(Page<BaseRole> page, String organId) throws DaoException {
	List<String> list = null;
	List<String> listCount = null;
	StringBuffer str1 = new StringBuffer();
	StringBuffer str2 = new StringBuffer();
	StringBuffer str3 = new StringBuffer();
	StringBuffer str4 = new StringBuffer();
	StringBuffer sb = new StringBuffer();

	str1.append(" select o from BaseRole o where o.baseOrgan.organId in ");

	str2.append(" select a.organId from BaseOrgan as a where a.organId ='").append(organId)
		.append("' or a.organId in (select b.organId from BaseOrgan as b where b.organFather ='").append(organId)
		.append("') or a.organFather in (select c.organId from BaseOrgan as c where c.organFather ='").append(organId)
		.append("') ");

	str3.append(" order by o.roleId desc ");

	int count = 0;

	sb.append("select count(o.roleId) from BaseRole o where o.baseOrgan.organId in ");

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
     * 查询当前组织下所有的角色，包括自组织的角色
     * 
     * @see com.qing.right.dao.IBaseRoleDao#getAllRoleByOrganId(java.lang.String)
     * @author huangqingjian/0050
     * @see 相关函数,对于重要的函数建议注释
     * @since 深烟智能物流管理平台, 2013-1-9
     * @param organId
     * @return
     * @throws DaoException
     */
    @Override
    public List<BaseRole> getAllRoleByOrganId(List<OrganVO> organVOs) throws DaoException {

	List<BaseRole> list = null;

	StringBuffer organIDs = new StringBuffer();

	for (int i = 0; i < organVOs.size(); i++) {
	    organIDs.append("'").append(organVOs.get(i).organId).append("'");
	    if (i < organVOs.size() - 1)
		organIDs.append(",");
	}
	StringBuffer str1 = new StringBuffer();
	str1.append(" select o from BaseRole o where o.baseOrgan.organId in (").append(organIDs).append(")");
	list = hibernateDao.findByValues(str1.toString(), null, false);

	return list;

    }

    /**
     * 
     * 根据当前登陆管理能管理的组织加载用户
     * 
     * @see com.qing.right.dao.IBaseUserDao#getAllRoleByOrganId(java.util.List)
     * @author huangqingjian/0050
     * @see 相关函数,对于重要的函数建议注释
     * @since 深烟智能物流管理平台, 2013-1-9
     * @param organVOs
     * @return
     * @throws DaoException
     */
    @Override
    public List<Map<String, Object>> getAllRoleVOByOrganId(String organID) throws DaoException {

	List<Map<String, Object>> list = null;
	String sql = " SELECT ORGAN_ID,ROLE_ID,ROLE_NAME FROM T_BASE_ROLE T WHERE T.ORGAN_ID=?";
	list = jdbcTemplate.queryForList(sql, organID);

	return list;

    }
}