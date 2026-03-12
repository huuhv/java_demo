package com.example.demo.repository;

import com.example.demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repository interface for User entity - handles all database operations
 * Interface Repository cho entity User - xử lý tất cả các thao tác với database
 *
 * Extends JpaRepository which provides built-in CRUD methods:
 * Kế thừa JpaRepository cung cấp sẵn các phương thức CRUD:
 *   - save(entity)      : Create or Update (Tạo hoặc Cập nhật)
 *   - findById(id)      : Find by primary key (Tìm theo khóa chính)
 *   - findAll()          : Get all records (Lấy tất cả bản ghi)
 *   - delete(entity)     : Delete a record (Xóa một bản ghi)
 *   - count()            : Count all records (Đếm tất cả bản ghi)
 *
 * @Repository: Marks this interface as a Spring Data repository bean
 *              Đánh dấu interface này là một Spring Data repository bean
 *
 * JpaRepository<User, Long>:
 *   - User: The entity type this repository manages (Loại entity mà repository này quản lý)
 *   - Long: The type of the primary key (Kiểu dữ liệu của khóa chính)
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * Custom query method: Find a user by email address
     * Phương thức truy vấn tùy chỉnh: Tìm user theo địa chỉ email
     *
     * Spring Data JPA automatically generates the SQL query based on method name
     * Spring Data JPA tự động tạo câu truy vấn SQL dựa trên tên phương thức
     *
     * Generated SQL: SELECT * FROM users WHERE email = ?
     *
     * @param email The email address to search for (Địa chỉ email cần tìm)
     * @return Optional<User> - Contains user if found, empty if not
     *         Optional<User> - Chứa user nếu tìm thấy, rỗng nếu không
     */
    Optional<User> findByEmail(String email);
}

