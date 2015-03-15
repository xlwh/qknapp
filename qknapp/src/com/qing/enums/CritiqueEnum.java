package com.qing.enums;

/**
 * 搜索的网站名称
 * (一句话功能简述)
 * (功能详细描述)
 * @author       huangqingjian/0050
 * @see          [相关类,可选,也可多条,对于重要的类或接口建议注释]
 * @since        清清网系统, 2014年8月10日
 */
public enum CritiqueEnum {

	PRAISE("点赞", 0), SELLMEN("卖男人", 1),  BROWSE("浏览量", 3), CONSULTATION("咨询量", 4), NOTINTERESTED("没兴趣", 5);

	// 成员变量
	private String name;

	private int index;

	// 构造方法
	private CritiqueEnum(String name, int index) {
		this.name = name;
		this.index = index;
	}

	// 普通方法
	public static String getName(int index) {
		for (CritiqueEnum c : CritiqueEnum.values()) {
			if (c.getIndex()==index) {
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

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	

}