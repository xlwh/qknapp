package com.qing.right.dao.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.qing.common.base.dao.HibernateDao;
import com.qing.common.exception.DaoException;
import com.qing.common.exception.handle.ExceptionHandle;
import com.qing.common.util.ErrorCode;
import com.qing.common.util.Page;
import com.qing.right.dao.IBaseUserDao;
import com.qing.right.dao.domain.BaseUser;
import com.qing.vo.OrganVO;

@Service
@Repository
public class BaseUserDaoImpl implements IBaseUserDao {

    @Resource
    private HibernateDao<BaseUser, String> hibernateDao;
    
    private JdbcTemplate                   jdbcTemplate;

    public void setDataSource(DataSource dataSource) {
	this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @SuppressWarnings("rawtypes")
	@Override
    public List findUsers(Object[] params, List<OrganVO> organVOs) throws DaoException {
	List<BaseUser> list = null;
	try {
	    // 查询参数
	    StringBuffer sb = new StringBuffer();
	    sb.append("from BaseUser as o where 1=1 and o.userType=3 ");

	    if (params != null && params.length > 0 && params[0] != null) {
		sb.append(" and o.userId='");
		sb.append(params[0]);
		sb.append("'");
	    }
	    if (params != null && params.length > 1 && params[1] != null) {
		sb.append(" and o.loginCode like '%");
		sb.append(params[1]);
		sb.append("%'");
	    }
	    if (params != null && params.length > 2 && params[2] != null) {
		sb.append(" and o.userName like '%");
		sb.append(params[2]);
		sb.append("%'");
	    }
	    if (params != null && params.length > 3 && params[3] != null) {
		sb.append(" and o.employeeId like '%");
		sb.append(params[3]);
		sb.append("%'");
	    }
	    if (params != null && params.length > 4 && params[4] != null) {
		sb.append(" and o.organName like '%");
		sb.append(params[4]);
		sb.append("%'");
	    }

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
	    throw new DaoException("查询用户信息列表失败！");
	}
	return list;
    }

    @SuppressWarnings("rawtypes")
	@Override
    public BaseUser findUserByLoginCode(String loginCode) throws DaoException {
	BaseUser user = null;
	StringBuilder hql = new StringBuilder("select o from BaseUser as o inner join o.baseOrgan og where o.loginCode=? ");
	List list = hibernateDao.findByValues(hql.toString(), new Object[] { loginCode }, false);
	if (list != null && list.size() > 0) {
	    return (BaseUser) list.get(0);
	}
	return user;
    }

    @Override
    public void resetPassword(String userId, String password) throws DaoException {
	try {
	    StringBuilder hql = new StringBuilder("update BaseUser o set o.password=?1,o.pwdLastUpdateTime=?3 where o.userId=?2 ");
	    hibernateDao.update(hql.toString(), new Object[] { password, userId, new Date(), });
	} catch (Exception e) {
	    e.printStackTrace();
	    throw new DaoException("重设用户密码失败！");
	}
    }

    @SuppressWarnings("rawtypes")
	@Override
    public Page queryBaseUser(Page page, int stype, Object[] params, Object[] organId) throws DaoException {
	int count = 0;
	try {
	    // 查询参数
	    StringBuffer sb = new StringBuffer(); // 查询列表
	    StringBuffer sb2 = new StringBuffer(); // 统计总数
	    StringBuffer sb3 = new StringBuffer();
	    StringBuffer sb4 = new StringBuffer();
	    StringBuffer sb5 = new StringBuffer();

	    sb2.append("select count(o.userId)");

	    sb3.append(" from BaseUser as o left join fetch o.baseOrgan og where 1=1");
	    sb4.append(" from BaseUser as o where 1=1");

	    // 当stype为1时查询当前组织的用户，为2时当前 组织及子组织的用户
	    if (stype == 1) {
		sb5.append(" and o.baseOrgan.organId= '" + organId[0] + "'");
	    }

	    if (params != null && params.length > 0 && params[0] != null) {
		sb5.append(" and o.userId='");
		sb5.append(params[0]);
		sb5.append("'");
	    }
	    if (params != null && params.length > 1 && params[1] != null) {
		sb5.append(" and o.loginCode like '");
		sb5.append(params[1]);
		sb5.append("'");
	    }
	    if (params != null && params.length > 2 && params[2] != null) {
		sb5.append(" and o.userName like '");
		sb5.append(params[2]);
		sb5.append("'");
	    }
	    if (params != null && params.length > 3 && params[3] != null) {
		sb5.append(" and o.employeeId like '");
		sb5.append(params[3]);
		sb5.append("'");
	    }
	    if (params != null && params.length > 4 && params[4] != null) {
		sb5.append(" and o.baseOrgan.organName like '");
		sb5.append(params[4]);
		sb5.append("'");
	    }

	    sb.append(sb3).append(sb5).append(" order by o.userId desc");
	    sb2.append(sb4).append(sb5);

	    // 查询列表
	    hibernateDao.findByValues(sb.toString(), null, false);
	    // 查询总数
	    count = hibernateDao.findNumByHQL(sb2.toString(), null);
	    page.setTotalCount(count);

	} catch (Exception e) {
	    e.printStackTrace();
	    throw new DaoException("根据查询条件取得用户信息！");
	}
	return page;
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
    public Page queryUserByOrganId(Page page, String organIds) throws DaoException {
	StringBuilder sql = new StringBuilder(" select o from BaseUser o where o.userType = 3 and o.baseOrgan.organId in ('" + organIds
	        + "')");

	StringBuilder countSQL = new StringBuilder(" select count(*) from BaseUser o where o.baseOrgan.organId in ('" + organIds + "')");

	int count = 0;

	// 查询列表
	hibernateDao.findByPage(page, sql.toString(), null);

	// 查询总数
	count = hibernateDao.findNumByHQL(countSQL.toString(), null);
	page.setTotalCount(count);
	return page;
    }

    @Override
    public void updateUserByLastLogin(String loginCode, String ipAddress) throws DaoException {
	try {
	    StringBuilder hql = new StringBuilder("update BaseUser o set o.lastLoginActive=?2,o.loginIp=?3 where o.loginCode=?1");
	    hibernateDao.update(hql.toString(), new Object[] { loginCode, new Date(), ipAddress });
	} catch (Exception e) {
	    e.printStackTrace();
	    throw new DaoException("用户最新一次登录时,用户信息更新失败！");
	}
    }

    @Override
    public Boolean checkLoginCode(String loginCode) throws DaoException {
	Boolean bool = false;
	long count = 0;
	try {
	    StringBuilder hql = new StringBuilder("select count(o.userId) from BaseUser as o where o.loginCode=?");
	    count = hibernateDao.findNumByHQL(hql.toString(), new Object[] { loginCode });

	    if (count > 0) {
		bool = true;
	    }
	} catch (Exception e) {
	    e.printStackTrace();
	    throw new DaoException("检查登陆账号是否重复失败！");
	}
	return bool;
    }

    @Override
    public BaseUser loginCheck(String loginCode, String password) throws DaoException {
	BaseUser user = null;
	StringBuilder hql = new StringBuilder(" from BaseUser as o where o.loginCode=?1 and o.password=?2 ");
	List<BaseUser> list = hibernateDao.findByValues(hql.toString(), new Object[] { loginCode, password }, false);
	if (list != null && list.size() > 0) {
	    user = list.get(0);
	}
	return user;
    }

    @Override
    public Boolean delBaseUser(String userId) throws DaoException {
	try {
	    // StringBuilder hql = new
	    // StringBuilder("delete from BaseUser as o where o.userId = ?");
	    // hibernateDao.remove(hql.toString(), new Object[]{userId});
	    /**
	     * 1. 查询userId相应的BaseUser对象 2. 更改BaseUser 字段status Y为N
	     */
	    StringBuilder hql = new StringBuilder("from BaseUser as o where o.userId=? ");
	    List<BaseUser> list = hibernateDao.findByValues(hql.toString(), new Object[] { userId }, false);
	    if (null != list && list.size() > 0) {
		BaseUser baseUser = list.get(0);
		baseUser.setStatus("N");
		hibernateDao.saveOrUpdate(baseUser);
	    }

	} catch (Exception e) {

	    e.printStackTrace();
	    throw new DaoException("删除用户信息是否失败！");
	}
	return true;
    }

    /**
     * 根据登陆编号权限获取手持机用户权限
     * 
     * @param loginCode
     * @return
     */
    @Override
    @SuppressWarnings("rawtypes")
    public List getHandMobilePermitByLoginCode(String loginCode) {
	StringBuilder hql = new StringBuilder(350);
	hql.append(" SELECT BP.PERMIT_NAME, BP.PERMIT_DESC, B.LOGIN_CODE, B.PASSWORD  FROM T_BASE_USER B , T_BASE_USER_PERMIT BUP, T_BASE_PERMIT BP  ");
	hql.append(" WHERE B.USER_ID =  BUP.USER_ID    AND BUP.PERMIT_ID = BP.PERMIT_ID   AND B.LOGIN_CODE = ? AND BP.PERMIT_TYPE='5' ");
	hql.append("UNION SELECT DISTINCT BP.PERMIT_NAME, BP.PERMIT_DESC, B.LOGIN_CODE, B.PASSWORD  FROM T_BASE_USER B ,T_BASE_ROLE_USER BRU, T_BASE_ROLE_PERMIT BRP, T_BASE_PERMIT BP   ");
	hql.append("WHERE B.LOGIN_CODE = ? AND B.USER_ID =  BRU.USER_ID   AND BRP.PERMIT_ID = BP.PERMIT_ID   AND  BP.PERMIT_TYPE='5' AND BRU.ROLE_ID=BRP.ROLE_ID");
	List list = jdbcTemplate.queryForList(hql.toString(), new Object[] { loginCode, loginCode });
	return list;
    }

    @Override
    public BaseUser getBaseUser(String userId) throws DaoException {
	BaseUser bu = null;
	try {
	    bu = hibernateDao.findById(BaseUser.class, userId);
	} catch (DaoException e) {
	    e.printStackTrace();
	    throw new DaoException("根据ID获取用户失败！");
	}
	return bu;
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
    public List<BaseUser> getAllUserByOrganId(List<OrganVO> organVOs) throws DaoException {

	List<BaseUser> list = null;

	StringBuffer organIDs = new StringBuffer();

	for (int i = 0; i < organVOs.size(); i++) {
	    organIDs.append("'").append(organVOs.get(i).organId).append("'");
	    if (i < organVOs.size() - 1)
		organIDs.append(",");
	}
	StringBuffer str1 = new StringBuffer();
	str1.append(" select o from BaseUser o where o.baseOrgan.organId in (").append(organIDs).append(")");
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
    public List<Map<String, Object>> getAllUserVOByOrganId(String organID) throws DaoException {

	List<Map<String, Object>> list = null;
	String sql = " SELECT ORGAN_ID,USER_ID,USER_NAME,USER_TYPE FROM T_BASE_USER T WHERE T.ORGAN_ID=?";
	list = jdbcTemplate.queryForList(sql, organID);

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
    public Map getRouteCodeByMcnCode(String mcnCode) throws DaoException {
	StringBuilder sql = new StringBuilder(150);
	sql.append(" SELECT ROUTE_CODE FROM T_HANDSET_BINDING WHERE MACHINE_CODE = ? ");
	List list = jdbcTemplate.queryForList(sql.toString(), mcnCode);
	if (null == list || list.size() <= 0) {
	    return null;
	} else {
	    return (Map) list.get(0);
	}
    }

    @Override
    public List<BaseUser> getUsersByCurOrganId(String organId) throws DaoException {
	List<BaseUser> busers = null;
	StringBuffer hql = new StringBuffer();
	hql.append(" from BaseUser bu where bu.baseOrgan.organId = ? ");
	try {
	    busers = hibernateDao.findByValues(hql.toString(), new Object[] { organId }, false);
	} catch (DaoException e) {
	    ExceptionHandle.throwDaoException(e, ErrorCode.DAO_QUERY_ERROR_004, "BaseUserDaoImpl.getUsersByCurOrganId()"
		    + "根据当前组织ID获取当前组织下所有人失败");
	}
	return busers;
    }

    @Override
    public BaseUser loginCheckInPhone(String loginCode, String password) throws DaoException {
	BaseUser user = null;
	StringBuilder hql = new StringBuilder(" from BaseUser as o where o.loginCode = ? and o.password = ?");
	List<BaseUser> list = hibernateDao.findByValues(hql.toString(), new Object[] { loginCode, password }, false);
	if (list != null && list.size() > 0) {
	    user = list.get(0);
	}
	return user;
    }
}