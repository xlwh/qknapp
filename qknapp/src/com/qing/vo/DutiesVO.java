package com.qing.vo;

public class DutiesVO {
    /**
     * 职务名称
     */
    private String dutiename;
    
    /**
     * 所属组织
     */
    private String organName;
    
    /**
     * 职务描述
     */
    private String dutiedesc;
    
    /**
     * 组织编号
     */
    private String organId;

    /**
     * 职位编号
     */
    private String dutieid;
    
    public String getDutieid() {
        return dutieid;
    }

    public void setDutieid(String dutieid) {
        this.dutieid = dutieid;
    }

  
    public String getOrganId() {
        return organId;
    }

    public void setOrganId(String organId) {
        this.organId = organId;
    }

    public String getDutiename() {
        return dutiename;
    }

    public void setDutiename(String dutiename) {
        this.dutiename = dutiename;
    }

    public String getOrganName() {
        return organName;
    }

    public void setOrganName(String organName) {
        this.organName = organName;
    }

    public String getDutiedesc() {
        return dutiedesc;
    }

    public void setDutiedesc(String dutiedesc) {
        this.dutiedesc = dutiedesc;
    }

   
}
