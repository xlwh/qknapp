package com.work.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * TMemberLevel entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "T_MEMBER_LEVEL")
public class MemberLevel implements java.io.Serializable {

	// Fields    

	/**
	 * (域的意义,目的,功能)
	 */
	private static final long serialVersionUID = 1L;
	private String memberLevelId;
	private String levelName;
	private Integer levelCode;
	private Integer minPoint;
	private Integer maxPoint;

	// Constructors

	/** default constructor */
	public MemberLevel() {
	}

	/** full constructor */
	public MemberLevel(String levelName, Integer levelCode, Integer minPoint, Integer maxPoint) {
		this.levelName = levelName;
		this.levelCode = levelCode;
		this.minPoint = minPoint;
		this.maxPoint = maxPoint;
	}

	// Property accessors
	@GenericGenerator(name = "generator", strategy = "uuid.hex")
	@Id
	@GeneratedValue(generator = "generator")
	@Column(name = "MEMBER_LEVEL_ID", unique = true, nullable = false, length = 32)
	public String getMemberLevelId() {
		return this.memberLevelId;
	}

	public void setMemberLevelId(String memberLevelId) {
		this.memberLevelId = memberLevelId;
	}

	@Column(name = "LEVEL_NAME", nullable = false, length = 20)
	public String getLevelName() {
		return this.levelName;
	}

	public void setLevelName(String levelName) {
		this.levelName = levelName;
	}

	@Column(name = "LEVEL_CODE", nullable = false)
	public Integer getLevelCode() {
		return this.levelCode;
	}

	public void setLevelCode(Integer levelCode) {
		this.levelCode = levelCode;
	}

	@Column(name = "MIN_POINT", nullable = false)
	public Integer getMinPoint() {
		return this.minPoint;
	}

	public void setMinPoint(Integer minPoint) {
		this.minPoint = minPoint;
	}

	@Column(name = "MAX_POINT", nullable = false)
	public Integer getMaxPoint() {
		return this.maxPoint;
	}

	public void setMaxPoint(Integer maxPoint) {
		this.maxPoint = maxPoint;
	}

}