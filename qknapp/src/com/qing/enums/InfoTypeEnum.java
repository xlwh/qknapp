package com.qing.enums;

/**
 * 信息类型
 * (一句话功能简述)
 * (功能详细描述)
 * @author       huangqingjian/0050
 * @see          [相关类,可选,也可多条,对于重要的类或接口建议注释]
 * @since        清清网系统, 2014年8月10日
 */
public enum InfoTypeEnum {

	PARTTIME("兼职信息", 0), RESUME("简历信息", 1), CIRCLE("实践圈", 2),BUSINESSDIRECTORY("企业名录", 3);

	// 成员变量
	private String name;

	private int index;

	// 构造方法
	private InfoTypeEnum(String name, int index) {
		this.name = name;
		this.index = index;
	}

	// 普通方法
	public static String getName(int index) {
		for (InfoTypeEnum c : InfoTypeEnum.values()) {
			if (c.getIndex() == index) {
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