package com.example.demo.enums;

public enum PointsType {
    EARN("获取"),
    SPEND("消费"),
    EXPIRE("过期"),
    ADJUST("调整"),
    LEVEL_UP_BONUS("升级奖励");

    private final String description;

    PointsType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
