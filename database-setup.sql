-- SQL Script để tạo database và table cho User CRUD
-- Hibernate sẽ tự động tạo table, nhưng file này có thể dùng để tạo thủ công nếu cần

-- Tạo database
CREATE DATABASE IF NOT EXISTS demo_java;
USE demo_java;

-- Tạo bảng users
CREATE TABLE IF NOT EXISTS users (
    id BIGINT NOT NULL AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    phone VARCHAR(255),
    address VARCHAR(255),
    PRIMARY KEY (id),
    UNIQUE KEY uk_email (email)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ================================================================
-- Tạo bảng posts (bài viết của user)
-- posts.user_id là khóa ngoại (FOREIGN KEY) tham chiếu tới users.id
-- Quan hệ: 1 user -> nhiều posts (One-to-Many)
-- ================================================================
CREATE TABLE IF NOT EXISTS posts (
    id         BIGINT       NOT NULL AUTO_INCREMENT,
    title      VARCHAR(255) NOT NULL,                  -- Tiêu đề bài viết
    content    TEXT,                                   -- Nội dung bài viết (TEXT để lưu nội dung dài)
    status     VARCHAR(50)  NOT NULL DEFAULT 'PUBLISHED', -- Trạng thái: PUBLISHED, DRAFT, ARCHIVED
    created_at DATETIME,                               -- Thời điểm tạo (tự động set bởi JPA @PrePersist)
    updated_at DATETIME,                               -- Thời điểm cập nhật (tự động set bởi JPA @PreUpdate)
    user_id    BIGINT       NOT NULL,                  -- Khóa ngoại tham chiếu tới bảng users
    PRIMARY KEY (id),
    CONSTRAINT fk_posts_user
        FOREIGN KEY (user_id) REFERENCES users (id)
            ON DELETE CASCADE                          -- Xóa user -> tự động xóa tất cả posts của user đó
            ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Insert dữ liệu mẫu cho users (optional)
INSERT INTO users (name, email, phone, address) VALUES
('Nguyễn Văn A', 'nguyenvana@example.com', '0123456789', 'Hà Nội, Việt Nam'),
('Trần Thị B', 'tranthib@example.com', '0912345678', 'Đà Nẵng, Việt Nam'),
('Lê Văn C', 'levanc@example.com', '0934567890', 'TP. Hồ Chí Minh, Việt Nam');

-- Insert dữ liệu mẫu cho posts (optional) - user_id phải khớp với id trong bảng users
INSERT INTO posts (title, content, status, created_at, updated_at, user_id) VALUES
('Bài viết đầu tiên của A', 'Đây là nội dung bài viết đầu tiên của Nguyễn Văn A.', 'PUBLISHED', NOW(), NOW(), 1),
('Bài viết thứ hai của A', 'Đây là bài viết nháp, chưa được đăng.', 'DRAFT', NOW(), NOW(), 1),
('Bài viết của B', 'Nội dung bài viết của Trần Thị B tại Đà Nẵng.', 'PUBLISHED', NOW(), NOW(), 2),
('Bài viết của C', 'Nội dung bài viết của Lê Văn C tại TP.HCM.', 'PUBLISHED', NOW(), NOW(), 3);

-- ================================================================
-- Xem dữ liệu
-- ================================================================
SELECT * FROM users;
SELECT * FROM posts;

-- Xem tất cả bài viết kèm thông tin user (JOIN)
SELECT
    p.id        AS post_id,
    p.title     AS post_title,
    p.status    AS post_status,
    p.created_at,
    u.id        AS user_id,
    u.name      AS author_name,
    u.email     AS author_email
FROM posts p
INNER JOIN users u ON p.user_id = u.id
ORDER BY p.created_at DESC;

-- Xem tất cả bài viết của một user cụ thể (ví dụ user_id = 1)
SELECT * FROM posts WHERE user_id = 1;

-- ================================================================
-- Các câu query hữu ích cho users:
-- ================================================================

-- Đếm số lượng users
SELECT COUNT(*) as total_users FROM users;

-- Tìm user theo email
SELECT * FROM users WHERE email = 'nguyenvana@example.com';

-- Đếm số bài viết của từng user
SELECT u.name, COUNT(p.id) AS total_posts
FROM users u
LEFT JOIN posts p ON u.id = p.user_id
GROUP BY u.id, u.name;

-- ================================================================
-- Dọn dẹp (cẩn thận!):
-- ================================================================

-- Xóa tất cả dữ liệu posts
TRUNCATE TABLE posts;

-- Xóa tất cả dữ liệu users (sẽ cascade xóa posts luôn do ON DELETE CASCADE)
TRUNCATE TABLE users;

-- Drop tables (thứ tự: posts trước, users sau vì có foreign key)
DROP TABLE IF EXISTS posts;
DROP TABLE IF EXISTS users;

-- Drop database (cẩn thận!)
DROP DATABASE IF EXISTS demo_java;



