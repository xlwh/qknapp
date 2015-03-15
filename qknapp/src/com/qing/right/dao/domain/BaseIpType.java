package com.qing.right.dao.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * 用户登录ip限制表
 */
@Entity
@Table(name = "T_BASE_IP_TYPE")
@NamedQueries(
{ @NamedQuery(name = "baseiptype.query", query = "from BaseIpType p  where p.ipId=? "),
        @NamedQuery(name = "baseiptype.query1", query = "from BaseIpType p  where p.ipId=? and p.bgIp=? ") })
public class BaseIpType implements java.io.Serializable
{

    private static final long serialVersionUID = 4804876049798261576L;

    // Fields
    /**
     * IP ID号
     */
    private String ipId;

    /**
     * 版本号
     */
    private Integer version = 0;

    /**
     * IP类型，1表示IP段，2表示IP范围
     */
    private String ipType;

    /**
     * IP网段， 例如: 192.*.*.* ,192.168.*.* ,192.168.0.*
     */
    private String ipLimit;

    /**
     * 起始IP
     */
    private String bgIp;

    /**
     * 终止IP
     */
    private String enIp;

    /**
     * 用户编码集，可以保存一个到多个用户，用户编码之间用分号隔离。
     */
    private String subCode;

    /**
     * 执行类型：Y、允许 N、禁止
     */
    private String runType;

    /**
     * 启/停用生效标识,Y/N
     */
    private String okFlag;

    /**
     * 描述
     */
    private String ipDesc;

    /**
     * 建立时间
     */
    private Date createTime;

    /**
     * 修改时间
     */
    private Date modifTime;

    /**
     * 建立人
     */
    private String founder;

    /**
     * 修改人
     */
    private String modifMan;

    // Constructors

    /** default constructor */
    public BaseIpType()
    {
    }

    /** minimal constructor */
    public BaseIpType(String ipId)
    {
        this.ipId = ipId;
    }

    // Property accessors
    @Id
    @GenericGenerator(name = "idGenerator", strategy = "uuid")
    @GeneratedValue(generator = "idGenerator")
    @Column(name = "IP_ID", nullable = false, length = 32)
    public String getIpId()
    {
        return this.ipId;
    }

    public void setIpId(String ipId)
    {
        this.ipId = ipId;
    }

    @Column(name = "VERSION")
    public Integer getVersion()
    {
        return this.version;
    }

    public void setVersion(Integer version)
    {
        this.version = version;
    }

    @Column(name = "IP_TYPE", length = 1)
    public String getIpType()
    {
        return this.ipType;
    }

    public void setIpType(String ipType)
    {
        this.ipType = ipType;
    }

    @Column(name = "IP_LIMIT", length = 50)
    public String getIpLimit()
    {
        return this.ipLimit;
    }

    public void setIpLimit(String ipLimit)
    {
        this.ipLimit = ipLimit;
    }

    @Column(name = "BG_IP", length = 50)
    public String getBgIp()
    {
        return this.bgIp;
    }

    public void setBgIp(String bgIp)
    {
        this.bgIp = bgIp;
    }

    @Column(name = "EN_IP", length = 50)
    public String getEnIp()
    {
        return this.enIp;
    }

    public void setEnIp(String enIp)
    {
        this.enIp = enIp;
    }

    @Column(name = "SUB_CODE", length = 2000)
    public String getSubCode()
    {
        return this.subCode;
    }

    public void setSubCode(String subCode)
    {
        this.subCode = subCode;
    }

    @Column(name = "RUN_TYPE", length = 1)
    public String getRunType()
    {
        return this.runType;
    }

    public void setRunType(String runType)
    {
        this.runType = runType;
    }

    @Column(name = "OK_FLAG", length = 1)
    public String getOkFlag()
    {
        return this.okFlag;
    }

    public void setOkFlag(String okFlag)
    {
        this.okFlag = okFlag;
    }

    @Column(name = "IP_DESC", length = 500)
    public String getIpDesc()
    {
        return this.ipDesc;
    }

    public void setIpDesc(String ipDesc)
    {
        this.ipDesc = ipDesc;
    }

    @Column(name = "CREATE_TIME", length = 23)
    public Date getCreateTime()
    {
        return this.createTime;
    }

    public void setCreateTime(Date createTime)
    {
        this.createTime = createTime;
    }

    @Column(name = "MODIF_TIME", length = 23)
    public Date getModifTime()
    {
        return this.modifTime;
    }

    public void setModifTime(Date modifTime)
    {
        this.modifTime = modifTime;
    }

    @Column(name = "FOUNDER", length = 20)
    public String getFounder()
    {
        return this.founder;
    }

    public void setFounder(String founder)
    {
        this.founder = founder;
    }

    @Column(name = "MODIF_MAN", length = 20)
    public String getModifMan()
    {
        return this.modifMan;
    }

    public void setModifMan(String modifMan)
    {
        this.modifMan = modifMan;
    }

    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((ipId == null) ? 0 : ipId.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj)
    {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        BaseIpType other = (BaseIpType) obj;
        if (ipId == null)
        {
            if (other.ipId != null)
                return false;
        }
        else if (!ipId.equals(other.ipId))
            return false;
        return true;
    }
}