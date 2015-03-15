package com.work.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * TExchangeGift entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "T_EXCHANGE_GIFT")
public class ExchangeGift implements java.io.Serializable {

	// Fields    

	/**
	 * (域的意义,目的,功能)
	 */
	private static final long serialVersionUID = 1L;
	private String giftId;
	private String giftName;
	private String giftPic;
	private String giftDescriptions;
	private Integer exchangeType;
	private Integer needPoint;
	private Integer totalAmount;
	private Integer usedAmount;
	private Date beginTime;
	private Date endTime;
	private String memberLevelId;
	private Integer maxAmount;
	private Date createTime;
	private String creator;
	private Date updateTime;

	// Constructors

	/** default constructor */
	public ExchangeGift() {
	}

	/** minimal constructor */
	public ExchangeGift(String giftName, Integer exchangeType, Integer needPoint, Integer totalAmount, Date beginTime,
			Date endTime, String memberLevelId, Integer maxAmount, Date createTime) {
		this.giftName = giftName;
		this.exchangeType = exchangeType;
		this.needPoint = needPoint;
		this.totalAmount = totalAmount;
		this.beginTime = beginTime;
		this.endTime = endTime;
		this.memberLevelId = memberLevelId;
		this.maxAmount = maxAmount;
		this.createTime = createTime;
	}

	/** full constructor */
	public ExchangeGift(String giftName, String giftPic, String giftDescriptions, Integer exchangeType,
			Integer needPoint, Integer totalAmount, Integer usedAmount, Date beginTime, Date endTime,
			String memberLevelId, Integer maxAmount, Date createTime, String creator) {
		this.giftName = giftName;
		this.giftPic = giftPic;
		this.giftDescriptions = giftDescriptions;
		this.exchangeType = exchangeType;
		this.needPoint = needPoint;
		this.totalAmount = totalAmount;
		this.usedAmount = usedAmount;
		this.beginTime = beginTime;
		this.endTime = endTime;
		this.memberLevelId = memberLevelId;
		this.maxAmount = maxAmount;
		this.createTime = createTime;
		this.creator = creator;
	}

	// Property accessors
	@GenericGenerator(name = "generator", strategy = "uuid.hex")
	@Id
	@GeneratedValue(generator = "generator")
	@Column(name = "GIFT_ID", unique = true, nullable = false, length = 32)
	public String getGiftId() {
		return this.giftId;
	}

	public void setGiftId(String giftId) {
		this.giftId = giftId;
	}

	@Column(name = "GIFT_NAME", nullable = false, length = 50)
	public String getGiftName() {
		return this.giftName;
	}

	public void setGiftName(String giftName) {
		this.giftName = giftName;
	}

	@Column(name = "GIFT_PIC", length = 150)
	public String getGiftPic() {
		return this.giftPic;
	}

	public void setGiftPic(String giftPic) {
		this.giftPic = giftPic;
	}

	@Column(name = "GIFT_DESCRIPTIONS", length = 150)
	public String getGiftDescriptions() {
		return this.giftDescriptions;
	}

	public void setGiftDescriptions(String giftDescriptions) {
		this.giftDescriptions = giftDescriptions;
	}

	@Column(name = "EXCHANGE_TYPE", nullable = false)
	public Integer getExchangeType() {
		return this.exchangeType;
	}

	public void setExchangeType(Integer exchangeType) {
		this.exchangeType = exchangeType;
	}

	@Column(name = "NEED_POINT", nullable = false)
	public Integer getNeedPoint() {
		return this.needPoint;
	}

	public void setNeedPoint(Integer needPoint) {
		this.needPoint = needPoint;
	}

	@Column(name = "TOTAL_AMOUNT", nullable = false)
	public Integer getTotalAmount() {
		return this.totalAmount;
	}

	public void setTotalAmount(Integer totalAmount) {
		this.totalAmount = totalAmount;
	}

	@Column(name = "USED_AMOUNT")
	public Integer getUsedAmount() {
		return this.usedAmount;
	}

	public void setUsedAmount(Integer usedAmount) {
		this.usedAmount = usedAmount;
	}

	@Column(name = "BEGIN_TIME", nullable = false, length = 19)
	public Date getBeginTime() {
		return this.beginTime;
	}

	public void setBeginTime(Date beginTime) {
		this.beginTime = beginTime;
	}

	@Column(name = "END_TIME", nullable = false, length = 19)
	public Date getEndTime() {
		return this.endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	@Column(name = "MEMBER_LEVEL_ID", nullable = false)
	public String getMemberLevelId() {
		return this.memberLevelId;
	}

	public void setMemberLevelId(String memberLevelId) {
		this.memberLevelId = memberLevelId;
	}

	@Column(name = "MAX_AMOUNT", nullable = false)
	public Integer getMaxAmount() {
		return this.maxAmount;
	}

	public void setMaxAmount(Integer maxAmount) {
		this.maxAmount = maxAmount;
	}

	@Column(name = "CREATE_TIME", updatable = false, length = 19)
	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Column(name = "CREATOR", length = 50)
	public String getCreator() {
		return this.creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	@Column(name = "UPDATE_TIME", insertable = false, length = 19)
	public Date getUpdateTime() {
		return this.updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

}