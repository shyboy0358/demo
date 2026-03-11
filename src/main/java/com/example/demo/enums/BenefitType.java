package com.example.demo.enums;

public enum BenefitType {
    DISCOUNT("折扣优惠"),
    POINTS_MULTIPLIER("积分倍率"),
    FREE_SHIPPING("免运费"),
    BIRTHDAY_GIFT("生日礼包"),
    EXCLUSIVE_PRODUCT("专属商品"),
    PRIORITY_SERVICE("优先客服");

    private final String description;

    BenefitType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
