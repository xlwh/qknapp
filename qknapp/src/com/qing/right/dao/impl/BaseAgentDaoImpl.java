package com.qing.right.dao.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.qing.common.base.dao.HibernateDao;
import com.qing.common.exception.DaoException;
import com.qing.common.util.Page;
import com.qing.right.dao.IBaseAgentDao;
import com.qing.right.dao.domain.BaseAgent;

@Service
@Repository
public class BaseAgentDaoImpl implements IBaseAgentDao {

    @Resource
    private HibernateDao<BaseAgent, String> hibernateDao;

    @SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
    public Page queryBaseAgent(Page page, Object[] params) throws DaoException {
	
	int count = 0;
	try {
	    // 查询参数
	    StringBuffer sb = new StringBuffer();
	    StringBuffer sb2 = new StringBuffer();
	    StringBuffer sb3 = new StringBuffer();
	    sb.append(" ");
	    sb2.append("select count(o.agentId)");
	    sb3.append(" from BaseAgent as o where 1=1");

	    if (params != null && params.length > 0 && params[0] != null) {
		sb3.append(" and o.moduleName like '");
		sb3.append(params[0]);
		sb3.append("'");
	    }
	    if (params != null && params.length > 1 && params[1] != null) {
		sb3.append(" and o.userName like '");
		sb3.append(params[1]);
		sb3.append("'");
	    }

	    sb.append(sb3);
	    sb2.append(sb3);

	    // 拼完语句后再排序
	    sb.append(" order by o.agentId desc ");

	    // 查询列表
	    hibernateDao.findByPage(page, sb.toString(), null);

	    // 查询总数
	    count = hibernateDao.findNumByHQL(sb2.toString(), null);
	    page.setTotalCount(count);

	} catch (Exception e) {
	    e.printStackTrace();
	    throw new DaoException("根据查询条件取得代理设定信息！");
	}
	return page;
    }
}