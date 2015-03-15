package com.work.domain;

// default package

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * ExchangeOrder entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "T_EXCHANGE_ORDER")
public class ExchangeOrder implements java.io.Serializable {

	// Fields    

	/**
	 * (域的意义,目的,功能)
	 */
	private static final long serialVersionUID = 1L;
	private String exchangeOrderId;
	private String memberId;
	private String orderSn;
	private String receiver;
	private String province;
	private String city;
	private String region;
	private String address;
	private String zipCode;
	private String receiverPhone;
	private String orderRemark;
	private Integer point;
	private Integer status;
	private String dealRemark;
	private Timestamp dealTime;
	private String dealCode;
	private Timestamp orderTime;

	// Constructors

	/** default constructor */
	public ExchangeOrder() {
	}

	/** minimal constructor */
	public ExchangeOrder(String memberId, String orderSn, String address, Integer point, Integer status,
			String dealCode, Timestamp orderTime) {
		this.memberId = memberId;
		this.orderSn = orderSn;
		this.address = address;
		this.point = point;
		this.status = status;
		this.dealCode = dealCode;
		this.orderTime = orderTime;
	}

	/** full constructor */
	public ExchangeOrder(String memberId, String orderSn, String receiver, String province, String city, String region,
			String address, String zipCode, String receiverPhone, String orderRemark, Integer point, Integer status,
			String dealRemark, Timestamp dealTime, String dealCode, Timestamp orderTime) {
		this.memberId = memberId;
		this.orderSn = orderSn;
		this.receiver = receiver;
		this.province = province;
		this.city = city;
		this.region = region;
		this.address = address;
		this.zipCode = zipCode;
		this.receiverPhone = receiverPhone;
		this.orderRemark = orderRemark;
		this.point = point;
		this.status = status;
		this.dealRemark = dealRemark;
		this.dealTime = dealTime;
		this.dealCode = dealCode;
		this.orderTime = orderTime;
	}

	// Property accessors
	@GenericGenerator(name = "generator", strategy = "uuid.hex")
	@Id
	@GeneratedValue(generator = "generator")
	@Column(name = "EXCHANGE_ORDER_ID", unique = true, nullable = false, length = 32)
	public String getExchangeOrderId() {
		return this.exchangeOrderId;
	}

	public void setExchangeOrderId(String exchangeOrderId) {
		this.exchangeOrderId = exchangeOrderId;
	}

	@Column(name = "MEMBER_ID", nullable = false, length = 32)
	public String getMemberId() {
		return this.memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	@Column(name = "ORDER_SN", nullable = false, length = 30)
	public String getOrderSn() {
		return this.orderSn;
	}

	public void setOrderSn(String orderSn) {
		this.orderSn = orderSn;
	}

	@Column(name = "RECEIVER", length = 20)
	public String getReceiver() {
		return this.receiver;
	}

	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}

	@Column(name = "PROVINCE", length = 20)
	public String getProvince() {
		return this.province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	@Column(name = "CITY", length = 20)
	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	@Column(name = "REGION", length = 20)
	public String getRegion() {
		return this.region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	@Column(name = "ADDRESS", nullable = false, length = 100)
	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Column(name = "ZIP_CODE", length = 10)
	public String getZipCode() {
		return this.zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	@Column(name = "RECEIVER_PHONE", length = 20)
	public String getReceiverPhone() {
		return this.receiverPhone;
	}

	public void setReceiverPhone(String receiverPhone) {
		this.receiverPhone = receiverPhone;
	}

	@Column(name = "ORDER_REMARK", length = 100)
	public String getOrderRemark() {
		return this.orderRemark;
	}

	public void setOrderRemark(String orderRemark) {
		this.orderRemark = orderRemark;
	}

	@Column(name = "POINT", nullable = false)
	public Integer getPoint() {
		return this.point;
	}

	public void setPoint(Integer point) {
		this.point = point;
	}

	@Column(name = "STATUS", nullable = false)
	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	@Column(name = "DEAL_REMARK", length = 100)
	public String getDealRemark() {
		return this.dealRemark;
	}

	public void setDealRemark(String dealRemark) {
		this.dealRemark = dealRemark;
	}

	@Column(name = "DEAL_TIME", length = 19)
	public Timestamp getDealTime() {
		return this.dealTime;
	}

	public void setDealTime(Timestamp dealTime) {
		this.dealTime = dealTime;
	}

	@Column(name = "DEAL_CODE", nullable = false, length = 20)
	public String getDealCode() {
		return this.dealCode;
	}

	public void setDealCode(String dealCode) {
		this.dealCode = dealCode;
	}

	@Column(name = "ORDER_TIME", nullable = false, length = 19)
	public Timestamp getOrderTime() {
		return this.orderTime;
	}

	public void setOrderTime(Timestamp orderTime) {
		this.orderTime = orderTime;
	}

}