package com.qing.vo;

/**
 * @author guojun
 * @version 创建时间：2012-11-27 下午5:44:39
 */
public class OrganVO {

    /**
     * 组织ID
     */
    public String organId;
    /**
     * 组织父节点ID
     */
    public String organFatherId;
    /**
     * 组织名称
     */
    public String organName;
    /**
     * 组织级别
     */
    public String organLevel;

    /**
     * 组织树状图
     */
    public String organTree;

    /**
     * 组织树状图
     */
    public String organTreeFather;

    /**
     * 区分组织类型，1为公司，0为部门
     */
    public String organMark;

    /**
     * 区分组织类型,用于界面显示的内容
     */
    public String organMarkStr;

    /**
     * 组织形式： 1、企业实体组织 2、企业虚拟组织
     */
    private String organForm;

    /**
     * 组织形式：界面显示
     */
    private String organFormStr;

    /**
     * 组织类别： 1、集团总公司 2、下属全资公司 3、下属控股公司 4、下属参股公司 5、事业部 6、部门
     * 该字段对应同类型的数据字典表BasTypeDict
     */
    private String organType;

    // 前台界面显示
    private String organTypeStr;

    public OrganVO() {
    }

    public String getOrganId() {
	return organId;
    }

    public void setOrganId(String organId) {
	this.organId = organId;
    }

    public String getOrganFatherId() {
	return organFatherId;
    }

    public void setOrganFatherId(String organFatherId) {
	this.organFatherId = organFatherId;
    }

    public String getOrganName() {
	return organName;
    }

    public void setOrganName(String organName) {
	this.organName = organName;
    }

    public String getOrganLevel() {
	return organLevel;
    }

    public void setOrganLevel(String organLevel) {
	this.organLevel = organLevel;
    }

    public String getOrganTree() {
	return organTree;
    }

    public void setOrganTree(String organTree) {
	this.organTree = organTree;
    }

    public String getOrganTreeFather() {
	return organTreeFather;
    }

    public void setOrganTreeFather(String organTreeFather) {
	this.organTreeFather = organTreeFather;
    }

    public String getOrganMark() {
	return organMark;
    }

    public void setOrganMark(String organMark) {
	this.organMark = organMark;
    }

    public String getOrganMarkStr() {
	return organMarkStr;
    }

    public void setOrganMarkStr(String organMarkStr) {
	this.organMarkStr = organMarkStr;
    }

    public String getOrganForm() {
	return organForm;
    }

    public void setOrganForm(String organForm) {
	this.organForm = organForm;
    }

    public String getOrganFormStr() {
	return organFormStr;
    }

    public void setOrganFormStr(String organFormStr) {
	this.organFormStr = organFormStr;
    }

    public String getOrganType() {
	return organType;
    }

    public void setOrganType(String organType) {
	this.organType = organType;
    }

    public String getOrganTypeStr() {
	return organTypeStr;
    }

    public void setOrganTypeStr(String organTypeStr) {
	this.organTypeStr = organTypeStr;
    }

}
