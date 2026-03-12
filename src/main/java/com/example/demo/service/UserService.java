package com.example.demo.service;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Service layer for User business logic
 * Tầng Service xử lý logic nghiệp vụ cho User
 *
 * This layer sits between Controller and Repository, handling business rules
 * Tầng này nằm giữa Controller và Repository, xử lý các quy tắc nghiệp vụ
 *
 * @Service: Marks this class as a Spring service bean (đánh dấu lớp này là Spring service bean)
 */
@Service
public class UserService {

    /**
     * Inject UserRepository to interact with the database
     * Inject UserRepository để tương tác với cơ sở dữ liệu
     *
     * @Autowired: Spring automatically injects the dependency
     *             Spring tự động inject dependency
     */
    @Autowired
    private UserRepository userRepository;

    // ==================== CREATE / TẠO MỚI ====================

    /**
     * Create a new user in the database
     * Tạo mới một user trong cơ sở dữ liệu
     *
     * @param user The user object to create (Đối tượng user cần tạo)
     * @return The created user with generated ID (User đã tạo với ID được tự động sinh)
     */
    public User createUser(User user) {
        return userRepository.save(user);
    }

    // ==================== READ / ĐỌC ====================

    /**
     * Retrieve all users from the database
     * Lấy tất cả users từ cơ sở dữ liệu
     *
     * @return List of all users (Danh sách tất cả users)
     */
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    /**
     * Find a user by their ID
     * Tìm user theo ID
     *
     * @param id The user's ID (ID của user)
     * @return Optional containing the user if found (Optional chứa user nếu tìm thấy)
     */
    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    /**
     * Find a user by their email address
     * Tìm user theo địa chỉ email
     *
     * @param email The email address to search for (Địa chỉ email cần tìm)
     * @return Optional containing the user if found (Optional chứa user nếu tìm thấy)
     */
    public Optional<User> getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    // ==================== UPDATE / CẬP NHẬT ====================

    /**
     * Update an existing user's information
     * Cập nhật thông tin của một user hiện tại
     *
     * Steps / Các bước:
     * 1. Find the existing user by ID (Tìm user hiện tại theo ID)
     * 2. If not found, throw RuntimeException (Nếu không tìm thấy, ném RuntimeException)
     * 3. Update all fields with new values (Cập nhật tất cả các trường với giá trị mới)
     * 4. Save and return updated user (Lưu và trả về user đã cập nhật)
     *
     * @param id          The ID of the user to update (ID của user cần cập nhật)
     * @param userDetails The new user data (Dữ liệu user mới)
     * @return The updated user (User đã được cập nhật)
     * @throws RuntimeException if user not found (nếu không tìm thấy user)
     */
    public User updateUser(Long id, User userDetails) {
        // Find existing user or throw exception
        // Tìm user hiện tại hoặc ném ngoại lệ
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));

        // Update fields with new values
        // Cập nhật các trường với giá trị mới
        user.setName(userDetails.getName());
        user.setEmail(userDetails.getEmail());
        user.setPhone(userDetails.getPhone());
        user.setAddress(userDetails.getAddress());

        // Save updated user to database and return
        // Lưu user đã cập nhật vào database và trả về
        return userRepository.save(user);
    }

    // ==================== DELETE / XÓA ====================

    /**
     * Delete a user by their ID
     * Xóa user theo ID
     *
     * @param id The ID of the user to delete (ID của user cần xóa)
     * @throws RuntimeException if user not found (nếu không tìm thấy user)
     */
    public void deleteUser(Long id) {
        // Find existing user or throw exception
        // Tìm user hiện tại hoặc ném ngoại lệ
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));

        // Delete user from database
        // Xóa user khỏi database
        userRepository.delete(user);
    }
}

