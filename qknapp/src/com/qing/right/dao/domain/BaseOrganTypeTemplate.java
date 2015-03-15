package com.qing.right.dao.domain;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * TBaseOrganTypeTemplate entity. @author MyEclipse Persistence Tools
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "T_BASE_ORGAN_TYPE_TEMPLATE")
public class BaseOrganTypeTemplate implements java.io.Serializable {

    // Fields

    private String typeId;
    private String fatherTypeId;
    private String typeLevel;
    private String typeValue;
    private String typeName;
    private String creator;
    private Timestamp createDate;
    private String modifier;
    private Timestamp modifyDate;
    private Integer version;

    // Constructors

    /** default constructor */
    public BaseOrganTypeTemplate() {
    }

    /** minimal constructor */
    public BaseOrganTypeTemplate(String typeId) {
	this.typeId = typeId;
    }

    /** full constructor */
    public BaseOrganTypeTemplate(String typeId, String fatherTypeId, String typeLevel, String typeValue, String typeName, String creator,
	    Timestamp createDate, String modifier, Timestamp modifyDate, Integer version) {
	this.typeId = typeId;
	this.fatherTypeId = fatherTypeId;
	this.typeLevel = typeLevel;
	this.typeValue = typeValue;
	this.typeName = typeName;
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
    @Column(name = "TYPE_ID", unique = true, nullable = false, length = 32)
    public String getTypeId() {
	return this.typeId;
    }

    public void setTypeId(String typeId) {
	this.typeId = typeId;
    }

    @Column(name = "FATHER_TYPE_ID", length = 32)
    public String getFatherTypeId() {
	return this.fatherTypeId;
    }

    public void setFatherTypeId(String fatherTypeId) {
	this.fatherTypeId = fatherTypeId;
    }

    @Column(name = "TYPE_LEVEL", length = 50)
    public String getTypeLevel() {
	return this.typeLevel;
    }

    public void setTypeLevel(String typeLevel) {
	this.typeLevel = typeLevel;
    }

    @Column(name = "TYPE_VALUE", length = 3)
    public String getTypeValue() {
	return this.typeValue;
    }

    public void setTypeValue(String typeValue) {
	this.typeValue = typeValue;
    }

    @Column(name = "TYPE_NAME", length = 300)
    public String getTypeName() {
	return this.typeName;
    }

    public void setTypeName(String typeName) {
	this.typeName = typeName;
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