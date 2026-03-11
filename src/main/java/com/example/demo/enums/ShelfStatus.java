package com.example.demo.enums;

public enum ShelfStatus {
    PENDING("待上架"),
    ON_SHELF("已上架"),
    OFF_SHELF("已下架");

    private final String description;

    ShelfStatus(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
