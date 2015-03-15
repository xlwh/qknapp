package com.qing.enums;

/**
 * 搜索的网站名称
 * (一句话功能简述)
 * (功能详细描述)
 * @author       huangqingjian/0050
 * @see          [相关类,可选,也可多条,对于重要的类或接口建议注释]
 * @since        清清网系统, 2014年8月10日
 */
public enum NetTypeEnum {

	QINGQING("清清网直招", "10"), ZEROMINING("1010兼职网", "0"), THE58CITY("58同城", "1");

	// 成员变量
	private String name;

	private String index;

	// 构造方法
	private NetTypeEnum(String name, String index) {
		this.name = name;
		this.index = index;
	}

	// 普通方法
	public static String getName(String index) {
		for (NetTypeEnum c : NetTypeEnum.values()) {
			if (c.getIndex().equals(index)) {
				return c.name;
			}
		}
		return "";
	}

	// get set 方法
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIndex() {
		return index;
	}

	public void setIndex(String index) {
		this.index = index;
	}

}