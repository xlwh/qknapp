package com.qing.right.dao.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * BaseAgent entity. @author MyEclipse Persistence Tools
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "T_BASE_AGENT")
public class BaseAgent implements java.io.Serializable {

    // Fields

    /**
     * 授权ID号
     */
    private String agentId;

    /**
     * 版本号
     */
    private Integer version;

    /**
     * 代理用户ID
     */
    private String accreditUserId;

    /**
     * 代理用户姓名
     */
    private String accreditUserName;

    /**
     * 开始时间
     */
    private Date bgTime;

    /**
     * 建立时间
     */
    private Date createTime;

    /**
     * 结束时间
     */
    private Date enTime;

    /**
     * 建立人
     */
    private String founder;

    /**
     * 1：表示永久 2：表示时间段, 该字段对应同类型的数据字典表BasTypeDict
     */
    private String howLong;

    /**
     * 修改人
     */
    private String modifMan;

    /**
     * 修改时间
     */
    private Date modifTime;

    /**
     * 模块ID （来自授权用户的可用模块）
     */
    private Integer moduleId;

    /**
     * 模块名称
     */
    private String moduleName;

    /**
     * 授权用户ID（本人授权自动过来，超级拥护需要选择）
     */
    private String userId;

    /**
     * 授权用户姓名（本人授权自动过来，超级拥护需要选择）
     */
    private String userName;

    // Constructors

    /** default constructor */
    public BaseAgent() {
    }

    /** full constructor */
    public BaseAgent(String accreditUserId, String accreditUserName, Date bgTime, Date createTime, Date enTime, String founder,
	    String howLong, String modifMan, Date modifTime, Integer moduleId, String moduleName, String userId, String userName) {
	this.accreditUserId = accreditUserId;
	this.accreditUserName = accreditUserName;
	this.bgTime = bgTime;
	this.createTime = createTime;
	this.enTime = enTime;
	this.founder = founder;
	this.howLong = howLong;
	this.modifMan = modifMan;
	this.modifTime = modifTime;
	this.moduleId = moduleId;
	this.moduleName = moduleName;
	this.userId = userId;
	this.userName = userName;
    }

    // Property accessors
    @GenericGenerator(name = "generator", strategy = "uuid.hex")
    @Id
    @GeneratedValue(generator = "generator")
    @Column(name = "AGENT_ID", unique = true, nullable = false, length = 32)
    public String getAgentId() {
	return this.agentId;
    }

    public void setAgentId(String agentId) {
	this.agentId = agentId;
    }

    @Column(name = "VERSION")
    public Integer getVersion() {
	return this.version;
    }

    public void setVersion(Integer version) {
	this.version = version;
    }

    @Column(name = "ACCREDIT_USER_ID", length = 20)
    public String getAccreditUserId() {
	return this.accreditUserId;
    }

    public void setAccreditUserId(String accreditUserId) {
	this.accreditUserId = accreditUserId;
    }

    @Column(name = "ACCREDIT_USER_NAME", length = 30)
    public String getAccreditUserName() {
	return this.accreditUserName;
    }

    public void setAccreditUserName(String accreditUserName) {
	this.accreditUserName = accreditUserName;
    }

    @Column(name = "BG_TIME", length = 23)
    public Date getBgTime() {
	return this.bgTime;
    }

    public void setBgTime(Date bgTime) {
	this.bgTime = bgTime;
    }

    @Column(name = "CREATE_TIME", length = 23)
    public Date getCreateTime() {
	return this.createTime;
    }

    public void setCreateTime(Date createTime) {
	this.createTime = createTime;
    }

    @Column(name = "EN_TIME", length = 23)
    public Date getEnTime() {
	return this.enTime;
    }

    public void setEnTime(Date enTime) {
	this.enTime = enTime;
    }

    @Column(name = "FOUNDER", length = 20)
    public String getFounder() {
	return this.founder;
    }

    public void setFounder(String founder) {
	this.founder = founder;
    }

    @Column(name = "HOW_LONG", length = 1)
    public String getHowLong() {
	return this.howLong;
    }

    public void setHowLong(String howLong) {
	this.howLong = howLong;
    }

    @Column(name = "MODIF_MAN", length = 20)
    public String getModifMan() {
	return this.modifMan;
    }

    public void setModifMan(String modifMan) {
	this.modifMan = modifMan;
    }

    @Column(name = "MODIF_TIME", length = 23)
    public Date getModifTime() {
	return this.modifTime;
    }

    public void setModifTime(Date modifTime) {
	this.modifTime = modifTime;
    }

    @Column(name = "MODULE_ID")
    public Integer getModuleId() {
	return this.moduleId;
    }

    public void setModuleId(Integer moduleId) {
	this.moduleId = moduleId;
    }

    @Column(name = "MODULE_NAME", length = 50)
    public String getModuleName() {
	return this.moduleName;
    }

    public void setModuleName(String moduleName) {
	this.moduleName = moduleName;
    }

    @Column(name = "USER_ID", length = 20)
    public String getUserId() {
	return this.userId;
    }

    public void setUserId(String userId) {
	this.userId = userId;
    }

    @Column(name = "USER_NAME", length = 30)
    public String getUserName() {
	return this.userName;
    }

    public void setUserName(String userName) {
	this.userName = userName;
    }

}