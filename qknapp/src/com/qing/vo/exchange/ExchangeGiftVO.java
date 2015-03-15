/*
 * Title:        清清网系统 2014-8-20
 * Description:  [描述模块的功能、作用、使用方法和注意事项]
 * Copyright:    Copyright (c) 2014
 * Company:      清清网
 * @author       luoqinglong
 * @version      2.0  2014-8-20
 */
package com.qing.vo.exchange;

/**
 * 礼品
 * @author       luoqinglong
 * @since        清清网系统, 2014-8-20
 */
public class ExchangeGiftVO {

	private String giftName;
	private Short exchangeType;

	public String getGiftName() {
		return this.giftName;
	}

	public void setGiftName(String giftName) {
		this.giftName = giftName;
	}

	public Short getExchangeType() {
		return this.exchangeType;
	}

	public void setExchangeType(Short exchangeType) {
		this.exchangeType = exchangeType;
	}

}
