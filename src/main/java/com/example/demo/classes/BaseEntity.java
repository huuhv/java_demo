package com.example.demo.classes;
// Thêm từ khóa abstract
abstract class BaseEntity {
    protected Long id;
    protected String createdAt;

    // Hàm có logic sẵn (Concrete method) - Các class con xài chung
    public void printLog() {
        System.out.println("Entity created at: " + createdAt);
    }

    // Hàm trừu tượng (Abstract method) - Không có thân hàm!
    // Bắt buộc các class con (User, Product) phải tự viết logic cho hàm này.
    public abstract void validateData();
}
