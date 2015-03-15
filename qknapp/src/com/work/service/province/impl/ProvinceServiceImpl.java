package com.work.service.province.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.qing.common.exception.DaoException;
import com.qing.common.exception.ServiceException;
import com.qing.common.exception.handle.ExceptionHandle;
import com.qing.common.util.ErrorCode;
import com.work.dao.province.IProvinceInfoDao;
import com.work.domain.ProvinceInfo;
import com.work.service.province.IProvinceInfoService;

/**
 * 省份业务处理类
 * 
 * @author luoqinglong
 * @date 2013-12-16 下午5:50:11
 * @since 1.0
 **/
@Service
public class ProvinceServiceImpl implements IProvinceInfoService {
	@Resource
	IProvinceInfoDao provinceInfoDaoImpl;

	/**
	 * 获取全部省份
	 * 
	 * @return
	 * @throws ServiceException
	 * @see com.mzo.service.IProvinceInfoService#getAllProvinceInfo()
	 **/
	@Override
	public List<ProvinceInfo> getAllProvinceInfo() throws ServiceException {
		List<ProvinceInfo> list = new ArrayList<ProvinceInfo>();
		try {
			list = provinceInfoDaoImpl.getAllProvinceInfo();
		} catch (DaoException e) {
			ExceptionHandle.throwServiceException(e, ErrorCode.SERVICE_QUERY_ERROR_004,
					"ProvinceServiceImpl.getAllProvinceInfo()");
		}
		return list;

	}

	@Override
	public ProvinceInfo findByProvinceCode(String provinceCode) throws ServiceException {
		ProvinceInfo provinceInfo = new ProvinceInfo();
		try {
			provinceInfo = provinceInfoDaoImpl.findByProvinceCode(provinceCode);
		} catch (DaoException e) {
			ExceptionHandle.throwServiceException(e, ErrorCode.SERVICE_QUERY_ERROR_004,
					"ProvinceServiceImpl.findByProvinceCode()");
		}
		return provinceInfo;
	}

	@Override
	public ProvinceInfo findByProvinceName(String provinceName) throws ServiceException {
		ProvinceInfo provinceInfo = new ProvinceInfo();
		try {
			provinceInfo = provinceInfoDaoImpl.findByProvinceName(provinceName);
		} catch (DaoException e) {
			ExceptionHandle.throwServiceException(e, ErrorCode.SERVICE_QUERY_ERROR_004,
					"ProvinceServiceImpl.findByProvinceCode()");
		}
		return provinceInfo;
	}
}
