package com.example.demo.dto;

import java.io.Serializable;

public class BenefitManageQueryDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private String benefitName;
    private String shelfStatus;
    private String benefitCategory;

    public String getBenefitName() { return benefitName; }
    public void setBenefitName(String benefitName) { this.benefitName = benefitName; }

    public String getShelfStatus() { return shelfStatus; }
    public void setShelfStatus(String shelfStatus) { this.shelfStatus = shelfStatus; }

    public String getBenefitCategory() { return benefitCategory; }
    public void setBenefitCategory(String benefitCategory) { this.benefitCategory = benefitCategory; }
}
