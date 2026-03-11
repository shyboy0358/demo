package com.example.demo.enums;

public enum BenefitRecordStatus {
    PENDING("待领取"),
    CLAIMED("已领取"),
    USED("已使用"),
    EXPIRED("已过期");

    private final String description;

    BenefitRecordStatus(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
