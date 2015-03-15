package com.qing.enums;

/**
 * 举报的信息类型
 * (一句话功能简述)
 * (功能详细描述)
 * @author       huangqingjian/0050
 * @see          [相关类,可选,也可多条,对于重要的类或接口建议注释]
 * @since        清清网系统, 2014年8月10日
 */
public enum ComplaintReasonEnum {
	//色情、欺诈、骚扰、不靠谱、侵权、其他
	SALACITY("色情", 0), CHEAT("欺诈", 1), HARASS("骚扰", 2), UNRELIABLE("不靠谱", 3), TORT("侵权", 4),OTHER("其他", 5);

	// 成员变量
	private String name;

	private int index;

	// 构造方法
	private ComplaintReasonEnum(String name, int index) {
		this.name = name;
		this.index = index;
	}

	// 普通方法
	public static String getName(int index) {
		for (ComplaintReasonEnum c : ComplaintReasonEnum.values()) {
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