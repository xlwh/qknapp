package com.work.domain;

// Generated 2014-8-18 21:42:25 by Hibernate Tools 3.2.2.GA

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;

/**
 * ValidateCode generated by hbm2java
 */
@Entity
@Table(name = "T_VALIDATE_CODE")
public class ValidateCode implements java.io.Serializable {

	private static final long serialVersionUID = -5158235921951548367L;
	
	private String id;
	private String code;
	private String sender;
	private Date expireTime;
	private String function;
	private Date createTime;

	public ValidateCode() {
	}

	public ValidateCode(String id, String code, Date expireTime) {
		this.id = id;
		this.code = code;
		this.expireTime = expireTime;
	}

	public ValidateCode(String id, String code, String sender, Date expireTime,
			String function) {
		this.id = id;
		this.code = code;
		this.sender = sender;
		this.expireTime = expireTime;
		this.function = function;
	}

	@GenericGenerator(name = "generator", strategy = "uuid.hex")
	@Id
	@GeneratedValue(generator = "generator")
	@Column(name = "ID", unique = true, nullable = false, length = 32)
	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Column(name = "CODE", nullable = false, length = 11)
	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@Column(name = "SENDER", length = 100)
	public String getSender() {
		return this.sender;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "EXPIRE_TIME", nullable = false, length = 19)
	public Date getExpireTime() {
		return this.expireTime;
	}

	public void setExpireTime(Date expireTime) {
		this.expireTime = expireTime;
	}

	@Column(name = "FUNCTION_NAME", length = 16)
	public String getFunction() {
		return this.function;
	}

	public void setFunction(String function) {
		this.function = function;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CREATE_TIME", nullable = false, length = 19, updatable = false)
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

}
