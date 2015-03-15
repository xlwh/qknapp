package com.qing.right.dao.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * BaseLog entity. @author MyEclipse Persistence Tools
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "T_BASE_LOG")
public class BaseLog implements java.io.Serializable {

	// Fields
	/**
	 * 日志编号
	 */
	private String logId;

	/**
	 * 访问者IP
	 */
	private String entryIp;

	/**
	 * 进入的模块ID （不需要显示给用户看）
	 */
	private String entryModuleId;

	/**
	 * 进入的模块说明(显示给用户看时需要显示模块层级)
	 */
	private String entryModuleName;

	/**
	 * 进入时间
	 */
	private Date entryTime;

	/**
	 * 进入的地址
	 */
	private String entryUrl;

	/**
	 * 进入人(用户登录帐号)
	 */
	private String entryUserCode;

	/**
	 * 修改人
	 */
	private String modifMan;

	/**
	 * 描述
	 */
	private String remark;

	/**
	 * 操作类型
	 */
	private String operateType;

	// Constructors

	/** default constructor */
	public BaseLog() {
	}

	/** minimal constructor */
	public BaseLog(Date entryTime, String entryUserCode) {
		this.entryTime = entryTime;
		this.entryUserCode = entryUserCode;
	}

	/** full constructor */
	public BaseLog(String entryIp, String entryModuleId, String entryModuleName, Date entryTime, String entryUrl,
			String entryUserCode, String modifMan) {
		this.entryIp = entryIp;
		this.entryModuleId = entryModuleId;
		this.entryModuleName = entryModuleName;
		this.entryTime = entryTime;
		this.entryUrl = entryUrl;
		this.entryUserCode = entryUserCode;
		this.modifMan = modifMan;
	}

	// Property accessors
	@GenericGenerator(name = "generator", strategy = "uuid.hex")
	@Id
	@GeneratedValue(generator = "generator")
	@Column(name = "LOG_ID", unique = true, nullable = false, length = 32)
	public String getLogId() {
		return this.logId;
	}

	public void setLogId(String logId) {
		this.logId = logId;
	}

	@Column(name = "ENTRY_IP", length = 30)
	public String getEntryIp() {
		return this.entryIp;
	}

	public void setEntryIp(String entryIp) {
		this.entryIp = entryIp;
	}

	@Column(name = "ENTRY_MODULE_ID")
	public String getEntryModuleId() {
		return this.entryModuleId;
	}

	public void setEntryModuleId(String entryModuleId) {
		this.entryModuleId = entryModuleId;
	}

	@Column(name = "ENTRY_MODULE_NAME", length = 100)
	public String getEntryModuleName() {
		return this.entryModuleName;
	}

	public void setEntryModuleName(String entryModuleName) {
		this.entryModuleName = entryModuleName;
	}

	@Column(name = "ENTRY_TIME", nullable = false, length = 23)
	public Date getEntryTime() {
		return this.entryTime;
	}

	public void setEntryTime(Date entryTime) {
		this.entryTime = entryTime;
	}

	@Column(name = "ENTRY_URL", length = 100)
	public String getEntryUrl() {
		return this.entryUrl;
	}

	public void setEntryUrl(String entryUrl) {
		this.entryUrl = entryUrl;
	}

	@Column(name = "ENTRY_USER_CODE", nullable = false, length = 20)
	public String getEntryUserCode() {
		return this.entryUserCode;
	}

	public void setEntryUserCode(String entryUserCode) {
		this.entryUserCode = entryUserCode;
	}

	@Column(name = "MODIF_MAN", length = 20)
	public String getModifMan() {
		return this.modifMan;
	}

	public void setModifMan(String modifMan) {
		this.modifMan = modifMan;
	}

	@Column(name = "REMARK")
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Column(name = "OPERATE_TYPE")
	public String getOperateType() {
		return operateType;
	}

	public void setOperateType(String operateType) {
		this.operateType = operateType;
	}

}