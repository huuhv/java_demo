package com.example.demo.classes;

// Lớp con kế thừa
class UserEntity extends BaseEntity {
    @Override
    public void validateData() {
        System.out.println("Kiểm tra email và password của User...");
    }
}
