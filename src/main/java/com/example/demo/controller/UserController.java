package com.example.demo.controller;

import com.example.demo.model.User;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST Controller for managing User CRUD operations
 * Controller REST để quản lý các thao tác CRUD của User
 *
 * MVC Pattern / Mô hình MVC:
 *   - Model: User entity (Thực thể User)
 *   - View: JSON response (Phản hồi dạng JSON)
 *   - Controller: This class - handles HTTP requests (Lớp này - xử lý các yêu cầu HTTP)
 *
 * @RestController: Combines @Controller + @ResponseBody, returns JSON/XML directly
 *                  Kết hợp @Controller + @ResponseBody, trả về JSON/XML trực tiếp
 * @RequestMapping: Sets base URL path for all endpoints in this controller
 *                  Thiết lập đường dẫn URL gốc cho tất cả endpoints trong controller này
 *
 * Base URL: /api/users
 */
@RestController
@RequestMapping("/api/users")
public class UserController {

    /**
     * Inject UserService - business logic layer
     * Inject UserService - tầng xử lý logic nghiệp vụ
     *
     * @Autowired: Spring automatically injects the dependency
     *             Spring tự động inject dependency
     */
    @Autowired
    private UserService userService;

    // ==================== CREATE / TẠO MỚI ====================

    /**
     * Create a new user
     * Tạo mới một user
     *
     * HTTP Method: POST
     * URL: POST /api/users
     * Request Body: User object in JSON format (Đối tượng User dạng JSON)
     * Response: Created user with HTTP 201 (CREATED) status
     *           User đã tạo với HTTP 201 (CREATED) status
     *
     * @param user The user data from request body (Dữ liệu user từ request body)
     * @return ResponseEntity containing created user (ResponseEntity chứa user đã tạo)
     */
    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        try {
            // Call service layer to save user to database
            // Gọi tầng service để lưu user vào database
            User createdUser = userService.createUser(user);

            // Return created user with HTTP 201 CREATED status
            // Trả về user đã tạo với HTTP 201 CREATED status
            return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
        } catch (Exception e) {
            // If error occurs, return HTTP 500 INTERNAL_SERVER_ERROR
            // Nếu có lỗi, trả về HTTP 500 INTERNAL_SERVER_ERROR
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // ==================== READ ALL / ĐỌC TẤT CẢ ====================

    /**
     * Retrieve all users from database
     * Lấy tất cả users từ database
     *
     * HTTP Method: GET
     * URL: GET /api/users
     * Response: List of users (HTTP 200) or empty (HTTP 204)
     *           Danh sách users (HTTP 200) hoặc rỗng (HTTP 204)
     *
     * @return ResponseEntity containing list of users (ResponseEntity chứa danh sách users)
     */
    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        try {
            // Fetch all users from database via service layer
            // Lấy tất cả users từ database thông qua tầng service
            List<User> users = userService.getAllUsers();

            // If no users found, return HTTP 204 NO_CONTENT
            // Nếu không tìm thấy users nào, trả về HTTP 204 NO_CONTENT
            if (users.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            // Return list of users with HTTP 200 OK
            // Trả về danh sách users với HTTP 200 OK
            return new ResponseEntity<>(users, HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // ==================== READ BY ID / ĐỌC THEO ID ====================

    /**
     * Retrieve a user by their ID
     * Lấy user theo ID
     *
     * HTTP Method: GET
     * URL: GET /api/users/{id}
     * Path Variable: id - User's unique identifier (Mã định danh duy nhất của user)
     * Response: User object (HTTP 200) or not found (HTTP 404)
     *
     * @param id The user's ID from URL path (ID của user từ đường dẫn URL)
     * @return ResponseEntity containing the user or not found status
     */
    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable("id") Long id) {
        try {
            // Find user by ID, returns Optional<User>
            // Tìm user theo ID, trả về Optional<User>
            return userService.getUserById(id)
                    // If found: wrap in ResponseEntity with HTTP 200 OK
                    // Nếu tìm thấy: đóng gói trong ResponseEntity với HTTP 200 OK
                    .map(user -> new ResponseEntity<>(user, HttpStatus.OK))
                    // If not found: return HTTP 404 NOT_FOUND
                    // Nếu không tìm thấy: trả về HTTP 404 NOT_FOUND
                    .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // ==================== READ BY EMAIL / ĐỌC THEO EMAIL ====================

    /**
     * Retrieve a user by their email address
     * Lấy user theo địa chỉ email
     *
     * HTTP Method: GET
     * URL: GET /api/users/email/{email}
     * Path Variable: email - User's email address (Địa chỉ email của user)
     * Response: User object (HTTP 200) or not found (HTTP 404)
     *
     * @param email The email from URL path (Email từ đường dẫn URL)
     * @return ResponseEntity containing the user or not found status
     */
    @GetMapping("/email/{email}")
    public ResponseEntity<User> getUserByEmail(@PathVariable("email") String email) {
        try {
            // Find user by email, returns Optional<User>
            // Tìm user theo email, trả về Optional<User>
            return userService.getUserByEmail(email)
                    // If found: wrap in ResponseEntity with HTTP 200 OK
                    // Nếu tìm thấy: đóng gói trong ResponseEntity với HTTP 200 OK
                    .map(user -> new ResponseEntity<>(user, HttpStatus.OK))
                    // If not found: return HTTP 404 NOT_FOUND
                    // Nếu không tìm thấy: trả về HTTP 404 NOT_FOUND
                    .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // ==================== UPDATE / CẬP NHẬT ====================

    /**
     * Update an existing user's information
     * Cập nhật thông tin của user hiện tại
     *
     * HTTP Method: PUT
     * URL: PUT /api/users/{id}
     * Path Variable: id - User's ID to update (ID user cần cập nhật)
     * Request Body: Updated user data in JSON (Dữ liệu user cập nhật dạng JSON)
     * Response: Updated user (HTTP 200) or not found (HTTP 404)
     *
     * @param id   The user's ID from URL path (ID của user từ đường dẫn URL)
     * @param user The updated user data from request body (Dữ liệu user cập nhật từ request body)
     * @return ResponseEntity containing updated user or error status
     */
    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable("id") Long id, @RequestBody User user) {
        try {
            // Call service to update user with new data
            // Gọi service để cập nhật user với dữ liệu mới
            User updatedUser = userService.updateUser(id, user);

            // Return updated user with HTTP 200 OK
            // Trả về user đã cập nhật với HTTP 200 OK
            return new ResponseEntity<>(updatedUser, HttpStatus.OK);
        } catch (RuntimeException e) {
            // User not found - return HTTP 404
            // Không tìm thấy user - trả về HTTP 404
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // ==================== DELETE / XÓA ====================

    /**
     * Delete a user by their ID
     * Xóa user theo ID
     *
     * HTTP Method: DELETE
     * URL: DELETE /api/users/{id}
     * Path Variable: id - User's ID to delete (ID user cần xóa)
     * Response: HTTP 204 (success), HTTP 404 (not found), or HTTP 500 (error)
     *
     * @param id The user's ID from URL path (ID của user từ đường dẫn URL)
     * @return ResponseEntity with appropriate HTTP status
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteUser(@PathVariable("id") Long id) {
        try {
            // Call service to delete user from database
            // Gọi service để xóa user khỏi database
            userService.deleteUser(id);

            // Return HTTP 204 NO_CONTENT (deleted successfully, no content to return)
            // Trả về HTTP 204 NO_CONTENT (xóa thành công, không có nội dung trả về)
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (RuntimeException e) {
            // User not found - return HTTP 404
            // Không tìm thấy user - trả về HTTP 404
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

