package com.example.demo.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DatabaseExample {
    public static void main(String[] args) {
        // 1. Khai báo Connection String (Chuỗi kết nối)
        String dbUrl = "jdbc:mysql://localhost:3306/demo_java";
        String username = "root";
        String password = "Aa@123456";

        // Câu lệnh SQL (Dùng dấu ? để truyền tham số an toàn)
        String sql = "SELECT id, email FROM users";//" WHERE status = ?";

        // 2. Mở kết nối sử dụng try-with-resources để TỰ ĐỘNG ĐÓNG connection
        try (
                // Tạo Connection
                Connection conn = DriverManager.getConnection(dbUrl, username, password);
                // Chuẩn bị câu lệnh (PreparedStatement giúp chống SQL Injection)
                PreparedStatement pstmt = conn.prepareStatement(sql);
        ) {
            System.out.println("Kết nối Database thành công!");

            // 3. Gán giá trị thực tế vào dấu ?
            // pstmt.setString(1, "ACTIVE"); // 1 tương ứng với dấu ? đầu tiên

            // 4. Thực thi truy vấn và nhận kết quả (ResultSet)
            // Nếu là lệnh INSERT/UPDATE/DELETE, ta dùng pstmt.executeUpdate()
            try (ResultSet rs = pstmt.executeQuery()) {

                // 5. Duyệt qua từng dòng kết quả
                while (rs.next()) {
                    int id = rs.getInt("id");
                    String email = rs.getString("email");
                    System.out.println("User ID: " + id + " - Email: " + email);
                }
            }

        } catch (SQLException e) {
            // Xử lý Checked Exception của JDBC
            System.err.println("Lỗi thao tác Database!");
            e.printStackTrace();
        }
    }
}
