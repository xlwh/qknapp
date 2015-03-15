package com.qing.vo;


/**
 * 角色用户实体类，提供给前台页面json使用
 * 
 * @author huangqingjian
 * 
 */
public class RoleUserVO {

    /**
     * 角色用户编号
     */
    private String roleUserId;

    /**
     * 系统登录名
     */
    private String loginCode;

    /**
     * 用户用户名称
     */
    private String userName;

    /**
     * 用户用户ID
     */
    private String userID;

    /**
     * 角色名称
     */
    private String roleName;

    /**
     * 角色ID
     */
    private String roleID;

    /**
     * 所属组织
     */
    private String organName;

    /**
     * 所属组织ID
     */
    private String organID;

    public String getRoleUserId() {
	return roleUserId;
    }

    public void setRoleUserId(String roleUserId) {
	this.roleUserId = roleUserId;
    }

    public String getLoginCode() {
	return loginCode;
    }

    public void setLoginCode(String loginCode) {
	this.loginCode = loginCode;
    }

    public String getUserName() {
	return userName;
    }

    public void setUserName(String userName) {
	this.userName = userName;
    }

    public String getUserID() {
	return userID;
    }

    public void setUserID(String userID) {
	this.userID = userID;
    }

    public String getRoleName() {
	return roleName;
    }

    public void setRoleName(String roleName) {
	this.roleName = roleName;
    }

    public String getRoleID() {
	return roleID;
    }

    public void setRoleID(String roleID) {
	this.roleID = roleID;
    }

    public String getOrganName() {
	return organName;
    }

    public void setOrganName(String organName) {
	this.organName = organName;
    }

    public String getOrganID() {
	return organID;
    }

    public void setOrganID(String organID) {
	this.organID = organID;
    }

}
