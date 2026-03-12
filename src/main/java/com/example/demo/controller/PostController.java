package com.example.demo.controller;

import com.example.demo.model.Post;
import com.example.demo.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST Controller for managing Post CRUD operations
 * Controller REST để quản lý các thao tác CRUD của bài viết (Post)
 *
 * MVC Pattern / Mô hình MVC:
 *   - Model: Post entity (Thực thể Post)
 *   - View: JSON response (Phản hồi dạng JSON)
 *   - Controller: This class - handles HTTP requests (Lớp này - xử lý các yêu cầu HTTP)
 *
 * URL Structure / Cấu trúc URL:
 *   - Nested under user:  /api/users/{userId}/posts
 *   - Get single post:    /api/posts/{postId}
 *
 * This follows RESTful convention where posts are a sub-resource of users
 * Điều này tuân theo quy ước RESTful khi bài viết là tài nguyên con của user
 */
@RestController
public class PostController {

    /**
     * Inject PostService - business logic layer for posts
     * Inject PostService - tầng xử lý logic nghiệp vụ cho bài viết
     */
    @Autowired
    private PostService postService;

    // ==================== CREATE / TẠO MỚI ====================

    /**
     * Create a new post for a specific user
     * Tạo mới một bài viết cho một user cụ thể
     *
     * HTTP Method: POST
     * URL: POST /api/users/{userId}/posts
     * Path Variable: userId - The author's user ID (ID user tác giả)
     * Request Body: Post object in JSON format (Đối tượng Post dạng JSON)
     * Response: Created post with HTTP 201 (CREATED) status
     *
     * Example Request Body / Ví dụ Request Body:
     * {
     *   "title": "My First Post",
     *   "content": "This is the content of my post",
     *   "status": "PUBLISHED"
     * }
     *
     * @param userId The user ID from URL path (ID user từ đường dẫn URL)
     * @param post   The post data from request body (Dữ liệu bài viết từ request body)
     * @return ResponseEntity containing created post (ResponseEntity chứa bài viết đã tạo)
     */
    @PostMapping("/api/users/{userId}/posts")
    public ResponseEntity<Post> createPost(
            @PathVariable("userId") Long userId,
            @RequestBody Post post) {
        try {
            // Call service to create post associated with the user
            // Gọi service để tạo bài viết liên kết với user
            Post createdPost = postService.createPost(userId, post);

            // Return created post with HTTP 201 CREATED
            // Trả về bài viết đã tạo với HTTP 201 CREATED
            return new ResponseEntity<>(createdPost, HttpStatus.CREATED);
        } catch (RuntimeException e) {
            // User not found or invalid data
            // Không tìm thấy user hoặc dữ liệu không hợp lệ
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // ==================== READ ALL / ĐỌC TẤT CẢ ====================

    /**
     * Retrieve ALL posts (from all users)
     * Lấy TẤT CẢ bài viết (của tất cả users)
     *
     * HTTP Method: GET
     * URL: GET /api/posts
     * Response: List of all posts (HTTP 200) or no content (HTTP 204)
     *
     * @return ResponseEntity containing list of all posts
     */
    @GetMapping("/api/posts")
    public ResponseEntity<List<Post>> getAllPosts() {
        try {
            List<Post> posts = postService.getAllPosts();

            if (posts.isEmpty()) {
                // No posts found - return HTTP 204 NO_CONTENT
                // Không có bài viết nào - trả về HTTP 204 NO_CONTENT
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            // Return all posts with HTTP 200 OK
            // Trả về tất cả bài viết với HTTP 200 OK
            return new ResponseEntity<>(posts, HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // ==================== READ BY USER / ĐỌC THEO USER ====================

    /**
     * Retrieve all posts belonging to a specific user
     * Lấy tất cả bài viết của một user cụ thể
     *
     * HTTP Method: GET
     * URL: GET /api/users/{userId}/posts
     * Path Variable: userId - The user's ID (ID của user)
     * Optional Query Param: status - Filter by post status (Lọc theo trạng thái)
     * Response: List of user's posts (HTTP 200) or not found (HTTP 404)
     *
     * Example URLs:
     *   GET /api/users/1/posts              -> All posts of user 1
     *   GET /api/users/1/posts?status=DRAFT -> Only DRAFT posts of user 1
     *
     * @param userId The user's ID from URL path (ID của user từ đường dẫn URL)
     * @param status Optional filter by status (Tùy chọn lọc theo trạng thái)
     * @return ResponseEntity containing list of posts
     */
    @GetMapping("/api/users/{userId}/posts")
    public ResponseEntity<List<Post>> getPostsByUserId(
            @PathVariable("userId") Long userId,
            @RequestParam(value = "status", required = false) String status) {
        try {
            List<Post> posts;

            if (status != null && !status.isEmpty()) {
                // Filter by status if provided
                // Lọc theo trạng thái nếu được cung cấp
                posts = postService.getPostsByUserIdAndStatus(userId, status);
            } else {
                // Get all posts of the user
                // Lấy tất cả bài viết của user
                posts = postService.getPostsByUserId(userId);
            }

            if (posts.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(posts, HttpStatus.OK);
        } catch (RuntimeException e) {
            // User not found
            // Không tìm thấy user
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // ==================== READ BY POST ID / ĐỌC THEO ID BÀI VIẾT ====================

    /**
     * Retrieve a single post by its ID
     * Lấy một bài viết theo ID của nó
     *
     * HTTP Method: GET
     * URL: GET /api/posts/{postId}
     * Path Variable: postId - The post's unique ID (ID duy nhất của bài viết)
     * Response: Post object (HTTP 200) or not found (HTTP 404)
     *
     * @param postId The post's ID from URL path (ID của bài viết từ đường dẫn URL)
     * @return ResponseEntity containing the post or not found status
     */
    @GetMapping("/api/posts/{postId}")
    public ResponseEntity<Post> getPostById(@PathVariable("postId") Long postId) {
        try {
            return postService.getPostById(postId)
                    // If found: wrap in ResponseEntity with HTTP 200 OK
                    // Nếu tìm thấy: đóng gói với HTTP 200 OK
                    .map(post -> new ResponseEntity<>(post, HttpStatus.OK))
                    // If not found: return HTTP 404 NOT_FOUND
                    // Nếu không tìm thấy: trả về HTTP 404 NOT_FOUND
                    .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // ==================== UPDATE / CẬP NHẬT ====================

    /**
     * Update an existing post (only by the post owner)
     * Cập nhật một bài viết hiện tại (chỉ bởi chủ sở hữu bài viết)
     *
     * HTTP Method: PUT
     * URL: PUT /api/users/{userId}/posts/{postId}
     * Path Variables:
     *   - userId: The user who owns the post (User sở hữu bài viết)
     *   - postId: The post to update (Bài viết cần cập nhật)
     * Request Body: Updated post data in JSON (Dữ liệu bài viết cập nhật dạng JSON)
     * Response: Updated post (HTTP 200), not found (HTTP 404), or forbidden (HTTP 403)
     *
     * @param userId     The user's ID from URL (ID của user từ URL)
     * @param postId     The post's ID from URL (ID của bài viết từ URL)
     * @param postDetails The updated post data (Dữ liệu bài viết đã cập nhật)
     * @return ResponseEntity containing updated post or error status
     */
    @PutMapping("/api/users/{userId}/posts/{postId}")
    public ResponseEntity<Post> updatePost(
            @PathVariable("userId") Long userId,
            @PathVariable("postId") Long postId,
            @RequestBody Post postDetails) {
        try {
            // Call service to update the post (with ownership validation)
            // Gọi service để cập nhật bài viết (kèm xác thực quyền sở hữu)
            Post updatedPost = postService.updatePost(userId, postId, postDetails);

            // Return updated post with HTTP 200 OK
            // Trả về bài viết đã cập nhật với HTTP 200 OK
            return new ResponseEntity<>(updatedPost, HttpStatus.OK);
        } catch (RuntimeException e) {
            // Post not found or user is not the owner
            // Không tìm thấy bài viết hoặc user không phải chủ sở hữu
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // ==================== DELETE / XÓA ====================

    /**
     * Delete a post (only by the post owner)
     * Xóa một bài viết (chỉ bởi chủ sở hữu bài viết)
     *
     * HTTP Method: DELETE
     * URL: DELETE /api/users/{userId}/posts/{postId}
     * Path Variables:
     *   - userId: The user who owns the post (User sở hữu bài viết)
     *   - postId: The post to delete (Bài viết cần xóa)
     * Response: HTTP 204 (success), HTTP 404 (not found)
     *
     * @param userId The user's ID from URL (ID của user từ URL)
     * @param postId The post's ID from URL (ID của bài viết từ URL)
     * @return ResponseEntity with appropriate HTTP status
     */
    @DeleteMapping("/api/users/{userId}/posts/{postId}")
    public ResponseEntity<HttpStatus> deletePost(
            @PathVariable("userId") Long userId,
            @PathVariable("postId") Long postId) {
        try {
            // Call service to delete the post (with ownership validation)
            // Gọi service để xóa bài viết (kèm xác thực quyền sở hữu)
            postService.deletePost(userId, postId);

            // Return HTTP 204 NO_CONTENT (deleted successfully)
            // Trả về HTTP 204 NO_CONTENT (xóa thành công)
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (RuntimeException e) {
            // Post not found or user is not the owner
            // Không tìm thấy bài viết hoặc user không phải chủ sở hữu
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

