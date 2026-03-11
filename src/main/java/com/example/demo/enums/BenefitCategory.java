package com.example.demo.enums;

public enum BenefitCategory {
    COURSE_COUPON("课程优惠券"),
    THIRD_PARTY_COUPON("第三方卡券"),
    FAMILY_POINTS("家庭积分共享"),
    EXPERIENCE("功能体验"),
    OTHER("其他");

    private final String description;

    BenefitCategory(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
