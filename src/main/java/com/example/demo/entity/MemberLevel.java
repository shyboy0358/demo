package com.example.demo.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class MemberLevel implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private String levelName;
    private String levelCode;
    private Integer levelSort;
    private Integer minPoints;
    private Integer maxPoints;
    private BigDecimal discount;
    private BigDecimal pointMultiplier;
    private String iconUrl;
    private String cardImageUrl;
    private String privilegeOverviewUrl;
    private String benefitOverviewUrl;
    private String benefitIntroUrl1;
    private String benefitIntroUrl2;
    private String lockedImageUrl;
    private String fallbackImageUrl;
    private String description;
    private Integer status;
    private Date createTime;
    private Date updateTime;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getLevelName() { return levelName; }
    public void setLevelName(String levelName) { this.levelName = levelName; }

    public String getLevelCode() { return levelCode; }
    public void setLevelCode(String levelCode) { this.levelCode = levelCode; }

    public Integer getLevelSort() { return levelSort; }
    public void setLevelSort(Integer levelSort) { this.levelSort = levelSort; }

    public Integer getMinPoints() { return minPoints; }
    public void setMinPoints(Integer minPoints) { this.minPoints = minPoints; }

    public Integer getMaxPoints() { return maxPoints; }
    public void setMaxPoints(Integer maxPoints) { this.maxPoints = maxPoints; }

    public BigDecimal getDiscount() { return discount; }
    public void setDiscount(BigDecimal discount) { this.discount = discount; }

    public BigDecimal getPointMultiplier() { return pointMultiplier; }
    public void setPointMultiplier(BigDecimal pointMultiplier) { this.pointMultiplier = pointMultiplier; }

    public String getIconUrl() { return iconUrl; }
    public void setIconUrl(String iconUrl) { this.iconUrl = iconUrl; }

    public String getCardImageUrl() { return cardImageUrl; }
    public void setCardImageUrl(String cardImageUrl) { this.cardImageUrl = cardImageUrl; }

    public String getPrivilegeOverviewUrl() { return privilegeOverviewUrl; }
    public void setPrivilegeOverviewUrl(String privilegeOverviewUrl) { this.privilegeOverviewUrl = privilegeOverviewUrl; }

    public String getBenefitOverviewUrl() { return benefitOverviewUrl; }
    public void setBenefitOverviewUrl(String benefitOverviewUrl) { this.benefitOverviewUrl = benefitOverviewUrl; }

    public String getBenefitIntroUrl1() { return benefitIntroUrl1; }
    public void setBenefitIntroUrl1(String benefitIntroUrl1) { this.benefitIntroUrl1 = benefitIntroUrl1; }

    public String getBenefitIntroUrl2() { return benefitIntroUrl2; }
    public void setBenefitIntroUrl2(String benefitIntroUrl2) { this.benefitIntroUrl2 = benefitIntroUrl2; }

    public String getLockedImageUrl() { return lockedImageUrl; }
    public void setLockedImageUrl(String lockedImageUrl) { this.lockedImageUrl = lockedImageUrl; }

    public String getFallbackImageUrl() { return fallbackImageUrl; }
    public void setFallbackImageUrl(String fallbackImageUrl) { this.fallbackImageUrl = fallbackImageUrl; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public Integer getStatus() { return status; }
    public void setStatus(Integer status) { this.status = status; }

    public Date getCreateTime() { return createTime; }
    public void setCreateTime(Date createTime) { this.createTime = createTime; }

    public Date getUpdateTime() { return updateTime; }
    public void setUpdateTime(Date updateTime) { this.updateTime = updateTime; }
}
