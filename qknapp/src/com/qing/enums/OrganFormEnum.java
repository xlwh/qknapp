package com.qing.enums;

/**
 * 
 * Description 组织形式枚举类
 * 
 * @author chen,hao-hao
 * @date 2013-6-28 上午10:40:34
 * @since 1.0
 */
public enum OrganFormEnum {
    /**
     * 企业实体组织
     */
    ENTITYORGAN("企业实体组织", "1"),
    /**
     * 企业虚拟组织
     */
    INVENTEDORGAN("企业虚拟组织", "2");
    // 成员变量
    private String name;
    private String index;

    // 构造方法
    private OrganFormEnum(String name, String index) {
	this.name = name;
	this.index = index;
    }

    // 普通方法
    public static String getName(String index) {
	for (OrganFormEnum c : OrganFormEnum.values()) {
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