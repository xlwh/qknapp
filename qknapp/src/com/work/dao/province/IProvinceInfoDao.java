package com.work.dao.province;

import java.util.List;

import com.qing.common.exception.DaoException;
import com.work.domain.ProvinceInfo;

/**
 * 省份字典
 * 
 * @author luoqinglong
 * @date 2013-12-16 下午5:21:46
 * @since 1.0
 **/
public interface IProvinceInfoDao {
	/**
	 * 获取所有的省份
	 * 
	 * @return
	 * @throws DaoException
	 * 
	 */
	public List<ProvinceInfo> getAllProvinceInfo() throws DaoException;

	/**
	 * 根据省编号查找省信息
	 * @param provinceCode
	 * @return
	 * @throws DaoException
	 */
	public ProvinceInfo findByProvinceCode(String provinceCode) throws DaoException;

	/**
	 * 根据省名称查找省信息
	 * @param provinceName
	 * @return
	 * @throws DaoException
	 */
	public ProvinceInfo findByProvinceName(String provinceName) throws DaoException;

}
