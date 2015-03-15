package com.work.dao.province.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.qing.common.base.dao.HibernateDao;
import com.qing.common.exception.DaoException;
import com.work.dao.province.IProvinceInfoDao;
import com.work.domain.ProvinceInfo;

/**
 * Description
 * 
 * @author luoqinglong
 * @date 2013-12-16 下午5:26:57
 * @since 1.0
 **/
@Repository
public class ProvinceDaoImpl implements IProvinceInfoDao {
	@Resource
	HibernateDao<ProvinceInfo, String> baseHibernateDaoImpl;

	/**
	 * 获取所有的省份
	 * 
	 * @return
	 * @throws DaoException
	 * @see com.mzo.dao.IProvinceInfoDao#getAllProvinceInfo()
	 **/
	@Override
	public List<ProvinceInfo> getAllProvinceInfo() throws DaoException {
		StringBuffer hql = new StringBuffer();
		hql.append("from ProvinceInfo ");
		List<ProvinceInfo> list = baseHibernateDaoImpl.findByValues(hql.toString(), new Object[] {}, false);
		return list;

	}

	@Override
	public ProvinceInfo findByProvinceCode(String provinceCode) throws DaoException {
		StringBuffer hql = new StringBuffer();
		hql.append("from ProvinceInfo where provinceCode='" + provinceCode + "'");
		List<ProvinceInfo> list = baseHibernateDaoImpl.findByValues(hql.toString(), new Object[] {}, false);
		if (!list.isEmpty()) {
			return list.get(0);
		}
		return null;
	}

	@Override
	public ProvinceInfo findByProvinceName(String provinceName) throws DaoException {
		StringBuffer hql = new StringBuffer();
		hql.append("from ProvinceInfo where provinceName=?");
		List<ProvinceInfo> list = baseHibernateDaoImpl.findByValues(hql.toString(), new Object[] { provinceName },
				false);
		if (!list.isEmpty()) {
			return list.get(0);
		}
		return null;
	}
}
