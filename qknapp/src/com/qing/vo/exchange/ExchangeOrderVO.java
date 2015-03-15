/*
 * Title:        清清网系统 2014-8-19
 * Description:  [描述模块的功能、作用、使用方法和注意事项]
 * Copyright:    Copyright (c) 2014
 * Company:      清清网
 * @author       luoqinglong
 * @version      2.0  2014-8-19
 */
package com.qing.vo.exchange;

import java.sql.Timestamp;
import java.util.Date;

/**
 * (一句话功能简述)
 * (功能详细描述)
 * @author       luoqinglong
 * @see          [相关类,可选,也可多条,对于重要的类或接口建议注释]
 * @since        清清网系统, 2014-8-19
 */
public class ExchangeOrderVO {
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
	private Date dealTime;
	private String dealCode;
	private Date orderTime;

	public String getExchangeOrderId() {
		return this.exchangeOrderId;
	}

	public void setExchangeOrderId(String exchangeOrderId) {
		this.exchangeOrderId = exchangeOrderId;
	}

	public String getMemberId() {
		return this.memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public String getOrderSn() {
		return this.orderSn;
	}

	public void setOrderSn(String orderSn) {
		this.orderSn = orderSn;
	}

	public String getReceiver() {
		return this.receiver;
	}

	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}

	public String getProvince() {
		return this.province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getRegion() {
		return this.region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getZipCode() {
		return this.zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public String getReceiverPhone() {
		return this.receiverPhone;
	}

	public void setReceiverPhone(String receiverPhone) {
		this.receiverPhone = receiverPhone;
	}

	public String getOrderRemark() {
		return this.orderRemark;
	}

	public void setOrderRemark(String orderRemark) {
		this.orderRemark = orderRemark;
	}

	public Integer getPoint() {
		return this.point;
	}

	public void setPoint(Integer point) {
		this.point = point;
	}

	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getDealRemark() {
		return this.dealRemark;
	}

	public void setDealRemark(String dealRemark) {
		this.dealRemark = dealRemark;
	}

	public void setDealTime(Timestamp dealTime) {
		this.dealTime = dealTime;
	}

	public String getDealCode() {
		return this.dealCode;
	}

	public void setDealCode(String dealCode) {
		this.dealCode = dealCode;
	}

	public Date getDealTime() {
		return this.dealTime;
	}

	public void setDealTime(Date dealTime) {
		this.dealTime = dealTime;
	}

	public Date getOrderTime() {
		return this.orderTime;
	}

	public void setOrderTime(Date orderTime) {
		this.orderTime = orderTime;
	}

	public void setOrderTime(Timestamp orderTime) {
		this.orderTime = orderTime;
	}

	@Override
	public String toString() {
		return "ExchangeOrderVO [exchangeOrderId=" + this.exchangeOrderId + ", memberId=" + this.memberId
				+ ", orderSn=" + this.orderSn + ", receiver=" + this.receiver + ", province=" + this.province
				+ ", city=" + this.city + ", region=" + this.region + ", address=" + this.address + ", zipCode="
				+ this.zipCode + ", receiverPhone=" + this.receiverPhone + ", orderRemark=" + this.orderRemark
				+ ", point=" + this.point + ", status=" + this.status + ", dealRemark=" + this.dealRemark
				+ ", dealTime=" + this.dealTime + ", dealCode=" + this.dealCode + ", orderTime=" + this.orderTime + "]";
	}

}
