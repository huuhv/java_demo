package com.example.demo.classes;

interface UserRepository {
    // Không cần ghi public abstract, Java tự hiểu!
    void saveUser(String name);
    boolean checkEmailExists(String email);
}
