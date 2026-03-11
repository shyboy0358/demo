package com.example.demo.enums;

public enum OperationType {
    CREATE("新增"),
    UPDATE("修改"),
    DELETE("删除"),
    SHELF_ON("上架"),
    SHELF_OFF("下架"),
    CLAIM("领取"),
    REMOVE_MEMBER("移除成员");

    private final String description;

    OperationType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
