package com.work.domain;

// Generated 2014-7-4 11:54:04 by Hibernate Tools 3.4.0.CR1

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
 * TLotteryUserSet generated by hbm2java
 */
@Entity
@Table(name = "T_LOTTERY_USER_SET")
public class LotteryUserSet implements java.io.Serializable {

	/**
	 * (域的意义,目的,功能)
	 */
	private static final long serialVersionUID = -3740214211670564077L;
	private String setId;
	private String lotteryId;
	private Integer usableNum;
	private String cellPhone;
	private String creator;
	private Date createDate;
	private String modifier;
	private Date modifyDate;
	private String checkCode;
	private String printCodeInfo;
	private Date effectiveTime;

	public LotteryUserSet() {
	}

	public LotteryUserSet(String setId, String lotteryId) {
		this.setId = setId;
		this.lotteryId = lotteryId;
	}

	public LotteryUserSet(String setId, String lotteryId, Integer usableNum, String cellPhone, String creator,
			Date createDate, String modifier, Date modifyDate, String checkCode, String printCodeInfo) {
		this.setId = setId;
		this.lotteryId = lotteryId;
		this.usableNum = usableNum;
		this.cellPhone = cellPhone;
		this.creator = creator;
		this.createDate = createDate;
		this.modifier = modifier;
		this.modifyDate = modifyDate;
		this.checkCode = checkCode;
		this.printCodeInfo = printCodeInfo;
	}

	@GenericGenerator(name = "generator", strategy = "uuid.hex")
	@Id
	@GeneratedValue(generator = "generator")
	@Column(name = "SET_ID", unique = true, nullable = false, length = 32)
	public String getSetId() {
		return this.setId;
	}

	public void setSetId(String setId) {
		this.setId = setId;
	}

	@Column(name = "LOTTERY_ID", nullable = false, length = 32)
	public String getLotteryId() {
		return this.lotteryId;
	}

	public void setLotteryId(String lotteryId) {
		this.lotteryId = lotteryId;
	}

	@Column(name = "USABLE_NUM")
	public Integer getUsableNum() {
		return this.usableNum;
	}

	public void setUsableNum(Integer usableNum) {
		this.usableNum = usableNum;
	}

	@Column(name = "CELL_PHONE", length = 11)
	public String getCellPhone() {
		return this.cellPhone;
	}

	public void setCellPhone(String cellPhone) {
		this.cellPhone = cellPhone;
	}

	@Column(name = "CREATOR", length = 20)
	public String getCreator() {
		return this.creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CREATE_DATE", length = 19)
	public Date getCreateDate() {
		return this.createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	@Column(name = "MODIFIER", length = 20)
	public String getModifier() {
		return this.modifier;
	}

	public void setModifier(String modifier) {
		this.modifier = modifier;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "MODIFY_DATE", length = 19)
	public Date getModifyDate() {
		return this.modifyDate;
	}

	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}

	@Column(name = "CHECK_CODE", length = 20)
	public String getCheckCode() {
		return this.checkCode;
	}

	public void setCheckCode(String checkCode) {
		this.checkCode = checkCode;
	}

	@Column(name = "PRINT_CODE_INFO", length = 50)
	public String getPrintCodeInfo() {
		return this.printCodeInfo;
	}

	public void setPrintCodeInfo(String printCodeInfo) {
		this.printCodeInfo = printCodeInfo;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "EFFECTIVE_TIME", length = 19)
	public Date getEffectiveTime() {
		return effectiveTime;
	}

	public void setEffectiveTime(Date effectiveTime) {
		this.effectiveTime = effectiveTime;
	}

}
