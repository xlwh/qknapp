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
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;

/**
 * BaseUser entity. @author MyEclipse Persistence Tools
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "T_BASE_USER")
public class BaseUser implements java.io.Serializable {

    // Fields
    /**
     * 用户编号
     */
    private String userId;

    /**
     * 版本控制(乐观锁)
     */
    private Integer version;

    private BaseOrgan baseOrgan;

    /**
     * 用户的职务
     */
    private BaseDuties baseDuties;
    /**
     * 激活码
     */
    private String activeCode;

    /**
     * 地址
     */
    private String addr;

    /**
     * 建立时间
     */
    private Date createTime;

    /**
     * 电子邮箱
     */
    private String email;

    /**
     * 员工编号
     */
    private String employeeId;

    /**
     * 传真
     */
    private String fax;

    /**
     * 建立人
     */
    private String founder;

    /**
     * 最后登陆时间
     */
    private Date lastLoginActive;

    /**
     * 系统登录名
     */
    private String loginCode;

    /**
     * 最后登陆IP地址
     */
    private String loginIp;

    /**
     * 修改人
     */
    private String modifMan;

    /**
     * 修改时间
     */
    private Date modifTime;

    /**
     * 密码
     */
    private String password;

    /**
     * 密码最小长度
     */
    private Integer passwordLeastLength;

    /**
     * 密码有效期
     */
    private Date passwordTerm;

    /**
     * 密码类型
     */
    private String passwordType;

    /**
     * 电话
     */
    private String phone;

    /**
     * 照片路径
     */
    private String photoUrl;

    /**
     * 密码最后修改时间
     */
    private Date pwdLastUpdateTime;

    /**
     * 注册时间
     */
    private Date regTime;

    /**
     * 账号状态
     */
    private String status;

    /**
     * 系统标识
     */
    private String sysFlag;

    /**
     * 用户姓名
     */
    private String userName;

    /**
     * 拼音
     */
    private String userPy;

    /**
     * 用户类型 1:超级管理员 admin 2：组织管理员 3：普通用户
     */
    private String userType;

    /**
     * 邮编
     */
    private String zip;
    /**
     * 品牌ID,如果本身为品牌用户或集体用户,则品牌ID就是当前用户所在组织,不映射数据表
     */
    private String brandId;

    private Set<BaseUserPermit> baseUserPermits = new HashSet<BaseUserPermit>(0);
    private Set<BaseRoleUser> baseRoleUsers = new HashSet<BaseRoleUser>(0);
    private Set<BaseOrganUser> baseOrganUsers = new HashSet<BaseOrganUser>(0);

    // Constructors

    /** default constructor */
    public BaseUser() {
    }

    /** minimal constructor */
    public BaseUser(BaseOrgan baseOrgan, String employeeId, String loginCode, String password, String userName) {
	this.baseOrgan = baseOrgan;
	this.employeeId = employeeId;
	this.loginCode = loginCode;
	this.password = password;
	this.userName = userName;
    }

    public BaseUser(String userId, Integer version, BaseOrgan baseOrgan, BaseDuties baseDuties, String activeCode, String addr,
	    Date createTime, String email, String employeeId, String fax, String founder, Date lastLoginActive, String loginCode,
	    String loginIp, String modifMan, Date modifTime, String password, Integer passwordLeastLength, Date passwordTerm,
	    String passwordType, String phone, String photoUrl, Date pwdLastUpdateTime, Date regTime, String status, String sysFlag,
	    String userName, String userPy, String userType, String zip, Set<BaseUserPermit> baseUserPermits,
	    Set<BaseRoleUser> baseRoleUsers, Set<BaseOrganUser> baseOrganUsers, String brandId) {
	super();
	this.userId = userId;
	this.version = version;
	this.baseOrgan = baseOrgan;
	this.baseDuties = baseDuties;
	this.activeCode = activeCode;
	this.addr = addr;
	this.createTime = createTime;
	this.email = email;
	this.employeeId = employeeId;
	this.fax = fax;
	this.founder = founder;
	this.lastLoginActive = lastLoginActive;
	this.loginCode = loginCode;
	this.loginIp = loginIp;
	this.modifMan = modifMan;
	this.modifTime = modifTime;
	this.password = password;
	this.passwordLeastLength = passwordLeastLength;
	this.passwordTerm = passwordTerm;
	this.passwordType = passwordType;
	this.phone = phone;
	this.photoUrl = photoUrl;
	this.pwdLastUpdateTime = pwdLastUpdateTime;
	this.regTime = regTime;
	this.status = status;
	this.sysFlag = sysFlag;
	this.userName = userName;
	this.userPy = userPy;
	this.userType = userType;
	this.zip = zip;
	this.baseUserPermits = baseUserPermits;
	this.baseRoleUsers = baseRoleUsers;
	this.baseOrganUsers = baseOrganUsers;
	this.brandId = brandId;
    }

    // Property accessors
    @GenericGenerator(name = "generator", strategy = "uuid.hex")
    @Id
    @GeneratedValue(generator = "generator")
    @Column(name = "USER_ID", unique = true, nullable = false, length = 32)
    public String getUserId() {
	return this.userId;
    }

    public void setUserId(String userId) {
	this.userId = userId;
    }

    @Column(name = "version")
    public Integer getVersion() {
	return this.version;
    }

    public void setVersion(Integer version) {
	this.version = version;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ORGAN_ID", nullable = false)
    public BaseOrgan getBaseOrgan() {
	return this.baseOrgan;
    }

    public void setBaseOrgan(BaseOrgan baseOrgan) {
	this.baseOrgan = baseOrgan;
    }

    @Column(name = "ACTIVE_CODE", length = 6)
    public String getActiveCode() {
	return this.activeCode;
    }

    public void setActiveCode(String activeCode) {
	this.activeCode = activeCode;
    }

    @Column(name = "ADDR", length = 200)
    public String getAddr() {
	return this.addr;
    }

    public void setAddr(String addr) {
	this.addr = addr;
    }

    @Column(name = "CREATE_TIME", length = 23)
    public Date getCreateTime() {
	return this.createTime;
    }

    public void setCreateTime(Date createTime) {
	this.createTime = createTime;
    }

    @Column(name = "EMAIL", length = 50)
    public String getEmail() {
	return this.email;
    }

    public void setEmail(String email) {
	this.email = email;
    }

    @Column(name = "EMPLOYEE_ID", nullable = false, length = 20)
    public String getEmployeeId() {
	return this.employeeId;
    }

    public void setEmployeeId(String employeeId) {
	this.employeeId = employeeId;
    }

    @Column(name = "FAX", length = 30)
    public String getFax() {
	return this.fax;
    }

    public void setFax(String fax) {
	this.fax = fax;
    }

    @Column(name = "FOUNDER", length = 20)
    public String getFounder() {
	return this.founder;
    }

    public void setFounder(String founder) {
	this.founder = founder;
    }

    @Column(name = "LAST_LOGIN_ACTIVE", length = 23)
    public Date getLastLoginActive() {
	return this.lastLoginActive;
    }

    public void setLastLoginActive(Date lastLoginActive) {
	this.lastLoginActive = lastLoginActive;
    }

    @Column(name = "LOGIN_CODE", nullable = false, length = 30)
    public String getLoginCode() {
	return this.loginCode;
    }

    public void setLoginCode(String loginCode) {
	this.loginCode = loginCode;
    }

    @Column(name = "LOGIN_IP", length = 50)
    public String getLoginIp() {
	return this.loginIp;
    }

    public void setLoginIp(String loginIp) {
	this.loginIp = loginIp;
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

    @Column(name = "PASSWORD", nullable = false, length = 50)
    public String getPassword() {
	return this.password;
    }

    public void setPassword(String password) {
	this.password = password;
    }

    @Column(name = "PASSWORD_LEAST_LENGTH")
    public Integer getPasswordLeastLength() {
	return this.passwordLeastLength;
    }

    public void setPasswordLeastLength(Integer passwordLeastLength) {
	this.passwordLeastLength = passwordLeastLength;
    }

    @Column(name = "PASSWORD_TERM", length = 23)
    public Date getPasswordTerm() {
	return this.passwordTerm;
    }

    public void setPasswordTerm(Date passwordTerm) {
	this.passwordTerm = passwordTerm;
    }

    @Column(name = "PASSWORD_TYPE", length = 1)
    public String getPasswordType() {
	return this.passwordType;
    }

    public void setPasswordType(String passwordType) {
	this.passwordType = passwordType;
    }

    @Column(name = "PHONE", length = 30)
    public String getPhone() {
	return this.phone;
    }

    public void setPhone(String phone) {
	this.phone = phone;
    }

    @Column(name = "PHOTO_URL", length = 500)
    public String getPhotoUrl() {
	return this.photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
	this.photoUrl = photoUrl;
    }

    @Column(name = "PWD_LAST_UPDATE_TIME", length = 23)
    public Date getPwdLastUpdateTime() {
	return this.pwdLastUpdateTime;
    }

    public void setPwdLastUpdateTime(Date pwdLastUpdateTime) {
	this.pwdLastUpdateTime = pwdLastUpdateTime;
    }

    @Column(name = "REG_TIME", length = 23)
    public Date getRegTime() {
	return this.regTime;
    }

    public void setRegTime(Date regTime) {
	this.regTime = regTime;
    }

    @Column(name = "STATUS", length = 1)
    public String getStatus() {
	return this.status;
    }

    public void setStatus(String status) {
	this.status = status;
    }

    @Column(name = "SYS_FLAG", length = 1)
    public String getSysFlag() {
	return this.sysFlag;
    }

    public void setSysFlag(String sysFlag) {
	this.sysFlag = sysFlag;
    }

    @Column(name = "USER_NAME", nullable = false, length = 20)
    public String getUserName() {
	return this.userName;
    }

    public void setUserName(String userName) {
	this.userName = userName;
    }

    @Column(name = "USER_PY", length = 20)
    public String getUserPy() {
	return this.userPy;
    }

    public void setUserPy(String userPy) {
	this.userPy = userPy;
    }

    @Column(name = "USER_TYPE", length = 1)
    public String getUserType() {
	return this.userType;
    }

    public void setUserType(String userType) {
	this.userType = userType;
    }

    @Column(name = "ZIP", length = 30)
    public String getZip() {
	return this.zip;
    }

    public void setZip(String zip) {
	this.zip = zip;
    }

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "baseUser")
    public Set<BaseUserPermit> getBaseUserPermits() {
	return this.baseUserPermits;
    }

    public void setBaseUserPermits(Set<BaseUserPermit> baseUserPermits) {
	this.baseUserPermits = baseUserPermits;
    }

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "baseUser")
    public Set<BaseRoleUser> getBaseRoleUsers() {
	return this.baseRoleUsers;
    }

    public void setBaseRoleUsers(Set<BaseRoleUser> baseRoleUsers) {
	this.baseRoleUsers = baseRoleUsers;
    }

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "baseUser")
    public Set<BaseOrganUser> getBaseOrganUsers() {
	return this.baseOrganUsers;
    }

    public void setBaseOrganUsers(Set<BaseOrganUser> baseOrganUsers) {
	this.baseOrganUsers = baseOrganUsers;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "DUTIES_ID")
    public BaseDuties getBaseDuties() {
	return baseDuties;
    }

    public void setBaseDuties(BaseDuties baseDuties) {
	this.baseDuties = baseDuties;
    }

    @Transient
    public String getBrandId() {
	return brandId;
    }

    public void setBrandId(String brandId) {
	this.brandId = brandId;
    }
}