package com.qing.right.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qing.common.base.dao.HibernateDao;
import com.qing.common.exception.DaoException;
import com.qing.right.dao.IBaseModuleDao;
import com.qing.right.dao.domain.BaseModule;
import com.qing.right.dao.domain.BasePermit;

@Service
@Repository
@Transactional
public class BaseModuleDaoImpl implements IBaseModuleDao {

    public static final String NO_ZERO = "00000";
    public static final String NO_START = "00100";
    public static final String NO_END = "10000";
    public static final int NO_CREMENT = 100;
    public static final int NO_MAX = 10000;
    public static final int NO_LEN = 5;

    @Resource
    private HibernateDao<BaseModule, String> hibernateDao;

    @Resource
    private HibernateDao<BasePermit, String> hibernatePermitDao;

    private JdbcTemplate jdbcTemplate;

    public void setDataSource(DataSource dataSource) {
	this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @SuppressWarnings("rawtypes")
	@Override
    public List queryTree() throws DaoException {
	List list = null;
	try {
	    list = hibernateDao.findByValues("from BaseModule as o order by o.moduleLev esc", null, false);
	} catch (Exception e) {
	    e.printStackTrace();
	    throw new DaoException("查询模块信息失败！");
	}
	return list;
    }

    /**
     * 计算新的等级
     * 
     * @param smallModule
     *            小模块对象
     * @param bigModule
     *            大模块对象
     * @return 等级编号
     * @throws CreateNoException
     */
    private String getNewModuleLevel(BaseModule smallModule, BaseModule bigModule) throws DaoException {
	String newLevel = "";
	if (smallModule == null && bigModule != null) {
	    newLevel = getNewLevel(null, bigModule.getModuleLev());
	} else if (bigModule == null && smallModule != null) {
	    newLevel = getNewLevel(smallModule.getModuleLev(), null);
	} else if (smallModule != null && bigModule != null) {
	    newLevel = getNewLevel(smallModule.getModuleLev(), bigModule.getModuleLev());
	}
	return newLevel;
    }

    /**
     * 工具类 ，生成新等级编号
     * 
     * @param bigModule
     * @param smallModule
     * @param curModule
     * @return
     * @throws CreateNoException
     */
    private String getNewLevel(String smallLevel, String bigLevel) throws DaoException {
	int bigNum = 0;
	int smallNum = 0;
	if (smallLevel != null) {
	    smallNum = Integer.parseInt(smallLevel.substring(smallLevel.length() - NO_LEN));
	}
	if (bigLevel != null) {
	    bigNum = Integer.parseInt(bigLevel.substring(bigLevel.length() - NO_LEN));
	} else {
	    bigNum = smallNum + NO_CREMENT;
	}
	// 编号
	int newNum = 0;
	String preStr = "";
	if (bigNum == smallNum) {
	    throw new DaoException("两等级编号相等，新等级编号无法生成!");
	} else if (bigNum > smallNum) {
	    if (bigNum - smallNum < 2) {
		throw new DaoException("等级编号相邻太近，新等级编号无法生成!");
	    }
	} else {
	    throw new RuntimeException("输入两编号的大小位置相反，不符合参数条件，新等级编号无法生成!");
	}
	preStr = smallLevel.substring(0, smallLevel.length() - 5);
	newNum = (bigNum + smallNum) / 2;
	String newLevel = NO_ZERO + newNum;
	newLevel = preStr + newLevel.substring(newLevel.length() - NO_LEN);
	return newLevel;
    }

    /**
     * 重设当前结点及子结点的等级
     * 
     * @param curModule
     *            当前结点
     * @param curLevel
     * @param newLevel
     * @throws DaoException
     */
    private void resetCurAndSubLevel(BaseModule curModule, String newLevel) throws DaoException {
	try {
	    List<BaseModule> list = hibernateDao.findByValues("from BaseModule o where o.moduleLev like '" + curModule.getModuleLev()
		    + "%' and o.moduleParentId='" + curModule.getModuleId() + "'", null, false);
	    for (BaseModule bm : list) {
		bm.setModuleLev(newLevel + bm.getModuleLev().substring(bm.getModuleLev().length() - newLevel.length()));
		hibernateDao.saveOrUpdate(bm);
	    }
	    curModule.setModuleLev(newLevel);
	    hibernateDao.saveOrUpdate(curModule);
	} catch (Exception e) {
	    e.printStackTrace();
	    throw new DaoException("更新失败");
	}
    }

    /**
     * 移动结点
     * 
     * @param smallModule
     * @param bigModule
     * @param curModule
     * @throws CreateNoException
     * @throws DaoException
     */
    public void moveNodeTree(BaseModule smallModule, BaseModule bigModule, BaseModule curModule) throws DaoException {
	String newLevel = getNewModuleLevel(smallModule, bigModule);
	resetCurAndSubLevel(curModule, newLevel);
    }

    /*
     * 添加
     */
    @SuppressWarnings("rawtypes")
	public void addSubNode(BaseModule parentModule, BaseModule curModule) throws DaoException {
	StringBuffer level = new StringBuffer();
	if (parentModule == null) {
	    // 此为根结点
	    level.append(NO_START);
	    curModule.setModuleLev(level.toString());
	} else {
	    // 取得子结点中最大的编号
	    List list = hibernateDao.findByValues("select max(o.moduleLev)from BaseModule as o where o.moduleParentId=? ", null, false);

	    level.append(parentModule.getModuleLev());
	    if (list != null && list.size() > 0) {
		// 编号递增
		String no = (String) list.get(0);
		int num = Integer.valueOf(no);
		int num2 = num + NO_CREMENT;
		String no2 = NO_ZERO + num2;
		no2 = no2.substring(no2.length() - NO_LEN);
		level.append(no2);
	    } else {
		// 第一个编号
		level.append(NO_START);
	    }
	    // 取得父结点的子
	}

	// 保存
	try {
	    hibernateDao.saveOrUpdate(curModule);
	} catch (Exception e) {
	    e.printStackTrace();
	    throw new DaoException("保存失败！");
	}
    }

    @Override
    public Map<String, BaseModule> getURLMap() throws DaoException {
	List<BaseModule> list = hibernateDao.findByValues("from BaseModule as o where o.url is not null", null, false);
	HashMap<String, BaseModule> map = new HashMap<String, BaseModule>();
	for (BaseModule bm : list) {
	    map.put(bm.getUrl(), bm);
	}
	return map;
    }

    @Override
    public List<BaseModule> getBaseModules() throws DaoException {
	List<BaseModule> list = hibernateDao.findByValues("from BaseModule as o where o.moduleType = 0 order by o.moduleLev", null, false);
	return list;
    }

    public Boolean delModule(String[] moduleId) throws DaoException {
	try {

	    StringBuilder hql = new StringBuilder("delete from BaseModule as o where o.moduleId = ?");
	    hibernateDao.remove(hql.toString(), new Object[] { moduleId });

	} catch (Exception e) {

	    e.printStackTrace();
	    throw new DaoException("删除菜单信息是否失败！");
	}
	return true;
    }

    /**
     * 根据菜单ID将对应的菜单信息查询出来
     * 
     * @param dutiesId
     * @return
     * @throws DaoException
     */
    @Override
    public BaseModule getBaseModule(String moduleId) throws DaoException {
	BaseModule bm = null;
	try {
	    bm = hibernateDao.findById(BaseModule.class, moduleId);
	} catch (DaoException e) {
	    e.printStackTrace();
	    throw new DaoException("根据ID获取菜单失败！");
	}
	return bm;
    }

    /**
     * 获取顶层模块 覆盖方法/实现方法(选择其一) (功能详细描述)
     * 
     * @see com.qing.right.dao.IBaseModuleDao#getTopModuleList()
     * @author huangqingjian/0050
     * @see 相关函数,对于重要的函数建议注释
     * @since 深烟智能物流管理平台, 2013-1-10
     * @return
     * @throws DaoException
     */
    @Override
    public List<Map<String, Object>> getTopModuleList() throws DaoException {
	List<Map<String, Object>> topModuleList = null;

	String sql = "SELECT MODULE_ID,MODULE_NAME FROM T_BASE_MODULE WHERE MODULE_PARENT_ID = '0' AND MODULE_TYPE = 0 ORDER BY MODULE_LEV";
	topModuleList = jdbcTemplate.queryForList(sql);

	return topModuleList;

    }

    @Override
    public long getModuleLevel() throws DaoException {
	long level = 0;

	String sql = "SELECT max(MODULE_LEV)+10000 FROM T_BASE_MODULE WHERE MODULE_PARENT_ID = '0'";
	level = jdbcTemplate.queryForLong(sql);

	return level;

    }

    @Override
    public long getModuleLevel(String parentId) throws DaoException {
	long level = 0;
	long topLevel = 0;
	String parentSql = "SELECT MODULE_LEV FROM T_BASE_MODULE WHERE MODULE_ID = ? ";
	topLevel = jdbcTemplate.queryForLong(parentSql, parentId);
	String sql = "SELECT max(MODULE_LEV)+1 FROM T_BASE_MODULE WHERE MODULE_PARENT_ID = ?";
	level = jdbcTemplate.queryForLong(sql, parentId);

	if (level == 0) {
	    level = topLevel * 100;
	}

	return level;

    }

    /**
     * 
     * 实现方法
     * 
     * @see com.qing.right.dao.IBaseModuleDao#getModuleListByParentId(java.lang.String)
     * @author huangqingjian/0050
     * @see 相关函数,对于重要的函数建议注释
     * @since 深烟智能物流管理平台, 2013-1-10
     * @param parentId
     * @return
     * @throws DaoException
     */
    @Override
    public List<BaseModule> getModuleListByParentId(String parentId) throws DaoException {
	List<BaseModule> ModuleList = hibernateDao.findByValues("from BaseModule as o where o.moduleParentId = ?",
		new Object[] { parentId }, false);

	return ModuleList;

    }

    /**
     * 
     * 实现方法
     * 
     * @see com.qing.right.dao.IBaseModuleDao#getModuleListByParentId(java.lang.String)
     * @author huangqingjian/0050
     * @see 相关函数,对于重要的函数建议注释
     * @since 深烟智能物流管理平台, 2013-1-10
     * @param parentId
     * @return
     * @throws DaoException
     */
    @Override
    public List<BasePermit> getPermitListByParentId(String moduleId) throws DaoException {
	List<BasePermit> ModuleList = hibernatePermitDao.findByValues(
		"from BasePermit as o where o.baseModule.moduleId = ? order by o.permitSort", new Object[] { moduleId }, false);

	return ModuleList;

    }
}