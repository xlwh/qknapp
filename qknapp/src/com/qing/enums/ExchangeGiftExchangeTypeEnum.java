/*
 * Title:        清清网系统 2014-8-25
 * Description:  [描述模块的功能、作用、使用方法和注意事项]
 * Copyright:    Copyright (c) 2014
 * Company:      清清网
 * @author       luoqinglong
 * @version      2.0  2014-8-25
 */
package com.qing.enums;

/**
 * 礼品类型
 * @author       luoqinglong
 * @see          [相关类,可选,也可多条,对于重要的类或接口建议注释]
 * @since        清清网系统, 2014-8-25
 */
public enum ExchangeGiftExchangeTypeEnum {
	/**
	 * 1, "实物"
	 */
	OBJECT(1, "实物"),
	/**
	 * 2, "虚拟"
	 */
	SUBJECT(2, "虚拟");

	int index;
	String description;

	private ExchangeGiftExchangeTypeEnum(int index, String description) {
		this.index = index;
		this.description = description;
	}

	public int getIndex() {
		return this.index;
	}

	public String getDescription() {
		return this.description;
	}

}
