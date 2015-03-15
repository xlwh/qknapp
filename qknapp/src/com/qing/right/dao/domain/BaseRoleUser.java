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
 * BaseRoleUser entity. @author MyEclipse Persistence Tools
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "T_BASE_ROLE_USER")
public class BaseRoleUser implements java.io.Serializable {

    // Fields
    /**
     * 角色用户编号
     */
    private String roleUserId;

    /**
     * 版本号
     */
    private Integer version;

    /**
     * 用户ID
     */
    private BaseUser baseUser;

    /**
     * 角色ID
     */
    private BaseRole baseRole;

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
    public BaseRoleUser() {
    }

    /** minimal constructor */
    public BaseRoleUser(BaseUser baseUser, BaseRole baseRole) {
	this.baseUser = baseUser;
	this.baseRole = baseRole;
    }

    /** full constructor */
    public BaseRoleUser(BaseUser baseUser, BaseRole baseRole, Date createTime, String founder, String modifMan, Date modifTime) {
	this.baseUser = baseUser;
	this.baseRole = baseRole;
	this.createTime = createTime;
	this.founder = founder;
	this.modifMan = modifMan;
	this.modifTime = modifTime;
    }

    // Property accessors
    @GenericGenerator(name = "generator", strategy = "uuid.hex")
    @Id
    @GeneratedValue(generator = "generator")
    @Column(name = "ROLE_USER_ID", unique = true, nullable = false, length = 32)
    public String getRoleUserId() {
	return this.roleUserId;
    }

    public void setRoleUserId(String roleUserId) {
	this.roleUserId = roleUserId;
    }

    @Column(name = "VERSION")
    public Integer getVersion() {
	return this.version;
    }

    public void setVersion(Integer version) {
	this.version = version;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "USER_ID", nullable = false)
    public BaseUser getBaseUser() {
	return this.baseUser;
    }

    public void setBaseUser(BaseUser baseUser) {
	this.baseUser = baseUser;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ROLE_ID", nullable = false)
    public BaseRole getBaseRole() {
	return this.baseRole;
    }

    public void setBaseRole(BaseRole baseRole) {
	this.baseRole = baseRole;
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