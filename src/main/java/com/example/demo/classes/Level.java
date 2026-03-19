package com.example.demo.classes;

// Khai báo cơ bản (Các giá trị luôn viết HOA theo chuẩn)
public enum Level {
//    LOW,
//    MEDIUM,
//    HIGH
// Enum constants (each has its own description)
    LOW("Low level"),
    MEDIUM("Medium level"),
    HIGH("High level");

    // Field (variable) to store the description text
    private String description;

    // Constructor (runs once for each constant above)
    private Level(String description) {
        this.description = description;
    }

    // Getter method to read the description
    public String getDescription() {
        return description;
    }
}
