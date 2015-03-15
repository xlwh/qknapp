package com.qing.right.dao.domain;

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
 * TBaseDuties entity. @author MyEclipse Persistence Tools
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "T_BASE_DUTIES")
public class BaseDuties implements java.io.Serializable {

    /**
     * 职务ID
     */
    private String dutieid;
    /**
     * 职务所属组织
     */
    private BaseOrgan baseOrgan;

    /**
     * 职务名称
     */
    private String dutiename;

    /**
     * 职务描述
     */
    private String dutiedesc;

    /**
     * 管理了此职务的用户
     */
    private Set<BaseUser> baseUsers = new HashSet<BaseUser>(0);

    // Constructors

    /** default constructor */
    public BaseDuties() {
    }

    /** minimal constructor */
    public BaseDuties(String dutieid, BaseOrgan BaseOrgan, String dutiename) {
	this.dutieid = dutieid;
	this.baseOrgan = BaseOrgan;
	this.dutiename = dutiename;
    }

    /** full constructor */
    public BaseDuties(String dutieid, BaseOrgan BaseOrgan, String dutiename, String dutiedesc, Set<BaseUser> BaseUsers) {
	this.dutieid = dutieid;
	this.baseOrgan = BaseOrgan;
	this.dutiename = dutiename;
	this.dutiedesc = dutiedesc;
	this.baseUsers = BaseUsers;
    }

    @GenericGenerator(name = "generator", strategy = "uuid.hex")
    @Id
    @GeneratedValue(generator = "generator")
    @Column(name = "DUTIEID", unique = true, nullable = false, length = 32)
    public String getDutieid() {
	return this.dutieid;
    }

    public void setDutieid(String dutieid) {
	this.dutieid = dutieid;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ORGANID", nullable = false)
    public BaseOrgan getBaseOrgan() {
	return this.baseOrgan;
    }

    public void setBaseOrgan(BaseOrgan BaseOrgan) {
	this.baseOrgan = BaseOrgan;
    }

    @Column(name = "DUTIENAME", nullable = false, length = 20)
    public String getDutiename() {
	return this.dutiename;
    }

    public void setDutiename(String dutiename) {
	this.dutiename = dutiename;
    }

    @Column(name = "DUTIEDESC", length = 50)
    public String getDutiedesc() {
	return this.dutiedesc;
    }

    public void setDutiedesc(String dutiedesc) {
	this.dutiedesc = dutiedesc;
    }

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "baseDuties")
    public Set<BaseUser> getBaseUsers() {
	return this.baseUsers;
    }

    public void setBaseUsers(Set<BaseUser> baseUsers) {
	this.baseUsers = baseUsers;
    }

}