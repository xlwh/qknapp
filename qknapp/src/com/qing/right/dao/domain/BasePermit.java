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
 * BasePermit entity. @author MyEclipse Persistence Tools
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "T_BASE_PERMIT")
public class BasePermit implements java.io.Serializable {

	// Fields
	/**
	 * 权限编号
	 */
	private String permitId;

	/**
	 * 版本号
	 */
	private Integer version;

	/**
	 * 模块编号(外键)
	 */
	private BaseModule baseModule;

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
	 * 备注
	 */
	private String note;

	/**
	 * 权限正文
	 */
	private String permitContent;

	/**
	 * 权限描述
	 */
	private String permitDesc;

	/**
	 * 权限名称
	 */
	private String permitName;

	/**
	 * 权限排序
	 */
	private String permitSort;

	/**
	 * 权限类型 0=权限管理可显示设置 5=手持机管理
	 */
	private String permitType;
	private Set<BaseOrganUserPermit> baseOrganUserPermits = new HashSet<BaseOrganUserPermit>(0);
	private Set<BaseUserPermit> baseUserPermits = new HashSet<BaseUserPermit>(0);
	private Set<BaseRolePermit> baseRolePermits = new HashSet<BaseRolePermit>(0);

	// Constructors

	/** default constructor */
	public BasePermit() {
	}

	/** minimal constructor */
	public BasePermit(String permitName) {
		this.permitName = permitName;
	}

	// Property accessors
	@GenericGenerator(name = "generator", strategy = "uuid.hex")
	@Id
	@GeneratedValue(generator = "generator")
	@Column(name = "PERMIT_ID", unique = true, nullable = false, length = 32)
	public String getPermitId() {
		return this.permitId;
	}

	public void setPermitId(String permitId) {
		this.permitId = permitId;
	}

	@Column(name = "VERSION")
	public Integer getVersion() {
		return this.version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "MODULE_ID")
	public BaseModule getBaseModule() {
		return this.baseModule;
	}

	public void setBaseModule(BaseModule baseModule) {
		this.baseModule = baseModule;
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

	@Column(name = "NOTE", length = 100)
	public String getNote() {
		return this.note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	@Column(name = "PERMIT_CONTENT", length = 300)
	public String getPermitContent() {
		return this.permitContent;
	}

	public void setPermitContent(String permitContent) {
		this.permitContent = permitContent;
	}

	@Column(name = "PERMIT_DESC", length = 50)
	public String getPermitDesc() {
		return this.permitDesc;
	}

	public void setPermitDesc(String permitDesc) {
		this.permitDesc = permitDesc;
	}

	@Column(name = "PERMIT_NAME", nullable = false, length = 50)
	public String getPermitName() {
		return this.permitName;
	}

	public void setPermitName(String permitName) {
		this.permitName = permitName;
	}

	@Column(name = "PERMIT_SORT", length = 2)
	public String getPermitSort() {
		return permitSort;
	}

	public void setPermitSort(String permitSort) {
		this.permitSort = permitSort;
	}

	@Column(name = "PERMIT_TYPE", length = 1)
	public String getPermitType() {
		return this.permitType;
	}

	public void setPermitType(String permitType) {
		this.permitType = permitType;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "basePermit")
	public Set<BaseOrganUserPermit> getBaseOrganUserPermits() {
		return this.baseOrganUserPermits;
	}

	public void setBaseOrganUserPermits(Set<BaseOrganUserPermit> baseOrganUserPermits) {
		this.baseOrganUserPermits = baseOrganUserPermits;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "basePermit")
	public Set<BaseUserPermit> getBaseUserPermits() {
		return this.baseUserPermits;
	}

	public void setBaseUserPermits(Set<BaseUserPermit> baseUserPermits) {
		this.baseUserPermits = baseUserPermits;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "basePermit")
	public Set<BaseRolePermit> getBaseRolePermits() {
		return this.baseRolePermits;
	}

	public void setBaseRolePermits(Set<BaseRolePermit> baseRolePermits) {
		this.baseRolePermits = baseRolePermits;
	}

}