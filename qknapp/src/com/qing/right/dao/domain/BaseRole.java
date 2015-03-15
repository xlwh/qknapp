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
 * BaseRole entity. @author MyEclipse Persistence Tools
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "T_BASE_ROLE")
public class BaseRole implements java.io.Serializable {

    // Fields
    /**
     * 角色编号
     */
    private String roleId;

    /**
     * 版本号
     */
    private Integer version;

    /**
     * 组织编号
     */
    private BaseOrgan baseOrgan;

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
     * 启/停用生效标识 Y/N
     */
    private String okFlag;

    /**
     * 角色描述
     */
    private String roleDesc;

    /**
     * 角色名称
     */
    private String roleName;

    /**
     * 角色类型 1=管理员级 2=系统用户 3=非系统用户 该字段对应同类型的数据字典表BasTypeDict
     */
    private String roleType;
    private Set<BaseRoleUser> baseRoleUsers = new HashSet<BaseRoleUser>(0);
    private Set<BaseRolePermit> baseRolePermits = new HashSet<BaseRolePermit>(0);

    // Constructors

    /** default constructor */
    public BaseRole() {
    }

    /** minimal constructor */
    public BaseRole(BaseOrgan baseOrgan, String roleName) {
	this.baseOrgan = baseOrgan;
	this.roleName = roleName;
    }

    /** full constructor */
    public BaseRole(BaseOrgan baseOrgan, Date createTime, String founder, String modifMan, Date modifTime, String okFlag, String roleDesc,
	    String roleName, String roleType, Set<BaseRoleUser> baseRoleUsers, Set<BaseRolePermit> baseRolePermits) {
	this.baseOrgan = baseOrgan;
	this.createTime = createTime;
	this.founder = founder;
	this.modifMan = modifMan;
	this.modifTime = modifTime;
	this.okFlag = okFlag;
	this.roleDesc = roleDesc;
	this.roleName = roleName;
	this.roleType = roleType;
	this.baseRoleUsers = baseRoleUsers;
	this.baseRolePermits = baseRolePermits;
    }

    // Property accessors
    @GenericGenerator(name = "generator", strategy = "uuid.hex")
    @Id
    @GeneratedValue(generator = "generator")
    @Column(name = "ROLE_ID", unique = true, nullable = false, length = 32)
    public String getRoleId() {
	return this.roleId;
    }

    public void setRoleId(String roleId) {
	this.roleId = roleId;
    }

    @Column(name = "VERSION")
    public Integer getVersion() {
	return this.version;
    }

    public void setVersion(Integer version) {
	this.version = version;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ORGAN_ID", nullable = false)
    public BaseOrgan getBaseOrgan() {
	return this.baseOrgan;
    }

    public void setBaseOrgan(BaseOrgan baseOrgan) {
	this.baseOrgan = baseOrgan;
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

    @Column(name = "OK_FLAG", length = 1)
    public String getOkFlag() {
	return this.okFlag;
    }

    public void setOkFlag(String okFlag) {
	this.okFlag = okFlag;
    }

    @Column(name = "ROLE_DESC", length = 100)
    public String getRoleDesc() {
	return this.roleDesc;
    }

    public void setRoleDesc(String roleDesc) {
	this.roleDesc = roleDesc;
    }

    @Column(name = "ROLE_NAME", nullable = false, length = 50)
    public String getRoleName() {
	return this.roleName;
    }

    public void setRoleName(String roleName) {
	this.roleName = roleName;
    }

    @Column(name = "ROLE_TYPE", length = 1)
    public String getRoleType() {
	return this.roleType;
    }

    public void setRoleType(String roleType) {
	this.roleType = roleType;
    }

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "baseRole")
    public Set<BaseRoleUser> getBaseRoleUsers() {
	return this.baseRoleUsers;
    }

    public void setBaseRoleUsers(Set<BaseRoleUser> baseRoleUsers) {
	this.baseRoleUsers = baseRoleUsers;
    }

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "baseRole")
    public Set<BaseRolePermit> getBaseRolePermits() {
	return this.baseRolePermits;
    }

    public void setBaseRolePermits(Set<BaseRolePermit> baseRolePermits) {
	this.baseRolePermits = baseRolePermits;
    }

}