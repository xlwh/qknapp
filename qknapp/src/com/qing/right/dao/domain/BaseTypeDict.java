package com.qing.right.dao.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * BaseTypeDict entity. @author MyEclipse Persistence Tools
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "T_BASE_TYPE_DICT")
public class BaseTypeDict implements java.io.Serializable {

    // Fields
    /**
     * 字典编号
     */
    private String dictId;

    /**
     * 版本号
     */
    private Integer version;

    /**
     * 建立时间
     */
    private Date createTime;

    /**
     * 归属表名
     */
    private String dictOfDb;

    /**
     * 归属字段
     */
    private String dictOfField;

    /**
     * 归属组织（到企事业单位分部级别）
     */
    private String dictOfOrganId;

    /**
     * 字典开放级别 1、表示开发人员专用 2、开放给系统维护人员
     */
    private String dictOpen;

    /**
     * 父字典ID
     */
    private String dictParentId;

    /**
     * 类型编号
     */
    private Integer dictTypeCode;

    /**
     * 类型名称
     */
    private String dictTypeName;

    /**
     * 建立人
     */
    private String founder;

    /**
     * 修改人
     */
    private String modifMan;

    /**
     * 修改时间
     */
    private Date modifTime;

    // Constructors

    /** default constructor */
    public BaseTypeDict() {
    }

    /** full constructor */
    public BaseTypeDict(Date createTime, String dictOfDb, String dictOfField, String dictOfOrganId, String dictOpen, String dictParentId,
	    Integer dictTypeCode, String dictTypeName, String founder, String modifMan, Date modifTime) {
	this.createTime = createTime;
	this.dictOfDb = dictOfDb;
	this.dictOfField = dictOfField;
	this.dictOfOrganId = dictOfOrganId;
	this.dictOpen = dictOpen;
	this.dictParentId = dictParentId;
	this.dictTypeCode = dictTypeCode;
	this.dictTypeName = dictTypeName;
	this.founder = founder;
	this.modifMan = modifMan;
	this.modifTime = modifTime;
    }

    // Property accessors
    @GenericGenerator(name = "generator", strategy = "uuid.hex")
    @Id
    @GeneratedValue(generator = "generator")
    @Column(name = "DICT_ID", unique = true, nullable = false, length = 32)
    public String getDictId() {
	return this.dictId;
    }

    public void setDictId(String dictId) {
	this.dictId = dictId;
    }

    @Column(name = "VERSION")
    public Integer getVersion() {
	return this.version;
    }

    public void setVersion(Integer version) {
	this.version = version;
    }

    @Column(name = "CREATE_TIME", length = 23)
    public Date getCreateTime() {
	return this.createTime;
    }

    public void setCreateTime(Date createTime) {
	this.createTime = createTime;
    }

    @Column(name = "DICT_OF_DB")
    public String getDictOfDb() {
	return this.dictOfDb;
    }

    public void setDictOfDb(String dictOfDb) {
	this.dictOfDb = dictOfDb;
    }

    @Column(name = "DICT_OF_FIELD", length = 50)
    public String getDictOfField() {
	return this.dictOfField;
    }

    public void setDictOfField(String dictOfField) {
	this.dictOfField = dictOfField;
    }

    @Column(name = "DICT_OF_ORGAN_ID")
    public String getDictOfOrganId() {
	return this.dictOfOrganId;
    }

    public void setDictOfOrganId(String dictOfOrganId) {
	this.dictOfOrganId = dictOfOrganId;
    }

    @Column(name = "DICT_OPEN", length = 1)
    public String getDictOpen() {
	return this.dictOpen;
    }

    public void setDictOpen(String dictOpen) {
	this.dictOpen = dictOpen;
    }

    @Column(name = "DICT_PARENT_ID")
    public String getDictParentId() {
	return this.dictParentId;
    }

    public void setDictParentId(String dictParentId) {
	this.dictParentId = dictParentId;
    }

    @Column(name = "DICT_TYPE_CODE")
    public Integer getDictTypeCode() {
	return this.dictTypeCode;
    }

    public void setDictTypeCode(Integer dictTypeCode) {
	this.dictTypeCode = dictTypeCode;
    }

    @Column(name = "DICT_TYPE_NAME", length = 50)
    public String getDictTypeName() {
	return this.dictTypeName;
    }

    public void setDictTypeName(String dictTypeName) {
	this.dictTypeName = dictTypeName;
    }

    @Column(name = "FOUNDER", length = 20)
    public String getFounder() {
	return this.founder;
    }

    public void setFounder(String founder) {
	this.founder = founder;
    }

    @Column(name = "MODIF_MAN", length = 20)
    public String getModifMan() {
	return this.modifMan;
    }

    public void setModifMan(String modifMan) {
	this.modifMan = modifMan;
    }

    @Column(name = "MODIF_TIME", length = 23)
    public Date getModifTime() {
	return this.modifTime;
    }

    public void setModifTime(Date modifTime) {
	this.modifTime = modifTime;
    }

}