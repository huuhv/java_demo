package com.example.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Simple Hello Controller for testing the application
 * Controller Hello đơn giản để kiểm tra ứng dụng
 *
 * @RestController: Marks this class as a REST controller that returns data directly
 *                  Đánh dấu lớp này là REST controller trả về dữ liệu trực tiếp
 */
@RestController
public class HelloController {

    /**
     * Simple GET endpoint to test if the application is running
     * Endpoint GET đơn giản để kiểm tra ứng dụng đang chạy
     *
     * HTTP Method: GET
     * URL: GET /hello
     *
     * @return A greeting message (Thông điệp chào mừng)
     */
    @GetMapping("/hello")
    public String sayHello() {
        return "Chào bạn! Tôi là Java Spring Boot, rất vui được gặp chuyên gia Laravel/NestJS!";
    }
}

