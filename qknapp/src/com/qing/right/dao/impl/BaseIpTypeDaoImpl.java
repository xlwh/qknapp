package com.qing.right.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.qing.common.base.dao.HibernateDao;
import com.qing.common.exception.DaoException;
import com.qing.common.util.Page;
import com.qing.right.dao.IBaseIpTypeDao;
import com.qing.right.dao.domain.BaseIpType;

@Service
@Repository
public class BaseIpTypeDaoImpl implements IBaseIpTypeDao {

    @Resource
    private HibernateDao<BaseIpType, String> hibernateDao;

    @SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
    public Page queryBaseIpType(Page page, Object[] params) throws DaoException {

	int count = 0;
	try {
	    // 查询参数
	    StringBuffer sb = new StringBuffer();
	    StringBuffer sb2 = new StringBuffer();
	    StringBuffer sb3 = new StringBuffer();
	    sb.append(" ");
	    sb2.append("select count(o.ipId)");
	    sb3.append(" from BaseIpType as o where 1=1");

	    if (params != null && params.length > 0 && params[0] != null) {
		sb3.append(" and o.ipLimit='");
		sb3.append(params[0]);
		sb3.append("'");
	    }
	    if (params != null && params.length > 1 && params[1] != null) {
		sb3.append(" and o.subCode like '");
		sb3.append(params[1]);
		sb3.append("'");
	    }

	    sb.append(sb3);
	    sb2.append(sb3);

	    // 拼完语句后再排序
	    sb.append(" order by o.ipId desc ");

	    // 查询列表
	    hibernateDao.findByPage(page, sb.toString(), null);

	    // 查询总数
	    count = hibernateDao.findNumByHQL(sb2.toString(), null);
	    page.setTotalCount(count);

	} catch (Exception e) {
	    e.printStackTrace();
	    throw new DaoException("根据查询条件取得IP限制信息！");
	}
	return page;
    }

    @SuppressWarnings("rawtypes")
	@Override
    public List findAllowedIpRange() throws DaoException {
	List list = null;
	try {
	    StringBuffer sb2 = new StringBuffer();
	    sb2.append("select o.bgIp,o.enIp,o.subCode");
	    sb2.append(" from BaseIpType as o");
	    sb2.append(" where o.ipType=2 and o.okFlag='Y' and o.runType='Y'");

	    // 查询列表
	    list = hibernateDao.findBySQL(sb2.toString(), null);
	} catch (Exception e) {
	    e.printStackTrace();
	    throw new DaoException("查询允许访问的IP地址范围失败！");
	}
	return list;
    }

    @SuppressWarnings("rawtypes")
	@Override
    public List findAllowedIpSegment() throws DaoException {
	List list = null;
	try {
	    StringBuffer sb2 = new StringBuffer();
	    sb2.append("select o.ipLimit,o.subCode");
	    sb2.append(" from BaseIpType as o");
	    sb2.append(" where o.ipType=1 and o.okFlag='Y' and o.runType='Y'");

	    // 查询列表
	    list = hibernateDao.findBySQL(sb2.toString(), null);
	} catch (Exception e) {
	    e.printStackTrace();
	    throw new DaoException("查询允许访问的IP地址段失败！");
	}
	return list;
    }

    @SuppressWarnings("rawtypes")
	@Override
    public List findRefusedIpRange() throws DaoException {
	List list = null;
	try {
	    StringBuffer sb2 = new StringBuffer();
	    sb2.append("select o.bgIp,o.enIp,o.subCode");
	    sb2.append(" from BaseIpType as o");
	    sb2.append(" where o.ipType=2 and o.okFlag='Y' and o.runType='N'");

	    // 查询列表
	    list = hibernateDao.findBySQL(sb2.toString(), null);
	} catch (Exception e) {
	    e.printStackTrace();
	    throw new DaoException("查询禁止访问的IP地址范围失败！");
	}
	return list;
    }

    @SuppressWarnings("rawtypes")
	@Override
    public List findRefusedIpSegment() throws DaoException {
	List list = null;
	try {
	    StringBuffer sb2 = new StringBuffer();
	    sb2.append("select o.ipLimit,o.subCode");
	    sb2.append(" from BaseIpType as o");
	    sb2.append(" where o.ipType=1 and o.okFlag='Y' and o.runType='N'");

	    // 查询列表
	    list = hibernateDao.findBySQL(sb2.toString(), null);
	} catch (Exception e) {
	    e.printStackTrace();
	    throw new DaoException("查询允许访问的IP地址段失败！");
	}
	return list;
    }
}