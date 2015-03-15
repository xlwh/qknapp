package com.work.service.province.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.qing.common.exception.DaoException;
import com.qing.common.exception.ServiceException;
import com.qing.common.exception.handle.ExceptionHandle;
import com.qing.common.util.ErrorCode;
import com.qing.common.util.StringUtil;
import com.work.dao.province.ICityInfoDao;
import com.work.domain.CityInfo;
import com.work.service.province.ICityInfoService;

/**
 * 城市地区业务类
 * 
 * @author luoqinglong
 * @date 2013-12-16 下午5:50:27
 * @since 1.0
 **/
@Service
public class CityInfoServiceImpl implements ICityInfoService {
	@Resource
	ICityInfoDao cityInfoDaoImpl;

	/**
	 * 获取省所辖的市区
	 * 
	 * @param provinceId
	 * @return
	 * @throws ServiceException
	 * @see com.mzo.service.ICityInfoService#getCityInfoByProvinceId(java.lang.String)
	 **/
	@Override
	public List<CityInfo> getCityInfoByProvinceId(String provinceId) throws ServiceException {
		List<CityInfo> list = new ArrayList<CityInfo>();
		try {
			list = cityInfoDaoImpl.getCityInfoByProvinceId(provinceId);
		} catch (DaoException e) {
			ExceptionHandle.throwServiceException(e, ErrorCode.SERVICE_QUERY_ERROR_004,
					"CityInfoServiceImpl.getCityInfoByProvinceId()");
		}
		return list;

	}

	/**
	 * 获取市所辖的地区和县
	 * 
	 * @param cityId
	 * @return
	 * @throws ServiceException
	 * @see com.mzo.service.ICityInfoService#getRegionInfoByCityId(java.lang.String)
	 **/
	@Override
	public List<CityInfo> getRegionInfoByCityId(String cityId) throws ServiceException {
		List<CityInfo> list = new ArrayList<CityInfo>();
		try {
			list = cityInfoDaoImpl.getRegionInfoByCityId(cityId);
		} catch (DaoException e) {
			ExceptionHandle.throwServiceException(e, ErrorCode.SERVICE_QUERY_ERROR_004,
					"CityInfoServiceImpl.getRegionInfoByCityId()");

		}
		return list;

	}

	@Override
	public CityInfo findByCityCode(String cityCode) throws ServiceException {
		CityInfo cityInfo = new CityInfo();
		try {
			cityInfo = cityInfoDaoImpl.findByCityCode(cityCode);
		} catch (DaoException e) {
			ExceptionHandle.throwServiceException(e, ErrorCode.SERVICE_QUERY_ERROR_004,
					"CityInfoServiceImpl.findByCityCode()");
		}
		return cityInfo;
	}

	@Override
	public CityInfo findByCityName(String cityName) throws ServiceException {
		if (!StringUtil.isValidStr(cityName)) {
			return null;
		}
		CityInfo cityInfo = new CityInfo();
		try {
			cityInfo = cityInfoDaoImpl.findByCityName(cityName);
		} catch (DaoException e) {
			ExceptionHandle.throwServiceException(e, ErrorCode.SERVICE_QUERY_ERROR_004,
					"CityInfoServiceImpl.findByCityCode()");
		}
		return cityInfo;
	}
}
