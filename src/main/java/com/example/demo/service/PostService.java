package com.example.demo.service;

import com.example.demo.model.Post;
import com.example.demo.model.User;
import com.example.demo.repository.PostRepository;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Service layer for Post business logic
 * Tầng Service xử lý logic nghiệp vụ cho bài viết (Post)
 *
 * This layer sits between Controller and Repository, handling business rules
 * Tầng này nằm giữa Controller và Repository, xử lý các quy tắc nghiệp vụ
 *
 * @Service: Marks this class as a Spring service bean (đánh dấu lớp này là Spring service bean)
 */
@Service
public class PostService {

    /**
     * Inject PostRepository to interact with posts table in database
     * Inject PostRepository để tương tác với bảng posts trong database
     */
    @Autowired
    private PostRepository postRepository;

    /**
     * Inject UserRepository to validate user existence before creating posts
     * Inject UserRepository để xác thực user tồn tại trước khi tạo bài viết
     */
    @Autowired
    private UserRepository userRepository;

    // ==================== CREATE / TẠO MỚI ====================

    /**
     * Create a new post for a specific user
     * Tạo mới một bài viết cho một user cụ thể
     *
     * Business rules / Quy tắc nghiệp vụ:
     * 1. Validate that the user exists (Xác thực rằng user tồn tại)
     * 2. Associate the post with the user (Liên kết bài viết với user)
     * 3. Save to database (Lưu vào database)
     *
     * @param userId The ID of the user creating the post (ID của user tạo bài viết)
     * @param post   The post data to create (Dữ liệu bài viết cần tạo)
     * @return The created post with generated ID (Bài viết đã tạo với ID được sinh ra)
     * @throws RuntimeException if user not found (nếu không tìm thấy user)
     */
    public Post createPost(Long userId, Post post) {
        // Find user by ID, throw exception if not found
        // Tìm user theo ID, ném ngoại lệ nếu không tìm thấy
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + userId));

        // Associate the post with the user (set foreign key)
        // Liên kết bài viết với user (gán khóa ngoại)
        post.setUser(user);

        // Save and return the created post
        // Lưu và trả về bài viết đã tạo
        return postRepository.save(post);
    }

    // ==================== READ ALL / ĐỌC TẤT CẢ ====================

    /**
     * Retrieve all posts from the database
     * Lấy tất cả bài viết từ cơ sở dữ liệu
     *
     * @return List of all posts (Danh sách tất cả bài viết)
     */
    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    // ==================== READ BY USER / ĐỌC THEO USER ====================

    /**
     * Retrieve all posts belonging to a specific user
     * Lấy tất cả bài viết của một user cụ thể
     *
     * Business rule: Validate user exists before fetching posts
     * Quy tắc nghiệp vụ: Xác thực user tồn tại trước khi lấy bài viết
     *
     * @param userId The ID of the user (ID của user)
     * @return List of posts for that user (Danh sách bài viết của user đó)
     * @throws RuntimeException if user not found (nếu không tìm thấy user)
     */
    public List<Post> getPostsByUserId(Long userId) {
        // Check if user exists first
        // Kiểm tra user có tồn tại không trước
        if (!userRepository.existsById(userId)) {
            throw new RuntimeException("User not found with id: " + userId);
        }
        return postRepository.findByUserId(userId);
    }

    /**
     * Retrieve posts of a user filtered by status
     * Lấy bài viết của user lọc theo trạng thái
     *
     * @param userId The ID of the user (ID của user)
     * @param status Post status: PUBLISHED, DRAFT, ARCHIVED (Trạng thái bài viết)
     * @return List of filtered posts (Danh sách bài viết đã lọc)
     */
    public List<Post> getPostsByUserIdAndStatus(Long userId, String status) {
        if (!userRepository.existsById(userId)) {
            throw new RuntimeException("User not found with id: " + userId);
        }
        return postRepository.findByUserIdAndStatus(userId, status);
    }

    // ==================== READ BY ID / ĐỌC THEO ID ====================

    /**
     * Find a post by its ID
     * Tìm bài viết theo ID
     *
     * @param id The post's ID (ID của bài viết)
     * @return Optional containing the post if found (Optional chứa bài viết nếu tìm thấy)
     */
    public Optional<Post> getPostById(Long id) {
        return postRepository.findById(id);
    }

    // ==================== UPDATE / CẬP NHẬT ====================

    /**
     * Update an existing post's information
     * Cập nhật thông tin của một bài viết hiện tại
     *
     * Business rules / Quy tắc nghiệp vụ:
     * 1. Find the existing post by ID (Tìm bài viết hiện tại theo ID)
     * 2. Validate the post belongs to the given user (Xác thực bài viết thuộc về user đã cho)
     * 3. Update only title, content, status - NOT the author (Chỉ cập nhật tiêu đề, nội dung, trạng thái - KHÔNG cập nhật tác giả)
     * 4. Save and return updated post (Lưu và trả về bài viết đã cập nhật)
     *
     * @param userId      The ID of the user who owns the post (ID của user sở hữu bài viết)
     * @param postId      The ID of the post to update (ID của bài viết cần cập nhật)
     * @param postDetails The new post data (Dữ liệu bài viết mới)
     * @return The updated post (Bài viết đã được cập nhật)
     * @throws RuntimeException if post not found or user is not the owner
     *                          nếu không tìm thấy bài viết hoặc user không phải chủ sở hữu
     */
    public Post updatePost(Long userId, Long postId, Post postDetails) {
        // Find post by ID or throw exception
        // Tìm bài viết theo ID hoặc ném ngoại lệ
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new RuntimeException("Post not found with id: " + postId));

        // Verify the post belongs to the given user (authorization check)
        // Xác thực bài viết thuộc về user đã cho (kiểm tra quyền)
        if (!post.getUser().getId().equals(userId)) {
            throw new RuntimeException("Post does not belong to user with id: " + userId);
        }

        // Update allowed fields only (title, content, status)
        // Chỉ cập nhật các trường được phép (tiêu đề, nội dung, trạng thái)
        post.setTitle(postDetails.getTitle());
        post.setContent(postDetails.getContent());
        post.setStatus(postDetails.getStatus());

        // Save and return updated post
        // Lưu và trả về bài viết đã cập nhật
        return postRepository.save(post);
    }

    // ==================== DELETE / XÓA ====================

    /**
     * Delete a post by its ID, ensuring user ownership
     * Xóa bài viết theo ID, đảm bảo quyền sở hữu của user
     *
     * Business rule: Only the post owner (user) can delete their post
     * Quy tắc nghiệp vụ: Chỉ chủ sở hữu bài viết (user) mới có thể xóa bài viết của mình
     *
     * @param userId The ID of the user (ID của user)
     * @param postId The ID of the post to delete (ID của bài viết cần xóa)
     * @throws RuntimeException if post not found or user is not the owner
     *                          nếu không tìm thấy bài viết hoặc user không phải chủ sở hữu
     */
    public void deletePost(Long userId, Long postId) {
        // Find post by ID or throw exception
        // Tìm bài viết theo ID hoặc ném ngoại lệ
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new RuntimeException("Post not found with id: " + postId));

        // Verify the post belongs to the given user (authorization check)
        // Xác thực bài viết thuộc về user đã cho (kiểm tra quyền)
        if (!post.getUser().getId().equals(userId)) {
            throw new RuntimeException("Post does not belong to user with id: " + userId);
        }

        // Delete the post from database
        // Xóa bài viết khỏi database
        postRepository.delete(post);
    }

    /**
     * Delete all posts of a specific user
     * Xóa tất cả bài viết của một user cụ thể
     *
     * @param userId The ID of the user (ID của user)
     */
    public void deleteAllPostsByUserId(Long userId) {
        // Get all posts of the user and delete them
        // Lấy tất cả bài viết của user và xóa chúng
        List<Post> posts = postRepository.findByUserId(userId);
        postRepository.deleteAll(posts);
    }
}

