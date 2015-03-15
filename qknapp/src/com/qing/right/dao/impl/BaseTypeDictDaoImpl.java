package com.qing.right.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.qing.common.base.dao.HibernateDao;
import com.qing.common.exception.DaoException;
import com.qing.right.dao.IBaseTypeDictDao;
import com.qing.right.dao.domain.BaseTypeDict;

@Service
@Repository
public class BaseTypeDictDaoImpl implements IBaseTypeDictDao {
    @Resource
    private HibernateDao<BaseTypeDict, String> hibernateDao;

    @SuppressWarnings("rawtypes")
	@Override
    public List queryBaseTypeDict(Object[] params) throws DaoException {
	List list = null;
	try {
	    // 查询参数
	    StringBuffer sb = new StringBuffer();
	    StringBuffer sb2 = new StringBuffer();
	    StringBuffer sb3 = new StringBuffer();
	    sb.append(" ");
	    sb2.append("select count(o.dictId)");
	    sb3.append(" from BaseTypeDict as o where 1=1");

	    if (params != null && params.length > 0 && params[0] != null) {
		sb3.append(" and o.dictParentId ='");
		sb3.append(params[0]);
		sb3.append("'");
	    }
	    if (params != null && params.length > 1 && params[1] != null) {
		sb3.append(" and o.dictOpen =");
		sb3.append(params[1]);
	    }

	    sb.append(sb3);
	    sb2.append(sb3);

	    // 拼完语句后再排序
	    sb.append(" order by o.dictId desc ");

	    // 查询列表
	    list = hibernateDao.findByValues(sb.toString(), null, false);

	} catch (Exception e) {
	    e.printStackTrace();
	    throw new DaoException("根据父ID取得数据字典信息！");
	}
	return list;
    }
}