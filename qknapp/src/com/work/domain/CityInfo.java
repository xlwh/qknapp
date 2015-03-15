package com.work.domain;

// default package

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * CityInfo entity. @author MyEclipse Persistence Tools
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "T_CITY_INFO")
public class CityInfo implements java.io.Serializable {

	// Fields    

	private String cityId;
	private String provinceCode;
	private String cityCode;
	private String cityName;
	private String citySName;
	private String creator;
	private Timestamp createDate;
	private String modifier;
	private Timestamp modifyDate;
	private Integer version;

	// Constructors

	/** default constructor */
	public CityInfo() {
	}

	/** full constructor */
	public CityInfo(String provinceCode, String cityCode, String cityName, String citySName, String creator,
			Timestamp createDate, String modifier, Timestamp modifyDate, Integer version) {
		this.provinceCode = provinceCode;
		this.cityCode = cityCode;
		this.cityName = cityName;
		this.citySName = citySName;
		this.creator = creator;
		this.createDate = createDate;
		this.modifier = modifier;
		this.modifyDate = modifyDate;
		this.version = version;
	}

	// Property accessors
	@GenericGenerator(name = "generator", strategy = "uuid.hex")
	@Id
	@GeneratedValue(generator = "generator")
	@Column(name = "CITY_ID", unique = true, nullable = false, length = 32)
	public String getCityId() {
		return this.cityId;
	}

	public void setCityId(String cityId) {
		this.cityId = cityId;
	}

	@Column(name = "PROVINCE_CODE", length = 32)
	public String getProvinceCode() {
		return this.provinceCode;
	}

	public void setProvinceCode(String provinceCode) {
		this.provinceCode = provinceCode;
	}

	@Column(name = "CITY_CODE", length = 32)
	public String getCityCode() {
		return this.cityCode;
	}

	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}

	@Column(name = "CITY_NAME", length = 20)
	public String getCityName() {
		return this.cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	@Column(name = "CITY_S_NAME", length = 10)
	public String getCitySName() {
		return this.citySName;
	}

	public void setCitySName(String citySName) {
		this.citySName = citySName;
	}

	@Column(name = "CREATOR", length = 50)
	public String getCreator() {
		return this.creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	@Column(name = "CREATE_DATE", length = 19)
	public Timestamp getCreateDate() {
		return this.createDate;
	}

	public void setCreateDate(Timestamp createDate) {
		this.createDate = createDate;
	}

	@Column(name = "MODIFIER", length = 50)
	public String getModifier() {
		return this.modifier;
	}

	public void setModifier(String modifier) {
		this.modifier = modifier;
	}

	@Column(name = "MODIFY_DATE", length = 19)
	public Timestamp getModifyDate() {
		return this.modifyDate;
	}

	public void setModifyDate(Timestamp modifyDate) {
		this.modifyDate = modifyDate;
	}

	@Column(name = "VERSION")
	public Integer getVersion() {
		return this.version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

}