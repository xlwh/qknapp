package com.qing.vo;

public class ZtreeVo {
    private String id;
    private String pId;
    private String name;

    private String levels;
    /**
     * 组织类别:化妆品参照OrganTypeEnum.java的定义
     */
    private String organType;

    public String getId() {
	return id;
    }

    public void setId(String id) {
	this.id = id;
    }

    public String getpId() {
	return pId;
    }

    public void setpId(String pId) {
	this.pId = pId;
    }

    public String getName() {
	return name;
    }

    public void setName(String name) {
	this.name = name;
    }

    /**
     * @return the organType
     **/
    public String getOrganType() {
	return organType;
    }

    /**
     * @param organType
     *            the organType to set
     **/
    public void setOrganType(String organType) {
	this.organType = organType;
    }

    public String getLevels() {
	return levels;
    }

    public void setLevels(String levels) {
	this.levels = levels;
    }

}
