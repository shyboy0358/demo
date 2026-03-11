package com.example.demo.entity;

import java.io.Serializable;
import java.util.Date;

public class MemberBenefit implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private Long levelId;
    private String benefitName;
    private String benefitType;
    private String benefitValue;
    private String description;
    private String shelfStatus;
    private String goodsId;
    private String schoolId;
    private String department;
    private String claimType;
    private String benefitCategory;
    private String useUrl;
    private Integer status;
    private Date createTime;
    private Date updateTime;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getLevelId() { return levelId; }
    public void setLevelId(Long levelId) { this.levelId = levelId; }

    public String getBenefitName() { return benefitName; }
    public void setBenefitName(String benefitName) { this.benefitName = benefitName; }

    public String getBenefitType() { return benefitType; }
    public void setBenefitType(String benefitType) { this.benefitType = benefitType; }

    public String getBenefitValue() { return benefitValue; }
    public void setBenefitValue(String benefitValue) { this.benefitValue = benefitValue; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getShelfStatus() { return shelfStatus; }
    public void setShelfStatus(String shelfStatus) { this.shelfStatus = shelfStatus; }

    public String getGoodsId() { return goodsId; }
    public void setGoodsId(String goodsId) { this.goodsId = goodsId; }

    public String getSchoolId() { return schoolId; }
    public void setSchoolId(String schoolId) { this.schoolId = schoolId; }

    public String getDepartment() { return department; }
    public void setDepartment(String department) { this.department = department; }

    public String getClaimType() { return claimType; }
    public void setClaimType(String claimType) { this.claimType = claimType; }

    public String getBenefitCategory() { return benefitCategory; }
    public void setBenefitCategory(String benefitCategory) { this.benefitCategory = benefitCategory; }

    public String getUseUrl() { return useUrl; }
    public void setUseUrl(String useUrl) { this.useUrl = useUrl; }

    public Integer getStatus() { return status; }
    public void setStatus(Integer status) { this.status = status; }

    public Date getCreateTime() { return createTime; }
    public void setCreateTime(Date createTime) { this.createTime = createTime; }

    public Date getUpdateTime() { return updateTime; }
    public void setUpdateTime(Date updateTime) { this.updateTime = updateTime; }
}
