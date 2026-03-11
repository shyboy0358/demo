package com.example.demo.enums;

public enum FamilyRole {
    PARENT("家长"),
    STUDENT("学员"),
    OTHER("其他");

    private final String description;

    FamilyRole(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
