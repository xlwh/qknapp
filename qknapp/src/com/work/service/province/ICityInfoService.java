package com.work.service.province;

import java.util.List;

import com.qing.common.exception.ServiceException;
import com.work.domain.CityInfo;

/**
 * 城市地区业务类
 * 
 * @author luoqinglong
 * @date 2013-12-16 下午5:49:14
 * @since 1.0
 **/
public interface ICityInfoService {
	/**
	 * 获取省所辖的市区
	 * 
	 * @param provinceId
	 * @return
	 * @throws ServivceException
	 * 
	 */
	List<CityInfo> getCityInfoByProvinceId(String provinceId) throws ServiceException;

	/**
	 * 根据市编号查找城市信息
	 * @param cityCode
	 * @return
	 * @throws ServiceException
	 */
	CityInfo findByCityCode(String cityCode) throws ServiceException;

	/**
	 * 
	 * 
	 * 获取市所辖的地区和县
	 * 
	 * @param cityId
	 * @return
	 * @throws ServivceException
	 * 
	 */
	List<CityInfo> getRegionInfoByCityId(String cityId) throws ServiceException;

	/**
	 * 根据城市名称获取城市信息
	 * (一句话功能简述)
	 * (功能详细描述)
	 * @author       huangqingjian/0050
	 * @see          相关函数,对于重要的函数建议注释
	 * @since        清清网系统, 2014年9月15日
	 * @param cityName
	 * @return
	 * @throws ServiceException
	 */
	CityInfo findByCityName(String cityName) throws ServiceException;
}
