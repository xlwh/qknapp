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
 * ProvinceInfo entity. @author MyEclipse Persistence Tools
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "T_PROVINCE_INFO")
public class ProvinceInfo implements java.io.Serializable {

	// Fields    

	private String provinceId;
	private String provinceCode;
	private String provinceName;
	private String provinceSName;
	private String creator;
	private Timestamp createDate;
	private String modifier;
	private Timestamp modifyDate;
	private Integer version;

	// Constructors

	/** default constructor */
	public ProvinceInfo() {
	}

	/** full constructor */
	public ProvinceInfo(String provinceCode, String provinceName, String provinceSName, String creator,
			Timestamp createDate, String modifier, Timestamp modifyDate, Integer version) {
		this.provinceCode = provinceCode;
		this.provinceName = provinceName;
		this.provinceSName = provinceSName;
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
	@Column(name = "PROVINCE_ID", unique = true, nullable = false, length = 32)
	public String getProvinceId() {
		return this.provinceId;
	}

	public void setProvinceId(String provinceId) {
		this.provinceId = provinceId;
	}

	@Column(name = "PROVINCE_CODE", length = 32)
	public String getProvinceCode() {
		return this.provinceCode;
	}

	public void setProvinceCode(String provinceCode) {
		this.provinceCode = provinceCode;
	}

	@Column(name = "PROVINCE_NAME", length = 10)
	public String getProvinceName() {
		return this.provinceName;
	}

	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}

	@Column(name = "PROVINCE_S_NAME", length = 10)
	public String getProvinceSName() {
		return this.provinceSName;
	}

	public void setProvinceSName(String provinceSName) {
		this.provinceSName = provinceSName;
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