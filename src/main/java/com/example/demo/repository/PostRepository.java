package com.example.demo.repository;

import com.example.demo.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository interface for Post entity - handles all database operations
 * Interface Repository cho entity Post - xử lý tất cả các thao tác với database
 *
 * Extends JpaRepository which provides built-in CRUD methods:
 * Kế thừa JpaRepository cung cấp sẵn các phương thức CRUD:
 *   - save(entity)    : Create or Update (Tạo hoặc Cập nhật)
 *   - findById(id)    : Find by primary key (Tìm theo khóa chính)
 *   - findAll()       : Get all records (Lấy tất cả bản ghi)
 *   - delete(entity)  : Delete a record (Xóa một bản ghi)
 *
 * @Repository: Marks this interface as a Spring Data repository bean
 *              Đánh dấu interface này là một Spring Data repository bean
 *
 * JpaRepository<Post, Long>:
 *   - Post: The entity type this repository manages (Loại entity mà repository này quản lý)
 *   - Long: The type of the primary key (Kiểu dữ liệu của khóa chính)
 */
@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

    /**
     * Find all posts by a specific user ID
     * Tìm tất cả bài viết của một user theo ID
     *
     * Spring Data JPA auto-generates SQL:
     * SELECT * FROM posts WHERE user_id = ?
     *
     * @param userId The ID of the user (ID của user)
     * @return List of posts belonging to that user (Danh sách bài viết thuộc về user đó)
     */
    List<Post> findByUserId(Long userId);

    /**
     * Find all posts by a specific user ID and status
     * Tìm tất cả bài viết của một user theo ID và trạng thái
     *
     * Spring Data JPA auto-generates SQL:
     * SELECT * FROM posts WHERE user_id = ? AND status = ?
     *
     * @param userId The ID of the user (ID của user)
     * @param status The post status: PUBLISHED, DRAFT, ARCHIVED (Trạng thái bài viết)
     * @return List of filtered posts (Danh sách bài viết đã lọc)
     */
    List<Post> findByUserIdAndStatus(Long userId, String status);

    /**
     * Find all posts by status across all users
     * Tìm tất cả bài viết theo trạng thái của tất cả users
     *
     * Spring Data JPA auto-generates SQL:
     * SELECT * FROM posts WHERE status = ?
     *
     * @param status The post status (Trạng thái bài viết)
     * @return List of posts with given status (Danh sách bài viết với trạng thái đã cho)
     */
    List<Post> findByStatus(String status);

    /**
     * Check if a user exists by ID (used for validation)
     * Kiểm tra xem user có tồn tại không theo ID (dùng để xác thực)
     *
     * @param userId The ID of the user (ID của user)
     * @return true if any post exists for the user (true nếu có bài viết của user)
     */
    boolean existsByUserId(Long userId);
}

