package com.work.dao.province;

import java.util.List;

import com.qing.common.exception.DaoException;
import com.work.domain.CityInfo;

/**
 * 城市和地区信息字典
 * 
 * @author luoqinglong
 * @date 2013-12-16 下午5:21:55
 * @since 1.0
 **/
public interface ICityInfoDao {
	/**
	 * 获取省所辖的市区
	 * 
	 * @param provinceId
	 * @return
	 * @throws DaoException
	 * 
	 */
	List<CityInfo> getCityInfoByProvinceId(String provinceId) throws DaoException;

	/**
	 * 根据市编号查找城市信息
	 * @param cityCode
	 * @return
	 * @throws DaoException
	 */
	CityInfo findByCityCode(String cityCode) throws DaoException;

	/**
	 * 
	 * 
	 * 获取市所辖的地区和县
	 * 
	 * @param cityId
	 * @return
	 * @throws DaoException
	 * 
	 */
	List<CityInfo> getRegionInfoByCityId(String cityId) throws DaoException;

	/**
	 * 根据城市名称获取城市信息
	 * (功能详细描述)
	 * @author       huangqingjian/0050
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        清清网系统, 2014年9月15日
	 * @param cityName
	 * @return
	 * @throws DaoException
	 */
	CityInfo findByCityName(String cityName) throws DaoException;

}
