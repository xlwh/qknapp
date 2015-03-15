package com.qing.vo;

/**
 * 角色实体类，提供给前台页面json使用
 * 
 * @author huangqingjian
 * 
 */
public class RoleVO {

    /**
     * 角色编号
     */
    private String roleId;

    /**
     * 组织编号
     */
    private String organID;

    /**
     * 组织名称
     */
    private String organName;

    /**
     * 启/停用生效标识 Y/N
     */
    private String okFlag;

    /**
     * 角色描述
     */
    private String roleDesc;

    /**
     * 角色名称
     */
    private String roleName;

    public String getRoleId() {
	return roleId;
    }

    public void setRoleId(String roleId) {
	this.roleId = roleId;
    }

    public String getOkFlag() {
	return okFlag;
    }

    public String getOrganID() {
	return organID;
    }

    public void setOrganID(String organID) {
	this.organID = organID;
    }

    public String getOrganName() {
	return organName;
    }

    public void setOrganName(String organName) {
	this.organName = organName;
    }

    public void setOkFlag(String okFlag) {
	this.okFlag = okFlag;
    }

    public String getRoleDesc() {
	return roleDesc;
    }

    public void setRoleDesc(String roleDesc) {
	this.roleDesc = roleDesc;
    }

    public String getRoleName() {
	return roleName;
    }

    public void setRoleName(String roleName) {
	this.roleName = roleName;
    }

}
