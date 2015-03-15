package com.qing.right.dao.domain;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * BaseOrgan entity. @author MyEclipse Persistence Tools
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "T_BASE_ORGAN")
public class BaseOrgan implements java.io.Serializable {

    // Fields
    /**
     * 组织编号
     */
    private String organId;

    /**
     * 版本号
     */
    private Integer version;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 创建人
     */
    private String founder;

    /**
     * 修改人
     */
    private String modifMan;

    /**
     * 最近修改时间
     */
    private Date modifTime;

    /**
     * 删除标记。1表示没有删除；2表示被删除
     */
    private String organDelFlag;

    /**
     * 描述
     */
    private String organDesc;

    /**
     * 组织的父ID
     */
    private String organFather;

    /**
     * 组织形式： 1、企业实体组织 2、企业虚拟组织（用于项目组和跨公司的项目组织）
     */
    private String organForm;

    /**
     * 组织分级号(例：001集团名称，001001一级分公司名称，001001001二级分公司名称) 由系统算法自动产生，更新时重算
     */
    private String organLev;

    /**
     * 组织的单位和部门区分： 1、表示企事业单位总部 2、表示企事业单位分部 3、表示部门 （该部分在系统界面上单选）
     */
    private String organMark;

    /**
     * 组织全名
     */
    private String organName;

    /**
     * 组织简称
     */
    private String organShort;

    /**
     * 门店编号
     */
    private String organNo;

    /**
     * 组织的排序序号
     */
    private Integer organSort;

    /**
     * 是否有子标志(N，没有；Y，有)
     */
    private String organTag;

    /**
     * 组织类别： 1、集团总公司 2、下属全资公司 3、下属控股公司 4、下属参股公司 5、事业部 6、部门
     * 该字段对应同类型的数据字典表BasTypeDict
     */
    private String organType;

    private String organTypeId;
    
    /**
     * ERP同步字段
     */
    private String erpOrganCode;

    private Set<BaseRole> baseRoles = new HashSet<BaseRole>(0);
    private Set<BaseUser> baseUsers = new HashSet<BaseUser>(0);
    private Set<BaseOrganUser> baseOrganUsers = new HashSet<BaseOrganUser>(0);
    private Set<BaseDuties> baseDuties = new HashSet<BaseDuties>(0);

    // Constructors

    /** default constructor */
    public BaseOrgan() {
    }

    /** full constructor */
    public BaseOrgan(Date createTime, String founder, String modifMan, Date modifTime, String organDelFlag, String organDesc,
	    String organFather, String organForm, String organLev, String organMark, String organName, String organShort, String organNo,
	    Integer organSort, String organTag, String organType, String organTypeId, Set<BaseRole> baseRoles, Set<BaseUser> baseUsers,
	    Set<BaseOrganUser> baseOrganUsers) {
	this.createTime = createTime;
	this.founder = founder;
	this.modifMan = modifMan;
	this.modifTime = modifTime;
	this.organDelFlag = organDelFlag;
	this.organDesc = organDesc;
	this.organFather = organFather;
	this.organForm = organForm;
	this.organLev = organLev;
	this.organMark = organMark;
	this.organName = organName;
	this.organShort = organShort;
	this.organNo = organNo;
	this.organSort = organSort;
	this.organTag = organTag;
	this.organType = organType;
	this.organTypeId = organTypeId;
	this.baseRoles = baseRoles;
	this.baseUsers = baseUsers;
	this.baseOrganUsers = baseOrganUsers;
    }

    // Property accessors
    @GenericGenerator(name = "generator", strategy = "uuid.hex")
    @Id
    @GeneratedValue(generator = "generator")
    @Column(name = "ORGAN_ID", unique = true, nullable = false, length = 32)
    public String getOrganId() {
	return this.organId;
    }

    public void setOrganId(String organId) {
	this.organId = organId;
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

    @Column(name = "ORGAN_DEL_FLAG", length = 1)
    public String getOrganDelFlag() {
	return this.organDelFlag;
    }

    public void setOrganDelFlag(String organDelFlag) {
	this.organDelFlag = organDelFlag;
    }

    @Column(name = "ORGAN_DESC", length = 200)
    public String getOrganDesc() {
	return this.organDesc;
    }

    public void setOrganDesc(String organDesc) {
	this.organDesc = organDesc;
    }

    @Column(name = "ORGAN_FATHER", length = 32)
    public String getOrganFather() {
	return this.organFather;
    }

    public void setOrganFather(String organFather) {
	this.organFather = organFather;
    }

    @Column(name = "ORGAN_FORM", length = 1)
    public String getOrganForm() {
	return this.organForm;
    }

    public void setOrganForm(String organForm) {
	this.organForm = organForm;
    }

    @Column(name = "ORGAN_LEV", length = 50)
    public String getOrganLev() {
	return this.organLev;
    }

    public void setOrganLev(String organLev) {
	this.organLev = organLev;
    }

    @Column(name = "ORGAN_MARK", length = 1)
    public String getOrganMark() {
	return this.organMark;
    }

    public void setOrganMark(String organMark) {
	this.organMark = organMark;
    }

    @Column(name = "ORGAN_NAME", length = 100)
    public String getOrganName() {
	return this.organName;
    }

    public void setOrganName(String organName) {
	this.organName = organName;
    }

    @Column(name = "ORGAN_SHORT", length = 20)
    public String getOrganShort() {
	return this.organShort;
    }

    public void setOrganShort(String organShort) {
	this.organShort = organShort;
    }

    @Column(name = "ORGAN_NO", length = 32)
    public String getOrganNo() {
	return organNo;
    }

    public void setOrganNo(String organNo) {
	this.organNo = organNo;
    }

    @Column(name = "ORGAN_SORT")
    public Integer getOrganSort() {
	return this.organSort;
    }

    public void setOrganSort(Integer organSort) {
	this.organSort = organSort;
    }

    @Column(name = "ORGAN_TAG", length = 1)
    public String getOrganTag() {
	return this.organTag;
    }

    public void setOrganTag(String organTag) {
	this.organTag = organTag;
    }

    @Column(name = "ORGAN_TYPE", length = 1)
    public String getOrganType() {
	return this.organType;
    }

    public void setOrganType(String organType) {
	this.organType = organType;
    }

    @Column(name = "ORGAN_TYPE_ID", length = 32)
    public String getOrganTypeId() {
	return organTypeId;
    }

    public void setOrganTypeId(String organTypeId) {
	this.organTypeId = organTypeId;
    }

    @Column(name = "ERP_ORGAN_CODE", length = 50)
    public String getErpOrganCode() {
		return erpOrganCode;
	}
   
	public void setErpOrganCode(String erpOrganCode) {
		this.erpOrganCode = erpOrganCode;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "baseOrgan")
    public Set<BaseRole> getBaseRoles() {
	return this.baseRoles;
    }

    public void setBaseRoles(Set<BaseRole> baseRoles) {
	this.baseRoles = baseRoles;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "baseOrgan")
    public Set<BaseUser> getBaseUsers() {
	return this.baseUsers;
    }

    public void setBaseUsers(Set<BaseUser> baseUsers) {
	this.baseUsers = baseUsers;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "baseOrgan")
    public Set<BaseOrganUser> getBaseOrganUsers() {
	return this.baseOrganUsers;
    }

    public void setBaseOrganUsers(Set<BaseOrganUser> baseOrganUsers) {
	this.baseOrganUsers = baseOrganUsers;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "baseOrgan")
    public Set<BaseDuties> getBaseDuties() {
	return baseDuties;
    }

    public void setBaseDuties(Set<BaseDuties> baseDuties) {
	this.baseDuties = baseDuties;
    }

}