package com.work.service.province;

import java.util.List;

import com.qing.common.exception.ServiceException;
import com.work.domain.ProvinceInfo;

/**
 * 省份业务处理类
 * 
 * @author luoqinglong
 * @date 2013-12-16 下午5:49:06
 * @since 1.0
 **/
public interface IProvinceInfoService {
	/**
	 * 
	 * 获取全部省份
	 * 
	 * @return
	 * @throws ServiceException
	 * 
	 */
	public List<ProvinceInfo> getAllProvinceInfo() throws ServiceException;

	/**
	 * 根据省编号查找省信息
	 * @param provinceCode
	 * @return
	 * @throws ServiceException
	 */
	public ProvinceInfo findByProvinceCode(String provinceCode) throws ServiceException;

	/**
	 * 根据省名称查找省信息
	 * @param provinceName
	 * @return
	 * @throws ServiceException
	 */
	public ProvinceInfo findByProvinceName(String provinceName) throws ServiceException;

}
