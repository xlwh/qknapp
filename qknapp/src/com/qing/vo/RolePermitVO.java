package com.qing.vo;

/**
 * 角色权限实体类，提供给前台页面json使用
 * 
 * @author huangqingjian
 * 
 */
public class RolePermitVO {

    /**
     * 角色权限编号
     */
    private String rolePermitId;

    /**
     * 角色ID
     */
    private String roleID;

    /**
     * 权限ID
     */
    private String basePermitId;

    /**
     * 模块名称
     */
    private String moduleName;

    /**
     * 权限描述
     */
    private String permitDesc;

    /**
     * 该角色是否拥有该权限
     */
    private String isCheck;

    public String getRolePermitId() {
	return rolePermitId;
    }

    public void setRolePermitId(String rolePermitId) {
	this.rolePermitId = rolePermitId;
    }

    public String getRoleID() {
	return roleID;
    }

    public void setRoleID(String roleID) {
	this.roleID = roleID;
    }

    public String getBasePermitId() {
	return basePermitId;
    }

    public void setBasePermitId(String basePermitId) {
	this.basePermitId = basePermitId;
    }

    public String getModuleName() {
	return moduleName;
    }

    public void setModuleName(String moduleName) {
	this.moduleName = moduleName;
    }

    public String getPermitDesc() {
	return permitDesc;
    }

    public void setPermitDesc(String permitDesc) {
	this.permitDesc = permitDesc;
    }

    public String getIsCheck() {
	return isCheck;
    }

    public void setIsCheck(String isCheck) {
	this.isCheck = isCheck;
    }

}
