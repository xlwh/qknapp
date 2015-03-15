package com.qing.right.dao.domain;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * BaseModule entity. @author MyEclipse Persistence Tools
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "T_BASE_MODULE")
public class BaseModule implements java.io.Serializable {

    // Fields
    /**
     * 模块编号
     */
    private String moduleId;

    /**
     * 版本号
     */
    private Integer version;

    /**
     * 建立时间
     */
    private Date createTime;

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

    /**
     * 模块描述
     */
    private String moduleDesc;

    /**
     * 模块分级号 由系统算法自动产生，更新时重算
     */
    private String moduleLev;

    /**
     * 模块名称
     */
    private String moduleName;

    /**
     * 父模块ID
     */
    private String moduleParentId;

    /**
     * 模块类型 1=提供给超级管理员使用的模块 0=用与权限设置加载的模块 5=手持机模块
     */
    private String moduleType;

    /**
     * 系统路径
     */
    private String url;
    private Set<BasePermit> basePermits = new HashSet<BasePermit>(0);

    // Constructors

    /** default constructor */
    public BaseModule() {
    }

    /** minimal constructor */
    public BaseModule(String moduleName) {
	this.moduleName = moduleName;
    }

    /** full constructor */
    public BaseModule(Date createTime, String founder, String modifMan, Date modifTime, String moduleDesc, String moduleLev,
	    String moduleName, String moduleParentId, String moduleType, String url, Set<BasePermit> basePermits) {
	this.createTime = createTime;
	this.founder = founder;
	this.modifMan = modifMan;
	this.modifTime = modifTime;
	this.moduleDesc = moduleDesc;
	this.moduleLev = moduleLev;
	this.moduleName = moduleName;
	this.moduleParentId = moduleParentId;
	this.moduleType = moduleType;
	this.url = url;
	this.basePermits = basePermits;
    }

    // Property accessors
    @GenericGenerator(name = "generator", strategy = "uuid.hex")
    @Id
    @GeneratedValue(generator = "generator")
    @Column(name = "MODULE_ID", unique = true, nullable = false, length = 32)
    public String getModuleId() {
	return this.moduleId;
    }

    public void setModuleId(String moduleId) {
	this.moduleId = moduleId;
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

    @Column(name = "MODULE_DESC", length = 50)
    public String getModuleDesc() {
	return this.moduleDesc;
    }

    public void setModuleDesc(String moduleDesc) {
	this.moduleDesc = moduleDesc;
    }

    @Column(name = "MODULE_LEV", length = 50)
    public String getModuleLev() {
	return this.moduleLev;
    }

    public void setModuleLev(String moduleLev) {
	this.moduleLev = moduleLev;
    }

    @Column(name = "MODULE_NAME", nullable = false, length = 50)
    public String getModuleName() {
	return this.moduleName;
    }

    public void setModuleName(String moduleName) {
	this.moduleName = moduleName;
    }

    @Column(name = "MODULE_PARENT_ID", length = 32)
    public String getModuleParentId() {
	return this.moduleParentId;
    }

    public void setModuleParentId(String moduleParentId) {
	this.moduleParentId = moduleParentId;
    }

    @Column(name = "MODULE_TYPE", length = 1)
    public String getModuleType() {
	return this.moduleType;
    }

    public void setModuleType(String moduleType) {
	this.moduleType = moduleType;
    }

    @Column(name = "URL", length = 100)
    public String getUrl() {
	return this.url;
    }

    public void setUrl(String url) {
	this.url = url;
    }

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "baseModule")
    public Set<BasePermit> getBasePermits() {
	return this.basePermits;
    }

    public void setBasePermits(Set<BasePermit> basePermits) {
	this.basePermits = basePermits;
    }

}