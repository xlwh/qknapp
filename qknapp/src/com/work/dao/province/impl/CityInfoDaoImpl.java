package com.work.dao.province.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.qing.common.base.dao.HibernateDao;
import com.qing.common.exception.DaoException;
import com.qing.common.util.StringUtil;
import com.work.dao.province.ICityInfoDao;
import com.work.domain.CityInfo;

/**
 * 城市和地区信息字典
 * 
 * @author luoqinglong
 * @date 2013-12-16 下午5:27:09
 * @since 1.0
 **/
@Repository
public class CityInfoDaoImpl implements ICityInfoDao {
	@Resource
	HibernateDao<CityInfo, String> hibernateDaoImpl;

	/**
	 * 获取省所辖的市区
	 * 
	 * @param provinceId
	 * @return
	 * @throws DaoException
	 * @see com.mzo.dao.ICityInfoDao#getCityInfoByProvinceId(java.lang.String)
	 **/
	@Override
	public List<CityInfo> getCityInfoByProvinceId(String provinceId) throws DaoException {
		String condition = provinceId.substring(0, 2).concat("__00");
		StringBuffer hql = new StringBuffer();
		hql.append(" from CityInfo c where cityCode like ?");
		List<CityInfo> list = hibernateDaoImpl.findByValues(hql.toString(), new Object[] { condition }, false);

		return list;

	}

	/**
	 * 获取市所辖的地区和县
	 * 
	 * @param cityId
	 * @return
	 * @throws DaoException
	 * @see com.mzo.dao.ICityInfoDao#getRegionInfoByCityId(java.lang.String)
	 **/
	@Override
	public List<CityInfo> getRegionInfoByCityId(String cityId) throws DaoException {
		String condition = cityId.substring(0, 4).concat("%");
		StringBuffer hql = new StringBuffer();
		hql.append(" from CityInfo c where  cityCode != ? and cityCode like ? ");
		List<CityInfo> list = hibernateDaoImpl.findByValues(hql.toString(), new Object[] { cityId, condition }, false);

		return list;

	}

	@Override
	public CityInfo findByCityCode(String cityCode) throws DaoException {
		StringBuffer hql = new StringBuffer();
		hql.append(" from CityInfo c where cityCode ='" + cityCode + "'");
		List<CityInfo> list = hibernateDaoImpl.findByValues(hql.toString(), null, false);
		if (!list.isEmpty()) {
			return list.get(0);
		}
		return null;
	}

	@Override
	public CityInfo findByCityName(String cityName) throws DaoException {
		StringBuffer hql = new StringBuffer();
		hql.append(" from CityInfo c where cityName like ? ");
		String cityNameStr = StringUtil.addLikeSymbol(cityName);
		List<CityInfo> list = hibernateDaoImpl.findByValues(hql.toString(), new Object[] { cityNameStr }, false);
		if (!list.isEmpty()) {
			return list.get(0);
		}
		return null;
	}
}
