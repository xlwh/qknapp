package com.qing.right.dao.impl;

import java.util.Date;

import javax.annotation.Resource;

import org.apache.commons.lang3.time.DateUtils;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.qing.common.base.dao.HibernateDao;
import com.qing.common.exception.DaoException;
import com.qing.common.util.Page;
import com.qing.common.util.StringUtil;
import com.qing.right.dao.IBaseLogDao;
import com.qing.right.dao.domain.BaseLog;

@Service
@Repository
public class BaseLogDaoImpl implements IBaseLogDao {
    @Resource
    private HibernateDao<BaseLog, String> hibernateDao;

    @SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
    public Page queryBaseLog(Page page, Object[] params) throws DaoException {

	int count = 0;
	try {
	    // 查询参数
	    StringBuffer sb = new StringBuffer();
	    StringBuffer sb2 = new StringBuffer();
	    StringBuffer sb3 = new StringBuffer();
	    sb.append(" ");
	    sb2.append("select count(o.logId)");
	    sb3.append(" from BaseLog as o where 1=1");

	    if (params != null && params.length > 0 && params[0] != null) {
		if(StringUtil.isValidStr(params[0].toString())){
		sb3.append(" and o.modifMan='");
		sb3.append(params[0]);
		sb3.append("'");
		}
	    }
	    if (params != null && params.length > 1 && params[1] != null) {
		if(StringUtil.isValidStr(params[1].toString())){
		sb3.append(" and o.entryUserCode='");
		sb3.append(params[1]);
		sb3.append("'");
		}
	    }
	    if (params != null && params.length > 1 && params[2] != null) {
		sb3.append(" and o.entryTime >='");
		sb3.append(params[2]);
		sb3.append("'");
	    }
	    if (params != null && params.length > 1 && params[3] != null) {
		sb3.append(" and o.entryTime < dateadd(day,1,'");
		sb3.append(params[3]);
		sb3.append("')");
	    }

	    sb.append(sb3);
	    sb2.append(sb3);

	    // 拼完语句后再排序
	    sb.append(" order by o.entryTime desc ");

	    // 查询列表
	    hibernateDao.findByPage(page, sb.toString(), null);

	    // 查询总数
	    count = hibernateDao.findNumByHQL(sb2.toString(), null);
	    page.setTotalCount(count);

	} catch (Exception e) {
	    e.printStackTrace();
	    throw new DaoException("根据查询条件取得日志信息！");
	}
	return page;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.sy.right.dao.IBaseUserDao#deleteExpiredLog(int)
     */
    @Override
    public void deleteExpiredLog(int expiredDays) throws DaoException {
	try {
	    StringBuilder sb = new StringBuilder("delete from BaseLog as o where o.entryTime < ?1");
	    Date day = DateUtils.addDays(new Date(), -expiredDays);
	    hibernateDao.update(sb.toString(), new Object[] { day });
	} catch (Exception e) {
	    e.printStackTrace();
	    throw new DaoException("日志批量删除失败！");
	}

    }
    @Override
    public Boolean delBaseLog(String logId) throws DaoException{
   	try {
   	    
   	    StringBuilder hql = new StringBuilder("delete from BaseLog as o where o.logId = ?");
   	    hibernateDao.remove(hql.toString(), new Object[]{logId});
   	   
   	    
   	} catch (Exception e) {
   	    
   	    e.printStackTrace();
   	    throw new DaoException("删除日志信息是否失败！");
   	}
   	return true;
       }
}