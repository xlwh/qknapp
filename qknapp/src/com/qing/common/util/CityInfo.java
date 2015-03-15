/*
 * Title:        清清网系统 2014年8月23日
 * Description:  城市信息的封装
 * Copyright:    Copyright (c) 2014
 * Company:      清清网
 * @author       zhanghongbin
 * @version      2.0  2014年8月23日
 */
package com.qing.common.util;

/**
 * (一句话功能简述)
 * (功能详细描述)
 * @author       zhanghongbin
 * @see          [相关类,可选,也可多条,对于重要的类或接口建议注释]
 * @since        清清网系统, 2014年8月23日
 */
public class CityInfo {
	private String city; //城市名
	private String district; //区县名
	private String province; //省名
	private String street; //街道名
	private String street_number; //街道门牌号
    private String formatted_address;  //位置
	/**
	 * @return Returns the city.
	 */
	public String getCity() {
		return city;
	}

	/**
	 * @param city The city to set.
	 */
	public void setCity(String city) {
		this.city = city;
	}

	/**
	 * @return Returns the district.
	 */
	public String getDistrict() {
		return district;
	}

	/**
	 * @param district The district to set.
	 */
	public void setDistrict(String district) {
		this.district = district;
	}

	/**
	 * @return Returns the province.
	 */
	public String getProvince() {
		return province;
	}

	/**
	 * @param province The province to set.
	 */
	public void setProvince(String province) {
		this.province = province;
	}

	/**
	 * @return Returns the street.
	 */
	public String getStreet() {
		return street;
	}

	/**
	 * @param street The street to set.
	 */
	public void setStreet(String street) {
		this.street = street;
	}

	/**
	 * @return Returns the street_number.
	 */
	public String getStreet_number() {
		return street_number;
	}

	/**
	 * @param street_number The street_number to set.
	 */
	public void setStreet_number(String street_number) {
		this.street_number = street_number;
	}

	/**
	 * @return Returns the formatted_address.
	 */
	public String getFormatted_address() {
		return formatted_address;
	}

	/**
	 * @param formatted_address The formatted_address to set.
	 */
	public void setFormatted_address(String formatted_address) {
		this.formatted_address = formatted_address;
	}
	
	

}
