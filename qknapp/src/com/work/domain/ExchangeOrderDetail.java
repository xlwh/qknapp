package com.work.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * TExchangeOrderDetail entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "T_EXCHANGE_ORDER_DETAIL")
public class ExchangeOrderDetail implements java.io.Serializable {

	// Fields    

	/**
	 * (域的意义,目的,功能)
	 */
	private static final long serialVersionUID = 7698049923818741704L;
	private String exchangeOrderDetailId;
	private String exchangeOrderId;
	private String giftId;
	private String giftName;
	private Integer perPoint;
	private Integer amount;

	// Constructors

	/** default constructor */
	public ExchangeOrderDetail() {
	}

	/** minimal constructor */
	public ExchangeOrderDetail(String exchangeOrderId, String giftId, Integer perPoint, Integer amount) {
		this.exchangeOrderId = exchangeOrderId;
		this.giftId = giftId;
		this.perPoint = perPoint;
		this.amount = amount;
	}

	/** full constructor */
	public ExchangeOrderDetail(String exchangeOrderId, String giftId, String giftName, Integer perPoint, Integer amount) {
		this.exchangeOrderId = exchangeOrderId;
		this.giftId = giftId;
		this.giftName = giftName;
		this.perPoint = perPoint;
		this.amount = amount;
	}

	// Property accessors
	@GenericGenerator(name = "generator", strategy = "uuid.hex")
	@Id
	@GeneratedValue(generator = "generator")
	@Column(name = "EXCHANGE_ORDER_DETAIL_ID", unique = true, nullable = false, length = 32)
	public String getExchangeOrderDetailId() {
		return this.exchangeOrderDetailId;
	}

	public void setExchangeOrderDetailId(String exchangeOrderDetailId) {
		this.exchangeOrderDetailId = exchangeOrderDetailId;
	}

	@Column(name = "EXCHANGE_ORDER_ID", nullable = false, length = 32)
	public String getExchangeOrderId() {
		return this.exchangeOrderId;
	}

	public void setExchangeOrderId(String exchangeOrderId) {
		this.exchangeOrderId = exchangeOrderId;
	}

	@Column(name = "GIFT_ID", nullable = false, length = 32)
	public String getGiftId() {
		return this.giftId;
	}

	public void setGiftId(String giftId) {
		this.giftId = giftId;
	}

	@Column(name = "GIFT_NAME", length = 50)
	public String getGiftName() {
		return this.giftName;
	}

	public void setGiftName(String giftName) {
		this.giftName = giftName;
	}

	@Column(name = "PER_POINT", nullable = false)
	public Integer getPerPoint() {
		return this.perPoint;
	}

	public void setPerPoint(Integer perPoint) {
		this.perPoint = perPoint;
	}

	@Column(name = "AMOUNT", nullable = false)
	public Integer getAmount() {
		return this.amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

}