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
 * BaseOrganUserPermit entity. @author MyEclipse Persistence Tools
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "T_BASE_ORGAN_USER_PERMIT")
public class BaseOrganUserPermit implements java.io.Serializable {

    // Fields
    /**
     * 管理员权限编号
     */
    private String organUserPermitId;

    /**
     * 版本号
     */
    private Integer version;

    /**
     * 管理员编号
     */
    private BaseOrganUser baseOrganUser;

    /**
     * 权限编号
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
    public BaseOrganUserPermit() {
    }

    /** full constructor */
    public BaseOrganUserPermit(BaseOrganUser baseOrganUser, BasePermit basePermit, Date createTime, String founder, String modifMan,
	    Date modifTime) {
	this.baseOrganUser = baseOrganUser;
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
    @Column(name = "ORGAN_USER_PERMIT_ID", unique = true, nullable = false, length = 32)
    public String getOrganUserPermitId() {
	return this.organUserPermitId;
    }

    public void setOrganUserPermitId(String organUserPermitId) {
	this.organUserPermitId = organUserPermitId;
    }

    @Column(name = "VERSION")
    public Integer getVersion() {
	return this.version;
    }

    public void setVersion(Integer version) {
	this.version = version;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ORGAN_USER_ID")
    public BaseOrganUser getBaseOrganUser() {
	return this.baseOrganUser;
    }

    public void setBaseOrganUser(BaseOrganUser baseOrganUser) {
	this.baseOrganUser = baseOrganUser;
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