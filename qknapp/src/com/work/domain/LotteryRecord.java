package com.work.domain;

// Generated 2014-7-4 11:54:04 by Hibernate Tools 3.4.0.CR1

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;

/**
 * TLotteryRecord generated by hbm2java
 */
@Entity
@Table(name = "T_LOTTERY_RECORD")
public class LotteryRecord implements java.io.Serializable {

	/**
	 * (域的意义,目的,功能)
	 */
	private static final long serialVersionUID = 6853255666741649574L;
	private String lotId;
	private String brandId;
	private String productId;
	private String cellPhone;
	private String contacts;
	private String phoneCheck;
	private Integer isWinned;
	private Integer isExpiry;//0未领奖 1领奖
	private Date expiryTime;
	private String scanIp;
	private String scanAddress;
	private String creator;
	private Date createDate;
	private String modifier;
	private Date modifyDate;
	private Lottery lottery;
	private LotteryPrizeSet lotteryPrizeSet;

	public LotteryRecord() {
	}

	public LotteryRecord(String lotId, String lotteryId, String prizeId) {
		this.lotId = lotId;
	}

	public LotteryRecord(String lotId, String brandId, String lotteryId, String prizeId, String productId,
			String cellPhone, String phoneCheck, Integer isWinned, Integer isExpiry, Date expiryTime, String scanIp,
			String scanAddress, String creator, Date createDate, String modifier, Date modifyDate) {
		this.lotId = lotId;
		this.brandId = brandId;
		this.productId = productId;
		this.cellPhone = cellPhone;
		this.phoneCheck = phoneCheck;
		this.isWinned = isWinned;
		this.isExpiry = isExpiry;
		this.expiryTime = expiryTime;
		this.scanIp = scanIp;
		this.scanAddress = scanAddress;
		this.creator = creator;
		this.createDate = createDate;
		this.modifier = modifier;
		this.modifyDate = modifyDate;
	}

	@GenericGenerator(name = "generator", strategy = "uuid.hex")
	@Id
	@GeneratedValue(generator = "generator")
	@Column(name = "LOT_ID", unique = true, nullable = false, length = 32)
	public String getLotId() {
		return this.lotId;
	}

	public void setLotId(String lotId) {
		this.lotId = lotId;
	}

	@Column(name = "BRAND_ID", length = 32)
	public String getBrandId() {
		return this.brandId;
	}

	public void setBrandId(String brandId) {
		this.brandId = brandId;
	}

	@Column(name = "PRODUCT_ID", length = 32)
	public String getProductId() {
		return this.productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	@Column(name = "CELL_PHONE", length = 11)
	public String getCellPhone() {
		return this.cellPhone;
	}

	public void setCellPhone(String cellPhone) {
		this.cellPhone = cellPhone;
	}

	@Column(name = "CONTACTS", length = 20)
	public String getContacts() {
		return contacts;
	}

	public void setContacts(String contacts) {
		this.contacts = contacts;
	}

	@Column(name = "PHONE_CHECK", length = 6)
	public String getPhoneCheck() {
		return this.phoneCheck;
	}

	public void setPhoneCheck(String phoneCheck) {
		this.phoneCheck = phoneCheck;
	}

	@Column(name = "IS_WINNED")
	public Integer getIsWinned() {
		return this.isWinned;
	}

	public void setIsWinned(Integer isWinned) {
		this.isWinned = isWinned;
	}

	@Column(name = "IS_EXPIRY")
	public Integer getIsExpiry() {
		return this.isExpiry;
	}

	public void setIsExpiry(Integer isExpiry) {
		this.isExpiry = isExpiry;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "EXPIRY_TIME", length = 19)
	public Date getExpiryTime() {
		return this.expiryTime;
	}

	public void setExpiryTime(Date expiryTime) {
		this.expiryTime = expiryTime;
	}

	@Column(name = "SCAN_IP", length = 15)
	public String getScanIp() {
		return this.scanIp;
	}

	public void setScanIp(String scanIp) {
		this.scanIp = scanIp;
	}

	@Column(name = "SCAN_ADDRESS", length = 100)
	public String getScanAddress() {
		return this.scanAddress;
	}

	public void setScanAddress(String scanAddress) {
		this.scanAddress = scanAddress;
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

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "LOTTERY_ID")
	public Lottery getLottery() {
		return lottery;
	}

	public void setLottery(Lottery lottery) {
		this.lottery = lottery;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "PRIZE_ID")
	public LotteryPrizeSet getLotteryPrizeSet() {
		return lotteryPrizeSet;
	}

	public void setLotteryPrizeSet(LotteryPrizeSet lotteryPrizeSet) {
		this.lotteryPrizeSet = lotteryPrizeSet;
	}

}