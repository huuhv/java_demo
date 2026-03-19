package com.example.demo.classes;

// Thực thi hợp đồng (Dùng từ khóa implements thay vì extends)
class UserRepositoryImpl implements UserRepository {
    @Override
    public void saveUser(String name) {
        System.out.println("Lưu " + name + " vào MySQL...");
    }

    @Override
    public boolean checkEmailExists(String email) {
        return true;
    }
}
