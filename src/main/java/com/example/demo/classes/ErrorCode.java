package com.example.demo.classes;

public enum ErrorCode {
    // 1. Định nghĩa các hằng số và truyền tham số vào Constructor
    NOT_FOUND(404, "Không tìm thấy dữ liệu"),
    UNAUTHORIZED(401, "Chưa đăng nhập"),
    SERVER_ERROR(500, "Lỗi hệ thống nghiêm trọng"); // Phải có dấu chấm phẩy ở cuối danh sách!

    // 2. Khai báo thuộc tính (Nên dùng final để không bị thay đổi)
    private final int code;
    private final String message;

    // 3. Hàm khởi tạo (Constructor của Enum luôn ngầm định là private)
    ErrorCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    // 4. Các hàm Getter để lấy dữ liệu ra
    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
