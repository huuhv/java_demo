# Tóm tắt: Tạo CRUD cho User trong Spring Boot

## ✅ Các file đã được tạo/cập nhật:

### 1. **pom.xml** - Đã cập nhật
- ✅ Thêm dependency `spring-boot-starter-data-jpa` để hỗ trợ JPA/Hibernate

### 2. **User.java** - CREATED (Entity)
📁 Đường dẫn: `src/main/java/com/example/demo/User.java`

**Chức năng:**
- Entity đại diện cho bảng `users` trong database
- Các thuộc tính: id, name, email, phone, address
- Email là unique và cả name, email đều bắt buộc (not null)
- Có đầy đủ getters/setters và constructors

### 3. **UserRepository.java** - CREATED (Repository)
📁 Đường dẫn: `src/main/java/com/example/demo/UserRepository.java`

**Chức năng:**
- Interface kế thừa JpaRepository
- Tự động có các method: save(), findAll(), findById(), delete()
- Custom method: findByEmail(String email)

### 4. **UserService.java** - CREATED (Service Layer)
📁 Đường dẫn: `src/main/java/com/example/demo/UserService.java`

**Chức năng:**
- Xử lý business logic
- Các phương thức:
  * createUser() - Tạo user mới
  * getAllUsers() - Lấy tất cả users
  * getUserById() - Lấy user theo ID
  * getUserByEmail() - Lấy user theo email
  * updateUser() - Cập nhật user
  * deleteUser() - Xóa user

### 5. **UserController.java** - UPDATED (REST Controller)
📁 Đường dẫn: `src/main/java/com/example/demo/UserController.java`

**Chức năng:**
- Cung cấp RESTful API endpoints
- 6 endpoints chính:
  1. POST /api/users - Tạo user mới
  2. GET /api/users - Lấy tất cả users
  3. GET /api/users/{id} - Lấy user theo ID
  4. GET /api/users/email/{email} - Lấy user theo email
  5. PUT /api/users/{id} - Cập nhật user
  6. DELETE /api/users/{id} - Xóa user

### 6. **application.properties** - UPDATED
📁 Đường dẫn: `src/main/resources/application.properties`

**Cấu hình:**
- Database connection (MySQL)
- JPA/Hibernate settings
- Server port (8080)

### 7. **README_USER_API.md** - CREATED
📁 Đường dẫn: `README_USER_API.md`

**Nội dung:**
- Tài liệu chi tiết về API
- Hướng dẫn sử dụng từng endpoint
- Ví dụ request/response
- Hướng dẫn cài đặt và chạy

### 8. **user-api-tests.http** - CREATED
📁 Đường dẫn: `user-api-tests.http`

**Nội dung:**
- File test API có thể sử dụng trực tiếp trong IntelliJ IDEA hoặc VS Code
- Chứa sẵn các request mẫu để test tất cả endpoints

---

## 📋 Cấu trúc API đầy đủ:

```
BASE URL: http://localhost:8080

ENDPOINTS:
├── POST   /api/users              → Tạo user mới
├── GET    /api/users              → Lấy tất cả users
├── GET    /api/users/{id}         → Lấy user theo ID
├── GET    /api/users/email/{email}→ Lấy user theo email
├── PUT    /api/users/{id}         → Cập nhật user
└── DELETE /api/users/{id}         → Xóa user
```

---

## 🗄️ Database Schema:

Table: **users**
```sql
+----------+--------------+------+-----+---------+----------------+
| Field    | Type         | Null | Key | Default | Extra          |
+----------+--------------+------+-----+---------+----------------+
| id       | bigint       | NO   | PRI | NULL    | auto_increment |
| name     | varchar(255) | NO   |     | NULL    |                |
| email    | varchar(255) | NO   | UNI | NULL    |                |
| phone    | varchar(255) | YES  |     | NULL    |                |
| address  | varchar(255) | YES  |     | NULL    |                |
+----------+--------------+------+-----+---------+----------------+
```

---

## 🚀 Cách chạy ứng dụng:

### Bước 1: Đảm bảo có JDK (không phải JRE)
```bash
java -version  # Phải hiển thị JDK, không phải JRE
```

### Bước 2: Tạo database MySQL
```sql
CREATE DATABASE demo_java;
```

### Bước 3: Cấu hình database trong application.properties
Kiểm tra và cập nhật nếu cần:
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/demo_java
spring.datasource.username=root
spring.datasource.password=123456
```

### Bước 4: Chạy ứng dụng

**Cách 1: Dùng Maven**
```bash
# Windows PowerShell
.\mvnw.cmd spring-boot:run
```

**Cách 2: Dùng IDE**
- Mở file `DemoApplication.java`
- Click Run (hoặc Shift+F10 trong IntelliJ)

### Bước 5: Test API
Ứng dụng sẽ chạy tại: `http://localhost:8080`

**Test bằng cURL:**
```bash
# Tạo user mới
curl -X POST http://localhost:8080/api/users -H "Content-Type: application/json" -d '{\"name\":\"Test User\",\"email\":\"test@example.com\",\"phone\":\"0123456789\",\"address\":\"Ha Noi\"}'

# Lấy tất cả users
curl -X GET http://localhost:8080/api/users
```

**Test bằng file user-api-tests.http:**
- Mở file `user-api-tests.http` trong IntelliJ IDEA
- Click vào nút "Run" bên cạnh mỗi request

**Test bằng Postman:**
- Import các request từ README_USER_API.md

---

## 📝 Lưu ý quan trọng:

1. **JDK vs JRE**: Phải cài đặt JDK (Java Development Kit), không phải JRE (Java Runtime Environment)

2. **MySQL**: Đảm bảo MySQL đang chạy và đã tạo database `demo_java`

3. **Port 8080**: Đảm bảo port 8080 không bị sử dụng bởi ứng dụng khác

4. **Auto-create table**: Hibernate sẽ tự động tạo bảng `users` khi chạy lần đầu tiên

5. **Email unique**: Không thể tạo 2 users có cùng email

6. **Required fields**: name và email là bắt buộc khi tạo/update user

---

## 🔧 Troubleshooting:

**Lỗi: "No compiler is provided in this environment"**
- ➡️ Cài đặt JDK (không phải JRE)
- ➡️ Set JAVA_HOME environment variable đến JDK folder

**Lỗi: "Cannot resolve symbol"**
- ➡️ Reload Maven project trong IDE
- ➡️ Chạy: `.\mvnw.cmd clean install`

**Lỗi kết nối database**
- ➡️ Kiểm tra MySQL đang chạy
- ➡️ Kiểm tra username/password trong application.properties
- ➡️ Đảm bảo database `demo_java` đã được tạo

---

## ✨ Tính năng đã được implement:

✅ Create User (POST)
✅ Read All Users (GET)  
✅ Read User by ID (GET)
✅ Read User by Email (GET)
✅ Update User (PUT)
✅ Delete User (DELETE)
✅ Error handling
✅ HTTP status codes chuẩn
✅ Database auto-create schema
✅ Email validation (unique constraint)
✅ Required fields validation

---

## 📚 Tài liệu tham khảo:

1. **README_USER_API.md** - Chi tiết về API và cách sử dụng
2. **user-api-tests.http** - File test sẵn các endpoints
3. **Spring Boot Documentation**: https://spring.io/projects/spring-boot
4. **Spring Data JPA**: https://spring.io/projects/spring-data-jpa

---

**Tác giả:** GitHub Copilot
**Ngày tạo:** 11/02/2026
**Spring Boot Version:** 4.0.2
**Java Version:** 21

