package com.work.domain;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * TPointRecord entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "T_POINT_RECORD")
public class PointRecord implements java.io.Serializable {

	// Fields    

	/**
	 * (域的意义,目的,功能)
	 */
	private static final long serialVersionUID = 1L;
	private String pointRedordId;
	private String memberId;
	private Integer point;
	private Integer pointType;
	private Timestamp createTime;
	private String creator;

	// Constructors

	/** default constructor */
	public PointRecord() {
	}

	/** minimal constructor */
	public PointRecord(String memberId, Integer point, Integer pointType) {
		this.memberId = memberId;
		this.point = point;
		this.pointType = pointType;
	}

	/** full constructor */
	public PointRecord(String memberId, Integer point, Integer pointType, Timestamp createTime, String creator) {
		this.memberId = memberId;
		this.point = point;
		this.pointType = pointType;
		this.createTime = createTime;
		this.creator = creator;
	}

	// Property accessors
	@GenericGenerator(name = "generator", strategy = "uuid.hex")
	@Id
	@GeneratedValue(generator = "generator")
	@Column(name = "POINT_REDORD_ID", unique = true, nullable = false, length = 32)
	public String getPointRedordId() {
		return this.pointRedordId;
	}

	public void setPointRedordId(String pointRedordId) {
		this.pointRedordId = pointRedordId;
	}

	@Column(name = "MEMBER_ID", nullable = false, length = 32)
	public String getMemberId() {
		return this.memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	@Column(name = "POINT", nullable = false)
	public Integer getPoint() {
		return this.point;
	}

	public void setPoint(Integer point) {
		this.point = point;
	}

	@Column(name = "POINT_TYPE", nullable = false)
	public Integer getPointType() {
		return this.pointType;
	}

	public void setPointType(Integer pointType) {
		this.pointType = pointType;
	}

	@Column(name = "CREATE_TIME", length = 19)
	public Timestamp getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	@Column(name = "CREATOR", length = 32)
	public String getCreator() {
		return this.creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

}