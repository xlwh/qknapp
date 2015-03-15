package com.qing.enums;

/**
 * 
 * Description 组织类型枚举类
 * 
 * @author chen,hao-hao
 * @date 2013-6-28 上午10:39:54
 * @since 1.0
 */
public enum OrganTypeEnum {

	/**
	 * 超级管理员所在的组织，是一个系统级的(0)
	 */
	ADMIN_ORGAN("顶层组织", "0"),
	/**
	 * 集团总公司(1)
	 */
	TOPGROUP("集团总公司", "1"),
	/**
	 * 品牌(2)
	 */
	SGROUP("品牌", "2"),
	/**
	 * 代理商(3)
	 */
	TGROUP("代理商", "3"),
	/**
	 * 门店(4)
	 */
	FGROUP("加盟店", "4"),
	/**
	 * 工厂(5)
	 */
	FACTORYGROUP("工厂", "5"),
	/**
	 * 直营店(6)
	 */
	DIRECTSALESTORE("直营店", "6"),

	/**
	 * 部门(7)
	 */
	DEPARTMENT("部门", "7");

	// , FIGROUP("事业部", "5"), COMPANY("部门", "6");
	// 成员变量
	private String name;

	private String index;

	// 构造方法
	private OrganTypeEnum(String name, String index) {
		this.name = name;
		this.index = index;
	}

	// 普通方法
	public static String getName(String index) {
		for (OrganTypeEnum c : OrganTypeEnum.values()) {
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