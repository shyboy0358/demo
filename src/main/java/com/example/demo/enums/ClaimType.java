package com.example.demo.enums;

public enum ClaimType {
    MANUAL("手动领取"),
    AUTO("自动发放");

    private final String description;

    ClaimType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
