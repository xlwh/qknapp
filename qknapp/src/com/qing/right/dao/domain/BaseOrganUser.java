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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * BaseOrganUser entity. @author MyEclipse Persistence Tools
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "T_BASE_ORGAN_USER")
public class BaseOrganUser implements java.io.Serializable {

    // Fields
    /**
     * 管理员编号
     */
    private String organUserId;

    /**
     * 版本号
     */
    private Integer version;

    /**
     * 用户ID
     */
    private BaseUser baseUser;

    /**
     * 组织ID
     */
    private BaseOrgan baseOrgan;

    /**
     * 管理员层级 1=主管理员 2=辅助管理员
     */
    private String adminLevel;

    /**
     * 建立时间
     */
    private Date createTime;

    /**
     * 建立人
     */
    private String founder;

    /**
     * 是否管理 N=非管理员 Y=管理员
     */
    private String isAdmin;

    /**
     * 修改人
     */
    private String modifMan;

    /**
     * 修改时间
     */
    private Date modifTime;
    private Set<BaseOrganUserPermit> baseOrganUserPermits = new HashSet<BaseOrganUserPermit>(0);

    // Constructors

    /** default constructor */
    public BaseOrganUser() {
    }

    /** full constructor */
    public BaseOrganUser(BaseUser baseUser, BaseOrgan baseOrgan, String adminLevel, Date createTime, String founder, String isAdmin,
	    String modifMan, Date modifTime, Set<BaseOrganUserPermit> baseOrganUserPermits) {
	this.baseUser = baseUser;
	this.baseOrgan = baseOrgan;
	this.adminLevel = adminLevel;
	this.createTime = createTime;
	this.founder = founder;
	this.isAdmin = isAdmin;
	this.modifMan = modifMan;
	this.modifTime = modifTime;
	this.baseOrganUserPermits = baseOrganUserPermits;
    }

    // Property accessors
    @GenericGenerator(name = "generator", strategy = "uuid.hex")
    @Id
    @GeneratedValue(generator = "generator")
    @Column(name = "ORGAN_USER_ID", unique = true, nullable = false, length = 32)
    public String getOrganUserId() {
	return this.organUserId;
    }

    public void setOrganUserId(String organUserId) {
	this.organUserId = organUserId;
    }

    @Column(name = "VERSION")
    public Integer getVersion() {
	return this.version;
    }

    public void setVersion(Integer version) {
	this.version = version;
    }

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "USER_ID")
    public BaseUser getBaseUser() {
	return this.baseUser;
    }

    public void setBaseUser(BaseUser baseUser) {
	this.baseUser = baseUser;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ORGAN_ID")
    public BaseOrgan getBaseOrgan() {
	return this.baseOrgan;
    }

    public void setBaseOrgan(BaseOrgan baseOrgan) {
	this.baseOrgan = baseOrgan;
    }

    @Column(name = "ADMIN_LEVEL", length = 1)
    public String getAdminLevel() {
	return this.adminLevel;
    }

    public void setAdminLevel(String adminLevel) {
	this.adminLevel = adminLevel;
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

    @Column(name = "IS_ADMIN", length = 1)
    public String getIsAdmin() {
	return this.isAdmin;
    }

    public void setIsAdmin(String isAdmin) {
	this.isAdmin = isAdmin;
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

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "baseOrganUser")
    public Set<BaseOrganUserPermit> getBaseOrganUserPermits() {
	return this.baseOrganUserPermits;
    }

    public void setBaseOrganUserPermits(Set<BaseOrganUserPermit> baseOrganUserPermits) {
	this.baseOrganUserPermits = baseOrganUserPermits;
    }

}