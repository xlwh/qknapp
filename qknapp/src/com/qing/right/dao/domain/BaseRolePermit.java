package com.qing.right.dao.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * BaseRolePermit entity. @author MyEclipse Persistence Tools
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "T_BASE_ROLE_PERMIT")
public class BaseRolePermit implements java.io.Serializable {

    // Fields
    /**
     * 编号
     */
    private String rolePermitId;

    /**
     * 版本号
     */
    private Integer version;

    /**
     * 角色ID
     */
    private BaseRole baseRole;

    /**
     * 权限ID
     */
    private BasePermit basePermit;

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

    // Constructors

    /** default constructor */
    public BaseRolePermit() {
    }

    /** full constructor */
    public BaseRolePermit(BaseRole baseRole, BasePermit basePermit, Date createTime, String founder, String modifMan, Date modifTime) {
	this.baseRole = baseRole;
	this.basePermit = basePermit;
	this.createTime = createTime;
	this.founder = founder;
	this.modifMan = modifMan;
	this.modifTime = modifTime;
    }

    // Property accessors
    @GenericGenerator(name = "generator", strategy = "uuid.hex")
    @Id
    @GeneratedValue(generator = "generator")
    @Column(name = "ROLE_PERMIT_ID", unique = true, nullable = false, length = 32)
    public String getRolePermitId() {
	return this.rolePermitId;
    }

    public void setRolePermitId(String rolePermitId) {
	this.rolePermitId = rolePermitId;
    }

    @Column(name = "VERSION")
    public Integer getVersion() {
	return this.version;
    }

    public void setVersion(Integer version) {
	this.version = version;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ROLE_ID")
    public BaseRole getBaseRole() {
	return this.baseRole;
    }

    public void setBaseRole(BaseRole baseRole) {
	this.baseRole = baseRole;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "PERMIT_ID")
    public BasePermit getBasePermit() {
	return this.basePermit;
    }

    public void setBasePermit(BasePermit basePermit) {
	this.basePermit = basePermit;
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

}