/*
 * Title:        美芝澳二维码系统 2014年1月24日
 * Description:  [描述模块的功能、作用、使用方法和注意事项]
 * Copyright:    Copyright (c) 2012
 * Company:      Tai woo. Co., Ltd
 * @author       huangqingjian/0050
 * @version      1.0  2014年1月24日
 */
package com.work.domain;

/**
 * 运单具体的内容信息
 * (一句话功能简述)
 * (功能详细描述)
 * @author       huangqingjian/0050
 * @see          [相关类,可选,也可多条,对于重要的类或接口建议注释]
 * @since        美芝澳二维码系统, 2014年1月24日
 */
public class ExpressContent {
	//发货人公司名
	private String senderCompanyName;
	//货到付款物流编号
	private String orderCode;
	//发货地址
	private String senderAddress;
	//发货邮编
	private String senderPostCode;

	//收件人名称
	private String receiverName;

	//收件人电话
	private String receiverTel;

	//订单编号
	private String orderId;
	//发货人名
	private String senderName;
	//收货人地址
	private String receiverAddress;
	//发货人电话
	private String senderTel;

	//收件人邮编
	private String receiverPostCode;

	//代收金额
	private String collectMoney;
	//备注
	private String userDefine;

	public String getSenderCompanyName() {
		return senderCompanyName;
	}

	public void setSenderCompanyName(String senderCompanyName) {
		this.senderCompanyName = senderCompanyName;
	}

	public String getOrderCode() {
		return orderCode;
	}

	public void setOrderCode(String orderCode) {
		this.orderCode = orderCode;
	}

	public String getSenderAddress() {
		return senderAddress;
	}

	public void setSenderAddress(String senderAddress) {
		this.senderAddress = senderAddress;
	}

	public String getSenderPostCode() {
		return senderPostCode;
	}

	public void setSenderPostCode(String senderPostCode) {
		this.senderPostCode = senderPostCode;
	}

	public String getReceiverName() {
		return receiverName;
	}

	public void setReceiverName(String receiverName) {
		this.receiverName = receiverName;
	}

	public String getReceiverTel() {
		return receiverTel;
	}

	public void setReceiverTel(String receiverTel) {
		this.receiverTel = receiverTel;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getSenderName() {
		return senderName;
	}

	public void setSenderName(String senderName) {
		this.senderName = senderName;
	}

	public String getReceiverAddress() {
		return receiverAddress;
	}

	public void setReceiverAddress(String receiverAddress) {
		this.receiverAddress = receiverAddress;
	}

	public String getSenderTel() {
		return senderTel;
	}

	public void setSenderTel(String senderTel) {
		this.senderTel = senderTel;
	}

	public String getReceiverPostCode() {
		return receiverPostCode;
	}

	public void setReceiverPostCode(String receiverPostCode) {
		this.receiverPostCode = receiverPostCode;
	}

	public String getCollectMoney() {
		return collectMoney;
	}

	public void setCollectMoney(String collectMoney) {
		this.collectMoney = collectMoney;
	}

	public String getUserDefine() {
		return userDefine;
	}

	public void setUserDefine(String userDefine) {
		this.userDefine = userDefine;
	}

}
