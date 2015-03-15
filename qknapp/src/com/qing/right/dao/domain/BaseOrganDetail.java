package com.qing.right.dao.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * TBaseOrganDetail entity. @author MyEclipse Persistence Tools
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "T_BASE_ORGAN_DETAIL")
public class BaseOrganDetail implements java.io.Serializable {

	// Fields

	private String id;

	private String organId;

	private String organFatherId;

	private String organFatherName;

	private String organName;

	private String status;

	private String ceo;

	private String cellPhone;

	private String telephone;

	private String fax;

	private String address;

	private String provinceCode;

	private String provinceName;

	private String cityCode;

	private String cityName;

	private String organQrCode;

	private String creator;

	private Date createDate;

	private String modifier;

	private Date modifyDate;

	private Integer version;

	// Constructors

	/** default constructor */
	public BaseOrganDetail() {
	}

	/** minimal constructor */
	public BaseOrganDetail(String organId) {
		this.organId = organId;
	}

	/** full constructor */
	public BaseOrganDetail(String organId, String organFatherId, String organFatherName, String organName,
			String status, String ceo, String cellPhone, String telephone, String fax, String address,
			String provinceCode, String provinceName, String cityCode, String cityName, String organQrCode,
			String creator, Date createDate, String modifier, Date modifyDate, Integer version) {
		this.organId = organId;
		this.organFatherId = organFatherId;
		this.organFatherName = organFatherName;
		this.organName = organName;
		this.status = status;
		this.ceo = ceo;
		this.cellPhone = cellPhone;
		this.telephone = telephone;
		this.fax = fax;
		this.address = address;
		this.provinceCode = provinceCode;
		this.provinceName = provinceName;
		this.cityCode = cityCode;
		this.cityName = cityName;
		this.organQrCode = organQrCode;
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
	@Column(name = "ID", unique = true, nullable = false, length = 32)
	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Column(name = "ORGAN_ID", nullable = false, length = 32)
	public String getOrganId() {
		return this.organId;
	}

	public void setOrganId(String organId) {
		this.organId = organId;
	}

	@Column(name = "ORGAN_FATHER_ID", length = 32)
	public String getOrganFatherId() {
		return this.organFatherId;
	}

	public void setOrganFatherId(String organFatherId) {
		this.organFatherId = organFatherId;
	}

	@Column(name = "ORGAN_FATHER_NAME", length = 100)
	public String getOrganFatherName() {
		return this.organFatherName;
	}

	public void setOrganFatherName(String organFatherName) {
		this.organFatherName = organFatherName;
	}

	@Column(name = "ORGAN_NAME", length = 100)
	public String getOrganName() {
		return this.organName;
	}

	public void setOrganName(String organName) {
		this.organName = organName;
	}

	@Column(name = "STATUS", length = 1)
	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Column(name = "CEO", length = 50)
	public String getCeo() {
		return this.ceo;
	}

	public void setCeo(String ceo) {
		this.ceo = ceo;
	}

	@Column(name = "CELL_PHONE", length = 20)
	public String getCellPhone() {
		return this.cellPhone;
	}

	public void setCellPhone(String cellPhone) {
		this.cellPhone = cellPhone;
	}

	@Column(name = "TELEPHONE", length = 10)
	public String getTelephone() {
		return this.telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	@Column(name = "FAX", length = 20)
	public String getFax() {
		return this.fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	@Column(name = "ADDRESS", length = 200)
	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Column(name = "PROVINCE_CODE", length = 20)
	public String getProvinceCode() {
		return this.provinceCode;
	}

	public void setProvinceCode(String provinceCode) {
		this.provinceCode = provinceCode;
	}

	@Column(name = "PROVINCE_NAME", length = 50)
	public String getProvinceName() {
		return this.provinceName;
	}

	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}

	@Column(name = "CITY_CODE", length = 20)
	public String getCityCode() {
		return this.cityCode;
	}

	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}

	@Column(name = "CITY_NAME", length = 50)
	public String getCityName() {
		return this.cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	@Column(name = "ORGAN_QR_CODE", length = 50)
	public String getOrganQrCode() {
		return this.organQrCode;
	}

	public void setOrganQrCode(String organQrCode) {
		this.organQrCode = organQrCode;
	}

	@Column(name = "CREATOR", length = 50)
	public String getCreator() {
		return this.creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	@Column(name = "CREATE_DATE", length = 0)
	public Date getCreateDate() {
		return this.createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	@Column(name = "MODIFIER", length = 50)
	public String getModifier() {
		return this.modifier;
	}

	public void setModifier(String modifier) {
		this.modifier = modifier;
	}

	@Column(name = "MODIFY_DATE", length = 0)
	public Date getModifyDate() {
		return this.modifyDate;
	}

	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}

	@Column(name = "VERSION")
	public Integer getVersion() {
		return this.version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	@Override
	public String toString() {
		return "BaseOrganDetail [id=" + id + ", organId=" + organId + ", organFatherId=" + organFatherId
				+ ", organFatherName=" + organFatherName + ", organName=" + organName + ", status=" + status + ", ceo="
				+ ceo + ", cellPhone=" + cellPhone + ", telephone=" + telephone + ", fax=" + fax + ", address="
				+ address + ", provinceCode=" + provinceCode + ", provinceName=" + provinceName + ", cityCode="
				+ cityCode + ", cityName=" + cityName + ", organQrCode=" + organQrCode + ", creator=" + creator
				+ ", createDate=" + createDate + ", modifier=" + modifier + ", modifyDate=" + modifyDate + ", version="
				+ version + "]";
	}

}